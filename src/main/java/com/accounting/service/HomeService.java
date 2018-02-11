/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.accounting.service;

import com.accounting.bean.AccessControlMasterTable;
import com.accounting.bean.UserMaster;
import com.accounting.dao.HomeDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MR
 */
@Service("homeService")
@Transactional
public class HomeService {
    @Autowired
  private HomeDao homeDao;
  
//  public Boolean CheckDatabaseService(String email,String password)
//  {
//    return this.homeDao.CheckDatabase(email, password);
//}
  public Boolean ChkRole(String role)
  {
    return this.homeDao.ChkRole(role);
}
  
//  public Boolean ChkStatus(int status)
//  {
//    return this.homeDao.ChkStatus(status);
//}
  
   public List<AccessControlMasterTable> chkGroupId(String groupid ,String role) {
            return homeDao.chkGroupId(groupid, role);
        }
    public List<UserMaster> getDbEmail(String email) {
            return homeDao.getDbEmail(email);
        }
  
}
