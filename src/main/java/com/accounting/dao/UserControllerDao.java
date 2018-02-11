/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.accounting.dao;

import com.accounting.bean.GroupTable;
import com.accounting.bean.UserMaster;
import com.accounting.encrypt_decrypt.PasswordEncrypt_Decrypt;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import java.io.*;
import javax.validation.ConstraintViolationException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MR
 */
@Repository("userControllerDao")
public class UserControllerDao  {
  @Autowired
  private SessionFactory sessionFactory;
 
   public int addUserDerails(UserMaster user)
  {
    this.sessionFactory.getCurrentSession().saveOrUpdate(user);
    return user.getUserid();
  }
   
    
  public List<UserMaster> listGroup()
  {
    List<UserMaster> group = null;
    try
    {
      group = this.sessionFactory.getCurrentSession().createQuery("from UserMaster").list();
    }
    catch (HibernateException e)
    {
      e.printStackTrace();
    }
    return group;
  }
  
  public List<GroupTable> getGroupName()
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
    
  public void deleteUserDetail(int userid) 
  {
     
      sessionFactory.getCurrentSession().createQuery("DELETE FROM UserMaster WHERE User_id='" + userid + "'").executeUpdate(); 
 
     
    
  }
  public UserMaster getUserByid(int userid)
  {
    return (UserMaster)this.sessionFactory.getCurrentSession().get(UserMaster.class, userid);
  }
//  
      public String getPwd(int userid)
  {
       String decryptedPassword=null;
    List<UserMaster> tlist=(List<UserMaster>)  sessionFactory.getCurrentSession().createQuery("FROM UserMaster WHERE User_id='"+userid+"'").list();
                 System.out.print("tlist:"+tlist);
                 if ((tlist != null &&  tlist.size()>0 ) ) {
                                Iterator itr=tlist.iterator();
                                    UserMaster tr=(UserMaster)itr.next();
                                    String userPassword=tr.getPassword();
                                        PasswordEncrypt_Decrypt dec= new PasswordEncrypt_Decrypt();
                                         decryptedPassword=dec.decrypt(userPassword);
                                         System.out.print("decryptedPassword:"+decryptedPassword);
                                                }
          return decryptedPassword;
  }
  }
