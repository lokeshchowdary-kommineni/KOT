package com.accounting.service;

import com.accounting.bean.AccessControl;
import com.accounting.dao.AccessMasterDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("accessservice")
@Transactional
public class AccessMasterServiceImp
  implements AccessMasterService
{
  @Autowired
  private AccessMasterDao accessDao;
  
  public List<AccessControl> listAccess()
  {
    return this.accessDao.listAccess();
  }
}
