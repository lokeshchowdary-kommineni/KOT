/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.util;

import com.accounting.bean.SalesBill;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import org.hibernate.SessionFactory;
/**
 *
 * @author MR
 */
public class UtilHelper {
    public static double getTotalCGSTAmt(List<SalesBill> list){
        Double tCGst=0.0d;
        
        for(SalesBill sb:list){
            tCGst+=sb.getTaxCgst().doubleValue();
        }
        return tCGst;
    } 
     public static double getTotalSGSTAmt(List<SalesBill> list){
        Double tSGst=0.0d;
        
        for(SalesBill sb:list){
            tSGst+=sb.getTaxSgst().doubleValue();
        }
        return tSGst;
    } 
     public static String convertArray(String[] kot){
       String kotStr="";
       for(String ko:kot){
       kotStr=kotStr+"'"+ko+"',";
       }
      int index= kotStr.lastIndexOf(",");
      kotStr= kotStr.substring(0, index);
       return kotStr;
}
     
      public static void jasperTestPrint(String reportfile,BillVO vo,Connection conn){
     
//       try{
//       Class.forName("com.mysql.jdbc.Driver");
//        con= (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/kot_db","root","");  
//        }catch(Exception e){
//        }
        
          try {
         JasperReport jasperReport = JasperCompileManager.compileReport(reportfile);
         JRDataSource jRDataSoruce = new JRBeanCollectionDataSource(vo.getDataSource());
              System.out.println("lokeshi"+vo.getDataSource().size());
     JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,vo.getParametersMap(), conn);
         // JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametersMap);
          
          JasperPrintManager.printPage(jasperPrint,0,false);
          /*
    	   printFileName = JasperFillManager.fillReportToFile( 
            sourceFileName, params);
           
        if(printFileName != null){
            JasperPrintManager.printReport( printFileName, true);
         }*/
      } catch (JRException e) {
         e.printStackTrace();
      }
     }
}
