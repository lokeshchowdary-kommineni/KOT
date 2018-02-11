/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.dao;

import com.accounting.bean.VoucherContra;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MR
 */
@Repository("VoucherContraDao")
public class VoucherContraDao {
    
    @Autowired SessionFactory sessionFactory;
    
    public int saveVoucherContra(VoucherContra vContra){
        sessionFactory.getCurrentSession().saveOrUpdate(vContra);
        return vContra.getVouchId();
    }
    
    public List<VoucherContra> listAllVoucherContra() {
        List<VoucherContra> list=sessionFactory.openSession().createQuery("FROM VoucherContra").list();
        return list;
    }
    
    public VoucherContra getVoucherContraByID(int pId) {
        
        return (VoucherContra) sessionFactory.getCurrentSession().get(VoucherContra.class, pId);
    }
    
    public void deleteVoucherContraById(int contraId){
        sessionFactory.openSession().createQuery("DELETE FROM VoucherContra WHERE contraId='"+contraId+"'").executeUpdate();
       
    }
     public List<Object[]> GetDatatableContraObject(String sql) {
        return (List<Object[]>)this.sessionFactory.getCurrentSession().createNativeQuery(sql).list();
    }
    public List<Object[]> GetDatatableContraCount(String sql2) {
        return (List<Object[]>)this.sessionFactory.getCurrentSession().createNativeQuery(sql2).list();
    }
    
}
