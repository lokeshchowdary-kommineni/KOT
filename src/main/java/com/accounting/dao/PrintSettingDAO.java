
package com.accounting.dao;


import com.accounting.bean.PrintSetting;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository("PrintSettingDAO")
public class PrintSettingDAO
{

    @Autowired
	private SessionFactory sessionFactory;
      
    public Integer addPrintSetting(PrintSetting ps) {        
           sessionFactory.getCurrentSession().saveOrUpdate(ps);
           return ps.getPrintId();
    }

     public List<PrintSetting> getPrintlist() {
        List<PrintSetting> pl = null;
        try {

            pl = (List<PrintSetting>)sessionFactory.getCurrentSession().createQuery("from PrintSetting").list();
           
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return pl;
    }
          
    public void deleteprint(Integer pid) {
    sessionFactory.getCurrentSession().createQuery("DELETE FROM PrintSetting WHERE printId='"+pid+"'").executeUpdate();    
    }
    
      
    public PrintSetting getprintById(Integer pid) {
    return (PrintSetting) sessionFactory.getCurrentSession().get(PrintSetting.class, pid);
    
    }
    public PrintSetting getprintType(String  type) {
    return (PrintSetting) sessionFactory.getCurrentSession().createQuery("FROM PrintSetting WHERE printSettingName='"+type+"'").uniqueResult();
    
    }
      
   
    
}

