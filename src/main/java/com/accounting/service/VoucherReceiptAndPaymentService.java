/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.service;

import com.accounting.bean.Entries;
import com.accounting.bean.Entryitems;
import com.accounting.bean.LedgerAccountMaster;
import com.accounting.bean.VoucherReceiptAndPayment;
import com.accounting.dao.VoucherReceiptAndPaymentDao;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SHINELOGICS
 */
@Service
@Transactional
public class VoucherReceiptAndPaymentService {
    
    @Autowired
    private VoucherReceiptAndPaymentDao voucherDao;
    
    public int saveVoucher(VoucherReceiptAndPayment vReceipt){
        return voucherDao.saveVoucher(vReceipt);
    }
    public int saveVoucherToEntries(Entries et){
        return voucherDao.saveVoucherToEntries(et);
    }
    public void updateVoucherToEntries(int e_id, Entries ei){
        voucherDao.updateVoucherToEntries(e_id, ei);
    }
    public Entries getVoucherEntryId(Entries ei){
        return voucherDao.getVoucherEntryId(ei);
    }
    public int saveVoucherToEntryItems(Entryitems ei){
        return voucherDao.saveVoucherToEntryItems(ei);
    }
    public List<Entryitems> getVoucherEntryItemsId(Entryitems et){
        return voucherDao.getVoucherEntryItemsId(et);
    }
    public void updateVoucherToEntryItems(Entryitems et){
        voucherDao.updateVoucherToEntryItems(et);
    }
    
    public VoucherReceiptAndPayment getVoucherById(int vouchId){
        return voucherDao.getVoucherById(vouchId);
    }
    
    public List<VoucherReceiptAndPayment> getVoucherList(){
        return voucherDao.getVoucherList();
    }
    
     public List<VoucherReceiptAndPayment> getVoucherReceiptList(String receipt){
        return voucherDao.getVoucherReceiptList(receipt);
    }
     
     public List<VoucherReceiptAndPayment> getVoucherPaymentList(String payment){
        return voucherDao.getVoucherPaymentList(payment);
    }
    
    public void deleteVoucherById(int vouchId){
        voucherDao.deleteVoucherById(vouchId);
       
    }
    
    public List<LedgerAccountMaster> listAllLedgerNames() {
        return voucherDao.listAllLedgerNames();
    }
    
     public List<LedgerAccountMaster> listAllLedgerExceptCIN() {
        return voucherDao.listAllLedgerExceptCIN();
    }
     
    public List<LedgerAccountMaster> listCashLedgers() {
        return voucherDao.listCashLedgers();
    }
     
    public LedgerAccountMaster getLedgerAccountByKey(String name) {
        return voucherDao.getLedgerAccountByKey(name);
    }
   
    //Voucher Raj
    
     public List<LedgerAccountMaster> listCashLedgersTO() {
        return voucherDao.listCashLedgersTO();
    }
     
     public List<LedgerAccountMaster> listCashLedgersCheque() {
        return voucherDao.listCashLedgersCheque();
    }
     // to get datatable object in json -- receipt
     public List<Object[]> GetDatatableReceiptObject(String query){
    return voucherDao.GetDatatableReceiptObject(query);
    }
     public List<Object[]> GetDatatableReceiptCount(String query){
    return voucherDao.GetDatatableReceiptCount(query);
    }
    
}
