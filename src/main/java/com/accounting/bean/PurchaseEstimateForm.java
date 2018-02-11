
package com.accounting.bean;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.list.LazyList;


public class PurchaseEstimateForm {
    
    private List<PurchaseEstimateItem> epurchases;
    private PurchaseEstimate epurchase;

    public List<PurchaseEstimateItem> getEpurchases() {
        return epurchases;
    }

    public void setEpurchases(List<PurchaseEstimateItem> epurchases) {
        this.epurchases = epurchases;
    }

    public PurchaseEstimate getEpurchase() {
        return epurchase;
    }

    public void setEpurchase(PurchaseEstimate epurchase) {
        this.epurchase = epurchase;
    }

    public PurchaseEstimateForm() {  
        epurchases= LazyList.decorate(new ArrayList(),  
        FactoryUtils.instantiateFactory(PurchaseEstimateItem.class)); 
    }

  
    
}
