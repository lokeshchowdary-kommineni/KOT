package com.accounting.dao;

import com.accounting.bean.AccessControl;
import java.util.List;

public abstract interface AccessMasterDao
{
  public abstract List<AccessControl> listAccess();
}
