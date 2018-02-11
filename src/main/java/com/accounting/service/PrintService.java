/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.service;


import com.accounting.bean.PrintSetting;
import com.accounting.dao.PrintSettingDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class PrintService  {

    
    @Autowired
    private PrintSettingDAO pado;
    

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Integer addprintSetting (PrintSetting ps) {
        return pado.addPrintSetting(ps);
            
    }
 public List<PrintSetting> listprintService() {
        return pado.getPrintlist();
 }

    public PrintSetting getPrintById(Integer pid) {
        return pado.getprintById(pid);
                        
    }
    public void deletePrint(Integer pid) {
        pado.deleteprint(pid);
      

    }
    public PrintSetting getPrintType(String type) {
        return pado.getprintType(type);
                        
    }
}

