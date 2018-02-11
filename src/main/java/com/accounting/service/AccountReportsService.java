/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.service;

import com.accounting.bean.Entryitems;
import com.accounting.bean.LedgerAccountMaster;
import com.accounting.dao.AccountReportsDao;
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
public class AccountReportsService {
    
    @Autowired private AccountReportsDao accountReportsDao;
    
        // Get All ledger ids from sub and Parent Groups
     public List<LedgerAccountMaster> getAllLedgerfromGroups(String Groupids) {
            return (List<LedgerAccountMaster>) accountReportsDao.getAllLedgerfromGroups(Groupids);
	}
    
    public List<LedgerAccountMaster> getLedgerAccountByKey(String name) {
        return accountReportsDao.getLedgerAccountByKey(name);
    }
    
    public List<Entryitems> getLedgerEntries(int l_id) {
        return accountReportsDao.getLedgerEntries(l_id);
    }
    
    public List<Object> getReportByDateAndLedger(String query) {
        return accountReportsDao.getReportByDateAndLedger(query);
    }
    
    public List<Object> getReportByDateAndunderGroup(String query) {
       return accountReportsDao.getReportByDateAndunderGroup(query);
    }
    
     public List<Object[]> GetAllSubGroupOpeningBalanceFromLedger(String groupids) {
       return accountReportsDao.GetAllSubGroupOpeningBalanceFromLedger(groupids);
    }
      public List<Object[]> GetAllSubGroupClosingBalanceBasedOnFromDate(String groupids,String Fdate) {
       return accountReportsDao.GetAllSubGroupClosingBalanceBasedOnFromDate(groupids,Fdate);
    }
      
     
     public List<Object[]> GetSingleGroupOpeningBalanceFromLedger(String groupid) {
       return accountReportsDao.GetAllSubGroupOpeningBalanceFromLedger(groupid);
    }
      public List<Object[]> GetSingleGroupClosingBalanceBasedOnFromDate(String groupid,String Fdate) {
       return accountReportsDao.GetAllSubGroupClosingBalanceBasedOnFromDate(groupid,Fdate);
    }  
    
      public List<Object[]> GetSingleGroupEndDateCurrentBalance(String groupid,String Startdate,String endDate) {
       return accountReportsDao.GetSingleGroupEndDateCurrentBalance(groupid,Startdate,endDate);
    }  
    public List<Object[]> GetSingleGroupEndDateCurrentBalance_with_EntryType(String groupid,String Startdate,String endDate,String entryType) {
       return accountReportsDao.GetSingleGroupEndDateCurrentBalance_with_Entry_type(groupid,Startdate,endDate,entryType);
    }
    public List<Object[]> trailBalance(int groupid,String Startdate,String endDate) {
       return accountReportsDao.trailbalance(groupid,Startdate,endDate);
    }  
    public List<Object[]> AccountGroupOpeningBalance(String groupid,String Startdate) {
       return accountReportsDao.accountGroupOpeningBalance(groupid,Startdate);
    }  
}
