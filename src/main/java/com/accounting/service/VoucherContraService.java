/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.service;

import com.accounting.bean.VoucherContra;
import com.accounting.dao.VoucherContraDao;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MR
 */
@Service
@Transactional
public class VoucherContraService {
    
    @Autowired
    private VoucherContraDao voucherContraDao;
    
    public int saveVoucherContra(VoucherContra vContra){
        return voucherContraDao.saveVoucherContra(vContra);
    }
    
    public List<VoucherContra> listAllVoucherContra(){
        return voucherContraDao.listAllVoucherContra();        
    }
    
    public VoucherContra getVoucherContraByID(int pId) {
        return voucherContraDao.getVoucherContraByID(pId);
    }
    
    public void deleteVoucherContraById(int contraId){
        voucherContraDao.deleteVoucherContraById(contraId);
    }
    public List<Object[]> GetDatatableContraObject(String query){
    return voucherContraDao.GetDatatableContraObject(query);
}
public List<Object[]> GetDatatableContraCount(String query){
    return voucherContraDao.GetDatatableContraCount(query);
}
    
}
