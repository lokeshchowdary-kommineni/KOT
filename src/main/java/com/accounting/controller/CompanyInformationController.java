/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.controller;

import com.accounting.bean.CompanyInformation;
import com.accounting.bean.DefaultSettings;
import com.accounting.service.CompanyService;
import com.accounting.validator.CompanyInformationValidator;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Path;
import org.apache.commons.io.FilenameUtils;
import static org.apache.poi.hssf.usermodel.HeaderFooter.file;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class CompanyInformationController {
    
    @Autowired
    private CompanyService companyservice;
    
    @Autowired
	CompanyInformationValidator companyInformationValidator;

    @ResponseBody
    //Set a form validator
    @InitBinder("companyFrom")
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        binder.setValidator(companyInformationValidator);
    }
    
    CompanyInformation company=new CompanyInformation();
    
     @RequestMapping(value={"Companyinformation"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView Companyinformation( HttpSession session)
  {
      ModelAndView model = new ModelAndView("Companyinformation");
      
    company=companyservice.getCompanyById(1);
    if(company==null )
    {
      CompanyInformation company1 = new CompanyInformation();
        model.addObject("companyFrom",company1);
        
    }
    else{
     model.addObject("companyFrom",company);
    }
   
     
      
    
    return model;
  }
  
  @RequestMapping(value={"saveCompany"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView save(@ModelAttribute("companyFrom") @Validated CompanyInformation company, BindingResult result ,HttpServletRequest req,RedirectAttributes redirectAttrs,@RequestParam (value="file", required=false ) MultipartFile file) throws IOException
  { 
    String imgname=req.getParameter("imgName");
    if (result.hasErrors()) {       
    ModelAndView model = new ModelAndView("Companyinformation");
    model.addObject("companyinfo",companyservice.listcompany());
    return model;
    }
    else{ 
     String outfile = "";
   
    if (!file.isEmpty())
    {
        
       String word =file.getOriginalFilename();
       if (word.length() == 5) {
//         return word;
        } else if (word.length() > 5) {
//          return word.substring(word.length() - 3);
        } else {
          // whatever is appropriate in this case
          throw new IllegalArgumentException("word has less than 3 characters!");
        }
       
        String wordtext=word.substring(word.length() - 4);
        
       if( ".jpg".equals(wordtext) || "jpeg".equals(wordtext) || ".png".equals(wordtext)){
          
          byte[] bytes = file.getBytes();
        String rootPath = req.getServletContext().getRealPath("/");
        File dir = new File(rootPath + File.separator + "Logo");
        if (!dir.exists()) {
          dir.mkdirs();
        }
         try
          {
         outfile = file.getOriginalFilename();
           // extension = FilenameUtils.getExtension(outfile);
            //FileNAme = userID + "." + extension;
            File serverFile = new File(dir.getAbsolutePath() + File.separator + outfile);
            try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) {
                stream.write(bytes);
            }
             if(company.getCompanyLogo()!=null &&!company.getCompanyLogo().isEmpty())
                {
                 File file1 = new File(req.getServletContext().getRealPath("/Logo/"+company.getCompanyLogo()));
                  if (file1.delete()) {}

                }
          }
         catch (MaxUploadSizeExceededException e)
          {
            return new ModelAndView("Companyinformation");
          }
          catch (Exception e)
          {
            return new ModelAndView("Companyinformation");
          }
         
        
          company.setCompanyLogo(outfile);
          
    }else {
          ModelAndView model = new ModelAndView("Companyinformation");
          model.addObject("msg", "The File You Uploaded is Not a .jpg,.jpeg,.png format");
          return model;
        }
       System.out.print("file.getOriginalFilename()  :"+file.getOriginalFilename());
       
         company.setCompanyLogo(file.getOriginalFilename());
        
    }
    else
    company.setCompanyLogo(imgname);    
    companyservice.addCompany(company);    
  }
     ModelAndView model = new ModelAndView("redirect:Companyinformation.html");
     redirectAttrs.addFlashAttribute("CompanyMessage", "Company Details Saved Successfully");
     return model;
  }
  
        @RequestMapping(value={"editCompany"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView editCompany(@RequestParam("id") int id)
    {   
        ModelAndView model = new ModelAndView("Companyinformation");
       
           model.addObject("companyFrom", companyservice.getCompanyById(id));
           model.addObject("companyinfo",companyservice.listcompany());
           return model;
      
           
    }
            
            @RequestMapping(value={"deleteCompany"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView deleteCompany(@RequestParam("id") String id, RedirectAttributes redirect)
    {
        ModelAndView model = new ModelAndView("redirect:Companyinformation.html");
       if(id!=null && id.length()>0){
           companyservice.deleteCompany(Integer.parseInt(id));
           return model;
       }
       else{
           return model;
       }
           
    }
    
}
