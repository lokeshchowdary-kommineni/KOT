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
public class PurchaseForm {
    
    private List<PurchaseItem> purchases;
    private Purchase purchase;

    public List<PurchaseItem> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<PurchaseItem> purchases) {
        this.purchases = purchases;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

  
    
    public PurchaseForm() {  
        purchases= LazyList.decorate(new ArrayList(),  
        FactoryUtils.instantiateFactory(PurchaseItem.class)); 
    }

    
}
