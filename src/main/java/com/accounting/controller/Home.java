/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.controller;

import com.accounting.bean.UserMaster;
import com.accounting.encrypt_decrypt.PasswordEncrypt_Decrypt;
import com.accounting.service.AccountDBOService;
import com.accounting.service.HomeService;
import com.accounting.validator.UserFormValidator;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author SHINELOGICS
 */
@Controller
public class Home {
    
    @Autowired
	UserFormValidator userFormValidator;
     @Autowired
        HomeService homeService;
      @Autowired
    private AccountDBOService as;
    //Set a form validator
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
            binder.setValidator(userFormValidator);
    }

    
  @RequestMapping(value={"/"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView home()
  {
      UserMaster user = new UserMaster();

    ModelAndView model = new ModelAndView("redirect:Index.html");
    model.addObject("userForm", user);
    return model;
  }
      
  @RequestMapping(value={"LicenseExpired"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView LicenseExpired()
  {
    UserMaster user = new UserMaster();
    ModelAndView model = new ModelAndView("LicenseExpired");    
    return model;
  }
  
  @RequestMapping(value={"Index"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView indexPage(HttpServletRequest req,RedirectAttributes redirectAttributes) throws IOException, ParseException
  {
       UserMaster user = new UserMaster();
       String role="superadmin";
       Boolean db=this.homeService.ChkRole(role);
//       String  PropFile = req.getServletContext().getRealPath("/")+"//conf//conf.properties";
     //  System.out.println(" License Is there "+as.CheckValidLicens(PropFile));
//       if(as.CheckValidLicens(PropFile))
//       {   
       if(db)
       {
          ModelAndView model = new ModelAndView("Index");
           model.addObject("userForm", user);
          return model;
//        }
       }
            
            ModelAndView model = new ModelAndView("redirect:SignUp.html");
//            redirectAttributes.addFlashAttribute("Message","Invalid License");
            return model;
         
  }
  
  @RequestMapping(value={"/verify"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView save(@ModelAttribute("userForm") @Validated UserMaster user, BindingResult result,HttpServletRequest request,HttpSession session ,final RedirectAttributes redirectAttributes)
  {
    if (result.hasErrors()) {
        ModelAndView model=new ModelAndView("redirect:Index.html");
        redirectAttributes.addFlashAttribute("Failed", "Username Or Password is Wrong!!!");
        return model;
    }
    else{
         String username = user.getEmail();
         String password = user.getPassword();
        
         System.out.println("Direct Password:"+password);  
         
             List list=homeService.getDbEmail(username);
           if(list!=null && list.size()>0){
             UserMaster userMaster=(UserMaster)list.iterator().next();
             PasswordEncrypt_Decrypt dec= new PasswordEncrypt_Decrypt();
             String decryptedPassword=dec.decrypt(userMaster.getPassword());

//              int logInOut= userMaster.getLogInOut();
              int status= userMaster.getStatus();              
               String groupid=userMaster.getGroupid();
             String uname=userMaster.getEmail();
             String userName=userMaster.getFirstName();   
             
          if(decryptedPassword.equalsIgnoreCase(password) && username.equalsIgnoreCase(uname)){

//               if(logInOut==1)
              if(status==1)
                   session.setAttribute("listGroup", this.homeService.chkGroupId(groupid, uname));
//               session.setAttribute("logInOut", logInOut);
                 session.setAttribute("logInOut", status);
                 session.setAttribute("userName", userName);
               ModelAndView model=new ModelAndView("redirect:/KotForm.html");
               return model;
          }
           else{
                ModelAndView model=new ModelAndView("redirect:Index.html");
                redirectAttributes.addFlashAttribute("Failed", "Username Or Password is Wrong!!!");
                return model;
             }
             }
         else{
                ModelAndView model=new ModelAndView("redirect:Index.html");
                redirectAttributes.addFlashAttribute("Failed", "Username Or Password is Wrong!!!");
                return model;
         }
         }
}
  
     @RequestMapping(value={"Dashboard"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView Companyinformation()
  {
       UserMaster user = new UserMaster();

    ModelAndView model = new ModelAndView("Dashboard");
    return model;
  }
  
   @RequestMapping(value = "/Logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpSession session, final RedirectAttributes redirectAttributes) {
            
                session.removeAttribute("logInOut");
                session.invalidate();
		ModelAndView model = new ModelAndView("redirect:Index.html");
                redirectAttributes.addFlashAttribute("successLogout", "Logged out Successfully!!!");
                return model;
	}
}
