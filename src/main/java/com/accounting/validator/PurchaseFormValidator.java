 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.validator;

import com.accounting.bean.Purchase;
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
public class PurchaseFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return PurchaseForm.class.isAssignableFrom(type); 
    }

    @Override
    public void validate(Object o, Errors errors) {
        
        PurchaseForm pForm=(PurchaseForm)o;
        List<PurchaseItem> purchases = pForm.getPurchases();
        if (purchases == null) {
            return;
        }
        else{
        
            for (int i = 0; i <purchases.size(); i++) {
                PurchaseItem purchase=purchases.get(i);
               
                
                
           
                //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "purchases["+i+"].itemCode", "NotEmpty.purchaseFrom.itemCode");
                 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "purchase.purchaseInvoiceId", "NotEmpty.purchaseFrom.purchaseInvoiceId");
                 //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "purchases.purchaseInvoiceId", "NotEmpty.purchaseFrom.purchaseInvoiceId");
        }
        }
    }
    
}
