package com.accounting.dao;

import com.accounting.bean.AccessControl;
import com.accounting.bean.AccessControlMasterTable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("AccesscontrolDao")
public class AccessControlMasterDaoImp
  implements AccessControlMasterDao
{
  @Autowired
  private SessionFactory sessionFactory;
  
  public String addAccess(AccessControlMasterTable access)
  {
    this.sessionFactory.getCurrentSession().saveOrUpdate(access);
    return String.valueOf(access.getAccessid());
  }
  
  public List<AccessControlMasterTable> listAccess()
  {
    List<AccessControlMasterTable> access = null;
    try
    {
      access = this.sessionFactory.getCurrentSession().createQuery("from AccessControlMasterTable").list();
    }
    catch (HibernateException e)
    {
      e.printStackTrace();
    }
    return access;
  }
  
  public List<AccessControlMasterTable> getAccessById(String accessId)
  {
    List<AccessControlMasterTable> access = null;
    access = this.sessionFactory.getCurrentSession().createQuery("from AccessControlMasterTable WHERE Group_id='" + accessId + "'").list();
    return access;
  }
  
  public List<Object[]> join(String accessid)
  {
        System.out.print(" access id b4 :"+accessid);
//    List list = this.sessionFactory.getCurrentSession().createQuery("SELECT a.accessid,a.createdon,a.moduleName FROM AccessControl AS a  LEFT OUTER JOIN AccessControlMasterTable AS ac ON a.accessid=ac.accessid AND ac.groupid= "+ accessid +" ").list();
   

    List list = this.sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM access_control AS a  LEFT OUTER JOIN access_control_master_table AS ac ON a.Access_id=ac.Access_id AND ac.Group_id=" + accessid + " ORDER BY a.Access_id").addEntity("access_control", AccessControl.class).addEntity("access_control_master_table", AccessControlMasterTable.class).list();
    
   System.out.print(" access id  :"+accessid);
    return list;
  }
  
  public void deleteAccess(String accessId)
  {
    this.sessionFactory.getCurrentSession().createQuery("DELETE FROM AccessControlMasterTable WHERE Group_id='" + accessId + "'").executeUpdate();
  }
}
