package com.accounting.service;

import com.accounting.bean.GroupTable;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public abstract interface GroupMasterService
{
  public abstract int addGroupDetails(GroupTable paramGroupMasterBean);
  
  public abstract List<GroupTable> listGroup();
  
  public abstract GroupTable getGroupById(int paramString);
  
  public abstract void deleteGroup(String paramString);
  
  public abstract List<GroupTable> listActiveGroup();

}
