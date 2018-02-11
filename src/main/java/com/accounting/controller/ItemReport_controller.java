
package com.accounting.controller;

import com.accounting.bean.ItemReport;
import com.accounting.service.ItemGroupZReportService;
import com.accounting.service.ItemReport_service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author shinelogics
 */
@Controller
public class ItemReport_controller {
    
     @Autowired
     private ItemReport_service reportService;
     
     @Autowired
     private ItemGroupZReportService igs;
     
     
    ItemReport ir = new ItemReport();
    
    
    @RequestMapping(value={"ItemReport"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView itemReport()
  {
      
    ModelAndView model = new ModelAndView("ItemReport");
   model.addObject("itemList",reportService.getItemList()); 
   model.addObject("itemGroupList",reportService.listItem()); 
    return model;
  }
     @RequestMapping(value={"StockSummary"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView StockSummary()
  {
      
    ModelAndView model = new ModelAndView("StockSummary"); 
    return model;
  }
   @RequestMapping(value={"ItemGroupReport"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView ItemGroupReport()
  {
      
    ModelAndView model = new ModelAndView("ItemGroupReport");
   model.addObject("itemList",reportService.getItemList()); 
   model.addObject("itemGroupList",reportService.listItem()); 
    return model;
  }
  @RequestMapping(value={"reportsave"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView itemReportSave( HttpServletRequest req) throws ParseException
  {
    
    ModelAndView model = new ModelAndView("ItemReport");   
    String gsQuery = "";
    String gsQuery1 = "";
    String textValue = "";
    
    String[] l =req.getParameterValues("list");
    
    String s="";
    
     if(l!=null)
     { 
         for(int i=0; i<l.length;i++)
         {
        if(s==""){
            s="'"+l[i]+"'";
        }else{
            s=s+",'"+l[i]+"'";
        }
         }
     }
   
//    System.out.println("S :"+s);
    
    String itemGroup =req.getParameter("itemgroup");
    String item = req.getParameter("item");
    String gsFromDate = req.getParameter("fromdate");
    String gsToDate = req.getParameter("todate");
    String month = req.getParameter("month");
    
    model.addObject("item", item);
     model.addObject("itemGroup", itemGroup);
      model.addObject("gsFromDate", gsFromDate);
       model.addObject("gsToDate", gsToDate);
        model.addObject("month", month);
   
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date convertedFromDate = sdf.parse(gsFromDate);
    String fdate=dmyFormat.format(convertedFromDate );
    
    Date convertedEndDate = sdf.parse(gsToDate);
    String edate=dmyFormat.format(convertedEndDate );
//    System.out.println(fdate);
     List<Object[]> opening=null;
     opening=igs.getItemOprningAndClosingBalance(item, fdate);
  
            if(  opening.size()<=0)
            {
                   opening=igs.getSingleItemOpenigBalance(item);

            }
          
   
    System.out.println("fffffffffff : "+ opening.size());
    
    gsQuery = " SELECT ir.inQuantity ,ir.inValue ,ir.outQuantity,ir.outValue,ir.closingQuantity,ir.closingValue,igm.itemGroupName,im.itemName,\n" +
" ir.transactionDate,ir.transactionId,ir.transactionName,ir.transactionType FROM ItemReport AS ir INNER JOIN ItemMaster AS im ON\n" +
" ir.itemId=im.id INNER JOIN ItemGroupMaster AS igm ON im.itemGroup=igm.idItem keyWhere order by ir.id ";
    String gsWhere = "";
    
    if ( itemGroup != null && !itemGroup.isEmpty())  {
        gsWhere = "WHERE   ir.itemGroupId='" + itemGroup + "'";
      } else {
        gsWhere = "";
      }
    if ((!item.isEmpty()) && (item != null)) {
        if (gsWhere != "") {
          gsWhere = gsWhere + "and ir.itemId='" + item + "'";
        } else {
          gsWhere = "WHERE   ir.itemId='" + item + "'";
        }
      }
     if (!gsFromDate.isEmpty())
      {
        if (!gsToDate.isEmpty())
        {
          if (gsWhere != "") {
            gsWhere = gsWhere + " and ir.transactionDate BETWEEN '" + fdate + "' AND '" + edate + "'  ";
          } else {
            gsWhere = gsWhere + "WHERE   ir.transactionDate BETWEEN '" + fdate + "' AND '" + edate + "'  ";
          }
        }
        else if (gsWhere != "") {
          gsWhere = gsWhere + " and ir.transactionDate>='" + fdate + "'";
        } else {
          gsWhere = gsWhere + "WHERE ir.transactionDate>='" + edate + "'";
        }
      }
     else if (!gsToDate.isEmpty()) {
        if (gsWhere != "") {
          gsWhere = gsWhere + " and ir.transactionDate<= '" + fdate + "'";
        } else {
          gsWhere = gsWhere + "WHERE ir.transactionDate<='" + edate + "'";
        }
      }
       if(l!=null )
       {
           if(gsWhere != "")
       {
            gsWhere=gsWhere+" AND ir.transactionType IN("+s+")";
       System.out.println("Different:" + gsWhere);
       }
       else {
         gsWhere=gsWhere+" WHERE ir.transactionType IN("+s+")";
       System.out.println("Different:" + gsWhere);
        }
       }
       else
       {
         gsWhere=gsWhere;  
       } 
       if(month!=null && month.equalsIgnoreCase("1"))
       {
       System.out.println("check if condition:");
        if(gsWhere != "")
       {
             gsQuery="  SELECT igm.itemGroupName,im.itemName,monthname (ir.transactionDate),im.itemName,im.itemName,im.itemName,SUM(ir.inQuantity) ,SUM(ir.inValue) ,SUM(ir.outQuantity),SUM(ir.outValue)\n" +
"  FROM ItemReport AS ir INNER JOIN ItemMaster AS im ON\n" +
" ir.itemId=im.id INNER JOIN ItemGroupMaster AS igm ON im.itemGroup=igm.idItem keyWhere ";
            gsQuery1=" SELECT ir.closingQuantity,ir.closingValue  FROM ItemReport AS ir where ir.id in ( SELECT MAX(ir.id)\n" +
"  FROM ItemReport AS ir INNER JOIN ItemMaster AS im ON\n" +
" ir.itemId=im.id INNER JOIN ItemGroupMaster AS igm ON im.itemGroup=igm.idItem keyWhere )";
       
            gsWhere=gsWhere+" GROUP BY monthname(ir.transactionDate)";
      
       }
        else
        {
             gsQuery="  SELECT igm.itemGroupName,im.itemName,monthname(ir.transactionDate),im.itemName,im.itemName,im.itemName,SUM(ir.inQuantity) ,SUM(ir.inValue) ,SUM(ir.outQuantity),SUM(ir.outValue)\n" +
"  FROM ItemReport AS ir INNER JOIN ItemMaster AS im ON\n" +
" ir.itemId=im.id INNER JOIN ItemGroupMaster AS igm ON im.itemGroup=igm.idItem keyWhere ";
            gsQuery1=" SELECT ir.closingQuantity,ir.closingValue  FROM ItemReport AS ir where ir.id in ( SELECT MAX(ir.id)\n" +
"  FROM ItemReport AS ir INNER JOIN ItemMaster AS im ON\n" +
" ir.itemId=im.id INNER JOIN ItemGroupMaster AS igm ON im.itemGroup=igm.idItem keyWhere )";
         
            gsWhere=gsWhere+" GROUP BY monthname(ir.transactionDate)";
        }
        
        if (gsWhere.isEmpty()) {
                gsQuery = gsQuery.replace("keyWhere", textValue);
                gsQuery1 = gsQuery1.replace("keyWhere", textValue);
              } else {
                gsQuery = gsQuery.replace("keyWhere", gsWhere);
                gsQuery1 = gsQuery1.replace("keyWhere", gsWhere);
              }
        
            List<Object[]> singleItemGroupbyPart1=reportService.filterReport(gsQuery);
            List<Object[]> singleItemGroupbyPart2=reportService.filterReport(gsQuery1);
            System.out.println("Size 1 "+singleItemGroupbyPart1.size());
            System.out.println("Size 21 "+singleItemGroupbyPart2.size());
            
             Object[] both={};
             Iterator    it1 = singleItemGroupbyPart1.iterator();
             Iterator it2 = singleItemGroupbyPart2.iterator();
             List<Object[]> SingleItemGroupBYmonth = new ArrayList<Object[]>();
           
            while(it1.hasNext() && it2.hasNext()) 
            {
               Object[] v1= (Object[]) it1.next();
               Object[] v2= (Object[]) it2.next();

                both = ArrayUtils.addAll(v1, v2);
                SingleItemGroupBYmonth.add(both);
            }
            
              model.addObject("reportList", SingleItemGroupBYmonth);
              model.addObject("itemOpeningBalance", opening);
              model.addObject("month", month);
       
       }
       else
       {
             if (gsWhere.isEmpty()) {
                gsQuery = gsQuery.replace("keyWhere", textValue);
              } else {
                gsQuery = gsQuery.replace("keyWhere", gsWhere);
              }
              model.addObject("reportList", this.reportService.filterReport(gsQuery));
              model.addObject("itemOpeningBalance", opening);
              model.addObject("day", 1);
              
       } 
       
       
      
      System.out.println("query:" + gsQuery);
          
    
    model.addObject("itemList",reportService.getItemList()); 
   
    return model;
  }
  @RequestMapping(value={"reportGroupPreview"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView reportGroupPreview( HttpServletRequest req) throws ParseException
  {
    
    ModelAndView model = new ModelAndView("ItemGroupReport");
    String gsQuery = "";
    String gsQuery1 = "";
    String textValue = "";
    
    String[] l =req.getParameterValues("list");    
    String s="";
    
     if(l!=null)
     { 
         for(int i=0; i<l.length;i++)
         {
        if(s==""){
            s="'"+l[i]+"'";
        }else{
            s=s+",'"+l[i]+"'";
        }
         }
     }
    String itemGroup =req.getParameter("itemgroup");
    String item = req.getParameter("item");
    String gsFromDate = req.getParameter("fromdate");
    String gsToDate = req.getParameter("todate");
   String month = req.getParameter("month");
   
    model.addObject("item", item);
     model.addObject("itemGroup", itemGroup);
      model.addObject("gsFromDate", gsFromDate);
       model.addObject("gsToDate", gsToDate);
        model.addObject("month", month);
   
    String childGroupsids=igs.returnChildItemids(itemGroup);
   
      String id="";
            if("".equals(childGroupsids)) 
            {  
            id="'"+itemGroup+"'";
            }
            else
            {
             id="'"+itemGroup+"',"+childGroupsids;   
            }  
  
   
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date convertedCurrentDate = sdf.parse(gsFromDate);
    String fdate=dmyFormat.format(convertedCurrentDate );
//    System.out.println(fdate);
    
    Date convertedEndDate = sdf.parse(gsToDate);
    String Edate=dmyFormat.format(convertedEndDate );
    
    
    
    List<Object[]> itemGroupOpeningFromEntries =igs.getGroupOprningAndClosingBalance(id, fdate);
    System.out.println(" size Openinf "+itemGroupOpeningFromEntries.size());
    List<Object[]> itemOPeningfromItemmaster =igs.getAllItemOpeningBalanceFromItemMaster(fdate,id);
   // List<Object[]> listallGroupClosing =igs.getGroupOprningAndClosingBalanceEnddate(id, Edate);
   // System.out.println(" size listallGroupClosing "+listallGroupClosing.size());
    
  
    
    gsQuery = " SELECT SUM(ir.inQuantity) ,SUM(ir.inValue) ,SUM(ir.outQuantity),SUM(ir.outValue),igm.itemGroupName,im.itemName,\n" +
            " ir.transactionDate,ir.itemId,ir.itemId,ir.itemId FROM ItemReport AS ir INNER JOIN ItemMaster AS im ON\n" +
            " ir.itemId=im.id INNER JOIN ItemGroupMaster AS igm ON im.itemGroup=igm.idItem keyWhere";
    gsQuery1 = "SELECT ir.closingQuantity,ir.closingValue FROM ItemReport AS ir where ir.id in  (SELECT max(ir.id) FROM ItemReport AS ir INNER JOIN ItemMaster AS im ON\n" +
            " ir.itemId=im.id INNER JOIN ItemGroupMaster AS igm ON im.itemGroup=igm.idItem keyWhere)";
    String gsWhere = "";
    String Groupby=" Group by ";
    Groupby=Groupby+"ir.itemGroupId,ir.itemId,ir.transactionDate";
    if ( itemGroup != null && !itemGroup.isEmpty())  {
        gsWhere = "WHERE   ir.itemGroupId in ("+id+")";
        
      } else {
        gsWhere = "";
      }
    if (((item != null) && !item.isEmpty()) ) {
        if (gsWhere != "") {
          gsWhere = gsWhere + "and ir.itemId='" + item + "'";
        } else {
          gsWhere = "WHERE   ir.itemId='" + item + "'";
        }
      }
     if (!gsFromDate.isEmpty())
      {
           
        if (!gsToDate.isEmpty())
        {
          if (gsWhere != "") {
            gsWhere = gsWhere + " and ir.transactionDate BETWEEN '" + fdate + "' AND '" + Edate + "'   ";
          } else {
            gsWhere = gsWhere + "WHERE   ir.transactionDate BETWEEN '" + fdate + "' AND '" + Edate + "'  ";
          }
        }
        else if (gsWhere != "") {
          gsWhere = gsWhere + " and ir.transactionDate>='" + fdate + "'";
        } else {
          gsWhere = gsWhere + "WHERE ir.transactionDate>='" + Edate + "'";
        }
      }
     else if (!gsToDate.isEmpty()) {
         
        if (gsWhere != "") {
          gsWhere = gsWhere + " and ir.transactionDate<= '" + fdate + "'";
        } else {
          gsWhere = gsWhere + "WHERE ir.transactionDate<= '" + Edate + "'";
        }
      }
       if(l!=null )
       {
           Groupby=Groupby+",ir.transactionType";
           
           if(gsWhere != "")
            {
                 gsWhere=gsWhere+" AND ir.transactionType IN("+s+")  ";
         //   System.out.println("Different:" + gsWhere);
            }
            else {
              gsWhere=gsWhere+" WHERE ir.transactionType IN("+s+")";
         //   System.out.println("Different:" + gsWhere);
             }
       }
       else
       {
         gsWhere=gsWhere;  
       } 
       if(month!=null && month.equalsIgnoreCase("1"))
       {
       System.out.println("check if condition:");
        if(gsWhere != "")
       {
           gsQuery=" SELECT SUM(ir.inQuantity) ,SUM(ir.inValue) ,SUM(ir.outQuantity),SUM(ir.outValue),igm.itemGroupName,im.itemName,\n" +
                    " monthname(ir.transactionDate),ir.itemId,ir.itemId,ir.itemId FROM ItemReport AS ir INNER JOIN ItemMaster AS im ON\n" +
                    " ir.itemId=im.id INNER JOIN ItemGroupMaster AS igm ON im.itemGroup=igm.idItem keyWhere ";
           gsQuery1=" SELECT ir.closingQuantity,ir.closingValue FROM ItemReport AS ir where ir.id in (SELECT MAX(ir.id) FROM ItemReport AS ir INNER JOIN ItemMaster AS im ON\n" +
                    " ir.itemId=im.id INNER JOIN ItemGroupMaster AS igm ON im.itemGroup=igm.idItem keyWhere ) ";
          //System.out.println("check gsquery:"+gsQuery);
            gsWhere=gsWhere+" GROUP BY ir.itemGroupId,ir.itemId,MONTH(ir.transactionDate)";
      
       }
        else
        {
            gsQuery="SELECT SUM(ir.inQuantity) ,SUM(ir.inValue) ,SUM(ir.outQuantity),SUM(ir.outValue),igm.itemGroupName,im.itemName,\n" +
                " monthname(ir.transactionDate),ir.itemId,ir.itemId,ir.itemId FROM ItemReport AS ir INNER JOIN ItemMaster AS im ON\n" +
                " ir.itemId=im.id INNER JOIN ItemGroupMaster AS igm ON im.itemGroup=igm.idItem keyWhere";
            gsQuery1="SELECT ir.closingQuantity,ir.closingValue FROM ItemReport AS ir where ir.id in  (SELECT MAX(ir.id) FROM ItemReport AS ir INNER JOIN ItemMaster AS im ON\n" +
                " ir.itemId=im.id INNER JOIN ItemGroupMaster AS igm ON im.itemGroup=igm.idItem keyWhere)";
          // System.out.println("check gsquery:"+gsQuery);
            gsWhere=gsWhere+" GROUP BY ir.itemGroupId,ir.itemId,MONTH(ir.transactionDate)";
        }
        
        if (gsWhere.isEmpty()) {
        gsQuery = gsQuery.replace("keyWhere", textValue);
        gsQuery1 = gsQuery1.replace("keyWhere", textValue);
        } else {
          gsQuery = gsQuery.replace("keyWhere", gsWhere);
          gsQuery1 = gsQuery1.replace("keyWhere", gsWhere);
        }
        System.out.println("gsQuery1 gsQuery1gsQuery1 "+gsQuery1);
         List<Object[]> ItemGroupbyPart1=reportService.filterReport(gsQuery);
            List<Object[]> ItemGroupbyPart2=reportService.filterReport(gsQuery1);
            System.out.println("Size 1 "+ItemGroupbyPart1.size());
            System.out.println("Size 21 "+ItemGroupbyPart2.size());
            
             Object[] both={};
             Iterator    it1 = ItemGroupbyPart1.iterator();
             Iterator it2 = ItemGroupbyPart2.iterator();
             List<Object[]> ItemGroupbothmonth = new ArrayList<Object[]>();
           
            while(it1.hasNext() && it2.hasNext()) 
            {
               Object[] v1= (Object[]) it1.next();
               Object[] v2= (Object[]) it2.next();

                both = ArrayUtils.addAll(v1, v2);
                ItemGroupbothmonth.add(both);
            }
            model.addObject("reportList", ItemGroupbothmonth);
            model.addObject("listallGroupOPening", itemGroupOpeningFromEntries);
            model.addObject("itemOpening", itemOPeningfromItemmaster);
       
       }
       else
       {
       //  System.out.println("check else condition:");
         gsWhere=gsWhere+Groupby;
         
        
        if (gsWhere.isEmpty()) {
        gsQuery = gsQuery.replace("keyWhere", textValue);
        gsQuery1 = gsQuery1.replace("keyWhere", textValue);
        } else {
        
          gsQuery = gsQuery.replace("keyWhere", gsWhere);
          gsQuery1 = gsQuery1.replace("keyWhere", gsWhere);
        }
        // System.out.println(" Query  "+gsQuery);
         List<Object[]> ItemGroupbyPart1=reportService.filterReport(gsQuery);
            List<Object[]> ItemGroupbyPart2=reportService.filterReport(gsQuery1);
        //    System.out.println("Size 1 "+ItemGroupbyPart1.size());
          //  System.out.println("Size 21 "+ItemGroupbyPart2.size());
            
             Object[] both={};
             Iterator    it1 = ItemGroupbyPart1.iterator();
             Iterator it2 = ItemGroupbyPart2.iterator();
             List<Object[]> ItemGroupboth = new ArrayList<Object[]>();
           
            while(it1.hasNext() && it2.hasNext()) 
            {
               Object[] v1= (Object[]) it1.next();
               Object[] v2= (Object[]) it2.next();

                both = ArrayUtils.addAll(v1, v2);
                ItemGroupboth.add(both);
            }
            
            model.addObject("reportList", ItemGroupboth);
            model.addObject("listallGroupOPening", itemGroupOpeningFromEntries);
            model.addObject("itemOpening", itemOPeningfromItemmaster);
        
       }
       
       
        
        
        
      System.out.println("query:" + gsQuery);
          
    
   
    model.addObject("itemGroupList",reportService.listItem());   
    return model;
  }
  
  @RequestMapping(value={"Get_Stock_Summary"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView Get_Stock_Summary( HttpServletRequest req) throws ParseException
  {
    
    ModelAndView model = new ModelAndView("StockSummary");    
   
   
    String gsFromDate = req.getParameter("startDate");
    String gsToDate = req.getParameter("endDate");
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date convertedCurrentDate = sdf.parse(gsFromDate);
    String fdate=dmyFormat.format(convertedCurrentDate );//    System.out.println(fdate);
    
    Date convertedEndDate = sdf.parse(gsToDate);
    String Edate=dmyFormat.format(convertedEndDate ); 
      model.addObject("gsFromDate", gsFromDate);
       model.addObject("gsToDate", gsToDate);
   
     List<Object[]> stockSummary=igs.getAllStockSummartBasedOnPRIMARYGroup("PRIMARY",Edate);
    model.addObject("stockSummary",stockSummary);   
    return model;
  }
}