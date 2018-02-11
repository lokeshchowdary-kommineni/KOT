 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.validator;

import com.accounting.bean.Purchase;
import com.accounting.bean.PurchaseEstimateForm;
import com.accounting.bean.PurchaseEstimateItem;
import com.accounting.bean.PurchaseForm;
import com.accounting.bean.PurchaseItem;
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
public class PurchaseEstimateFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return PurchaseEstimateForm.class.isAssignableFrom(type); 
    }

    @Override
    public void validate(Object o, Errors errors) {
        
        PurchaseEstimateForm pForm=(PurchaseEstimateForm)o;
        List<PurchaseEstimateItem> purchases = pForm.getEpurchases();
        if (purchases == null) {
            return;
        }
        else{
        
            for (int i = 0; i <purchases.size(); i++) {
                PurchaseEstimateItem purchase=purchases.get(i);
               
            
            // ValidationUtils.rejectIfEmptyOrWhitespace(errors, "epurchases["+i+"].itemCode", "NotEmpty.purchaseEFrom.itemCode");
             //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "epurchases["+i+"].qty", "NotEmpty.purchaseEFrom.qty");
        }
        }
    }
    
}
