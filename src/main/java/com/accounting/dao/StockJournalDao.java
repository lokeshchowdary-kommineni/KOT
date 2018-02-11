
package com.accounting.dao;

import com.accounting.bean.StockFjournalItem;
import com.accounting.bean.StockJournal;
import com.accounting.bean.StockJournalItem;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("stockJournalDao")
public class StockJournalDao{

    @Autowired
	private SessionFactory sessionFactory;

 public int saveStockJournalTable(StockJournal stock) {        
           sessionFactory.getCurrentSession().saveOrUpdate(stock);
           return stock.getStockJournalId();
    }

    
    public List<StockJournal> listStockJournalTable() {
        List<StockJournal> stock = null;
        try {

            stock = (List<StockJournal>)sessionFactory.openSession().createQuery("from StockJournal").list();
            
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return stock;
    }

    
    
     public int saveStockJournalTableItem(StockJournalItem stock){
        sessionFactory.getCurrentSession().saveOrUpdate(stock);
        return stock.getId();
    }
    
     public int saveFStockJournalTableItem(StockFjournalItem stock){
        sessionFactory.getCurrentSession().saveOrUpdate(stock);
        return stock.getId();
    }
     
    public StockJournal getStockJournalTableById(int id){
        return (StockJournal)sessionFactory.getCurrentSession().get(StockJournal.class, id);
       
    }
    
    public List<StockJournalItem> getStockJournalTableByStockJournalTableId(int id){
        List<StockJournalItem> listC=sessionFactory.getCurrentSession().createQuery("FROM StockJournalItem WHERE stockJournalId='"+id+"'").list();
        return listC;
    }
    
    public List<Object[]> getStockJournalTableByStockJournalTableIdInvoice(int id){
        List<Object[]> listC=sessionFactory.getCurrentSession().createQuery("SELECT s.stockJournalId,s.nameOfTheItem,s.qty,u.unitSymbol,s.rate,s.amount FROM  StockJournalItem AS s INNER JOIN UnitMaster AS u ON s.unit = u.idUnit WHERE  s.stockJournalId='"+id+"' ").list();
        return listC;
    }
    
     public List<StockFjournalItem> getFStockJournalTableByStockJournalTableId(int id){
        List<StockFjournalItem> listF=sessionFactory.getCurrentSession().createQuery("FROM StockFjournalItem WHERE stockJournalId='"+id+"'").list();
        return listF;
    }
     public List<Object[]> getFStockJournalTableByStockJournalTableIdInvoice(int id){
        List<Object[]> listF=sessionFactory.getCurrentSession().createQuery("SELECT s.stockJournalId,s.nameOfTheItem,s.qty,u.unitSymbol,s.rate,s.amonut FROM StockFjournalItem AS s INNER JOIN UnitMaster AS u ON s.unit = u.idUnit WHERE  s.stockJournalId='"+id+"'").list();
        return listF;
    }
       
    public void deleteStockJournalTable(String stockId) {
        sessionFactory.getCurrentSession().createQuery("DELETE FROM StockJournal WHERE stockJournalId='"+stockId+"'").executeUpdate();    
    }
      
    public List<StockJournalItem> getStockJournalTable(){
        List<StockJournalItem> list=sessionFactory.getCurrentSession().createQuery("FROM StockJournalItem").list();
        return list;
    }
    
      public void deleteStockJournalItemTable(String stockId) {
        sessionFactory.getCurrentSession().createQuery("DELETE FROM StockJournalItem WHERE stockJournalId='"+stockId+"'").executeUpdate();    
    }
      
       public void deleteFStockJournalItemTable(String stockId) {
        sessionFactory.getCurrentSession().createQuery("DELETE FROM StockFjournalItem WHERE stockJournalId='"+stockId+"'").executeUpdate();    
    }
    
       
         public List<Object[]> GetDatatableObject(String sql) {
        return (List<Object[]>)this.sessionFactory.getCurrentSession().createNativeQuery(sql).list();
    }
    public List<Object[]> GetDatatableCount(String sql2) {
        return (List<Object[]>)this.sessionFactory.getCurrentSession().createNativeQuery(sql2).list();
    }
}

