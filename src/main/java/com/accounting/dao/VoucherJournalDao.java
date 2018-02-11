/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.dao;

import com.accounting.bean.LedgerAccountMaster;
import com.accounting.bean.VoucherJournal;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MR
 */
@Repository("voucherJournalDao")
public class VoucherJournalDao {
    @Autowired
    private SessionFactory sessionFactory;
    
    public int saveVoucherJournal(VoucherJournal vJournal){
        sessionFactory.getCurrentSession().saveOrUpdate(vJournal);
        return vJournal.getVouchId();
    }
    
    public List<VoucherJournal> listAllVoucherJournal() {
        List<VoucherJournal> list=sessionFactory.openSession().createQuery("FROM VoucherJournal").list();
        return list;
    }
     public List<LedgerAccountMaster> listOfVoucherJournalExceptCashBankAcc() {
        List<LedgerAccountMaster> list=sessionFactory.openSession().createQuery("FROM LedgerAccountMaster WHERE  NOT  underGroup='15' AND NOT underGroup='7' AND NOT underGroup='16'").list();
        return list;
    }
    
    public VoucherJournal getVoucherJournalByID(int pId) {
        
        return (VoucherJournal) sessionFactory.getCurrentSession().get(VoucherJournal.class, pId);
    }
    
    public void deleteVoucherJournalById(int journalId){
        sessionFactory.openSession().createQuery("DELETE FROM VoucherJournal WHERE journalId='"+journalId+"'").executeUpdate();
       
    }
    
     public List<Object[]> GetDatatableJournalObject(String sql) {
        return (List<Object[]>)this.sessionFactory.getCurrentSession().createNativeQuery(sql).list();
    }
    public List<Object[]> GetDatatableJournalCount(String sql2) {
        return (List<Object[]>)this.sessionFactory.getCurrentSession().createNativeQuery(sql2).list();
    }
    
    public List<LedgerAccountMaster> listCashLedgersWithBank() {
        List<LedgerAccountMaster> list=sessionFactory.openSession().createQuery("SELECT lam.nameOfLedger, lam.idLedger FROM LedgerAccountMaster lam INNER JOIN AccountGroupMaster agm ON (lam.underGroup=agm.idAccount) WHERE (agm.idAccount) IN (7,15,16)").list();
        return list;
    }
    
     public List<LedgerAccountMaster> listCashLedgersWithCIH() {
        List<LedgerAccountMaster> list=sessionFactory.openSession().createQuery("SELECT lam.nameOfLedger, lam.idLedger FROM LedgerAccountMaster lam INNER JOIN AccountGroupMaster agm ON (lam.underGroup=agm.idAccount) WHERE (agm.idAccount) IN (15)").list();
        return list;
    } 
     
     public List<LedgerAccountMaster> listCashLedgersWithBankList() {
        List<LedgerAccountMaster> list=sessionFactory.openSession().createQuery("SELECT lam.nameOfLedger, lam.idLedger FROM LedgerAccountMaster lam INNER JOIN AccountGroupMaster agm ON (lam.underGroup=agm.idAccount) WHERE (agm.idAccount) IN (7,16)").list();
        return list;
    }
    
}
