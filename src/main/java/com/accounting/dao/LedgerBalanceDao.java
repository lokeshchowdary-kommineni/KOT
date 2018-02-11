/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.dao;

import com.accounting.bean.Entryitems;
import com.accounting.bean.LedgerAccountMaster;
import com.accounting.service.ItemGroupZReportService;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MR
 */
@Repository("LedgerBalanceDao")
public class LedgerBalanceDao {
    @Autowired private SessionFactory sessionFactory;
    @Autowired 
     private ItemGroupZReportService itemGroupZReportService;
    
    // Get Single Ledger Cuttent balance from Entries items table
     public List<Object[]> SingleLedgerCurrentBalanceFromEntriesTable(String ledgerid,String fromDate) {
        return (List<Object[]>)this.sessionFactory.getCurrentSession().createQuery("SELECT eItems.closingAmtDr,eItems.closingAmtCr FROM  Entryitems AS  eItems WHERE eItems.id=(\n" +
"SELECT MAX(ei.id) FROM Entryitems AS ei INNER JOIN Entries AS e ON ei.entryId=e.id AND e.date<='"+fromDate+"' AND ei.ledgerId="+ledgerid+")" ).list();
    } 
     public List<Object[]> SingleLedgerOpeningBalanceFromLedgerTable(String ledgerid) {
        return (List<Object[]>)this.sessionFactory.getCurrentSession().createQuery("SELECT COALESCE((CASE WHEN lam.openingType = 'DR' THEN lam.openingAmount ELSE 0 END),0) AS dr,COALESCE((CASE WHEN lam.openingType = 'CR' THEN lam.openingAmount  ELSE 0 END),0) AS cr FROM LedgerAccountMaster lam   where lam.idLedger="+ledgerid+" " ).list();
    }  
    
      // Get Single Ledger Cuttent OPening balance from Entries items table
    public List<Object[]> SingleLedgerCurrentOpeningBalanceFromEntriesTable(String ledgerid,String fromDate) {
        return (List<Object[]>)this.sessionFactory.getCurrentSession().createQuery("SELECT eItems.closingAmtDr,eItems.closingAmtCr FROM  Entryitems AS  eItems WHERE eItems.id=(" +
        "SELECT MAX(ei.id) FROM Entryitems AS ei INNER JOIN Entries AS e ON ei.entryId=e.id AND e.date<'"+fromDate+"' AND ei.ledgerId="+ledgerid+")" ).list();
    } 
          // Get All(all sub group) Ledger Cuttent OPening balance from Entries items table
    public List<Object[]> AllSubgroupLedgerCurrentOpeningBalanceFromEntriesTable(String groupids,String fromDate) {
        return (List<Object[]>)this.sessionFactory.getCurrentSession().createQuery("SELECT coalesce(SUM(eis.closingAmtDr),0),coalesce(SUM(eis.closingAmtCr),0) FROM Entryitems eis WHERE eis.id IN(" +
            "        SELECT MAX(ei.id) FROM Entryitems ei INNER JOIN Entries e ON e.id=ei.entryId INNER JOIN LedgerAccountMaster la ON la.idLedger=ei.ledgerId" +
            "        INNER JOIN AccountGroupMaster ag ON ag.idAccount=la.underGroup AND la.underGroup IN("+groupids+") AND e.date<'"+fromDate+"'  GROUP BY ei.ledgerId" +
            ")" ).list();
    } 
    
    // Get All(all sub group) Ledger Cuttent OPening balance from Entries items table
    public List<Object[]> AllSubgroupLedgerOpeningBalanceFromLedgerAccount(String fromdate,String groupids) {
         String inIds=itemGroupZReportService.returnChildids(groupids);
      
            String id="";
            if("".equals(inIds)) 
            {  
            id="'"+groupids+"'";
            }
            else
            {
             id="'"+groupids+"',"+inIds;   
            }
        System.out.println("groupids " +groupids);
        String LedgerOPening="SELECT COALESCE(SUM(CASE WHEN lam.openingType = 'DR' THEN lam.openingAmount ELSE 0 END),0) AS dr,COALESCE(SUM(CASE WHEN lam.openingType = 'CR' THEN lam.openingAmount  ELSE 0 END),0) AS cr FROM LedgerAccountMaster lam WHERE lam.idLedger NOT IN ( " +
        "SELECT ei.ledgerId FROM Entryitems ei INNER JOIN Entries e ON e.id=ei.entryId  INNER JOIN LedgerAccountMaster lm ON " +
        "lm.idLedger=ei.ledgerId AND lm.underGroup IN("+id+") " +
        "WHERE e.date<='"+fromdate+"' GROUP BY ei.ledgerId " +
        ") AND lam.underGroup IN("+id+") ";
//        return (List<Object[]>)this.sessionFactory.getCurrentSession().createQuery("SELECT coalesce(sum(CASE WHEN lam.openingType = 'DR' THEN lam.openingAmount ELSE 0 END),0) AS dr,coalesce(sum(CASE WHEN lam.openingType = 'CR' THEN lam.openingAmount  ELSE 0 END),0) AS cr FROM LedgerAccountMaster lam LEFT JOIN Entryitems ei " +
//"ON lam.idLedger=ei.ledgerId  WHERE ei.ledgerId is null AND lam.underGroup IN ("+id+") " ).list();
          return (List<Object[]>)this.sessionFactory.getCurrentSession().createQuery(LedgerOPening).list();
    } 
     
}
