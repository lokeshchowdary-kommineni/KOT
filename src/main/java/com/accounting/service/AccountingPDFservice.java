/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.service;

import com.accounting.dao.AccountingPDFdao;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author shine
 */
@Service
@Transactional
public class AccountingPDFservice {
    
    @Autowired
  private AccountingPDFdao accountingPDFdao;
  
  public void GeneratePdfReport(String id, String ReportFile, String filename, String outDir, String print)
    throws JRException
  {
    this.accountingPDFdao.GeneratePdfReports(id, filename, ReportFile);
  }
    
}
