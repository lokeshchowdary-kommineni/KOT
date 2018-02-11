
package com.accounting.validator;

import com.accounting.bean.PredefinedBillForm;
import com.accounting.bean.PredefinedBillItem;
import com.accounting.bean.StockJournalForm;
import com.accounting.bean.StockJournalItem;
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
public class StockJournallFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return StockJournalForm.class.isAssignableFrom(type); 
    }

    @Override
    public void validate(Object o, Errors errors) {
        
        StockJournalForm sForm=(StockJournalForm)o;
        List<StockJournalItem> stocks =sForm.getStocks();
        if (stocks == null) {
            return;
        }
        else{
            
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "stock.date", "NotEmpty.userForm.uName");
        
            for (int i = 0; i <stocks.size(); i++) {
                StockJournalItem stock=stocks.get(i);
                
             //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "stocks["+i+"].itemCode", "NotEmpty.userForm.uName");
        }
        }
    }
    
}
