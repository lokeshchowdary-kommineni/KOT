/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.validator;

import com.accounting.bean.ItemMaster;
import com.accounting.service.ItemMasterService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author SHINELOGICS
 */
@Component
public class ItemMasterFormValidator implements Validator{
    
    @Autowired
    private ItemMasterService itemMasterService;

    @Override
    public boolean supports(Class<?> type) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return ItemMaster.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        
        ItemMaster itemMaster=(ItemMaster)o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "itemCode", "NotEmpty.itemForm.itemCode");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "itemName", "NotEmpty.itemForm.itemName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comCode", "NotEmpty.itemForm.comCode");
        if(itemMaster.getId()==null || itemMaster.equals("")){
        ItemMaster list=itemMasterService.getItmeByItemNo(itemMaster.getItemCode());
       
        if(list!=null ){
            
          errors.rejectValue("itemCode", "NotEmpty.itemForm.itemCode.dublicate");  
        }
        }
        if(itemMaster.getId()==null || itemMaster.equals("")){
        ItemMaster list=itemMasterService.getItmeByItemName(itemMaster.getItemName());
       
        if(list!=null ){
            
          errors.rejectValue("itemName", "NotEmpty.itemForm.itemName.dublicate");  
        }
        }
        
        if(itemMaster.getUnit()==null ){
            
          errors.rejectValue("unit", "NotEmpty.itemForm.unit");  
        }
        if(itemMaster.getBasicPrice()!=null || itemMaster.getBasicVatPrice()!=null){
            System.out.println("If :"+itemMaster.getBasicPrice());
            if(itemMaster.getBasicPrice().equals("") && itemMaster.getBasicVatPrice().equals("")){
                errors.rejectValue("basicPrice", "NotEmpty.itemForm.basicPrice");
            }
        }
        else{
             errors.rejectValue("basicPrice", "NotEmpty.itemForm.basicPrice");
        }
        
    }
    
}
