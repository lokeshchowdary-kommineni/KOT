
package com.accounting.validator;

import com.accounting.bean.PurchaseEstimateForm;
import com.accounting.bean.PurchaseEstimateItem;
import com.accounting.bean.ReversalOfItcForm;
import com.accounting.bean.ReversalOfItcItem;
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
public class ReversalofitcFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return ReversalOfItcForm.class.isAssignableFrom(type); 
    }

    @Override
    public void validate(Object o, Errors errors) {
        
        ReversalOfItcForm rForm=(ReversalOfItcForm)o;
        List<ReversalOfItcItem> reversalofitcs = rForm.getReversalofitcs();
        if (reversalofitcs == null) {
            return;
        }
        else{
             ValidationUtils.rejectIfEmptyOrWhitespace(errors, "reversalofitc.returnDate", "NotEmpty.reversalFrom.returnDate");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "reversalofitc.categoryForReversal", "NotEmpty.reversalFrom.categoryForReversal");
           
          
        
            for (int i = 0; i <reversalofitcs.size(); i++) {
                ReversalOfItcItem reversal=reversalofitcs.get(i);
               
                 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "reversalofitcs["+i+"].returnQty", "NotEmpty.reversalFrom.returnQty");
               }
        }
    }
    
}
