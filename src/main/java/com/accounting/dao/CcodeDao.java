package com.accounting.dao;

import com.accounting.bean.CcodeMaster;
import java.util.List;
import javax.validation.ConstraintViolationException;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("codeDao")
public class CcodeDao

{
  @Autowired
  private SessionFactory sessionFactory;
  
  public int saveCode(CcodeMaster code)
  {
    this.sessionFactory.getCurrentSession().saveOrUpdate(code);
    return code.getCcodeId();
  }
  
  public List<CcodeMaster> listCcode()
  {
    List<CcodeMaster> code = null;
    try
    {
      code = this.sessionFactory.getCurrentSession().createQuery("from CcodeMaster").list();
    }
    catch (HibernateException e)
    {
      e.printStackTrace();
    }
    return code;
  }
  
  public void deleteCcode(int ccode_id)throws ConstraintViolationException
  {
    this.sessionFactory.getCurrentSession().createQuery("DELETE FROM CcodeMaster WHERE ccode_id=" + ccode_id + "").executeUpdate();
  }
  
  public CcodeMaster getCcodeByid(int codeId)
  {
    return (CcodeMaster)this.sessionFactory.getCurrentSession().get(CcodeMaster.class, codeId);
  }
  public boolean checkCcode(String us) {
            
             List<CcodeMaster> CcodeInfo = null;
            
		CcodeInfo=(List<CcodeMaster>) sessionFactory.getCurrentSession().createQuery("  FROM CcodeMaster where ccode='"+us+"' ").list();
	if(CcodeInfo.size()>0)
        {
        return true;
        }else
        {
          return false;
        }
}

}