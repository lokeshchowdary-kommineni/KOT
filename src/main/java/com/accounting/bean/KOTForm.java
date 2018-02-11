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
 * @author MR
 */
public class KOTForm {
    
    private List<KotItem> kotItem;
    private Kot kot;

    public List<KotItem> getKotItem() {
        return kotItem;
    }

    public void setKotItem(List<KotItem> kotItem) {
        this.kotItem = kotItem;
    }

    public Kot getKot() {
        return kot;
    }

    public void setKot(Kot kot) {
        this.kot = kot;
    }
    
    public KOTForm() {  
        kotItem= LazyList.decorate(new ArrayList(),  
        FactoryUtils.instantiateFactory(KotItem.class)); 
    }
}
