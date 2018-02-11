/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.validator;

import com.accounting.bean.ItemMaster;
import com.accounting.bean.Sales;
import com.accounting.bean.SalesForm;

import com.accounting.bean.SalesItem;
import com.accounting.service.ItemMasterService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author SHINELOGICS
 */
@Component
public class SalesFormValidator implements Validator {
    
    @Autowired
    private ItemMasterService itemMasterService;

    @Override
    public boolean supports(Class<?> type) {
        return SalesForm.class.isAssignableFrom(type); 
    }

    @Override
    public void validate(Object o, Errors errors) {
        
        SalesForm sForm=(SalesForm)o;
        List<SalesItem> sales = sForm.getSales();
        if (sales == null) {
            return;
        }
        else{
         //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sale.invoiceNo", "NotEmpty.itemForm.itemName");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sale.date", "NotEmpty.salesForm.date");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sale.category", "NotEmpty.salesForm.category");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sale.mode", "NotEmpty.salesForm.mode");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sale.nameOfBuyer", "NotEmpty.salesForm.nameOfBuyer");
            for (int i = 0; i <sales.size(); i++) {
                SalesItem sale=sales.get(i);
               // ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sales["+i+"].itemCode", "NotEmpty.salesForm.itemCode");
                
                
                
                
                
//                if(sale.getItemCode()!=null && sale.getItemCode().length()>0){
//                    List list=itemMasterService.getItmeByItemNo(sale.getItemCode());
//                    if(list!=null && list.size()>0){
//                        ItemMaster iMaster=(ItemMaster)list.iterator().next();
//           
//                        if(Integer.parseInt(iMaster.getCurrentStock())<sale.getQuantity()){
//                            String qtyMessage="Qty exist,"+iMaster.getCurrentStock()+" available ";
//                            errors.rejectValue("sales["+i+"].quantity","error.SalesItem", qtyMessage);
//                        }
//                    }
//                }
            }
        }
    }
    
}
