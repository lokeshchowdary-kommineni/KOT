/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.dao;


import com.accounting.bean.Entries;
import com.accounting.bean.Entryitems;
import com.accounting.bean.LedgerAccountMaster;
import com.accounting.bean.VoucherReceiptAndPayment;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SHINELOGICS
 */
@Repository("voucherReceiptAndPaymentDao")
public class VoucherReceiptAndPaymentDao {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    public int saveVoucher(VoucherReceiptAndPayment vReceipt){
        sessionFactory.getCurrentSession().saveOrUpdate(vReceipt);
        return vReceipt.getVouchId();
    }
    public int saveVoucherToEntries(Entries et){
        sessionFactory.getCurrentSession().saveOrUpdate(et);
        return et.getId();
    }
    public void updateVoucherToEntries(int e_id, Entries ei){
        String hqlUpdate = "update Entries set billType = :pBillType, crTotal = :pCrTotal, drTotal=:pDrTotal, entryType = :pEntryType, narration=:pNarration where billId = :pBillId";
        sessionFactory.openSession().createQuery(hqlUpdate).setString("pBillType", ei.getBillType()).setDouble("pCrTotal", ei.getCrTotal()).setDouble("pDrTotal", ei.getDrTotal()).setString("pEntryType", ei.getEntryType()).setString("pNarration", ei.getNarration()).setInteger("pBillId", e_id).executeUpdate();
        System.out.println("From DAO Entry ID :"+ei.getId());
    }
    
    public Entries getVoucherEntryId(Entries ei){
        Criteria criteria = sessionFactory.openSession().createCriteria(Entries.class)
                    .add(Restrictions.eq("billId", ei.getBillId())).add(Restrictions.eq("entryType", ei.getEntryType()));
        System.out.println("Bill ID is :"+ei.getBillId());
        return (Entries) criteria.uniqueResult();
    }
    
    public int saveVoucherToEntryItems(Entryitems ei){
        sessionFactory.getCurrentSession().saveOrUpdate(ei);
        return ei.getId();
    }
    public List<Entryitems> getVoucherEntryItemsId(Entryitems et){
        Criteria criteria = sessionFactory.openSession().createCriteria(Entryitems.class)
                    .add(Restrictions.eq("entryId", et.getEntryId()));
        return (List<Entryitems>) criteria.list();
    }
    public void updateVoucherToEntryItems(Entryitems et){
        String hqlUpdate = "update Entryitems set amount = :pAmount, type = :pType where entryId = :pEntryId AND ledgerId=:pLedgerId";
        System.out.println("The Types :" +et.getType());
        sessionFactory.openSession().createQuery(hqlUpdate).setDouble("pAmount", et.getAmount()).setString("pType", et.getType()).setInteger("pEntryId", et.getEntryId()).setInteger("pLedgerId", et.getLedgerId()).executeUpdate();
    }
    
    public VoucherReceiptAndPayment getVoucherById(int vouchId){
        return (VoucherReceiptAndPayment) sessionFactory.openSession().get(VoucherReceiptAndPayment.class, vouchId);
    }
    
    public List<VoucherReceiptAndPayment> getVoucherList(){
        List<VoucherReceiptAndPayment> list=sessionFactory.openSession().createQuery("FROM VoucherReceiptAndPayment").list();
        return list;
    }
    
     public List<VoucherReceiptAndPayment> getVoucherReceiptList(String receipt){
        List<VoucherReceiptAndPayment> list=sessionFactory.openSession().createQuery("FROM VoucherReceiptAndPayment WHERE vouch_type='"+receipt+"'").list();
        return list;
    }
     
      public List<VoucherReceiptAndPayment> getVoucherPaymentList(String payment){
        List<VoucherReceiptAndPayment> list=sessionFactory.openSession().createQuery("FROM VoucherReceiptAndPayment WHERE vouch_type='"+payment+"'").list();
        return list;
    }
    public void deleteVoucherById(int vouchId){
        sessionFactory.openSession().createQuery("DELETE FROM VoucherReceiptAndPayment WHERE vouch_id='"+vouchId+"'").executeUpdate();
       
    }
    
    public List<LedgerAccountMaster> listAllLedgerNames() {
        List<LedgerAccountMaster> list=sessionFactory.openSession().createQuery("from LedgerAccountMaster").list();
        return list;
    }
    
    public List<LedgerAccountMaster> listAllLedgerExceptCIN() {
        List<LedgerAccountMaster> list=sessionFactory.openSession().createQuery("from LedgerAccountMaster WHERE  NOT  underGroup='15' AND NOT underGroup='7' AND NOT underGroup='16' and nameOfLedger not in('CASH BUYER','CASH SUPPLIER','CASH MEDIATOR')").list();
        return list;
    }
    
    public List<LedgerAccountMaster> listCashLedgers() {
        List<LedgerAccountMaster> list=sessionFactory.openSession().createQuery("SELECT lam.nameOfLedger, lam.idLedger FROM LedgerAccountMaster lam INNER JOIN AccountGroupMaster agm ON (lam.underGroup=agm.idAccount) WHERE (agm.idAccount) IN (7,15)").list();
        return list;
    }
    
    public LedgerAccountMaster getLedgerAccountByKey(String name) {
        return (LedgerAccountMaster)this.sessionFactory.getCurrentSession().createQuery("from LedgerAccountMaster where idLedger='"+name+"'").uniqueResult();
    }
    
    
    //Voucher Raj
    
    public List<LedgerAccountMaster> listCashLedgersTO() {
        List<LedgerAccountMaster> list=sessionFactory.openSession().createQuery("SELECT lam.nameOfLedger, lam.idLedger FROM LedgerAccountMaster lam INNER JOIN AccountGroupMaster agm ON (lam.underGroup=agm.idAccount) WHERE (agm.idAccount) IN (15)").list();
        return list;
    }
    
    public List<LedgerAccountMaster> listCashLedgersCheque() {
        List<LedgerAccountMaster> list=sessionFactory.openSession().createQuery("SELECT lam.nameOfLedger, lam.idLedger FROM LedgerAccountMaster lam INNER JOIN AccountGroupMaster agm ON (lam.underGroup=agm.idAccount) WHERE (agm.idAccount) IN (7,16)").list();
        return list;
    }
    public List<Object[]> GetDatatableReceiptObject(String sql) {
        return (List<Object[]>)this.sessionFactory.getCurrentSession().createNativeQuery(sql).list();
    }
    public List<Object[]> GetDatatableReceiptCount(String sql2) {
        return (List<Object[]>)this.sessionFactory.getCurrentSession().createNativeQuery(sql2).list();
    }
   
}
