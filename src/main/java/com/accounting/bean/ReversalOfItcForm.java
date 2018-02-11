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
public class ReversalOfItcForm {
    
    
    private List<ReversalOfItcItem> reversalofitcs;
    private ReversalOfItc reversalofitc;

    public List<ReversalOfItcItem> getReversalofitcs() {
        return reversalofitcs;
    }

    public void setReversalofitcs(List<ReversalOfItcItem> reversalofitcs) {
        this.reversalofitcs = reversalofitcs;
    }

    public ReversalOfItc getReversalofitc() {
        return reversalofitc;
    }

    public void setReversalofitc(ReversalOfItc reversalofitc) {
        this.reversalofitc = reversalofitc;
    }

   
    
    public ReversalOfItcForm() {  
        reversalofitcs= LazyList.decorate(new ArrayList(),  
        FactoryUtils.instantiateFactory(ReversalOfItcItem.class)); 
    }

    
}
