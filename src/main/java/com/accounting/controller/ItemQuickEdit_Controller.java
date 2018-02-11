/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.controller;
import com.accounting.bean.DatatableObject;
import com.accounting.bean.ItemMaster;
import com.accounting.service.AccountDBOService;
import com.accounting.service.ItemMasterService;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Admin
 */
@RestController
public class ItemQuickEdit_Controller {
    @Autowired
    private ItemMasterService itemMasterService;   
    @Autowired
    private AccountDBOService as;
   
    @RequestMapping(value={"GetDatatableQuickEdit"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   
    public DatatableObject getDatatableQuick( HttpServletRequest req)
    { 
        System.out.println("hello Quick Edit");
      DatatableObject db =new DatatableObject();
           String[] cols = { "item_code", "item_group", "item_name" };
    String table = "item_master";
    
//    JSONObject result = new JSONObject();
//    JSONArray array = new JSONArray();
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
     
    System.out.println("sStart" +sStart);
    System.out.println("sAmount" +sAmount);
    
    
      String itemCode = req.getParameter("columns[2][search][value]");
      System.out.println(" item code " + itemCode );
    List<String> sArray = new ArrayList<String>();
    if (!itemCode.equals("")) {
        String sCode = " LOWER(item_code) like LOWER('" + itemCode + "%')";
        sArray.add(sCode);
 
    }

    String individualSearch = "";
    if(sArray.size()==1){
        individualSearch = sArray.get(0);
    }else if(sArray.size()>1){
        for(int i=0;i<sArray.size()-1;i++){
            individualSearch += sArray.get(i)+ " and ";
        }
        individualSearch += sArray.get(sArray.size()-1);
    }
     
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
        String sql = "SELECT item_code ,item_name ,basic_price,COALESCE(lr, 0 )  AS LR,COALESCE(er,0) AS ER ,COALESCE(ca,0) AS CA ,COALESCE(cb,0) AS CB,COALESCE(va,0) AS VA ,COALESCE(vb,0) AS VB ,COALESCE(mc,0) AS MC ,COALESCE(mca,0) AS MCA ,COALESCE(rol ,0) AS ROL,COALESCE(tr,0) AS TR,total_unit  FROM item_master where basic_vat_price IS NULL ";
        System.out.println("abirami today" + sql);
        List<Object[]> list = itemMasterService.GetDatatableObject(sql);
        total=list.size();
        System.out.println("the list object" + total);
        
    }catch(Exception e){
         
    }
    int totalAfterFilter = total;
    //result.put("sEcho",echo);
 
    try {
        String searchSQL = "";
        String sql = "SELECT item_code ,item_name ,basic_price,COALESCE(lr, 0 )  AS LR,COALESCE(er,0) AS ER ,COALESCE(ca,0) AS CA ,COALESCE(cb,0) AS CB,COALESCE(va,0) AS VA ,COALESCE(vb,0) AS VB ,COALESCE(mc,0) AS MC ,COALESCE(mca,0) AS MCA ,COALESCE(rol ,0) AS ROL,COALESCE(tr,0) AS TR,total_unit  FROM item_master where basic_vat_price IS NULL ";
        String searchTerm =req.getParameter("columns[1][search][value]");
        System.out.println(" Valahsdfh "+searchTerm);
         String globeSearch =  " AND (LOWER(item_name) like LOWER('"+searchTerm+"%'))";
        if(searchTerm!=""&&individualSearch!=""){
            searchSQL = globeSearch + " and " + individualSearch;
        }
        else if(individualSearch!=""){
            searchSQL = " AND " + individualSearch;
        }else if(searchTerm!=""){
            searchSQL=globeSearch;
        }
        sql += searchSQL;
        sql += " order by " + colName + " " + dir;
        sql += " limit " + start + ", " + amount;
        System.out.println(" SQL abi "+sql);
        // For aData
         List<Object[]> list2 = itemMasterService.GetDatatableObject(sql);
         // For Filter Count 
        String sql2 = "SELECT item_code ,item_name ,basic_price,COALESCE(lr, 0 )  AS LR,COALESCE(er,0) AS ER ,COALESCE(ca,0) AS CA ,COALESCE(cb,0) AS CB,COALESCE(va,0) AS VA ,COALESCE(vb,0) AS VB ,COALESCE(mc,0) AS MC ,COALESCE(mca,0) AS MCA ,COALESCE(rol ,0) AS ROL,COALESCE(tr,0) AS TR,total_unit  FROM item_master where basic_vat_price IS NULL ";
       if (searchTerm != "") {
            sql2 += searchSQL;
          List<Object[]> count = itemMasterService.GetDatatableCount(sql2);
          totalAfterFilter=count.size();
        }
  
   db.setiTotalRecords(total);
   db.setiTotalDisplayRecords(totalAfterFilter);
   db.setAaData(list2);
    } catch (Exception e) {
 
    }
      return db;
	}
      @RequestMapping(value={"SaveItemQuick"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView saveQuick(HttpServletRequest req)
  {
      String itemCode[]=req.getParameterValues("code");
      String itemName[]=req.getParameterValues("name");
      String basicPrice[]=req.getParameterValues("basicPrice");
      String lR[]=req.getParameterValues("lr");
      String eR[]=req.getParameterValues("er");
      String ca[]=req.getParameterValues("ca");
      String cb[]=req.getParameterValues("cb");
      String va[]=req.getParameterValues("va");
      String vb[]=req.getParameterValues("vb");
      String mc[]=req.getParameterValues("mc");
      String mca[]=req.getParameterValues("mca");
       String rol[]=req.getParameterValues("rol");
      
     System.out.println("size itemcode ----- >" +itemCode.length);
       
      for(int i=0;i<itemCode.length;i++)
      {
      System.out.println("checking itemcode ----- >" +itemCode[i]);
     
     
      ItemMaster iMaster = (ItemMaster)this.itemMasterService.getItmeByItemNo((itemCode[i]));
        double pp = Double.parseDouble(basicPrice[i]);
        double lr = Double.parseDouble(lR[i]);
        double er = Double.parseDouble(eR[i]);
        
       
        double tr = iMaster.getTr().doubleValue();
        double la = lr / 100.0D * pp;
        double lp = pp - la;
        double ea = er / 100.0D * lp;
        double ep = lp + ea;
        double ta = tr / 100.0D * ep;
        double tp = ep + ta;
         System.out.println("tax rate" + Double.parseDouble(ca[i]));
        double Ca=Double.parseDouble(ca[i]);
        double Cb=Double.parseDouble(cb[i]);
        double Va=Double.parseDouble(va[i]);
        double Vb=Double.parseDouble(vb[i]);
        
        double vap_noRound = ep * 100.0D / (100.0D - Va);
        
        String vapFixed=(new DecimalFormat("##.##").format(vap_noRound));
       
        Double vap=as.roundUpService(Double.parseDouble(vapFixed));
        
        double vbp_noRound = ep * 100.0D / (100.0D - Vb);
        
        String vbpFixed=(new DecimalFormat("##.##").format(vbp_noRound));
      
        Double vbp=as.roundUpService(Double.parseDouble(vbpFixed));
        
        
        double cap_noRound = (ep * 100.0D + ep * tr) / (100.0D - Ca);
      
        String capFixed=(new DecimalFormat("##.##").format(cap_noRound));
      
        Double cap=as.roundUpService(Double.parseDouble(capFixed));
    
        double cbp_noRound = (ep * 100.0D + ep * tr) / (100.0D -  Cb);
        
        String cbpFixed=(new DecimalFormat("##.##").format(cbp_noRound));
       
        Double cbp=as.roundUpService(Double.parseDouble(cbpFixed));
        double lpAlt = 0.0;
        double epAlt = 0.0;
        double tpAlt = 0.0;
        double vapAlt = 0.0;
        double vbpAlt = 0.0;
        double capAlt = 0.0;
        double cbpAlt = 0.0;
        
      
        
       if(!iMaster.getTotalUnit().isEmpty() && iMaster.getTotalUnit()!="0"){
       double whereAltUnit = Integer.parseInt(iMaster.getTotalUnit());
        
         lpAlt = lp / whereAltUnit;
         epAlt = ep / whereAltUnit;
         tpAlt = tp / whereAltUnit;
         vapAlt = vap / whereAltUnit;
         vbpAlt = vbp / whereAltUnit;
         capAlt = cap / whereAltUnit;
         cbpAlt = cbp / whereAltUnit;
        
      }
        
       
        
        iMaster.setBasicPrice(Double.valueOf(pp));
        iMaster.setLr(Double.valueOf(lr));
        iMaster.setEr(Double.valueOf(er));
        iMaster.setLa(Double.valueOf(la));
        iMaster.setLp(Double.valueOf(lp));
        iMaster.setTa(Double.valueOf(ta));
        iMaster.setTp(Double.valueOf(tp));
        iMaster.setEp(ep);
        iMaster.setVap(Double.valueOf(vap));
        iMaster.setVbp(Double.valueOf(vbp));
        iMaster.setCap(Double.valueOf(cap));
        iMaster.setCbp(Double.valueOf(cbp));
        
        iMaster.setLpAlt(Double.valueOf(lpAlt));
        iMaster.setEpAlt(Double.valueOf(epAlt));
        iMaster.setTpAlt(Double.valueOf(tpAlt));
        iMaster.setVapAlt(Double.valueOf(vapAlt));
        iMaster.setVbpAlt(Double.valueOf(vbpAlt));
        iMaster.setCapAlt(Double.valueOf(capAlt));
        iMaster.setCbpAtl(Double.valueOf(cbpAlt));
        
        iMaster.setVapCheckbox(Boolean.valueOf(false));
        iMaster.setVbpCheckbox(Boolean.valueOf(false));
        iMaster.setCapCheckbox(Boolean.valueOf(false));
        iMaster.setCbpCheckbox(Boolean.valueOf(false));
        iMaster.setItemName(itemName[i]);
        iMaster.setCa(Double.valueOf(ca[i]));
        iMaster.setCb(Double.valueOf(cb[i]));
        iMaster.setVa(Double.valueOf(va[i]));
        iMaster.setVb(Double.valueOf(vb[i]));
        iMaster.setRol(Double.valueOf(rol[i]));
         iMaster.setMc(Double.valueOf(mc[i]));
        iMaster.setMca(Double.valueOf(mca[i]));
        
       
        
        itemMasterService.saveItem(iMaster);
      
   
       
  }
      
    
   return new ModelAndView("redirect:ItemMasterQuickEdit.html");
  }
  
}
