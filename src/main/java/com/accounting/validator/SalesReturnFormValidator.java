/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.validator;

import com.accounting.bean.SalesEstimateForm;
import com.accounting.bean.SalesEstimateItem;
import com.accounting.bean.SalesReturnForm;
import com.accounting.bean.SalesReturnItem;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author SHINELOGICS
 */
@Component
public class SalesReturnFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
       return SalesReturnForm.class.isAssignableFrom(type); 
    }

    @Override
    public void validate(Object o, Errors errors) {
       SalesReturnForm sForm=(SalesReturnForm)o;
        List<SalesReturnItem> sales = sForm.getSales();
        
        
        if (sales == null) {
            return;
        }
        else{
        
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sale.returnDate", "NotEmpty.salesReturnForm.returnDate");
            
            for (int i = 0; i <sales.size(); i++) {
                SalesReturnItem sale=sales.get(i);
             
                
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sales["+i+"].itemCode", "NotEmpty.salesReturnForm.itemCode");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sales["+i+"].returnQuantity", "NotEmpty.salesReturnForm.returnQuantity");
        
             
            
            }
        }
    }
}
