/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.controller;

import com.accounting.bean.DatatableObject;
import com.accounting.bean.Waitermaster;
import com.accounting.service.StockMaintanceService;
import com.accounting.service.WaiterService;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author MR
 */
@Controller
public class WaiterController {
    
    @Autowired
    private WaiterService waiterservice;
    
   @Autowired
    private StockMaintanceService sms;
    
    Waitermaster waiter=new Waitermaster();

    @RequestMapping(value={"WaiterMaster"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView WaiterForm(HttpSession session)
    {
       ModelAndView model=new ModelAndView("WaiterMaster");
       model.addObject("waiterList",waiterservice.listWaiter());
       return model.addObject("waiterForm",waiter);
    }

    @RequestMapping(value="saveWaiter",method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String saveWaiter(@ModelAttribute("waiterForm")@Validated Waitermaster waiter, BindingResult result){
        waiterservice.saveWaiter(waiter);
        return "redirect:/WaiterMaster.html";
    }
    
    @RequestMapping(value="EditWaiter", method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView editWaiter(@ModelAttribute("waiterForm")@Validated Waitermaster waiter, BindingResult result,HttpServletRequest req)
    {
        int editid=Integer.parseInt(req.getParameter("wid"));
        System.out.println("wid is "+editid);
        ModelAndView model = new ModelAndView("WaiterMaster");
        List<Waitermaster> list=waiterservice.listWaiter();
        System.out.println("length of list "+list.size());
        model.addObject("waiterList",waiterservice.listWaiter());
        model.addObject("waiterForm",waiterservice.getWaiterById(editid));
        Iterator itr=list.iterator();
         while(itr.hasNext()){  
            Waitermaster wt=(Waitermaster)itr.next();  
            System.out.println(wt.getWaiterName());  
        }  
//        model.addObject("unitForm",us.getUnitbyId(Integer.parseInt(editid)));
//        model.addObject("unitList",us.listUnit());
        return model;
    }
    
    @RequestMapping(value={"DeleteWaiter"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView deleteUnit(HttpServletRequest req,RedirectAttributes redirectAttributes)
    { 

        try{       

        int deleteid=Integer.parseInt(req.getParameter("wid"));
        waiterservice.deleteWaiter(deleteid);
        ModelAndView model = new ModelAndView("redirect:/WaiterMaster.html");
        redirectAttributes.addFlashAttribute("message", "Your Record has been Deleted... ");
        return model;

        }catch(Exception e)
        {


            ModelAndView model1 = new ModelAndView("redirect:WaiterMaster.html");  
            redirectAttributes.addFlashAttribute("message", "Waiter Already Used Somewhere,Cannot be Deleted"); 
            return model1;
        }



    }
    
     @RequestMapping(value={"WaiterReport"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView WaiterReport(HttpSession session)
    {
       ModelAndView model=new ModelAndView("WaiterReport");
         model.addObject("waiterList",waiterservice.listWaiter());
         
       return model;
    }
    
      @RequestMapping(value={"AllWaiterReport"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView AllWaiterReport(HttpSession session)
    {
       ModelAndView model=new ModelAndView("AllWaiterReport");
       return model;
    }
    
    
     @RequestMapping(value="WaiterItemReport",method={org.springframework.web.bind.annotation.RequestMethod.POST})
   public ModelAndView WaiterItemReport(HttpSession session,HttpServletRequest req,RedirectAttributes redirectAttributes)
    {
       String waiterName=req.getParameter("waitername");
         System.out.println("waiterName :"+waiterName);
       String fDate=req.getParameter("FromDate");
         System.out.println("fromdate"+fDate);
       String todate=req.getParameter("ToDate");
         System.out.println("todate"+todate);
            
         
       ModelAndView model=new ModelAndView("WaiterReport");
        List<Object[]> Object =  sms.getWaiterReport(fDate, todate, waiterName);
         model.addObject("list", Object);
                   int s=Object.size();
                   if(s==0)
                    {
                     ModelAndView model1 = new ModelAndView("redirect:WaiterReport.html");
                     redirectAttributes.addFlashAttribute("msg","No Records Found !");
                      return model1;
                    }
           model.addObject("waiterList",waiterservice.listWaiter());  
          
       return model;
    }
   
    @ResponseBody
   @RequestMapping(value={"GetWaiterReport"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   
        public DatatableObject GetWineStockReport( HttpServletRequest req)
        {
          DatatableObject db =new DatatableObject();

               String[] cols = {"item_name","current_stock","unit_name","item_name","current_stock","unit_name","item_name"};
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
            String sql = "SELECT w.waiter_name,i.item_name,i.vap_alt,s.quantity,(i.vap_alt*s.quantity),s.invoice_no,si.sdate ,gm.item_group_name FROM sales_bill AS s JOIN sale_invoice AS si ON  s.invoice_no=si.invoice_no LEFT JOIN waitermaster AS w ON w.waiter_id =s.waiter_id JOIN item_master AS i ON  s.item_code=i.item_code INNER JOIN item_group_master AS gm ON gm.id_item =i.item_group";
               List<Object[]> list = sms.GetStockReportObject(sql);
               total=list.size();
        }catch(Exception e){

        }
        int totalAfterFilter = total;

        try {
            String searchSQL = "";
            String sql ="SELECT w.waiter_name,i.item_name,i.vap_alt,s.quantity,(i.vap_alt*s.quantity),s.invoice_no,si.sdate ,gm.item_group_name FROM sales_bill AS s JOIN sale_invoice AS si ON  s.invoice_no=si.invoice_no LEFT JOIN waitermaster AS w ON w.waiter_id =s.waiter_id JOIN item_master AS i ON  s.item_code=i.item_code INNER JOIN item_group_master AS gm ON gm.id_item =i.item_group" ;
            String searchTerm =req.getParameter("search[value]");
             String globeSearch =  " where (w.waiter_name like '%"+searchTerm+"%')";
      
            if(searchTerm!=""){
                searchSQL=globeSearch;
            }
            sql += searchSQL;
            sql += " order by " + colName + " " + dir;
            sql += " limit " + start + ", " + amount;
            // For aData
           
             List<Object[]> list2 = sms.GetStockReportObject(sql);
             // For Filter Count 
            String sql2 = "SELECT w.waiter_name,i.item_name,i.vap_alt,s.quantity,(i.vap_alt*s.quantity),s.invoice_no,si.sdate ,gm.item_group_name FROM sales_bill AS s JOIN sale_invoice AS si ON  s.invoice_no=si.invoice_no LEFT JOIN waitermaster AS w ON w.waiter_id =s.waiter_id JOIN item_master AS i ON  s.item_code=i.item_code INNER JOIN item_group_master AS gm ON gm.id_item =i.item_group" ;
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