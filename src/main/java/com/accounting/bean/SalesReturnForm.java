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
public class SalesReturnForm {
    
    private List<SalesReturnItem> sales;
    private SalesReturn sale;
    
    public SalesReturnForm() {  
        sales= LazyList.decorate(new ArrayList(),  
        FactoryUtils.instantiateFactory(SalesReturnItem.class)); 
    }

    public List<SalesReturnItem> getSales() {
        return sales;
    }

    public void setSales(List<SalesReturnItem> sales) {
        this.sales = sales;
    }

    public SalesReturn getSale() {
        return sale;
    }

    public void setSale(SalesReturn sale) {
        this.sale = sale;
    }
    
}
