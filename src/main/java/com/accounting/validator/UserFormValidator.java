/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.validator;

import com.accounting.bean.UserMaster;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;





/**
 *
 * @author SHINELOGICS
 */
@Component
public class UserFormValidator implements Validator{
  
    

    @Override
    public boolean supports(Class<?> type) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return UserMaster.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                UserMaster user = (UserMaster) o;
                
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.userForm.email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.userForm.password");
                
                
                
                if(user.getPassword().length()<5){
                    System.out.println("Sivaraj Password");
			errors.rejectValue("password", "length.userForm.password");
		}
                
                
    }

  


   
    
}
