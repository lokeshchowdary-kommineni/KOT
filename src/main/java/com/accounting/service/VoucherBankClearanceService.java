/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.service;

import com.accounting.bean.LedgerAccountMaster;
import com.accounting.bean.VoucherReceiptAndPayment;
import com.accounting.dao.VoucherBankClearanceDao;
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
public class VoucherBankClearanceService {
    
    @Autowired
    private VoucherBankClearanceDao voucherBankClearanceDao;
    
     public List<VoucherReceiptAndPayment> getAllBanks(){
         return voucherBankClearanceDao.getAllBanks();
     }
     public List<LedgerAccountMaster> getAllBankss(){
         return voucherBankClearanceDao.getAllBankss();
     }
     public List<VoucherReceiptAndPayment> getVouchersByBank(String bankName, String vouchType){
         return voucherBankClearanceDao.getVouchersByBank(bankName , vouchType);
     }
     
     // update VoucherTransactionAuth
     public void updateBankClearanceById(int id){
         voucherBankClearanceDao.updateBankClearanceById(id);
     }
    
     public List<Object[]> GetDatatableObject(String query){
    return voucherBankClearanceDao.GetDatatableObject(query);
}
public List<Object[]> GetDatatableCount(String query){
    return voucherBankClearanceDao.GetDatatableCount(query);
}
}
