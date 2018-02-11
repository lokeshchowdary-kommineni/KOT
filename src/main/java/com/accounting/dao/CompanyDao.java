
package com.accounting.dao;

import com.accounting.bean.CompanyInformation;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("companyDao")
public class CompanyDao{

    @Autowired
	private SessionFactory sessionFactory;
      
    public Integer addCompany(CompanyInformation company) {        
           sessionFactory.getCurrentSession().saveOrUpdate(company);
           return company.getIdCompany();
    }

     public List<CompanyInformation> listCompany() {
        List<CompanyInformation> company = null;
        try {

            company = (List<CompanyInformation>)sessionFactory.getCurrentSession().createQuery("from CompanyInformation").list();
            if(!company.isEmpty()){

            }
            else{
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return company;
    }
          
    public void deletecompany(Integer companyId) {
    sessionFactory.getCurrentSession().createQuery("DELETE FROM CompanyInformation WHERE id_company='"+companyId+"'").executeUpdate();    
    }
      
    public CompanyInformation getCompanyById(Integer companyId) {
    return (CompanyInformation) sessionFactory.getCurrentSession().get(CompanyInformation.class, companyId);
    
    }
      
   
    
}

