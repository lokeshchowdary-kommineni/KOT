package com.accounting.dao;

import com.accounting.bean.GroupTable;
//import com.accounting.beans.ProductTable;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("groupDao")
public class GroupMasterDaoImp
  implements GroupMasterDao
{
  @Autowired
  private SessionFactory sessionFactory;
  
  public int addGroup(GroupTable group)
  {
    this.sessionFactory.getCurrentSession().saveOrUpdate(group);
    return group.getGroupid();
  }
  
  public List<GroupTable> listGroup()
  {
    List<GroupTable> group = null;
    try
    {
      group = this.sessionFactory.getCurrentSession().createQuery("from GroupTable").list();
    }
    catch (HibernateException e)
    {
      e.printStackTrace();
    }
    return group;
  }
  
  public GroupTable getGroupById(int groupId)
  {
    return (GroupTable)this.sessionFactory.getCurrentSession().get(GroupTable.class, groupId);
  }
  
  public void deleteGroup(String groupId)
  {
    this.sessionFactory.getCurrentSession().createQuery("DELETE FROM GroupTable WHERE groupid='" + groupId + "'").executeUpdate();
  }
  
  public List<GroupTable> listActiveGroup()
  {
    List<GroupTable> group = null;
    try
    {
      group = this.sessionFactory.getCurrentSession().createQuery("from GroupTable WHERE groupstatus='Active'").list();
    }
    catch (HibernateException e)
    {
      e.printStackTrace();
    }
    return group;
  }


}
