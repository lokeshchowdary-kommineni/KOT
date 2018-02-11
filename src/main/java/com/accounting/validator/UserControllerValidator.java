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
 * @author MR
 */

@Component
public class UserControllerValidator implements Validator {
     @Override
    public boolean supports(Class<?> type) {
        return UserMaster.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserMaster userMaster= (UserMaster) o;
         ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.userCon.emailID");
    }

}
