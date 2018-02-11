/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.controller;

import com.accounting.bean.CcodeMaster;
import com.accounting.bean.CompanyInformation;
import com.accounting.bean.LedgerAccountMaster;
import com.accounting.service.CcodeService;
import com.accounting.service.CompanyService;
import com.accounting.service.LedgerAccount_Service;
import com.accounting.validator.UserFormValidator;
import java.io.IOException;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author SHINELOGICS
 */
@Controller
public class CcodeController {
  @Autowired
  private CcodeService cService;
  
  @Autowired
    private LedgerAccount_Service lgs;
  @Autowired
    private SessionFactory sessionFactory;
@Autowired
  private CompanyService companyservice;
    
  @RequestMapping(value={"/ccode"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView ccode()
  {
    CcodeMaster code = new CcodeMaster();

    ModelAndView model = new ModelAndView("ccode");
    model.addObject("codeform", code);
    model.addObject("codeList", cService.listCode());
    return model;
  }
  
 
  @RequestMapping(value={"save_ccode"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView save(@ModelAttribute("userForm")  CcodeMaster code, BindingResult result,HttpServletRequest req,final RedirectAttributes redirectAttributes)
  {
      
       String message="";
       String hiddenValue=req.getParameter("hidecode"); 
       
       Integer id=code.getCcodeId();
       int count=0;
       if(id==null )
       {
         List list= cService.listCode();  
         count=list.size();
         code.setCcodeId(null);
         String value=code.getCcode();
       Boolean checkLedger=cService.checkCcode(value);
               String notification="";
                if(checkLedger)
               {
                notification="HSN Code you have entered is Already Exist!";
                ModelAndView model = new ModelAndView("ccode");
                 model.addObject("val",notification);
                 model.addObject("codeform", code);
                model.addObject("codeList", cService.listCode());
      return model;
               }
                message="Record Added Successfully"; 
       }
       else
       {
        List list= cService.listCode();  
        count=list.size()-1;   
        code.setCcodeId(id);   
        String formValue=code.getCcode();
        
        
        if(!hiddenValue.equals(formValue)){
        String value=code.getCcode();
       Boolean checkLedger=cService.checkCcode(value);
               String notification="";
                if(checkLedger)
               {
                notification="HSN Code you have entered is Already Exist!";
                ModelAndView model = new ModelAndView("ccode");
                 model.addObject("val",notification);
                 model.addObject("codeform", code);
                model.addObject("codeList", cService.listCode());
      return model;
               }
                 message="Record Updated Successfully"; 
        }
       }   
       
                cService.saveCode(code);
                
//                if(getCode>0){
//                
//                if(!lgs.checkLedgerName("Sales @"+code.getTaxRate().toString().trim()+"% GST")){
//          LedgerAccountMaster lam=new LedgerAccountMaster();
//          lam.setNameOfLedger("Sales @"+code.getTaxRate().toString().trim()+"% GST");
////          lam.setPredefined(1);
//          lam.setUnderGroup(25);
//          lgs.addLedgerAccount(lam);
//       }
//       if(!lgs.checkLedgerName("Sales Return @"+code.getTaxRate().toString().trim()+"% GST")){
//          LedgerAccountMaster lam=new LedgerAccountMaster();
//          lam.setNameOfLedger("Sales Return @"+code.getTaxRate().toString().trim()+"% GST");
////          lam.setPredefined(1);
//          lam.setUnderGroup(25);
//          lgs.addLedgerAccount(lam);
//       }
//       if(!lgs.checkLedgerName("Purchase @"+code.getTaxRate().toString().trim()+"% GST")){
//          LedgerAccountMaster lam=new LedgerAccountMaster();
//          lam.setNameOfLedger("Purchase @"+code.getTaxRate().toString().trim()+"% GST");
// //         lam.setPredefined(1);
//          lam.setUnderGroup(22);
//          lgs.addLedgerAccount(lam);
//       }
//       if(!lgs.checkLedgerName("Purchase Return @"+code.getTaxRate().toString().trim()+"% GST")){
//          LedgerAccountMaster lam=new LedgerAccountMaster();
//          lam.setNameOfLedger("Purchase Return @"+code.getTaxRate().toString().trim()+"% GST");
////          lam.setPredefined(1);
//          lam.setUnderGroup(22); 
//          lgs.addLedgerAccount(lam);
//       }
//                
//                }
           ModelAndView model = new ModelAndView("redirect:ccode.html");          
           redirectAttributes.addFlashAttribute("message", message);
        return model;
        }
  
  @RequestMapping(value={"/CcodeEdit"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView CcodeEdit(HttpServletRequest req)
  {
    int value = Integer.parseInt(req.getParameter("CodeID"));
    
    ModelAndView model1 = new ModelAndView("ccode");
    model1.addObject("codeform", cService.getCodeByID(value));
    model1.addObject("codeList", cService.listCode());
    return model1;
  }
  @RequestMapping(value={"/CcodeDelete"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView CcodeDelete(HttpServletRequest req,RedirectAttributes redirectAttributes)
  {
      try{
          int value = Integer.parseInt(req.getParameter("CodeID"));
        cService.deleteCode(value);
          
      }catch(Exception e)
      {
          System.out.println("e:"+e);
       CcodeMaster code1 = new CcodeMaster();
         ModelAndView model = new ModelAndView("redirect:ccode.html");  
        redirectAttributes.addFlashAttribute("message", "HSNcode Already Use Somewhere,Cannot Delete"); 
        return model;
          
      }
    ModelAndView model1 = new ModelAndView("redirect:ccode.html");
    redirectAttributes.addFlashAttribute("message","Record Deleted Successfully");
    return model1;
  }
  
   @RequestMapping(value={"HSNCodeReport"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView HSNCodeReport()
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
       
    ModelAndView model = new ModelAndView("HSNCodeReport");
    model.addObject("codeList", this.cService.listCode());
    model.addObject("curDate", dt);
    model.addObject("finalDate", finalDate);
    return model;
  }
  
   @RequestMapping(value={"GetHSNCodeReports"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView GetHSNCodeReports(HttpServletRequest req,HttpServletResponse response) throws ParseException, JRException, IOException
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
    String hsnCode = req.getParameter("hsnCode");    
    String viewType=req.getParameter("viewType");
    String viewTypeList=req.getParameter("type");
    

    ModelAndView model = new ModelAndView("ItemProfitReports");
    model.addObject("codeList", this.cService.listCode());
  

    ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
    HttpServletRequest request = attr.getRequest();
    CompanyInformation u = companyservice.getCompanyById(1);
    String cname = u.getCompanyName();
    String address = u.getAddress();
    
    Map parametersMap = new HashMap();
    parametersMap.put("companyName", cname);
    parametersMap.put("Companyaddress", address);
    parametersMap.put("startDate", sdfIn.parse(gsFromDate));
    parametersMap.put("endDate", sdfInn.parse(gsToDate));
    parametersMap.put("hsnCode", hsnCode);
    
      String reportfile = "";
    
    if(viewTypeList.equalsIgnoreCase("0"))
    {
        System.out.print(" viewType 0");
     reportfile = req.getServletContext().getRealPath("//Reports//SalesHSN.jrxml");
 
    }
    if(viewTypeList.equalsIgnoreCase("1"))
    {
       
     reportfile = req.getServletContext().getRealPath("//Reports//sales_return_HSNCode.jrxml");
 
    }
      if(viewTypeList.equalsIgnoreCase("2"))
    {
       
     reportfile = req.getServletContext().getRealPath("//Reports//purchase_HSNCode.jrxml");
 
    }
      if(viewTypeList.equalsIgnoreCase("3"))
    {
     
     reportfile = req.getServletContext().getRealPath("//Reports//purchase_returnHSNCode.jrxml");
 
    }
      
//    String reportfile=req.getServletContext().getRealPath("//Reports//HSNCodeReports.jrxml");
    JasperReport jasperReport = JasperCompileManager.compileReport(reportfile);
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
        response.setHeader("Content-Disposition", "inline;filename=HSNReports.xlsx");
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
