package com.accounting.service;

import com.accounting.bean.GroupTable;
import com.accounting.dao.GroupMasterDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("groupService")
@Transactional
public class GroupMasterServiceImp implements GroupMasterService
{
  @Autowired
  private GroupMasterDao groupDao;
  
  @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
  public int addGroupDetails(GroupTable group)
  {
    return this.groupDao.addGroup(group);
  }
  
  public List<GroupTable> listGroup()
  {
    return this.groupDao.listGroup();
  }
  
  public GroupTable getGroupById(int groupId)
  {
    return this.groupDao.getGroupById(groupId);
  }
  
  public void deleteGroup(String groupId)
  {
    this.groupDao.deleteGroup(groupId);
  }
  
  public List<GroupTable> listActiveGroup()
  {
    return this.groupDao.listActiveGroup();
  }
  

}
