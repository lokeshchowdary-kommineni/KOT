
package com.accounting.controller;   

import com.accounting.bean.BuyerMaster;
import com.accounting.bean.CcodeMaster;
import com.accounting.bean.CompanyInformation;
import com.accounting.bean.DatatableObject;
import com.accounting.bean.DefaultSettings;
import com.accounting.bean.Entries;
import com.accounting.bean.Entryitems;
import com.accounting.bean.ItemMaster;
import com.accounting.bean.ItemReport;
import com.accounting.bean.KOTForm;
import com.accounting.bean.Kot;
import com.accounting.bean.KotItem;
import com.accounting.bean.LedgerAccountMaster;
import com.accounting.bean.TempKot;
import com.accounting.bean.PrintSetting;
import com.accounting.bean.Purchase;
import com.accounting.bean.PurchaseForm;
import com.accounting.bean.PurchaseItem;
import com.accounting.bean.Sales;
import com.accounting.bean.SalesBill;
import com.accounting.bean.SalesEstimate;
import com.accounting.bean.SalesEstimateItem;
import com.accounting.bean.SalesForm;
import com.accounting.bean.SaleInvoice;
import com.accounting.bean.SalesItem;
import com.accounting.bean.SalesReturn;
import com.accounting.bean.SalesReturnItem;
import com.accounting.bean.TaxStructure;
import com.accounting.bean.UnitMaster;
import com.accounting.dao.SalesDao;
import com.accounting.service.AccountDBOService;
import com.accounting.service.Buyer_service;
import com.accounting.service.CcodeService;
import com.accounting.service.CompanyService;
import com.accounting.service.DefaultSetting_service;
import com.accounting.service.EntryService;
import com.accounting.service.ItcReversal_service;
import com.accounting.service.ItemMasterService;
import com.accounting.service.ItemReport_service;
import com.accounting.service.KOTService;
import com.accounting.service.LedgerAccount_Service;
import com.accounting.service.LedgerBalanceService;
import com.accounting.service.PrintService;
import com.accounting.service.PurchaseService;
import com.accounting.service.SalesEstimateService;
import com.accounting.service.SalesService;
import com.accounting.service.TaxStructure_Service;
import com.accounting.service.Unit_service;
import com.accounting.util.BillVO;
import com.accounting.util.UtilHelper;
import com.accounting.validator.SalesFormValidator;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintServiceLookup;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.PrinterName;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimplePrintServiceExporterConfiguration;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.misc.Request;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.PrinterName;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.ui.Model;
import javax.servlet.ServletOutputStream;  
import java.io.ByteArrayOutputStream;  


@RestController
public class SalesController {
    
    
    @Autowired
    private SalesFormValidator salesFormValidator;
    
    @Autowired
    private KOTService kotservice;
          
    @Autowired
    private SalesService salesService;
    
    @Autowired
    private SalesEstimateService salesEstimateService;
    
    @Autowired
    private ItemMasterService itemMasterService;
   
    @Autowired
    private Buyer_service bs;
    
    @Autowired
    private Unit_service unitService;
    
    @Autowired
    private TaxStructure_Service tServ;
    
    @Autowired
    private EntryService entryService;
    
     @Autowired
    private CompanyService companyservice;
    
    @Autowired
    private LedgerAccount_Service ledgerAccountService;
    
    @Autowired
    private ItemReport_service itemReportService;
    
    @Autowired
    private CcodeService cService;
    
    @Autowired
    private LedgerBalanceService lbs;
    
     @Autowired
    private DefaultSetting_service des;
     
     @Autowired
    private AccountDBOService as;
     
     @Autowired
     private Unit_service us;
      @Autowired
     private ItcReversal_service is;
    @Autowired
    private PrintService ps;  
    
    @Autowired
    private PurchaseService purchaseService;  
    
    
     
     @Autowired
    private SessionFactory sessionFactory;
     
     DefaultSettings ds=new DefaultSettings();
    
    //Set a form validator
    @InitBinder("salesForm")
    protected void initBinder(WebDataBinder binder) {
    binder.setValidator(this.salesFormValidator);   
    }
    
    
    
    @RequestMapping("/SalesBill")  
    public ModelAndView showform(HttpServletRequest req)
    {
        return new ModelAndView("SalesBill","command",new SaleInvoice());
    }  
    
     @RequestMapping("/SalesInvoicelist")  
    public ModelAndView SalesInvoicelist(HttpServletRequest req)
    {  
         System.out.println("SalesInvoicelist controller");
         ModelAndView model = new ModelAndView("SalesInvoicelist");
         return model;
    }  
    
       @RequestMapping("/AuditSalesInvoicelist")  
    public ModelAndView AuditSalesInvoicelist(HttpServletRequest req){  
       
         return new ModelAndView("SalesAuditlist","command",new SaleInvoice());
    } 
     @RequestMapping("/RegenateBill")  
    public ModelAndView RegenateBill(HttpServletRequest req) throws ParseException{ 
        if(req.getParameter("kot_no")!=null)
        {
            String invoice=req.getParameter("invoice");
            String rgb="RGB";
            salesService.updatergb(invoice,rgb);
            String Kotnos=req.getParameter("kot_no");
            System.out.println("--------------"+Kotnos);
        String kotArr[]=req.getParameter("kot_no").split(",");
        String kotIds=req.getParameter("kot_id");
        System.out.println("--------------"+kotIds);
        String kotIdArr[]=req.getParameter("kot_id").split(",");
        List<SalesBill> list=salesService.getRegeneratedBill(kotIdArr);
        
        String orderDate="";
        String WaiterID="";
      
        if(list!=null && list.size()>0){
            //2017-10-04 12:16:51.0
             WaiterID=list.get(0).getWaiterId();
        String dt=list.get(0).getSalesdate();
         String newFormat = "dd-MM-yyyy";
    String oldFormat = "yyyy-MM-dd HH:mm:ss.s";
      
     SimpleDateFormat sdf1 = new SimpleDateFormat(oldFormat);
    SimpleDateFormat sdf2 = new SimpleDateFormat(newFormat);


    try {
        orderDate=sdf2.format(sdf1.parse(dt));
      

    } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
        }
        
       
       
        String inv=salesService.saveSalesItem(list,Kotnos,kotIds);
        SaleInvoice si=new SaleInvoice();
        si.setInvoiceNo(inv);
        salesService.saveInvoice(si);
        
         //For inserting in ItemReport 
        List<SalesBill> list1=salesService.getRegeneratedBill(kotIdArr);
             Iterator itr=list1.iterator();
                  while(itr.hasNext()){
                  SalesBill c=(SalesBill)itr.next();
                    String sbItemCode=c.getItemCode();
                    String sbDate=c.getSalesdate();
                    String sDate=sbDate.substring(0,10);
                    DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
                    Date salleDate=df.parse(sDate);
                    String sunit=c.getUnit();
                    int su=Integer.parseInt(sunit);
                    Double sbQu=c.getQuantity();
                        ItemMaster iMaster=itemMasterService.getItmeByItemNo(sbItemCode);   
                        ItemMaster iMaster_after_update=itemMasterService.getItmeByItemNo(sbItemCode);
                             ItemReport iReport=new ItemReport();
                             iReport.setItemId(iMaster_after_update.getId());
                             iReport.setItemName(iMaster_after_update.getItemName());
                             iReport.setItemGroupId(iMaster_after_update.getItemGroup());
                             iReport.setTransactionId(inv);
                             iReport.setTransactionType("Sale");
                             iReport.setTransactionDate(salleDate);
          
//          Finding unit or alt unit                 
//                             if(su==iMaster.getAltUnit())
                              if(su==1)
                              {
                               sbQu=sbQu/1000;
                                  System.out.println("sbQu if:"+sbQu);
                               iReport.setOutQuantity(sbQu);
                              }
                              else if(su==2)
                              {
                                   sbQu=(sbQu*60)/1000;
                                  System.out.println("sbQu elseif:"+sbQu);
                                  iReport.setOutQuantity(sbQu);
                              }
                             else
                              {
                                System.out.println("else");
                               iReport.setOutQuantity(sbQu);
                              }                
                            itemReportService.addItemReport(iReport);
                   }
        //code completed for inserting in item report
        
         String tableName=list.get(0).getTableName();
         String tlist=salesService.gettotalamountforrgb(kotArr);
         String talist=salesService.gettotalamountauditforrgb(kotArr);
         String toalabreveragecap=salesService.getBreveragestotal(inv);
         String additionalcharges=salesService.getadditinalcharges(tableName);
         String waitername=salesService.getWaitername(WaiterID);
         
       Double tot_cgst=UtilHelper.getTotalCGSTAmt(list);
       Double tot_sgst=UtilHelper.getTotalSGSTAmt(list);
       
       
        ModelAndView model=new ModelAndView("forward:SalesBill.html");   
        model.addObject("Tname",tableName);
        model.addObject("KOTNOS",Kotnos);
        model.addObject("orderDate",orderDate);
        model.addObject("WaiterID",waitername);
        model.addObject("tot_cgst",tot_cgst);
        model.addObject("tot_sgst",tot_sgst);
        model.addObject("invcno",inv);
        model.addObject("tbsum",toalabreveragecap);
        model.addObject("addcharges",additionalcharges);
        model.addObject("itemList",list);
        model.addObject("totalcap",tlist);
        model.addObject("totalvap",talist);
        model.addObject("command",new SalesBill());
        return model ;
        
        }
        return null;
       
    }
    
        @RequestMapping("void.html")  
        public ModelAndView voidinvoicelist(HttpServletRequest req){  
              String canclebillvalue="void";
              String invnumber=req.getParameter("invoice");
              salesService.voidbills(invnumber,canclebillvalue);
        //           DELETE FROM iTEMrEPORT
              itemReportService.deleteItemReport(invnumber);

            return new ModelAndView("redirect:/SalesInvoicelist.html");
        } 
          
        @RequestMapping("paymentmode.html")  
        public ModelAndView paymentmode(HttpServletRequest req){  
              String paymentmode="CARD";
              String invnumber=req.getParameter("invoice");
              salesService.changepayementmode(invnumber,paymentmode);
              return new ModelAndView("redirect:/SalesInvoicelist.html");
        }
                 
        @RequestMapping("paymentcashmode.html")  
        public ModelAndView paymentcashmode(HttpServletRequest req){  
                String paymentmode="CASH";
                String invnumber=req.getParameter("invoice");
                salesService.changepayementmode(invnumber,paymentmode);
                return new ModelAndView("redirect:/SalesInvoicelist.html");
        }
        
       
        @RequestMapping(value="cashandcard",method={org.springframework.web.bind.annotation.RequestMethod.GET})
        public ModelAndView cashandcard(HttpServletRequest req){
         String invnumber=req.getParameter("invoice");
            System.out.println("invnumber :"+invnumber);
         ModelAndView model = new ModelAndView("cashandcard");
         model.addObject("invnumber", invnumber);
        return model;
        }  
        
        @RequestMapping(value="paymentcashandcardmode",method={org.springframework.web.bind.annotation.RequestMethod.POST})
        public ModelAndView paymentcashandcardmode(HttpServletRequest req){  
                String cashAmount=req.getParameter("cashAmount");
                String cardAmount=req.getParameter("cardAmount");
                String paymentmode="CASH AND CARD";
                String invnumber=req.getParameter("invnumber");
                salesService.cashandcardpayementmode(invnumber,paymentmode,cashAmount,cardAmount);
                return new ModelAndView("redirect:/SalesInvoicelist.html");
        }
        
      @RequestMapping("paid.html")  
      public ModelAndView payementmode(HttpServletRequest req){  
     
        return null;
      } 
      
     @RequestMapping(value = "/searchspring", method = RequestMethod.GET)
     public String view(Model model) throws Exception {
        
        return "EmployeeForm";
     }
     
     @RequestMapping(value="saveSalesKOT",method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public ModelAndView saveSalesKOT(@ModelAttribute("kotForm")@Validated KOTForm kotForm, BindingResult result){
        
        List<KotItem> kotItemList=kotForm.getKotItem();
        
        Kot kot=kotForm.getKot();
        kot.setKotTimestamp(new Timestamp(System.currentTimeMillis()));
        KotItem kotItem=new KotItem();
        TempKot orderKot=new TempKot();
       
        String kotNo=kotservice.saveKOT(kot);
        Iterator itr=kotItemList.iterator();
        System.out.println("kot id "+kotItem.getId());
        while(itr.hasNext()){
            KotItem kItem=(KotItem)itr.next();
            if(kItem.getItemCode()!=null){
                
                kotItem.setId(kItem.getId());
                kotItem.setKotNo(kotNo);
                kotItem.setKotid(kot.getId());
                kotItem.setAuditRate(kItem.getAuditRate());
                kotItem.setCap(kItem.getCap());
                kotItem.setCgstPercent(kItem.getCgstPercent());
                kotItem.setItemCode(kItem.getItemCode());
                kotItem.setItemName(kItem.getItemName());
                kotItem.setQuantity(kItem.getQuantity());
                kotItem.setRate(kItem.getRate());
                kotItem.setSgstPercent(kItem.getSgstPercent());
                kotItem.setTaxCgst(kItem.getTaxCgst());
                kotItem.setTaxSgst(kItem.getTaxSgst());
                kotItem.setUnit(kItem.getUnit());
                kotItem.setVap(kItem.getVap());


                kotservice.saveKOTItem(kotItem);
            
            }  
        }
        return new ModelAndView("redirect:SalesInvoicelist.html");
    }
    @RequestMapping(value="SalesKOTedit",method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public ModelAndView KotFormAddRow(@ModelAttribute("kotForm")  KOTForm kotForm, HttpServletRequest req){
        
        System.out.println("came to sales KOT edit controller");
        KotItem kotItem_plus=new KotItem();
        KotItem kotItem=new KotItem();
        KOTForm kForm=kotForm;
        List<KotItem> list=kForm.getKotItem();
        List<KotItem> listNew=new ArrayList<KotItem>();
        Iterator<KotItem> i = list.iterator();
        while (i.hasNext())
        {
            kotItem = i.next();
            // this condition exclude deleted empty row of data
            if(kotItem.getItemCode()=="" || kotItem.getItemCode()==null)
            {

            }
            else
            {
              // here add existing data to new arraylist 
                System.out.println("kot unit is "+kotItem.getUnit());
              listNew.add(kotItem);
            }    
        }
         
        listNew.add(kotItem_plus);
        kForm.setKotItem(listNew);
        ModelAndView model=new ModelAndView("SalesKOTedit");
        model.addObject("kotForm",kForm);
        return model;
        
    }
   
    @RequestMapping(value="EditKOTPopUp", method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView editRunningKot(@ModelAttribute("kotForm")@Validated KOTForm kotForm, BindingResult result,HttpServletRequest req)
    {
        String kotId=req.getParameter("kotId");
        System.out.println("tid is "+kotId);
        ModelAndView model = new ModelAndView("EditKOTPopUp");
        List<KotItem> kotItemList=kotservice.listkotItem(kotId);
        List<Kot> kotList=kotservice.listkot(kotId);
        Kot kotObj=new Kot();
        Iterator itr=kotList.iterator();
        while(itr.hasNext()){
            Kot kot=(Kot)itr.next();
            kotObj.setId(kot.getId());
            kotObj.setKotNo(kot.getKotNo());
            kotObj.setTableName(kot.getTableName());
            kotObj.setWaiterId(kot.getWaiterId());
            kotObj.setKotTimestamp(kot.getKotTimestamp());
            kotObj.setTotalCgst(kot.getTotalCgst());
            kotObj.setTotalSgst(kot.getTotalSgst());
            kotObj.setTotalKotvalue(kot.getTotalKotvalue());
        }
        System.out.println("kot Item list size "+kotItemList.size());
        KOTForm kForm=new KOTForm();
        kForm.setKotItem(kotItemList);
        kForm.setKot(kotObj);
        System.out.println("kot obj Table"+kotObj.getTableName());
        model.addObject("kotForm",kForm);
        model.addObject("tableCategory",kotservice.listTableCategory());
        model.addObject("waiterId",kotservice.listWaiterId());
        return model;
    }
    @RequestMapping("Duplicateprint")  
    public ModelAndView Duplicateprint(HttpServletRequest req,HttpServletResponse res) throws IOException, JRException{  
        
            
           
            String inv=req.getParameter("invoice");
           System.out.println("inv"+inv);
            String reportfile = req.getServletContext().getRealPath("//Reports//SalesBill.jrxml");
//           
//           
            BillVO vo=salesService.printVO(inv);
         
    Session session1 = this.sessionFactory.openSession();
    SessionImpl sessionImpl = (SessionImpl)session1;
    Connection conn = sessionImpl.connection();
//            
//             UtilHelper.jasperTestPrint(reportfile,vo,conn,res);
             
             JasperReport jasperReport = JasperCompileManager.compileReport(reportfile);        
     JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,vo.getParametersMap(), conn);
       ServletOutputStream out = res.getOutputStream();  
         res.setHeader("Cache-Control", "max-age=0");  
        res.setContentType("application/pdf"); 
        res.setHeader("Content-Disposition", "inline; filename=\"Bill.pdf\"");
       ByteArrayOutputStream baos = new ByteArrayOutputStream();  
        JasperExportManager.exportReportToPdfStream(jasperPrint, baos);  
        out.write(baos.toByteArray());  
        out.flush();  
        out.close();  
         //   UtilHelper.jasperTestPrint(reportfile,vo,conn,res);
          
    return new ModelAndView("redirect:SalesInvoicelist.html");
    }
    
     @RequestMapping(value="save",method = RequestMethod.POST)  
    public ModelAndView save(@ModelAttribute("emp") SaleInvoice emp,HttpServletRequest req,HttpServletResponse res) throws IOException, JRException{  
        
            
            salesService.saveSalesinvoice(emp);
            String inv=emp.getInvoiceNo();
           String tbn=req.getParameter("tbn");
            System.out.println(tbn);
            String reportfile = req.getServletContext().getRealPath("//Reports//SalesBill.jrxml");
//           
//           
            BillVO vo=salesService.printVO(inv);
    HttpSession session = null;
    Session session1 = this.sessionFactory.openSession();
    SessionImpl sessionImpl = (SessionImpl)session1;
    Connection conn = sessionImpl.connection();
            
             UtilHelper.jasperTestPrint(reportfile,vo,conn);
            UtilHelper.jasperTestPrint(reportfile,vo,conn);
           salesService.deleteorderkot(tbn);
    return new ModelAndView("redirect:KotForm.html");
    }   
    @RequestMapping("/RePrintBill")
    public ModelAndView RePrintBill(HttpServletRequest req){  
        
        
        
       
        return new ModelAndView("RePrintBill","command",new SalesBill());
    }
    
       @RequestMapping("rePrintBill.html")  
    public ModelAndView rePrintBill(HttpServletRequest req) throws ParseException{
           String itemname=""; String qty="";String cgst="";String sgst="";String price="";String saledate="";String waitername="";String tabelname="";String kotNos="";
        
           String cCtotal="";String cgstamount="";String sgstamount="";String discount="";String grandtotal="";String roundoff="";String nettotal="";String additionalcharges="";
           
           String BillName=req.getParameter("invoice");
        
         
        System.out.println("salesItem"+BillName);
         List<SalesBill> rlist=salesService.getSalesItembybill(BillName);
        
         Iterator itr=rlist.iterator();
                  while(itr.hasNext()){
                  SalesBill cb=(SalesBill)itr.next();
                 itemname=cb.getItemName();
                      System.out.println("itemname"+itemname);
                  
                  qty=String.valueOf(cb.getQuantity());
                 cgst=String.valueOf(cb.getTaxCgst());
                 sgst=String.valueOf(cb.getTaxSgst());
                  price=String.valueOf(cb.getCap());
                  tabelname=cb.getTableName();
                 
                 
                  kotNos=cb.getKotNos();
                  }
                  List<SaleInvoice> rslist=salesService.getvalues(BillName);
          Iterator itr1=rslist.iterator();
                  while(itr1.hasNext()){
                  SaleInvoice si=(SaleInvoice)itr1.next();
                  waitername=si.getWaiterName();
                  cCtotal=String.valueOf(si.getCCtotal());
                  cgstamount=String.valueOf(si.getCgstamount());
                  sgstamount=String.valueOf(si.getSgstamount());
                  discount=String.valueOf(si.getDiscount());
                  grandtotal=String.valueOf(si.getGrandtotal());
                  roundoff=String.valueOf(si.getRoundoff());
                   nettotal=String.valueOf(si.getNettotal());
                   additionalcharges=String.valueOf(si.getAdditionalcharges());
                   saledate=si.getSdate();
                  }
    
       ModelAndView model=new ModelAndView("forward:RePrintBill.html");
        model.addObject("qty",qty);
        model.addObject("cgst",cgst);
       model.addObject("sgst",sgst);
        model.addObject("price",price);
        model.addObject("tabelname",tabelname);
       model.addObject("saledate",saledate);
         model.addObject("BillName",BillName);
         model.addObject("kotNos",kotNos);
          model.addObject("waitername",waitername);
           model.addObject("itemname",itemname);
            model.addObject("cCtotal",cCtotal);
             model.addObject("cgstamount",cgstamount);
              model.addObject("sgstamount",sgstamount);
               model.addObject("discount",discount);
                model.addObject("grandtotal",grandtotal);
                 model.addObject("roundoff",roundoff);
                  model.addObject("nettotal",nettotal);
                   model.addObject("additionalcharges",additionalcharges);
         model.addObject("rlist",rlist);
                  
      
        return model;
                  
        
    }

    @RequestMapping(value="CloseBill",method = RequestMethod.POST)  
    public ModelAndView CloseBill(HttpServletRequest req) throws ParseException{  
        
      String tableName=req.getParameter("hiddenTableName");
        System.out.println("hhhhhhhhhhhhhhhhh"+tableName);
        String orderDate="";
        String WaiterID="";
        List<SalesBill> list=salesService.getSalesItemBytableIdForBill(tableName);
        if(list!=null && list.size()>0){
            //2017-10-04 12:16:51.0
             WaiterID=list.get(0).getWaiterId();
        String dt=list.get(0).getSalesdate();
         String newFormat = "dd-MM-yyyy";
    String oldFormat = "yyyy-MM-dd HH:mm:ss.s";
      
     SimpleDateFormat sdf1 = new SimpleDateFormat(oldFormat);
    SimpleDateFormat sdf2 = new SimpleDateFormat(newFormat);


    try {
        orderDate=sdf2.format(sdf1.parse(dt));
      

    } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
        }
        
       List<TempKot> klist=salesService.getSalesKotNos(tableName);
       List<TempKot> kotIdList=salesService.getSalesKotIds(tableName);
        String idList = klist.toString();
        String Kotnos = idList.substring(1, idList.length() - 1).replace(", ", ",");
        System.out.println("hi"+klist.size()+" csv "+Kotnos);
        String kotidList = kotIdList.toString();
        String KotIds = kotidList.substring(1, kotidList.length() - 1).replace(", ", ",");
        System.out.println("hi"+kotIdList.size()+" csv "+KotIds);
        String inv=salesService.saveSalesItem(list,Kotnos,KotIds);
        SaleInvoice si=new SaleInvoice();
        si.setInvoiceNo(inv);
        
       //For inserting in ItemReport 
        List<SalesBill> list1=salesService.getSalesItemBytableIdForBill(tableName);
             Iterator itr=list1.iterator();
                  while(itr.hasNext()){
                  SalesBill c=(SalesBill)itr.next();
                    String sbItemCode=c.getItemCode();
                    String sbDate=c.getSalesdate();
                    String sDate=sbDate.substring(0,10);
                    DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
                    Date salleDate=df.parse(sDate);
                    String sunit=c.getUnit();
                    System.out.println("sunit :"+sunit);
                    int su=Integer.parseInt(sunit);
                     System.out.println("su :"+su);
                    Double sbQu=c.getQuantity();
                        ItemMaster iMaster=itemMasterService.getItmeByItemNo(sbItemCode);   
                        ItemMaster iMaster_after_update=itemMasterService.getItmeByItemNo(sbItemCode);
                             ItemReport iReport=new ItemReport();
                             iReport.setItemId(iMaster_after_update.getId());
                             iReport.setItemName(iMaster_after_update.getItemName());
                             iReport.setItemGroupId(iMaster_after_update.getItemGroup());
                             iReport.setTransactionId(inv);
                             iReport.setTransactionType("Sale");
                             iReport.setTransactionDate(salleDate);
          
//          Finding unit or alt unit                 
//                            if(su==iMaster.getAltUnit())
                              if(su==1)
                              {
                               sbQu=sbQu/1000;
                                  System.out.println("sbQu if :"+sbQu);
                               iReport.setOutQuantity(sbQu);
                              }
                              else if(su==2)
                              {
                                   sbQu=(sbQu*60)/1000;
                                  System.out.println("sbQu elseif :"+sbQu);
                                  iReport.setOutQuantity(sbQu);
                              }
                             else
                              {
                                System.out.println("else");
                               iReport.setOutQuantity(sbQu);
                              }                
                            itemReportService.addItemReport(iReport);
               System.out.println("seju");
                   }

//               CODE FOR UPDATE              
//            String type="Sale";
//            List irList= purchaseService.getItemReportByPurchaseId(inv, type ,sbItemName);
//            String Query="UPDATE item_report SET in_quantity="+sbQu+",transaction_type='"+type+"',item_name='"+sbItemName+"' WHERE transaction_id='"+inv+"' AND transaction_type='"+type+"' AND item_name='"+sbItemName+"'";
//            purchaseService.updateItemReport(Query);                
      
       salesService.saveInvoice(si);
       String tlist=salesService.gettotalamount(tableName);
         String talist=salesService.gettotalamountaudit(tableName);
         String category="1";
         String toalabreveragecap=salesService.getBreveragestotal(inv);
         String additionalcharges=salesService.getadditinalcharges(tableName);
       String waitername=salesService.getWaitername(WaiterID);
         
       Double tot_cgst=UtilHelper.getTotalCGSTAmt(list);
       Double tot_sgst=UtilHelper.getTotalSGSTAmt(list);
       
       
        ModelAndView model=new ModelAndView("forward:SalesBill.html");   
        model.addObject("Tname",tableName);
        model.addObject("KOTNOS",Kotnos);
        model.addObject("orderDate",orderDate);
         model.addObject("WaiterID",waitername);
         model.addObject("tot_cgst",tot_cgst);
          model.addObject("tot_sgst",tot_sgst);
      model.addObject("invcno",inv);
      
      model.addObject("tbsum",toalabreveragecap);
       model.addObject("addcharges",additionalcharges);
        model.addObject("itemList",list);
        model.addObject("totalcap",tlist);
         model.addObject("totalvap",talist);
        model.addObject("command",new SalesBill());
       
        return model; 
    }  
    
@RequestMapping(value={"Sales"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
public ModelAndView itemMasterPage() throws ParseException
{

        
    
    Sales s=new Sales();
    SalesItem sItem=new SalesItem();
  
    SalesForm sForm=new SalesForm();
    List<SalesItem> list=new ArrayList<SalesItem>();
    list.add(sItem);
    sForm.setSales(list);
    sForm.setSale(s);
    ModelAndView model = new ModelAndView("Sales");
    if(des.getDefaultbyId(1)!=null ){
    ds=des.getDefaultbyId(1);
    s.setCategory(ds.getSalesCategory());
    s.setMode(ds.getSalesMode());
    model.addObject("CategoryAuto", ds.getSalesCategory());
    }
    else
    {
    model.addObject("CategoryAuto", "");   
    }    
 
    model.addObject("salesForm", sForm);
    model.addObject("unitList", unitService.listUnit());
    model.addObject("taxStructure", tServ.getTaxId(1));
    model.addObject("BuyMast",bs.listBuyer());
     
    HashSet<String> set=new HashSet<String> ();
    List cCodelist=cService.listCode();
    Iterator itr=cCodelist.iterator();
    while(itr.hasNext()){
        CcodeMaster c=(CcodeMaster)itr.next();
        set.add(c.getTaxRate().toString());
    }
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date();
    String currentDate=dateFormat.format(date);
    String previousInvoiceDate=salesService.getLastInvoiceDate();
    
    if(previousInvoiceDate.equalsIgnoreCase(""))
    {
       if(companyservice.getCompanyById(1)!=null )
        {
         Date d=companyservice.getCompanyById(1).getAccountsFrom();
         DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");      
         previousInvoiceDate=formatter.format(d);
        
         }
         else
         {
           previousInvoiceDate=currentDate;  
         }
    }
    else
    {
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date convertedCurrentDate = sdf.parse(previousInvoiceDate);
     previousInvoiceDate=dateFormat.format(convertedCurrentDate );
    } 
    String CompanyGstno="";
    if(companyservice.getCompanyById(1)!=null)
    {
      CompanyGstno=companyservice.getCompanyById(1).getCompanyTin();
      CompanyGstno=CompanyGstno.substring(0, 2);
    }   
     model.addObject("currentDate", currentDate);
     model.addObject("previousInvoiceDate", previousInvoiceDate);
     model.addObject("CompanyGstno",CompanyGstno);
    model.addObject("cCodeList", set);
    
    
    return model;
    
}

@RequestMapping(value={"SaveSales"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
public ModelAndView saveItem(@ModelAttribute("salesForm") @Validated SalesForm salesForm, BindingResult result,
         RedirectAttributes redirect, HttpServletRequest request ,HttpSession ses)
{
    
    if (result.hasErrors()) {
        return new ModelAndView("Sales");
        
    }
    else{  
        DecimalFormat df = new DecimalFormat("#.##");  
                List<SalesItem> listSale=salesForm.getSales(); // Getting all rows from forms
                SalesItem  saleItem= new SalesItem();
            List<SalesItem> listNew= new ArrayList<SalesItem>(); // for add undeleted items 
            Iterator<SalesItem> i = listSale.iterator();
            // this loop used to iterate all rows of data
            while (i.hasNext())
            {
                saleItem = i.next();
                // this condition exclude deleted empty row of data
                if(saleItem.getItemCode()==null || saleItem.getItemCode().isEmpty() )
                {

                }
                else
                {
                  // here add existing data to new arraylist  
                  if(saleItem.getQuantity()!=0)
                  {    
                      System.out.println(" saleItem.getQuantity() "+saleItem.getQuantity());
                  listNew.add(saleItem);
                  }
                }    

            }
          
             salesForm.setSales(listNew); //setting all data with empty row into form
  
 
        System.out.print("Sales ##");
        Sales s=salesForm.getSale();
        String invoicNo="";
        String BuyerName="";
        int buyerLedgerId=0;
        double openingBalanceOfLedger=0;
        int  mediatorLedgerId=0;
        int salesCASHMEDLedgerId=0;
       if(s.getNameOfMediator()!=null && !s.getNameOfMediator().isEmpty()){
          mediatorLedgerId=ledgerAccountService.getLederByReferenceId(String.valueOf(s.getMediatorId()), "Buyer").iterator().next().getIdLedger();
         salesCASHMEDLedgerId=mediatorLedgerId;
        }
         double openingBalance_salesCASHMEDLedgerId=0;
        int entryId=0;
        if(s.getCategory().equalsIgnoreCase("Consumers(B2C)"))
        {
       buyerLedgerId=ledgerAccountService.getLederByReferenceId(String.valueOf(s.getBuyerId()), "Buyer").iterator().next().getIdLedger();
       BuyerName=ledgerAccountService.getLederByReferenceId(String.valueOf(s.getBuyerId()), "Buyer").iterator().next().getNameOfLedger();     
       String yearRange=as.GetYearRange();
       long previousId=as.getPreviousIdBasedStartAndEndYear("invoiceNo", "Sales", yearRange);       
        String primaryIdPE="";  
          if(previousId==0){
              List<Sales> salesCount=salesService.listSales();
              if(salesCount.size()==0)
              {
                   if(des.getDefaultbyId(1)!=null )
                   {
                    ds=des.getDefaultbyId(1);
                    long previousNumber=ds.getSalesNumber();
                    primaryIdPE=String.valueOf(previousNumber+1)+"/"+yearRange;
                   }
                   else
                   {
                    primaryIdPE=String.valueOf(0+1)+"/"+yearRange;   
                   }    
              }
              else
              {   primaryIdPE=previousId+1+"/"+yearRange; }
          }else{
               primaryIdPE=previousId+1+"/"+yearRange;
          }
           ses.setAttribute("primaryIdPE", primaryIdPE);        
      
       s.setInvoiceNo(null);
       invoicNo=salesService.saveSales(s);
       }
       
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
      double totalIgstEstimate=0;
      double invoiceAmountEstimate=0;
       
       
       List list=salesForm.getSales();
       System.out.println("list size -- 19.07.2017" +list.size());
       
        SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();        
        String CurrentDate= dmyFormat.format(date);
        
       if(s.getCategory().equalsIgnoreCase("Consumers(B2C)")){  
       
       List<Object[]> currentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(buyerLedgerId), CurrentDate);
      
      
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
       }
       
       System.out.println(" openingBalanceOfLedger "+openingBalanceOfLedger);    
       HashSet<String> set=new HashSet<String>();
       
       ArrayList<SalesItem> sales=new ArrayList<SalesItem>();
       ArrayList<SalesEstimateItem> salesEstimate=new ArrayList<SalesEstimateItem>();
       
       Iterator itr=list.iterator();
       while(itr.hasNext()){
           SalesItem sItem=(SalesItem)itr.next();
           sItem.setInvoiceNo(invoicNo);
           ItemMaster iMaster=itemMasterService.getItmeByItemNo(sItem.getItemCode());
           
            System.out.println(" getQuantity "+sItem.getQuantity());    
                 System.out.println(" getCurrentStock "+iMaster.getCurrentStock());   
           
           if(sItem.getQuantity()>(iMaster.getCurrentStock())){
                System.out.println(" getQuantity "+sItem.getQuantity());   
                 System.out.println(" getQuantity "+iMaster.getCurrentStock());   
              //for slae sestimate
              double qty=sItem.getQuantity()-(iMaster.getCurrentStock());
              // end qty sales estimate
              
              sItem.setQuantity((iMaster.getCurrentStock()));
            
              if(sItem.getDiscount()==null || sItem.getDiscount().toString().length()==0){
                  sItem.setDiscount(0.0);
              }
              double amount=(sItem.getQuantity()*sItem.getRate())-sItem.getDiscount();
       
              sItem.setAmount(amount);
              double taxValue=0.0;
              double cgst=0.0;
              double vat=0.0;
              double igst=0.0;
              double itemAssessValue=0.0;
              double itemVaa=0.0;
              double taxCgst=0.0;
              double taxSgst=0.0;
              double taxIgst=0.0;

              
              if(s.getCategory().equalsIgnoreCase("Consumers(B2C)")){
                 double taxamount=(amount*Double.parseDouble(sItem.getTax()))/(100+Double.parseDouble(sItem.getTax()));
                taxCgst=(taxamount*sItem.getTaxCgst())/(Double.parseDouble(sItem.getTax()));
                taxSgst=(taxamount*sItem.getTaxSgst())/(Double.parseDouble(sItem.getTax()));
                taxIgst=(taxamount*sItem.getTaxIgst())/(Double.parseDouble(sItem.getTax()));
                if(Double.isNaN(taxCgst))
                cgst=0.00;
                else
                cgst=taxCgst; 
                if(Double.isNaN(taxSgst))
                vat=0.00;
                else
                vat=taxSgst;
                if(Double.isNaN(taxIgst))
                igst=0.00;
                else
                igst=taxIgst;
                    
    
                itemAssessValue=amount-(cgst+vat+igst);
                 assessValue=assessValue+itemAssessValue;
              }
              if(s.getCategory().equalsIgnoreCase("GST Dealers(B2B)")){
                taxCgst=(amount*sItem.getTaxCgst())/100;
                taxSgst=(amount*sItem.getTaxSgst())/100;
                taxIgst=(amount*sItem.getTaxIgst())/100;

                cgst=taxCgst;
                vat=taxSgst;
                igst=taxIgst;
                itemAssessValue=amount;
                 assessValue=assessValue+itemAssessValue;
              }
             
            //  totalVat=totalVat+vat;
            //  totalCgst=totalCgst+cgst;

              sItem.setCgst(Double.valueOf(df.format(cgst)));
              sItem.setVat(Double.valueOf(df.format(vat)));
              sItem.setIgst(Double.valueOf(df.format(igst)));
              sItem.setTax(iMaster.getTr().toString());

              Double cpCpvAmount=sItem.getQuantity()*iMaster.getCp();
              itemVaa=itemAssessValue-cpCpvAmount;
            //  vaa=vaa+itemVaa;
              sItem.setCpcpvAmount(String.valueOf(cpCpvAmount));
              sItem.setWithoutTax(itemAssessValue);

              double mcMca=0;
              if(iMaster.getMc()!=null && iMaster.getMc().toString().length()>0){
                  mcMca=iMaster.getMc();
              }
              if(iMaster.getMca()!=null && iMaster.getMca().toString().length()>0){
                  mcMca=iMaster.getMca();
              }
              sItem.setMcmca(String.valueOf(mcMca));
              Double mcMcaAmount=sItem.getQuantity()*mcMca;
           //   mca=mca+mcMcaAmount;
              sItem.setMcmcaAmount(String.valueOf(mcMcaAmount));              
              invoiceAmount=invoiceAmount+itemAssessValue+vat+cgst+igst;
              
               System.out.println("Direct Invoice :"+invoiceAmount);
              if(sItem.getQuantity()!=0)
              {  
              sales.add(sItem);
              }
              // chandra
              //Convert to sales item estimate item
              SalesEstimateItem sei=new SalesEstimateItem();
              sei.setItemCode(sItem.getItemCode());
              sei.setItemName(sItem.getItemName());
             
              
              sei.setQuantity(qty);
              sei.setUnit(sItem.getUnit());
              sei.setMargin(sItem.getMargin());
              sei.setRate(sItem.getRate());
                if(s.getCategory().equalsIgnoreCase("Consumers(B2C)")){
                    double cp=iMaster.getCp();
                    double tax=iMaster.getTr();
                    double taxValue2=cp*tax/100;   
                    double cp_tax = Double.valueOf(df.format(taxValue2+cp));
                    sei.setCpcpV(Double.valueOf(df.format(cp_tax)));
                }
                else if(s.getCategory().equalsIgnoreCase("GST Dealers(B2B)"))
                {
                 double cp=iMaster.getCp();
                 sei.setCpcpV(cp);    
                }
              double amountEstimate=qty*sItem.getRate();
              sei.setAmount(amountEstimate);
              
              
      
            if(s.getCategory().equalsIgnoreCase("Consumers(B2C)")){
                double taxamount=(amountEstimate*Double.parseDouble(sItem.getTax()))/(100+Double.parseDouble(sItem.getTax()));
                taxCgst=(taxamount*sItem.getTaxCgst())/(Double.parseDouble(sItem.getTax()));
                taxSgst=(taxamount*sItem.getTaxSgst())/(Double.parseDouble(sItem.getTax()));
                taxIgst=(taxamount*sItem.getTaxIgst())/(Double.parseDouble(sItem.getTax()));
                if(Double.isNaN(taxCgst))
                 cgst=0.00;
                 else
                 cgst=taxCgst; 
                 if(Double.isNaN(taxSgst))
                 vat=0.00;
                 else
                 vat=taxSgst;
                 if(Double.isNaN(taxIgst))
                 igst=0.00;
                 else
                 igst=taxIgst;
                itemAssessValue=amountEstimate-(cgst+vat+igst);
                 assessValue=assessValue+itemAssessValue;
              }
              if(s.getCategory().equalsIgnoreCase("GST Dealers(B2B)")){
                taxCgst=(amountEstimate*sItem.getTaxCgst())/100;
                taxSgst=(amountEstimate*sItem.getTaxSgst())/100;
                taxIgst=(amountEstimate*sItem.getTaxIgst())/100;

                cgst=taxCgst;
                vat=taxSgst;
                igst=taxIgst;
                itemAssessValue=amountEstimate;
                 assessValue=assessValue+itemAssessValue;
              }
              sei.setVat(Double.valueOf(df.format(vat)));
              sei.setCgst(Double.valueOf(df.format(cgst)));
              sei.setIgst(Double.valueOf(df.format(igst)));
              double cpCpvAmountEstimate=qty*iMaster.getCp();
              sei.setCpcpvAmount(String.valueOf(cpCpvAmountEstimate));
              sei.setMcmca(sItem.getMcmca());
              double mcMcaAmountEstimate=qty*Double.parseDouble(sItem.getMcmca());
              sei.setMcmcaAmount(String.valueOf(mcMcaAmountEstimate));
              sei.setTax(sItem.getTax());
              
              sei.setTaxCgst(sItem.getTaxCgst());
              sei.setTaxSgst(sItem.getTaxSgst());
              sei.setTaxIgst(sItem.getTaxIgst());
              
              salesEstimate.add(sei);
              
              vaaEstimate=vaaEstimate+(itemAssessValue-cpCpvAmountEstimate);
              mcaEstimate=mcaEstimate+mcMcaAmountEstimate;
              assessValueEstimate=assessValueEstimate+itemAssessValue;
              totalVatEstimate=totalVatEstimate+vat;
              totalCgstEstimate=totalCgstEstimate+cgst;
              totalIgstEstimate=totalIgstEstimate+igst;
              invoiceAmountEstimate=invoiceAmountEstimate+itemAssessValue+vat+cgst+igst;
              if(sItem.getQuantity()!=0)
              {
                 vaa=vaa+itemVaa;
                 assessValue=assessValue+itemAssessValue;
                 mca=mca+mcMcaAmount;
                 invoiceAmount=invoiceAmount+itemAssessValue+vat+cgst+igst;
                 totalVat=totalVat+vat;
                 totalCgst=totalCgst+cgst;
                 totalIgst=totalIgst+igst;
              }   
                 
           }
           else{
               if(sItem.getQuantity()!=0)
              {
             double ItemAvalue=0.0;
              totalVat=totalVat+sItem.getVat();
              totalCgst=totalCgst+sItem.getCgst();
              totalIgst=totalIgst+sItem.getIgst();
              
              if(s.getCategory().equalsIgnoreCase("Consumers(B2C)")){
               vaa=vaa+((sItem.getWithoutTax())-(Double.parseDouble(sItem.getCpcpvAmount())));
               ItemAvalue=(sItem.getAmount()-sItem.getVat()-sItem.getCgst()-sItem.getIgst());
                assessValue=assessValue+ItemAvalue;
              }
              if(s.getCategory().equalsIgnoreCase("GST Dealers(B2B)")){
               vaa=vaa+((sItem.getWithoutTax())-(Double.parseDouble(sItem.getCpcpvAmount()))); 
               ItemAvalue=(sItem.getAmount());
                assessValue=assessValue+ItemAvalue;
              }
              
              mca=mca+Double.parseDouble(sItem.getMcmcaAmount());
              invoiceAmount=invoiceAmount+ItemAvalue+sItem.getVat()+sItem.getCgst()+sItem.getIgst();             
              sales.add(sItem);
              }
           }
           System.out.println("sales itemsize :"+sales.size());
           // Sales to Consume means Insert Sales Items
           // if sales Qty 0 means dont insert records
            if(s.getCategory().equalsIgnoreCase("Consumers(B2C)") && sales.size()>0)
            {
           salesService.saveSalesItem(sItem);
           double qty=0.0;
           if(sItem.getUnit()==iMaster.getAltUnit())
           {
           qty=sItem.getQuantity()/(Double.parseDouble(iMaster.getTotalUnit()));
           String query="UPDATE `item_master` SET current_stock=current_stock-"+qty+" WHERE item_code='"+sItem.getItemCode()+"'";
           itemMasterService.updateItemMaster(query);
           }
           else
           {
           String query="UPDATE `item_master` SET current_stock=current_stock-"+sItem.getQuantity()+" WHERE item_code='"+sItem.getItemCode()+"'";
           itemMasterService.updateItemMaster(query);
           }    
           
           
           ItemMaster iMaster_after_update=itemMasterService.getItmeByItemNo(sItem.getItemCode());
           ItemReport iReport=new ItemReport();
           iReport.setItemId(iMaster_after_update.getId());
           iReport.setItemName(iMaster_after_update.getItemName());
           iReport.setItemGroupId(iMaster_after_update.getItemGroup());
           iReport.setTransactionId((invoicNo));
           iReport.setTransactionName(BuyerName);
           iReport.setTransactionType("Sales");
           iReport.setTransactionDate(s.getDate());
            if(sItem.getUnit()==iMaster.getAltUnit())
            {
             qty=qty/(Double.parseDouble(iMaster.getTotalUnit()));   
             iReport.setOutQuantity(qty);
             iReport.setOutValue(((qty)*iMaster_after_update.getCp()));
            }
            else
            {
            iReport.setOutQuantity((double) sItem.getQuantity());
            iReport.setOutValue((((double)sItem.getQuantity())*iMaster_after_update.getCp()));   
            }    
          
           iReport.setClosingQuantity(iMaster_after_update.getCurrentStock());
           iReport.setClosingValue((iMaster_after_update.getCurrentStock()*iMaster_after_update.getCp()));

           itemReportService.addItemReport(iReport);
             }
           //find same tax item
           set.add(iMaster.getTr().toString());
           
       }
       System.out.println("sales itemsize outer :"+sales.size());
       // update sales after out of stock calculation
       // For item out of stock there is no need to create sales and its entries following if condtion 
    if(sales.size()>0)   
    {    
        if(s.getCategory().equalsIgnoreCase("Consumers(B2C)"))
        {
        s.setInvoiceNo(invoicNo);
        s.setVaa(vaa);
        if(s.getCategory().equalsIgnoreCase("Consumers(B2C)")){
          s.setAssessValue(assessValue);
        }
        if(s.getCategory().equalsIgnoreCase("GST Dealers(B2B)")){
           s.setAssessValue(assessValue);
        }
        s.setTotalVat(totalVat);
        s.setTotalCgst(totalCgst);
        s.setTotalIgst(totalIgst);
        s.setMca(mca);
        if(s.getAddLess()==null || s.getAddLess().toString().length()==0){
           s.setAddLess(0.0);
        }
        invoiceAmount=invoiceAmount+s.getAddLess();
        System.out.println(" s.setAddLess  "+ s.getAddLess()+"/ "+s.getActualMca()+"/ "+s.getInvoiceAmount());
        s.setInvoiceAmount(invoiceAmount);
        
         // For voucher closing Balance and type
                Double BuyerVoucherBalance_Consumer=openingBalanceOfLedger+invoiceAmount;
                if(BuyerVoucherBalance_Consumer>0) 
                {
                    s.setBuyerBalance(BuyerVoucherBalance_Consumer);
                    s.setBuyerType("DR");
                }
                else
                {
                    s.setBuyerBalance(Math.abs(BuyerVoucherBalance_Consumer));
                    s.setBuyerType("CR");
                }    
         // for getting mediator balance
          if(s.getNameOfMediator()!=null && !s.getNameOfMediator().isEmpty()){
        List<Object[]> salesCASHMEDLedgerIdcurrentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(salesCASHMEDLedgerId), CurrentDate);
         
            if( salesCASHMEDLedgerIdcurrentBalance!=null && salesCASHMEDLedgerIdcurrentBalance.size()>0)
           {
                 for (Object[] column : salesCASHMEDLedgerIdcurrentBalance) {
                            openingBalance_salesCASHMEDLedgerId = (Double)column[0]- (Double)column[1];
                    }

           }
           else
           {
               List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(salesCASHMEDLedgerId));
               for (Object[] column : OpeningBalance) {             
                            openingBalance_salesCASHMEDLedgerId = (Double)column[0]- (Double)column[1];
                    }
           }
        
         Double MediatorVoucherBalance_Consumer=openingBalance_salesCASHMEDLedgerId-s.getActualMca();
                if(MediatorVoucherBalance_Consumer>0) 
                {
                    s.setMediatorBalance(MediatorVoucherBalance_Consumer);
                    s.setMediatorType("DR");
                }
                else
                {
                    s.setMediatorBalance(Math.abs(MediatorVoucherBalance_Consumer));
                    s.setMediatorType("CR");
                }        
          }
        System.out.println(" BuyerVoucherBalance  BuyerVoucherBalance "+BuyerVoucherBalance_Consumer);
        salesService.saveSales(s);
         }
      
       
      if(s.getCategory().equalsIgnoreCase("Consumers(B2C)"))
      {
       Entries e=new Entries();
       e.setEntryType("S");
       e.setDate(s.getDate());
       e.setCrTotal(invoiceAmount);
       e.setDrTotal(invoiceAmount);
       e.setBillType("Sales");
       e.setBillId(invoicNo);       
       entryId=entryService.saveEntry(e);
       
       Entryitems eItem=new Entryitems();
       Double currentBuyerBalance=openingBalanceOfLedger+invoiceAmount;
       eItem.setEntryId(entryId);
       eItem.setAmount(invoiceAmount);
       eItem.setLedgerId(buyerLedgerId);
       eItem.setType("D");
       if(currentBuyerBalance>0)
       eItem.setClosingAmtDr(currentBuyerBalance);
       else
       eItem.setClosingAmtCr( Math.abs(currentBuyerBalance));
      
       entryService.saveEntryItem(eItem);
      }
       Iterator itrSet=set.iterator();
       
        double totalVatEntry=0;
        double totalCgstEntry=0;
        double totalIgstEntry=0;
        double totalAllVatEntry=0;
        double totalAllCgstEntry=0;
        double totalAllIgstEntry=0;
        double totalMCAEntry=0;
       
      
        
       while(itrSet.hasNext()){
        double tax=Double.parseDouble(itrSet.next().toString());
        double assessValueEntry=0;
        double vatDealer_Vat=0.0;
        double cgstDealer_vat=0.0;
        double igstDealer_vat=0.0;
        double vat_VAA=0.0;
        double vat_MCA=0.0;
        double vat_invoice_amt=0.0;
        double addorLestfromInvoiceAmt=0.0;
        double rounding_invoice_amt=0.0;
        
        //Create tax wise Invoice for Vat Dealer 
            if(s.getCategory().equalsIgnoreCase("GST Dealers(B2B)"))
           {
          buyerLedgerId=ledgerAccountService.getLederByReferenceId(String.valueOf(s.getBuyerId()), "Buyer").iterator().next().getIdLedger();
          BuyerName=ledgerAccountService.getLederByReferenceId(String.valueOf(s.getBuyerId()), "Buyer").iterator().next().getNameOfLedger();     
          String yearRange=as.GetYearRange();
          long previousId=as.getPreviousIdBasedStartAndEndYear("invoiceNo", "Sales", yearRange);       
           String primaryIdPE="";  
             if(previousId==0){
                List salesCount=salesService.listSales();
              if(salesCount.size()==0)
              {
                   if(des.getDefaultbyId(1)!=null )
                   {
                    ds=des.getDefaultbyId(1);
                    long previousNumber=ds.getSalesNumber();
                  primaryIdPE=String.valueOf(previousNumber+1)+"/"+yearRange;
                   }
              }
              else
              {   primaryIdPE=previousId+1+"/"+yearRange; }
             }else{
                  primaryIdPE=previousId+1+"/"+yearRange;
             }
              ses.setAttribute("primaryIdPE", primaryIdPE);        

          s.setInvoiceNo(null);
          
          invoicNo=salesService.saveSales(s);
          }
            
            
           Iterator iter=sales.iterator();
           while(iter.hasNext()){
              SalesItem si=(SalesItem)iter.next();
              
              if(tax==Double.parseDouble(si.getTax())){
                  
              
                 // assessValueEntry=assessValueEntry+si.getAmount();
                  totalVatEntry=totalVatEntry+si.getVat();
                  totalAllVatEntry=s.getTotalVat();
                  totalAllCgstEntry=s.getTotalCgst();
                  totalCgstEntry=totalCgstEntry+si.getCgst();
                  totalAllIgstEntry=s.getTotalIgst();
                  totalIgstEntry=totalIgstEntry+si.getIgst();
                  
                  if(s.getCategory().equalsIgnoreCase("Consumers(B2C)")){
                      assessValueEntry=assessValueEntry+(si.getAmount()-si.getVat()-si.getCgst());
                    }
                    if(s.getCategory().equalsIgnoreCase("GST Dealers(B2B)")){
                         assessValueEntry=assessValueEntry+(si.getAmount());
                         vatDealer_Vat=vatDealer_Vat+si.getVat();
                         cgstDealer_vat=cgstDealer_vat+si.getCgst();  
                         igstDealer_vat=igstDealer_vat+si.getIgst(); 
                         System.out.println(" assessValueEntry "+assessValueEntry+"/si.getCpcpvAmount() "+si.getCpcpvAmount()+"/");
                         vat_VAA=vat_VAA+(si.getAmount()-Double.parseDouble(si.getCpcpvAmount()));
                         vat_MCA=vat_MCA+Double.parseDouble(si.getMcmcaAmount());
                         vat_invoice_amt=vat_invoice_amt+(si.getAmount()+si.getVat()+si.getCgst()+si.getIgst());
                         // Vat Dealer Incoice Item list
                         si.setInvoiceNo(invoicNo);                         
                         salesService.saveSalesItem(si);
                            // Update Item_report
                         ItemMaster iMaster=itemMasterService.getItmeByItemNo(si.getItemCode());   
                            if(si.getUnit()==iMaster.getAltUnit())
                            {
                             double qty=0.0;    
                             qty =si.getQuantity()/(Double.parseDouble(iMaster.getTotalUnit()));
                             String query="UPDATE `item_master` SET current_stock=current_stock-"+qty+" WHERE item_code='"+si.getItemCode()+"'";
                             itemMasterService.updateItemMaster(query);  
                            }
                            else
                            {
                            String query="UPDATE `item_master` SET current_stock=current_stock-"+si.getQuantity()+" WHERE item_code='"+si.getItemCode()+"'";
                            itemMasterService.updateItemMaster(query);  
                            }    
                           

                            ItemMaster iMaster_after_update=itemMasterService.getItmeByItemNo(si.getItemCode());
                            ItemReport iReport=new ItemReport();
                            iReport.setItemId(iMaster_after_update.getId());
                            iReport.setItemName(iMaster_after_update.getItemName());
                            iReport.setItemGroupId(iMaster_after_update.getItemGroup());
                            iReport.setTransactionId((invoicNo));
                            iReport.setTransactionName(BuyerName);
                            iReport.setTransactionType("Sales");
                            iReport.setTransactionDate(s.getDate());
                            if(si.getUnit()==iMaster.getAltUnit())
                            {
                             double qty=0.0;    
                             qty =si.getQuantity()/(Double.parseDouble(iMaster.getTotalUnit()));
                             iReport.setOutQuantity(qty);
                             iReport.setOutValue(((qty)*iMaster_after_update.getCp()));
                            }
                             else
                             {
                            iReport.setOutQuantity((double) si.getQuantity());
                            iReport.setOutValue((((double)si.getQuantity())*iMaster_after_update.getCp()));  
                             }    
                            
                            iReport.setClosingQuantity((iMaster_after_update.getCurrentStock()));
                            iReport.setClosingValue(iMaster_after_update.getCurrentStock()*iMaster_after_update.getCp());

                            itemReportService.addItemReport(iReport);
                         
                    }
                   
              }
           }
           // update sales to vat dealer bill vat,mca,cgst,vaa
            if(s.getCategory().equalsIgnoreCase("GST Dealers(B2B)"))
                {
                 double vat_buyer_balance=0.0;
                List<Object[]> currentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(buyerLedgerId), CurrentDate);
                if( currentBalance!=null && currentBalance.size()>0)
                {
                      for (Object[] column : currentBalance) {
                                 vat_buyer_balance = (Double)column[0]- (Double)column[1];
                         }

                }
                else
                {
                    List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(buyerLedgerId));
                    for (Object[] column : OpeningBalance) {
                                 vat_buyer_balance = (Double)column[0]- (Double)column[1];
                         }
                } 
                
               
                s.setInvoiceNo(invoicNo);
                s.setVaa(vat_VAA);               
                if(s.getCategory().equalsIgnoreCase("GST Dealers(B2B)")){
                   s.setAssessValue(assessValueEntry);
                }
                s.setTotalVat(vatDealer_Vat);
                s.setTotalCgst(cgstDealer_vat);
                s.setTotalCgst(igstDealer_vat);
                s.setMca(vat_MCA);
                int tax_set_Size=set.size();
                rounding_invoice_amt=Math.round(vat_invoice_amt)-vat_invoice_amt;
                
//                if(s.getAddLess()==null || s.getAddLess().toString().length()==0){
//                   s.setAddLess(0.0);
//                }
                    
                if(tax_set_Size==1)
                {  
                addorLestfromInvoiceAmt=vat_invoice_amt+s.getAddLess();    
                s.setAddLess(s.getAddLess());
                s.setInvoiceAmount(addorLestfromInvoiceAmt);         
             //       System.out.println(" 1 vaa "+vat_VAA+"/ vat_invoice_amt "+vat_invoice_amt+"/ addorLestfromInvoiceAmt "+addorLestfromInvoiceAmt+"/ s.getAddLess() "+s.getAddLess());
                }
                else
                {
                 addorLestfromInvoiceAmt=Math.round(vat_invoice_amt);   
                 s.setAddLess(rounding_invoice_amt);   
                 s.setInvoiceAmount(addorLestfromInvoiceAmt);
             //      System.out.println(" 2 vaa "+vat_VAA+"/ vat_invoice_amt "+vat_invoice_amt+"/ addorLestfromInvoiceAmt "+addorLestfromInvoiceAmt);
                }    
               
                 // For voucher closing Balance and type
                Double BuyerVoucherBalance_dealer=vat_buyer_balance+addorLestfromInvoiceAmt;
                if(BuyerVoucherBalance_dealer>0) 
                {
                    s.setBuyerBalance(BuyerVoucherBalance_dealer);
                    s.setBuyerType("DR");
                         }
                else
                {
                    s.setBuyerBalance(Math.abs(BuyerVoucherBalance_dealer));
                    s.setBuyerType("CR");
                         }
                 
                salesService.saveSales(s);
                // Calculate Current Balance of Buyer for each tax Items
               
                 
                     Entries e=new Entries();
                     e.setEntryType("S");
                     e.setDate(s.getDate());
                     e.setCrTotal(addorLestfromInvoiceAmt);
                     e.setDrTotal(addorLestfromInvoiceAmt);
                     e.setBillType("Sales");
                     e.setBillId(invoicNo);       
                     entryId=entryService.saveEntry(e);

                     Entryitems eItem=new Entryitems();
                     Double currentBuyerBalance=vat_buyer_balance+addorLestfromInvoiceAmt;
                     eItem.setEntryId(entryId);
                     eItem.setAmount(addorLestfromInvoiceAmt);
                     eItem.setLedgerId(buyerLedgerId);
                     eItem.setType("D");
                     if(currentBuyerBalance>0)
                     eItem.setClosingAmtDr(currentBuyerBalance);
                     else
                     eItem.setClosingAmtCr( Math.abs(currentBuyerBalance));

                     entryService.saveEntryItem(eItem);
               }
            
          
           
            if(tax==0 || tax==0.0)
            {
                 System.out.println("assessValueEntry  "+tax+"/"+assessValueEntry);
                 if(s.getCategory().equalsIgnoreCase("Consumers(B2C)"))
                 {
                  int salesLedgerId=18;
                  double openingBalancesalesLedgerId=0;        
                  List<Object[]> salesLedgerIdcurrentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(salesLedgerId), CurrentDate);
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
                  Double currentsalesLedgerIdBalance=openingBalancesalesLedgerId-assessValueEntry;
                  Entryitems eItem1=new Entryitems();
                  eItem1.setEntryId(entryId);
                  eItem1.setAmount(assessValueEntry);
                  eItem1.setLedgerId(salesLedgerId);
                  eItem1.setType("C");
                  if(currentsalesLedgerIdBalance>=0)
                  eItem1.setClosingAmtDr(currentsalesLedgerIdBalance);
                  else
                  eItem1.setClosingAmtCr( Math.abs(currentsalesLedgerIdBalance));
                  entryService.saveEntryItem(eItem1);   
                 }
                 // for Vat Dealer Access values Entry bill wise
                 else
                 {
                  int salesLedgerId=18;
                  double openingBalancesalesLedgerId=0;        
                  List<Object[]> salesLedgerIdcurrentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(salesLedgerId), CurrentDate);
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
                  Double currentsalesLedgerIdBalance=openingBalancesalesLedgerId-assessValueEntry;
                  Entryitems eItem1=new Entryitems();
                  eItem1.setEntryId(entryId);
                  eItem1.setAmount(assessValueEntry);
                  eItem1.setLedgerId(salesLedgerId);
                  eItem1.setType("C");
                  if(currentsalesLedgerIdBalance>=0)
                  eItem1.setClosingAmtDr(currentsalesLedgerIdBalance);
                  else
                  eItem1.setClosingAmtCr( Math.abs(currentsalesLedgerIdBalance));
                  entryService.saveEntryItem(eItem1);  
                 }    
            }
            else
            { 
                 System.out.println("assessValueEntry  "+tax+"/"+assessValueEntry);
                if(s.getCategory().equalsIgnoreCase("Consumers(B2C)"))
                {
                int salesLedgerId=ledgerAccountService.getLedgerByLedgerName("Sales @"+tax+"% GST").iterator().next().getIdLedger();
                double openingBalancesalesLedgerId=0;        
                List<Object[]> salesLedgerIdcurrentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(salesLedgerId), CurrentDate);
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
                Double currentsalesLedgerIdBalance=openingBalancesalesLedgerId-assessValueEntry;
                Entryitems eItem1=new Entryitems();
                eItem1.setEntryId(entryId);
                eItem1.setAmount(assessValueEntry);
                eItem1.setLedgerId(salesLedgerId);
                eItem1.setType("C");
                if(currentsalesLedgerIdBalance>=0)
                eItem1.setClosingAmtDr(currentsalesLedgerIdBalance);
                else
                eItem1.setClosingAmtCr( Math.abs(currentsalesLedgerIdBalance));
                entryService.saveEntryItem(eItem1);
                }
                // for Vat dealer access value entries
                else
                {
                  int salesLedgerId=ledgerAccountService.getLedgerByLedgerName("Sales @"+tax+"% GST").iterator().next().getIdLedger();
                double openingBalancesalesLedgerId=0;        
                List<Object[]> salesLedgerIdcurrentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(salesLedgerId), CurrentDate);
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
                Double currentsalesLedgerIdBalance=openingBalancesalesLedgerId-assessValueEntry;
                Entryitems eItem1=new Entryitems();
                eItem1.setEntryId(entryId);
                eItem1.setAmount(assessValueEntry);
                eItem1.setLedgerId(salesLedgerId);
                eItem1.setType("C");
                if(currentsalesLedgerIdBalance>=0)
                eItem1.setClosingAmtDr(currentsalesLedgerIdBalance);
                else
                eItem1.setClosingAmtCr( Math.abs(currentsalesLedgerIdBalance));
                entryService.saveEntryItem(eItem1);
                }
            }
            
            // put All enteies cgst and vat and add or less entries for Vat Dealer
            if(s.getCategory().equalsIgnoreCase("GST Dealers(B2B)"))
                {
                 int salesVatLedgerId=12;
                 int salesCgstLedgerId=11; 
                 int salesIgstLedgerId=14;
                 List<Object[]> salesVatLedgerIdcurrentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(salesVatLedgerId), CurrentDate);
                 List<Object[]> salesCgstLedgerIdcurrentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(salesCgstLedgerId), CurrentDate);
                 List<Object[]> salesIgstLedgerIdcurrentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(salesIgstLedgerId), CurrentDate);
                 double openingBalance_salesVatLedgerId=0;
                 double openingBalance_salesCgstLedgerId=0;
                 double openingBalance_salesIgstLedgerId=0;
              

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
                      for (Object[] column : salesVatLedgerIdcurrentBalance) {
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
                   // // For Sales Vat  Ledger Entries  vatDealer_Vat cgstDealer_vat
                    Double currentVatLedgerIdBalance=openingBalance_salesVatLedgerId-vatDealer_Vat;
                    Entryitems eItem2=new Entryitems();
                    eItem2.setEntryId(entryId);
                    eItem2.setAmount(vatDealer_Vat);
                    eItem2.setLedgerId(salesVatLedgerId);
                    eItem2.setType("C");
                    if(currentVatLedgerIdBalance>=0)
                    eItem2.setClosingAmtDr(currentVatLedgerIdBalance);
                    else
                    eItem2.setClosingAmtCr( Math.abs(currentVatLedgerIdBalance));

                    entryService.saveEntryItem(eItem2);

                    // For Sales CGST Entries

                    Double currentCgstLedgerIdBalance=openingBalance_salesCgstLedgerId-cgstDealer_vat;
                    Entryitems eItem3=new Entryitems();
                    eItem3.setEntryId(entryId);
                    eItem3.setAmount(cgstDealer_vat);
                    eItem3.setLedgerId(salesCgstLedgerId);
                    eItem3.setType("C");
                    if(currentCgstLedgerIdBalance>=0)
                    eItem3.setClosingAmtDr(currentCgstLedgerIdBalance);
                    else
                    eItem3.setClosingAmtCr( Math.abs(currentCgstLedgerIdBalance));
                    entryService.saveEntryItem(eItem3); 
                    
                    // For Sales IGST Entries
                    Double currentIgstLedgerIdBalance=openingBalance_salesIgstLedgerId-igstDealer_vat;
                    Entryitems eItem4=new Entryitems();
                    eItem4.setEntryId(entryId);
                    eItem4.setAmount(igstDealer_vat);
                    eItem4.setLedgerId(salesIgstLedgerId);
                    eItem4.setType("C");
                    if(currentIgstLedgerIdBalance>=0)
                    eItem4.setClosingAmtDr(currentIgstLedgerIdBalance);
                    else
                    eItem4.setClosingAmtCr( Math.abs(currentIgstLedgerIdBalance));
                    entryService.saveEntryItem(eItem4); 
                    
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
                        
                        Double currentSalesRounding=openingBalanceSalesRounding-rounding_invoice_amt;
                        Entryitems eItem5=new Entryitems();
                        eItem5.setEntryId(entryId);
                        eItem5.setAmount(rounding_invoice_amt);
                        eItem5.setLedgerId(sales_rounding_Ledger_id);
                        eItem5.setType("C");
                        if(currentSalesRounding>=0)
                        eItem5.setClosingAmtDr(currentSalesRounding);
                        else
                        eItem5.setClosingAmtCr( Math.abs(currentSalesRounding));
                        entryService.saveEntryItem(eItem5);
                        
                    
                }
            
       }    
       
       if(s.getCategory().equalsIgnoreCase("Consumers(B2C)"))
       {
        int salesVatLedgerId=12;
        int salesCgstLedgerId=11;
        int salesIgstLedgerId=14;
        int salesMCALedgerId=16;
       
        List<Object[]> salesVatLedgerIdcurrentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(salesVatLedgerId), CurrentDate);
        List<Object[]> salesCgstLedgerIdcurrentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(salesCgstLedgerId), CurrentDate);
        List<Object[]> salesIgstLedgerIdcurrentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(salesIgstLedgerId), CurrentDate);
        List<Object[]> salesMCALedgerIdcurrentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(salesMCALedgerId), CurrentDate);
      
        
       double openingBalance_salesVatLedgerId=0;
       double openingBalance_salesCgstLedgerId=0;
       double openingBalance_salesIgstLedgerId=0;
       double openingBalance_salesMCALedgerId=0;
        
        
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
       // for Igst Balance
       if(salesIgstLedgerIdcurrentBalance!=null && salesIgstLedgerIdcurrentBalance.size()>0)
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
       
       if( salesMCALedgerIdcurrentBalance!=null && salesMCALedgerIdcurrentBalance.size()>0)
       {
             for (Object[] column : salesMCALedgerIdcurrentBalance) {
                        openingBalance_salesMCALedgerId = (Double)column[0]- (Double)column[1];
                }
           
       }
       else
       {
           List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(salesMCALedgerId));
           for (Object[] column : OpeningBalance) {
                        openingBalance_salesMCALedgerId = (Double)column[0]- (Double)column[1];
                }
       } 
       
       
           
         
            

          // // For Sales Vat  Ledger Entries  
           Double currentVatLedgerIdBalance=openingBalance_salesVatLedgerId-totalVatEntry;
           Entryitems eItem2=new Entryitems();
           eItem2.setEntryId(entryId);
           eItem2.setAmount(totalVatEntry);
           eItem2.setLedgerId(salesVatLedgerId);
           eItem2.setType("C");
           if(currentVatLedgerIdBalance>=0)
           eItem2.setClosingAmtDr(currentVatLedgerIdBalance);
           else
           eItem2.setClosingAmtCr( Math.abs(currentVatLedgerIdBalance));
         
           entryService.saveEntryItem(eItem2);
           
           // For Sales CGST Entries
      
           Double currentCgstLedgerIdBalance=openingBalance_salesCgstLedgerId-totalCgstEntry;
           Entryitems eItem3=new Entryitems();
           eItem3.setEntryId(entryId);
           eItem3.setAmount(totalCgstEntry);
           eItem3.setLedgerId(salesCgstLedgerId);
           eItem3.setType("C");
           if(currentCgstLedgerIdBalance>=0)
           eItem3.setClosingAmtDr(currentCgstLedgerIdBalance);
           else
           eItem3.setClosingAmtCr( Math.abs(currentCgstLedgerIdBalance));
           entryService.saveEntryItem(eItem3);   
           
           // for Igst
           Double currentIgstLedgerIdBalance=openingBalance_salesIgstLedgerId-totalIgstEntry;
           Entryitems eItem4=new Entryitems();
           eItem4.setEntryId(entryId);
           eItem4.setAmount(totalIgstEntry);
           eItem4.setLedgerId(salesIgstLedgerId);
           eItem4.setType("C");
           if(currentIgstLedgerIdBalance>=0)
           eItem4.setClosingAmtDr(currentIgstLedgerIdBalance);
           else
           eItem4.setClosingAmtCr( Math.abs(currentIgstLedgerIdBalance));
           entryService.saveEntryItem(eItem4);   
           
           Double currentMCALedgerIdBalance=openingBalance_salesMCALedgerId+s.getActualMca();
           Entryitems eItem6=new Entryitems();
           eItem6.setEntryId(entryId);
           eItem6.setAmount(s.getActualMca());
           eItem6.setLedgerId(salesMCALedgerId);
           eItem6.setType("D");      
           if(currentMCALedgerIdBalance>=0)
           eItem6.setClosingAmtDr(currentMCALedgerIdBalance);
           else
           eItem6.setClosingAmtCr( Math.abs(currentMCALedgerIdBalance));
         
           entryService.saveEntryItem(eItem6);
           
           if(s.getNameOfMediator()!=null && !s.getNameOfMediator().isEmpty()){
           
           Double currentCASHMEDLedgerIdBalance=openingBalance_salesCASHMEDLedgerId-s.getActualMca();
           Entryitems eItem5=new Entryitems();
           eItem5.setEntryId(entryId);
           eItem5.setAmount(s.getActualMca());
           eItem5.setLedgerId(salesCASHMEDLedgerId);
           eItem5.setType("C");         
           if(currentCASHMEDLedgerIdBalance>=0)
           eItem5.setClosingAmtDr(currentCASHMEDLedgerIdBalance);
           else
           eItem5.setClosingAmtCr( Math.abs(currentCASHMEDLedgerIdBalance));
           entryService.saveEntryItem(eItem5);
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
        
           Double currentSalesRounding=openingBalanceSalesRounding-s.getAddLess();
           Entryitems eItem7=new Entryitems();
           eItem7.setEntryId(entryId);
           eItem7.setAmount(s.getAddLess());
           eItem7.setLedgerId(sales_rounding_Ledger_id);
           eItem7.setType("C");
           if(currentSalesRounding>=0)
           eItem7.setClosingAmtDr(currentSalesRounding);
           else
           eItem7.setClosingAmtCr( Math.abs(currentSalesRounding));
          
         
           entryService.saveEntryItem(eItem7);
       }
       }
    } // ending item size condition above 1
    else
    {
     // delete created salen Entries  
     salesService.deleteSales(invoicNo);
    }    
       //Save sales estimate
       if(salesEstimate.size()>0)
       {
         //Convert sales to sales estimate
       SalesEstimate se=new SalesEstimate();
       se.setDate(s.getDate());
       se.setCategory(s.getCategory());
       se.setCashBuyer(s.getCashBuyer());
       se.setTinNo(s.getTinNo());
       se.setPos(s.getPos());
       se.setIsIgst(s.getIsIgst());
       se.setMode(s.getMode());
       se.setNameOfBuyer(s.getNameOfBuyer());
       se.setBuyerId(s.getBuyerId());
       se.setNameOfMediator(s.getNameOfMediator());
       se.setMediatorId(s.getMediatorId());
       se.setVaa(Double.valueOf(df.format(vaaEstimate)));
       se.setAssessValue(Double.valueOf(df.format(assessValueEstimate)));
       se.setTotalVat(Double.valueOf(df.format(totalVatEstimate)));
       se.setTotalCgst(Double.valueOf(df.format(totalCgstEstimate)));
       se.setTotalIgst(Double.valueOf(df.format(totalIgstEstimate)));
       se.setMca(Double.valueOf(df.format(mcaEstimate)));
       se.setInvoiceAmount(Double.valueOf(df.format(invoiceAmountEstimate)));
       se.setStatus("To Be Purchased");   
           
           
          String yearRange1=as.GetYearRange();
          long previousId1=as.getPreviousIdBasedStartAndEndYear("invoiceNo", "SalesEstimate", yearRange1);      
          String primaryIdPE1="";  
          if(previousId1==0){
             primaryIdPE1=previousId1+1+"/"+yearRange1;
          }else{
               primaryIdPE1=previousId1+1+"/"+yearRange1;
          }
          ses.setAttribute("primaryIdPE", primaryIdPE1);   
          // end of finacial year  
        String salesEstimateId=salesEstimateService.saveSalesEstimate(se);
        Iterator iterEstimateList=salesEstimate.iterator();
        while(iterEstimateList.hasNext()){
            SalesEstimateItem seItem=(SalesEstimateItem)iterEstimateList.next();
            seItem.setInvoiceNo(salesEstimateId);
            salesEstimateService.saveSalesEstimateItem(seItem);
        }
       }
        
      // return new ModelAndView("redirect:editSales.html?id="+invoicNo+"");
      return new ModelAndView("redirect:SalesGrid.html");
    }
}
@ResponseBody
//   @RequestMapping(value={"GetDatatableSales"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
//   
//    public DatatableObject getDatatableSale( HttpServletRequest req,RedirectAttributes redirect)
//    {
//      DatatableObject db =new DatatableObject();
//      
//           String[] cols = {  "invoice_no", "date", "name_of_buyer","mode","category","buyer_balance","invoice_amount" };
//    String table = "sales";
//    
//
//    int amount = 10;
//    int start = 0;
//    int echo = 0;
//    int col = 0;
//    
//     
//    String engine = "";
//    String browser = "";
//    String platform = "";
//    String version = "";
//    String grade = "";
// 
//    String dir = "asc";
//    String sStart = req.getParameter("start");
//    String sAmount = req.getParameter("length");
//    String sEcho = req.getParameter("sEcho");
//    String sCol = req.getParameter("order[0][column]");
//    String sdir = req.getParameter("order[0][dir]");
//     
//    System.out.println("sStart" +sStart);
//    System.out.println("sAmount" +sAmount);
//    
//  
//   int saleRet=0; 
//   if (des.getDefaultbyId(1)!=null )
//   {
//   String saleReturn=des.getDefaultbyId(1).getSalesReturn();
//   saleRet =Integer.parseInt(saleReturn);
//   }
//   else
//   {
////        ModelAndView modelRedirect = new ModelAndView("redirect:Companyinformation.html");
////       redirect.addFlashAttribute("message", "Create the Company Information!");
////       return modelRedirect;
//   }
//   
//   
//  // System.out.println("SALE RETUN &&&&&   " + saleRet);
//   
//     
//    if (sStart != null) {
//        start = Integer.parseInt(sStart);
//        if (start < 0)
//            start = 0;
//    }
//    if (sAmount != null) {
//        amount = Integer.parseInt(sAmount);
//        if (amount < 10 || amount > 100)
//            amount = 10;
//    }
//    if (sEcho != null) {
//        echo = Integer.parseInt(sEcho);
//    }
//    if (sCol != null) {
//        col = Integer.parseInt(sCol);
//        if (col < 0 || col > 6)
//            col = 0;
//    }
//    if (sdir != null) {
//        if (!sdir.equals("asc"))
//            dir = "desc";
//    }
//    String colName = cols[col];
//    int total = 0;
//   
//    try {
//        String sql = "SELECT s.invoice_no,s.date,b.buyer_name,s.mode,s.category,s.buyer_balance,s.invoice_amount  from sales AS s  INNER JOIN buyer_master AS b ON s.buyer_id = b.id_buyer ";
//        System.out.println("abi" + sql);
//        List<Object[]> list = salesService.GetDatatableSalesObject(sql);
//        System.out.println("sales List" + list);
//        total=list.size();
//        System.out.println("the list size sales" + total);
//        
//    }catch(Exception e){
//         
//    }
//    int totalAfterFilter = total;
//    //result.put("sEcho",echo);
// 
//    try {
//        String searchSQL = "";
//        String sql = "SELECT s.invoice_no,s.date,b.buyer_name,s.mode,s.category,s.buyer_balance,s.invoice_amount,s.add_less,'"+saleRet+"'  from sales AS s  INNER JOIN buyer_master AS b ON s.buyer_id = b.id_buyer";
//        String searchTerm =req.getParameter("search[value]");
//        System.out.println(" Valahsdfh "+searchTerm);
//         String globeSearch =  " where (invoice_no like '"+searchTerm+"%')";
//    
//        if(searchTerm!=""){
//            searchSQL=globeSearch;
//        }
//        sql += searchSQL;
//        sql += " order by " + colName + " " + dir;
//        sql += " limit " + start + ", " + amount;
//        System.out.println(" SQL abi "+sql);
//        // For aData
//         List<Object[]> list2 = salesService.GetDatatableSalesObject(sql);
//         // For Filter Count 
//        String sql2 = "SELECT s.invoice_no,s.date,b.buyer_name,s.mode,s.category,s.buyer_balance,s.invoice_amount,s.add_less,s.assess_value from sales AS s  INNER JOIN buyer_master AS b ON s.buyer_id = b.id_buyer";
//       if (searchTerm != "") {
//            sql2 += searchSQL;
//          List<Object[]> count = salesService.GetDatatableSalesCount(sql2);
//          totalAfterFilter=count.size();
//        }
//  
//   db.setiTotalRecords(total);
//   db.setiTotalDisplayRecords(totalAfterFilter);
//   db.setAaData(list2);
//   db.setReturnLimit(saleRet);
//    } catch (Exception e) {
// 
//    }
//      return db;
//	}  
@RequestMapping(value={"SalesGrid"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
public ModelAndView SalesGrid() throws ParseException
{
    SalesForm sForm=new SalesForm();
    ModelAndView model = new ModelAndView("SalesGrid");
      // edit sale 
           List l=des.listDefaultSettings();
           if(des.getDefaultbyId(1)!=null){
           DefaultSettings ds=(DefaultSettings)l.iterator().next();
           String el=  ds.getEditLimit();  
           System.out.print("SalesGrid:"+l);// edit limit
           model.addObject("el", el);
           System.out.print("EditLimit:"+el);
     //Today date       
            LocalDate tD =java.time.LocalDate.now();
            model.addObject("curDate", tD);
            System.out.print("curDate:"+tD);
        
       //sales return
          String sr=ds.getSalesReturn();
          System.out.print("saleRtn:"+sr);
          model.addObject("saleRtn", sr);
            
            model.addObject("salesinfo",salesService.listSales());
            model.addObject("salesForm", sForm);
           }
    return model;
}


@RequestMapping(value={"SalesReports"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
public ModelAndView SalesReports()
{
    
    ModelAndView model = new ModelAndView("SalesReports");
    model.addObject("salesListReportsinfo",salesService.listSalesRegister());
  
    return model;
}
  
@RequestMapping(value={"editSales"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
public ModelAndView editSales(@RequestParam("id") String id, RedirectAttributes redirect)
{
    
    if(id!=null && id.length()>0){
        SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();        
        String CurrentDate= dmyFormat.format(date);  
        
        ModelAndView model = new ModelAndView("Sales");
      Sales sale=salesService.getSalesById(id);
      List list=salesService.getSalesItemBySalesId(id);
      
      BuyerMaster buyer=bs.getBuyerbyId(sale.getBuyerId());
      if(!sale.getMediatorId().isEmpty())
      {
      BuyerMaster medi=bs.getBuyerbyId(Integer.parseInt(sale.getMediatorId()));
      int  MediatorLedgerId=ledgerAccountService.getLederByReferenceId(String.valueOf(sale.getMediatorId()), "Buyer").iterator().next().getIdLedger();
//      sale.setNameOfMediator(medi.getBuyerName());
         
           
       Double MediatorVoucherBalance_Consumer=0.0;
        List<Object[]> salesCASHMEDLedgerIdcurrentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(MediatorLedgerId), CurrentDate);
          
            if( salesCASHMEDLedgerIdcurrentBalance!=null && salesCASHMEDLedgerIdcurrentBalance.size()>0)
           {
                 for (Object[] column : salesCASHMEDLedgerIdcurrentBalance) {
                            MediatorVoucherBalance_Consumer = (Double)column[0]- (Double)column[1];
      }

      }
           else
           {
               List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(MediatorLedgerId));
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
//       sale.setNameOfBuyer(buyer.getBuyerName());
      
      
      SalesForm sForm=new SalesForm();
      sForm.setSale(sale);
      sForm.setSales(list);     
   
      Sales sale1=salesService.getSalesById(id);
       sale.setCategory(sale1.getCategory());
         sale.setMode(sale1.getMode());
         model.addObject("CategoryAuto", sale1.getCategory());
      model.addObject("salesForm", sForm);
      model.addObject("taxStructure", tServ.getTaxId(1));
      HashSet<String> set=new HashSet<String> ();
        List cCodelist=cService.listCode();
        Iterator itr=cCodelist.iterator();
        while(itr.hasNext()){
            CcodeMaster c=(CcodeMaster)itr.next();
            set.add(c.getTaxRate().toString());
        }
        
        // Getting Cuurent closing balance of buyer and nediator
       
               int  byuerLedgerId=ledgerAccountService.getLederByReferenceId(String.valueOf(sale.getBuyerId()), "Buyer").iterator().next().getIdLedger(); 
               double openingBalanceOfBuyerLedger=0.0;
                List<Object[]> currentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(byuerLedgerId), CurrentDate);
                if( currentBalance!=null && currentBalance.size()>0)
                {
                      for (Object[] column : currentBalance) {
                                 openingBalanceOfBuyerLedger = (Double)column[0]- (Double)column[1];
                         }

                }
                else
                {
                    List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(byuerLedgerId));
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
        
        String CompanyGstno="";
        if(companyservice.getCompanyById(1)!=null)
        {
          CompanyGstno=companyservice.getCompanyById(1).getCompanyTin();
          CompanyGstno=CompanyGstno.substring(0, 2);
        }
        model.addObject("CompanyGstno",CompanyGstno);
        model.addObject("cCodeList", set);
        
        System.out.println();
      return model;
    }
   else{
        ModelAndView model = new ModelAndView("redirect:SalesGrid.html");
       return model;
   }

}
  
   
  
@RequestMapping(value={"deleteSales"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
public ModelAndView deleteSales(@RequestParam("id") String id, RedirectAttributes redirect)
{
    ModelAndView model = new ModelAndView("redirect:SalesGrid.html");
   if(id!=null && id.length()>0){
      salesService.deleteSales(id);         
       return model;
   }
   else{
       return model;
   }

}
    
     

@RequestMapping(value={"sales_addrow"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
public ModelAndView itemMasterPages(@ModelAttribute("salesForm")  SalesForm salesForm, HttpServletRequest req)
{
   
    SalesItem sItem_plus=new SalesItem();
    SalesItem sItem=new SalesItem();
    SalesForm sForm=salesForm;
    List<SalesItem> list=sForm.getSales(); // Getting all rows from forms
    List<SalesItem> listNew= new ArrayList<SalesItem>(); // for add undeleted items 
    Iterator<SalesItem> i = list.iterator();
    // this loop used to iterate all rows of data
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
            System.out.println("sales Item unit is "+sItem.getUnit());
          listNew.add(sItem);
        }    

    }
   listNew.add(sItem_plus);    // this step add empty row into arraylist
    sForm.setSales(listNew); //setting all data with empty row into form
  
    ModelAndView model = new ModelAndView("Sales_AddRow");
    
    if(des.getDefaultbyId(1)!=null ){
    ds=des.getDefaultbyId(1); 
    model.addObject("CategoryAuto", ds.getSalesCategory());
    }
    else
    {
    model.addObject("CategoryAuto", "");   
    }   
    model.addObject("salesForm", sForm);
    model.addObject("taxStructure", tServ.getTaxId(1));
    model.addObject("BuyMast",bs.listBuyer());
    HashSet<String> set=new HashSet<String> ();
        List cCodelist=cService.listCode();
        Iterator itr=cCodelist.iterator();
        while(itr.hasNext()){
            CcodeMaster c=(CcodeMaster)itr.next();
            set.add(c.getTaxRate().toString());
        }
        String CompanyGstno="";
        if(companyservice.getCompanyById(1)!=null)
        {
          CompanyGstno=companyservice.getCompanyById(1).getCompanyTin();
          CompanyGstno=CompanyGstno.substring(0, 2);
        }
        model.addObject("CompanyGstno",CompanyGstno);
        model.addObject("cCodeList", set);
    return model;
}

@RequestMapping(value={"ConvertToSales"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
public ModelAndView convertToSales(@RequestParam("eId") String id, RedirectAttributes redirect) throws ParseException
{
   
    DecimalFormat df = new DecimalFormat("#.##");   
    if(id!=null && id.length()>0){
      
      SalesEstimate sale=salesEstimateService.getSalesEstimateById(id);
      List list=salesEstimateService.getSalesEstimateItemBySalesId(id);
      
      Sales p=new Sales();
      p.setDate(sale.getDate());
      p.setCategory(sale.getCategory());
      p.setCashBuyer(sale.getCashBuyer());
      p.setTinNo(sale.getTinNo());
      p.setMode(sale.getMode());
      p.setNameOfBuyer(sale.getNameOfBuyer());     
      p.setBuyerId(sale.getBuyerId());
      p.setNameOfMediator(sale.getNameOfMediator());
      p.setMediatorId(sale.getMediatorId());     
      p.setNarration(sale.getNarration());
      p.setPos(sale.getPos());
      p.setIsIgst(sale.getIsIgst());
                
      double vaa=0;
      double mca=0;
      double assessValue=0;
      double totalVat=0;
      double totalCgst=0;
      double totalIgst=0;
      double invoiceAmount=0;
      
      List<SalesItem> saleList=new ArrayList<SalesItem>();
     
      Iterator itr=list.iterator();
      while(itr.hasNext()){
           SalesItem sItem=new SalesItem();
          SalesEstimateItem pItem=(SalesEstimateItem)itr.next();      
          ItemMaster iMaster=itemMasterService.getItmeByItemNo(pItem.getItemCode());
          double rate=0;
          double cpValue=0.00;
          if(sale.getCategory().equalsIgnoreCase("Consumers(B2C)")){
           
              if(pItem.getUnit()==(iMaster.getUnit())){
             
                  if(pItem.getMargin().equalsIgnoreCase("A")){                       
                    rate=iMaster.getCap();    
                    double cp=iMaster.getCp();
                    double tax=iMaster.getTr();
                    double taxValue=cp*tax/100; 
                    double cp_tax = Double.valueOf(df.format(taxValue+cp));
                    pItem.setCpcpV(cp_tax); 
                    cpValue=cp;
                   
                  }
                  if(pItem.getMargin().equalsIgnoreCase("B")){
                    rate=iMaster.getCbp();
                    double cp=iMaster.getCp();
                    double tax=iMaster.getTr();
                    double taxValue=cp*tax/100; 
                    double cp_tax = Double.valueOf(df.format(taxValue+cp));
                    pItem.setCpcpV(cp_tax);  
                    cpValue=cp;
                  }

              }
        
              if(pItem.getUnit().equals(iMaster.getAltUnit())){
                  if(pItem.getMargin().equalsIgnoreCase("A")){
                    rate=iMaster.getCapAlt();
                    double cp=iMaster.getCp();
                    double tax=iMaster.getTr();
                    double taxValue=cp*tax/100; 
                    double cp_tax = Double.valueOf(df.format(taxValue+cp));
                    pItem.setCpcpV(cp_tax/Double.valueOf(iMaster.getTotalUnit()));  
                    cpValue=cp/Double.valueOf(iMaster.getTotalUnit());
                  }
                  if(pItem.getMargin().equalsIgnoreCase("B")){
                      rate=iMaster.getCbpAtl();
                      double cp=iMaster.getCp();
                      double tax=iMaster.getTr();
                      double taxValue=cp*tax/100; 
                      double cp_tax = Double.valueOf(df.format(taxValue+cp));
                      pItem.setCpcpV(cp_tax/Double.valueOf(iMaster.getTotalUnit())); 
                      cpValue=cp/Double.valueOf(iMaster.getTotalUnit());
                  }

              }

          }
          if(sale.getCategory().equalsIgnoreCase("GST Dealers(B2B)")){
            if(pItem.getUnit().equals(iMaster.getUnit())){
                  if(pItem.getMargin().equalsIgnoreCase("A")){
                      rate=iMaster.getVap();
                       double cp=iMaster.getCp();
                       pItem.setCpcpV(cp);  
                       cpValue=cp;
                  }
                  if(pItem.getMargin().equalsIgnoreCase("B")){
                      rate=iMaster.getVbp();
                       double cp=iMaster.getCp();
                       pItem.setCpcpV(cp);  
                        cpValue=cp;
                  }

              }
              if(pItem.getUnit().equals(iMaster.getAltUnit())){
                  if(pItem.getMargin().equalsIgnoreCase("A")){
                      rate=iMaster.getVapAlt();
                       double cp=iMaster.getCp();
                       pItem.setCpcpV(cp/Double.valueOf(iMaster.getTotalUnit())); 
                       cpValue=cp/Double.valueOf(iMaster.getTotalUnit());
                  }
                  if(pItem.getMargin().equalsIgnoreCase("B")){
                      rate=iMaster.getVbpAlt();
                      double cp=iMaster.getCp();
                      pItem.setCpcpV(cp/Double.valueOf(iMaster.getTotalUnit()));  
                      cpValue=cp/Double.valueOf(iMaster.getTotalUnit());
                  }
              }  
          }

          double amount=(pItem.getQuantity()*rate)-pItem.getDiscount();
             
              pItem.setAmount(amount);
      
              double taxValue=0.0;
              double cgst=0.0;
              double vat=0.0;
              double igst=0.0;
              double itemAssessValue=0.0;
              double itemVaa=0.0;
              double taxCgst=0.0;
              double taxSgst=0.0;
              double taxIgst=0.0;
                Double cpCpvAmount=pItem.getQuantity()*cpValue;
            if(p.getCategory().equalsIgnoreCase("Consumers(B2C)")){
             double taxamount=(amount*Double.parseDouble(pItem.getTax()))/(100+Double.parseDouble(pItem.getTax()));
                taxCgst=(taxamount*pItem.getTaxCgst())/(Double.parseDouble(pItem.getTax()));
                taxSgst=(taxamount*pItem.getTaxSgst())/(Double.parseDouble(pItem.getTax()));
                taxIgst=(taxamount*pItem.getTaxIgst())/(Double.parseDouble(pItem.getTax()));  
                if(Double.isNaN(taxCgst))
                {
                cgst=0; 
                }
                else
                {
                cgst=taxCgst;  
                } 
                if(Double.isNaN(taxIgst))
                {
                igst=0; 
                }
                else
                {
                igst=taxIgst;  
                } 
                if(Double.isNaN(taxSgst))
                {
                vat=0; 
                }
                else
                {
                vat=taxSgst;  
                } 
               
                itemAssessValue=amount-(cgst+vat+igst);
                 assessValue=assessValue+itemAssessValue;
              }
              if(p.getCategory().equalsIgnoreCase("GST Dealers(B2B)")){
                  taxCgst=(amount*pItem.getTaxCgst())/100;
                taxSgst=(amount*pItem.getTaxSgst())/100;
                taxIgst=(amount*pItem.getTaxIgst())/100;
                if(Double.isNaN(taxCgst))
                {
                cgst=0; 
                }
                else
                {
                cgst=taxCgst;  
                } 
                if(Double.isNaN(taxIgst))
                {
                igst=0; 
                }
                else
                {
                igst=taxIgst;  
                } 
                if(Double.isNaN(taxSgst))
                {
                vat=0; 
                }
                else
                {
                vat=taxSgst;  
                } 
              
                itemAssessValue=amount;
                 assessValue=assessValue+itemAssessValue;
              }
              totalVat=totalVat+vat;
              totalCgst=totalCgst+cgst;
              totalIgst=totalIgst+igst;
              pItem.setCgst(Double.valueOf(df.format(cgst)));
              pItem.setVat(Double.valueOf(df.format(vat)));
              pItem.setIgst(Double.valueOf(df.format(igst)));
              pItem.setTax(iMaster.getTr().toString());
              itemVaa=itemAssessValue-cpCpvAmount;
              System.out.println("Vaa Direct:"+itemVaa);
              vaa=vaa+itemVaa;
              pItem.setCpcpvAmount(String.valueOf(cpCpvAmount));

              double mcMca=0;
              if(iMaster.getMc()!=null && iMaster.getMc().toString().length()>0){
                  mcMca=iMaster.getMc();
              }
              if(iMaster.getMca()!=null && iMaster.getMca().toString().length()>0){
                  mcMca=iMaster.getMca();
              }
              pItem.setMcmca(String.valueOf(mcMca));
              Double mcMcaAmount=pItem.getQuantity()*mcMca;
              mca=mca+mcMcaAmount;
              pItem.setMcmcaAmount(String.valueOf(mcMcaAmount));
              invoiceAmount=invoiceAmount+itemAssessValue+vat+cgst+igst;
                   
               
               if(p.getCategory().equalsIgnoreCase("Consumers(B2C)"))
        {
       p.setVaa(Double.valueOf(df.format(vaa)));
        if(p.getCategory().equalsIgnoreCase("Consumers(B2C)")){
          p.setAssessValue(Double.valueOf(df.format(assessValue)));
        }
        if(p.getCategory().equalsIgnoreCase("GST Dealers(B2B)")){
           p.setAssessValue(Double.valueOf(df.format(assessValue)));
        }
        p.setTotalVat(Double.valueOf(df.format(totalVat)));
        p.setTotalCgst(Double.valueOf(df.format(totalCgst)));
        p.setTotalIgst(Double.valueOf(df.format(totalIgst)));
       p.setMca(mca);
        if(p.getAddLess()==null || p.getAddLess().toString().length()==0){
           p.setAddLess(0.0);
        }
        invoiceAmount=invoiceAmount+p.getAddLess();
       
        p.setInvoiceAmount(Double.valueOf(df.format(invoiceAmount)));
         }
         else
        {
       p.setVaa(Double.valueOf(df.format(vaa)));
        if(p.getCategory().equalsIgnoreCase("Consumers(B2C)")){
          p.setAssessValue(Double.valueOf(df.format(assessValue)));
        }
        if(p.getCategory().equalsIgnoreCase("GST Dealers(B2B)")){
          p.setAssessValue(Double.valueOf(df.format(assessValue)));
        }
        p.setTotalVat(Double.valueOf(df.format(totalVat)));
        p.setTotalCgst(Double.valueOf(df.format(totalCgst)));
        p.setTotalIgst(Double.valueOf(df.format(totalIgst)));
       p.setMca(mca);
        if(p.getAddLess()==null || p.getAddLess().toString().length()==0){
           p.setAddLess(0.0);
        }
        invoiceAmount=invoiceAmount+p.getAddLess();
       
        p.setInvoiceAmount(Double.valueOf(df.format(invoiceAmount)));
         }
          sItem.setWithoutTax(itemAssessValue);
          sItem.setTax(pItem.getTax());
          sItem.setCpcpvAmount(pItem.getCpcpvAmount());
          sItem.setMcmca(pItem.getMcmca());
          sItem.setMcmcaAmount(pItem.getMcmcaAmount());
          
          sItem.setItemCode(pItem.getItemCode());
          sItem.setItemName(pItem.getItemName());
          sItem.setQuantity(pItem.getQuantity());
          sItem.setUnit(pItem.getUnit());
          sItem.setMargin(pItem.getMargin());
          sItem.setRate(rate);
          sItem.setCpcpV(Double.valueOf(df.format(pItem.getCpcpV())));
          sItem.setCgst(pItem.getCgst());
          sItem.setIgst(pItem.getIgst());
          sItem.setVat(pItem.getVat());
          sItem.setAmount(Double.valueOf(df.format(pItem.getAmount())));
          sItem.setTaxCgst(pItem.getTaxCgst());
          sItem.setTaxSgst(pItem.getTaxSgst());
          sItem.setTaxIgst(pItem.getTaxIgst());
          saleList.add(sItem);
  }
          
      
      SalesForm sForm=new SalesForm();
      sForm.setSale(p);
      sForm.setSales(saleList);
      
      ModelAndView model = new ModelAndView("Sales");
       String CompanyGstno="";
        if(companyservice.getCompanyById(1)!=null)
        {
          CompanyGstno=companyservice.getCompanyById(1).getCompanyTin();
          CompanyGstno=CompanyGstno.substring(0, 2);
          model.addObject("CompanyGstno",CompanyGstno);
        }
        else
        model.addObject("CompanyGstno",CompanyGstno); 
        model.addObject("mode","no"); 
      DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date();
    String currentDate=dateFormat.format(date);
    String previousInvoiceDate=salesService.getLastInvoiceDate();
  
    if(previousInvoiceDate.equalsIgnoreCase(""))
    {
        previousInvoiceDate=currentDate;
    }
    else
    {
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date convertedCurrentDate = sdf.parse(previousInvoiceDate);
     previousInvoiceDate=dateFormat.format(convertedCurrentDate );
    }    
     model.addObject("currentDate", currentDate);
      model.addObject("previousInvoiceDate", previousInvoiceDate);
       model.addObject("CategoryAuto", p.getCategory());
      
      
      model.addObject("salesForm", sForm);
      model.addObject("taxStructure", tServ.getTaxId(1));
      HashSet<String> set=new HashSet<String> ();
        List cCodelist=cService.listCode();
        Iterator iter=cCodelist.iterator();
        while(iter.hasNext()){
            CcodeMaster c=(CcodeMaster)iter.next();
            set.add(c.getTaxRate().toString());
        }
    model.addObject("cCodeList", set);
      return model;
      
      }
  
      else {
        ModelAndView model = new ModelAndView("redirect:SalesGrid.html");
       return model;
   }

}

@RequestMapping(value={"GetItemCodeForSales"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
public List<ItemMaster> getItemCodeForSales(@RequestParam("term") String key, @RequestParam("tax") String tax,@RequestParam("items") String items)
{       
        if(items.length()>0){
          
            return itemMasterService.getItemListByKeyWithTax(key, tax,items);
        }
        else{
          
          return itemMasterService.getItemListByKey(key);
        }
}
@RequestMapping(value={"GetItemNameForSales"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
public List<ItemMaster> getItemNameForSales(@RequestParam("term") String key, @RequestParam("tax") String tax,@RequestParam("items") String items)
{       
        if(items.length()>0){
          
            return itemMasterService.getNameOfItemListByKeyWithTax(key, tax,items);
        }
        else{
          
          return itemMasterService.getNameOfItemByKey(key);
        }
}
////Purchse Estimate ROL//////////
@RequestMapping(value={"GetItemCodeForSalesROL"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
public List<ItemMaster> GetItemCodeForSalesROL(@RequestParam("term") String key, @RequestParam("tax") String tax,@RequestParam("items") String items)
{       
        if(items.length()>0){
          
            return itemMasterService.getItemListByKeyWithTaxROL(key, tax,items);
        }
        else{
          
          return itemMasterService.getItemListByKeyROL(key);
        }
}
@RequestMapping(value={"GetItemNameForSalesROL"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
public List<ItemMaster> GetItemNameForSalesROL(@RequestParam("term") String key, @RequestParam("tax") String tax,@RequestParam("items") String items)
{       
        if(items.length()>0){
          
            return itemMasterService.getNameOfItemListByKeyWithTaxROL(key, tax,items);
        }
        else{
          
          return itemMasterService.getNameOfItemByKeyROL(key);
        }
}
////Purchse Estimate ROL//////////

@RequestMapping(value={"GetBuyer"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
public List<BuyerMaster> getBuyer(HttpServletRequest req)
    throws IOException
  {
    String categoary=req.getParameter("categoary");
     List<BuyerMaster> buyer=null;
    if(categoary.equalsIgnoreCase("GST Dealers(B2B)"))
    {
    buyer= this.salesService.listBuyerLikeGst(req.getParameter("term"));
    }
    else if(categoary.equalsIgnoreCase("Consumers(B2C)"))
    {
    buyer = this.salesService.listBuyerLike(req.getParameter("term"));    
    }
    
    return buyer;
  }

@RequestMapping(value={"GetBuyerMediator"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
public List<BuyerMaster> getBuyerMediator(HttpServletRequest req)
    throws IOException
  {
    List<BuyerMaster> buyer = this.salesService.listBuyerMediatorLike(req.getParameter("term"));
    
    return buyer;
  }

@RequestMapping(value={"GetBuyerList"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
public List<BuyerMaster> getBuyerList(){
    List<BuyerMaster> buyer = bs.listBuyer();
    return buyer;
  }



@RequestMapping(value={"GetItemByItemCode"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
public ItemMaster getItemByItemCode(@RequestParam("itemCode") String itemCode){
    ItemMaster itemMaster = itemMasterService.getItmeByItemNo(itemCode);
    return itemMaster;
}

@RequestMapping(value={"getUnit"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
public List<UnitMaster> getUnit(@RequestParam("itemCode") String itemCode){
    
    List<UnitMaster> list=new ArrayList<UnitMaster>();
    
    ItemMaster itemMaster=itemMasterService.getItmeByItemNo(itemCode);
    if(itemMaster!=null ){
   
    list.add(unitService.getUnitbyId((itemMaster.getUnit())));
    if(itemMaster.getAltUnit()!=null ){
    list.add(unitService.getUnitbyId((itemMaster.getAltUnit())));
    }
    }
    return list;
}
@RequestMapping(value={"getBuyerAccount"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public LedgerAccountMaster getBuyerAccount(@RequestParam("id") String buyerID, LedgerAccountMaster bm,HttpServletRequest request)
    {
       System.out.println("key :"+ buyerID);
       
       String type=request.getParameter("type");
       System.out.println("key :"+ buyerID);
       Integer  ledgerID=salesService.getLedgerIdByBuyerId(Integer.parseInt(buyerID),type).getIdLedger();
       System.out.println(" check ledger id:"+ ledgerID );
       
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
    

@RequestMapping(value={"SalesInvoice"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
public ModelAndView salesInvoice(@RequestParam("id") String id,HttpServletRequest req){
    
    if(id!=null && id.length()>0){
        
        List list=companyservice.listcompany();
        CompanyInformation company=(CompanyInformation)list.iterator().next();
      ModelAndView model=new ModelAndView("SalesInvoice");  
      String type=req.getParameter("type");     
      
      if(type!=null && type.equalsIgnoreCase("pre"))
      {
        PrintSetting prePrint=ps.getPrintType("Pre Printed");  
        model.addObject("Type", "pre");  
        model.addObject("print", prePrint );  
      }
      else
      {
        PrintSetting plain=ps.getPrintType("Plain");  
        model.addObject("Type", "plain");  
        model.addObject("print", plain);  
      }    
      Sales sale=salesService.getSalesById(id);
      List<Object[]> slaesItemList=salesService.getSalesItemBySalesIdInvoice(id);
    
    
      model.addObject("company", company);
      model.addObject("sales", sale);
      model.addObject("buyer", bs.getBuyerbyId(sale.getBuyerId()));
      model.addObject("salesItem", slaesItemList);
     
      
       company=companyservice.getCompanyById(1);
      model.addObject("companyFrom",company);
      return model;
    }
   else{
       ModelAndView model = new ModelAndView("redirect:Sales.html");
       return model;
   }
    
}
@RequestMapping(value={"Salesinvoice"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
public ModelAndView salesInvoices(@RequestParam("id") String id,HttpServletRequest req){
    
    if(id!=null && id.length()>0){
        
        List list=companyservice.listcompany();
        CompanyInformation company=(CompanyInformation)list.iterator().next();
         String type=req.getParameter("type");     
      ModelAndView model=new ModelAndView("SalesInvoice");
      if(type!=null && type.equalsIgnoreCase("pre"))
      {
        PrintSetting prePrint=ps.getPrintType("Pre Printed");  
        model.addObject("Type", "pre");  
        model.addObject("print", prePrint );  
      }
      else
      {
        PrintSetting plain=ps.getPrintType("Plain");  
        model.addObject("Type", "plain");  
        model.addObject("print", plain);  
      } 
      Sales sale=salesService.getSalesById(id);
      List<Object[]> slaesItemList=salesService.getSalesItemBySalesIdInvoice(id);
      model.addObject("company", company);
      model.addObject("sales", sale);
      model.addObject("buyer", bs.getBuyerbyId(sale.getBuyerId()));
      model.addObject("salesItem", slaesItemList);
      model.addObject("withVat", "withVat");
      company=companyservice.getCompanyById(1);
      model.addObject("companyFrom",company);
      return model;
    }
   else{
       ModelAndView model = new ModelAndView("redirect:Sales.html");
       return model;
   }
    
}

@RequestMapping(value={"SalesMediator"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
public ModelAndView salesMediator(@RequestParam("id") String id,SalesForm salesForm){
    
    if(id!=null && id.length()>0){
        
        List list=companyservice.listcompany();
        CompanyInformation company=(CompanyInformation)list.iterator().next();
        
      Sales sale=salesService.getSalesById(id);
      List slaesItemList=salesService.getSalesItemBySalesId(id);
      Sales s=salesForm.getSale();      
      Integer entry1=entryService.getEntryListId(id,"Sales").getId();   
      ArrayList<SalesItem> sales=new ArrayList<SalesItem>();
      TaxStructure ts=tServ.getTaxId(1);
      
      Iterator itr=list.iterator();
      if(sale.getCategory()=="CASH MEDIATOR"){
                        

        SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();        
        String CurrentDate= dmyFormat.format(date);
        
               double totalCASHMEDEntry=0;
               double totalCASHEntry=0;

        int salesCASHMEDLedgerId=7;
        int salesCASHLedgerId=1;
           

        List<Object[]> salesCASHMEDLedgerIdcurrentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(salesCASHMEDLedgerId), CurrentDate);
        List<Object[]> salesCASHLedgerIdcurrentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(salesCASHLedgerId), CurrentDate);

       double openingBalance_salesCASHMEDLedgerId=0;
        double openingBalance_salesCASHLedgerId=0; 

       
       if( salesCASHMEDLedgerIdcurrentBalance!=null && salesCASHMEDLedgerIdcurrentBalance.size()>0)
       {
             for (Object[] column : salesCASHMEDLedgerIdcurrentBalance) {
                        openingBalance_salesCASHMEDLedgerId = (Double)column[0]- (Double)column[1];
                }
           
       }
       else
       {
           List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(salesCASHMEDLedgerId));
           for (Object[] column : OpeningBalance) {
                        openingBalance_salesCASHMEDLedgerId = (Double)column[0]- (Double)column[1];
                }
       } 
       
       
       if( salesCASHLedgerIdcurrentBalance!=null && salesCASHLedgerIdcurrentBalance.size()>0)
       {
             for (Object[] column : salesCASHLedgerIdcurrentBalance) {
                        openingBalance_salesCASHLedgerId = (Double)column[0]- (Double)column[1];
                }
           
       }
       else
       {
           List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(salesCASHLedgerId));
           for (Object[] column : OpeningBalance) {
                        openingBalance_salesCASHLedgerId = (Double)column[0]- (Double)column[1];
                }
       } 
           Iterator iter=sales.iterator();

             totalCASHMEDEntry=sale.getActualMca(); 
             totalCASHEntry=sale.getActualMca();
               
           Double currentMCALedgerIdBalance=openingBalance_salesCASHMEDLedgerId-totalCASHMEDEntry;
           Entryitems eItem1=new Entryitems();
           eItem1.setEntryId(entry1);           
           eItem1.setAmount(totalCASHMEDEntry);
           eItem1.setLedgerId(salesCASHMEDLedgerId);
           eItem1.setType("D");       
           eItem1.setClosingAmtDr(totalCASHMEDEntry);     
           eItem1.setClosingAmtCr(0.0);
           entryService.saveEntryItem(eItem1);
           
           Double currentCASHMEDLedgerIdBalance=openingBalance_salesCASHLedgerId-totalCASHEntry;
           Entryitems eItem2=new Entryitems();
           eItem2.setEntryId(entry1);
           eItem2.setAmount(totalCASHEntry);
           eItem2.setLedgerId(salesCASHLedgerId);
           eItem2.setType("C");          
           eItem2.setClosingAmtDr(0.0);         
           eItem2.setClosingAmtCr( totalCASHEntry);
           entryService.saveEntryItem(eItem2);
 
      }else{      
    
          SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();        
        String CurrentDate= dmyFormat.format(date);
        
               double totalCASHMEDEntry=0;
               double totalCASHEntry=0;


               
               int salesCASHMEDLedgerId=ledgerAccountService.getLedgerByLedgerName("MCA EXPENSES").iterator().next().getIdLedger();
        int salesCASHLedgerId=ledgerAccountService.getLedgerByLedgerName("CASH").iterator().next().getIdLedger();
         

        List<Object[]> salesCASHMEDLedgerIdcurrentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(salesCASHMEDLedgerId), CurrentDate);
        List<Object[]> salesCASHLedgerIdcurrentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(salesCASHLedgerId), CurrentDate);

       double openingBalance_salesCASHMEDLedgerId=0;
        double openingBalance_salesCASHLedgerId=0; 

       
       if( salesCASHMEDLedgerIdcurrentBalance!=null && salesCASHMEDLedgerIdcurrentBalance.size()>0)
       {
             for (Object[] column : salesCASHMEDLedgerIdcurrentBalance) {
                        openingBalance_salesCASHMEDLedgerId = (Double)column[0]- (Double)column[1];
                }
           
       }
       else
       {
           List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(salesCASHMEDLedgerId));
           for (Object[] column : OpeningBalance) {
                        openingBalance_salesCASHMEDLedgerId = (Double)column[0]- (Double)column[1];
                }
       } 
       
       
       if( salesCASHLedgerIdcurrentBalance!=null && salesCASHLedgerIdcurrentBalance.size()>0)
       {
             for (Object[] column : salesCASHLedgerIdcurrentBalance) {
                        openingBalance_salesCASHLedgerId = (Double)column[0]- (Double)column[1];
                }
           
       }
       else
       {
           List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(salesCASHLedgerId));
           for (Object[] column : OpeningBalance) {
                        openingBalance_salesCASHLedgerId = (Double)column[0]- (Double)column[1];
                }
       } 
           Iterator iter=sales.iterator();

             totalCASHMEDEntry=sale.getMca(); 
             totalCASHEntry=sale.getMca();
               
           Double currentMCALedgerIdBalance=openingBalance_salesCASHMEDLedgerId-totalCASHMEDEntry;
           Entryitems eItem1=new Entryitems();
           eItem1.setEntryId(entry1);
           eItem1.setAmount(totalCASHMEDEntry);
           eItem1.setLedgerId(salesCASHMEDLedgerId);
           eItem1.setType("D");       
           eItem1.setClosingAmtDr(totalCASHMEDEntry);     
           eItem1.setClosingAmtCr(0.0);
           entryService.saveEntryItem(eItem1);
           
           Double currentCASHMEDLedgerIdBalance=openingBalance_salesCASHLedgerId-totalCASHEntry;
           Entryitems eItem2=new Entryitems();
           eItem2.setEntryId(entry1);
           eItem2.setAmount(totalCASHEntry);
           eItem2.setLedgerId(salesCASHLedgerId);
           eItem2.setType("C");          
           eItem2.setClosingAmtDr(0.0);         
           eItem2.setClosingAmtCr( totalCASHEntry);
           entryService.saveEntryItem(eItem2);
          
      }

        System.out.println("sale.getMediatorId():"+sale.getMediatorId());
      ModelAndView model=new ModelAndView("SalesMediator");
      model.addObject("company", company);
      company=companyservice.getCompanyById(1);
      model.addObject("companyFrom",company);
      model.addObject("sales", sale);
      model.addObject("buyer", bs.getBuyerbyId(Integer.parseInt(sale.getMediatorId())));
      
      return model;
    }
   else{
       ModelAndView model = new ModelAndView("redirect:SalesMediator.html");
       return model;
   }
    
}

@RequestMapping(value={"GetSalesReports"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView GetSalesReports( HttpServletRequest req)throws ParseException
  {   
    String gsQuery = "";

    String gsFromDate = req.getParameter("fromdate");
//    System.out.println("gsFromDate :"+gsFromDate);
    SimpleDateFormat sdfIn = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdfOut = new SimpleDateFormat("yyyy-MM-dd");
    Date date = sdfIn.parse(gsFromDate);
     System.out.println("date :"+date);
//    System.out.println(sdfOut.format(date));
    String gsToDate = req.getParameter("todate");
    System.out.println("gsToDate :"+gsToDate);
    SimpleDateFormat sdfInn = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdfOutt = new SimpleDateFormat("yyyy-MM-dd");
    Date datee = sdfInn.parse(gsToDate);
//    System.out.println(sdfOutt.format(datee));
    System.out.println("datee :"+datee);
    
    gsQuery = "From Sales keyWhere ";
    
    String gsWhere = "";
   
     if (!gsFromDate.isEmpty())
      {
        if (!gsToDate.isEmpty())
        {
          if (gsWhere != "") {
            gsWhere = gsWhere + " and DATE_FORMAT(date,  '%y/%m/%d' ) \n" + "BETWEEN (  '" + gsFromDate + "') \n" + "AND (  '" + gsToDate + "')  ";
          } else {
            gsWhere = gsWhere + "WHERE   date between ('" +sdfOut.format(date) + "') and ('" +sdfOut.format(datee) + "') ";
          }
        }

      }

        gsQuery = gsQuery.replace("keyWhere", gsWhere);
        System.out.println("gsQuery check"  + gsQuery);
    
      ModelAndView model = new ModelAndView("SalesReports");
    model.addObject("salesListReportsinfo", salesService.SalesReports(gsQuery));
    return model;
  }
  


@RequestMapping(value={"UpdateSales"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
public ModelAndView UpdateSales(@ModelAttribute("salesForm") @Validated SalesForm salesForm, BindingResult result,
         RedirectAttributes redirect, HttpServletRequest request ,HttpSession ses)

  {
    
    if (result.hasErrors()) {
        return new ModelAndView("Sales");
        
    }
    else{        
        Sales s=salesForm.getSale();
        String invoicNo="";
        String BuyerName="";
        int buyerLedgerId=0;
        double openingBalanceOfLedger=0;
        int entryId=0;
        if(s.getCategory().equalsIgnoreCase("Consumers(B2C)"))
        {
       buyerLedgerId=ledgerAccountService.getLederByReferenceId(String.valueOf(s.getBuyerId()), "Buyer").iterator().next().getIdLedger();
       BuyerName=ledgerAccountService.getLederByReferenceId(String.valueOf(s.getBuyerId()), "Buyer").iterator().next().getNameOfLedger();     
       String yearRange=as.GetYearRange();
       long previousId=as.getPreviousIdBasedStartAndEndYear("invoiceNo", "Sales", yearRange);       
        String primaryIdPE="";  
          if(previousId==0){
             primaryIdPE=previousId+1+"/"+yearRange;
          }else{
               primaryIdPE=previousId+1+"/"+yearRange;
          }
           ses.setAttribute("primaryIdPE", primaryIdPE);        
      
       s.setInvoiceNo(null);
       invoicNo=salesService.saveSales(s);
       }
       
      double vaa=0;
      double mca=0;
      double assessValue=0;
      double totalVat=0;
      double totalCgst=0;
      double invoiceAmount=0;
      
      double vaaEstimate=0;
      double mcaEstimate=0;
      double assessValueEstimate=0;
      double totalVatEstimate=0;
      double totalCgstEstimate=0;
      double invoiceAmountEstimate=0;
       
       
       List list=salesForm.getSales();
       
        SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();        
        String CurrentDate= dmyFormat.format(date);
        
       if(s.getCategory().equalsIgnoreCase("Consumers(B2C)")){
       
       List<Object[]> currentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(buyerLedgerId), CurrentDate);
      
      
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
       }
       
       System.out.println(" openingBalanceOfLedger "+openingBalanceOfLedger);    
       HashSet<String> set=new HashSet<String>();
       
       ArrayList<SalesItem> sales=new ArrayList<SalesItem>();
       ArrayList<SalesEstimateItem> salesEstimate=new ArrayList<SalesEstimateItem>();
       
       TaxStructure ts=tServ.getTaxId(1);
       
       Iterator itr=list.iterator();
       while(itr.hasNext()){
           SalesItem sItem=(SalesItem)itr.next();
           sItem.setInvoiceNo(invoicNo);
           ItemMaster iMaster=itemMasterService.getItmeByItemNo(sItem.getItemCode());
           
            System.out.println(" getQuantity "+sItem.getQuantity());    
                 System.out.println(" getCurrentStock "+iMaster.getCurrentStock());   
           
           if(sItem.getQuantity()>(iMaster.getCurrentStock())){
                System.out.println(" getQuantity "+sItem.getQuantity());   
                 System.out.println(" getQuantity "+iMaster.getCurrentStock());   
              //for slae sestimate
              int qty=(int) (sItem.getQuantity()-(iMaster.getCurrentStock()));
              // end qty sales estimate
              
              sItem.setQuantity((iMaster.getCurrentStock()));
            
              if(sItem.getDiscount()==null || sItem.getDiscount().toString().length()==0){
                  sItem.setDiscount(0.0);
              }
              double amount=(sItem.getQuantity()*sItem.getRate())-sItem.getDiscount();
             
              sItem.setAmount(amount);
              double taxValue=0.0;
              double cgst=0.0;
              double vat=0.0;
              double itemAssessValue=0.0;
              double itemVaa=0.0;
              double taxCgst=0.0;
              double taxSgst=0.0;

              
              if(s.getCategory().equalsIgnoreCase("Consumers(B2C)")){
                
                double taxamount=(amount*Double.parseDouble(sItem.getTax()))/(100+Double.parseDouble(sItem.getTax()));
                taxCgst=(taxamount*sItem.getTaxCgst())/(Double.parseDouble(sItem.getTax()));
                taxSgst=(taxamount*sItem.getTaxSgst())/(Double.parseDouble(sItem.getTax()));
                if(Double.isNaN(taxCgst) || Double.isNaN(taxSgst)){
                  cgst=0;
                vat=0;
                   
               }else{
                   cgst=taxCgst;
                   vat=taxSgst;
               }  
                itemAssessValue=amount-(cgst+vat);
                 assessValue=assessValue+itemAssessValue;
              }
              if(s.getCategory().equalsIgnoreCase("GST Dealers(B2B)")){
                taxCgst=(amount*sItem.getTaxCgst())/100;
                taxSgst=(amount*sItem.getTaxSgst())/100;

                cgst=taxCgst;
                vat=taxSgst;
                itemAssessValue=amount;
                 assessValue=assessValue+itemAssessValue;
              }
             
              totalVat=totalVat+vat;
              totalCgst=totalCgst+cgst;

              sItem.setCgst(cgst);
              sItem.setVat(vat);
              sItem.setTax(iMaster.getTr().toString());

              Double cpCpvAmount=sItem.getQuantity()*iMaster.getCp();
              itemVaa=itemAssessValue-cpCpvAmount;
              vaa=vaa+itemVaa;
              sItem.setCpcpvAmount(String.valueOf(cpCpvAmount));
              sItem.setWithoutTax(itemAssessValue);

              double mcMca=0;
              if(iMaster.getMc()!=null && iMaster.getMc().toString().length()>0){
                  mcMca=iMaster.getMc();
              }
              if(iMaster.getMca()!=null && iMaster.getMca().toString().length()>0){
                  mcMca=iMaster.getMca();
              }
              sItem.setMcmca(String.valueOf(mcMca));
              Double mcMcaAmount=sItem.getQuantity()*mcMca;
              mca=mca+mcMcaAmount;
              sItem.setMcmcaAmount(String.valueOf(mcMcaAmount));
              
               System.out.println("Direct Invoice :"+invoiceAmount);

              invoiceAmount=invoiceAmount+itemAssessValue+vat+cgst;
              
               System.out.println("Direct Invoice :"+invoiceAmount);
              
              sales.add(sItem);
              // chandra
              //Convert to sales item estimate item
              SalesEstimateItem sei=new SalesEstimateItem();
              sei.setItemCode(sItem.getItemCode());
              sei.setItemName(sItem.getItemName());
             
              
              sei.setQuantity((double )qty );
              sei.setUnit(sItem.getUnit());
              sei.setMargin(sItem.getMargin());
              sei.setRate(sItem.getRate());
              sei.setCpcpV(iMaster.getEp());
              double amountEstimate=qty*sItem.getRate();
              sei.setAmount(amountEstimate);
              
              
      
            if(s.getCategory().equalsIgnoreCase("Consumers(B2C)")){
                
                double taxamount=(amount*Double.parseDouble(sItem.getTax()))/(100+Double.parseDouble(sItem.getTax()));
                taxCgst=(taxamount*sItem.getTaxCgst())/(Double.parseDouble(sItem.getTax()));
                taxSgst=(taxamount*sItem.getTaxSgst())/(Double.parseDouble(sItem.getTax()));
                if(Double.isNaN(taxCgst) || Double.isNaN(taxSgst)){
                  cgst=0;
                vat=0;
                   
               }else{
                   cgst=taxCgst;
                   vat=taxSgst;
               }  
                itemAssessValue=amount-(cgst+vat);
                 assessValue=assessValue+itemAssessValue;
              }
              if(s.getCategory().equalsIgnoreCase("GST Dealers(B2B)")){
                taxCgst=(amount*sItem.getTaxCgst())/100;
                taxSgst=(amount*sItem.getTaxSgst())/100;

                cgst=taxCgst;
                vat=taxSgst;
                itemAssessValue=amount;
                 assessValue=assessValue+itemAssessValue;
              }

              sei.setVat(vat);
              sei.setCgst(cgst);
              double cpCpvAmountEstimate=qty*iMaster.getEp();
              sei.setCpcpvAmount(String.valueOf(cpCpvAmountEstimate));
              sei.setMcmca(sItem.getMcmca());
              double mcMcaAmountEstimate=qty*Double.parseDouble(sItem.getMcmca());
              sei.setMcmcaAmount(String.valueOf(mcMcaAmountEstimate));
              sei.setTax(sItem.getTax());
              
              salesEstimate.add(sei);
              
              vaaEstimate=vaaEstimate+cpCpvAmountEstimate;
              mcaEstimate=mcaEstimate+mcMcaAmountEstimate;
              assessValueEstimate=assessValueEstimate+amountEstimate;
              totalVatEstimate=totalVatEstimate+vat;
              totalCgstEstimate=totalCgstEstimate+cgst;
              invoiceAmountEstimate=invoiceAmountEstimate+assessValueEstimate+totalVatEstimate+totalCgstEstimate;
              
           }
           else{
             double ItemAvalue=0.0;
              totalVat=totalVat+sItem.getVat();
              totalCgst=totalCgst+sItem.getCgst();
              if(s.getCategory().equalsIgnoreCase("Consumers(B2C)")){
               vaa=vaa+((sItem.getWithoutTax())-(Double.parseDouble(sItem.getCpcpvAmount())));
               ItemAvalue=(sItem.getAmount()-sItem.getVat()-sItem.getCgst());
                assessValue=assessValue+ItemAvalue;
              }
              if(s.getCategory().equalsIgnoreCase("GST Dealers(B2B)")){
               vaa=vaa+((sItem.getWithoutTax())-(Double.parseDouble(sItem.getCpcpvAmount()))); 
               ItemAvalue=(sItem.getAmount());
                assessValue=assessValue+ItemAvalue;
              }
              
              mca=mca+Double.parseDouble(sItem.getMcmcaAmount());
              invoiceAmount=invoiceAmount+ItemAvalue+sItem.getVat()+sItem.getCgst();
              sales.add(sItem);
           }
           System.out.println("Down Direct Invoice -- :"+invoiceAmount);
           // Sales to Consume means Insert Sales Items
            if(s.getCategory().equalsIgnoreCase("Consumers(B2C)"))
            {
           salesService.saveSalesItem(sItem);
           String query="UPDATE `item_master` SET current_stock=current_stock-"+sItem.getQuantity()+" WHERE item_code='"+sItem.getItemCode()+"'";
           itemMasterService.updateItemMaster(query);
           
           ItemMaster iMaster_after_update=itemMasterService.getItmeByItemNo(sItem.getItemCode());
           ItemReport iReport=new ItemReport();
           iReport.setItemId(iMaster_after_update.getId());
           iReport.setItemName(iMaster_after_update.getItemName());
           iReport.setItemGroupId(iMaster_after_update.getItemGroup());
           iReport.setTransactionId((invoicNo));
           iReport.setTransactionName(BuyerName);
           iReport.setTransactionType("Sales");
           iReport.setTransactionDate(s.getDate());
           iReport.setOutQuantity((double) sItem.getQuantity());
           iReport.setOutValue((((double)sItem.getQuantity())*iMaster_after_update.getCp()));
           iReport.setClosingQuantity((iMaster_after_update.getCurrentStock()));
           iReport.setClosingValue(iMaster_after_update.getCurrentStock()*iMaster_after_update.getCp());

           itemReportService.addItemReport(iReport);
             }
           //find same tax item
           set.add(iMaster.getTr().toString());
           
       }
       
       // update sales after out of stock calculation
        if(s.getCategory().equalsIgnoreCase("Consumers(B2C)"))
        {
        s.setInvoiceNo(invoicNo);
        s.setVaa(vaa);
        if(s.getCategory().equalsIgnoreCase("Consumers(B2C)")){
          s.setAssessValue(assessValue);
        }
        if(s.getCategory().equalsIgnoreCase("GST Dealers(B2B)")){
           s.setAssessValue(assessValue);
        }
        s.setTotalVat(totalVat);
        s.setTotalCgst(totalCgst);
        s.setMca(mca);
        if(s.getAddLess()==null || s.getAddLess().toString().length()==0){
           s.setAddLess(0.0);
        }
        invoiceAmount=invoiceAmount+s.getAddLess();
        System.out.println(" s.setAddLess  "+ s.getAddLess()+"/ "+s.getActualMca()+"/ "+s.getInvoiceAmount());
        s.setInvoiceAmount(invoiceAmount);
        salesService.saveSales(s);
         }
       //Convert sales to sales estimate
       SalesEstimate se=new SalesEstimate();
       se.setDate(s.getDate());
       se.setCategory(s.getCategory());
       se.setCashBuyer(s.getCashBuyer());
       se.setTinNo(s.getTinNo());
       se.setMode(s.getMode());
       se.setNameOfBuyer(s.getNameOfBuyer());
       se.setBuyerId(s.getBuyerId());
       se.setNameOfMediator(s.getNameOfMediator());
       se.setMediatorId(s.getMediatorId());
       se.setVaa(vaaEstimate);
       se.setAssessValue(assessValueEstimate);
       se.setTotalVat(totalVatEstimate);
       se.setTotalCgst(totalCgstEstimate);
       se.setMca(mcaEstimate);
       se.setInvoiceAmount(invoiceAmountEstimate);
       se.setStatus("To Be Purchased");
       
      if(s.getCategory().equalsIgnoreCase("Consumers(B2C)"))
      {
       Entries e=new Entries();
       e.setEntryType("S");
       e.setDate(s.getDate());
       e.setCrTotal(invoiceAmount);
       e.setDrTotal(invoiceAmount);
       e.setBillType("Sales");
       e.setBillId(invoicNo);       
       entryId=entryService.saveEntry(e);
       
       Entryitems eItem=new Entryitems();
       Double currentBuyerBalance=openingBalanceOfLedger+invoiceAmount;
       eItem.setEntryId(entryId);
       eItem.setAmount(invoiceAmount);
       eItem.setLedgerId(buyerLedgerId);
       eItem.setType("D");
       if(currentBuyerBalance>0)
       eItem.setClosingAmtDr(currentBuyerBalance);
       else
       eItem.setClosingAmtCr( Math.abs(currentBuyerBalance));
      
       entryService.saveEntryItem(eItem);
      }
       Iterator itrSet=set.iterator();
       
        double totalVatEntry=0;
        double totalCgstEntry=0;
        double totalAllVatEntry=0;
        double totalAllCgstEntry=0;
        double totalMCAEntry=0;
       
      
        
       while(itrSet.hasNext()){
        double tax=Double.parseDouble(itrSet.next().toString());
        double assessValueEntry=0;
        double vatDealer_Vat=0.0;
        double cgstDealer_vat=0.0;
        double vat_VAA=0.0;
        double vat_MCA=0.0;
        double vat_invoice_amt=0.0;
        double addorLestfromInvoiceAmt=0.0;
        double rounding_invoice_amt=0.0;
        
        //Create tax wise Invoice for Vat Dealer 
            if(s.getCategory().equalsIgnoreCase("GST Dealers(B2B)"))
           {
          buyerLedgerId=ledgerAccountService.getLederByReferenceId(String.valueOf(s.getBuyerId()), "Buyer").iterator().next().getIdLedger();
          BuyerName=ledgerAccountService.getLederByReferenceId(String.valueOf(s.getBuyerId()), "Buyer").iterator().next().getNameOfLedger();     
          String yearRange=as.GetYearRange();
          long previousId=as.getPreviousIdBasedStartAndEndYear("invoiceNo", "Sales", yearRange);       
           String primaryIdPE="";  
             if(previousId==0){
                primaryIdPE=previousId+1+"/"+yearRange;
             }else{
                  primaryIdPE=previousId+1+"/"+yearRange;
             }
              ses.setAttribute("primaryIdPE", primaryIdPE);        

          s.setInvoiceNo(null);
          
          invoicNo=salesService.saveSales(s);
          }
            
            
           Iterator iter=sales.iterator();
           while(iter.hasNext()){
              SalesItem si=(SalesItem)iter.next();
              
              if(tax==Double.parseDouble(si.getTax())){
                  
              
                 // assessValueEntry=assessValueEntry+si.getAmount();
                  totalVatEntry=totalVatEntry+si.getVat();
                  totalAllVatEntry=s.getTotalVat();
                  totalAllCgstEntry=s.getTotalCgst();
                  totalCgstEntry=totalCgstEntry+si.getCgst();
                  
                  if(s.getCategory().equalsIgnoreCase("Consumers(B2C)")){
                      assessValueEntry=assessValueEntry+(si.getAmount()-si.getVat()-si.getCgst());
                    }
                    if(s.getCategory().equalsIgnoreCase("GST Dealers(B2B)")){
                         assessValueEntry=assessValueEntry+(si.getAmount());
                         vatDealer_Vat=vatDealer_Vat+si.getVat();
                         cgstDealer_vat=cgstDealer_vat+si.getCgst();                         
                         System.out.println(" assessValueEntry "+assessValueEntry+"/si.getCpcpvAmount() "+si.getCpcpvAmount()+"/");
                         vat_VAA=vat_VAA+(si.getAmount()-Double.parseDouble(si.getCpcpvAmount()));
                         vat_MCA=vat_MCA+Double.parseDouble(si.getMcmcaAmount());
                         vat_invoice_amt=vat_invoice_amt+(si.getAmount()+si.getVat()+si.getCgst());
                         // Vat Dealer Incoice Item list
                         si.setInvoiceNo(invoicNo);                         
                         salesService.saveSalesItem(si);
                            // Update Item_report
                            String query="UPDATE `item_master` SET current_stock=current_stock-"+si.getQuantity()+" WHERE item_code='"+si.getItemCode()+"'";
                            itemMasterService.updateItemMaster(query);

                            ItemMaster iMaster_after_update=itemMasterService.getItmeByItemNo(si.getItemCode());
                            ItemReport iReport=new ItemReport();
                            iReport.setItemId(iMaster_after_update.getId());
                            iReport.setItemName(iMaster_after_update.getItemName());
                            iReport.setItemGroupId(iMaster_after_update.getItemGroup());
                            iReport.setTransactionId((invoicNo));
                            iReport.setTransactionName(BuyerName);
                            iReport.setTransactionType("Sales");
                            iReport.setTransactionDate(s.getDate());
                            iReport.setOutQuantity((double) si.getQuantity());
                            iReport.setOutValue((((double)si.getQuantity())*iMaster_after_update.getCp()));
                            iReport.setClosingQuantity((iMaster_after_update.getCurrentStock()));
                            iReport.setClosingValue((iMaster_after_update.getCurrentStock()*iMaster_after_update.getCp()));

                            itemReportService.addItemReport(iReport);
                         
                    }
                   
              }
           }
           // update sales to vat dealer bill vat,mca,cgst,vaa
            if(s.getCategory().equalsIgnoreCase("GST Dealers(B2B)"))
                {
                s.setInvoiceNo(invoicNo);
                s.setVaa(vat_VAA);               
                if(s.getCategory().equalsIgnoreCase("GST Dealers(B2B)")){
                   s.setAssessValue(assessValueEntry);
                }
                s.setTotalVat(vatDealer_Vat);
                s.setTotalCgst(cgstDealer_vat);
                s.setMca(vat_MCA);
                int tax_set_Size=set.size();
                rounding_invoice_amt=Math.round(vat_invoice_amt)-vat_invoice_amt;
                
//                if(s.getAddLess()==null || s.getAddLess().toString().length()==0){
//                   s.setAddLess(0.0);
//                }
                    
                if(tax_set_Size==1)
                {  
                addorLestfromInvoiceAmt=vat_invoice_amt+s.getAddLess();    
                s.setAddLess(s.getAddLess());
                s.setInvoiceAmount(addorLestfromInvoiceAmt);         
             //       System.out.println(" 1 vaa "+vat_VAA+"/ vat_invoice_amt "+vat_invoice_amt+"/ addorLestfromInvoiceAmt "+addorLestfromInvoiceAmt+"/ s.getAddLess() "+s.getAddLess());
                }
                else
                {
                 addorLestfromInvoiceAmt=Math.round(vat_invoice_amt);   
                 s.setAddLess(rounding_invoice_amt);   
                 s.setInvoiceAmount(addorLestfromInvoiceAmt);
             //      System.out.println(" 2 vaa "+vat_VAA+"/ vat_invoice_amt "+vat_invoice_amt+"/ addorLestfromInvoiceAmt "+addorLestfromInvoiceAmt);
                }    
               
                salesService.saveSales(s);
                // Calculate Current Balance of Buyer for each tax Items
                double vat_buyer_balance=0.0;
                List<Object[]> currentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(buyerLedgerId), CurrentDate);
                if( currentBalance!=null && currentBalance.size()>0)
                {
                      for (Object[] column : currentBalance) {
                                 vat_buyer_balance = (Double)column[0]- (Double)column[1];
                         }

                }
                else
                {
                    List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(buyerLedgerId));
                    for (Object[] column : OpeningBalance) {
                                 vat_buyer_balance = (Double)column[0]- (Double)column[1];
                         }
                } 
                 
                     Entries e=new Entries();
                     e.setEntryType("S");
                     e.setDate(s.getDate());
                     e.setCrTotal(addorLestfromInvoiceAmt);
                     e.setDrTotal(addorLestfromInvoiceAmt);
                     e.setBillType("Sales");
                     e.setBillId(invoicNo);       
                     entryId=entryService.saveEntry(e);

                     Entryitems eItem=new Entryitems();
                     Double currentBuyerBalance=vat_buyer_balance+addorLestfromInvoiceAmt;
                     eItem.setEntryId(entryId);
                     eItem.setAmount(addorLestfromInvoiceAmt);
                     eItem.setLedgerId(buyerLedgerId);
                     eItem.setType("D");
                     if(currentBuyerBalance>0)
                     eItem.setClosingAmtDr(currentBuyerBalance);
                     else
                     eItem.setClosingAmtCr( Math.abs(currentBuyerBalance));

                     entryService.saveEntryItem(eItem);
               }
            
          
           
            if(tax==0 || tax==0.0)
            {
                 System.out.println("assessValueEntry  "+tax+"/"+assessValueEntry);
                 if(s.getCategory().equalsIgnoreCase("Consumers(B2C)"))
                 {
                  int salesLedgerId=18;
                  double openingBalancesalesLedgerId=0;        
                  List<Object[]> salesLedgerIdcurrentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(salesLedgerId), CurrentDate);
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
                  Double currentsalesLedgerIdBalance=openingBalancesalesLedgerId-assessValueEntry;
                  Entryitems eItem1=new Entryitems();
                  eItem1.setEntryId(entryId);
                  eItem1.setAmount(assessValueEntry);
                  eItem1.setLedgerId(salesLedgerId);
                  eItem1.setType("C");
                  if(currentsalesLedgerIdBalance>=0)
                  eItem1.setClosingAmtDr(currentsalesLedgerIdBalance);
                  else
                  eItem1.setClosingAmtCr( Math.abs(currentsalesLedgerIdBalance));
                  entryService.saveEntryItem(eItem1);   
                 }
                 // for Vat Dealer Access values Entry bill wise
                 else
                 {
                  int salesLedgerId=18;
                  double openingBalancesalesLedgerId=0;        
                  List<Object[]> salesLedgerIdcurrentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(salesLedgerId), CurrentDate);
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
                  Double currentsalesLedgerIdBalance=openingBalancesalesLedgerId-assessValueEntry;
                  Entryitems eItem1=new Entryitems();
                  eItem1.setEntryId(entryId);
                  eItem1.setAmount(assessValueEntry);
                  eItem1.setLedgerId(salesLedgerId);
                  eItem1.setType("C");
                  if(currentsalesLedgerIdBalance>=0)
                  eItem1.setClosingAmtDr(currentsalesLedgerIdBalance);
                  else
                  eItem1.setClosingAmtCr( Math.abs(currentsalesLedgerIdBalance));
                  entryService.saveEntryItem(eItem1);  
                 }    
            }
            else
            { 
                 System.out.println("assessValueEntry  "+tax+"/"+assessValueEntry);
                if(s.getCategory().equalsIgnoreCase("Consumers(B2C)"))
                {
                int salesLedgerId=ledgerAccountService.getLedgerByLedgerName("Sales @"+tax+"% GST").iterator().next().getIdLedger();
                double openingBalancesalesLedgerId=0;        
                List<Object[]> salesLedgerIdcurrentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(salesLedgerId), CurrentDate);
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
                Double currentsalesLedgerIdBalance=openingBalancesalesLedgerId-assessValueEntry;
                Entryitems eItem1=new Entryitems();
                eItem1.setEntryId(entryId);
                eItem1.setAmount(assessValueEntry);
                eItem1.setLedgerId(salesLedgerId);
                eItem1.setType("C");
                if(currentsalesLedgerIdBalance>=0)
                eItem1.setClosingAmtDr(currentsalesLedgerIdBalance);
                else
                eItem1.setClosingAmtCr( Math.abs(currentsalesLedgerIdBalance));
                entryService.saveEntryItem(eItem1);
                }
                // for Vat dealer access value entries
                else
                {
                  int salesLedgerId=ledgerAccountService.getLedgerByLedgerName("Sales @"+tax+"% GST").iterator().next().getIdLedger();
                double openingBalancesalesLedgerId=0;        
                List<Object[]> salesLedgerIdcurrentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(salesLedgerId), CurrentDate);
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
                Double currentsalesLedgerIdBalance=openingBalancesalesLedgerId-assessValueEntry;
                Entryitems eItem1=new Entryitems();
                eItem1.setEntryId(entryId);
                eItem1.setAmount(assessValueEntry);
                eItem1.setLedgerId(salesLedgerId);
                eItem1.setType("C");
                if(currentsalesLedgerIdBalance>=0)
                eItem1.setClosingAmtDr(currentsalesLedgerIdBalance);
                else
                eItem1.setClosingAmtCr( Math.abs(currentsalesLedgerIdBalance));
                entryService.saveEntryItem(eItem1);
                }
            }
            
            // put All enteies cgst and vat and add or less entries for Vat Dealer
            if(s.getCategory().equalsIgnoreCase("GST Dealers(B2B)"))
                {
                 int salesVatLedgerId=12;
                 int salesCgstLedgerId=11;               
                 List<Object[]> salesVatLedgerIdcurrentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(salesVatLedgerId), CurrentDate);
                 List<Object[]> salesCgstLedgerIdcurrentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(salesCgstLedgerId), CurrentDate);
                 double openingBalance_salesVatLedgerId=0;
                 double openingBalance_salesCgstLedgerId=0;
              

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
                      for (Object[] column : salesVatLedgerIdcurrentBalance) {
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
                   // // For Sales Vat  Ledger Entries  vatDealer_Vat cgstDealer_vat
                    Double currentVatLedgerIdBalance=openingBalance_salesVatLedgerId-vatDealer_Vat;
                    Entryitems eItem2=new Entryitems();
                    eItem2.setEntryId(entryId);
                    eItem2.setAmount(vatDealer_Vat);
                    eItem2.setLedgerId(salesVatLedgerId);
                    eItem2.setType("C");
                    if(currentVatLedgerIdBalance>=0)
                    eItem2.setClosingAmtDr(currentVatLedgerIdBalance);
                    else
                    eItem2.setClosingAmtCr( Math.abs(currentVatLedgerIdBalance));

                    entryService.saveEntryItem(eItem2);

                    // For Sales CGST Entries

                    Double currentCgstLedgerIdBalance=openingBalance_salesCgstLedgerId-cgstDealer_vat;
                    Entryitems eItem3=new Entryitems();
                    eItem3.setEntryId(entryId);
                    eItem3.setAmount(cgstDealer_vat);
                    eItem3.setLedgerId(salesCgstLedgerId);
                    eItem3.setType("C");
                    if(currentCgstLedgerIdBalance>=0)
                    eItem3.setClosingAmtDr(currentCgstLedgerIdBalance);
                    else
                    eItem3.setClosingAmtCr( Math.abs(currentCgstLedgerIdBalance));
                    entryService.saveEntryItem(eItem3);     
                    if(s.getAddLess()!=0)
                    {      
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
                        
                        Double currentSalesRounding=openingBalanceSalesRounding-rounding_invoice_amt;
                        Entryitems eItem4=new Entryitems();
                        eItem4.setEntryId(entryId);
                        eItem4.setAmount(rounding_invoice_amt);
                        eItem4.setLedgerId(sales_rounding_Ledger_id);
                        eItem4.setType("C");
                        if(currentSalesRounding>=0)
                        eItem4.setClosingAmtDr(currentSalesRounding);
                        else
                        eItem4.setClosingAmtCr( Math.abs(currentSalesRounding));
                        entryService.saveEntryItem(eItem4);
                        
                        System.out.println( "currentSalesRounding  "+currentSalesRounding);
                    }
                }
            
       }    
       
       if(s.getCategory().equalsIgnoreCase("Consumers(B2C)"))
       {
        int salesVatLedgerId=12;
        int salesCgstLedgerId=11;
        int salesMCALedgerId=16;
        int salesCASHMEDLedgerId=7;
        List<Object[]> salesVatLedgerIdcurrentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(salesVatLedgerId), CurrentDate);
        List<Object[]> salesCgstLedgerIdcurrentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(salesCgstLedgerId), CurrentDate);
        List<Object[]> salesMCALedgerIdcurrentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(salesMCALedgerId), CurrentDate);
        List<Object[]> salesCASHMEDLedgerIdcurrentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(salesCASHMEDLedgerId), CurrentDate);
      
       double openingBalance_salesVatLedgerId=0;
       double openingBalance_salesCgstLedgerId=0;
       double openingBalance_salesMCALedgerId=0;
       double openingBalance_salesCASHMEDLedgerId=0; 
        
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
             for (Object[] column : salesVatLedgerIdcurrentBalance) {
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
       
       if( salesMCALedgerIdcurrentBalance!=null && salesMCALedgerIdcurrentBalance.size()>0)
       {
             for (Object[] column : salesVatLedgerIdcurrentBalance) {
                        openingBalance_salesMCALedgerId = (Double)column[0]- (Double)column[1];
                }
           
       }
       else
       {
           List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(salesMCALedgerId));
           for (Object[] column : OpeningBalance) {
                        openingBalance_salesMCALedgerId = (Double)column[0]- (Double)column[1];
                }
       } 
       
       
       if( salesCASHMEDLedgerIdcurrentBalance!=null && salesCASHMEDLedgerIdcurrentBalance.size()>0)
       {
             for (Object[] column : salesVatLedgerIdcurrentBalance) {
                        openingBalance_salesCASHMEDLedgerId = (Double)column[0]- (Double)column[1];
                }
           
       }
       else
       {
           List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(salesCASHMEDLedgerId));
           for (Object[] column : OpeningBalance) {             
                        openingBalance_salesCASHMEDLedgerId = (Double)column[0]- (Double)column[1];
                }
       } 
         
            

          // // For Sales Vat  Ledger Entries  
           Double currentVatLedgerIdBalance=openingBalance_salesVatLedgerId-totalVatEntry;
           Entryitems eItem2=new Entryitems();
           eItem2.setEntryId(entryId);
           eItem2.setAmount(totalVatEntry);
           eItem2.setLedgerId(salesVatLedgerId);
           eItem2.setType("C");
           if(currentVatLedgerIdBalance>=0)
           eItem2.setClosingAmtDr(currentVatLedgerIdBalance);
           else
           eItem2.setClosingAmtCr( Math.abs(currentVatLedgerIdBalance));
         
           entryService.saveEntryItem(eItem2);
           
           // For Sales CGST Entries
      
           Double currentCgstLedgerIdBalance=openingBalance_salesCgstLedgerId-totalCgstEntry;
           Entryitems eItem3=new Entryitems();
           eItem3.setEntryId(entryId);
           eItem3.setAmount(totalCgstEntry);
           eItem3.setLedgerId(salesCgstLedgerId);
           eItem3.setType("C");
           if(currentCgstLedgerIdBalance>=0)
           eItem3.setClosingAmtDr(currentCgstLedgerIdBalance);
           else
           eItem3.setClosingAmtCr( Math.abs(currentCgstLedgerIdBalance));
           entryService.saveEntryItem(eItem3);      
           
           Double currentMCALedgerIdBalance=openingBalance_salesMCALedgerId+s.getActualMca();
           Entryitems eItem6=new Entryitems();
           eItem6.setEntryId(entryId);
           eItem6.setAmount(s.getActualMca());
           eItem6.setLedgerId(salesMCALedgerId);
           eItem6.setType("D");      
           if(currentMCALedgerIdBalance>=0)
           eItem3.setClosingAmtDr(currentMCALedgerIdBalance);
           else
           eItem3.setClosingAmtCr( Math.abs(currentMCALedgerIdBalance));
         
           entryService.saveEntryItem(eItem6);
           
           if(s.getNameOfMediator()!=null && s.getNameOfMediator().isEmpty()){
           
           Double currentCASHMEDLedgerIdBalance=openingBalance_salesCASHMEDLedgerId-s.getActualMca();
           Entryitems eItem5=new Entryitems();
           eItem5.setEntryId(entryId);
           eItem5.setAmount(s.getActualMca());
           eItem5.setLedgerId(salesCASHMEDLedgerId);
           eItem5.setType("C");         
           if(currentCASHMEDLedgerIdBalance>=0)
           eItem3.setClosingAmtDr(currentCASHMEDLedgerIdBalance);
           else
           eItem3.setClosingAmtCr( Math.abs(currentCASHMEDLedgerIdBalance));
           entryService.saveEntryItem(eItem5);
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
        
           Double currentSalesRounding=openingBalanceSalesRounding-s.getAddLess();
           Entryitems eItem4=new Entryitems();
           eItem4.setEntryId(entryId);
           eItem4.setAmount(s.getAddLess());
           eItem4.setLedgerId(sales_rounding_Ledger_id);
           eItem4.setType("C");
           if(currentSalesRounding>=0)
           eItem4.setClosingAmtDr(currentSalesRounding);
           else
           eItem4.setClosingAmtCr( Math.abs(currentSalesRounding));
          
         
           entryService.saveEntryItem(eItem4);
       }
       }
       //Save sales estimate
       if(salesEstimate.size()>0)
       {
          String yearRange1=as.GetYearRange();
          long previousId1=as.getPreviousIdBasedStartAndEndYear("invoiceNo", "SalesEstimate", yearRange1);      
          String primaryIdPE1="";  
          if(previousId1==0){
             primaryIdPE1=previousId1+1+"/"+yearRange1;
          }else{
               primaryIdPE1=previousId1+1+"/"+yearRange1;
          }
          ses.setAttribute("primaryIdPE", primaryIdPE1);   
          // end of finacial year  
        String salesEstimateId=salesEstimateService.saveSalesEstimate(se);
        Iterator iterEstimateList=salesEstimate.iterator();
        while(iterEstimateList.hasNext()){
            SalesEstimateItem seItem=(SalesEstimateItem)iterEstimateList.next();
            seItem.setInvoiceNo(salesEstimateId);
            salesEstimateService.saveSalesEstimateItem(seItem);
        }
       }
        // For Calculate Every Profit and loss account using Pre defined ledger accounts 
        int pl_Ledger_id=2;
        List<Object[]> salesRoundingcurrentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(pl_Ledger_id), CurrentDate);
        double openingBalancePL=0;
        if( salesRoundingcurrentBalance!=null && salesRoundingcurrentBalance.size()>0)
       {
             for (Object[] column : salesRoundingcurrentBalance) {
                        openingBalancePL = (Double)column[0]- (Double)column[1];
                }

       }
       else
       {
           List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(pl_Ledger_id));
           for (Object[] column : OpeningBalance) {
                        openingBalancePL = (Double)column[0]- (Double)column[1];
                }
       } 
       
       double plamount= as.calculateNetProfit_Every_transcation(CurrentDate, CurrentDate);
       System.out.println(" plamount "+plamount);
       Entryitems eItem7=new Entryitems();
           eItem7.setEntryId(entryId);
           eItem7.setAmount(plamount);
           eItem7.setLedgerId(pl_Ledger_id);
           eItem7.setType("D");
           if(plamount>=0)
           eItem7.setClosingAmtDr(plamount);
           else
           eItem7.setClosingAmtCr(Math.abs(plamount));
         
           entryService.saveEntryItem(eItem7);
      // return new ModelAndView("redirect:editSales.html?id="+invoicNo+"");
      return new ModelAndView("redirect:SalesGrid.html");
    }
}
@RequestMapping(value={"SaleReportForm"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView SaleReportForm()
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
    
       ModelAndView model = new ModelAndView("SaleReportForm");
       model.addObject("curDate", dt);
       model.addObject("finalDate", finalDate);
       return model;
  }
  @RequestMapping(value={"GetSaleReport"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView GetSaleReport(HttpServletRequest req,HttpServletResponse response)throws ParseException, JRException, IOException
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
    
   
    String reportfile = req.getServletContext().getRealPath("//Reports//Sale_register.jrxml");

    CompanyInformation u = companyservice.getCompanyById(1);
//      System.out.println("Company Name:"+u.getCompanyName());
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
        response.setHeader("Content-Disposition", "inline;filename=SaleReport.xlsx");
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
  @RequestMapping(value="SalesReport",method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView SalesReport(HttpSession session){
        
//      salesService
    
        ModelAndView model=new ModelAndView("SalesReport");
      
        return model;
        
    }
     @RequestMapping(value="SalesReportbydate.html",method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public ModelAndView SalesReportbydate(HttpSession session,HttpServletRequest req) throws ParseException{
        
       String fromdate=req.getParameter("FromDate");
         System.out.println("fromdate"+fromdate);
       String todate=req.getParameter("ToDate");
        System.out.println("todate"+todate);
//     String newFormat = "dd-MM-yyyy";
//     String oldFormat = "yyyy-MM-dd";
//      SimpleDateFormat sdf1 = new SimpleDateFormat(oldFormat);
//    SimpleDateFormat sdf2 = new SimpleDateFormat(newFormat);
//    String fdate=sdf2.format(sdf1.parse(fromdate));
//         System.out.println("fdate"+fdate);
//    String tdate=sdf2.format(sdf1.parse(todate));
//    System.out.println("tdate"+tdate);
    
     
        ModelAndView model=new ModelAndView("SalesReport");
      model.addObject("saleamt",salesService.getSalesamt(fromdate, todate));
      model.addObject("cashamt",salesService.getcashamt(fromdate, todate));
      model.addObject("cardamt",salesService.getcardamt(fromdate, todate));
      model.addObject("voidamt",salesService.getvoidamt(fromdate, todate));
        return model;
        
    }
  
 
 @RequestMapping(value={"GetDatatablesGrid"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public DatatableObject getsDatatable(HttpServletRequest req,RedirectAttributes redirect)
    {
    DatatableObject db =new DatatableObject();
       
    String[] cols = {"p.invoice_no", "sdate", "waiter_name" ,"C_C_total","nettotal","b.kot_nos","cancledbill","payementmode","cancledbill","cancledbill"};
    
    int amount = 10;
    int start = 0;
    int echo = 0;
    int col = 0;
    
     
    String engine = "";
    String browser = "";
    String platform = "";
    String version = "";
    String grade = "";
 
    String dir = "desc";
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
        if (col < 0 || col > 10)
            col = 0;
    }
    if (sdir != null) {
        if (!sdir.equals("asc"))
            dir = "desc";
    }
    String colName = cols[col];
    int total = 0;
    
    try {
        String sql = "SELECT p.invoice_no,p.sdate,p.waiter_name,b.table_name,p.nettotal,b.kot_nos,p.payementmode,p.cancledbill,p.rgb,'',b.kot_ids  FROM sale_invoice AS p INNER JOIN sales_bill AS b ON p.invoice_no=b.invoice_no GROUP BY invoice_no";
        System.out.println("SQl statement is " + sql);
        List<Object[]> list = salesService.GetDatatableSalesObject(sql);
        System.out.println("KOT List" + list);
        total=list.size();
        System.out.println("the list size KOT" + total);
        
    }catch(Exception e){                        
         
    }
    int totalAfterFilter = total;
    //result.put("sEcho",echo);
 
    try {
        String searchSQL = "";
        String sql = "SELECT p.invoice_no,p.sdate,p.waiter_name,b.table_name,p.nettotal,b.kot_nos,p.payementmode,p.cancledbill,p.rgb,'',b.kot_ids  FROM sale_invoice AS p INNER JOIN sales_bill AS b ON p.invoice_no=b.invoice_no";
        String searchTerm =req.getParameter("search[value]");
        System.out.println(" Valahsdfh "+searchTerm);
        String globeSearch =  " where (p.invoice_no like '%"+searchTerm+"%' OR p.sdate like '"+searchTerm+"%' OR p.payementmode like '"+searchTerm+"%') GROUP BY invoice_no";
    
        if(searchTerm!=""){
            searchSQL=globeSearch;                             
        }
        else{
            searchSQL=" GROUP BY invoice_no"; 
        }
        sql += searchSQL;
        sql += " order by " + colName + " " + dir;
        sql += " limit " + start + ", " + amount;
        System.out.println(" SQL abi "+sql);
        // For aData
         List<Object[]> list2 = salesService.GetDatatableObject(sql);
          String sql2 = "SELECT p.invoice_no,p.sdate,p.waiter_name,b.table_name,p.nettotal,b.kot_nos,p.payementmode,p.cancledbill,p.rgb,'',b.kot_ids  FROM sale_invoice AS p INNER JOIN sales_bill AS b ON p.invoice_no=b.invoice_no";
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
    @RequestMapping(value={"GetDatatablesAuditGrid"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public DatatableObject getsAuditDatatable(HttpServletRequest req,RedirectAttributes redirect)
    {
    DatatableObject db1 =new DatatableObject();
       
    String[] cols = {"p.invoice_no", "sdate", "waiter_name" ,"C_C_total","nettotal","b.kot_nos"};
    
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
        if (col < 0 || col > 7)
            col = 0;
    }
    if (sdir != null) {
        if (!sdir.equals("asc"))
            dir = "desc";
    }
    String colName = cols[col];
    int total = 0;
    
    try {
        String sql = "SELECT p.invoice_no as inv_no,p.sdate,p.waiter_name,b.table_name,p.audittotalamount,b.kot_nos,''FROM sale_invoice AS p INNER JOIN sales_bill AS b ON p.invoice_no=b.invoice_no GROUP BY p.invoice_no";
        System.out.println("SQl statement is " + sql);
        List<Object[]> list = salesService.GetDatatableSalesObject(sql);
        System.out.println("KOT List" + list);
        total=list.size();
        System.out.println("the list size KOT" + total);
        
    }catch(Exception e){                        
         
    }
    int totalAfterFilter = total;
    //result.put("sEcho",echo);
 
    try {
        String searchSQL = "";
        String sql = "SELECT p.invoice_no as inv_no,p.sdate,p.waiter_name,b.table_name,p.audittotalamount,b.kot_nos,'' FROM sale_invoice AS p INNER JOIN sales_bill AS b ON p.invoice_no=b.invoice_no";
        String searchTerm =req.getParameter("search[value]");
        System.out.println(" Valahsdfh "+searchTerm);
        String globeSearch =  " where (p.invoice_no like '"+searchTerm+"%') GROUP BY p.invoice_no";
    
        if(searchTerm!=""){
            searchSQL=globeSearch;                             
        }
        else{
            searchSQL=" GROUP BY p.invoice_no"; 
        }
        sql += searchSQL;
        sql += " order by " + colName + " " + dir;
        sql += " limit " + start + ", " + amount;
        System.out.println(" SQL abi "+sql);
        // For aData
         List<Object[]> list2 = salesService.GetDatatableObject(sql);
         String sql2 = "SELECT p.invoice_no as inv_no,p.sdate,p.waiter_name,b.table_name,p.audittotalamount,b.kot_nos,'' FROM sale_invoice AS p INNER JOIN sales_bill AS b ON p.invoice_no=b.invoice_no";
       if (searchTerm != "") {
            sql2 += searchSQL;
          List<Object[]> count = purchaseService.GetDatatableCount(sql2);
          totalAfterFilter=count.size();
        } 


        db1.setiTotalRecords(total);
        db1.setiTotalDisplayRecords(totalAfterFilter);
        db1.setAaData(list2);
        } catch (Exception e) {
 
    }
      return db1;
}
    
     @RequestMapping(value="SalesReportByItem",method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView SalesReportByItem(HttpSession session,HttpServletRequest req) throws ParseException{
     
        ModelAndView model=new ModelAndView("SalesReportByItem");
        model.addObject("ItemList", itemMasterService.getItemList());
     return model;
    }
   
           
    @RequestMapping(value="SalesReportItem",method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public ModelAndView SalesReportItem(HttpSession session,HttpServletRequest req,RedirectAttributes redirectAttributes) throws ParseException{
        System.out.println("hi");
      String FDate=req.getParameter("FromDate");
      String ToDate=req.getParameter("ToDate");
      String itemname=req.getParameter("itemname");
      
     ModelAndView model=new ModelAndView("SalesReportByItem");
     List<Object[]> Object =  salesService.getItemSalesReport(itemname, FDate, ToDate);
         model.addObject("iList", Object);
                   int s=Object.size();
                   if(s==0)
                    {
                     ModelAndView model1 = new ModelAndView("redirect:SalesReportByItem.html");
                     redirectAttributes.addFlashAttribute("msg","No Records Found !");
                      return model1;
                    }
            model.addObject("ItemList", itemMasterService.getItemList()); 
          
       return model;
    }       
           
           
}




    
