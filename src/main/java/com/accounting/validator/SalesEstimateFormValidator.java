/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.validator;

import com.accounting.bean.SalesEstimateForm;
import com.accounting.bean.SalesEstimateItem;
import com.accounting.bean.SalesForm;
import com.accounting.bean.SalesItem;
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
public class SalesEstimateFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
       return SalesEstimateForm.class.isAssignableFrom(type); 
    }

    @Override
    public void validate(Object o, Errors errors) {
       SalesEstimateForm sForm=(SalesEstimateForm)o;
        List<SalesEstimateItem> sales = sForm.getSales();
        if (sales == null) {
            return;
        }
        else{
         //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sale.invoiceNo", "NotEmpty.itemForm.itemName");
            for (int i = 0; i <sales.size(); i++) {
                SalesEstimateItem sale=sales.get(i);
             
             //  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sales["+i+"].itemCode", "NotEmpty.userForm.uName");
        }
        }
    }
    
}
