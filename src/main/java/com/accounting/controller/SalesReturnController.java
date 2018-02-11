
package com.accounting.controller; 

import com.accounting.bean.CompanyInformation;
import com.accounting.bean.DatatableObject;
import com.accounting.bean.Entries;
import com.accounting.bean.Entryitems;
import com.accounting.bean.ItemMaster;
import com.accounting.bean.ItemReport;
import com.accounting.bean.PurchaseForm;
import com.accounting.bean.Sales;
import com.accounting.bean.SalesEstimate;
import com.accounting.bean.SalesEstimateForm;
import com.accounting.bean.SalesEstimateItem;
import com.accounting.bean.SalesForm;
import com.accounting.bean.SalesItem;
import com.accounting.bean.SalesReturn;
import com.accounting.bean.SalesReturnForm;
import com.accounting.bean.SalesReturnItem;
import com.accounting.service.AccountDBOService;
import com.accounting.service.Buyer_service;
import com.accounting.service.CompanyService;
import com.accounting.service.EntryService;
import com.accounting.service.ItemMasterService;
import com.accounting.service.ItemReport_service;
import com.accounting.service.LedgerAccount_Service;
import com.accounting.service.LedgerBalanceService;
import com.accounting.service.SalesReturnService;
import com.accounting.service.SalesService;
import com.accounting.service.TaxStructure_Service;
import com.accounting.validator.SalesFormValidator;
import com.accounting.validator.SalesReturnFormValidator;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RestController
public class SalesReturnController {
    
    @Autowired
    private SalesReturnFormValidator salesReturnFormValidator;
    
    @Autowired
    private SalesReturnService salesReturnService;
    
    @Autowired
    private SalesService salesService;
    
    @Autowired
    private TaxStructure_Service tServ;
    
    @Autowired
    private ItemMasterService itemMasterService;
    
    @Autowired
    private EntryService entryService;
    
    @Autowired
    private LedgerAccount_Service ledgerAccountService;
    
    @Autowired
    private ItemReport_service itemReportService;
    
    @Autowired
    private CompanyService companyservice; 
    
     SalesReturn sr=new SalesReturn();
    
    @Autowired
    private Buyer_service bs;
    
    @Autowired
    private LedgerBalanceService lbs;
       @Autowired
    private AccountDBOService as;
       @Autowired
    private SessionFactory sessionFactory;
    //Set a form validator
    @InitBinder("salesReturnForm")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(this.salesReturnFormValidator);    
    }

@RequestMapping(value={"SalesReturn"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
public ModelAndView itemMastere()
{
    SalesReturn s=new SalesReturn();
    SalesReturnItem srItem=new SalesReturnItem();
    SalesReturnForm sForm=new SalesReturnForm();
    List<SalesReturnItem> list=new ArrayList<SalesReturnItem>();
    list.add(srItem);
    sForm.setSale(s);
    sForm.setSales(list);
    
    ModelAndView model = new ModelAndView("SalesReturn");
    model.addObject("salesReturnForm", sForm);
    return model;
}

@RequestMapping(value={"SaveSalesReturn"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
public ModelAndView saveItem(@ModelAttribute("salesReturnForm") @Validated SalesReturnForm salesReturnForm, BindingResult result,
         RedirectAttributes redirect,HttpSession ses)
{
    
    if (result.hasErrors()) {
        return new ModelAndView("SalesReturn");
        
    }
    else{
     
          // start finacial year  
       String yearRange=as.GetYearRange();
       long previousId=as.getPreviousIdBasedStartAndEndYear("id", "SalesReturn", yearRange);
       
        String primaryIdPE="";  
          if(previousId==0){
             primaryIdPE=previousId+1+"/"+yearRange;
          }else{
               primaryIdPE=previousId+1+"/"+yearRange;
          }
           ses.setAttribute("primaryIdPE", primaryIdPE);
         
          // end of finacial year  
        
       Date returnDate= sr.getReturnDate();
     
       SalesReturn s=salesReturnForm.getSale();
       s.setId(null);
   
      double vaa=0;
      double mca=0;
      double assessValue=0;
      double totalVat=0;
      double totalCgst=0;
      double totalIgst=0;
      double invoiceAmount=0;
      
      double vaaEstimate=0;
      double mcaEstimate=0;
      double assessValueEstimate=0;
      double totalVatEstimate=0;
      double totalCgstEstimate=0;
      double invoiceAmountEstimate=0;
       
       List list=salesReturnForm.getSales();
       
       SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();        
        String CurrentDate= dmyFormat.format(date);
       
       int buyerLedgerId=ledgerAccountService.getLederByReferenceId(s.getBuyerId(), "Buyer").iterator().next().getIdLedger();
       String BuyerName=ledgerAccountService.getLederByReferenceId(s.getBuyerId(), "Buyer").iterator().next().getNameOfLedger();
       List<Object[]> currentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(buyerLedgerId), CurrentDate);
      
       double openingBalanceOfLedger=0;
       if( currentBalance!=null && currentBalance.size()>0)
       {
             for (Object[] column : currentBalance) {
                        openingBalanceOfLedger = (Double)column[0]- (Double)column[1];
                }
           
       }
       else
       {
           List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(buyerLedgerId));
           for (Object[] column : OpeningBalance) {
                        openingBalanceOfLedger = (Double)column[0]- (Double)column[1];
                }
       }
       double buyervoucherBalance=openingBalanceOfLedger-s.getInvoiceAmount();
       if(buyervoucherBalance>0) 
                {
                    s.setBuyerBalance(buyervoucherBalance);
                    s.setBuyerType("DR");
                }
                else
                {
                    s.setBuyerBalance(Math.abs(buyervoucherBalance));
                    s.setBuyerType("CR");
                }
       if(s.getMediatorId()!=null && !s.getMediatorId().isEmpty())
       {
        int mediLedgerId=ledgerAccountService.getLederByReferenceId(s.getMediatorId(), "Buyer").iterator().next().getIdLedger();
        List<Object[]> salesCASHMEDLedgerIdcurrentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(mediLedgerId), CurrentDate);
        double openingBalance_salesCASHMEDLedgerId=0; 
       if( salesCASHMEDLedgerIdcurrentBalance!=null && salesCASHMEDLedgerIdcurrentBalance.size()>0)
       {
             for (Object[] column : salesCASHMEDLedgerIdcurrentBalance) {
                        openingBalance_salesCASHMEDLedgerId = (Double)column[0]- (Double)column[1];
                }
           
       }
       else
       {
           List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(mediLedgerId));
           for (Object[] column : OpeningBalance) {
                        openingBalance_salesCASHMEDLedgerId = (Double)column[0]- (Double)column[1];
                }
       }
       if(openingBalance_salesCASHMEDLedgerId>0) 
                {
                    s.setMediatorBalance(openingBalance_salesCASHMEDLedgerId);
                    s.setMediatorType("DR");
                }
                else
                {
                    s.setMediatorBalance(Math.abs(openingBalance_salesCASHMEDLedgerId));
                    s.setMediatorType("CR");
                }
       }
    
       
      String invoicNo=salesReturnService.saveSalesReturn(s);    
       HashSet<String> set=new HashSet<String>();   
       Iterator itr=list.iterator();
       while(itr.hasNext())
       {
           SalesReturnItem sItem=(SalesReturnItem)itr.next();
        if(sItem.getReturnQuantity()>0 && sItem.getReturnQuantity()!=null)
        { 
           sItem.setInvoiceNo(s.getInvoiceNo());
           ItemMaster iMaster = (ItemMaster)this.itemMasterService.getItmeByItemNo(sItem.getItemCode());
          
           if(sItem.getId()==null){            
           double qty=0.0;
           if(sItem.getUnit()==iMaster.getAltUnit())
           {
           qty=sItem.getReturnQuantity()/(Double.parseDouble(iMaster.getTotalUnit()));  
           String query="UPDATE `item_master` SET current_stock=current_stock+"+qty+" WHERE item_code='"+sItem.getItemCode()+"'";
           itemMasterService.updateItemMaster(query);
           }
           else
           {
            String query="UPDATE `item_master` SET current_stock=current_stock+"+sItem.getReturnQuantity()+" WHERE item_code='"+sItem.getItemCode()+"'";
            itemMasterService.updateItemMaster(query);
           }    
               
         
           double ccs = 0;
           double salesCp = 0;
           if(s.getCategory().equalsIgnoreCase("Consumers(B2C)"))
           {
              double cpGST=sItem.getEpepV();
              salesCp=(cpGST*100)/(100+Double.parseDouble(sItem.getTax()));
              System.out.println(" Consumers(B2C)  salesCp cpGST "+cpGST +" salesCp "+salesCp);
           }
           else if(s.getCategory().equalsIgnoreCase("GST Dealers(B2B)"))
           {
              salesCp=sItem.getEpepV();
              System.out.println(" GST Dealers(B2B)  salesCp cpGST "+salesCp +" salesCp "+salesCp);
           }    
      if ((iMaster.getCurrentStock() != null)) {
        ccs = (iMaster.getCurrentStock());
      }
      double psv = ccs * iMaster.getCp();
      double isv = sItem.getReturnQuantity().doubleValue() * salesCp;
      ccs = (int)(ccs + sItem.getReturnQuantity().doubleValue());
      
      double cp = (psv + isv) / ccs;
      double newCp=0.0;
      if(salesCp<=cp)
      {  
          newCp=salesCp;   
          
      }
      else if(salesCp>cp)
      {
         newCp=iMaster.getTp();
         
      }
      
      System.out.print("New cp:" + newCp);
     if (salesCp>cp)
      {
        System.out.print("New cp: in if loop");
        double pp = newCp;
        double lr = 0.0D;
        double er = 0.0D;
        double tr = iMaster.getTr().doubleValue();
        double la = lr / 100.0D * pp;
        double lp = pp - la;
        double ea = er / 100.0D * lp;
        double ep = lp + ea;
        double ta = tr / 100.0D * ep;
        double tp = ep + ta;
        
        double vap_noRound = ep * 100.0D / (100.0D - iMaster.getVa().doubleValue());
        String vapFixed=(new DecimalFormat("##.##").format(vap_noRound));
        Double vap=as.roundUpService(Double.parseDouble(vapFixed));
        
        double vbp_noRound = ep * 100.0D / (100.0D - iMaster.getVb().doubleValue());
        String vbpFixed=(new DecimalFormat("##.##").format(vbp_noRound));
        Double vbp=as.roundUpService(Double.parseDouble(vbpFixed));
        
        double cap_noRound = (ep * 100.0D + ep * tr) / (100.0D - iMaster.getCa().doubleValue());
        String capFixed=(new DecimalFormat("##.##").format(cap_noRound));
        Double cap=as.roundUpService(Double.parseDouble(capFixed));
        
        double cbp_noRound= (ep * 100.0D + ep * tr) / (100.0D - iMaster.getCb().doubleValue());
        String cbpFixed=(new DecimalFormat("##.##").format(cbp_noRound));
        Double cbp=as.roundUpService(Double.parseDouble(cbpFixed));
        
                double lpAlt = 0.0;
                double epAlt = 0.0;
                double tpAlt = 0.0;
                double vapAlt = 0.0;
                double vbpAlt = 0.0;
                double capAlt = 0.0;
                double cbpAlt = 0.0;


               if(!iMaster.getTotalUnit().isEmpty() && iMaster.getTotalUnit()!="0"){
               double whereAltUnit = Integer.parseInt(iMaster.getTotalUnit());

                 lpAlt = lp / whereAltUnit;
                 epAlt = ep / whereAltUnit;
                 tpAlt = tp / whereAltUnit;
                 vapAlt = vap / whereAltUnit;
                 vbpAlt = vbp / whereAltUnit;
                 capAlt = cap / whereAltUnit;
                 cbpAlt = cbp / whereAltUnit;

              }     
             
        
        iMaster.setBasicPrice(Double.valueOf(pp));
        iMaster.setLr(Double.valueOf(lr));
        iMaster.setEr(Double.valueOf(er));
        iMaster.setLa(Double.valueOf(la));
        iMaster.setLp(Double.valueOf(lp));
        iMaster.setTa(Double.valueOf(ta));
        iMaster.setTp(Double.valueOf(tp));
        iMaster.setEp(ep);
        iMaster.setVap(Double.valueOf(vap));
        iMaster.setVbp(Double.valueOf(vbp));
        iMaster.setCap(Double.valueOf(cap));
        iMaster.setCbp(Double.valueOf(cbp));
        
        iMaster.setLpAlt(Double.valueOf(lpAlt));
        iMaster.setEpAlt(Double.valueOf(epAlt));
        iMaster.setTpAlt(Double.valueOf(tpAlt));
        iMaster.setVapAlt(Double.valueOf(vapAlt));
        iMaster.setVbpAlt(Double.valueOf(vbpAlt));
        iMaster.setCapAlt(Double.valueOf(capAlt));
        iMaster.setCbpAtl(Double.valueOf(cbpAlt));
        
        iMaster.setVapCheckbox(Boolean.valueOf(false));
        iMaster.setVbpCheckbox(Boolean.valueOf(false));
        iMaster.setCapCheckbox(Boolean.valueOf(false));
        iMaster.setCbpCheckbox(Boolean.valueOf(false));
        
        iMaster.setCurrentStock(ccs);
        iMaster.setCp(Double.valueOf(cp));
        
        this.itemMasterService.saveItem(iMaster);
      }
     else if(salesCp<=cp)
     {
        iMaster.setCurrentStock(ccs); 
        iMaster.setCp(Double.valueOf(cp));  
        this.itemMasterService.saveItem(iMaster);
     }   
               sItem.setInvoiceNo(invoicNo);
               salesReturnService.saveSalesReturnItemItem(sItem);
           ItemMaster iMaster_after_update=itemMasterService.getItmeByItemNo(sItem.getItemCode());    
           ItemReport iReport=new ItemReport();
           iReport.setItemId(iMaster_after_update.getId());
           iReport.setItemName(iMaster_after_update.getItemName());
           iReport.setItemGroupId(iMaster_after_update.getItemGroup());
           iReport.setTransactionId(invoicNo);
           iReport.setTransactionName(BuyerName);
           iReport.setTransactionType("SalesReturn");
           iReport.setTransactionDate(s.getDate());
           if(sItem.getUnit()==iMaster.getAltUnit())
           {
           qty=sItem.getReturnQuantity()/(Double.parseDouble(iMaster.getTotalUnit()));      
           iReport.setInQuantity(qty);
           iReport.setInValue((qty*iMaster_after_update.getCp())); 
           }
           else
           {
           iReport.setInQuantity((double)sItem.getReturnQuantity());
           iReport.setInValue((sItem.getReturnQuantity()*iMaster_after_update.getCp()));   
           }    
           
           iReport.setClosingQuantity((iMaster_after_update.getCurrentStock()));
           iReport.setClosingValue((iMaster_after_update.getCurrentStock())*iMaster_after_update.getCp());             
           
           itemReportService.addItemReport(iReport);
               
               set.add(iMaster.getTr().toString());
           }
           System.out.println("getItemCode taxt "+sItem.getItemCode());
                    
        } 
       }  
       Entries e=new Entries();
       e.setEntryType("SR");
       e.setDate(s.getDate());
       e.setCrTotal(s.getInvoiceAmount());
       e.setDrTotal(s.getInvoiceAmount());
       e.setBillType("SalesReturn");
       e.setBillId(invoicNo);
       
       int entryId=entryService.saveEntry(e);
       
       Double currentBuyerBalance=openingBalanceOfLedger-s.getInvoiceAmount();
       Entryitems eItem=new Entryitems();
       eItem.setEntryId(entryId);
       eItem.setAmount(s.getInvoiceAmount());
       eItem.setLedgerId(buyerLedgerId);
       eItem.setType("C");
        if(currentBuyerBalance>0)
       eItem.setClosingAmtDr(currentBuyerBalance);
       else
       eItem.setClosingAmtCr( Math.abs(currentBuyerBalance));
       
       entryService.saveEntryItem(eItem);

       Iterator itrSet=set.iterator();
       
       
        double totalVatEntry=0.0;
        double totalCgstEntry=0.0;
        double totalIgstEntry=0.0;
        double totalMCAEntry=0.0; 
       
       while(itrSet.hasNext()){
        double tax=Double.parseDouble(itrSet.next().toString());
            
            double assessValueEntry=0;
        
           Iterator iter=list.iterator();
           while(iter.hasNext()){
              SalesReturnItem si=(SalesReturnItem)iter.next();
            if(si.getReturnQuantity()>0 && si.getReturnQuantity()!=null)
            { 
                
              if(tax==Double.parseDouble(si.getTax())){
                //  assessValueEntry=assessValueEntry+si.getAmount();
                  totalVatEntry=totalVatEntry+si.getVat();
                  totalCgstEntry=totalCgstEntry+si.getCgst();
                  totalIgstEntry=totalIgstEntry+si.getIgst();
                  totalMCAEntry=s.getActualMca();
                  
                  if(s.getCategory().equalsIgnoreCase("Consumers(B2C)")){
                      assessValueEntry=assessValueEntry+(si.getAmount()-si.getVat()-si.getCgst()-si.getIgst());
                    }
                     if(s.getCategory().equalsIgnoreCase("GST Dealers(B2B)")){
                         assessValueEntry=assessValueEntry+(si.getAmount());
                    }
                      
              }
            } 
           }
               if(tax==0 || tax==0.0)
                {
                   int salesLedgerId=20;
                   List<Object[]> salesLedgerIdcurrentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(salesLedgerId), CurrentDate);
                   double openingBalancesalesLedgerId=0;

                    if( salesLedgerIdcurrentBalance!=null && salesLedgerIdcurrentBalance.size()>0)
                   {
                         for (Object[] column : salesLedgerIdcurrentBalance) {
                                    openingBalancesalesLedgerId = (Double)column[0]- (Double)column[1];
                            }

                   }
                   else
                   {
                       List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(salesLedgerId));
                       for (Object[] column : OpeningBalance) {
                                    openingBalancesalesLedgerId = (Double)column[0]- (Double)column[1];
                            }
                   }  
                   
                    Entryitems eItem1=new Entryitems();           
                    Double currentsalesLedgerIdBalance=openingBalancesalesLedgerId+assessValueEntry;
                    eItem1.setEntryId(entryId);
                    eItem1.setAmount(assessValueEntry);
                    eItem1.setLedgerId(salesLedgerId);
                    eItem1.setType("D");
                    if(currentsalesLedgerIdBalance>=0)
                    eItem1.setClosingAmtDr(currentsalesLedgerIdBalance);
                    else
                    eItem1.setClosingAmtCr( Math.abs(currentsalesLedgerIdBalance));
                    entryService.saveEntryItem(eItem1);   
                }
                else
                {   
                int salesLedgerId=ledgerAccountService.getLedgerByLedgerName("Sales Return @"+tax+"% GST").iterator().next().getIdLedger();
                List<Object[]> salesLedgerIdcurrentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(salesLedgerId), CurrentDate);
                double openingBalancesalesLedgerId=0;

                if( salesLedgerIdcurrentBalance!=null && salesLedgerIdcurrentBalance.size()>0)
               {
                     for (Object[] column : salesLedgerIdcurrentBalance) {
                                openingBalancesalesLedgerId = (Double)column[0]- (Double)column[1];
                        }

               }
               else
               {
                   List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(salesLedgerId));
                   for (Object[] column : OpeningBalance) {
                                openingBalancesalesLedgerId = (Double)column[0]- (Double)column[1];
                        }
               }
                
                        Entryitems eItem1=new Entryitems();           
                   Double currentsalesLedgerIdBalance=openingBalancesalesLedgerId+assessValueEntry;
                   eItem1.setEntryId(entryId);
                   eItem1.setAmount(assessValueEntry);
                   eItem1.setLedgerId(salesLedgerId);
                   eItem1.setType("D");
                   if(currentsalesLedgerIdBalance>=0)
                   eItem1.setClosingAmtDr(currentsalesLedgerIdBalance);
                   else
                   eItem1.setClosingAmtCr( Math.abs(currentsalesLedgerIdBalance));
                   entryService.saveEntryItem(eItem1);  
                }
                

           
       
        
           
       }
       
       int salesVatLedgerId=12;
       int salesCgstLedgerId=11;
       int salesIgstLedgerId=14;
        int salesMCALedgerId=16;
      //  int salesCASHMEDLedgerId=7;
        
        
        List<Object[]> salesVatLedgerIdcurrentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(salesVatLedgerId), CurrentDate);
        List<Object[]> salesCgstLedgerIdcurrentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(salesCgstLedgerId), CurrentDate);
        List<Object[]> salesIgstLedgerIdcurrentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(salesIgstLedgerId), CurrentDate);
        List<Object[]> salesMCALedgerIdcurrentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(salesMCALedgerId), CurrentDate);
      //  List<Object[]> salesCASHMEDLedgerIdcurrentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(salesCASHMEDLedgerId), CurrentDate);
      
      
       double openingBalance_salesVatLedgerId=0;
       double openingBalance_salesCgstLedgerId=0;
       double openingBalance_salesIgstLedgerId=0;
     //   double openingBalance_salesMCALedgerId=0;
      
       
       
       if( salesVatLedgerIdcurrentBalance!=null && salesVatLedgerIdcurrentBalance.size()>0)
       {
             for (Object[] column : salesVatLedgerIdcurrentBalance) {
                        openingBalance_salesVatLedgerId = (Double)column[0]- (Double)column[1];
                }
           
       }
       else
       {
           List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(salesVatLedgerId));
           for (Object[] column : OpeningBalance) {
                        openingBalance_salesVatLedgerId = (Double)column[0]- (Double)column[1];
                }
       }
       if( salesCgstLedgerIdcurrentBalance!=null && salesCgstLedgerIdcurrentBalance.size()>0)
       {
             for (Object[] column : salesCgstLedgerIdcurrentBalance) {
                        openingBalance_salesCgstLedgerId = (Double)column[0]- (Double)column[1];
                }
           
       }
       else
       {
           List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(salesCgstLedgerId));
           for (Object[] column : OpeningBalance) {
                        openingBalance_salesCgstLedgerId = (Double)column[0]- (Double)column[1];
                }
       } 
       if( salesIgstLedgerIdcurrentBalance!=null && salesIgstLedgerIdcurrentBalance.size()>0)
       {
             for (Object[] column : salesIgstLedgerIdcurrentBalance) {
                        openingBalance_salesIgstLedgerId = (Double)column[0]- (Double)column[1];
                }
           
       }
       else
       {
           List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(salesIgstLedgerId));
           for (Object[] column : OpeningBalance) {
                        openingBalance_salesIgstLedgerId = (Double)column[0]- (Double)column[1];
                }
       } 
       
//       if( salesMCALedgerIdcurrentBalance!=null && salesMCALedgerIdcurrentBalance.size()>0)
//       {
//             for (Object[] column : salesMCALedgerIdcurrentBalance) {
//                        openingBalance_salesMCALedgerId = (Double)column[0]- (Double)column[1];
//                }
//           
//       }
//       else
//       {
//           List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(salesMCALedgerId));
//           for (Object[] column : OpeningBalance) {
//                        openingBalance_salesMCALedgerId = (Double)column[0]- (Double)column[1];
//                }
//       } 
       
       
        
       
       Entryitems eItem2=new Entryitems();  
           Double currentVatLedgerIdBalance=openingBalance_salesVatLedgerId+totalVatEntry;
           eItem2.setEntryId(entryId);
           eItem2.setAmount(s.getTotalVat());
           eItem2.setLedgerId(salesVatLedgerId);
           eItem2.setType("D");
           if(currentVatLedgerIdBalance>=0)
           eItem2.setClosingAmtDr(currentVatLedgerIdBalance);
           else
           eItem2.setClosingAmtCr( Math.abs(currentVatLedgerIdBalance));
         
           entryService.saveEntryItem(eItem2);

           Entryitems eItem3=new Entryitems();           
           Double currentCgstLedgerIdBalance=openingBalance_salesCgstLedgerId+totalCgstEntry;
           eItem3.setEntryId(entryId);
           eItem3.setAmount(s.getTotalCgst());
           eItem3.setLedgerId(salesCgstLedgerId);
           eItem3.setType("D");
           if(currentCgstLedgerIdBalance>=0)
           eItem3.setClosingAmtDr(currentCgstLedgerIdBalance);
           else
           eItem3.setClosingAmtCr( Math.abs(currentCgstLedgerIdBalance));
           entryService.saveEntryItem(eItem3);
         
           Entryitems eItem7=new Entryitems();           
           Double currentIgstLedgerIdBalance=openingBalance_salesIgstLedgerId+totalIgstEntry;
           eItem7.setEntryId(entryId);
           eItem7.setAmount(s.getTotalIgst());
           eItem7.setLedgerId(salesIgstLedgerId);
           eItem7.setType("D");
           if(currentIgstLedgerIdBalance>=0)
           eItem7.setClosingAmtDr(currentIgstLedgerIdBalance);
           else
           eItem7.setClosingAmtCr( Math.abs(currentIgstLedgerIdBalance));
           entryService.saveEntryItem(eItem7);
           

           if(s.getNameOfMediator()!=null && s.getNameOfMediator().isEmpty()){
           
//           Double currentCASHMEDLedgerIdBalance=openingBalance_salesCASHMEDLedgerId+totalMCAEntry;
//           Entryitems eItem5=new Entryitems();
//           eItem5.setEntryId(entryId);
//           eItem5.setAmount(totalMCAEntry);
//           eItem5.setLedgerId(salesCASHMEDLedgerId);
//           eItem5.setType("D");
//           if(currentCASHMEDLedgerIdBalance>=0)
//           eItem5.setClosingAmtDr(currentCASHMEDLedgerIdBalance);
//           else
//           eItem5.setClosingAmtCr( Math.abs(currentCASHMEDLedgerIdBalance));
//           entryService.saveEntryItem(eItem5);
           }
       
       if(s.getAddLess()!=0){      
        int sales_rounding_Ledger_id=3;
        List<Object[]> salesRoundingcurrentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(sales_rounding_Ledger_id), CurrentDate);
        double openingBalanceSalesRounding=0;
        if( salesRoundingcurrentBalance!=null && salesRoundingcurrentBalance.size()>0)
       {
             for (Object[] column : salesRoundingcurrentBalance) {
                        openingBalanceSalesRounding = (Double)column[0]- (Double)column[1];
                }

       }
       else
       {
           List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(sales_rounding_Ledger_id));
           for (Object[] column : OpeningBalance) {
                        openingBalanceSalesRounding = (Double)column[0]- (Double)column[1];
                }
       }  
        
           Double currentSalesRounding=openingBalanceSalesRounding+s.getAddLess();
           Entryitems eItem4=new Entryitems();
           eItem4.setEntryId(entryId);
           eItem4.setAmount(s.getAddLess());
           eItem4.setLedgerId(sales_rounding_Ledger_id);
           eItem4.setType("D");
           if(currentSalesRounding>=0)
           eItem4.setClosingAmtDr(currentSalesRounding);
           else
           eItem4.setClosingAmtCr(Math.abs(currentSalesRounding));
         
           entryService.saveEntryItem(eItem4);
       }  
       
//        // For Calculate Every Profit and loss account using Pre defined ledger accounts 
//        int pl_Ledger_id=2;
//        List<Object[]> salesRoundingcurrentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(pl_Ledger_id), CurrentDate);
//        double openingBalancePL=0;
//        if( salesRoundingcurrentBalance!=null && salesRoundingcurrentBalance.size()>0)
//       {
//             for (Object[] column : salesRoundingcurrentBalance) {
//                        openingBalancePL = (Double)column[0]- (Double)column[1];
//                }
//
//       }
//       else
//       {
//           List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(pl_Ledger_id));
//           for (Object[] column : OpeningBalance) {
//                        openingBalancePL = (Double)column[0]- (Double)column[1];
//                }
//       } 
//       
//      
//      double plamount= as.calculateNetProfit_Every_transcation(CurrentDate, CurrentDate);
//       System.out.println(" plamount "+plamount);
//       Entryitems eItem7=new Entryitems();
//           eItem7.setEntryId(entryId);
//           eItem7.setAmount(plamount);
//           eItem7.setLedgerId(pl_Ledger_id);
//           eItem7.setType("D");
//           if(plamount>=0)
//           eItem7.setClosingAmtDr(plamount);
//           else
//           eItem7.setClosingAmtCr(Math.abs(plamount));
//         
//           entryService.saveEntryItem(eItem7);
       
        return new ModelAndView("redirect:SalesReturnGrid.html");
    }
}

@RequestMapping(value={"editSalesReturn"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
public ModelAndView editSales(@RequestParam("editid") String id, RedirectAttributes redirect)
{
    
    if(id!=null && id.length()>0){
       SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
       Date date = new Date();        
       String CurrentDate= dmyFormat.format(date); 
      SalesReturn sale=salesReturnService.getSalesReturnById((id)); 
      List list=salesReturnService.getSalesReturnItemBySalesId((id));
      
      SalesReturnForm sForm=new SalesReturnForm();
      sForm.setSale(sale);
      sForm.setSales(list);
      
      ModelAndView model = new ModelAndView("SalesReturn");
      SalesReturn sale1=salesReturnService.getSalesReturnById((id)); 
       sale.setCategory(sale1.getCategory());
         sale.setMode(sale1.getMode());
         
      // for getting cuurent buyer balance
      double openingBalanceOfBuyerLedger=0.0;
                int  buyerLedgerId=ledgerAccountService.getLederByReferenceId(String.valueOf(sale1.getBuyerId()), "Buyer").iterator().next().getIdLedger(); 
                List<Object[]> currentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(buyerLedgerId), CurrentDate);
                if( currentBalance!=null && currentBalance.size()>0)
                {
                      for (Object[] column : currentBalance) {
                                 openingBalanceOfBuyerLedger = (Double)column[0]- (Double)column[1];
                         }

                }
                else
                {
                    List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(buyerLedgerId));
                    for (Object[] column : OpeningBalance) {
                                 openingBalanceOfBuyerLedger = (Double)column[0]- (Double)column[1];
                         }
                }               
                if(openingBalanceOfBuyerLedger>0) 
                {
                     model.addObject("currentBuyerBalance",Math.abs(openingBalanceOfBuyerLedger));
                     model.addObject("TypeBuyer","DR"); 
                }
                else
                {
                   model.addObject("currentBuyerBalance",Math.abs(openingBalanceOfBuyerLedger));
                   model.addObject("TypeBuyer","CR"); 
                } 
        if(!sale.getMediatorId().isEmpty())   
        {
         Double MediatorVoucherBalance_Consumer=0.0;
         int  mediatorLedgerId=ledgerAccountService.getLederByReferenceId(String.valueOf(sale1.getMediatorId()), "Buyer").iterator().next().getIdLedger(); 
        List<Object[]> salesCASHMEDLedgerIdcurrentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(mediatorLedgerId), CurrentDate);
         
            if( salesCASHMEDLedgerIdcurrentBalance!=null && salesCASHMEDLedgerIdcurrentBalance.size()>0)
           {
                 for (Object[] column : salesCASHMEDLedgerIdcurrentBalance) {
                            MediatorVoucherBalance_Consumer = (Double)column[0]- (Double)column[1];
                    }

           }
           else
           {
               List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(mediatorLedgerId));
               for (Object[] column : OpeningBalance) {             
                            MediatorVoucherBalance_Consumer = (Double)column[0]- (Double)column[1];
                    }
           }        
        
                if(MediatorVoucherBalance_Consumer>0) 
                {
                    model.addObject("currentMediatorBalance",Math.abs(MediatorVoucherBalance_Consumer));
                     model.addObject("TypeM","DR"); 
                }
                else
                {
                    model.addObject("currentMediatorBalance",Math.abs(MediatorVoucherBalance_Consumer));
                     model.addObject("TypeM","DR"); 
                }
        }
         model.addObject("CategoryAuto", sale1.getCategory());
      model.addObject("salesReturnForm", sForm);
      model.addObject("returnId", id);
      return model;
    }
   else{
        ModelAndView model = new ModelAndView("redirect:SalesReturnGrid.html");
       return model;
   }

}
  
@RequestMapping(value={"SalesReturnGrid"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
public ModelAndView SalesReturnGrid()
{
    SalesReturnForm sForm=new SalesReturnForm();
ModelAndView model = new ModelAndView("SalesReturnGrid");
model.addObject("salesinfo",salesReturnService.listSalesReturn());
    model.addObject("salesReturnForm", sForm);
return model;
}

@RequestMapping(value={"SalesReturnReports"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
public ModelAndView SalesReturnReports()
{
    SalesReturnForm sForm=new SalesReturnForm();
ModelAndView model = new ModelAndView("SalesReturnReports");
model.addObject("SalesReturnListinfo",salesReturnService.listSalesReturn());
    model.addObject("salesReturnForm", sForm);
return model;
}
  
 
    
@RequestMapping(value={"deleteSalesReturn"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
public ModelAndView deleteSalesReturn(@RequestParam("id") String id, RedirectAttributes redirect)
{
    ModelAndView model = new ModelAndView("redirect:SalesReturnGrid.html");
   if(id!=null && id.length()>0){
       salesReturnService.deleteSalesReturn(id);
       return model;
   }
   else{
       return model;
   }

}

@RequestMapping(value={"ConvertToReturn"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
public ModelAndView convertToSales(@RequestParam("id") String id, RedirectAttributes redirect)
{
    
    if(id!=null && id.length()>0){
      Sales sale=salesService.getSalesById(id);
      List list=salesService.getSalesItemBySalesId(id);
      
      SalesReturn s=new SalesReturn();
      s.setInvoiceNo(sale.getInvoiceNo());
      s.setDate(sale.getDate());
      s.setCategory(sale.getCategory());
      s.setCashBuyer(sale.getCashBuyer());
      s.setTinNo(sale.getTinNo());
      s.setMode(sale.getMode());
      s.setNameOfBuyer(sale.getNameOfBuyer());
      s.setBuyerId(String.valueOf(sale.getBuyerId()));
      s.setBuyerBalance(sale.getBuyerBalance());
      s.setWorkSite(sale.getWorkSite());
      s.setNameOfMediator(sale.getNameOfMediator());
      s.setMediatorId(sale.getMediatorId());
      s.setMediatorBalance(sale.getMediatorBalance());
      s.setVaa(sale.getVaa());
      s.setActualMca(sale.getActualMca());
      s.setMca(sale.getMca());
      s.setAssessValue(sale.getAssessValue());
      s.setTotalVat(sale.getTotalVat());
      s.setTotalCgst(sale.getTotalCgst());
      s.setTotalIgst(sale.getTotalIgst());
      s.setAddLess(sale.getAddLess());
      s.setInvoiceAmount(sale.getInvoiceAmount());
      s.setNarration(sale.getNarration());
        System.out.println("getMediatorType()"+sale.getMediatorType());
      s.setMediatorType(sale.getMediatorType());
      s.setBuyerType(sale.getBuyerType());
      s.setPos(sale.getPos());
      double currentyQty=0.0;
      
      List<SalesReturnItem> saleList=new ArrayList<SalesReturnItem>();
      Iterator itr=list.iterator();
      while(itr.hasNext()){
          SalesItem seItem=(SalesItem)itr.next();
          
          SalesReturnItem sItem=new SalesReturnItem();
          sItem.setItemCode(seItem.getItemCode());
          String itemcode=seItem.getItemCode();
          sItem.setItemName(seItem.getItemName());
          System.out.println("sItem.setItemName:"+seItem.getItemCode());
          if(seItem.getQuantity()>0){    
              
                double CurrentQty=salesReturnService.getRSalesReturnId(itemcode,sale.getInvoiceNo());
                
                sItem.setQuantity(seItem.getQuantity()-CurrentQty);  
                
                 currentyQty=seItem.getQuantity()-CurrentQty;
              
            }else{
                sItem.setQuantity(seItem.getQuantity());
            }
          
         
          
          sItem.setUnit(seItem.getUnit());
          sItem.setMargin(seItem.getMargin());
          sItem.setRate(seItem.getRate());
          sItem.setEpepV(seItem.getCpcpV());
          sItem.setDiscount(seItem.getDiscount());
          sItem.setAmount(seItem.getAmount());
          sItem.setIgst(seItem.getIgst());
          sItem.setCgst(seItem.getCgst());
          sItem.setVat(seItem.getVat());
          sItem.setTax(seItem.getTax());
          sItem.setCpcpvAmopunt(seItem.getCpcpvAmount());
          sItem.setMcmcaAmount(seItem.getMcmcaAmount());
          sItem.setMcmca(seItem.getMcmcaAmount());
          sItem.setCpcpvAmount(String.valueOf(seItem.getCpcpV()));
          sItem.setTaxIgst(seItem.getTaxIgst());
          sItem.setTaxCgst(seItem.getTaxCgst());
          sItem.setTaxSgst(seItem.getTaxSgst());
          saleList.add(sItem);
      }
      
      SalesReturnForm sForm=new SalesReturnForm();
      sForm.setSale(s);
      sForm.setSales(saleList);
      
      ModelAndView model = new ModelAndView("SalesReturn");
      Sales sale1=salesService.getSalesById(id);
       s.setCategory(sale1.getCategory());
         s.setMode(sale1.getMode());
         model.addObject("CategoryAuto", sale1.getCategory());
      model.addObject("salesReturnForm", sForm);
      model.addObject("taxStructure", tServ.getTaxId(1));
      model.addObject("CurrentQty", currentyQty);
      return model;
    }
   else{
        ModelAndView model = new ModelAndView("redirect:SalesGrid.html");
        return model;
   }
    

}


@RequestMapping(value={"SalesReturnInvoice"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
public ModelAndView salesInvoice(@RequestParam("id") String id){
    
    if(id!=null && id.length()>0){
        
        List list=companyservice.listcompany();
        CompanyInformation company=(CompanyInformation)list.iterator().next();
        
      SalesReturn sale=salesReturnService.getSalesReturnById((id));
      List slaesItemList=salesReturnService.getSalesReturnItemBySalesId((id));
      
      ModelAndView model=new ModelAndView("SalesInvoice");
      model.addObject("company", company);
      model.addObject("sales", sale);
      model.addObject("buyer", bs.getBuyerbyId(Integer.parseInt(sale.getBuyerId())));
      model.addObject("salesItem", slaesItemList);
        company=companyservice.getCompanyById(1);
      model.addObject("companyFrom",company);
      return model;
    }
   else{
       ModelAndView model = new ModelAndView("redirect:SalesReturn.html");
       return model;
   }
    
}
@RequestMapping(value={"SalesReturninvoice"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
public ModelAndView salesInvoices(@RequestParam("id") String id){
    
    if(id!=null && id.length()>0){
        
        List list=companyservice.listcompany();
        CompanyInformation company=(CompanyInformation)list.iterator().next();
        
      SalesReturn sale=salesReturnService.getSalesReturnById((id));
      List slaesItemList=salesReturnService.getSalesReturnItemBySalesId((id));
      
      
      ModelAndView model=new ModelAndView("SalesInvoice");
      model.addObject("company", company);
      model.addObject("sales", sale);
      model.addObject("buyer", bs.getBuyerbyId(Integer.parseInt(sale.getBuyerId())));
      model.addObject("salesItem", slaesItemList);
      model.addObject("withVat", "withVat");
        company=companyservice.getCompanyById(1);
      model.addObject("companyFrom",company);
      return model;
    }
   else{
       ModelAndView model = new ModelAndView("redirect:SalesReturn.html");
       return model;
   }
    
}

@RequestMapping(value={"SalesReturnReports"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView GetPurchaseReports( HttpServletRequest req)throws ParseException
  {   
    String gsQuery = "";

    String gsFromDate = req.getParameter("fromdate");
    SimpleDateFormat sdfIn = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdfOut = new SimpleDateFormat("yyyy-MM-dd");
    Date date = sdfIn.parse(gsFromDate);
    System.out.println(sdfOut.format(date));
    String gsToDate = req.getParameter("todate");
    SimpleDateFormat sdfInn = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdfOutt = new SimpleDateFormat("yyyy-MM-dd");
    Date datee = sdfInn.parse(gsToDate);
    System.out.println(sdfOutt.format(datee));

    
    gsQuery = "From SalesReturn keyWhere ";
    String gsWhere = "";
   
     if (!gsFromDate.isEmpty())
      {
        if (!gsToDate.isEmpty())
        {
          if (gsWhere != "") {
            gsWhere = gsWhere + " and DATE_FORMAT(date,  '%y/%m/%d' ) \n" + "BETWEEN (  '" + gsFromDate + "') \n" + "AND (  '" + gsToDate + "')  ";
          } else {
            gsWhere = gsWhere + "WHERE   date between ('" + sdfOutt.format(date) + "') and ('" +sdfOutt.format(datee) + "') ";
          }
        } 
      }

     
   
        gsQuery = gsQuery.replace("keyWhere", gsWhere);
        System.out.println("gsquery check it " + gsQuery);
    
      ModelAndView model = new ModelAndView("SalesReturnReports");
    model.addObject("SalesReturnListFullinfo", salesReturnService.SalesReturnReports(gsQuery));
    return model;
  }
  @RequestMapping(value={"SaleReturnReportForm"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView SaleReturnReportForm()
  {
    String fromDate = companyservice.getCompanyById(1).getFinancialYearFrom();
      
    String financialdateMonth=fromDate.substring(0, 5);
      
    String financialmonth=financialdateMonth.substring(3, 5);
    int intFinancialMonth=Integer.parseInt(financialmonth);
       
       
       
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy ");
    Date currentdate = new Date();
    String dt=dateFormat.format(currentdate);
    
    String currentMonth=dt.substring(3,5);
    int intcurrentMonth=Integer.parseInt(currentMonth);
    
    String currentYear=dt.substring(6,10);
    int intCurrentYear=Integer.parseInt(currentYear);
    
      
     int fromYear=0;
       if(intFinancialMonth <= intcurrentMonth)
       {
       fromYear = intCurrentYear;
         
       }
       else
       {
         fromYear = intCurrentYear-1;
          
       }
       
       String finalDate=financialdateMonth+"/" + String.valueOf(fromYear);
    ModelAndView model = new ModelAndView("SaleReturnReportForm");
    model.addObject("curDate", dt);
    model.addObject("finalDate", finalDate);
    return model;
  }
  @RequestMapping(value={"GetSaleReturnReport"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView GetSaleReturnReport(HttpServletRequest req,HttpServletResponse response)throws ParseException, JRException, IOException
  {
    String gsQuery = "";
    
    HttpSession session = null;
    Session session1 = this.sessionFactory.openSession();
    SessionImpl sessionImpl = (SessionImpl)session1;
    Connection conn = sessionImpl.connection();
    
    String gsFromDate = req.getParameter("fromdate");
    

    
    String gsToDate = req.getParameter("todate");
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date convertedFromDate = sdf.parse(gsFromDate);
    String fdate=dmyFormat.format(convertedFromDate );    
    Date convertedEndDate = sdf.parse(gsToDate);
    String edate=dmyFormat.format(convertedEndDate );
    
    String viewType=req.getParameter("viewType");
    
   
    String reportfile = req.getServletContext().getRealPath("//Reports//Sale_Return_Register.jrxml");

    CompanyInformation u = companyservice.getCompanyById(1);
      System.out.println("Company Name:"+u.getCompanyName());
    String cname = u.getCompanyName();
    String address = u.getAddress();
    JasperReport jasperReport = JasperCompileManager.compileReport(reportfile);
    Map parametersMap = new HashMap();
    parametersMap.put("Company Name", cname);
    parametersMap.put("Company Address", address);
    parametersMap.put("FromDate", fdate);
    parametersMap.put("Todate", edate);
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametersMap, conn);
    if(viewType.equalsIgnoreCase("PDF")){      
        JRPdfExporter pdfExp = new JRPdfExporter();
        pdfExp.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        pdfExp.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
        pdfExp.exportReport();
    }else if(viewType.equalsIgnoreCase("HTML")){
        JRHtmlExporter htmlExp = new JRHtmlExporter();
        htmlExp.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
        htmlExp.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        htmlExp.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
        htmlExp.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,	Boolean.FALSE);
        htmlExp.exportReport();
    }else{
        response.setContentType("application/x-xlsx");
        response.setHeader("Content-Disposition", "inline;filename=SaleReturnReport.xlsx");
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
        exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,true);
        exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,false);
        exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);       
        exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
        exporter.exportReport();

    }
    return null;
  }
  @ResponseBody
   @RequestMapping(value={"GetDatatableSaleReturn"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   
    public DatatableObject getDatatableSaleReturn( HttpServletRequest req)
    {
      DatatableObject db =new DatatableObject();
      
           String[] cols = {  "invoice_no", "date", "name_of_buyer","mode","category","buyer_balance","invoice_amount" };
    String table = "sales_return";
    

    int amount = 10;
    int start = 0;
    int echo = 0;
    int col = 0;
    
     
    String engine = "";
    String browser = "";
    String platform = "";
    String version = "";
    String grade = "";
 
    String dir = "asc";
    String sStart = req.getParameter("start");
    String sAmount = req.getParameter("length");
    String sEcho = req.getParameter("sEcho");
    String sCol = req.getParameter("order[0][column]");
    String sdir = req.getParameter("order[0][dir]");
     
    System.out.println("sStart" +sStart);
    System.out.println("sAmount" +sAmount);
    
  
     
    if (sStart != null) {
        start = Integer.parseInt(sStart);
        if (start < 0)
            start = 0;
    }
    if (sAmount != null) {
        amount = Integer.parseInt(sAmount);
        if (amount < 10 || amount > 100)
            amount = 10;
    }
    if (sEcho != null) {
        echo = Integer.parseInt(sEcho);
    }
    if (sCol != null) {
        col = Integer.parseInt(sCol);
        if (col < 0 || col > 6)
            col = 0;
    }
    if (sdir != null) {
        if (!sdir.equals("asc"))
            dir = "desc";
    }
    String colName = cols[col];
    int total = 0;
   
    try {
        String sql = "select  sr.id,sr.date ,bm.buyer_name,bm.cell_no ,sr.mode ,sr.category,sr.buyer_balance ,sr.invoice_amount,sr.assess_value   from SALES_RETURN as sr  inner join BUYER_MASTER as bm  ON sr.buyer_id = bm.id_buyer";
        System.out.println("abi" + sql);
        List<Object[]> list = bs.GetDatatableBuyerObject(sql);
        total=list.size();
        System.out.println("the list size buyer" + total);
        
    }catch(Exception e){
         
    }
    int totalAfterFilter = total;
    //result.put("sEcho",echo);
 
    try {
        String searchSQL = "";
        String sql = "select  sr.id,sr.date ,bm.buyer_name,bm.cell_no ,sr.mode ,sr.category,sr.buyer_balance ,sr.invoice_amount,sr.assess_value   from SALES_RETURN as sr  inner join BUYER_MASTER as bm  ON sr.buyer_id = bm.id_buyer";
        String searchTerm =req.getParameter("search[value]");
        System.out.println(" Valahsdfh "+searchTerm);
         String globeSearch =  " where (sr.id like '"+searchTerm+"%')";
         System.out.println(" globeSearch "+globeSearch);
    
        if(searchTerm!=""){
            searchSQL=globeSearch;
        }
        sql += searchSQL;
        sql += " order by " + colName + " " + dir;
        sql += " limit " + start + ", " + amount;
        System.out.println(" SQL abi "+sql);
        // For aData
         List<Object[]> list2 = bs.GetDatatableBuyerObject(sql);
         // For Filter Count 
        String sql2 = "select  sr.id,sr.date ,bm.buyer_name,bm.cell_no ,sr.mode ,sr.category,sr.buyer_balance ,sr.invoice_amount,sr.assess_value   from SALES_RETURN as sr  inner join BUYER_MASTER as bm  ON sr.buyer_id = bm.id_buyer";
       if (searchTerm != "") {
            sql2 += searchSQL;
          List<Object[]> count = bs.GetDatatableBuyerCount(sql2);
          totalAfterFilter=count.size();
        }
  
   db.setiTotalRecords(total);
   db.setiTotalDisplayRecords(totalAfterFilter);
   db.setAaData(list2);
    } catch (Exception e) {
 
    }
      return db;
	}  
 

    
}
