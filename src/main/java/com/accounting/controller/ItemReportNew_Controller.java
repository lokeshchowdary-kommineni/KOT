
package com.accounting.controller;

import com.accounting.bean.CompanyInformation;
import com.accounting.bean.ItemReport;
import com.accounting.service.CompanyService;
import com.accounting.service.ItemGroupZReportService;
import com.accounting.service.ItemReport_service;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author shinelogics
 */
@Controller
public class ItemReportNew_Controller {
    
     @Autowired
     private ItemReport_service reportService;
     
     @Autowired
     private ItemGroupZReportService igs;
      @Autowired
    private SessionFactory sessionFactory;
        @Autowired
     private CompanyService companyservice;
     
     
    ItemReport ir = new ItemReport();
    
    
    @RequestMapping(value={"Item_reports"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView Item_reports()
  {
       String fromDate = companyservice.getCompanyById(1).getFinancialYearFrom();
       System.out.println("fromDate " +fromDate);
    String financialdateMonth=fromDate.substring(0, 5);
       System.out.println("financialdateMonth " +financialdateMonth);
    String financialmonth=financialdateMonth.substring(3, 5);
    int intFinancialMonth=Integer.parseInt(financialmonth);
       System.out.println("financialmonth " +financialmonth);
       
       
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy ");
    Date currentdate = new Date();
    String dt=dateFormat.format(currentdate);
    System.out.println("current date ------------ "+ dt);
    String currentMonth=dt.substring(3,5);
    int intcurrentMonth=Integer.parseInt(currentMonth);
    System.out.println("currentMonth "+ currentMonth);
    String currentYear=dt.substring(6,10);
    int intCurrentYear=Integer.parseInt(currentYear);
    System.out.println("currentYear "+ currentYear);
      
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
       
       
    ModelAndView model = new ModelAndView("Item_reports");
   model.addObject("itemList",reportService.getItemList()); 
   model.addObject("itemGroupList",reportService.listItem()); 
   model.addObject("curDate", dt);
   model.addObject("finalDate", finalDate);
    return model;
  }
     @RequestMapping(value={"Stock_reports"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView StockSummary()
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
    ModelAndView model = new ModelAndView("Stock_reports"); 
     model.addObject("curDate", dt);
     model.addObject("finalDate", finalDate);
    return model;
  }
   @RequestMapping(value={"Item_group_reports"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView ItemGroupReport()
  {
      
   String fromDate = companyservice.getCompanyById(1).getFinancialYearFrom();
       System.out.println("fromDate " +fromDate);
    String financialdateMonth=fromDate.substring(0, 5);
       System.out.println("financialdateMonth " +financialdateMonth);
    String financialmonth=financialdateMonth.substring(3, 5);
    int intFinancialMonth=Integer.parseInt(financialmonth);
       System.out.println("financialmonth " +financialmonth);
       
       
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy ");
    Date currentdate = new Date();
    String dt=dateFormat.format(currentdate);
    System.out.println("current date ------------ "+ dt);
    String currentMonth=dt.substring(3,5);
    int intcurrentMonth=Integer.parseInt(currentMonth);
    System.out.println("currentMonth "+ currentMonth);
    String currentYear=dt.substring(6,10);
    int intCurrentYear=Integer.parseInt(currentYear);
    System.out.println("currentYear "+ currentYear);
      
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
      
   ModelAndView model = new ModelAndView("Item_group_reports");
   model.addObject("itemGroupList",reportService.listItem());
   model.addObject("curDate", dt);
   model.addObject("finalDate", finalDate);
    return model;
  }
  @RequestMapping(value={"ItemPreview"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView ItemPreview( HttpServletRequest req,HttpServletResponse response) throws ParseException, JRException, IOException
  {
    
  
    
   
    String item = req.getParameter("item");
    String gsFromDate = req.getParameter("fromdate");
    String gsToDate = req.getParameter("todate");
    String type = req.getParameter("type");
    String formate = req.getParameter("format");    
    
   
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date convertedFromDate = sdf.parse(gsFromDate);
    String fdate=dmyFormat.format(convertedFromDate );    
    Date convertedEndDate = sdf.parse(gsToDate);
    String edate=dmyFormat.format(convertedEndDate );
 
//    System.out.println(fdate);
    List<Object[]> opening=null;
     opening=igs.getItemOprningAndClosingBalance(item, fdate);
    if(  opening.size()<=0)
    {
           opening=igs.getSingleItemOpenigBalance(item);

    }
    String openingQty="0";
    String openingValue="0";
    
     if( opening!=null && opening.size()>0)
            {
                  for (Object[] column : opening) {
                             openingQty = String.valueOf(column[0]);
                             openingValue=String.valueOf(column[1]);
                     }

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
    parametersMap.put("itemid", item);
    parametersMap.put("openingValue", openingValue);
    parametersMap.put("openingQty", openingQty);
    
    String reportfile="";
    
    if(type.equalsIgnoreCase("0"))
    {
     reportfile = req.getServletContext().getRealPath("//Reports//ItemVoucher_Land.jrxml");
 
    }
    if(type.equalsIgnoreCase("1"))
    {
     reportfile = req.getServletContext().getRealPath("//Reports//Item_Report_date.jrxml");
 
    }
    if(type.equalsIgnoreCase("2"))
    {
     reportfile = req.getServletContext().getRealPath("//Reports//ItemReport_month.jrxml");
 
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
  @RequestMapping(value={"GroupPreview"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView GroupPreview( HttpServletRequest req,HttpServletResponse response) throws ParseException, JRException, IOException
  {
   
    String itemGroup = req.getParameter("itemgroup");
    String gsFromDate = req.getParameter("fromdate");
    String gsToDate = req.getParameter("todate");
    String type = req.getParameter("type");
    String formate = req.getParameter("format");    
    String GroupName = req.getParameter("groupName");   
    
     String childGroupsids=igs.returnChildItemids(itemGroup);
     String id="";
    if("PRIMARY".equals(itemGroup))
    {
          if("".equals(childGroupsids)) 
            {  
            id="''";
            }
            else
            {
             id=childGroupsids;   
            }
    }
    else
    {
     if("".equals(childGroupsids)) 
          {  
          id="'"+itemGroup+"'";
          }
          else
          {
           id="'"+itemGroup+"',"+childGroupsids;   
          }
        
    }   
    
          
    
   
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date convertedFromDate = sdf.parse(gsFromDate);
    String fdate=dmyFormat.format(convertedFromDate );    
    Date convertedEndDate = sdf.parse(gsToDate);
    String edate=dmyFormat.format(convertedEndDate );
  
//    System.out.println(fdate);
   
     List<Object[]> itemGroupOpeningFromEntries =igs.getGroupWiseOpeningBalance(id, fdate);
   
    Double openingQty=0.0;
    Double openingValue=0.0;
    
     if( itemGroupOpeningFromEntries!=null && itemGroupOpeningFromEntries.size()>0)
            {
                  for (Object[] column : itemGroupOpeningFromEntries) {
                             openingQty = openingQty+((BigDecimal)(column[0])).doubleValue();
                             openingValue=openingValue+((BigDecimal)(column[1])).doubleValue();
                     }

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
    parametersMap.put("itemgroupId", id);
    parametersMap.put("openingValue", String.valueOf(openingValue));
    parametersMap.put("openingQty", String.valueOf(openingQty));
    parametersMap.put("groupName", GroupName);
    
    System.out.println(" openingQty "+openingQty+"  openingValue "+openingValue );
    String reportfile="";
    
    if(type.equalsIgnoreCase("0"))
    {
     reportfile = req.getServletContext().getRealPath("//Reports//ItemGroupDate.jrxml");
 
    }
    if(type.equalsIgnoreCase("1"))
    {
     reportfile = req.getServletContext().getRealPath("//Reports//ItemGroupMonth.jrxml");
 
    }
    if(type.equalsIgnoreCase("2"))
    {
     reportfile = req.getServletContext().getRealPath("//Reports//ItemGroupwise.jrxml");
 
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
        response.setHeader("Content-Disposition", "inline;filename=ItemGroupReport.xlsx");
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
   @RequestMapping(value={"StockPreview"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView StockPreview( HttpServletRequest req,HttpServletResponse response) throws ParseException, JRException, IOException
  {    
    String gsFromDate = req.getParameter("fromdate");
    String gsToDate = req.getParameter("todate");
   // String type = req.getParameter("type");
    String formate = req.getParameter("format");    
    String groupids="";
         String childGroupsids=igs.returnChildItemids("PRIMARY");
            if("".equals(childGroupsids)) 
            {  
            groupids="''";
            }
            else
            {
             groupids=childGroupsids;   
            }
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
    parametersMap.put("fromdate", fdate);
    parametersMap.put("enddate", edate);
    parametersMap.put("itemgroupId", groupids);
  
    String reportfile=req.getServletContext().getRealPath("//Reports//Stock_ItemWise.jrxml");
    
//    if(type.equalsIgnoreCase("0"))
//    {
//     reportfile = ;
// 
//    }
//    if(type.equalsIgnoreCase("1"))
//    {
//     reportfile = req.getServletContext().getRealPath("//Reports//Item_Report_date.jrxml");
// 
//    }
//    if(type.equalsIgnoreCase("2"))
//    {
//     reportfile = req.getServletContext().getRealPath("//Reports//ItemReport_month.jrxml");
// 
//    }
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
        response.setHeader("Content-Disposition", "inline;filename=StockReport.xlsx");
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
//  @RequestMapping(value={"reportGroupPreview"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
//  public ModelAndView reportGroupPreview( HttpServletRequest req) throws ParseException
//  {
//    
//    ModelAndView model = new ModelAndView("ItemGroupReport");
//    String gsQuery = "";
//    String gsQuery1 = "";
//    String textValue = "";
//    
//    String[] l =req.getParameterValues("list");    
//    String s="";
//    
//     if(l!=null)
//     { 
//         for(int i=0; i<l.length;i++)
//         {
//        if(s==""){
//            s="'"+l[i]+"'";
//        }else{
//            s=s+",'"+l[i]+"'";
//        }
//         }
//     }
//    String itemGroup =req.getParameter("itemgroup");
//    String item = req.getParameter("item");
//    String gsFromDate = req.getParameter("fromdate");
//    String gsToDate = req.getParameter("todate");
//   String month = req.getParameter("month");
//   
//    model.addObject("item", item);
//     model.addObject("itemGroup", itemGroup);
//      model.addObject("gsFromDate", gsFromDate);
//       model.addObject("gsToDate", gsToDate);
//        model.addObject("month", month);
//   
//    String childGroupsids=igs.returnChildItemids(itemGroup);
//   
//      String id="";
//            if("".equals(childGroupsids)) 
//            {  
//            id="'"+itemGroup+"'";
//            }
//            else
//            {
//             id="'"+itemGroup+"',"+childGroupsids;   
//            }  
//  
//   
//    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//    SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
//    Date convertedCurrentDate = sdf.parse(gsFromDate);
//    String fdate=dmyFormat.format(convertedCurrentDate );
////    System.out.println(fdate);
//    
//    Date convertedEndDate = sdf.parse(gsToDate);
//    String Edate=dmyFormat.format(convertedEndDate );
//    
//    
//    
//    List<Object[]> itemGroupOpeningFromEntries =igs.getGroupOprningAndClosingBalance(id, fdate);
//    System.out.println(" size Openinf "+itemGroupOpeningFromEntries.size());
//    List<Object[]> itemOPeningfromItemmaster =igs.getAllItemOpeningBalanceFromItemMaster(fdate,id);
//   // List<Object[]> listallGroupClosing =igs.getGroupOprningAndClosingBalanceEnddate(id, Edate);
//   // System.out.println(" size listallGroupClosing "+listallGroupClosing.size());
//    
//  
//    
//    gsQuery = " SELECT SUM(ir.inQuantity) ,SUM(ir.inValue) ,SUM(ir.outQuantity),SUM(ir.outValue),igm.itemGroupName,im.itemName,\n" +
//            " ir.transactionDate,ir.itemId,ir.itemId,ir.itemId FROM ItemReport AS ir INNER JOIN ItemMaster AS im ON\n" +
//            " ir.itemId=im.id INNER JOIN ItemGroupMaster AS igm ON im.itemGroup=igm.idItem keyWhere";
//    gsQuery1 = "SELECT ir.closingQuantity,ir.closingValue FROM ItemReport AS ir where ir.id in  (SELECT max(ir.id) FROM ItemReport AS ir INNER JOIN ItemMaster AS im ON\n" +
//            " ir.itemId=im.id INNER JOIN ItemGroupMaster AS igm ON im.itemGroup=igm.idItem keyWhere)";
//    String gsWhere = "";
//    String Groupby=" Group by ";
//    Groupby=Groupby+"ir.itemGroupId,ir.itemId,ir.transactionDate";
//    if ( itemGroup != null && !itemGroup.isEmpty())  {
//        gsWhere = "WHERE   ir.itemGroupId in ("+id+")";
//        
//      } else {
//        gsWhere = "";
//      }
//    if (((item != null) && !item.isEmpty()) ) {
//        if (gsWhere != "") {
//          gsWhere = gsWhere + "and ir.itemId='" + item + "'";
//        } else {
//          gsWhere = "WHERE   ir.itemId='" + item + "'";
//        }
//      }
//     if (!gsFromDate.isEmpty())
//      {
//           
//        if (!gsToDate.isEmpty())
//        {
//          if (gsWhere != "") {
//            gsWhere = gsWhere + " and ir.transactionDate BETWEEN '" + fdate + "' AND '" + Edate + "'   ";
//          } else {
//            gsWhere = gsWhere + "WHERE   ir.transactionDate BETWEEN '" + fdate + "' AND '" + Edate + "'  ";
//          }
//        }
//        else if (gsWhere != "") {
//          gsWhere = gsWhere + " and ir.transactionDate>='" + fdate + "'";
//        } else {
//          gsWhere = gsWhere + "WHERE ir.transactionDate>='" + Edate + "'";
//        }
//      }
//     else if (!gsToDate.isEmpty()) {
//         
//        if (gsWhere != "") {
//          gsWhere = gsWhere + " and ir.transactionDate<= '" + fdate + "'";
//        } else {
//          gsWhere = gsWhere + "WHERE ir.transactionDate<= '" + Edate + "'";
//        }
//      }
//       if(l!=null )
//       {
//           Groupby=Groupby+",ir.transactionType";
//           
//           if(gsWhere != "")
//            {
//                 gsWhere=gsWhere+" AND ir.transactionType IN("+s+")  ";
//         //   System.out.println("Different:" + gsWhere);
//            }
//            else {
//              gsWhere=gsWhere+" WHERE ir.transactionType IN("+s+")";
//         //   System.out.println("Different:" + gsWhere);
//             }
//       }
//       else
//       {
//         gsWhere=gsWhere;  
//       } 
//       if(month!=null && month.equalsIgnoreCase("1"))
//       {
//       System.out.println("check if condition:");
//        if(gsWhere != "")
//       {
//           gsQuery=" SELECT SUM(ir.inQuantity) ,SUM(ir.inValue) ,SUM(ir.outQuantity),SUM(ir.outValue),igm.itemGroupName,im.itemName,\n" +
//                    " monthname(ir.transactionDate),ir.itemId,ir.itemId,ir.itemId FROM ItemReport AS ir INNER JOIN ItemMaster AS im ON\n" +
//                    " ir.itemId=im.id INNER JOIN ItemGroupMaster AS igm ON im.itemGroup=igm.idItem keyWhere ";
//           gsQuery1=" SELECT ir.closingQuantity,ir.closingValue FROM ItemReport AS ir where ir.id in (SELECT MAX(ir.id) FROM ItemReport AS ir INNER JOIN ItemMaster AS im ON\n" +
//                    " ir.itemId=im.id INNER JOIN ItemGroupMaster AS igm ON im.itemGroup=igm.idItem keyWhere ) ";
//          //System.out.println("check gsquery:"+gsQuery);
//            gsWhere=gsWhere+" GROUP BY ir.itemGroupId,ir.itemId,MONTH(ir.transactionDate)";
//      
//       }
//        else
//        {
//            gsQuery="SELECT SUM(ir.inQuantity) ,SUM(ir.inValue) ,SUM(ir.outQuantity),SUM(ir.outValue),igm.itemGroupName,im.itemName,\n" +
//                " monthname(ir.transactionDate),ir.itemId,ir.itemId,ir.itemId FROM ItemReport AS ir INNER JOIN ItemMaster AS im ON\n" +
//                " ir.itemId=im.id INNER JOIN ItemGroupMaster AS igm ON im.itemGroup=igm.idItem keyWhere";
//            gsQuery1="SELECT ir.closingQuantity,ir.closingValue FROM ItemReport AS ir where ir.id in  (SELECT MAX(ir.id) FROM ItemReport AS ir INNER JOIN ItemMaster AS im ON\n" +
//                " ir.itemId=im.id INNER JOIN ItemGroupMaster AS igm ON im.itemGroup=igm.idItem keyWhere)";
//          // System.out.println("check gsquery:"+gsQuery);
//            gsWhere=gsWhere+" GROUP BY ir.itemGroupId,ir.itemId,MONTH(ir.transactionDate)";
//        }
//        
//        if (gsWhere.isEmpty()) {
//        gsQuery = gsQuery.replace("keyWhere", textValue);
//        gsQuery1 = gsQuery1.replace("keyWhere", textValue);
//        } else {
//          gsQuery = gsQuery.replace("keyWhere", gsWhere);
//          gsQuery1 = gsQuery1.replace("keyWhere", gsWhere);
//        }
//        System.out.println("gsQuery1 gsQuery1gsQuery1 "+gsQuery1);
//         List<Object[]> ItemGroupbyPart1=reportService.filterReport(gsQuery);
//            List<Object[]> ItemGroupbyPart2=reportService.filterReport(gsQuery1);
//            System.out.println("Size 1 "+ItemGroupbyPart1.size());
//            System.out.println("Size 21 "+ItemGroupbyPart2.size());
//            
//             Object[] both={};
//             Iterator    it1 = ItemGroupbyPart1.iterator();
//             Iterator it2 = ItemGroupbyPart2.iterator();
//             List<Object[]> ItemGroupbothmonth = new ArrayList<Object[]>();
//           
//            while(it1.hasNext() && it2.hasNext()) 
//            {
//               Object[] v1= (Object[]) it1.next();
//               Object[] v2= (Object[]) it2.next();
//
//                both = ArrayUtils.addAll(v1, v2);
//                ItemGroupbothmonth.add(both);
//            }
//            model.addObject("reportList", ItemGroupbothmonth);
//            model.addObject("listallGroupOPening", itemGroupOpeningFromEntries);
//            model.addObject("itemOpening", itemOPeningfromItemmaster);
//       
//       }
//       else
//       {
//       //  System.out.println("check else condition:");
//         gsWhere=gsWhere+Groupby;
//         
//        
//        if (gsWhere.isEmpty()) {
//        gsQuery = gsQuery.replace("keyWhere", textValue);
//        gsQuery1 = gsQuery1.replace("keyWhere", textValue);
//        } else {
//        
//          gsQuery = gsQuery.replace("keyWhere", gsWhere);
//          gsQuery1 = gsQuery1.replace("keyWhere", gsWhere);
//        }
//        // System.out.println(" Query  "+gsQuery);
//         List<Object[]> ItemGroupbyPart1=reportService.filterReport(gsQuery);
//            List<Object[]> ItemGroupbyPart2=reportService.filterReport(gsQuery1);
//        //    System.out.println("Size 1 "+ItemGroupbyPart1.size());
//          //  System.out.println("Size 21 "+ItemGroupbyPart2.size());
//            
//             Object[] both={};
//             Iterator    it1 = ItemGroupbyPart1.iterator();
//             Iterator it2 = ItemGroupbyPart2.iterator();
//             List<Object[]> ItemGroupboth = new ArrayList<Object[]>();
//           
//            while(it1.hasNext() && it2.hasNext()) 
//            {
//               Object[] v1= (Object[]) it1.next();
//               Object[] v2= (Object[]) it2.next();
//
//                both = ArrayUtils.addAll(v1, v2);
//                ItemGroupboth.add(both);
//            }
//            
//            model.addObject("reportList", ItemGroupboth);
//            model.addObject("listallGroupOPening", itemGroupOpeningFromEntries);
//            model.addObject("itemOpening", itemOPeningfromItemmaster);
//        
//       }
//       
//       
//        
//        
//        
//      System.out.println("query:" + gsQuery);
//          
//    
//   
//    model.addObject("itemGroupList",reportService.listItem());   
//    return model;
//  }
//  
//  @RequestMapping(value={"Get_Stock_Summary"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
//  public ModelAndView Get_Stock_Summary( HttpServletRequest req) throws ParseException
//  {
//    
//    ModelAndView model = new ModelAndView("StockSummary");    
//   
//   
//    String gsFromDate = req.getParameter("startDate");
//    String gsToDate = req.getParameter("endDate");
//    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//    SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
//    Date convertedCurrentDate = sdf.parse(gsFromDate);
//    String fdate=dmyFormat.format(convertedCurrentDate );//    System.out.println(fdate);
//    
//    Date convertedEndDate = sdf.parse(gsToDate);
//    String Edate=dmyFormat.format(convertedEndDate ); 
//      model.addObject("gsFromDate", gsFromDate);
//       model.addObject("gsToDate", gsToDate);
//   
//     List<Object[]> stockSummary=igs.getAllStockSummartBasedOnPRIMARYGroup("PRIMARY",Edate);
//    model.addObject("stockSummary",stockSummary);   
//    return model;
//  }
}