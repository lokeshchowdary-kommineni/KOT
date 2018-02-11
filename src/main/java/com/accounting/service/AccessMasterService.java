package com.accounting.service;

import com.accounting.bean.AccessControl;
import java.util.List;

public abstract interface AccessMasterService
{
  public abstract List<AccessControl> listAccess();
}
