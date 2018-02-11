
package com.accounting.controller;

import com.accounting.bean.CcodeMaster;
import com.accounting.bean.DatatableObject;
import com.accounting.bean.DefaultSettings;
import com.accounting.bean.ItemMaster;
import com.accounting.bean.ItemReport;
import com.accounting.bean.PredefinedBill;
import com.accounting.bean.PredefinedBillForm;
import com.accounting.bean.PredefinedBillItem;
import com.accounting.bean.PurchaseItem;
import com.accounting.bean.Sales;
import com.accounting.bean.SalesEstimate;
import com.accounting.bean.SalesEstimateItem;
import com.accounting.bean.SalesForm;
import com.accounting.bean.SalesItem;
import com.accounting.bean.TaxStructure;
import com.accounting.service.CcodeService;
import com.accounting.service.CompanyService;
import com.accounting.service.DefaultSetting_service;
import com.accounting.service.ItemMasterService;
import com.accounting.service.PredefinedBillService;
import com.accounting.service.SalesService;
import com.accounting.service.TaxStructure_Service;
import com.accounting.service.Unit_service;
import com.accounting.validator.PredefinedbillFormValidator;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList; 
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator; 
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
public class PredefinedBillTableController {
    
  @Autowired
  private PredefinedbillFormValidator predefinedbillFormValidator;
  
  @Autowired
  private PredefinedBillService predefinedBillService;
  
  @Autowired
    private Unit_service unitService;
  
   @Autowired
    private TaxStructure_Service tServ;
   
   
   @Autowired
    private ItemMasterService itemMasterService;
   
   @Autowired
    private CcodeService cService;
   
   @Autowired
    private SalesService salesService;
    @Autowired
    private CompanyService companyservice;
   @Autowired
    private DefaultSetting_service des;
    DefaultSettings ds=new DefaultSettings();
    DecimalFormat df = new DecimalFormat("#.##");     
   @InitBinder("predefineFrom")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(predefinedbillFormValidator);     
    }
    
    @RequestMapping(value={"PredefinedBill"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView PredefinedBillinformation()
  {
   PredefinedBill p=new PredefinedBill();
    PredefinedBillForm pDForm=new PredefinedBillForm();
    PredefinedBillItem pDItem=new PredefinedBillItem();
    List<PredefinedBillItem> list=new ArrayList<PredefinedBillItem>();
    list.add(pDItem);   
    pDForm.setSales(list);
    pDForm.setSale(p);
    ModelAndView model = new ModelAndView("PredefinedBill");
    if(des.getDefaultbyId(1)!=null){
    ds=des.getDefaultbyId(1);
    p.setCategory(ds.getSalesCategory());
    model.addObject("CategoryAuto", ds.getSalesCategory());
    }
    else        
    model.addObject("CategoryAuto", "");
    String CompanyGstno="";
    if(companyservice.getCompanyById(1)!=null)
    {
      CompanyGstno=companyservice.getCompanyById(1).getCompanyTin();
      CompanyGstno=CompanyGstno.substring(0, 2);
      model.addObject("CompanyGstno",CompanyGstno);
    }
    model.addObject("CompanyGstno",CompanyGstno);
    model.addObject("predefineFrom", pDForm);
    model.addObject("unitList", unitService.listUnit());
    model.addObject("taxStructure", tServ.getTaxId(1));
    
    HashSet<String> set=new HashSet<String> ();
    List cCodelist=cService.listCode();
    Iterator itr=cCodelist.iterator();
    while(itr.hasNext()){
        CcodeMaster c=(CcodeMaster)itr.next();
        set.add(c.getTaxRate().toString());
    }
    model.addObject("cCodeList", set);
    
    return model;
}

@RequestMapping(value={"SavePredefinedBill"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
public ModelAndView saveItem(@ModelAttribute("predefineFrom")@Validated PredefinedBillForm predefineFrom, BindingResult result,RedirectAttributes redirect)
{
    
    if (result.hasErrors()) {
        return new ModelAndView("PredefinedBill");
        
    }
    else{
     // code to ignore validator(itemCode) while removing addRow --- Starts(Abi-20.07.2017)   
    List<PredefinedBillItem> listPreDefined= predefineFrom.getSales();
    PredefinedBillItem preDItem=new PredefinedBillItem();
    List<PredefinedBillItem> listNew =new ArrayList<PredefinedBillItem>();
    Iterator<PredefinedBillItem> i = listPreDefined.iterator();
    while (i.hasNext())
    {
        preDItem = i.next();
        // this condition exclude deleted empty row of data
        if( preDItem.getItemCode()==null || preDItem.getItemCode().isEmpty())
        {
           
        }
        else
        {
          // here add existing data to new arraylist  
          listNew.add(preDItem);
        }    

    } 
    predefineFrom.setSales(listNew);
    
     // ---- End(Abi 20.07.2017)
    
        PredefinedBill p=predefineFrom.getSale();
       int predefinedBillId=predefinedBillService.savepredefinedBill(p);
       System.out.println("invoicNo :"+predefinedBillId);
        predefinedBillService.deletepredefinedBillItem(String.valueOf(predefinedBillId));
       List list=predefineFrom.getSales();       
       Iterator itr=list.iterator();
       while(itr.hasNext()){
           PredefinedBillItem pDForm=(PredefinedBillItem)itr.next();
           pDForm.setPredefinedBillId(predefinedBillId);
           pDForm.setId(null);
           predefinedBillService.savepredefinedBillItem(pDForm);
       }
       return new ModelAndView("redirect:PredefinedBillGrid.html");
    }
}



 @RequestMapping(value={"PredefinedBillGrid"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView SalesGrid()
  {
    PredefinedBillForm pDForm=new PredefinedBillForm();
    ModelAndView model = new ModelAndView("PredefinedBillGrid");    
    model.addObject("predefinedBillinfo",predefinedBillService.listpredefinedBill());
    model.addObject("predefineFrom", pDForm);
    return model;
  }
  
@RequestMapping(value={"editpredefined"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
public ModelAndView editSales(@RequestParam("id") String id, RedirectAttributes redirect)
{
    
    if(id!=null && id.length()>0){
        
      PredefinedBill p=predefinedBillService.getpredefinedBillById(Integer.parseInt(id)); 
      List list=predefinedBillService.getpredefinedBillItemBypredefinedBillId(Integer.parseInt(id));
    
   List<PredefinedBillItem> saleList=new ArrayList<PredefinedBillItem>();
      Iterator itr = list.iterator();
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
    
    while (itr.hasNext())
    {
        
      PredefinedBillItem pItem = (PredefinedBillItem)itr.next();
      ItemMaster iMaster = (ItemMaster)this.itemMasterService.getItmeByItemNo(pItem.getItemCode());
      
      TaxStructure ts=tServ.getTaxId(1);
      double rate=0;
      double cpValue=0.00;
          if(p.getCategory().equalsIgnoreCase("Consumers(B2C)")){
           
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
          if(p.getCategory().equalsIgnoreCase("GST Dealers(B2B)")){
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
             
              pItem.setAmount(Double.valueOf(df.format(amount)));
              pItem.setRate(rate);
              double taxValue=0.0;
              double cgst=0.0;
              double igst=0.0;
              double vat=0.0;
              double itemAssessValue=0.0;
              double itemVaa=0.0;
      
            double taxCgst=0.0;
              double taxSgst=0.0;
              double taxIgst=0.0;
      
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
              pItem.setTax(iMaster.getTr().toString());
              pItem.setIgst(Double.valueOf(df.format(igst)));

              Double cpCpvAmount=pItem.getQuantity()*cpValue;
              itemVaa=itemAssessValue-cpCpvAmount;
             System.out.println("itemAssessValue "+itemAssessValue +" cpCpvAmount /"+cpCpvAmount);
              vaa=vaa+itemVaa;
            
              pItem.setCpcpvAmount(String.valueOf(cpCpvAmount));
              pItem.setCpcpV(Double.valueOf(df.format(pItem.getCpcpV())));
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
       p.setMca(mca);
        if(p.getAddLess()==null || p.getAddLess().toString().length()==0){
           p.setAddLess(0.0);
        }
        invoiceAmount=invoiceAmount+p.getAddLess();
       
        p.setInvoiceAmount(invoiceAmount);
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
       p.setMca(mca);
        if(p.getAddLess()==null || p.getAddLess().toString().length()==0){
           p.setAddLess(0.0);
        }
        invoiceAmount=invoiceAmount+p.getAddLess();
       
        p.setInvoiceAmount(invoiceAmount);
         }
          saleList.add(pItem);   
    }
      
        p.setTotalVat(Double.valueOf(df.format(totalVat)));
        p.setTotalCgst(Double.valueOf(df.format(totalCgst)));
        p.setTotalIgst(Double.valueOf(df.format(totalIgst)));
    
      PredefinedBillForm pDForm=new PredefinedBillForm();
      pDForm.setSale(p);
      pDForm.setSales(saleList);
      ModelAndView model = new ModelAndView("PredefinedBill");
        p.getPredefinedBillId();
         model.addObject("pId", p.getPredefinedBillId());
      model.addObject("predefineFrom", pDForm);
      model.addObject("unitList", unitService.listUnit());
    model.addObject("taxStructure", tServ.getTaxId(1));
    String CompanyGstno="";
    if(companyservice.getCompanyById(1)!=null)
    {
      CompanyGstno=companyservice.getCompanyById(1).getCompanyTin();
      CompanyGstno=CompanyGstno.substring(0, 2);
      model.addObject("CompanyGstno",CompanyGstno);
    }
    model.addObject("CompanyGstno",CompanyGstno);
    HashSet<String> sett=new HashSet<String> ();
    List cCodelist=cService.listCode();
    Iterator itrr=cCodelist.iterator();
    while(itrr.hasNext()){
        CcodeMaster c=(CcodeMaster)itrr.next();
        sett.add(c.getTaxRate().toString());
    }
      model.addObject("cCodeList", sett);
      return model;
    }
   else{
        ModelAndView model = new ModelAndView("redirect:PredefinedBillGrid.html");
       return model;
   }

}
  
   
  
@RequestMapping(value={"deletepredefined"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
public ModelAndView deletepredefined(@RequestParam("id") String id, RedirectAttributes redirect)
{
    ModelAndView model = new ModelAndView("redirect:PredefinedBillGrid.html");
   if(id!=null && id.length()>0){
       predefinedBillService.deletepredefinedBill(id);
       predefinedBillService.deletepredefinedBillItem(id);
       return model;
   }
   else{
       return model;
   }

}
    
     

@RequestMapping(value={"predefinedbill_addrow"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
public ModelAndView predefinedbill_addrow(@ModelAttribute("predefineFrom") PredefinedBillForm predefineFrom, HttpServletRequest req)
{
    
    PredefinedBillItem pDItem_plus=new PredefinedBillItem();
    PredefinedBillItem pDItem=new PredefinedBillItem();
    PredefinedBillForm pDForm=predefineFrom;
    
    List<PredefinedBillItem> list= pDForm.getSales();
    List<PredefinedBillItem> listNew =new ArrayList<PredefinedBillItem>();
    Iterator<PredefinedBillItem> i = list.iterator();
    while (i.hasNext())
    {
        pDItem = i.next();
        // this condition exclude deleted empty row of data
        if(pDItem.getItemCode()=="" || pDItem.getItemCode()==null)
        {
           
        }
        else
        {
          // here add existing data to new arraylist  
          listNew.add(pDItem);
        }    

    }
    listNew.add(pDItem_plus);    // this step add empty row into arraylist
    pDForm.setSales(listNew); //setting all data with empty row into form
    
    ModelAndView model = new ModelAndView("predefinedbill_addrow");
    model.addObject("unitList", unitService.listUnit());
    model.addObject("taxStructure", tServ.getTaxId(1));
    model.addObject("predefineFrom", pDForm);
    HashSet<String> set=new HashSet<String> ();
    List cCodelist=cService.listCode();
    Iterator itr=cCodelist.iterator();
    while(itr.hasNext()){
        CcodeMaster c=(CcodeMaster)itr.next();
        set.add(c.getTaxRate().toString());
    }
     model.addObject("cCodeList", set);
    return model;
}
@ResponseBody
   @RequestMapping(value={"GetDatatablePredefined"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   
    public DatatableObject getDatatableBuyer( HttpServletRequest req)
    {
      DatatableObject db =new DatatableObject();
      
           String[] cols = {  "predefined_bill_id", "category", "bill_name","assess_value","vat","add_or_less","invoice_amount" };
    String table = "predefined_bill";
    

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
        String sql = "SELECT predefined_bill_id,category,bill_name,assess_value,vat,add_or_less,invoice_amount from predefined_bill";
        System.out.println("abi" + sql);
        List<Object[]> list = predefinedBillService.GetDatatablePredefinedObject(sql);
        total=list.size();
        System.out.println("the list size predefined" + total);
        
    }catch(Exception e){
         
    }
    int totalAfterFilter = total;
    //result.put("sEcho",echo);
 
    try {
        String searchSQL = "";
        String sql = "SELECT predefined_bill_id,category,bill_name,assess_value,total_vat,add_less,invoice_amount,amount,discount from predefined_bill";
        String searchTerm =req.getParameter("search[value]");
        System.out.println(" Valahsdfh "+searchTerm);
         String globeSearch =  " where (predefined_bill_id like '"+searchTerm+"%')";
    
        if(searchTerm!=""){
            searchSQL=globeSearch;
        }
        sql += searchSQL;
        sql += " order by " + colName + " " + dir;
        sql += " limit " + start + ", " + amount;
        System.out.println(" SQL abi "+sql);
        // For aData
         List<Object[]> list2 = predefinedBillService.GetDatatablePredefinedObject(sql);
         // For Filter Count 
        String sql2 = "SELECT predefined_bill_id,category,bill_name,assess_value,total_vat,add_less,invoice_amount,amount,discount from predefined_bill";
       if (searchTerm != "") {
            sql2 += searchSQL;
          List<Object[]> count = predefinedBillService.GetDatatablePredefinedCount(sql2);
          totalAfterFilter=count.size();
        }
  
   db.setiTotalRecords(total);
   db.setiTotalDisplayRecords(totalAfterFilter);
   db.setAaData(list2);
    } catch (Exception e) {
 
    }
      return db;
	}  


@RequestMapping(value={"PreConvertToSales"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
public ModelAndView PreConvertToSales(@RequestParam("eId") String id, RedirectAttributes redirect) throws ParseException
{
    
    if(id!=null && id.length()>0){
      PredefinedBill sale=predefinedBillService.getpredefinedBillById(Integer.parseInt(id));
      List list=predefinedBillService.getpredefinedBillItemBypredefinedBillId(Integer.parseInt(id));
     
      Sales p=new Sales();
      p.setCategory(sale.getCategory());
      p.setTaxPercentage(sale.getTaxPercentage());
      p.setVaa(sale.getVaa());
      p.setMca(sale.getMca());
      p.setAssessValue(sale.getAssessValue());
      p.setTotalCgst(sale.getTotalCgst());
      p.setTotalVat(sale.getTotalVat());
      p.setTotalIgst(sale.getTotalIgst());
      p.setAddLess(sale.getAddLess());
      p.setInvoiceAmount(sale.getInvoiceAmount());
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
          PredefinedBillItem pItem=(PredefinedBillItem)itr.next();
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
              double igst=0.0;
              double cgst=0.0;
              double vat=0.0;
              double itemAssessValue=0.0;
              double itemVaa=0.0;
              double taxCgst=0.0;
              double taxSgst=0.0;
              double taxIgst=0.0;
      
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
              pItem.setTax(iMaster.getTr().toString());
              pItem.setIgst(Double.valueOf(df.format(igst)));

              Double cpCpvAmount=pItem.getQuantity()*cpValue;
              itemVaa=itemAssessValue-cpCpvAmount;
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
       
        p.setInvoiceAmount(invoiceAmount);
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
       
        p.setInvoiceAmount(invoiceAmount);
         }
          sItem.setItemCode(pItem.getItemCode());
          sItem.setTax(pItem.getTax());
          sItem.setCpcpvAmount(pItem.getCpcpvAmount());
          sItem.setMcmcaAmount(pItem.getMcmcaAmount());
          sItem.setMcmca(pItem.getMcmca());
          sItem.setWithoutTax(itemAssessValue);
          sItem.setItemName(pItem.getItemName());
          sItem.setQuantity(pItem.getQuantity());
          sItem.setUnit(pItem.getUnit());
          sItem.setMargin(pItem.getMargin());
          sItem.setRate(rate);
          sItem.setCpcpV(Double.valueOf(df.format(pItem.getCpcpV())));
          sItem.setCgst(pItem.getCgst());
          sItem.setVat(pItem.getVat());
          sItem.setIgst(pItem.getIgst());
          sItem.setAmount(Double.valueOf(df.format(amount)));
          sItem.setTaxCgst(pItem.getTaxCgst());
          sItem.setTaxSgst(pItem.getTaxSgst());
          sItem.setTaxIgst(pItem.getTaxIgst());
          saleList.add(sItem);
  }
       ModelAndView model = new ModelAndView("Sales");   
       // Required Value for Sales Page
       
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
      SalesForm sForm=new SalesForm();
      sForm.setSale(p);
      sForm.setSales(saleList);
      
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
    else{
        ModelAndView model = new ModelAndView("redirect:SalesGrid.html");
       return model;
   }

}
    
    
}
