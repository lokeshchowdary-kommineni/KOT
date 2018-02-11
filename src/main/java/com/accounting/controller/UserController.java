
package com.accounting.controller;


import com.accounting.bean.UserMaster;
import com.accounting.encrypt_decrypt.PasswordEncrypt_Decrypt;
import com.accounting.service.UserControllerService;
import com.accounting.validator.UserControllerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author MR
 */
@Controller
public class UserController {
     @Autowired
  private UserControllerService userControllerService;
     
      
    @Autowired
    private UserControllerValidator userControllerValidator;
    
     
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
            binder.setValidator(userControllerValidator);
    }
     
      @RequestMapping(value={"/UserMaster"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView user()
  {
      UserMaster userMaster=new UserMaster();
      ModelAndView model = new ModelAndView("UserMaster");
      model.addObject("userDetail", userMaster);
      model.addObject("userList", userControllerService.listGroup());
      model.addObject("groupName", userControllerService.getGroupName());
      return model;
  }
     @RequestMapping(value={"save_user"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public String saveUser(@ModelAttribute("userDetail") @Validated UserMaster user, BindingResult result)
  {
       Integer id=user.getUserid();
       
       System.out.println("Insert Id "+id);
       if(result.hasErrors()){
           
           return "UserMaster";
       }else{
            if(id==null )
            {
             user.setPassword(PasswordEncrypt_Decrypt.encrypt(user.getPassword()) );
              user.setUserid(null);
            }
            else
            {
             user.setUserid(id);
             user.setPassword(PasswordEncrypt_Decrypt.encrypt(user.getPassword()) );
            }
       }
           
       
       userControllerService.addUserDerails(user);
        ModelAndView model = new ModelAndView("UserMaster");
        return "redirect:UserMaster.html";
  }
   @RequestMapping(value={"editUser"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView editUser(@RequestParam("id") String id, RedirectAttributes redirect)
    {
        ModelAndView model = new ModelAndView("UserMaster");
        if(id!=null && id.length()>0){
           model.addObject("userDetail", userControllerService.getUserByid(Integer.parseInt(id)));
           model.addObject("userList", userControllerService.listGroup());
           model.addObject("groupName", userControllerService.getGroupName());
           model.addObject("pwd", userControllerService.getPwd(Integer.parseInt(id)));
           return model;
        }
        else{
           System.out.println("return Edit Id "+id);
           return model;
       }
    }
  @RequestMapping(value={"deleteUser"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView deleteUser(@RequestParam("id") String id, RedirectAttributes redirect)
    {
        ModelAndView model = new ModelAndView("redirect:UserMaster.html");
       if(id!=null && id.length()>0){
           userControllerService.deleteUserDetail(Integer.parseInt(id));
           return model;
       }
       else{
           return model;
       }
           
    }
}
