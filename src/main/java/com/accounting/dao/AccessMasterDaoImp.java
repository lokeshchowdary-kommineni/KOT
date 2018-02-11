package com.accounting.dao;

import com.accounting.bean.AccessControl;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("accessDao")
public class AccessMasterDaoImp
  implements AccessMasterDao
{
  @Autowired
  private SessionFactory sessionFactory;
  
  public List<AccessControl> listAccess()
  {
    List<AccessControl> accesss = null;
    try
    {
      accesss = this.sessionFactory.getCurrentSession().createQuery("from AccessControl").list();
    }
    catch (HibernateException e)
    {
      e.printStackTrace();
    }
    return accesss;
  }
}
