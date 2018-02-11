/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.accounting.service;

import com.accounting.bean.AccountGroupMaster;
import com.accounting.dao.AccountGroup_dao;
import com.accounting.dao.LedgerBalanceDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author shinelogics
 */
@Service
@Transactional
public class LedgerBalanceService {
     @Autowired
        private LedgerBalanceDao lbd;
	
    public List<Object[]> SingleLedgerCurrentBalanceFromEntriesTable(String ledgerid,String Fromdate) {
       return lbd.SingleLedgerCurrentBalanceFromEntriesTable(ledgerid,Fromdate);
    }  
    public List<Object[]> SingleLedgerOpeningBalanceFromLedgerTable(String ledgerid) {
       return lbd.SingleLedgerOpeningBalanceFromLedgerTable(ledgerid);
    }  
    public List<Object[]> SingleLedgerCurrentOpeningBalanceFromEntriesTable(String ledgerid,String Fromdate) {
       return lbd.SingleLedgerCurrentOpeningBalanceFromEntriesTable(ledgerid,Fromdate);
    }  
     public List<Object[]> AllsubGroupLedgerCurrentOpeningBalanceFromEntriesTable(String groupIds,String Fromdate) {
       return lbd.AllSubgroupLedgerCurrentOpeningBalanceFromEntriesTable(groupIds,Fromdate);
    }  
    public List<Object[]> AllsubGroupLedgerOpeningBalanceFromLedgerTable(String fromdate,String groupIds) {
       return lbd.AllSubgroupLedgerOpeningBalanceFromLedgerAccount(fromdate,groupIds);
    }  
}
