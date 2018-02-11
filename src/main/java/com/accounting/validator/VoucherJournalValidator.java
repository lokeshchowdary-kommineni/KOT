/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.validator;

import com.accounting.bean.VoucherJournal;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author MR
 */
@Component
public class VoucherJournalValidator implements Validator{

    @Override
    public boolean supports(Class<?> type) {
        return VoucherJournal.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        VoucherJournal vouchJournal = (VoucherJournal) o;
        
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "vouchNo", "NotEmpty.vouchForm.vouchNo");
        
       
    }
    
}
