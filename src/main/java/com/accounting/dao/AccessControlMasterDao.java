package com.accounting.dao;

import com.accounting.bean.AccessControlMasterTable;
import java.util.List;

public abstract interface AccessControlMasterDao
{
  public abstract String addAccess(AccessControlMasterTable paramAccessControlMasterBean);
  
  public abstract List<AccessControlMasterTable> listAccess();
  
  public abstract List<AccessControlMasterTable> getAccessById(String paramString);
  
  public abstract void deleteAccess(String paramString);
  
  public abstract List<Object[]> join(String paramString);
}
