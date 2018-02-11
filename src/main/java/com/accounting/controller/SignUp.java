/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.accounting.controller;
import com.accounting.bean.BuyerMaster;
import com.accounting.bean.MasterFromServer;
import com.accounting.bean.UserMaster;
import com.accounting.ed.Encryptor;
import com.accounting.ed.GetSystemData;
import com.accounting.encrypt_decrypt.PasswordEncrypt_Decrypt;
import com.accounting.service.AccountDBOService;
import com.accounting.service.Buyer_service;
import com.accounting.service.HomeService;
import com.accounting.service.SignUpService;
import com.accounting.service.UserControllerService;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author MR
 */

@Controller
public class SignUp {
    
     @Autowired
  private SignUpService signUpService;
       @Autowired
    private AccountDBOService as;
       @Autowired
        HomeService homeService;
         @Autowired
  private UserControllerService userControllerService;
     
       @RequestMapping(value={"/SignUp"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView signUp(HttpServletRequest req,RedirectAttributes redirectAttributes) throws IOException, ParseException
  {   String  PropFile = req.getServletContext().getRealPath("/")+"//conf//conf.properties";
      if(as.CheckValidLicens(PropFile))
        { 
         ModelAndView model = new ModelAndView("redirect:Index.html");      
         return model;
        }
       else
        {
       Boolean db=this.homeService.ChkRole("superadmin");
       String  propertiesFilePath = req.getServletContext().getRealPath("/")+"//conf//conf.properties";
       File f=new File(propertiesFilePath);
         if(db && f.exists())
         {
           UserMaster user = new UserMaster();  
           ModelAndView model = new ModelAndView("redirect:LicenseExpired.html");
           redirectAttributes.addFlashAttribute("Message", "License Expired Contact Software Administrator");
           return model;
         }
         else
         {
            UserMaster userMaster=new UserMaster();
            ModelAndView model = new ModelAndView("SignUp");
            model.addObject("signUp", userMaster);
            return model;
         }
        }
     
  }
    
    @RequestMapping(value={"sign_Up"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public String saveUser(@ModelAttribute("signUp") @Validated UserMaster user,HttpServletRequest req,RedirectAttributes redirectAttributes) throws FileNotFoundException, UnsupportedEncodingException, IOException
  {
                                  user.setPassword(PasswordEncrypt_Decrypt.encrypt(user.getPassword()));
                                  user.setUserid(null);
                                  user.setRole("superadmin");
                                  user.setGroupName("superadmin");
                                  user.setStatus(1);
                                  user.setGroupid("1");

                                   String query4="INSERT INTO unit_master(id_unit,decimal_nos,unit_name,unit_symbol) VALUE (1,1,'Liter','LTR'),(2,2,'Large','LA'),(3,1,'Bottel','BOT'),(4,2,'Alt Bottle','BT'),(5,1,'Plate','PLA'),(6,2,'Alt Plate','PL'),(7,2,'ml','ML')";
                                   signUpService.accesscontrol(query4);
                                    
                                    String query5=" INSERT  INTO access_control(`Access_id`,`Module_Name`,`Created_on`) VALUES (1,'Company Information','2017-10-10 01:00:00'),(2,'Unit Master','2017-10-10 01:00:00'), (3,'Item Groups','2017-10-10 01:00:00'),(4,'Commodity Code','2017-10-10 01:00:00'), (5,'Item Quick Edit','2017-10-10 01:00:00'),(6,'ItemMaster','2017-10-10 01:00:00'), (7,'Buyer Master','2017-10-10 01:00:00'),(8,'Supplier Master','2017-10-10 01:00:00'),( 9,'Purchase','2017-10-10 01:00:00'),(10,'Default Setting','2017-10-10 01:00:00'),(11,'Access Control','2017-10-10 01:00:00'), (12,'Group Details','2017-10-10 01:00:00'),(13,'User Master','2017-10-10 01:00:00'),(14,'ReversalOfItc ','2017-10-10 01:00:00'), (15,'StockReport ','2017-10-10 01:00:00'),(16,'ReversalOfItcGrid ','2017-10-10 01:00:00'),(17,'ReversalOfItc ','2017-10-10 01:00:00'), (18,'TableMaster ','2017-10-10 01:00:00'),(19,'WaiterMaster ','2017-10-10 01:00:00'),(20,'KotForm ','2017-10-10 01:00:00'), (21,'SalesInvoicelist ','2017-10-10 01:00:00'),(22,'AuditSalesInvoicelist ','2017-10-10 01:00:00'), (23,'PurchaseGrid ','2017-10-10 01:00:00')";
                                    signUpService.accesscontrol(query5);
                                    String query6="INSERT  INTO `access_control_master_table`(`AccessControl_id`,`Access_id`,`Create_value`,`Delete_value`,`Edit_value`,`Group_id`,`Module_Name`,`View_value`)VALUES(1,'1','1','1','1',1,'Company Information','1'),(2,'2','1','1','1',1,'Unit Master','1'), (3,'3','1','1','1',1,'Item Groups','1'),(4,'4','1','1','1',1,'Commodity Code','1'), (5,'5','1','1','1',1,'Item Quick Edit','1'),(6,'6','1','1','1',1,'ItemMaster','1'), (7,'7','1','1','1',1,'Buyer Master','1'),(8,'8','1','1','1',1,'Supplier Master','1'),(10,'10','1','1','1',1,'Default Setting','1'),(9,'9','1','1','1',1,'Purchase','1'),(11,'11','1','1','1',1,'Access Control','1'),(12,'12','1','1','1',1,'Group Details','1'),(13,'13','1','1','1',1,'User Master','1'),(14,'14','1','1','1',1,'ReversalOfItc','1'),(15,'15','1','1','1',1,'StockReport','1'),(16,'16','1','1','1',1,'ReversalOfItcGrid','1'),(17,'17','1','1','1',1,'ReversalOfItc','1'),(18,'18','1','1','1',1,'TableMaster','1'),(19,'19','1','1','1',1,'WaiterMaster','1'),(20,'20','1','1','1',1,'KotForm','1'),(21,'21','1','1','1',1,'SalesInvoicelist','1'),(22,'22','1','1','1',1,'AuditSalesInvoicelist','1'),(23,'23','1','1','1',1,'PurchaseGrid','1')";      

                                    signUpService.accesscontrolmastertable(query6);
                                     String query7="insert  into `group_table`(`Group_Name`,`Created_on`,`Group_status`,`Group_id`) values ('superadmin',NULL,'Active',1)";       
                                    signUpService.grouptable(query7);
                                     String query8="insert  into `itc_reversal_master`(`id_itc`,`category_name`,`descriptions`,`sec_code`,`under_category`,`predefined`) values (1,'PURCHASE RETURN','use','1',0,0),(2,'SELF USE','23423','2',0,0),(4,'GIFT-FREE SAMPLE',NULL,'4',NULL,0),(5,'THEFT OF LOSS',NULL,'5',NULL,0),(6,'DESTROYED IN FIRE','','6',0,0),(7,'TRANSIT DAMAGE',NULL,'7',NULL,0),(8,'EXEMPTED GOODS PRODUCTION',NULL,'8',NULL,0),(9,'PURCHASE OF AUTOMOBILES',NULL,'9',NULL,0),(10,'PURCHASE OF AIR CONDITIONERS',NULL,'10',NULL,0),(11,'DESTROYED IN MANUFACTURING',NULL,'11',NULL,0),(12,'UNDER CATEGORY',NULL,'12',NULL,0),(3,'CIVIL STRUCTURE','','3',0,0)";        
                                    signUpService.itcreversalmaster(query8);
                                   // for insert into item group
                                    String query_9="INSERT INTO item_group_master(id_item,group_under,item_group_name) VALUES(1,'PRIMARY','BEVRAGES'),(2,'PRIMARY','KITCHEN'),(3,'BEVRAGES','BEER'),(4,'BEVRAGES','WINE'),(5,'BEVRAGES','HOT DRINKS'),(6,'PRIMARY','SOFT DRINKS')";        
                                    signUpService.itcreversalmaster(query_9);    
                               
                                    signUpService.addUserDerails(user);

        return "redirect:Index.html";
}
}
