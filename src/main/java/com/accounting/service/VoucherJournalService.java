/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.service;
import com.accounting.bean.LedgerAccountMaster;
import com.accounting.bean.VoucherJournal;
import com.accounting.dao.VoucherJournalDao;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MR
 */
@Service
@Transactional
public class VoucherJournalService {
    
    @Autowired
    private VoucherJournalDao vocuherJournalDao;
    
    public int saveVoucherJournal(VoucherJournal vJournal){
        return vocuherJournalDao.saveVoucherJournal(vJournal);
    }
    
    public List<VoucherJournal> listAllVoucherJournal(){
        return vocuherJournalDao.listAllVoucherJournal();        
    }
    
     public List<LedgerAccountMaster> listOfVoucherJournalExceptCashBankAcc(){
        return vocuherJournalDao.listOfVoucherJournalExceptCashBankAcc();
    }
    
    public VoucherJournal getVoucherJournalByID(int pId) {
        return vocuherJournalDao.getVoucherJournalByID(pId);
    }
    
    public void deleteVoucherJournalById(int journalId){
        vocuherJournalDao.deleteVoucherJournalById(journalId);
    }
    
       public List<LedgerAccountMaster> listCashLedgersWithBank() {
        return vocuherJournalDao.listCashLedgersWithBank();
    }
       public List<Object[]> GetDatatableJournalObject(String query){
    return vocuherJournalDao.GetDatatableJournalObject(query);
}
public List<Object[]> GetDatatableJournalCount(String query){
    return vocuherJournalDao.GetDatatableJournalCount(query);
}
    
         public List<LedgerAccountMaster> listCashLedgersWithCIH() {
        return vocuherJournalDao.listCashLedgersWithCIH();
    }
           public List<LedgerAccountMaster> listCashLedgersWithBankList() {
        return vocuherJournalDao.listCashLedgersWithBankList();
    }
    
}
