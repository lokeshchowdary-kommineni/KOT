
package com.accounting.controller;

import com.accounting.bean.CompanyInformation;
import com.accounting.bean.DatatableObject;
import com.accounting.bean.Entries;
import com.accounting.bean.Entryitems;
import com.accounting.bean.ItemMaster;
import com.accounting.bean.ItemReport;
import com.accounting.bean.StockFjournalItem;
import com.accounting.bean.StockJournal;
import com.accounting.bean.StockJournalForm;
import com.accounting.bean.StockJournalItem;
import com.accounting.bean.UnitMaster;
import com.accounting.service.AccountDBOService;
import com.accounting.service.CompanyService;
import com.accounting.service.EntryService;
import com.accounting.service.ItemMasterService;
import com.accounting.service.ItemReport_service;
import com.accounting.service.LedgerAccount_Service;
import com.accounting.service.LedgerBalanceService;
import com.accounting.service.StockJournalService;
import com.accounting.service.TaxStructure_Service;
import com.accounting.service.Unit_service;
import com.accounting.validator.StockJournallFormValidator;
import java.io.PrintStream;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RestController
public class StockJournalTableController {
    
    @Autowired
  private Unit_service unitService;
    
    @Autowired
    private StockJournalService stockJournalService;
    
     @Autowired
    private TaxStructure_Service tServ;
     
     @Autowired
    private ItemMasterService itemMasterService;
     
       @Autowired
    private CompanyService companyservice;
       
       @Autowired
    private EntryService entryService; 
       
       @Autowired
  private ItemReport_service itemReportService;
       
       @Autowired
    private LedgerAccount_Service ledgerAccountService;
     
      @Autowired
    private StockJournallFormValidator stockJournallFormValidator;
      @Autowired
    private LedgerBalanceService lbs;
             @Autowired
    private AccountDBOService as;
 @InitBinder("stockjournalFrom")
    protected void initBinder(WebDataBinder binder) {

                          binder.setValidator(stockJournallFormValidator);
          
    }
    
    @RequestMapping(value={"StockJournal"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView StockJournalinformation()
  {
   StockJournal s=new StockJournal();
    StockJournalForm sForm=new StockJournalForm();
    StockJournalItem sItem=new StockJournalItem();
    StockFjournalItem sFItem=new StockFjournalItem();
    List<StockJournalItem> list=new ArrayList<StockJournalItem>();
    list.add(sItem);  
    
    List<StockFjournalItem> listF=new ArrayList<StockFjournalItem>();
    listF.add(sFItem); 
    
    sForm.setStock(s);
    sForm.setStocks(list);
    sForm.setStocksF(listF);
    ModelAndView model = new ModelAndView("StockJournal");
    model.addObject("stockjournalFrom", sForm);
    return model;
}

@RequestMapping(value={"SaveCStockJournal"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
public ModelAndView saveItem(@ModelAttribute("stockjournalFrom")  StockJournalForm stockjournalFrom, BindingResult result,RedirectAttributes redirect)
{   
     SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
     Date date = new Date();        
     String CurrentDate= dmyFormat.format(date);
    
    if (result.hasErrors()) {
        return new ModelAndView("StockJournal");
        
    }
    else{
     
    // code to ignore validator(itemCode) while removing addRow --- Starts(Abi-20.07.2017)
    
    StockJournalItem stockItem=new StockJournalItem();
    List<StockJournalItem> listStockJournal= stockjournalFrom.getStocks();
    List<StockJournalItem> listNew =new ArrayList<StockJournalItem>(); 
    Iterator<StockJournalItem> i = listStockJournal.iterator();
    while (i.hasNext())
    {
       stockItem = i.next();
        // this condition exclude deleted empty row of data
        if( stockItem.getItemCode()==null || stockItem.getItemCode().isEmpty() )
        {
         
        }
        else
        {
          // here add existing data to new arraylist  
          listNew.add(stockItem);
        }  
    }
 
    stockjournalFrom.setStocks(listNew);   
     // ---- End(Abi 20.07.2017)
       StockJournal s=stockjournalFrom.getStock();
       
       int stockJournalId=stockJournalService.saveStockJournal(s);      
       List list=stockjournalFrom.getStocks();
       Iterator itr=list.iterator();
       while(itr.hasNext()){         
           StockJournalItem sItem=(StockJournalItem)itr.next();
           sItem.setStockJournalId(stockJournalId);             
                sItem.setStockjournalType("C");   
                ItemMaster iMaster = (ItemMaster) this.itemMasterService.getItmeByItemNo(sItem.getItemCode());
               // System.out.println(" sItem.getQty() "+sItem.getQty()+" / "+iMaster.getCurrentStock());
      if(sItem.getQty()<=(iMaster.getCurrentStock()))
      { 
                 if ((sItem.getId() == null) || (sItem.getId().equals("")))
      {
                
                String query="UPDATE `item_master` SET current_stock=current_stock-"+sItem.getQty()+" WHERE item_code='"+sItem.getItemCode()+"'";
               itemMasterService.updateItemMaster(query);
      }
                
      }
      else
      {
          stockJournalService.deleteStockJournal(String.valueOf(stockJournalId));         
        StockFjournalItem sFItem=new StockFjournalItem();
        List<StockFjournalItem> listF=new ArrayList<StockFjournalItem>();
        listF.add(sFItem);   
           stockjournalFrom.getStock().setStockJournalId(null);
           stockjournalFrom.setStocksF(listF);
           ModelAndView model =new  ModelAndView("StockJournal");
           model.addObject("stockjournalFrom", stockjournalFrom);
           model.addObject("Message", "    "+sItem.getNameOfTheItem()+" is out of stock please decrese qty");
           return model;
      }    
     
      stockJournalService.saveStockJournalItem(sItem);
      ItemMaster iMaster_after_update=itemMasterService.getItmeByItemNo(sItem.getItemCode());
      ItemReport iReport = new ItemReport();
           System.out.println("iMaster_after_update.getId(:"+iMaster_after_update.getId());
      iReport.setItemId(iMaster_after_update.getId());
      iReport.setItemName(iMaster_after_update.getItemName());
      iReport.setItemGroupId(iMaster_after_update.getItemGroup());
      iReport.setTransactionDate(s.getDate());
      iReport.setTransactionId(String.valueOf(stockJournalId));
      iReport.setTransactionType("Stock Journals");
      iReport.setOutQuantity(sItem.getQty());
      iReport.setOutValue(sItem.getQty() * iMaster_after_update.getCp());
      iReport.setClosingQuantity((iMaster_after_update.getCurrentStock()));
      iReport.setClosingValue(iMaster_after_update.getCurrentStock() * iMaster_after_update.getCp());
      
      this.itemReportService.addItemReport(iReport);
      
      
       }
      
        
       double totalProducationCost=0.0;
       int salesDLedgerId=15;
       List<Object[]> salesDLedgerIdcurrentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(salesDLedgerId), CurrentDate);
       
       double openingBalanceDLedgerId=0;
       
       if( salesDLedgerIdcurrentBalance!=null && salesDLedgerIdcurrentBalance.size()>0)
       {
             for (Object[] column : salesDLedgerIdcurrentBalance) {
                        openingBalanceDLedgerId = (Double)column[0]- (Double)column[1];
                }
           
       }
       else
       {
           List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(salesDLedgerId));
           for (Object[] column : OpeningBalance) {
                        openingBalanceDLedgerId = (Double)column[0]- (Double)column[1];
                }
       }
       
       
       int salesCashLedgerId=1;
       List<Object[]> salesCashLedgerIdcurrentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(salesCashLedgerId), CurrentDate);
       
       double openingBalanceCashLedgerId=0;
       
       if( salesCashLedgerIdcurrentBalance!=null && salesCashLedgerIdcurrentBalance.size()>0)
       {
             for (Object[] column : salesCashLedgerIdcurrentBalance) {
                        openingBalanceCashLedgerId = (Double)column[0]- (Double)column[1];
                }
           
       }
       else
       {
           List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(salesCashLedgerId));
           for (Object[] column : OpeningBalance) {
                        openingBalanceCashLedgerId = (Double)column[0]- (Double)column[1];
                }
       }
       
       totalProducationCost=s.getValuAdditiion();
       
       Entries e=new Entries();
       e.setEntryType("SC");
       e.setDate(s.getDate());
       e.setCrTotal(s.getValueOfFinishGood());
       e.setDrTotal(s.getValueOfFinishGood());
       e.setBillType("Stock");
       e.setBillId(String.valueOf(s.getStockJournalId()));
       
       int entryId=entryService.saveEntry(e);     
       
       
           Double salesDIRECTLedgerIdcurrentBalance=openingBalanceDLedgerId+totalProducationCost;
           Entryitems eItemV=new Entryitems();
           eItemV.setEntryId(entryId);
           eItemV.setAmount(s.getValuAdditiion());
           eItemV.setLedgerId(salesDLedgerId);
           eItemV.setType("D");
           if(salesDIRECTLedgerIdcurrentBalance>=0)
           eItemV.setClosingAmtDr(salesDIRECTLedgerIdcurrentBalance);
           else
           eItemV.setClosingAmtCr( Math.abs(salesDIRECTLedgerIdcurrentBalance));
         
           entryService.saveEntryItem(eItemV);
           
           Double salesCashLedgercurrentBalance=openingBalanceCashLedgerId-totalProducationCost;
           Entryitems eItemVC=new Entryitems();
           eItemVC.setEntryId(entryId);
           eItemVC.setAmount(s.getValuAdditiion());
           eItemVC.setLedgerId(salesCashLedgerId);
           eItemVC.setType("C");
           if(salesCashLedgercurrentBalance>=0)
           eItemVC.setClosingAmtDr(salesCashLedgercurrentBalance);
           else
           eItemVC.setClosingAmtCr( Math.abs(salesCashLedgercurrentBalance));
         
           entryService.saveEntryItem(eItemVC);
         
       

       return new ModelAndView("redirect:StockJournalGrid.html");
    }
}


@RequestMapping(value={"SaveFStockJournal"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
public ModelAndView SaveFStockJournal(@ModelAttribute("stockjournalFrom") StockJournalForm stockjournalFrom, BindingResult result,RedirectAttributes redirect)
{
    
    System.out.println("SaveFStockJournal:");
     SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
     Date date = new Date();        
     String CurrentDate= dmyFormat.format(date);
    if (result.hasErrors()) {
        
        System.out.println("");
        return new ModelAndView("StockJournal");
        
    }
    else{
    
    // code to ignore validator(itemCode) while removing addRow --- Starts(Abi-20.07.2017)
    
    StockFjournalItem stockFinishItem=new StockFjournalItem();
        
    List<StockFjournalItem> listStockFinish= stockjournalFrom.getStocksF();
    List<StockFjournalItem> listNew =new ArrayList<StockFjournalItem>();
    Iterator<StockFjournalItem> i = listStockFinish.iterator();
    while (i.hasNext())
    {
        stockFinishItem = i.next();
        // this condition exclude deleted empty row of data
        if(stockFinishItem.getItemCode()==null || stockFinishItem.getItemCode().isEmpty() )
        {
           
        }
        else
        {
          // here add existing data to new arraylist  
          listNew.add(stockFinishItem);
        }    

    }
    stockjournalFrom.setStocksF(listNew);
     // ---- End(Abi 20.07.2017)
    
       StockJournal s=stockjournalFrom.getStock();       
       List list=stockjournalFrom.getStocksF();     
       Iterator itr=list.iterator();
       while(itr.hasNext()){         
           StockFjournalItem sItem=(StockFjournalItem)itr.next();
            sItem.setStockJournalId(s.getStockJournalId());
                sItem.setStockjournalType("F");            
               
               ItemMaster iMaster=itemMasterService.getItmeByItemNo(sItem.getItemCode());
           double ccs=0;
           if(iMaster.getCurrentStock()!=null){
             ccs=(iMaster.getCurrentStock());
           }
           double psv=ccs*iMaster.getCp();
           double isv=sItem.getQty()*sItem.getEp();
           ccs=(int) (ccs+sItem.getQty());
           
           System.out.print("ccs:"+ccs);
             System.out.print("sItem.getQty() :"+sItem.getQty());
           double cp=(psv+isv)/ccs;
           
            System.out.print("New cp:"+cp);
           // update current stock 
            String query="UPDATE `item_master` SET current_stock=current_stock+"+sItem.getQty()+" WHERE item_code='"+sItem.getItemCode()+"'";
            itemMasterService.updateItemMaster(query);
          //  if(iMaster.getCp()<cp)
            {
               double pp=cp;
               double lr=0;
               double er=0;
               double tr=iMaster.getTr();
               double la=(lr/100)*pp;
               double lp=(pp-la);
               double ea=(er/100)*lp;
               double ep=(lp+ea);
               double ta=(tr/100)*ep;
               double tp=(ep+ta);
               
               System.out.print("Stock value: "+ep);
               System.out.print("Stock value: "+iMaster.getVa());
               
               double vap_noRound=(ep*100)/(100-iMaster.getVa());
               String vapFixed=(new DecimalFormat("##.##").format(vap_noRound));
               Double vap=as.roundUpService(Double.parseDouble(vapFixed));
               
               double vbp_noRound=(ep*100)/(100-iMaster.getVb());
               String vbpFixed=(new DecimalFormat("##.##").format(vbp_noRound));
               Double vbp=as.roundUpService(Double.parseDouble(vbpFixed));
        
               double cap_noRound=(ep*100+ep*tr)/(100-iMaster.getCa());
               String capFixed=(new DecimalFormat("##.##").format(cap_noRound));
               Double cap=as.roundUpService(Double.parseDouble(capFixed));
               
               double cbp_noRound=(ep*100+ep*tr)/(100-iMaster.getCb());
               String cbpFixed=(new DecimalFormat("##.##").format(cbp_noRound));
               Double cbp=as.roundUpService(Double.parseDouble(cbpFixed));
               
               
               //System.out.print("Stock value: "+vap); 
               System.out.print("Stock value: "+vbp);
               System.out.print("Stock value: "+cap);
               System.out.print("Stock value: "+cbp);
               
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
             
               
               iMaster.setBasicPrice(pp);
               iMaster.setLr(lr);
               iMaster.setEr(er);
               iMaster.setLa(la);
               iMaster.setLp(lp);
               iMaster.setTa(ta);
               iMaster.setTp(tp);
               iMaster.setEp(ep);
               
               iMaster.setVap(vap);
               iMaster.setVbp(vbp);
               iMaster.setCap(cap);
               iMaster.setCbp(cbp);
               
               iMaster.setLpAlt(lpAlt);
               iMaster.setEpAlt(epAlt);
               iMaster.setTpAlt(tpAlt);
               iMaster.setVapAlt(vapAlt);
               iMaster.setVbpAlt(vbpAlt);
               iMaster.setCapAlt(capAlt);
               iMaster.setCbpAtl(cbpAlt);
               
               iMaster.setVapCheckbox(false);
               iMaster.setVbpCheckbox(false);
               iMaster.setCapCheckbox(false);
               iMaster.setCbpCheckbox(false);
               
               iMaster.setCurrentStock((ccs));
               iMaster.setCp(cp); 
               
               itemMasterService.saveItem(iMaster);
                      }   
            

            
             stockJournalService.saveFStockJournalItem(sItem);
             
      ItemMaster iMaster_after_update=itemMasterService.getItmeByItemNo(sItem.getItemCode());       
      ItemReport iReport = new ItemReport();
      iReport.setItemId(iMaster_after_update.getId());
      iReport.setItemName(iMaster_after_update.getItemName());
      iReport.setItemGroupId(iMaster_after_update.getItemGroup());
      iReport.setTransactionDate(s.getFdate());
      iReport.setTransactionId(String.valueOf(s.getStockJournalId()));
      iReport.setTransactionType("Stock Journals");
      iReport.setInQuantity(sItem.getQty());
      iReport.setInValue(sItem.getQty() * iMaster_after_update.getCp());
      iReport.setClosingQuantity(iMaster_after_update.getCurrentStock());
      iReport.setClosingValue(iMaster_after_update.getCurrentStock() * iMaster_after_update.getCp());
      
      this.itemReportService.addItemReport(iReport);
      

       }    
      
       
   
       return new ModelAndView("redirect:StockJournalGrid.html");
    }
}


 @RequestMapping(value={"StockJournalGrid"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView SalesGrid()
  {
    StockJournalForm sForm=new StockJournalForm();
    ModelAndView model = new ModelAndView("StockJournalGrid");    
      model.addObject("stockJournalinfo",stockJournalService.listStockJournal());
    model.addObject("stockjournalFrom", sForm);
    return model;
  }
  
@RequestMapping(value={"editStockJournal"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
public ModelAndView editStockJournal(@RequestParam("id") String id, RedirectAttributes redirect)
{
     StockJournalForm sForm=new StockJournalForm();
    
    if(id!=null && id.length()>0){
        StockFjournalItem sFItem=new StockFjournalItem(); 
        
      StockJournal s=stockJournalService.getStockJournalById(Integer.parseInt(id));
      List list=stockJournalService.getStockJournalItemByStockJournalId(Integer.parseInt(id));
     List listf=stockJournalService.getFStockJournalItemByStockJournalId(Integer.parseInt(id));
     int stockJournalId=0;
     if(listf!=null && listf.size()>0){
         
         StockFjournalItem sji=(StockFjournalItem)listf.iterator().next();
         stockJournalId=sji.getStockJournalId();
         
     }
       if(s.getStockJournalId()==stockJournalId){
           System.out.print("If loop:");
            List listA=stockJournalService.getFStockJournalItemByStockJournalId(Integer.parseInt(id));
            sForm.setStocksF(listA);     
          
       }else{
           System.out.print("Else :");
          List listA=new ArrayList<StockFjournalItem>();
          listA.add(sFItem);
          sForm.setStocksF(listA);
          
       }
          sForm.setStock(s);
          sForm.setStocks(list);
          
         
      ModelAndView model = new ModelAndView("StockJournal");
      model.addObject("stockjournalFrom", sForm);
      //hide addRow
          model.addObject("sId", s.getStockJournalId());
          model.addObject("sFid",sForm.getStocksF().iterator().next().getId());
      return model;
    }
   else{
        ModelAndView model = new ModelAndView("redirect:StockJournalGrid.html");
       return model;
   }

}
  
   
  
@RequestMapping(value={"deleteStockJournal"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
public ModelAndView deletepredefined(@RequestParam("id") String id, RedirectAttributes redirect)
{
    ModelAndView model = new ModelAndView("redirect:StockJournalGrid.html");
   if(id!=null && id.length()>0){
       stockJournalService.deleteStockJournal(id);
       stockJournalService.deleteStockJournalItem(id);
       stockJournalService.deleteFStockJournalItem(id);
       return model;
   }
   else{
       return model;
   }

}
    
     

@RequestMapping(value={"stockjournal_addrow"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
public ModelAndView stockjournal_addrow(@ModelAttribute("stockjournalFrom") StockJournalForm stockjournalFrom, HttpServletRequest req)
{
    
    StockJournalItem sItem_plus=new StockJournalItem();
    StockJournalItem sItem=new StockJournalItem();
    StockJournalForm sForm=stockjournalFrom;
    
    List<StockJournalItem> list= sForm.getStocks();
    List<StockJournalItem> listNew =new ArrayList<StockJournalItem>(); 
    Iterator<StockJournalItem> i = list.iterator();
    while (i.hasNext())
    {
       sItem = i.next();
        // this condition exclude deleted empty row of data
        if(sItem.getItemCode()=="" || sItem.getItemCode()==null)
        {
           
        }
        else
        {
          // here add existing data to new arraylist  
          listNew.add(sItem);
        }    

    }
    listNew.add(sItem_plus);    // this step add empty row into arraylist
    sForm.setStocks(listNew); //setting all data with empty row into form
    
    
    ModelAndView model = new ModelAndView("stockjournal_addrow");
   model.addObject("stockjournalFrom", sForm);
    return model;
}

@RequestMapping(value={"stockjournalFinish_addrow"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
public ModelAndView stockjournalFinish_addrow(@ModelAttribute("stockjournalFrom") StockJournalForm stockjournalFrom, HttpServletRequest req)
{
 
    StockFjournalItem sItem_plus=new StockFjournalItem();
    StockFjournalItem sItem=new StockFjournalItem();
    StockJournalForm sFForm=stockjournalFrom;    
    List<StockFjournalItem> list1= sFForm.getStocksF();
    List<StockFjournalItem> listNew =new ArrayList<StockFjournalItem>();
    Iterator<StockFjournalItem> i = list1.iterator();
    while (i.hasNext())
    {
        sItem = i.next();
        // this condition exclude deleted empty row of data
        if(sItem.getItemCode()=="" || sItem.getItemCode()==null)
        {
           
        }
        else
        {
          // here add existing data to new arraylist  
          listNew.add(sItem);
        }    

    }
    listNew.add(sItem_plus);    // this step add empty row into arraylist
    sFForm.setStocksF(listNew); //setting all data with empty row into form
    
    
    
    ModelAndView model = new ModelAndView("stockjournalFinish_addrow");
   model.addObject("stockjournalFrom", sFForm);
    return model;
}
@RequestMapping(value={"StockJournalInvoice"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
public ModelAndView PurchaseEstimateInvoice(@RequestParam("id") String id){
    
    if(id!=null && id.length()>0){
        
        List list=companyservice.listcompany();
        CompanyInformation company=(CompanyInformation)list.iterator().next();
        
        
        Double listSitem=stockJournalService.getStockJournalItemByStockJournalId(Integer.parseInt(id)).iterator().next().getUnit();
        
        StockJournal StockJournal=stockJournalService.getStockJournalById(Integer.parseInt(id));
        //List StockJournallist=stockJournalService.getStockJournalItemByStockJournalId(Integer.parseInt(id));
        List<Object[]> StockJournallist=stockJournalService.getStockJournalItemByStockJournalIdInvoice(Integer.parseInt(id)); 
        Iterator itr=StockJournallist.iterator();
        
        while(itr.hasNext()){
        System.out.println("StockJournallist:"+itr.next());
        }
        //List StockJournallistF=stockJournalService.getFStockJournalItemByStockJournalId(Integer.parseInt(id));
        List<Object[]> StockJournallistF=stockJournalService.getFStockJournalItemByStockJournalIdInvoice(Integer.parseInt(id));

        
       
      ModelAndView model=new ModelAndView("StockJournalInvoice");
      model.addObject("company", company);
      model.addObject("StockJournal", StockJournal);
      model.addObject("StockJournallist", StockJournallist);
      model.addObject("StockJournallistF",StockJournallistF);
       company=companyservice.getCompanyById(1);
      model.addObject("companyFrom",company);
      return model;
    }
   else{
       ModelAndView model = new ModelAndView("redirect:StockJournal.html");
       return model;
   }
    
} 

@RequestMapping(value={"GetDatatableSTGrid"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   
    public DatatableObject getDatatable( HttpServletRequest req)
    {
      DatatableObject db =new DatatableObject();
           String[] cols = {"stock_journal_id", "date", "total_cost_comp", "valu_additiion","stock_journal_id", "fdate", "value_of_finish_good", "id", "value_of_finish_good"};

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

    String individualSearch = "";

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
        String sql = "SELECT st.stock_journal_id as sid,st.date,st.total_cost_comp,st.valu_additiion,st.stock_journal_id,st.fdate,st.value_of_finish_good,st.id,st.value_of_finish_good as vfg FROM stock_journal AS st";
        List<Object[]> list = stockJournalService.GetDatatableObject(sql);
        total=list.size();
    }catch(Exception e){
      
    }
    int totalAfterFilter = total;
    //result.put("sEcho",echo);
 
    try {
        String searchSQL = "";
        String sql = "SELECT st.stock_journal_id as sid,st.date,st.total_cost_comp,st.valu_additiion,st.stock_journal_id,st.fdate,st.value_of_finish_good,st.id,st.value_of_finish_good as vfg FROM stock_journal AS st";
        String searchTerm =req.getParameter("search[value]");
         String globeSearch =  " where (stock_journal_id like '"+searchTerm+"%')";
        if(searchTerm!=""&&individualSearch!=""){
            searchSQL = globeSearch + " and " + individualSearch;
        }
        else if(individualSearch!=""){
            searchSQL = " where " + individualSearch;
        }else if(searchTerm!=""){
            searchSQL=globeSearch;
        }
        sql += searchSQL;
        sql += " order by " + colName + " " + dir;
        sql += " limit " + start + ", " + amount;
        // For aData
         List<Object[]> list2 = stockJournalService.GetDatatableObject(sql);
         // For Filter Count 
        String sql2 = "SELECT st.stock_journal_id as sid ,st.date,st.total_cost_comp,st.valu_additiion,st.stock_journal_id,st.fdate,st.value_of_finish_good,st.id,st.value_of_finish_good as vfg FROM stock_journal AS st";
       if (searchTerm != "") {
            sql2 += searchSQL;
          List<Object[]> count = stockJournalService.GetDatatableCount(sql2);
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
