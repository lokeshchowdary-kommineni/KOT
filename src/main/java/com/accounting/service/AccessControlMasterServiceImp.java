package com.accounting.service;

import com.accounting.bean.AccessControlMasterTable;
import com.accounting.dao.AccessControlMasterDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("accesscontrolService")
@Transactional
public class AccessControlMasterServiceImp
  implements AccessControlMasterService
{
  @Autowired
  private AccessControlMasterDao accesscontrolDao;
  
  @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
  public String addAccessDetails(AccessControlMasterTable access)
  {
    return this.accesscontrolDao.addAccess(access);
  }
  
  public List<AccessControlMasterTable> listAccess()
  {
    return this.accesscontrolDao.listAccess();
  }
  
  public List<AccessControlMasterTable> getAccessById(String accessId)
  {
    return this.accesscontrolDao.getAccessById(accessId);
  }
  
  public void deleteAccess(String accessId)
  {
    this.accesscontrolDao.deleteAccess(accessId);
  }
  
  public List<Object[]> join(String accessid)
  {
    return this.accesscontrolDao.join(accessid);
  }
}
