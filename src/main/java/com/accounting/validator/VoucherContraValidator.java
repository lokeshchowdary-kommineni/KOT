/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.validator;

import com.accounting.bean.VoucherContra;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author MR
 */
@Component
public class VoucherContraValidator implements Validator{

    @Override
    public boolean supports(Class<?> type) {
        return VoucherContra.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        VoucherContra vouchContra = (VoucherContra) o;
        
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "vouchNo", "NotEmpty.vouchForm.vouchNo");
// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "vouchAmount", "NotEmpty.vouchContra.vouchAmount");
// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "vouchType", "NotEmpty.vouchContra.vouchType");
// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "vouchFrom", "NotEmpty.vouchContra.vouchFrom");
// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "vouchTo", "NotEmpty.vouchContra.vouchTo");
    }
    
}
