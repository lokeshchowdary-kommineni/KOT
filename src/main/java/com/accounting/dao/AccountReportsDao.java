/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.dao;

import com.accounting.bean.Entryitems;
import com.accounting.bean.LedgerAccountMaster;
import com.accounting.service.ItemGroupZReportService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.ArrayUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MR
 */
@Repository("accountReportsDao")
public class AccountReportsDao {
    @Autowired private SessionFactory sessionFactory;
     @Autowired 
     private ItemGroupZReportService itemGroupZReportService;
    
    // Get All ledger ids from sub and Parent Groups
     public List<LedgerAccountMaster> getAllLedgerfromGroups(String Groupids) {
            return (List<LedgerAccountMaster>) sessionFactory.getCurrentSession().createQuery(" FROM LedgerAccountMaster where underGroup in ("+Groupids+")").list();
	}
    
    
    public List<LedgerAccountMaster> getLedgerAccountByKey(String name) {
        return (List<LedgerAccountMaster>)this.sessionFactory.getCurrentSession().createQuery("from LedgerAccountMaster where underGroup='"+name+"'").list();
    }
    
    public List<Entryitems> getLedgerEntries(int l_id) {
        return (List<Entryitems>)this.sessionFactory.getCurrentSession().createQuery("from Entryitems where ledgerId='"+l_id+"'").list();
    }
    
    public List<Object> getReportByDateAndLedger(String query) {
        return (List<Object>)this.sessionFactory.getCurrentSession().createQuery(query).list();
    }
    
    public List<Object> getReportByDateAndunderGroup(String query) {
        return (List<Object>)this.sessionFactory.getCurrentSession().createQuery(query).list();
    }
    // For All Group from Ledger OPening Balance
    public List<Object[]> GetAllSubGroupOpeningBalanceFromLedger(String groupids) {
        return (List<Object[]>)this.sessionFactory.getCurrentSession().createQuery(" SELECT  SUM(CASE WHEN lam.openingType = 'DR' THEN lam.openingAmount ELSE 0 END) AS ldr, \n" +
" SUM(CASE WHEN lam.openingType = 'CR' THEN lam.openingAmount ELSE 0 END) AS lcr  FROM  LedgerAccountMaster lam\n" +
" WHERE lam.underGroup IN("+groupids+") ").list();
    }
   // For All Group OPening Balance by from date 
    public List<Object[]> GetAllSubGroupClosingBalanceBasedOnFromDate(String groupids,String Fdate) {
        return (List<Object[]>)this.sessionFactory.getCurrentSession().createQuery("  SELECT SUM(ei.closingAmtDr),SUM(ei.closingAmtCr) FROM Entryitems ei WHERE e.id IN (\n" +
" SELECT MAX(ei.id)\n" +
" FROM Entryitems ei INNER JOIN Entries e ON e.id=ei.entryId INNER JOIN LedgerAccountMaster lam ON lam.idLedger=ei.ledgerId \n" +
" INNER JOIN AccountGroupMaster agm ON agm.idAccount=lam.underGroup AND lam.underGroup IN ("+groupids+") AND e.date < '"+Fdate+"' \n" +
" )").list();
    }
      // For Single Group from Ledger OPening Balance 
     public List<Object[]> GetSingleGroupOpeningBalanceFromLedger(String groupid) {
        return (List<Object[]>)this.sessionFactory.getCurrentSession().createQuery(" SELECT  SUM(CASE WHEN lam.openingType = 'DR' THEN lam.openingAmount ELSE 0 END) AS ldr, \n" +
" SUM(CASE WHEN lam.openingType = 'CR' THEN lam.openingAmount ELSE 0 END) AS lcr  FROM  LedgerAccountMaster lam\n" +
" WHERE lam.underGroup in ("+groupid+") ").list();
    }
     // For Single Group OPening Balance by from date
    public List<Object[]> GetSingleGroupClosingBalanceBasedOnFromDate(String groupid,String Fdate) {
        return (List<Object[]>)this.sessionFactory.getCurrentSession().createQuery("  SELECT SUM(CASE WHEN ei.type = 'C' THEN ei.amount ELSE 0 END) AS cr,SUM(CASE WHEN ei.type = 'D' THEN ei.amount ELSE 0 END) AS dr FROM Entryitems ei INNER JOIN Entries e ON e.id=ei.entryId INNER JOIN LedgerAccountMaster lam ON lam.idLedger=ei.ledgerId INNER JOIN AccountGroupMaster agm ON agm.idAccount=lam.underGroup AND lam.underGroup in ("+groupid+") AND e.date <= '"+Fdate+"' ").list();
    } 
    // For Profit and Loss Get All ledger current closing balance  from entries table
     public List<Object[]> GetSingleGroupEndDateCurrentBalance(String groupid,String Startdate ,String enddate) {
      String inIds=itemGroupZReportService.returnChildids(groupid);
      
            String id="";
            if("".equals(inIds)) 
            {  
            id="'"+groupid+"'";
            }
            else
            {
             id="'"+groupid+"',"+inIds;   
            }
       // return (List<Object[]>)this.sessionFactory.getCurrentSession().createQuery("SELECT ei.id,SUM(CASE WHEN ei.type = 'C' THEN ei.amount ELSE 0 END) AS cr,SUM(CASE WHEN ei.type = 'D' THEN ei.amount ELSE 0 END) AS dr ,DATE_FORMAT(e.date,'%M-%Y'),agm.accountGroupName,agm.idAccount,lam.idLedger,lam.nameOfLedger,e.entryType FROM Entryitems ei INNER JOIN Entries e ON e.id=ei.entryId INNER JOIN LedgerAccountMaster lam ON lam.idLedger=ei.ledgerId INNER JOIN AccountGroupMaster agm ON agm.idAccount=lam.underGroup AND lam.underGroup in ("+groupid+") and e.date BETWEEN '"+Fdate+"' AND '"+enddate+"'  GROUP BY lam.underGroup,lam.idLedger ").list();
       return (List<Object[]>)this.sessionFactory.getCurrentSession().createQuery(" SELECT ei.id,ei.closingAmtDr,ei.closingAmtCr,agm.accountGroupName,agm.idAccount,lam.idLedger,lam.nameOfLedger,e.entryType FROM Entryitems ei\n" +
" INNER JOIN Entries e ON e.id=ei.entryId INNER JOIN LedgerAccountMaster lam ON lam.idLedger=ei.ledgerId \n" +
" INNER JOIN AccountGroupMaster agm ON agm.idAccount=lam.underGroup WHERE ei.id IN\n" +
" (  \n" +
" SELECT MAX(ei.id) FROM Entryitems ei\n" +
" INNER JOIN Entries e ON e.id=ei.entryId INNER JOIN LedgerAccountMaster lam ON lam.idLedger=ei.ledgerId \n" +
" INNER JOIN AccountGroupMaster agm ON agm.idAccount=lam.underGroup AND lam.underGroup IN ("+id+") AND e.date\n" +
"  between '"+Startdate+"' and '"+enddate+"'  GROUP BY lam.underGroup,lam.idLedger\n" +
" )").list();
       
   // for calculate current balance 
//    return (List<Object[]>)this.sessionFactory.getCurrentSession().createQuery(" SELECT ei.id,ei.closingAmtDr,ei.closingAmtCr,agm.accountGroupName,agm.idAccount,lam.idLedger,lam.nameOfLedger,e.entryType FROM Entryitems ei\n" +
//" INNER JOIN Entries e ON e.id=ei.entryId INNER JOIN LedgerAccountMaster lam ON lam.idLedger=ei.ledgerId \n" +
//" INNER JOIN AccountGroupMaster agm ON agm.idAccount=lam.underGroup WHERE ei.id IN\n" +
//" (  \n" +
//" SELECT MAX(ei.id) FROM Entryitems ei\n" +
//" INNER JOIN Entries e ON e.id=ei.entryId INNER JOIN LedgerAccountMaster lam ON lam.idLedger=ei.ledgerId \n" +
//" INNER JOIN AccountGroupMaster agm ON agm.idAccount=lam.underGroup AND lam.underGroup IN ("+id+") AND " +
//"  e.date<='"+enddate+"'  GROUP BY lam.underGroup,lam.idLedger\n" +
//" )").list();
       
    } 
     // For Profit and Loss Get All ledger current closing balance  from entries table based Purchase Defination and vat formula
     public List<Object[]> GetSingleGroupEndDateCurrentBalance_with_Entry_type(String groupid,String Startdate ,String enddate,String entriesType) {
      String inIds=itemGroupZReportService.returnChildids(groupid);
      
            String id="";
            if("".equals(inIds)) 
            {  
            id="'"+groupid+"'";
            }
            else
            {
             id="'"+groupid+"',"+inIds;   
            }
       // return (List<Object[]>)this.sessionFactory.getCurrentSession().createQuery("SELECT ei.id,SUM(CASE WHEN ei.type = 'C' THEN ei.amount ELSE 0 END) AS cr,SUM(CASE WHEN ei.type = 'D' THEN ei.amount ELSE 0 END) AS dr ,DATE_FORMAT(e.date,'%M-%Y'),agm.accountGroupName,agm.idAccount,lam.idLedger,lam.nameOfLedger,e.entryType FROM Entryitems ei INNER JOIN Entries e ON e.id=ei.entryId INNER JOIN LedgerAccountMaster lam ON lam.idLedger=ei.ledgerId INNER JOIN AccountGroupMaster agm ON agm.idAccount=lam.underGroup AND lam.underGroup in ("+groupid+") and e.date BETWEEN '"+Fdate+"' AND '"+enddate+"'  GROUP BY lam.underGroup,lam.idLedger ").list();
       return (List<Object[]>)this.sessionFactory.getCurrentSession().createQuery(" SELECT ei.id,ei.closingAmtDr,ei.closingAmtCr,agm.accountGroupName,agm.idAccount,lam.idLedger,lam.nameOfLedger,e.entryType FROM Entryitems ei\n" +
" INNER JOIN Entries e ON e.id=ei.entryId INNER JOIN LedgerAccountMaster lam ON lam.idLedger=ei.ledgerId \n" +
" INNER JOIN AccountGroupMaster agm ON agm.idAccount=lam.underGroup WHERE ei.id IN\n" +
" (  \n" +
" SELECT MAX(ei.id) FROM Entryitems ei\n" +
" INNER JOIN Entries e ON e.id=ei.entryId INNER JOIN LedgerAccountMaster lam ON lam.idLedger=ei.ledgerId \n" +
" INNER JOIN AccountGroupMaster agm ON agm.idAccount=lam.underGroup AND lam.underGroup IN ("+id+") AND e.date\n" +
"  between '"+Startdate+"' and '"+enddate+"' and e.entryType='"+entriesType+"' GROUP BY lam.underGroup,lam.idLedger\n" +
" )").list();
       
   // for calculate current balance 
//    return (List<Object[]>)this.sessionFactory.getCurrentSession().createQuery(" SELECT ei.id,ei.closingAmtDr,ei.closingAmtCr,agm.accountGroupName,agm.idAccount,lam.idLedger,lam.nameOfLedger,e.entryType FROM Entryitems ei\n" +
//" INNER JOIN Entries e ON e.id=ei.entryId INNER JOIN LedgerAccountMaster lam ON lam.idLedger=ei.ledgerId \n" +
//" INNER JOIN AccountGroupMaster agm ON agm.idAccount=lam.underGroup WHERE ei.id IN\n" +
//" (  \n" +
//" SELECT MAX(ei.id) FROM Entryitems ei\n" +
//" INNER JOIN Entries e ON e.id=ei.entryId INNER JOIN LedgerAccountMaster lam ON lam.idLedger=ei.ledgerId \n" +
//" INNER JOIN AccountGroupMaster agm ON agm.idAccount=lam.underGroup AND lam.underGroup IN ("+id+") AND " +
//"  e.date<='"+enddate+"'  GROUP BY lam.underGroup,lam.idLedger\n" +
//" )").list();
       
    } 
    // Trail balance query 
     // For Profit and Loss Get All ledger current closing balance  from entries table based Purchase Defination and vat formula
     public List<Object[]> trailbalance(int groupid,String Startdate ,String enddate) {
      String inIds=itemGroupZReportService.returnChildids(String.valueOf(groupid));
      
            String id="";
            if("".equals(inIds)) 
            {  
            id="'"+groupid+"'";
            }
            else
            {
             id="'"+groupid+"',"+inIds;   
            }
  String queryfirstHalf = "  SELECT lam.name_of_ledger,lam.id_ledger,agm.account_group_name,lam.opening_amount,lam.opening_type, \n" +
"  SUM(CASE WHEN et.type='D' THEN et.amount ELSE 0 END)+(CASE WHEN lam.opening_type = 'DR' AND et.ledger_id IS NULL THEN lam.opening_amount ELSE 0 END) AS dr,\n" +
"   SUM(CASE WHEN et.type='C' THEN et.amount ELSE 0 END)+(CASE WHEN lam.opening_type = 'CR' AND et.ledger_id IS NULL THEN lam.opening_amount ELSE 0 END) AS cr\n" +
"    FROM entryitems et RIGHT JOIN ledger_account_master lam ON (lam.id_ledger=et.ledger_id)\n" +
"     AND et.ledger_id IN (SELECT MAX(ei.ledger_id) FROM entries entries1_ INNER JOIN entryitems ei ON \n" +
"     (ei.entry_id=entries1_.id)INNER JOIN ledger_account_master lam ON (lam.id_ledger=ei.ledger_id) WHERE (lam.under_group IN ("+id+"))\n" +
"      AND (entries1_.date <= '"+enddate+"') GROUP BY ei.ledger_id) INNER JOIN  account_group_master agm ON agm.id_account=lam.under_group\n" +
"	GROUP BY lam.id_ledger  ORDER BY lam.id_ledger ";
  List<Object[]> first=this.sessionFactory.getCurrentSession().createNativeQuery(queryfirstHalf).list();

  List<Object[]> second=this.sessionFactory.getCurrentSession().createNativeQuery("SELECT  COALESCE(et.closing_amt_dr,0)+(CASE WHEN lam.opening_type = 'DR' AND et.ledger_id IS  NULL   THEN lam.opening_amount ELSE 0 END) AS dr,\n" +
" COALESCE(et.closing_amt_cr,0)+(CASE WHEN lam.opening_type = 'CR' AND et.ledger_id IS  NULL THEN lam.opening_amount ELSE 0 END) AS cr\n" +
"FROM entryitems et RIGHT JOIN ledger_account_master lam ON (lam.id_ledger=et.ledger_id) \n" +

" AND et.id IN (SELECT MAX(ei.id) FROM entries entries1_ \n" +
"INNER JOIN entryitems ei ON (ei.entry_id=entries1_.id)INNER JOIN ledger_account_master lam ON \n" +
"(lam.id_ledger=ei.ledger_id) WHERE (lam.under_group \n" +
"IN ("+id+")) AND \n" +
"(entries1_.date <= '"+enddate+"') GROUP BY \n" +
" ei.ledger_id) INNER JOIN account_group_master agm ON agm.id_account=lam.under_group GROUP BY lam.id_ledger  ORDER BY lam.id_ledger  ").list();
        
            Object[] both={};
            Iterator    it1 = first.iterator();
            Iterator it2 = second.iterator();
            List<Object[]> finalGroupobj = new ArrayList<Object[]>();
           List<Object[]> a=null;
            while(it1.hasNext() && it2.hasNext()) 
            {
               Object[] v1= (Object[]) it1.next();
               Object[] v2= (Object[]) it2.next();
             
                both = ArrayUtils.addAll(v1, v2);
                finalGroupobj.add(both);
            }

       return finalGroupobj;
    } 
     public List<Object[]> accountGroupOpeningBalance(String groupid,String Startdate ) {
//      String inIds=itemGroupZReportService.returnChildids(String.valueOf(groupid));
//      
//            String id="";
//            if("".equals(inIds)) 
//            {  
//            id="'"+groupid+"'";
//            }
//            else
//            {
//             id="'"+groupid+"',"+inIds;   
//            }


  List<Object[]> Balance=this.sessionFactory.getCurrentSession().createNativeQuery("SELECT  COALESCE(et.closing_amt_dr,0)+(CASE WHEN lam.opening_type = 'DR' AND et.ledger_id IS  NULL   THEN lam.opening_amount ELSE 0 END) AS dr,\n" +
" COALESCE(et.closing_amt_cr,0)+(CASE WHEN lam.opening_type = 'CR' AND et.ledger_id IS  NULL THEN lam.opening_amount ELSE 0 END) AS cr\n" +
"FROM entryitems et RIGHT JOIN ledger_account_master lam ON (lam.id_ledger=et.ledger_id) \n" +

" AND et.id IN (SELECT MAX(ei.id) FROM entries entries1_ \n" +
"INNER JOIN entryitems ei ON (ei.entry_id=entries1_.id)INNER JOIN ledger_account_master lam ON \n" +
"(lam.id_ledger=ei.ledger_id) WHERE (lam.under_group \n" +
"IN ("+groupid+")) AND \n" +
"(entries1_.date <= '"+Startdate+"') GROUP BY \n" +
" ei.ledger_id) INNER JOIN account_group_master agm ON agm.id_account=lam.under_group AND lam.under_group IN ("+groupid+")GROUP BY lam.id_ledger  ORDER BY lam.id_ledger  ").list();
        
         

       return Balance;
    } 
}
