/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.validator;

import com.accounting.bean.CompanyInformation;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;





/**
 *
 * @author SHINELOGICS
 */
@Component
public class CompanyInformationValidator implements Validator{
  
    

    @Override
    public boolean supports(Class<?> type) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return CompanyInformation.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
              CompanyInformation company=new CompanyInformation();
                
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companyName", "NotEmpty.companyFrom.companyName");

                  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companyPan", "NotEmpty.companyFrom.companyPan");

                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountsFrom", "NotEmpty.companyFrom.accountsFrom");
   
    }

  


   
    
}
