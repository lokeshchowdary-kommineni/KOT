package com.accounting.controller;

import com.accounting.bean.CompanyInformation;
import com.accounting.bean.DatatableObject;
import com.accounting.bean.DefaultSettings;
import com.accounting.bean.Entries;
import com.accounting.bean.Entryitems;
import com.accounting.bean.ItemMaster;
import com.accounting.bean.ItemReport;
import com.accounting.bean.LedgerAccountMaster;
import com.accounting.bean.Purchase;
import com.accounting.bean.PurchaseForm;
import com.accounting.bean.PurchaseItem;
import com.accounting.bean.Sales;
import com.accounting.bean.SalesItem;
import com.accounting.bean.SalesReturn;
import com.accounting.bean.SalesReturnItem;
import com.accounting.bean.SupplierMaster;
import com.accounting.dao.AccountingPDFdao;
import com.accounting.service.AccountDBOService;
import com.accounting.service.CcodeService;
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
import com.accounting.service.SalesReturnService;
import com.accounting.service.SalesService;
import com.accounting.service.Supplier_Service;
import com.accounting.service.TaxStructure_Service;
import com.accounting.service.Unit_service;
import com.accounting.validator.PurchaseFormValidator;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import static sun.misc.Version.print;

@RestController
public class PurchaseController
{
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
  private PurchaseFormValidator purchaseFormValidator;
  @Autowired
  private LedgerAccount_Service ledgerAccountService;
  @Autowired
  private ItemReport_service itemReportService;
  @Autowired
  private SalesService salesService;
  @Autowired
  private SalesReturnService salesReturnService;
  
  @Autowired
    private SessionFactory sessionFactory;
  
  @Autowired
    private LedgerBalanceService lbs;
   @Autowired
    private AccountDBOService as;
   
   @Autowired 
   private AccountingPDFdao accountingPDFdao;
 
      @Autowired
    private DefaultSetting_service des;
    DefaultSettings ds=new DefaultSettings();
  
  @InitBinder({"purchaseFrom"})
  protected void initBinder(WebDataBinder binder)
  {
    binder.setValidator(this.purchaseFormValidator);
  }
  
  @RequestMapping(value={"Purchase"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView Purchaseinformation(HttpServletRequest req)
  {
    Purchase p = new Purchase();
    PurchaseForm pForm = new PurchaseForm();
    PurchaseItem pItem = new PurchaseItem();
    List<PurchaseItem> list = new ArrayList();
    list.add(pItem);
    pForm.setPurchases(list);
    pForm.setPurchase(p);
    ModelAndView model = new ModelAndView("Purchase");
    if(des.getDefaultbyId(1)!=null)
    {
    ds=des.getDefaultbyId(1);
    p.setCategory(ds.getPurchaseCategory());
    p.setMode(ds.getPurchaseMode());
    model.addObject("CategoryAuto", ds.getPurchaseCategory());
    model.addObject("Automode", ds.getPurchaseMode());
    }
    else
    {
    model.addObject("CategoryAuto", "");
    model.addObject("Automode", "");  
    }
      
    if(companyservice.getCompanyById(1)!=null)
    {
    CompanyInformation tin= companyservice.getCompanyById(1);    
    String gstinCompany=tin.getCompanyTin().substring(0, 2);
    model.addObject("CompanyGstin", gstinCompany);  
    pForm.getPurchase().setPos(gstinCompany);
    }
    else
    {
     model.addObject("CompanyGstin", "");      
    }  
    
     String CompanyGstno="";
    if(companyservice.getCompanyById(1)!=null)
    {
      CompanyGstno=companyservice.getCompanyById(1).getCompanyTin();
      CompanyGstno=CompanyGstno.substring(0, 2);
    }  
    model.addObject("purchaseFrom", pForm);
    model.addObject("supplierList", this.ss.listSupplier());
    model.addObject("unitList", this.unitService.listUnit());
    model.addObject("taxStructure", this.tServ.getTaxId(1));
    model.addObject("CompanyGstno",CompanyGstno);
     
    ItemMaster itemMaster = new ItemMaster();
    model.addObject("itemForm", itemMaster);
    return model;
  }
  
  @RequestMapping(value={"SavePurchase"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView saveItem(@ModelAttribute("purchaseFrom") @Validated PurchaseForm purchaseFrom, BindingResult result, RedirectAttributes redirect, HttpServletRequest req,HttpSession ses)
  {
    if (result.hasErrors())
    {
      return new ModelAndView("Purchase");
    }
          String pid=req.getParameter("pid");
            // code to ignore validator(itemCode) while removing addRow --- Starts(Abi-20.07.2017)

                List<PurchaseItem> listPurchase = purchaseFrom.getPurchases();

                PurchaseItem purchaseItem = new PurchaseItem();

                    List<PurchaseItem> listNew =new ArrayList<PurchaseItem>(); 
                    Iterator<PurchaseItem> i = listPurchase.iterator();
                    while (i.hasNext())
                    {
                        purchaseItem = i.next();
                        // this condition exclude deleted empty row of data
                        if( purchaseItem.getItemCode()==null || purchaseItem.getItemCode().isEmpty())
                        {

                        }
                        else
                        {
                          // here add existing data to new arraylist  
                          listNew.add(purchaseItem);
                        }   
                    }
            purchaseFrom.setPurchases(listNew);
   
    
                    // start finacial year  
                      String yearRange=as.GetYearRange();
                      long previousId=as.getPreviousIdBasedStartAndEndYear("invoiceNo", "Purchase", yearRange);

                       String primaryIdPE="";  
                         if(previousId==0){
                            primaryIdPE=previousId+1+"/"+yearRange;
                         }else{
                              primaryIdPE=previousId+1+"/"+yearRange;
                         }
                          ses.setAttribute("primaryIdPE", primaryIdPE);

                      // end of finacial year  
    
    
        Purchase p = purchaseFrom.getPurchase();
      
        
        if(pid==null)
             {
                 p.setInvoiceNo(null);
             }
        else if(pid.isEmpty())
             {
                 p.setInvoiceNo(null);
             }
        else
             {
                 p.setInvoiceNo(pid);
             }

        String invoiceNo = this.purchaseService.savePurchase(p);
        
        List list = purchaseFrom.getPurchases();
        HashSet<String> set = new HashSet();
        Iterator itr = list.iterator();
            while (itr.hasNext())
            {
              PurchaseItem pItem = (PurchaseItem)itr.next();
              pItem.setInvoiceNo(invoiceNo);
              pItem.setDate(p.getDate());
              set.add(pItem.getTax());
              
               String piId=String.valueOf(pItem.getId());
               String iName= pItem.getNameOfTheItem();
           if(piId.equalsIgnoreCase("null"))
           {
               ItemMaster iMaster_after_update=itemMasterService.getItmeByItemNo(pItem.getItemCode());
                             ItemReport iReport=new ItemReport();
                             iReport.setItemId(iMaster_after_update.getId());
                             iReport.setItemName(iMaster_after_update.getItemName());
                             iReport.setItemGroupId(iMaster_after_update.getItemGroup());
                             iReport.setTransactionId(invoiceNo);
                             iReport.setTransactionType("Purchase");
                             iReport.setTransactionDate(p.getDate());
                             iReport.setInQuantity(pItem.getQty());
                             iReport.setInValue(pItem.getQty() * pItem.getEp());   
                             itemReportService.addItemReport(iReport);
           }
           else
           {
            String type="Purchase";
            List irList= purchaseService.getItemReportByPurchaseId(pid, type ,iName);
            String Query="UPDATE item_report SET in_quantity="+pItem.getQty()+",in_value="+pItem.getAmount()+",transaction_id='"+pid+"',transaction_type='"+type+"',item_name='"+pItem.getNameOfTheItem()+"' WHERE transaction_id='"+pid+"' AND transaction_type='"+type+"' AND item_name='"+iName+"'";
            purchaseService.updateItemReport(Query);
           }
              
              
                purchaseService.savePurchaseItem(pItem);
    }
    return new ModelAndView("redirect:PurchaseGrid.html");
  }
  
  @RequestMapping(value={"PurchaseGrid"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView PurchaseGrid()
  {
    PurchaseForm pForm = new PurchaseForm();
    ModelAndView model = new ModelAndView("PurchaseGrid");
    model.addObject("purchaseinfo", this.purchaseService.listPurchse());
    model.addObject("purchaseFrom", pForm);
    return model;
  }
  
  @RequestMapping(value={"PurchaseReports"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView PurchaseReports()
  {
    PurchaseForm pForm = new PurchaseForm();
    ModelAndView model = new ModelAndView("PurchaseReports");
    model.addObject("purchaseListFullinfo", this.purchaseService.listPurchse());
    model.addObject("purchaseFrom", pForm);
    return model;
  }
  
  @RequestMapping(value={"editPurchase"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView editSales(@RequestParam("id") String id, RedirectAttributes redirect)
  {
    if ((id != null) && (id.length() > 0))
      
    {
      Purchase p = new Purchase();
     
      
        Purchase purchase = this.purchaseService.getPurchaseById(id);
      List list = this.purchaseService.getPurchaseItemByPurchaseId(id);
      
      
            
      PurchaseForm pForm = new PurchaseForm();
      pForm.setPurchase(purchase);
      pForm.setPurchases(list);
      ModelAndView model = new ModelAndView("Purchase");
      Purchase pur = this.purchaseService.getPurchaseById(id);
      p.setCategory(pur.getCategory());
      p.setMode(pur.getMode());
     
      
      model.addObject("pId", id);
      model.addObject("CategoryAuto", pur.getCategory());
      model.addObject("purchaseFrom", pForm);
      model.addObject("taxStructure", this.tServ.getTaxId(1));
      model.addObject("unitList", this.unitService.listUnit());
      model.addObject("supplierList", this.ss.listSupplier());
      return model;
    }
    ModelAndView model = new ModelAndView("redirect:PurchaseGrid.html");
    return model;
  }
  
  @RequestMapping(value={"deletePurchase"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView deleteSales(@RequestParam("id") String id, RedirectAttributes redirect)
  {
    ModelAndView model = new ModelAndView("redirect:PurchaseGrid.html");
    if ((id != null) && (id.length() > 0))
    {
      this.purchaseService.deletePurchase(id);
      this.purchaseService.deletePurchaseItem(id);
      return model;
    }
    return model;
  }
  
  @RequestMapping(value={"purchase_addrow"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView purchaseaddrow(@ModelAttribute("purchaseFrom") PurchaseForm purchaseFrom, HttpServletRequest req)
  {
    Purchase p = new Purchase();
    PurchaseItem pItem_plus = new PurchaseItem();
    PurchaseItem pItem = new PurchaseItem();
    PurchaseForm pForm = purchaseFrom;
    List<PurchaseItem> list = pForm.getPurchases();
    List<PurchaseItem> listNew =new ArrayList<PurchaseItem>(); 
    Iterator<PurchaseItem> i = list.iterator();
    while (i.hasNext())
    {
        pItem = i.next();
        // this condition exclude deleted empty row of data
        if(pItem.getItemCode()=="" || pItem.getItemCode()==null)
        {
           
        }
        else
        {
          // here add existing data to new arraylist  
          listNew.add(pItem);
        }    

    }
    listNew.add(pItem_plus);    // this step add empty row into arraylist
    pForm.setPurchases(listNew); //setting all data with empty row into form
   
   
    ModelAndView model = new ModelAndView("purchase_addrow");
   
    
    if(des.getDefaultbyId(1)!=null)
    {
    ds=des.getDefaultbyId(1);
    p.setCategory(ds.getPurchaseCategory());
    p.setMode(ds.getPurchaseMode());
    model.addObject("CategoryAuto", ds.getPurchaseCategory());
    model.addObject("Automode", ds.getPurchaseMode());
    }
    else
    {
    model.addObject("CategoryAuto", "");
    model.addObject("Automode", "");  
    }
    if(companyservice.getCompanyById(1)!=null)
    {
    CompanyInformation tin= companyservice.getCompanyById(1);    
    String gstinCompany=tin.getCompanyTin().substring(0, 2);
    model.addObject("CompanyGstin", gstinCompany);     
    pForm.getPurchase().setPos(gstinCompany);
    }
    else
    {
     model.addObject("CompanyGstin", "");      
    }  
    
    model.addObject("purchaseFrom", pForm);
    model.addObject("supplierList", this.ss.listSupplier());
    model.addObject("unitList", this.unitService.listUnit());
    model.addObject("taxStructure", this.tServ.getTaxId(1));
    return model;
  }
  
  @RequestMapping(value={"SaveItemPopUp"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public String SaveItemPopUp(@ModelAttribute("itemForm") ItemMaster itemMaster)
  {
    if ((itemMaster.getId() == null) || (itemMaster.getId().equals("")))
    {
      itemMaster.setCurrentStock(itemMaster.getOpeningStock());
      itemMaster.setCp(itemMaster.getCp());
    }
//    this.itemMasterService.saveItem(itemMaster);
    
    return "xys";
  }
   @RequestMapping(value={"PurchaseReportForm"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView PurchaseReportForm()
  {
    PurchaseForm pForm = new PurchaseForm();
    
    
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
     
       
    ModelAndView model = new ModelAndView("PurchaseReportForm");
    model.addObject("curDate", dt);
    model.addObject("finalDate", finalDate);
   
    return model;
  }
  @RequestMapping(value={"GetPurchaseReport"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView GetPurchaseReport(HttpServletRequest req,HttpServletResponse response)throws ParseException, JRException, IOException
  {
    String gsQuery = "";
    
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd ");
   Date currentDate = new Date();
   System.out.println(dateFormat.format(currentDate));
      System.out.println("current date");
      
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
    
   
    String reportfile = req.getServletContext().getRealPath("//Reports//Purchase.jrxml");

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
        response.setHeader("Content-Disposition", "inline;filename=Purchase.xlsx");
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
  
  @RequestMapping(value={"GetSupplier"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public List<SupplierMaster> GetSuppliers(HttpServletRequest req)
    throws IOException
  {
      String checkCategory=req.getParameter("category");
      List<SupplierMaster> supplierList = null;
      
      System.out.println("ITC Purchase"  +  checkCategory);
      
      if(checkCategory.equalsIgnoreCase("ITC Purchase") ) 
        
      {
          
           supplierList = this.purchaseService.listSupplierLike(req.getParameter("term"));
           System.out.println("entered ITC Purchase");
      }
    
     else if(checkCategory.equalsIgnoreCase("Purchases from unregistered Persons"))
            {
            supplierList = this.purchaseService.listSupplierWithoutGstin(req.getParameter("term"));
            System.out.println("entered Category");
            }
    
    return supplierList;
  }
  
  @RequestMapping(value={"GetSupplierList"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public List<SupplierMaster> GetSupplierList()
  {
    List<SupplierMaster> supplier = this.ss.listSupplier();
    return supplier;
  }
  /// Credit Not Show Cash Supplier///
   @RequestMapping(value={"GetSupplierListCredit"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public List<SupplierMaster> GetSupplierListCredit()
  {
    List<SupplierMaster> supplier = this.ss.listSupplierCredit();
    return supplier;
  }
  //////////////////////
  @RequestMapping(value={"PopUpWindowItemMaster"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView PopUpWindowItemMaster(@ModelAttribute("id") String itemCode, RedirectAttributes redirect)
  {
    if ((itemCode != null) && (itemCode.length() > 0))
    {
      ModelAndView model = new ModelAndView("PopUpWindowItemMaster");
      ItemMaster iForm = (ItemMaster)this.itemMasterService.getItmeByItemNo(itemCode);
      model.addObject("itemList", this.itemMasterService.getItemList());
      model.addObject("itemGroupList", this.itemGroupService.listItem());
      model.addObject("cCodeList", this.cService.listCode());
      model.addObject("unitList", this.unitService.listUnit());
      model.addObject("sumOfStockValue", this.itemMasterService.sumOfStockValue());
      model.addObject("itemForm", iForm);
      return model;
    }
    ModelAndView model = new ModelAndView("Purchase");
    return model;
  }
  @RequestMapping(value={"getSupplierAccount"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public LedgerAccountMaster getSupplierAccount(@RequestParam("id") String supplierID, LedgerAccountMaster bm,HttpServletRequest request)
    {
      
       String type=request.getParameter("type");
       Integer  ledgerID=purchaseService.getLedgerIdBySupplierId(Integer.parseInt(supplierID),type).getIdLedger();
      
       SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
       Date date = new Date();        
       String CurrentDate= dmyFormat.format(date);
       List<Object[]> currentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf( ledgerID), CurrentDate);
      
       double openingBalanceOfLedger=0;
       if( currentBalance!=null && currentBalance.size()>0)
       {
             for (Object[] column : currentBalance) {
                 
                        openingBalanceOfLedger = (Double)column[0]- (Double)column[1];
                }
           
       }
       else
       {
           List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf( ledgerID));
           for (Object[] column : OpeningBalance) {
                        openingBalanceOfLedger = (Double)column[0]- (Double)column[1];
                }
       }
        if(openingBalanceOfLedger>=0)
        {
          bm.setOpeningAmount(openingBalanceOfLedger);
          bm.setOpeningType("DR");
        }
          else
        {
          bm.setOpeningAmount(Math.abs(openingBalanceOfLedger));
          bm.setOpeningType("CR");  
         
        }
        return bm;
    }
    

  
  @RequestMapping(value={"ItemProfitReports"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView ItemProfitReports()
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
       
    ModelAndView model = new ModelAndView("ItemProfitReports");
    model.addObject("itemList", this.itemMasterService.getItemList());
//  model.addObject("ItemProfitListReportsInfo", this.purchaseService.listItemReportsSSR());
    model.addObject("itemGroupList",itemGroupService.listItem());
    model.addObject("curDate", dt);
    model.addObject("finalDate", finalDate);
    return model;
  }
  
  @RequestMapping(value={"VAAMCAReports"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView VAA_MCA_Reports()
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
       
    ModelAndView model = new ModelAndView("VAAMCAReports");
    model.addObject("VAAMCARListFullinfo", this.purchaseService.listVAAMCASSR());
    model.addObject("curDate", dt);
    model.addObject("finalDate", finalDate);
    return model;
  }
  
  @RequestMapping(value={"GetPurchaseReports"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView GetPurchaseReports(HttpServletRequest req) throws ParseException
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
    
    gsQuery = "From Purchase keyWhere ";
    String gsWhere = "";
    if (!gsFromDate.isEmpty())
    {
      if (!gsToDate.isEmpty())
      {
        if (gsWhere != "") {
          gsWhere = gsWhere + " and DATE_FORMAT(date,  '%y/%m/%d' )  \nBETWEEN  (  '" + sdfOut.format(date) + "') \nAND (  '" + sdfOut.format(datee) + "')  ";
        }
        else {
          gsWhere = gsWhere + "WHERE  date  between  ('" + sdfOut.format(date) + "') and ('" + sdfOut.format(datee) + "') ";
        }
      }
   
    }

    gsQuery = gsQuery.replace("keyWhere", gsWhere);
    System.out.println("purchase controller" + gsQuery);
    
    ModelAndView model = new ModelAndView("PurchaseReports");
    model.addObject("purchaseListFullinfo", this.purchaseService.purchaseReports(gsQuery));
    
    return model;
  }
  
  @RequestMapping(value={"GetItemSalesReports"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView GetItemSalesReports(HttpServletRequest req,HttpServletResponse response) 
  {
      try {
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
          String ItemCode = req.getParameter("item");
          String whereQry="";
          String itemwiseWhereQry="";
          if(!ItemCode.isEmpty())
          {
             whereQry="AND si.item_code=$P{ItemCode} OR sri.item_code=$P{ItemCode}";
             itemwiseWhereQry="AND im.item_code=$P{ItemCode}";
          }
          String viewType=req.getParameter("viewType");
          String viewTypeList=req.getParameter("type");
          
          ModelAndView model = new ModelAndView("ItemProfitReports");
          model.addObject("itemList", this.itemMasterService.getItemList());
          
            System.out.println(" test 1"+sdfOut.format(date));
          ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
          HttpServletRequest request = attr.getRequest();
          CompanyInformation u = companyservice.getCompanyById(1);
          String cname = u.getCompanyName();
          String address = u.getAddress();
           System.out.println(" test 1"+sdfOut.format(date));
           System.out.println("sdfIn.parse(gsFromDate)"+sdfIn.parse(gsFromDate));
           System.out.println("sdfInn.parse(gsToDate)) "+sdfInn.parse(gsToDate));
          
          
          Map parametersMap = new HashMap();
          parametersMap.put("companyName", cname);
          parametersMap.put("Companyaddress", address);
          parametersMap.put("startDate", sdfIn.parse(gsFromDate));
          parametersMap.put("endDate", sdfInn.parse(gsToDate));
          parametersMap.put("ItemCode", ItemCode);
         
            System.out.print(" inside 0 hxszdfhjisjdItemCode "+ItemCode); 
          String reportfile = "";
          
          if(viewTypeList.equalsIgnoreCase("0"))
          {
            
              parametersMap.put("wQry", whereQry);
              reportfile = req.getServletContext().getRealPath("//Reports//ItemProfitReportsVoucherWise.jrxml");
              
          }
          if(viewTypeList.equalsIgnoreCase("1"))
          {
            
               parametersMap.put("wQry", itemwiseWhereQry);
              reportfile = req.getServletContext().getRealPath("//Reports//ItemProfitReportsItemWise.jrxml");
              
          }
          
          JasperReport jasperReport = JasperCompileManager.compileReport(reportfile);
          JasperPrint jasperPrint = null;
          try {
              jasperPrint = JasperFillManager.fillReport(jasperReport, parametersMap, conn);
          } catch (JRException ex) {
               System.out.print(" viewType 1 "+ex.getStackTrace());
              Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
          }
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
              response.setHeader("Content-Disposition", "inline;filename=ItemProfitReports.xlsx");
              JRXlsxExporter exporter = new JRXlsxExporter();
              exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
              exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
              exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,true);
              exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,false);
              exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
              exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
              exporter.exportReport();
              
          }
        conn.close();
      } catch (ParseException ex) {
          System.out.println(" ex  "+ex.getStackTrace());
          Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
      } catch (JRException ex) {
           System.out.println(" ex  "+ex.getStackTrace());
          Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
      } catch (IOException ex) {
           System.out.println(" ex  "+ex.getStackTrace());
          Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
      } catch (SQLException ex) {
          Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
      }
      
        return null;
  }
  
  @RequestMapping(value={"GetVAAMCAReports"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ByteArrayOutputStream GetVAAMCAReports(HttpServletRequest req,HttpServletResponse response)throws ParseException, JRException, IOException
  {
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
    
    ModelAndView model = new ModelAndView("VAAMCAReports");
    String reportfile = req.getServletContext().getRealPath("//Reports//vaa_mca_reports.jrxml");

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
        response.setHeader("Content-Disposition", "inline;filename=VAA_MCA_Reports.xlsx");
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
  
  @RequestMapping(value={"ConvertToPurchase"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
public ModelAndView ConvertToPurchase(@RequestParam("eId") String id, RedirectAttributes redirect)
{
    
    if(id!=null && id.length()>0){
     Sales sale=salesService.getSalesById(id);
      List list=salesService.getSalesItemBySalesId(id);
      
      Purchase s=new Purchase();
      s.setCategory("Purchases from unregistered Persons");
      s.setMode(sale.getMode());
      List<PurchaseItem> sList=new ArrayList<PurchaseItem>();
      
      HashSet<String> set = new HashSet();
      Iterator itr=list.iterator();
      while(itr.hasNext()){
          SalesItem seItem=(SalesItem)itr.next();
          ItemMaster iMaster=itemMasterService.getItemWithHSnValue(seItem.getItemCode());
          set.add(seItem.getTax());
          PurchaseItem sItem=new PurchaseItem();
          sItem.setItemCode(seItem.getItemCode());
          sItem.setNameOfTheItem(seItem.getItemName());
          sItem.setUnit(seItem.getUnit());
          sItem.setRate(iMaster.getBasicPrice());
          sItem.setAmount(0.00);
          sItem.setEp(iMaster.getTp());
          sItem.setCgst(seItem.getCgst());
          sItem.setIgst(0.00);
          sItem.setVat(seItem.getVat());
          sItem.setTaxCgst(iMaster.getTaxCgst());
          sItem.setTaxSgst(iMaster.getTaxSgst());
          sItem.setTaxIgst(0.00);
          sItem.setTax(seItem.getTax());
          
          sList.add(sItem);
      }    
      
      
      PurchaseForm pFrom=new PurchaseForm();
      pFrom.setPurchase(s);
      pFrom.setPurchases(sList);
      
      ModelAndView model = new ModelAndView("Purchase");
      model.addObject("purchaseFrom", pFrom);
      if(companyservice.getCompanyById(1)!=null)
    {
    CompanyInformation tin= companyservice.getCompanyById(1);    
    String gstinCompany=tin.getCompanyTin().substring(0, 2);
    model.addObject("CompanyGstin", gstinCompany);  
    pFrom.getPurchase().setPos(gstinCompany);
    }
    else
    {
     model.addObject("CompanyGstin", "");      
    }
    model.addObject("CategoryAuto",s.getCategory());  
    model.addObject("supplierList", this.ss.listSupplier());
    model.addObject("unitList", this.unitService.listUnit());
    model.addObject("taxStructure", this.tServ.getTaxId(1));
      return model;
    }
    else{
        ModelAndView model = new ModelAndView("redirect:PurchaseGrid.html");
       return model;
   }

}

  @RequestMapping(value={"GetDatatablePGrid"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   
    public DatatableObject getDatatable( HttpServletRequest req)
    {
      DatatableObject db =new DatatableObject();
           String[] cols = {"invoice_no", "date", "name_of_supplier", "tin_no","mode", "category", "total_amount", "total_invoice_amount"};

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
        String sql = "SELECT p.purchase_invoice_id,p.date,p.cash_supplier,p.mode,p.category,p.total_invoice_amount,p.total_amount,'' FROM purchase AS p";
        List<Object[]> list = purchaseService.GetDatatableObject(sql);
        total=list.size();

    }catch(Exception e){
         
    }
    int totalAfterFilter = total;
    //result.put("sEcho",echo);
 
    try {
        String searchSQL = "";
        String sql = "SELECT p.purchase_invoice_id,p.date,p.cash_supplier,p.mode,p.category,p.total_invoice_amount,p.total_amount,'' FROM purchase AS p";
        String searchTerm =req.getParameter("search[value]");
         String globeSearch =  " where (purchase_invoice_id like '"+searchTerm+"%')";
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
         List<Object[]> list2 = purchaseService.GetDatatableObject(sql);
         // For Filter Count 
        String sql2 = "SELECT p.purchase_invoice_id,p.date,p.cash_supplier,p.mode,p.category,p.total_invoice_amount,p.total_amount,'' FROM purchase AS p";
       if (searchTerm != "") {
            sql2 += searchSQL;
          List<Object[]> count = purchaseService.GetDatatableCount(sql2);
          totalAfterFilter=count.size();
        }
  
   db.setiTotalRecords(total);
   db.setiTotalDisplayRecords(totalAfterFilter);
   db.setAaData(list2);
    } catch (Exception e) {
 
    }
      return db;
	}  

    @RequestMapping(value={"UnitPopup"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView UnitPopup()
  {
    ModelAndView model = new ModelAndView("UnitPopup");
    return model;
  }
  
}
