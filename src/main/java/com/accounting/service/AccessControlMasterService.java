package com.accounting.service;

import com.accounting.bean.AccessControlMasterTable;
import java.util.List;

public abstract interface AccessControlMasterService
{
  public abstract String addAccessDetails(AccessControlMasterTable paramAccessControlMasterBean);
  
  public abstract List<AccessControlMasterTable> listAccess();
  
  public abstract List<AccessControlMasterTable> getAccessById(String paramString);
  
  public abstract void deleteAccess(String paramString);
  
  public abstract List<Object[]> join(String paramString);
}
