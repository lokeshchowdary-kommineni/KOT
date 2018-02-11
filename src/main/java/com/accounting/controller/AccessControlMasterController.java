package com.accounting.controller;

import com.accounting.bean.AccessControlMasterTable;
import com.accounting.bean.GroupTable;
import com.accounting.service.AccessControlMasterService;
import com.accounting.service.AccessMasterService;
import com.accounting.service.GroupMasterService;
import java.io.PrintStream;
import java.sql.Timestamp;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AccessControlMasterController
{
  @Autowired
  private GroupMasterService groupService;
  
  @Autowired
  private AccessControlMasterService accesscontrolService;
 
  Date date = new Date();
  Timestamp currentTime = new Timestamp(this.date.getTime());
  
  @RequestMapping(value={"/AccessControl"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView listAccess()
  {
    ModelAndView model = new ModelAndView("AccessControl");
    model.addObject("join", this.accesscontrolService.join(null));
    return model;
  }
  
  @RequestMapping(value={"/save_AccessControl"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView saveGroup(HttpServletRequest request, RedirectAttributes redirectAttributes)
  {
    GroupTable group = new GroupTable();
    
    group.setGroupName(request.getParameter("Group_Name"));
    group.setGroupstatus(request.getParameter("Group_status"));
    if ((request.getParameter("Group_id") == null) || (request.getParameter("Group_id").isEmpty()))
    {
      System.out.println("Group_id :" + request.getParameter("Group_id"));
      group.setGroupid(null);
    }
    else
    {
      System.out.println("Group_id Else:" + request.getParameter("Group_id"));
      this.accesscontrolService.deleteAccess(request.getParameter("Group_id"));
      group.setGroupid(Integer.parseInt(request.getParameter("Group_id")));
    }
    int groupid = this.groupService.addGroupDetails(group);
    
    int count = Integer.parseInt(request.getParameter("count"));
    String id = request.getParameter("Group_id");
    System.out.println("Group_id count:" + count);
    int i;
    if (count != 0) {
      for (i = 0; i < count; i++)
      {
        System.out.println("Group_id count56:" + count);
        AccessControlMasterTable access = new AccessControlMasterTable();
        access.setGroupid(groupid);
        access.setModuleName(request.getParameter("Module_Name" + i));
        System.out.println("seka :" + i);
//        access.setAccessid(Integer.parseInt(request.getParameter("Access_id" + i)));
        System.out.println("Sivaraj:Access_id" + i);
        System.out.println("Sivaraj:" + request.getParameter(new StringBuilder().append("Access_id").append(i).toString()));
        
        String accessid = request.getParameter("Access_id" + i);
        if (accessid != null)
        {
          access.setAccessid(Integer.parseInt(request.getParameter("Access_id" + i)));  
          System.out.println("Group_id count568:" + count);
          if (request.getParameter("create" + i) == null) {
            access.setCreatevalue("0");
          } else {
            access.setCreatevalue("1");
          }
          if (request.getParameter("edit" + i) == null) {
            access.setEditvalue("0");
          } else {
            access.setEditvalue("1");
          }
          if (request.getParameter("delete" + i) == null) {
            access.setDeletevalue("0");
          } else {
            access.setDeletevalue("1");
          }
          if (request.getParameter("view" + i) == null) {
            access.setViewvalue("0");
          } else {
            access.setViewvalue("1");
          }
          String saveToDB = "";
          if (groupid == 0)
          {
            group.setGroupid(0);
            saveToDB = "Insert";
          }
          else
          {
            group.setGroupid(groupid);
            saveToDB = "Update";
          }
          redirectAttributes.addFlashAttribute("saveToDB", saveToDB);
          redirectAttributes.addFlashAttribute("groupid", groupid);
          this.accesscontrolService.addAccessDetails(access);
        }
      }
    } else {
      ModelAndView model=new ModelAndView("Group_Details");
    }
    ModelAndView model = new ModelAndView("redirect:Group_Details.html");
    return model;
  }
  
  @RequestMapping(value={"/Group_Details"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView listGroup()
  {
    ModelAndView model = new ModelAndView("Group_Details");
    model.addObject("GroupList", this.groupService.listGroup());
    model.addObject("accesscontrol", this.accesscontrolService.listAccess());
    return model;
  }
  
  @RequestMapping(value={"/edit-group"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView getGroup(HttpServletRequest request)
  {
    String groupId = request.getParameter("id");
    ModelAndView model = new ModelAndView("AccessControl");
    model.addObject("groupDetails", this.groupService.getGroupById(Integer.parseInt(groupId)));
    model.addObject("join", this.accesscontrolService.join(groupId));
    return model;
  }
  
  @RequestMapping(value={"/delete-group"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView deletegroup(HttpServletRequest request, RedirectAttributes redirectAttributes)
  {
    String groupId = request.getParameter("id");
    this.groupService.deleteGroup(groupId);
    this.accesscontrolService.deleteAccess(groupId);
    ModelAndView model = new ModelAndView("redirect:Group_Details.html");
    redirectAttributes.addFlashAttribute("saveToDB", "Delete");
    redirectAttributes.addFlashAttribute("groupid", groupId);
    return model;
  }
  
  
   @RequestMapping(value={"/GroupDetails"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView GroupDetail()
  {
    ModelAndView model = new ModelAndView("GroupDetails");
    return model;
  }
  
   
}
