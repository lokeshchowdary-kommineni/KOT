/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.controller;

import com.accounting.bean.CompanyInformation;
import com.accounting.service.AccountReportsService;
import com.accounting.service.CompanyService;
import com.accounting.service.ItemGroupZReportService;
import com.accounting.service.ItemMasterService;
import com.accounting.service.LedgerAccount_Service;
import com.accounting.service.LedgerBalanceService;
import java.io.IOException;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author shine
 */
@Controller
public class DayBookController {
    
     
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
    
    @RequestMapping(value={"DayBookReports"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
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
        ModelAndView model = new ModelAndView("DayBookReports");
        model.addObject("accountGroupList",lgs.listAccountGroups());
        model.addObject("listAllLedger",lgs.listAllLedger());
        model.addObject("curDate", dt);
        model.addObject("finalDate", finalDate);
        return model;
    }
    
     @RequestMapping(value={"DayBookPreview"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView LedgerPreview( HttpServletRequest req,HttpServletResponse response) throws ParseException, JRException, IOException
  {
    //String ledgerId = req.getParameter("byLedger");
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

    Session session1 = this.sessionFactory.openSession();
    SessionImpl sessionImpl = (SessionImpl)session1;
    Connection conn = sessionImpl.connection();
    CompanyInformation u = companyservice.getCompanyById(1);
    String cname = u.getCompanyName();
    String address = u.getAddress();
    
    Map parametersMap = new HashMap();
    parametersMap.put("companyName", cname);
    parametersMap.put("companyAddress", address);    
    parametersMap.put("fromdate", sdf.parse(gsFromDate));
    parametersMap.put("enddate", sdf.parse(gsToDate));
//    parametersMap.put("ledgerId", ledgerId);
//    parametersMap.put("openingBalance", Opening_Balance);
     
    String reportfile = req.getServletContext().getRealPath("//Reports//DayBookReports.jrxml");

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
    
}
