/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.controller;

import com.accounting.bean.AccountGroupMaster;
import com.accounting.bean.CompanyInformation;
import com.accounting.bean.LedgerAccountMaster;
import com.accounting.service.AccountReportsService;
import com.accounting.service.CompanyService;
import com.accounting.service.ItemGroupZReportService;
import com.accounting.service.ItemMasterService;
import com.accounting.service.LedgerAccount_Service;
import com.accounting.service.LedgerBalanceService;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.apache.commons.lang.ArrayUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author MR
 */
@Controller
public class AccountReportNew_controller {
    
     @Autowired
     private LedgerAccount_Service lgs;
     @Autowired
     private ItemMasterService ims;
     
     @Autowired 
     private AccountReportsService accountReportsService;
     
     @Autowired 
     private ItemGroupZReportService itemGroupZReportService;
     @Autowired
     private LedgerBalanceService lbs;
     @Autowired
     private SessionFactory sessionFactory;
      @Autowired
     private CompanyService companyservice;
    
     
    @RequestMapping(value={"Accounting_ledger_report"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView accountReports()
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
        ModelAndView model = new ModelAndView("Accounting_ledger_report");
        model.addObject("accountGroupList",lgs.listAccountGroups());
        model.addObject("listAllLedger",lgs.listAllLedger());
        model.addObject("curDate", dt);
        model.addObject("finalDate", finalDate);
        return model;
    }
    @RequestMapping(value={"AccountGroup_report"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView AccountGroup_report()
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
       
        ModelAndView model = new ModelAndView("AccountGroup_report");
        model.addObject("accountGroupList",lgs.listAccountGroups());
        model.addObject("listAllLedger",lgs.listAllLedger());
        model.addObject("curDate", dt);
        model.addObject("finalDate", finalDate);
        return model;
    }
      @RequestMapping(value={"LedgerPreview"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView LedgerPreview( HttpServletRequest req,HttpServletResponse response) throws ParseException, JRException, IOException
  {
    String ledgerId = req.getParameter("byLedger");
    String gsFromDate = req.getParameter("startDate");
    String gsToDate = req.getParameter("endDate");
    String type = req.getParameter("type");
    String formate = req.getParameter("format");    
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date convertedFromDate = sdf.parse(gsFromDate);
    String fdate=dmyFormat.format(convertedFromDate );    
    Date convertedEndDate = sdf.parse(gsToDate);
    String edate=dmyFormat.format(convertedEndDate );
 
    List<Object[]> currentBalance=lbs.SingleLedgerCurrentOpeningBalanceFromEntriesTable(String.valueOf(ledgerId), fdate);
            String Opening_Balance="";
            double openingBalanceOfLedger=0;
            if( currentBalance!=null && currentBalance.size()>0)
            {
                  for (Object[] column : currentBalance) {
                             openingBalanceOfLedger = (Double)column[0]- (Double)column[1];
                     }

            }
            else
            {
                List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(ledgerId));
                for (Object[] column : OpeningBalance) {
                             openingBalanceOfLedger = (Double)column[0]- (Double)column[1];
                     }
            }
            if(openingBalanceOfLedger>=0)
            {
                Opening_Balance=openingBalanceOfLedger+" DR";                
            }
            else
            {
                Opening_Balance=Math.abs(openingBalanceOfLedger)+" CR";   
            }    
          
    Session session1 = this.sessionFactory.openSession();
    SessionImpl sessionImpl = (SessionImpl)session1;
    Connection conn = sessionImpl.connection();
    CompanyInformation u = companyservice.getCompanyById(1);
    String cname = u.getCompanyName();
    String address = u.getAddress();
    
    Map parametersMap = new HashMap();
    parametersMap.put("companyName", cname);
    parametersMap.put("companyAddress", address);    
    parametersMap.put("fromdate", fdate);
    parametersMap.put("enddate", edate);
    parametersMap.put("ledgerId", ledgerId);
    parametersMap.put("openingBalance", Opening_Balance);
 
    
    String reportfile="";
    
    if(type.equalsIgnoreCase("0"))
    {
     reportfile = req.getServletContext().getRealPath("//Reports//LedgerVoucherWise.jrxml");
 
    }
    if(type.equalsIgnoreCase("1"))
    {
     reportfile = req.getServletContext().getRealPath("//Reports//LedgerDateWise.jrxml");
 
    }
    if(type.equalsIgnoreCase("2"))
    {
     reportfile = req.getServletContext().getRealPath("//Reports//LedgerMonthWise.jrxml");
 
    }
    JasperReport jasperReport = JasperCompileManager.compileReport(reportfile);
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametersMap, conn);    
  
    if(formate.equalsIgnoreCase("PDF"))   
    {
                JRPdfExporter pdfExp = new JRPdfExporter();
		pdfExp.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		pdfExp.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
		pdfExp.exportReport();
    }
    if(formate.equalsIgnoreCase("HTML"))   
    {   
                JRHtmlExporter htmlExp = new JRHtmlExporter();
		htmlExp.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
		htmlExp.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		htmlExp.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
		htmlExp.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,	Boolean.FALSE);
		htmlExp.exportReport();
        
    }
    if(formate.equalsIgnoreCase("EXCEL"))   
    {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "inline;filename=LedgerReport.xlsx");
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
      @RequestMapping(value={"AccountGroupSummary"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView AccountGroupSummary( HttpServletRequest req,HttpServletResponse response) throws ParseException, JRException, IOException
  {
    String groupid = req.getParameter("byAccountGroups");
    String gsFromDate = req.getParameter("startDate");
    String gsToDate = req.getParameter("endDate");
    String type = req.getParameter("type");
    String formate = req.getParameter("format");    
    String groupName = req.getParameter("groupName");  
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date convertedFromDate = sdf.parse(gsFromDate);
    String fdate=dmyFormat.format(convertedFromDate );    
    Date convertedEndDate = sdf.parse(gsToDate);
    String edate=dmyFormat.format(convertedEndDate );
    
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
 
            List<Object[]> currentBalance=accountReportsService.AccountGroupOpeningBalance(id, fdate);
            String Opening_Balance="";
            double openingBalanceOfLedger=0;
            double cr=0;
            double dr=0;
            
            if( currentBalance!=null && currentBalance.size()>0)
            {
                  for (Object[] column : currentBalance) {
                      System.out.println("sd "+((BigDecimal)column[0]));
                             dr = dr+((BigDecimal)column[0] ).doubleValue();
                             cr=cr+((BigDecimal)column[1]).doubleValue();
                     }

            }
            openingBalanceOfLedger=dr-cr;
            if(openingBalanceOfLedger>=0)
            {
                Opening_Balance=openingBalanceOfLedger+" DR";                
            }
            else
            {
                Opening_Balance=Math.abs(openingBalanceOfLedger)+" CR";   
            }    
          
    Session session1 = this.sessionFactory.openSession();
    SessionImpl sessionImpl = (SessionImpl)session1;
    Connection conn = sessionImpl.connection();
    CompanyInformation u = companyservice.getCompanyById(1);
    String cname = u.getCompanyName();
    String address = u.getAddress();
    
    Map parametersMap = new HashMap();
    parametersMap.put("companyName", cname);
    parametersMap.put("companyAddress", address);    
    parametersMap.put("fromdate", fdate);
    parametersMap.put("enddate", edate);
    parametersMap.put("groupId", id);
    parametersMap.put("groupName", groupName);
    parametersMap.put("openingBalance", Opening_Balance);
 
    
    String reportfile="";
    
    if(type.equalsIgnoreCase("0"))
    {
     reportfile = req.getServletContext().getRealPath("//Reports//AccountGroupDate.jrxml");
 
    }
    if(type.equalsIgnoreCase("1"))
    {
     reportfile = req.getServletContext().getRealPath("//Reports//AccountGroupMonth.jrxml");
 
    }
    if(type.equalsIgnoreCase("2"))
    {
     reportfile = req.getServletContext().getRealPath("//Reports//AccountGroupWise.jrxml");
 
    }
    JasperReport jasperReport = JasperCompileManager.compileReport(reportfile);
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametersMap, conn);    
  
    if(formate.equalsIgnoreCase("PDF"))   
    {
                JRPdfExporter pdfExp = new JRPdfExporter();
		pdfExp.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		pdfExp.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
		pdfExp.exportReport();
    }
    if(formate.equalsIgnoreCase("HTML"))   
    {   
                JRHtmlExporter htmlExp = new JRHtmlExporter();
		htmlExp.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
		htmlExp.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		htmlExp.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
		htmlExp.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,	Boolean.FALSE);
		htmlExp.exportReport();
        
    }
    if(formate.equalsIgnoreCase("EXCEL"))   
    {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "inline;filename=ItemReport.xlsx");
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
    
     @RequestMapping(value={"PreviewTrialBalance"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView PreviewTrialBalance( HttpServletRequest req,HttpServletResponse response) throws ParseException, JRException, IOException
  {
    String groupid = "0";
    String gsFromDate = req.getParameter("startDate");
    String gsToDate = req.getParameter("endDate");
    String type = req.getParameter("type");
    String formate = req.getParameter("format");    
    String groupName = req.getParameter("groupName");  
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date convertedFromDate = sdf.parse(gsFromDate);
    String fdate=dmyFormat.format(convertedFromDate );    
    Date convertedEndDate = sdf.parse(gsToDate);
    String edate=dmyFormat.format(convertedEndDate );
    
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
 
       
          
    Session session1 = this.sessionFactory.openSession();
    SessionImpl sessionImpl = (SessionImpl)session1;
    Connection conn = sessionImpl.connection();
    CompanyInformation u = companyservice.getCompanyById(1);
    String cname = u.getCompanyName();
    String address = u.getAddress();
    
    Map parametersMap = new HashMap();
    parametersMap.put("companyName", cname);
    parametersMap.put("companyAddress", address);    
    parametersMap.put("fromdate", fdate);
    parametersMap.put("enddate", edate);
    parametersMap.put("groupId", id);
    
    String reportfile = req.getServletContext().getRealPath("//Reports//TrialBalance.jrxml");
    
  
    JasperReport jasperReport = JasperCompileManager.compileReport(reportfile);
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametersMap, conn);    
  
    if(formate.equalsIgnoreCase("PDF"))   
    {
                JRPdfExporter pdfExp = new JRPdfExporter();
		pdfExp.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		pdfExp.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
		pdfExp.exportReport();
    }
    if(formate.equalsIgnoreCase("HTML"))   
    {   
                JRHtmlExporter htmlExp = new JRHtmlExporter();
		htmlExp.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
		htmlExp.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		htmlExp.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
		htmlExp.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,	Boolean.FALSE);
		htmlExp.exportReport();
        
    }
    if(formate.equalsIgnoreCase("EXCEL"))   
    {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "inline;filename=TrialBalance.xlsx");
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
