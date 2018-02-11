/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.dao;

import com.accounting.bean.LedgerAccountMaster;
import com.accounting.bean.VoucherReceiptAndPayment;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MR
 */
@Repository("voucherBankClearanceDao")
public class VoucherBankClearanceDao {
    
    @Autowired 
    private SessionFactory sessionfactory;
    
    public List<VoucherReceiptAndPayment> getAllBanks(){
        List<VoucherReceiptAndPayment> list = (List<VoucherReceiptAndPayment>)sessionfactory.getCurrentSession().createQuery("FROM VoucherReceiptAndPayment WHERE vouch_depositTo_bank !=0 GROUP BY vouch_depositTo_bank").list();
        return list;
    }

    
     public List<LedgerAccountMaster> getAllBankss() {
        List<LedgerAccountMaster> list=sessionfactory.openSession().createQuery("SELECT lam.nameOfLedger, lam.idLedger FROM LedgerAccountMaster lam INNER JOIN AccountGroupMaster agm ON (lam.underGroup=agm.idAccount) WHERE (agm.idAccount) IN (7,16)").list();
        return list;
    }
    
    public List<VoucherReceiptAndPayment> getVouchersByBank(String bankName, String vouchType){
        List<VoucherReceiptAndPayment> list = (List<VoucherReceiptAndPayment>)sessionfactory.getCurrentSession().createQuery("FROM VoucherReceiptAndPayment WHERE vouch_depositTo_bank='"+bankName+"' AND vouch_type='"+vouchType+"' AND authorisation='0' ORDER BY vouch_id").list();
        return list;
    }
    
    
    // update VoucherTransactionAuth
    public void updateBankClearanceById(int id){
         String queryUpdate = "update VoucherReceiptAndPayment vRP set vRP.authorisation = 1 where vRP.vouchId ='"+id+"'";
        sessionfactory.getCurrentSession().createQuery(queryUpdate).executeUpdate();
       
    }
    
    public List<Object[]> GetDatatableObject(String sql) {
        return (List<Object[]>)this.sessionfactory.getCurrentSession().createNativeQuery(sql).list();
    }
    public List<Object[]> GetDatatableCount(String sql2) {
        return (List<Object[]>)this.sessionfactory.getCurrentSession().createNativeQuery(sql2).list();
    }
    
}
