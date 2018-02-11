/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.accounting.service;

import com.accounting.bean.GroupTable;
import com.accounting.bean.UserMaster;
import com.accounting.dao.UserControllerDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MR
 */

@Service("userControllerService")
@Transactional
public class UserControllerService {
    
    @Autowired
  private UserControllerDao userControllerDao;
    
     
  @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
  public int addUserDerails(UserMaster user)
  {
    return userControllerDao.addUserDerails(user);
  }
    
     
  public List<UserMaster> listGroup()
  {
    return this.userControllerDao.listGroup();
  }
  
   public List<GroupTable> getGroupName()
  {
      return userControllerDao.getGroupName();
  }
  public void deleteUserDetail(int userid)
  {
    this.userControllerDao.deleteUserDetail(userid);
  }
  public UserMaster getUserByid(int id)
  {
    return this.userControllerDao.getUserByid(id);
  }
  public String getPwd(int userid)
  {
  return this.userControllerDao.getPwd(userid);
  }
}
