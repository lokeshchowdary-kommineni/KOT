package com.accounting.dao;

import com.accounting.bean.GroupTable;
import java.util.List;

public abstract interface GroupMasterDao
{
  public abstract int addGroup(GroupTable paramGroupMasterBean);
  
  public abstract List<GroupTable> listGroup();
  
  public abstract GroupTable getGroupById(int paramString);
  
  public abstract void deleteGroup(String paramString);
  
  public abstract List<GroupTable> listActiveGroup();
  

}
