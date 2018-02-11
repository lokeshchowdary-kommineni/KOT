
package com.accounting.dao;

import com.accounting.bean.PredefinedBill;
import com.accounting.bean.PredefinedBillItem;
import com.accounting.bean.Purchase;
import com.accounting.bean.PurchaseEstimate;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("predefinedBillTableDao")
public class PredefinedBillTableDao{

    @Autowired
	private SessionFactory sessionFactory;
 public int savePredefinedBillTable(PredefinedBill pre) {  
     System.out.print("predefinedBillTableDao:");
           sessionFactory.getCurrentSession().saveOrUpdate(pre);
            System.out.print("predefinedBillTableDao After 1:");
           return pre.getPredefinedBillId();
    }

    
    public List<PredefinedBill> listPredefinedBillTable() {
        List<PredefinedBill> pre = null;
        try {

            pre = (List<PredefinedBill>)sessionFactory.getCurrentSession().createQuery("from PredefinedBill").list();
            
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return pre;
    }

    
    
     public int savePredefinedBillTableItem(PredefinedBillItem pre){
        sessionFactory.getCurrentSession().saveOrUpdate(pre);
        return pre.getId();
    }
    
    public PredefinedBill getPredefinedBillTableById(int id){
        return (PredefinedBill)sessionFactory.getCurrentSession().get(PredefinedBill.class, id);
       
    }
    
    public List<PredefinedBillItem> getPredefinedBillTableByPredefinedBillTableId(int id){
        List<PredefinedBillItem> list=sessionFactory.getCurrentSession().createQuery("FROM PredefinedBillItem WHERE predefinedBillId="+id+" ").list();
        return list;
    }
       
    public void deletePredefinedBillTable(String preDId) {
        sessionFactory.getCurrentSession().createQuery("DELETE FROM PredefinedBill WHERE predefinedBillId="+preDId+" ").executeUpdate();    
    }
      
    public List<PredefinedBillItem> getPredefinedBillTable(){
        List<PredefinedBillItem> list=sessionFactory.getCurrentSession().createQuery("FROM PredefinedBillItem").list();
        return list;
    }
    
    public void deletePredefinedBillTableItem(String preDId) {
        sessionFactory.getCurrentSession().createQuery("DELETE FROM PredefinedBillItem WHERE predefinedBillId='"+preDId+"'").executeUpdate();    
    }
     public List<Object[]> GetDatatablePredefinedObject(String sql) {
        return (List<Object[]>)this.sessionFactory.getCurrentSession().createNativeQuery(sql).list();
        }
        public List<Object[]> GetDatatablePredefinedCount(String sql2) {
        return (List<Object[]>)this.sessionFactory.getCurrentSession().createNativeQuery(sql2).list();
        } 
}

