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
public class SalesEstimateForm {
    
     
    
    private List<SalesEstimateItem> sales;
    private SalesEstimate sale;

    public SalesEstimateForm() {  
        sales= LazyList.decorate(new ArrayList(),  
        FactoryUtils.instantiateFactory(SalesEstimateItem.class)); 
    } 
    
    public List<SalesEstimateItem> getSales() {
        return sales;
    }

    public void setSales(List<SalesEstimateItem> sales) {
        this.sales = sales;
    }

    public SalesEstimate getSale() {
        return sale;
    }

    public void setSale(SalesEstimate sale) {
        this.sale = sale;
    }
    
}
