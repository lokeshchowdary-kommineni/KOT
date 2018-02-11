
package com.accounting.dao;

import com.accounting.bean.ItemMaster;
import com.accounting.bean.ItemReport;
import com.accounting.bean.LedgerAccountMaster;
import com.accounting.bean.Purchase;
import com.accounting.bean.PurchaseItem;
import com.accounting.bean.Sales;
import com.accounting.bean.SalesReturn;
import com.accounting.bean.SupplierMaster;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("purchaseDao")
public class PurchseDao{

    @Autowired
	private SessionFactory sessionFactory;
      
    public String savePurchase(Purchase purchase) {        
           sessionFactory.getCurrentSession().saveOrUpdate(purchase);
           return purchase.getInvoiceNo();
    }

    
    public List<Purchase> listPurchase() {
        List<Purchase> purchase = null;
        try {

            purchase = (List<Purchase>)sessionFactory.openSession().createQuery("from Purchase").list();
            
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return purchase;
    }

    
    
     public int savePurchaseItem(PurchaseItem purchase){
        sessionFactory.getCurrentSession().saveOrUpdate(purchase);
        return purchase.getId();
    }
    
    public Purchase getPurchaseById(String id){
        return (Purchase)sessionFactory.getCurrentSession().get(Purchase.class, id);
       
    }
    
    public List<PurchaseItem> getPurchaseItemByPurchaseId(String id){
        List<PurchaseItem> list=sessionFactory.getCurrentSession().createQuery("FROM PurchaseItem WHERE invoiceNo='"+id+"'").list();
        return list;
    }
    
     public List<ItemReport> getItemReportByPurchaseId(String id,String type,String iName){
        List<ItemReport> list=sessionFactory.getCurrentSession().createQuery("FROM ItemReport WHERE transaction_id='"+id+"' AND transaction_type='"+type+"' AND item_name='"+iName+"'").list();
        return list;
    }
      public void updateItemReport(String query){
       sessionFactory.getCurrentSession().createSQLQuery(query).executeUpdate();
    
    }
     
    public LedgerAccountMaster getledgerIdBySupplierId(int id,String type){
        LedgerAccountMaster list=(LedgerAccountMaster) sessionFactory.getCurrentSession().createQuery("FROM LedgerAccountMaster  WHERE rId='"+id+"' AND type='"+ type +"' ").uniqueResult();
        return list;
    }
       
    public void deletePurchse(String purchaseId) {
        sessionFactory.getCurrentSession().createQuery("DELETE FROM Purchase WHERE invoiceNo='"+purchaseId+"'").executeUpdate();    
    }
    public void deletePurchseItem(String purchaseId) {
        sessionFactory.getCurrentSession().createQuery("DELETE FROM PurchaseItem WHERE invoiceNo='"+purchaseId+"'").executeUpdate();    
    }
      
    public List<PurchaseItem> getSalesItemList(){
        List<PurchaseItem> list=sessionFactory.openSession().createQuery("FROM PurchaseItem").list();
        return list;
    }
   
    public List<SupplierMaster> listSupplierLike(String key)
  {
    List<SupplierMaster> supplier = null;
    supplier = sessionFactory.getCurrentSession().createQuery("from SupplierMaster WHERE NOT  supplier_name='CASH SUPPLIER' AND tin !='' AND (LOWER(supplier_name) LIKE LOWER('" + key + "%')) ").list();
    return supplier;
  }
     public List<SupplierMaster> listSupplierWithoutGstin(String key)
  {
    List<SupplierMaster> supplier = null;
    supplier = sessionFactory.getCurrentSession().createQuery("from SupplierMaster WHERE NOT  supplier_name='CASH SUPPLIER' AND tin ='' AND (LOWER(supplier_name) LIKE LOWER('" + key + "%')) ").list();
    return supplier;
  }
public List<SupplierMaster> listSupplierMasterCustomerLike(String key)
  {
    List<SupplierMaster> supplier = null;
    supplier = sessionFactory.getCurrentSession().createQuery("from SupplierMaster WHERE supplier_name LIKE '%" + key + "%' AND supplier_name='Cash Supplier'").list();
    return supplier;
 }
// Reports Start  
public List<Sales> listItemReportsSSR() {
        List<Sales> list=sessionFactory.openSession().createNativeQuery("SELECT \n" +
"s.invoice_no AS sinvoice,sr.invoice_no AS srinvoice,s.date AS sdate,sr.return_date AS srdate,si.quantity AS squantity,\n" +
"sri.return_quantity AS srquantity,si.unit AS sunit,sri.unit AS srunit,si.cpcpv_amount AS scpcpvAmount,sri.cpcpv_Amount AS srcpcpv_amount,si.amount AS samount,\n" +
"sri.amount AS sramount,si.item_code AS sitemcode,sri.item_code AS sritemcode,s.category AS scategory,sr.category AS srcategory ,\n" +
"si.WithoutTax AS sWithoutTax,sri.WithoutTax AS srWithoutTax FROM\n" +
"sales AS s INNER JOIN \n" +
"sales_item AS si ON s.invoice_no=si.invoice_no LEFT JOIN sales_return AS sr ON \n" +
"s.invoice_no=sr.invoice_no LEFT JOIN sales_return_item AS sri ON sr.invoice_no=sri.invoice_no  AND si.item_code=sri.item_code \n" +
"GROUP BY si.id,sri.id ").list();
        return list;
    }

public List<Purchase> PurchaseReports(String gsQuery)
  {
    List<Purchase> purchase = sessionFactory.getCurrentSession().createQuery(gsQuery).list();
    
    return purchase;
  }

public List<Sales> listVAAMCASSR() {
        List<Sales> list=sessionFactory.openSession().createNativeQuery("SELECT s.invoice_no,s.date AS sdate,s.vaa,s.mca,sr.invoice_no AS srno,sr.date AS srdate,sr.vaa AS srvaa,sr.mca AS srmca,s.assess_value,sr.assess_value AS srassessvalue FROM sales AS s LEFT JOIN sales_return AS sr ON s.invoice_no = sr.invoice_no").list();
        return list;
    }

public List<Object[]> listVAAMCASSRReports(String gsQuery)
  {
      System.out.print("Loist Query");
    List<Object[]> list = sessionFactory.getCurrentSession().createNativeQuery(gsQuery).list();
    
    return list;
  }

public List<Object[]> listItemSaleReports(String gsQuery)
  {
      System.out.print("Loist Query");
    List<Object[]> list = sessionFactory.getCurrentSession().createNativeQuery(gsQuery).list();
    
    return list;
  }
 public List<Object[]> GetDatatableObject(String sql) {
        return (List<Object[]>)this.sessionFactory.getCurrentSession().createNativeQuery(sql).list();
    }
    public List<Object[]> GetDatatableCount(String sql2) {
        return (List<Object[]>)this.sessionFactory.getCurrentSession().createNativeQuery(sql2).list();
    }
     public Double sumTotalinvoiceAmt(String Categoary,String Date){
        BigDecimal invAmt=(BigDecimal) sessionFactory.openSession().createNativeQuery("select  COALESCE(sum(total_invoice_amount ) ,0) as totalInvoiceAmt from PURCHASE where category='"+Categoary+"' and date='"+Date+"'").uniqueResult();
        return invAmt.doubleValue();
    }
     public Double sumTotalCgstSgstPerday(String Categoary,String Date,int LedgerID){
        BigDecimal invAmt=(BigDecimal) sessionFactory.openSession().createNativeQuery("select COALESCE(sum(ei.amount ) ,0) from ENTRYITEMS ei inner join ENTRIES e on e.id=ei.entry_id  inner join PURCHASE  p on p.invoice_no=e.bill_id and p.date=e.date  and p.category ='"+Categoary+"' and p.date='"+Date+"' where ei.ledger_id="+LedgerID+"").uniqueResult();
        return invAmt.doubleValue();
    }
     public List<Object[]> getTotalSgtsandcgstFromPurchaseTable(String Categoary,String Date) {
        return (List<Object[]>)this.sessionFactory.getCurrentSession().createNativeQuery("SELECT COALESCE(sum (total_cgst) ,0) as total_cgst, COALESCE(sum(total_vat),0)  as total_vat FROM PURCHASE where category='"+Categoary+"' and date='"+Date+"'").list();
    } 
}

