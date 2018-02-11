/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.validator;

import com.accounting.bean.VoucherReceiptAndPayment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author SHINELOGICS
 */
@Component
public class VoucherReceiptAndPaymentValidator implements Validator{

    @Override
    public boolean supports(Class<?> type) {
       return VoucherReceiptAndPayment.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
       VoucherReceiptAndPayment vReceipt=new VoucherReceiptAndPayment();
       
       ValidationUtils.rejectIfEmptyOrWhitespace(errors, "vouchAmount", "NotEmpty.vouchPaymentReceipt.vouchAmount");
       ValidationUtils.rejectIfEmptyOrWhitespace(errors, "vouchType", "NotEmpty.vouchPaymentReceipt.vouchType");
       ValidationUtils.rejectIfEmptyOrWhitespace(errors, "vouchMode", "NotEmpty.vouchPaymentReceipt.vouchMode");
       ValidationUtils.rejectIfEmptyOrWhitespace(errors, "vouchFrom", "NotEmpty.vouchPaymentReceipt.vouchFrom");
       ValidationUtils.rejectIfEmptyOrWhitespace(errors, "vouchTo", "NotEmpty.vouchPaymentReceipt.vouchTo");
       

 
    }
    
}
