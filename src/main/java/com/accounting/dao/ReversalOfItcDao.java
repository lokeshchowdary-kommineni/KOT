
package com.accounting.dao;

import com.accounting.bean.Purchase;
import com.accounting.bean.ReversalOfItc;
import com.accounting.bean.ReversalOfItcItem;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("reversalOfItcDao")
public class ReversalOfItcDao{

    @Autowired
	private SessionFactory sessionFactory;
    
 public String saveReversalOfItc(ReversalOfItc reversalOfItc) {        
           sessionFactory.getCurrentSession().saveOrUpdate(reversalOfItc);
           return reversalOfItc.getReversalOdItcId();
                  
    }

    
    public List<ReversalOfItc> listReversalOfItc() {
        List<ReversalOfItc> reversalOfItc = null;
        try {

            reversalOfItc = (List<ReversalOfItc>)sessionFactory.getCurrentSession().createQuery("from ReversalOfItc").list();
            
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return reversalOfItc;
    }

    
    
     public int saveReversalOfItcItem(ReversalOfItcItem reversal){
        sessionFactory.getCurrentSession().saveOrUpdate(reversal);
        return reversal.getId();
    }
    
    public ReversalOfItc getReversalOfItcById(String id){
        return (ReversalOfItc)sessionFactory.getCurrentSession().get(ReversalOfItc.class, id);
       
    }
    
    public List<ReversalOfItcItem> getReversalOfItcItemByReversalOfItcId(String id){
        List<ReversalOfItcItem> list=sessionFactory.getCurrentSession().createQuery("FROM ReversalOfItcItem WHERE reversalOdItcId='"+id+"'").list();
        return list;
    }
       
    public void deleteReversalOfItc(String reversalId) {
        sessionFactory.getCurrentSession().createQuery("DELETE FROM ReversalOfItc WHERE reversalOdItcId='"+reversalId+"'").executeUpdate();    
    }
      
    public List<ReversalOfItcItem> getReversalOfItc(){
        List<ReversalOfItcItem> list=sessionFactory.getCurrentSession().createQuery("FROM ReversalOfItcItem").list();
        return list;
    }
//Return Qty change 
    
    public double  getReversalReturnId(String code,String purchaseinvoiceNumner) {
            double i=(double) sessionFactory.getCurrentSession().createQuery("SELECT coalesce(SUM(roi.returnQty),0) AS Totalreturnqty FROM ReversalOfItcItem roi inner join ReversalOfItc r on roi.reversalOdItcId=r.reversalOdItcId inner join Purchase p on p.invoiceNo=r.billNo WHERE  roi.itemCode='"+code+"' and p.invoiceNo='"+purchaseinvoiceNumner+"'").uniqueResult();

            return i;
	}
    
    public List<Object[]> GetDatatableObject(String sql) {
        return (List<Object[]>)this.sessionFactory.getCurrentSession().createNativeQuery(sql).list();
    }
    public List<Object[]> GetDatatableCount(String sql2) {
        return (List<Object[]>)this.sessionFactory.getCurrentSession().createNativeQuery(sql2).list();
    }
    
    
    public List<ReversalOfItc> ReversalOfItcReports(String gsQuery)
  {
    List<ReversalOfItc> reversalOfItc = sessionFactory.getCurrentSession().createQuery(gsQuery).list();
    
    return reversalOfItc;
  }
}

