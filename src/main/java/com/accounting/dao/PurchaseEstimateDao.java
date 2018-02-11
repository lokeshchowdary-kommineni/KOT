
package com.accounting.dao;

import com.accounting.bean.PurchaseEstimate;
import com.accounting.bean.PurchaseEstimateItem;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("purchaseEstimateDao")
public class PurchaseEstimateDao{

    @Autowired
	private SessionFactory sessionFactory;
      
public String saveEPurchase(PurchaseEstimate purchase) {         
           sessionFactory.getCurrentSession().saveOrUpdate(purchase);
           return purchase.getPurchaseEstimateId();
    }

    
    public List<PurchaseEstimate> listPurchaseEstimate() {
        List<PurchaseEstimate> purchaseE = null;
        try {

            purchaseE = (List<PurchaseEstimate>)sessionFactory.openSession().createQuery("from PurchaseEstimate").list();
            
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return purchaseE;
    }

    
    
     public int savePurchaseEstimateItem(PurchaseEstimateItem purchase){
        sessionFactory.getCurrentSession().saveOrUpdate(purchase);
        return purchase.getId();
    }
    
    public PurchaseEstimate getPurchaseEstimateById(String id){
        return (PurchaseEstimate)sessionFactory.getCurrentSession().get(PurchaseEstimate.class, id);
       
    }
    
    public List<Object[]> getPurchaseEstimateItemByPurchaseEstimateId(String id){
       List<Object[]> list=sessionFactory.getCurrentSession().createQuery(" SELECT est.nameOfTheItem,est.qty,um.unitSymbol,est.tpRate,est.amount ,est.cgst,est.vat,est.igst   from PurchaseEstimateItem  AS est INNER JOIN UnitMaster  AS um ON est.unit = um.idUnit where est.purchaseEstimateId ='"+id+"' ").list();
        return list;
    }
       
    public void deletePurchaseEstimate(String purchaseEId) {
        sessionFactory.getCurrentSession().createQuery("DELETE FROM PurchaseEstimate WHERE purchaseEstimateId='"+purchaseEId+"'").executeUpdate();    
    }
    public void deletePurchaseEstimateItem(String purchaseEId) {
        sessionFactory.getCurrentSession().createQuery("DELETE FROM PurchaseEstimateItem WHERE purchaseEstimateId='"+purchaseEId+"'").executeUpdate();    
    }
      
    public List<PurchaseEstimateItem> getSalesItemList(){
        List<PurchaseEstimateItem> list=sessionFactory.getCurrentSession().createQuery("FROM PurchaseEstimateItem").list();
        return list;
    }
   
     public List<Object[]> GetDatatableObject(String sql) {
        return (List<Object[]>)this.sessionFactory.getCurrentSession().createNativeQuery(sql).list();
    }
    public List<Object[]> GetDatatableCount(String sql2) {
        return (List<Object[]>)this.sessionFactory.getCurrentSession().createNativeQuery(sql2).list();
    }
    
    public List<PurchaseEstimateItem> getPurchaseEstimateItem(String id){
       List<PurchaseEstimateItem> list=sessionFactory.getCurrentSession().createQuery(" from PurchaseEstimateItem  where purchaseEstimateId ='"+id+"' ").list();
        return list;
    }
}

