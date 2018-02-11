/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.dao;

import com.accounting.bean.CompanyInformation;
import com.accounting.service.CompanyService;
import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author shine
 */
@Repository("accountingPDFdao")
public class AccountingPDFdao {
    
    @Autowired
  private SessionFactory sessionFactory;
@Autowired
    private CompanyService companyservice;
    
    public void GeneratePdfReports(String id, String filename, String reportfile)
    throws JRException
  {
    HttpSession session = null;
    Session session1 = this.sessionFactory.openSession();
    SessionImpl sessionImpl = (SessionImpl)session1;
    Connection conn = sessionImpl.connection();
    ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
    HttpServletRequest request = attr.getRequest();
    session = request.getSession(false);
    CompanyInformation u = this.companyservice.getCompanyById(1);
    String cname = u.getCompanyName();
    String address = u.getAddress();

    JasperReport jasperReport = JasperCompileManager.compileReport(reportfile);
    Map parametersMap = new HashMap();
    parametersMap.put("cname", cname);
    parametersMap.put("address", address);
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametersMap, conn);
    JasperExportManager.exportReportToPdfFile(jasperPrint,"");

  }
    
}
