package com.accounting.controller;

import com.accounting.bean.CompanyInformation;
import com.accounting.bean.DatatableObject;
import com.accounting.bean.DefaultSettings;
import com.accounting.bean.Entries;
import com.accounting.bean.Entryitems;
import com.accounting.bean.ItemMaster;
import com.accounting.bean.ItemReport;
import com.accounting.bean.Purchase;
import com.accounting.bean.PurchaseForm;
import com.accounting.bean.PurchaseItem;
import com.accounting.bean.ReversalOfItc;
import com.accounting.bean.ReversalOfItcForm;
import com.accounting.bean.ReversalOfItcItem;
import com.accounting.service.AccountDBOService;
import com.accounting.service.CcodeService;
import java.text.DecimalFormat;
import com.accounting.service.CompanyService;
import com.accounting.service.DefaultSetting_service;
import com.accounting.service.EntryService;
import com.accounting.service.ItcReversal_service;
import com.accounting.service.ItemGroup_Service;
import com.accounting.service.ItemMasterService;
import com.accounting.service.ItemReport_service;
import com.accounting.service.LedgerAccount_Service;
import com.accounting.service.LedgerBalanceService;
import com.accounting.service.PurchaseService;
import com.accounting.service.ReversalOfItcService;
import com.accounting.service.Supplier_Service;
import com.accounting.service.TaxStructure_Service;
import com.accounting.service.Unit_service;
import com.accounting.validator.ReversalofitcFormValidator;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Connection;
import java.text.DateFormat;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RestController
public class ReversalOfItcTableController {
    
    @Autowired
    private Supplier_Service ss;

      @Autowired
    private PurchaseService purchaseService;
      
      @Autowired
    private ItemMasterService itemMasterService;
      
      @Autowired
    private TaxStructure_Service tServ;
      
      @Autowired
    private ItcReversal_service its;
     @Autowired
    private ReversalOfItcService reversalOfItcService;

    
    @Autowired
    private ItemGroup_Service itemGroupService;
    
    @Autowired
    private CcodeService cService;
    
    @Autowired
    private Unit_service unitService;
    
     @Autowired
    private CompanyService companyservice; 
     
     @Autowired
    private EntryService entryService;

     
     @Autowired
     private ReversalofitcFormValidator reversalofitcFormValidator;
     
    @Autowired
    private LedgerAccount_Service ledgerAccountService;
    
    @Autowired
    private ItemReport_service itemReportService;
    
    @Autowired
    private LedgerBalanceService lbs;
         @Autowired
    private AccountDBOService as;
         
         
         @Autowired
    private SessionFactory sessionFactory;
         
    @Autowired
    private DefaultSetting_service des;
    DefaultSettings ds=new DefaultSettings();
     
   @InitBinder("reversalFrom")
    protected void initBinder(WebDataBinder binder) {

                          binder.setValidator(reversalofitcFormValidator);
          
    }
    
    @RequestMapping(value={"ReversalOfItc"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView Purchaseinformation()
  {
   ReversalOfItc r=new ReversalOfItc();
    ReversalOfItcForm rForm=new ReversalOfItcForm();
    ReversalOfItcItem rItem=new ReversalOfItcItem();
    List<ReversalOfItcItem> list=new ArrayList<ReversalOfItcItem>();
    list.add(rItem);
    rForm.setReversalofitc(r);
    rForm.setReversalofitcs(list);
    ModelAndView model = new ModelAndView("ReversalOfItc");
    
    model.addObject("reversalFrom",rForm);
     model.addObject("itcList",its.listReversal());
     model.addObject("unitList", this.unitService.listUnit());
     model.addObject("taxStructure", tServ.getTaxId(1));
    
    return model;
}

@RequestMapping(value={"SaveReversalOfItc"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
public ModelAndView saveItem(@ModelAttribute("reversalFrom")@Validated ReversalOfItcForm reversalFrom, BindingResult result,RedirectAttributes redirect,HttpSession ses)
{
    
    if (result.hasErrors()) {
        return new ModelAndView("ReversalOfItc");
        
    }
    else{
        
         // start finacial year  
       String yearRange=as.GetYearRange();
       long previousId=as.getPreviousIdBasedStartAndEndYear("reversalOdItcId", "ReversalOfItc", yearRange);
        System.out.print("  sekar  :"+previousId);
        String primaryIdPE="";  
          if(previousId==0){
             primaryIdPE=previousId+1+"/"+yearRange;
          }else{
               primaryIdPE=previousId+1+"/"+yearRange;
          }
           ses.setAttribute("primaryIdPE", primaryIdPE);
            System.out.print("  sekar -- :"+primaryIdPE);
          // end of finacial year  
      
        ReversalOfItc r=reversalFrom.getReversalofitc();
        r.setReversalOdItcId(null);
       String reversalOdItcId=reversalOfItcService.saveReversalOfItc(r);
       
       List list=reversalFrom.getReversalofitcs();  
  
       
       Iterator itr=list.iterator();
       while(itr.hasNext()){
           ReversalOfItcItem rItem=(ReversalOfItcItem)itr.next();
           if(rItem.getReturnQty()>0 && rItem.getReturnQty()!=null)
           {
           rItem.setReversalOdItcId(reversalOdItcId);        
           rItem.setDate(r.getReturnDate());
           ItemMaster iMaster=itemMasterService.getItmeByItemNo(rItem.getItemCode());
           
//           System.out.println("ReturnQty" + rItem.getReturnQty());
//          System.out.println("CurrentStock" + iMaster.getCurrentStock());
//           if(rItem.getReturnQty() <=(iMaster.getCurrentStock()))
//      { 
//          System.out.println("ReturnQty" + rItem.getReturnQty());
//          System.out.println("CurrentStock" + iMaster.getCurrentStock());
//           if(rItem.getId()==null || rItem.getId().equals(""))
//           {
//               String query="UPDATE `item_master` SET current_stock=current_stock-"+rItem.getReturnQty()+" WHERE item_code='"+rItem.getItemCode()+"'";
//               itemMasterService.updateItemMaster(query);
//                             
//           }
//      }
//           else 
//           {
//                System.out.println("else ..... condition" + iMaster.getCurrentStock());
//            ModelAndView model =new  ModelAndView("ReversalOfItc");
//           
//            model.addObject("Message", "    "+rItem.getNameOfTheItem()+" is out of stock please decrese qty");
//             model.addObject("reversalFrom", reversalFrom);
//              model.addObject("itcList",its.listReversal());
//           return model;
//           }
           
           reversalOfItcService.saveReversalOfItcItem(rItem);
           
//           set.add(rItem.getTax());
           
           //Save item transaction table

         
           //Purpose of Return
           double closingStockvalue=(iMaster.getCurrentStock())*iMaster.getCp();
           double currentCP_EP=rItem.getReturnQty()*(rItem.getEp());
           double currentClosingStockValue=closingStockvalue-currentCP_EP;
            // Purpose of Return
           ItemMaster iMaster_after_update=itemMasterService.getItmeByItemNo(rItem.getItemCode()); 
           ItemReport iReport=new ItemReport();
           iReport.setTransactionDate(r.getReturnDate());
           iReport.setItemId(iMaster_after_update.getId());
           iReport.setItemName(iMaster_after_update.getItemName());
           iReport.setItemGroupId(iMaster_after_update.getItemGroup());
           iReport.setTransactionId(String.valueOf(reversalOdItcId));
//           iReport.setTransactionName(supplier_name);
           iReport.setTransactionType("Purchase Return");
           iReport.setOutQuantity(rItem.getReturnQty());
           iReport.setOutValue((rItem.getReturnQty()*rItem.getEp()));
//           iReport.setClosingQuantity(iMaster_after_update.getCurrentStock());
//           iReport.setClosingValue(currentClosingStockValue);
//           double cp=currentClosingStockValue/iMaster_after_update.getCurrentStock();
           
           itemReportService.addItemReport(iReport);  
           
             
//        iMaster.setCurrentStock(iMaster_after_update.getCurrentStock());
//        iMaster.setCp(Double.valueOf(cp));
        
        itemMasterService.saveItem(iMaster);
       } 
       }   
    }
       return new ModelAndView("redirect:ReversalOfItcGrid.html");
}



 @RequestMapping(value={"ReversalOfItcGrid"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView SalesGrid()
  {
    ReversalOfItcForm rForm=new ReversalOfItcForm();
    
    ModelAndView model = new ModelAndView("ReversalOfItcGrid");    
   
      model.addObject("reversalOfItcinfo",reversalOfItcService.listReversalOfItc());
      model.addObject("reversalFrom",rForm);
     
    return model;
  }
  
@RequestMapping(value={"editReversalOfItc"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
public ModelAndView editSales(@RequestParam("vid") String id, RedirectAttributes redirect)
{
    
    if(id!=null && id.length()>0){
        
      ReversalOfItc r=reversalOfItcService.getReversalOfItcById(id);
      List list=reversalOfItcService.getReversalOfItcItemByReversalOfItcId(id);
      
      ReversalOfItcForm rForm=new ReversalOfItcForm();
      rForm.setReversalofitc(r);
      rForm.setReversalofitcs(list);
      ModelAndView model = new ModelAndView("ReversalOfItc");
      
     
         model.addObject("roId",id);
          model.addObject("viewId",id);
         
        model.addObject("reversalFrom",rForm);
             model.addObject("itcList",its.listReversal());
              model.addObject("unitList", this.unitService.listUnit());
      return model;
    }
   else{
        ModelAndView model = new ModelAndView("redirect:ReversalOfItcGrid.html");
       return model;
   }

}
  
   
  
@RequestMapping(value={"deleteReversalOfItc"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
public ModelAndView deleteSales(@RequestParam("id") String id, RedirectAttributes redirect)
{
    ModelAndView model = new ModelAndView("redirect:ReversalOfItcGrid.html");
   if(id!=null && id.length()>0){
       reversalOfItcService.deleteReversalOfItc(id);    
       return model;
   }
   else{
       return model;
   }

}
    
     

@RequestMapping(value={"reversal_addrow"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
public ModelAndView purchaseaddrow(@ModelAttribute("reversalFrom") ReversalOfItcForm reversalFrom, HttpServletRequest req)
{
     ReversalOfItcItem rItem=new ReversalOfItcItem();
     ReversalOfItcForm rForm=reversalFrom;
    List<ReversalOfItcItem> list= rForm.getReversalofitcs();
    list.add(rItem);
    rForm.setReversalofitcs(list);
    ModelAndView model = new ModelAndView("reversal_addrow");
     model.addObject("itcList",its.listReversal());
     model.addObject("supplierList",ss.listSupplier());
    model.addObject("reversalFrom",rForm);
    return model;
}

@RequestMapping(value={"PurchaseReturn"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
public ModelAndView PurchaseReturn(@RequestParam("id") String id, RedirectAttributes redirect)
{
    System.out.println("seka");
    if(id!=null && id.length()>0){
         ModelAndView model = new ModelAndView("ReversalOfItc");
        Purchase purchase=purchaseService.getPurchaseById(id);
        List list=purchaseService.getPurchaseItemByPurchaseId(id);
        
      ReversalOfItc r=new ReversalOfItc();
      r.setCashSupplier(purchase.getCashSupplier());
      r.setBillNo(purchase.getInvoiceNo());
      r.setSupplierId(purchase.getSupplierId());
      r.setTinNo(purchase.getTinNo());
      r.setMode(purchase.getMode());
      r.setBalance(purchase.getInvoiceAmount());
      r.setAssessValue(purchase.getAssessValue());
      r.setTotalCgst(purchase.getTotalCgst());
      r.setTotalVat(purchase.getTotalVat());
      r.setTotalIgst(purchase.getTotalIgst());
       System.out.println("igst:"+  purchase.getTotalIgst());
      r.setAddOrLess(purchase.getAddOrLess());
      r.setSupplierType(purchase.getSupplierType());
        System.out.println("setAddOrLess:"+purchase.getAddOrLess());
      r.setTotalAmount(purchase.getTotalAmount());
      r.setDebtAmount(purchase.getTotalInvoiceAmount());
      Date d=purchase.getDate();
      r.setDate(d);
      r.setLedgerAccount(purchase.getNameOfSupplier());
      
     double countQty=0.0;
      
      List<ReversalOfItcItem> purchaseList=new ArrayList<ReversalOfItcItem>();
      Iterator itr=list.iterator();
      while(itr.hasNext()){
          PurchaseItem puItem=(PurchaseItem)itr.next();
          
          ReversalOfItcItem rItem=new ReversalOfItcItem();
          String itemcode=puItem.getItemCode();
          rItem.setItemCode(puItem.getItemCode());
           rItem.setNameOfTheItem(puItem.getNameOfTheItem());
           //rItem.setQty(puItem.getQty());
            if(puItem.getQty()>0){
                
              double CurrentQty=reversalOfItcService.getReversalReturnId(itemcode,purchase.getInvoiceNo());

                rItem.setQty(puItem.getQty()-CurrentQty);
                
                countQty=puItem.getQty()-CurrentQty;
           
            }else
            {
               
                rItem.setQty(puItem.getQty());
            }
            
             rItem.setUnit(puItem.getUnit());
               rItem.setEp(puItem.getEp());
                rItem.setAmount(puItem.getAmount());
                 rItem.setCgst(puItem.getCgst());
                  rItem.setVat(puItem.getVat());
                   rItem.setIgst(puItem.getIgst());
                  rItem.setTaxCgst(puItem.getTaxCgst());
                  rItem.setTaxSgst(puItem.getTaxSgst());
                  rItem.setTaxIgst(puItem.getTaxIgst());
                  rItem.setTax(puItem.getTax());
                    purchaseList.add(rItem);
      }
      
      ReversalOfItcForm rForm=new ReversalOfItcForm();
      rForm.setReversalofitc(r);
      rForm.setReversalofitcs(purchaseList);

     
//      ds=des.getDefaultbyId(1);
//        System.out.println("ds= "+ds);
//      r.setCategoryForReversal(ds.getReversalItcCategory());
//       System.out.println("ds.getReversalItcCategory() : "+ds.getReversalItcCategory());
      model.addObject("reversalFrom", rForm);
      model.addObject("taxStructure", tServ.getTaxId(1));
      model.addObject("itcList",its.listReversal());
      model.addObject("unitList", this.unitService.listUnit());
      model.addObject("CurrentQty", countQty);
      return model;
    }
   else{
        ModelAndView model = new ModelAndView("redirect:PurchaseGrid.html");
       return model;
   }

}

 @RequestMapping(value={"GetDatatablePRGrid"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   
    public DatatableObject getDatatable( HttpServletRequest req)
    {
      DatatableObject db =new DatatableObject();
           String[] cols = {"reversal_od_itc_id", "date", "name_of_supplier", "tin_no","mode", "category", "total_amount"};

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
        String sql = "SELECT r.reversal_od_itc_id,r.date,r.bill_no,r.mode,r.return_date,'' FROM reversal_of_itc AS r";
        List<Object[]> list = reversalOfItcService.GetDatatableObject(sql);
        total=list.size();

    }catch(Exception e){
         
    }
    int totalAfterFilter = total;
    //result.put("sEcho",echo);
 
    try {
        String searchSQL = "";
        String sql = "SELECT r.reversal_od_itc_id,r.date,r.bill_no,r.mode,r.return_date,'' FROM reversal_of_itc AS r";
        String searchTerm =req.getParameter("search[value]");
         String globeSearch =  " where (reversal_od_itc_id like '"+searchTerm+"%')";
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
         List<Object[]> list2 = reversalOfItcService.GetDatatableObject(sql);
         // For Filter Count 
        String sql2 = "SELECT r.reversal_od_itc_id,r.date,r.bill_no,r.mode,r.return_date,'' FROM reversal_of_itc AS r";
       if (searchTerm != "") {
            sql2 += searchSQL;
          List<Object[]> count = reversalOfItcService.GetDatatableCount(sql2);
          totalAfterFilter=count.size();
        }
  
   db.setiTotalRecords(total);
   db.setiTotalDisplayRecords(totalAfterFilter);
   db.setAaData(list2);
    } catch (Exception e) {
 
    }
      return db;
	}  

    
    @RequestMapping(value={"ReversalofITCReports"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView ReversalofITCReports()
  {
    
      
    String fromDate = companyservice.getCompanyById(1).getFinancialYearFrom();
      
    String financialdateMonth=fromDate.substring(0, 5);
       
    String financialmonth=financialdateMonth.substring(3, 5);
    int intFinancialMonth=Integer.parseInt(financialmonth);
       System.out.println("financialmonth " +financialmonth);
       
       
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
      
    ModelAndView model = new ModelAndView("ReversalofITCReports");
   // model.addObject("ReversalofITCListFullinfo", this.reversalOfItcService.listReversalOfItc());
    model.addObject("curDate", dt);
    model.addObject("finalDate", finalDate);
    return model;
  }

  @RequestMapping(value={"GetReversalofITCReports"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView GetReversalofITCReports(HttpServletRequest req,HttpServletResponse response)throws ParseException, JRException, IOException
  {
    String gsQuery = "";
    
    HttpSession session = null;
    Session session1 = this.sessionFactory.openSession();
    SessionImpl sessionImpl = (SessionImpl)session1;
    Connection conn = sessionImpl.connection();
    
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
    
    String viewType=req.getParameter("viewType");
    
    ModelAndView model = new ModelAndView("ReversalofITCReports");
    String reportfile = req.getServletContext().getRealPath("//Reports//ReversalofITCReports.jrxml");

    CompanyInformation u = companyservice.getCompanyById(1);
      System.out.println("Company Name:"+u.getCompanyName());
    String cname = u.getCompanyName();
    String address = u.getAddress();
    JasperReport jasperReport = JasperCompileManager.compileReport(reportfile);
    Map parametersMap = new HashMap();
    parametersMap.put("companyName", cname);
    parametersMap.put("Companyaddress", address);
    parametersMap.put("startDate", sdfIn.parse(gsFromDate));
    parametersMap.put("endDate", sdfInn.parse(gsToDate));
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
        response.setHeader("Content-Disposition", "inline;filename=ReversalofITCReports.xlsx");
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
  
    
}
