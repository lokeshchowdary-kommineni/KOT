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
public class SalesForm {
    
    private List<SalesItem> sales;
    private Sales sale;

    public Sales getSale() {
        return sale;
    }

    public void setSale(Sales sale) {
        this.sale = sale;
    }
    
    public SalesForm() {  
        sales= LazyList.decorate(new ArrayList(),  
        FactoryUtils.instantiateFactory(SalesItem.class)); 
    }

    public List<SalesItem> getSales() {
        return sales;
    }

    public void setSales(List<SalesItem> sales) {
        this.sales = sales;
    }
    
    
}
