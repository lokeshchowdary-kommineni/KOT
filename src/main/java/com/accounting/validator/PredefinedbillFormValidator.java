 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.validator;

import com.accounting.bean.PredefinedBillForm;
import com.accounting.bean.PredefinedBillItem;
import com.accounting.bean.PurchaseEstimateForm;
import com.accounting.bean.PurchaseEstimateItem;
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
public class PredefinedbillFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return PredefinedBillForm.class.isAssignableFrom(type); 
    }

    @Override
    public void validate(Object o, Errors errors) {
        
        PredefinedBillForm pDForm=(PredefinedBillForm)o;
        List<PredefinedBillItem> predefined = pDForm.getSales();
        if (predefined == null) {
            return;
        }
        else{
        
             ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sale.category", "NotEmpty.predefineFrom.category");
             ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sale.billName", "NotEmpty.predefineFrom.billName");        
            
            for (int i = 0; i <predefined.size(); i++) {
                PredefinedBillItem purchase=predefined.get(i);
               
                
                
            //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sales["+i+"].itemCode", "NotEmpty.itemForm.itemCode");
//                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "predefined["+i+"].itemCode", "NotEmpty.userForm.uName");
        }
        }
    }
    
}
