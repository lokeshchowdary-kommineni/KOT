/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.controller;

import com.accounting.bean.DatatableObject;
import com.accounting.service.StockMaintanceService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author SHINELOGICS
 */
@Controller
public class StockMaintanceController {
      @Autowired
    private StockMaintanceService sms;
    
    
    
    @RequestMapping(value={"/StockReport"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView StockReportDashboard()
  {
      ModelAndView model = new ModelAndView("StockReport");
      return model;
  }
    
     @ResponseBody
   @RequestMapping(value={"GetStockReport"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   
        public DatatableObject GetStockReport( HttpServletRequest req)
        {
          DatatableObject db =new DatatableObject();

               String[] cols = {"item_name","current_stock","unit_name"};
        String table = "";


        int amount = 10;
        int start = 0;
        int echo = 0;
        int col = 0;


        String engine = "";
        String browser = "";
        String platform = "";
        String version = "";
        String grade = "";

        String dir = "asc";
        String sStart = req.getParameter("start");
        String sAmount = req.getParameter("length");
        String sEcho = req.getParameter("sEcho");
        String sCol = req.getParameter("order[0][column]");
        String sdir = req.getParameter("order[0][dir]");


        if (sStart != null) {
            start = Integer.parseInt(sStart);
            if (start < 0)
                start = 0;
        }
        if (sAmount != null) {
            amount = Integer.parseInt(sAmount);
            if (amount < 10 || amount > 100)
                amount = 10;
        }
        if (sEcho != null) {
            echo = Integer.parseInt(sEcho);
        }
        if (sCol != null) {
            col = Integer.parseInt(sCol);
            if (col < 0 || col > 6)
                col = 0;
        }
        if (sdir != null) {
            if (!sdir.equals("asc"))
                dir = "desc";
        }
        String colName = cols[col];
        int total = 0;

        try {
            String sql = "SELECT im.item_name,igm.item_group_name,im.opening_stock+(COALESCE(SUM(ir.in_quantity),0)-COALESCE(SUM(ir.out_quantity),0)),ROUND(im.opening_stock+(COALESCE(SUM(ir.in_quantity),0)-COALESCE(SUM(ir.out_quantity),0))*1000/750,2),u.unit_name  FROM `item_master` AS im JOIN `unit_master` AS u ON u.id_unit=im.unit JOIN item_group_master AS igm ON igm.id_item=im.item_group LEFT JOIN item_report AS ir ON ir.item_id=im.id WHERE im.bit_item!=\"b'1'\" AND igm.item_group_name=\"HOT DRINKS\" GROUP BY im.id ";
               List<Object[]> list = sms.GetStockReportObject(sql);
               total=list.size();
        }catch(Exception e){

        }
        int totalAfterFilter = total;

        try {
            String searchSQL = "";
            String sql = "SELECT im.item_name,igm.item_group_name,im.opening_stock+(COALESCE(SUM(ir.in_quantity),0)-COALESCE(SUM(ir.out_quantity),0)),ROUND(im.opening_stock+(COALESCE(SUM(ir.in_quantity),0)-COALESCE(SUM(ir.out_quantity),0))*1000/750,2),u.unit_name  FROM `item_master` AS im JOIN `unit_master` AS u ON u.id_unit=im.unit JOIN item_group_master AS igm ON igm.id_item=im.item_group LEFT JOIN item_report AS ir ON ir.item_id=im.id WHERE im.bit_item!=\"b'1'\" AND igm.item_group_name=\"HOT DRINKS\" GROUP BY im.id ";
            String searchTerm =req.getParameter("search[value]");
             String globeSearch =  " and (im.item_name like '"+searchTerm+"%')";
      
            if(searchTerm!=""){
                searchSQL=globeSearch;
            }
            sql += searchSQL;
            sql += " order by " + colName + " " + dir;
            sql += " limit " + start + ", " + amount;
            // For aData
           
             List<Object[]> list2 = sms.GetStockReportObject(sql);
             // For Filter Count 
            String sql2 = "SELECT im.item_name,igm.item_group_name,im.opening_stock+(COALESCE(SUM(ir.in_quantity),0)-COALESCE(SUM(ir.out_quantity),0)),ROUND(im.opening_stock+(COALESCE(SUM(ir.in_quantity),0)-COALESCE(SUM(ir.out_quantity),0))*1000/750,2),u.unit_name  FROM `item_master` AS im JOIN `unit_master` AS u ON u.id_unit=im.unit JOIN item_group_master AS igm ON igm.id_item=im.item_group LEFT JOIN item_report AS ir ON ir.item_id=im.id WHERE im.bit_item!=\"b'1'\" AND igm.item_group_name=\"HOT DRINKS\" GROUP BY im.id ";
           if (searchTerm != "") {
               
                sql2 += searchSQL; 
              List<Object[]> count = sms.GetStockReportCount(sql2);
              totalAfterFilter=count.size();
            }

       db.setiTotalRecords(total);
       db.setiTotalDisplayRecords(totalAfterFilter);
       db.setAaData(list2);
        } catch (Exception e) {

        }
          return db;
            }  
  
        @RequestMapping(value={"/StockReportBeer"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView StockReportBeer()
  {
      ModelAndView model = new ModelAndView("StockReportBeer");
      return model;
  }
    
      @ResponseBody
   @RequestMapping(value={"GetBeerStockReport"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   
        public DatatableObject GetBeerStockReport( HttpServletRequest req)
        {
          DatatableObject db =new DatatableObject();

               String[] cols = {"item_name","current_stock","unit_name"};
        String table = "";


        int amount = 10;
        int start = 0;
        int echo = 0;
        int col = 0;


        String engine = "";
        String browser = "";
        String platform = "";
        String version = "";
        String grade = "";

        String dir = "asc";
        String sStart = req.getParameter("start");
        String sAmount = req.getParameter("length");
        String sEcho = req.getParameter("sEcho");
        String sCol = req.getParameter("order[0][column]");
        String sdir = req.getParameter("order[0][dir]");


        if (sStart != null) {
            start = Integer.parseInt(sStart);
            if (start < 0)
                start = 0;
        }
        if (sAmount != null) {
            amount = Integer.parseInt(sAmount);
            if (amount < 10 || amount > 100)
                amount = 10;
        }
        if (sEcho != null) {
            echo = Integer.parseInt(sEcho);
        }
        if (sCol != null) {
            col = Integer.parseInt(sCol);
            if (col < 0 || col > 6)
                col = 0;
        }
        if (sdir != null) {
            if (!sdir.equals("asc"))
                dir = "desc";
        }
        String colName = cols[col];
        int total = 0;

        try {
            String sql = "SELECT im.item_name ,igm.item_group_name,im.opening_stock+(COALESCE(SUM(ir.in_quantity),0)-COALESCE(SUM(ir.out_quantity),0)),ROUND(im.opening_stock+(COALESCE(SUM(ir.in_quantity),0)-COALESCE(SUM(ir.out_quantity),0))/12,2),u.unit_name  FROM `item_master` AS im JOIN `unit_master` AS u ON u.id_unit=im.unit JOIN item_group_master AS igm ON igm.id_item=im.item_group LEFT JOIN item_report AS ir ON ir.item_id=im.id WHERE im.bit_item!=\"b'1'\" AND igm.item_group_name=\"BEER\" GROUP BY im.id ";
               List<Object[]> list = sms.GetStockReportObject(sql);
               total=list.size();
        }catch(Exception e){

        }
        int totalAfterFilter = total;

        try {
            String searchSQL = "";
            String sql = "SELECT im.item_name ,igm.item_group_name,im.opening_stock+(COALESCE(SUM(ir.in_quantity),0)-COALESCE(SUM(ir.out_quantity),0)),ROUND(im.opening_stock+(COALESCE(SUM(ir.in_quantity),0)-COALESCE(SUM(ir.out_quantity),0))/12,2),u.unit_name  FROM `item_master` AS im JOIN `unit_master` AS u ON u.id_unit=im.unit JOIN item_group_master AS igm ON igm.id_item=im.item_group LEFT JOIN item_report AS ir ON ir.item_id=im.id WHERE im.bit_item!=\"b'1'\" AND igm.item_group_name=\"BEER\" GROUP BY im.id " ;
            String searchTerm =req.getParameter("search[value]");
             String globeSearch =  " and (im.item_name like '"+searchTerm+"%')";
      
            if(searchTerm!=""){
                searchSQL=globeSearch;
            }
            sql += searchSQL;
            sql += " order by " + colName + " " + dir;
            sql += " limit " + start + ", " + amount;
            // For aData
           
             List<Object[]> list2 = sms.GetStockReportObject(sql);
             // For Filter Count 
            String sql2 = "SELECT im.item_name ,igm.item_group_name,im.opening_stock+(COALESCE(SUM(ir.in_quantity),0)-COALESCE(SUM(ir.out_quantity),0)),ROUND(im.opening_stock+(COALESCE(SUM(ir.in_quantity),0)-COALESCE(SUM(ir.out_quantity),0))/12,2),u.unit_name FROM `item_master` AS im JOIN `unit_master` AS u ON u.id_unit=im.unit JOIN item_group_master AS igm ON igm.id_item=im.item_group LEFT JOIN item_report AS ir ON ir.item_id=im.id WHERE im.bit_item!=\"b'1'\" AND igm.item_group_name=\"BEER\" GROUP BY im.id " ;
           if (searchTerm != "") {
               
                sql2 += searchSQL; 
              List<Object[]> count = sms.GetStockReportCount(sql2);
              totalAfterFilter=count.size();
            }

       db.setiTotalRecords(total);
       db.setiTotalDisplayRecords(totalAfterFilter);
       db.setAaData(list2);
        } catch (Exception e) {

        }
          return db;
            }  
        
        
        @RequestMapping(value={"/WineStock"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView WineStock()
  {
      ModelAndView model = new ModelAndView("WineStock");
      return model;
  }
    
     @ResponseBody
   @RequestMapping(value={"GetWineStockReport"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   
        public DatatableObject GetWineStockReport( HttpServletRequest req)
        {
          DatatableObject db =new DatatableObject();

               String[] cols = {"item_name","current_stock","unit_name"};
        String table = "";


        int amount = 10;
        int start = 0;
        int echo = 0;
        int col = 0;


        String engine = "";
        String browser = "";
        String platform = "";
        String version = "";
        String grade = "";

        String dir = "asc";
        String sStart = req.getParameter("start");
        String sAmount = req.getParameter("length");
        String sEcho = req.getParameter("sEcho");
        String sCol = req.getParameter("order[0][column]");
        String sdir = req.getParameter("order[0][dir]");


        if (sStart != null) {
            start = Integer.parseInt(sStart);
            if (start < 0)
                start = 0;
        }
        if (sAmount != null) {
            amount = Integer.parseInt(sAmount);
            if (amount < 10 || amount > 100)
                amount = 10;
        }
        if (sEcho != null) {
            echo = Integer.parseInt(sEcho);
        }
        if (sCol != null) {
            col = Integer.parseInt(sCol);
            if (col < 0 || col > 6)
                col = 0;
        }
        if (sdir != null) {
            if (!sdir.equals("asc"))
                dir = "desc";
        }
        String colName = cols[col];
        int total = 0;

        try {
            String sql = "SELECT im.item_name,igm.item_group_name,im.opening_stock+(COALESCE(SUM(ir.in_quantity),0)-COALESCE(SUM(ir.out_quantity),0)),ROUND(im.opening_stock+(COALESCE(SUM(ir.in_quantity),0)-COALESCE(SUM(ir.out_quantity),0))*1000/(3*750),2),u.unit_name  FROM `item_master` AS im JOIN `unit_master` AS u ON u.id_unit=im.unit JOIN item_group_master AS igm ON igm.id_item=im.item_group LEFT JOIN item_report AS ir ON ir.item_id=im.id WHERE im.bit_item!=\"b'1'\" AND igm.item_group_name=\"WINE\" GROUP BY im.id";
               List<Object[]> list = sms.GetStockReportObject(sql);
               total=list.size();
        }catch(Exception e){

        }
        int totalAfterFilter = total;

        try {
            String searchSQL = "";
            String sql = "SELECT im.item_name,igm.item_group_name,im.opening_stock+(COALESCE(SUM(ir.in_quantity),0)-COALESCE(SUM(ir.out_quantity),0)),ROUND(im.opening_stock+(COALESCE(SUM(ir.in_quantity),0)-COALESCE(SUM(ir.out_quantity),0))*1000/(3*750),2),u.unit_name  FROM `item_master` AS im JOIN `unit_master` AS u ON u.id_unit=im.unit JOIN item_group_master AS igm ON igm.id_item=im.item_group LEFT JOIN item_report AS ir ON ir.item_id=im.id WHERE im.bit_item!=\"b'1'\" AND igm.item_group_name=\"WINE\" GROUP BY im.id " ;
            String searchTerm =req.getParameter("search[value]");
             String globeSearch =  " and (im.item_name like '"+searchTerm+"%')";
      
            if(searchTerm!=""){
                searchSQL=globeSearch;
            }
            sql += searchSQL;
            sql += " order by " + colName + " " + dir;
            sql += " limit " + start + ", " + amount;
            // For aData
           
             List<Object[]> list2 = sms.GetStockReportObject(sql);
             // For Filter Count 
            String sql2 = "SELECT im.item_name,igm.item_group_name,im.opening_stock+(COALESCE(SUM(ir.in_quantity),0)-COALESCE(SUM(ir.out_quantity),0)),ROUND(im.opening_stock+(COALESCE(SUM(ir.in_quantity),0)-COALESCE(SUM(ir.out_quantity),0))*1000/(3*750),2),u.unit_name  FROM `item_master` AS im JOIN `unit_master` AS u ON u.id_unit=im.unit JOIN item_group_master AS igm ON igm.id_item=im.item_group LEFT JOIN item_report AS ir ON ir.item_id=im.id WHERE im.bit_item!=\"b'1'\" AND igm.item_group_name=\"WINE\" GROUP BY im.id " ;
           if (searchTerm != "") {
               
                sql2 += searchSQL; 
              List<Object[]> count = sms.GetStockReportCount(sql2);
              totalAfterFilter=count.size();
            }

       db.setiTotalRecords(total);
       db.setiTotalDisplayRecords(totalAfterFilter);
       db.setAaData(list2);
        } catch (Exception e) {

        }
          return db;
            }  
}
