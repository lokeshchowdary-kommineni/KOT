/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.bean;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.list.LazyList;

/**
 *
 * @author SHINELOGICS
 */
public class PredefinedBillForm {
    
    
    private List<PredefinedBillItem> sales;
    private PredefinedBill sale;

    public List<PredefinedBillItem> getSales() {
        return sales;
    }

    public void setSales(List<PredefinedBillItem> sales) {
        this.sales = sales;
    }

    public PredefinedBill getSale() {
        return sale;
    }

    public void setSale(PredefinedBill sale) {
        this.sale = sale;
    }

    

  
    
    public PredefinedBillForm() {  
        sales= LazyList.decorate(new ArrayList(),  
        FactoryUtils.instantiateFactory(PredefinedBillItem.class)); 
    }

    
    
}
