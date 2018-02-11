/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.controller;

import com.accounting.bean.DatatableObject;
import com.accounting.bean.ItemMaster;
import com.accounting.bean.KOTForm;
import com.accounting.bean.Kot;
import com.accounting.bean.KotItem;
import com.accounting.bean.TempKot;
import com.accounting.bean.Tablemaster;
import com.accounting.bean.Waitermaster;
import com.accounting.service.DefaultSetting_service;
import com.accounting.service.ItemMasterService;
import com.accounting.service.KOTService;
import com.accounting.util.MySqlBackup;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
/**
 *
 * @author MR
 */
@Controller
public class KOTController {
    
    
    
    @Autowired
    private KOTService kotservice;
    
    @Autowired
    private ItemMasterService itemMasterService;
    
    @Autowired
    private DefaultSetting_service des;
    
    @RequestMapping(value="KotForm",method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView KotForm(HttpSession session){
        
        Kot kot=new Kot();
        KotItem kotItem=new KotItem();
        KOTForm kotForm=new KOTForm();
        List<KotItem> list=new ArrayList<KotItem>();
        list.add(kotItem);
        kotForm.setKotItem(list);
        kotForm.setKot(kot);
    
        ModelAndView model=new ModelAndView("KOTForm");
        model.addObject("tableCategory",kotservice.listTableCategory());
        model.addObject("waiterId",kotservice.listWaiterId());
        model.addObject("itemCodes",kotservice.itemCodes());
        model.addObject("kotForm",kotForm);
        return model;
        
    }
    
    @RequestMapping(value="KOTList",method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView KotListForm(HttpSession session){
        ModelAndView model=new ModelAndView("KOTList");
        return model;
        
    }
    
    @RequestMapping(value="KOT_AddRow",method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public ModelAndView KotFormAddRow(@ModelAttribute("kotForm")  KOTForm kotForm, HttpServletRequest req){
        
        KotItem kotItem_plus=new KotItem();
        KotItem kotItem=new KotItem();
        KOTForm kForm=kotForm;
        List<KotItem> list=kForm.getKotItem();
        List<KotItem> listNew=new ArrayList<KotItem>();
        Iterator<KotItem> i = list.iterator();
        while (i.hasNext())
        {
            kotItem = i.next();
            // this condition exclude deleted empty row of data
            if(kotItem.getItemCode()=="" || kotItem.getItemCode()==null)
            {

            }
            else
            {
              // here add existing data to new arraylist 
                listNew.add(kotItem);
            }    
        }
         
        listNew.add(kotItem_plus);
        kForm.setKotItem(listNew);
        ModelAndView model=new ModelAndView("KOT_AddRow");
        model.addObject("tableCategory",kotservice.listTableCategory());
        model.addObject("waiterId",kotservice.listWaiterId());
        model.addObject("itemCodes",kotservice.itemCodes());
        model.addObject("kotForm",kForm);
        return model;
        
    }
    
    @RequestMapping(value="EditKOTFromKOTList_AddRow",method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public ModelAndView EditKOTFromKOTListAddRow(@ModelAttribute("kotForm")  KOTForm kotForm, HttpServletRequest req){
        
        KotItem kotItem_plus=new KotItem();
        KotItem kotItem=new KotItem();
        KOTForm kForm=kotForm;
        List<KotItem> list=kForm.getKotItem();
        List<KotItem> listNew=new ArrayList<KotItem>();
        Iterator<KotItem> i = list.iterator();
        while (i.hasNext())
        {
            kotItem = i.next();
            // this condition exclude deleted empty row of data
            if(kotItem.getItemCode()=="" || kotItem.getItemCode()==null)
            {

            }
            else
            {
              // here add existing data to new arraylist 
                listNew.add(kotItem);
            }    
        }
         
        listNew.add(kotItem_plus);
        kForm.setKotItem(listNew);
        ModelAndView model=new ModelAndView("EditKOTFromKOTList_AddRow");
        model.addObject("tableCategory",kotservice.listTableCategory());
        model.addObject("waiterId",kotservice.listWaiterId());
        model.addObject("itemCodes",kotservice.itemCodes());
        model.addObject("kotForm",kForm);
        return model;
        
    }
    
    @RequestMapping(value="EmptyKOTForm",method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public ModelAndView emptyKotForm(HttpSession session){
        Kot kot=new Kot();
        KotItem kotItem=new KotItem();
        KOTForm kotForm=new KOTForm();
        List<KotItem> list=new ArrayList<KotItem>();
        list.add(kotItem);
        kotForm.setKotItem(list);
        kotForm.setKot(kot);
    
        ModelAndView model=new ModelAndView("EmptyKOTForm");
        model.addObject("tableCategory",kotservice.listTableCategory());
        model.addObject("waiterId",kotservice.listWaiterId());
        model.addObject("itemCodes",kotservice.itemCodes());
        model.addObject("kotForm",kotForm);
        return model;
    }
    
    @RequestMapping(value="AjaxTableList",method = RequestMethod.GET)
    public ModelAndView getAjaxTableList(HttpServletRequest req, HttpSession session) {
            
        String category=req.getParameter("category");
        ModelAndView model=new ModelAndView("AjaxTableList");
        model.addObject("orderKOTtableList",kotservice.listOrderKOTTables(category));
        model.addObject("tableList",kotservice.listTablesByCategory(category));
        return model;
    }
    
    @RequestMapping(value="AjaxKOTList",method = RequestMethod.GET)
    public ModelAndView getAjaxKOTList(HttpServletRequest req, HttpSession session) {
            
        String tableName=req.getParameter("tableName");
        ModelAndView model=new ModelAndView("AjaxKOTList");
        List<TempKot> orderKOT=kotservice.listOrderKot(tableName);
        return model.addObject("kotList",orderKOT);
    
    }
    
    @RequestMapping(value="CheckRecordByTable",method = RequestMethod.GET)
    @ResponseBody public int CheckRecordByTable(HttpServletRequest req, HttpSession session) {
            
        String tableName=req.getParameter("tableName");
        int status=0;
        List<TempKot> orderKOT=kotservice.listOrderKot(tableName);
        if(!orderKOT.isEmpty()){
            status=1;
        }
        else{
            status=0;
        }
        System.out.println("status is----------"+status);
        return status;
    
    }
    
    @RequestMapping(value={"GetWaiterCode"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody public List<Waitermaster> getWaiterIdForKOT(@RequestParam("term") String waiterName)
    {       
        return kotservice.getWitersListByName(waiterName);
     
    }
    
    @RequestMapping(value={"DeleteItemById"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody public String deleteItemById(@RequestParam("itemId") String itemId)
    {       
        return kotservice.deleteItemById(itemId);
     
    }
    
    @RequestMapping(value={"DeleteItemByIdfromKOTList"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody public String deleteItemByIdfromKOTList(@RequestParam("itemId") String itemId)
    {       
        return kotservice.deleteItemByIdfromKOTList(itemId);
     
    }
    
    @RequestMapping(value={"GetTableNames"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody public List<Tablemaster> getTableForKOT(@RequestParam("term") String tableName)
    {       
        return kotservice.getTableListByName(tableName);
     
    }
    
    @RequestMapping(value={"GetWaiterNameById"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody public String getWaiterNameById(@RequestParam("waiterId") String waiterId)
    {       
        return kotservice.getWaiterNameById(waiterId);
     
    }
    
    @RequestMapping(value={"AjaxWaiterName"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody public String getAjaxWaiterName(@RequestParam("tableName") String tableName)
    {       
        return kotservice.getWaiterNameByTableName(tableName);
     
    }
    
    @RequestMapping(value={"GetItemCodeForKOT"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody public List<ItemMaster> getItemCodeForKOT(@RequestParam("term") String key, @RequestParam("tax") String tax,@RequestParam("items") String items)
    {       
        if(items.length()>0){
                return itemMasterService.getItemListByKeyWithTax(key, tax,items);
            }
            else{
              return itemMasterService.getItemListByKey(key);
            }
    }

    @RequestMapping(value="saveKOT",method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String saveKOT(@ModelAttribute("kotForm")@Validated KOTForm kotForm, BindingResult result){
        
        List<KotItem> kotItemList=kotForm.getKotItem();
        
        Kot kot=kotForm.getKot();
        KotItem kotItem=new KotItem();
        TempKot orderKot=new TempKot();
        Date date=new Date();
        kot.setKotTimestamp(new Timestamp(System.currentTimeMillis()));
        String kotNo=kotservice.saveKOT(kot);
        Iterator itr=kotItemList.iterator();
        while(itr.hasNext()){
            KotItem kItem=(KotItem)itr.next();
            if(kItem.getItemCode()!=null){
                
                kotItem.setId(kItem.getId());
                kotItem.setKotNo(kotNo);
                kotItem.setKotid(kot.getId());
                kotItem.setAuditRate(kItem.getAuditRate());
                kotItem.setCap(kItem.getCap());
                kotItem.setCgstPercent(kItem.getCgstPercent());
                kotItem.setItemCode(kItem.getItemCode());
                kotItem.setItemName(kItem.getItemName());
                kotItem.setQuantity(kItem.getQuantity());
                kotItem.setRate(kItem.getRate());
                kotItem.setSgstPercent(kItem.getSgstPercent());
                kotItem.setTaxCgst(kItem.getTaxCgst());
                kotItem.setTaxSgst(kItem.getTaxSgst());
                kotItem.setUnit(kItem.getUnit());
                kotItem.setVap(kItem.getVap());


                kotservice.saveKOTItem(kotItem);
                
                orderKot.setId(kItem.getId());
                orderKot.setKotNo(kotNo);
                orderKot.setAuditRate(kItem.getAuditRate());
                orderKot.setCap(kItem.getCap());
                orderKot.setItemCode(kItem.getItemCode());
                orderKot.setItemName(kItem.getItemName());
                orderKot.setQuantity(kItem.getQuantity());
                orderKot.setKotid(kot.getId());
                orderKot.setRate(kItem.getRate());
                orderKot.setTaxCgst(kItem.getTaxCgst());
                orderKot.setTaxSgst(kItem.getTaxSgst());
                orderKot.setUnit(kItem.getUnit());
                orderKot.setVap(kItem.getVap());
                orderKot.setKotTimestamp(new Timestamp(System.currentTimeMillis()));
                orderKot.setTableName(kot.getTableName());
                orderKot.setWaiterId(kot.getWaiterId());
                kotservice.saveOrderKOT(orderKot);
            }
                       
        }
        return "redirect:/KotForm.html";
    }
    
    @RequestMapping(value="saveKOTFromKOTList",method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String saveKOTFromKOTList(@ModelAttribute("kotForm")@Validated KOTForm kotForm, BindingResult result){
        
        List<KotItem> kotItemList=kotForm.getKotItem();
        
        Kot kot=kotForm.getKot();
        KotItem kotItem=new KotItem();
        TempKot orderKot=new TempKot();
        Date date=new Date();
        kot.setKotTimestamp(new Timestamp(System.currentTimeMillis()));
        String kotNo=kotservice.saveKOT(kot);
        Iterator itr=kotItemList.iterator();
        while(itr.hasNext()){
            KotItem kItem=(KotItem)itr.next();
            if(kItem.getItemCode()!=null){
                
                kotItem.setId(kItem.getId());
                kotItem.setKotNo(kotNo);
                kotItem.setKotid(kot.getId());
                kotItem.setAuditRate(kItem.getAuditRate());
                kotItem.setCap(kItem.getCap());
                kotItem.setCgstPercent(kItem.getCgstPercent());
                kotItem.setItemCode(kItem.getItemCode());
                kotItem.setItemName(kItem.getItemName());
                kotItem.setQuantity(kItem.getQuantity());
                kotItem.setRate(kItem.getRate());
                kotItem.setSgstPercent(kItem.getSgstPercent());
                kotItem.setTaxCgst(kItem.getTaxCgst());
                kotItem.setTaxSgst(kItem.getTaxSgst());
                kotItem.setUnit(kItem.getUnit());
                kotItem.setVap(kItem.getVap());


                kotservice.saveKOTItem(kotItem);
                
            }
                       
        }
        return "redirect:/KOTList.html";
    }
    
    
    @RequestMapping(value="EditKOT", method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView editRunningKot(@ModelAttribute("kotForm")@Validated KOTForm kotForm, BindingResult result,HttpServletRequest req)
    {
        String kotId=req.getParameter("kotId");
        ModelAndView model = new ModelAndView("EditKOT");
        List<KotItem> kotItemList=kotservice.listkotItem(kotId);
        List<Kot> kotList=kotservice.listkot(kotId);
        Kot kotObj=new Kot();
        Iterator itr=kotList.iterator();
        while(itr.hasNext()){
            Kot kot=(Kot)itr.next();
            kotObj.setId(kot.getId());
            kotObj.setKotNo(kot.getKotNo());
            kotObj.setTableName(kot.getTableName());
            kotObj.setWaiterId(kot.getWaiterId());
            kotObj.setKotTimestamp(kot.getKotTimestamp());
            kotObj.setTotalCgst(kot.getTotalCgst());
            kotObj.setTotalSgst(kot.getTotalSgst());
            kotObj.setTotalKotvalue(kot.getTotalKotvalue());
        }
        KOTForm kForm=new KOTForm();
        kForm.setKotItem(kotItemList);
        kForm.setKot(kotObj);
        model.addObject("kotForm",kForm);
        model.addObject("tableCategory",kotservice.listTableCategory());
        model.addObject("waiterId",kotservice.listWaiterId());
        return model;
    }
    
    @RequestMapping(value="EditKOTFromList", method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView editKotFromList(@ModelAttribute("kotForm")@Validated KOTForm kotForm, BindingResult result,HttpServletRequest req,final RedirectAttributes redirectAttributes)
    {
        String kotId=req.getParameter("kotId");
        String tableName=req.getParameter("tableName");
        List<TempKot> orderKOT=kotservice.listTempKotById(kotId);
        if(orderKOT.isEmpty()){
            System.out.println("list is empty");
            ModelAndView model = new ModelAndView("EditKOTFromKOTList");
            List<KotItem> kotItemList=kotservice.listkotItem(kotId);
            List<Kot> kotList=kotservice.listkot(kotId);
            Kot kotObj=new Kot();
            Iterator itr=kotList.iterator();
            while(itr.hasNext()){
                Kot kot=(Kot)itr.next();
                kotObj.setId(kot.getId());
                kotObj.setKotNo(kot.getKotNo());
                kotObj.setTableName(kot.getTableName());
                kotObj.setWaiterId(kot.getWaiterId());
                kotObj.setKotTimestamp(kot.getKotTimestamp());
                kotObj.setTotalCgst(kot.getTotalCgst());
                kotObj.setTotalSgst(kot.getTotalSgst());
                kotObj.setTotalKotvalue(kot.getTotalKotvalue());
            }
            KOTForm kForm=new KOTForm();
            kForm.setKotItem(kotItemList);
            kForm.setKot(kotObj);
            model.addObject("kotForm",kForm);
            model.addObject("tableCategory",kotservice.listTableCategory());
            model.addObject("waiterId",kotservice.listWaiterId());
            return model;
        }
        else{
            redirectAttributes.addFlashAttribute("message","It is running KOT. You Can Edit here by selecting Table.");
            ModelAndView model = new ModelAndView("redirect:/KotForm.html");
            return model;
        }
        
    }
    
    @RequestMapping(value={"GetKOTListGrid"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public DatatableObject getsDatatable(HttpServletRequest req,RedirectAttributes redirect)
    {
    DatatableObject db =new DatatableObject();
       
    String[] cols = {"k.kot_no", "table_name", "waiter_id" ,"kot_timestamp","cap"};
    
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
        String sql = "SELECT k.kot_no,k.table_name,wm.waiter_name,DATE_FORMAT(k.kot_timestamp,'%d-%m-%Y')AS TIMESTAMP,SUM(ki.cap) AS cap,'',k.id FROM kot k INNER JOIN kot_item ki ON k.id=ki.kotid INNER JOIN waitermaster wm ON k.waiter_id=wm.waiter_id GROUP BY k.id";
        List<Object[]> list = kotservice.GetDatatableKOTList(sql);
        total=list.size();
        
    }catch(Exception e){                        
         
    }
    int totalAfterFilter = total;
    //result.put("sEcho",echo);
 
    try {
        String searchSQL = "";
        String sql = "SELECT k.kot_no,k.table_name,wm.waiter_name,DATE_FORMAT(k.kot_timestamp,'%d-%m-%Y')AS TIMESTAMP,SUM(ki.cap) AS cap,'',k.id FROM kot k INNER JOIN kot_item ki ON k.id=ki.kotid INNER JOIN waitermaster wm ON k.waiter_id=wm.waiter_id";
        String searchTerm =req.getParameter("search[value]");
        String globeSearch =  " where (k.kot_no like '"+searchTerm+"%') GROUP BY k.id";
    
        if(searchTerm!=""){
            searchSQL=globeSearch;                             
        }
        else{
            searchSQL=" GROUP BY k.id"; 
        }
        sql += searchSQL;
        sql += " order by " + colName + " " + dir;
        sql += " limit " + start + ", " + amount;
        // For aData
         List<Object[]> list2 = kotservice.GetDatatableKOTList(sql);
         // For Filter Count 
        
        String sql2 = "SELECT k.kot_no,k.table_name,wm.waiter_name,DATE_FORMAT(k.kot_timestamp,'%d-%m-%Y')AS TIMESTAMP,SUM(ki.cap) AS cap,'',k.id FROM kot k INNER JOIN kot_item ki ON k.id=ki.kotid INNER JOIN waitermaster wm ON k.waiter_id=wm.waiter_id";
       if (searchTerm != "") {
            sql2 += searchSQL;
          List<Object[]> count = kotservice.GetDatatableKOTList(sql2);
          totalAfterFilter=count.size();
        } 
        


        db.setiTotalRecords(total);
        db.setiTotalDisplayRecords(totalAfterFilter);
        db.setAaData(list2);
        } catch (Exception e) {
 
    }
      return db;
} 
    
      @RequestMapping(value={"DBImportExport"})
    public ModelAndView DBImportExport(){
        ModelAndView model = new ModelAndView("DBImportExport");
        return model;
    }
    
    @RequestMapping(value={"BackupDatabase"},method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public void backupDatabase(HttpServletRequest req,HttpServletResponse response) throws FileNotFoundException, IOException{
          
       DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy");
       Date date = new Date();
       String currentDate=dateFormat.format(date);
       
//       System.out.println("current date " +currentDate);
        String filename = "kotdb.sql";
        String currentPath = req.getRealPath("")+"\\";
        String backupFile= currentPath +filename;
//                System.out.println("backupFile :"+backupFile);
                
        MySqlBackup b = new MySqlBackup();
        b.backUpDB(backupFile);
        response.setContentType("text/html");  
        PrintWriter out = response.getWriter(); 
        response.setContentType("APPLICATION/OCTET-STREAM");   
        response.setHeader("Content-Disposition","attachment; filename=\""  + currentDate + filename+ "\"");   
      
        FileInputStream fileInputStream = new FileInputStream(backupFile);  
    
        int i;   
        while ((i=fileInputStream.read()) != -1) {  
        out.write(i);   
        }   
        fileInputStream.close();   
        out.close();   
   
        File file = new File(backupFile);

        file.delete();
       
    }
    
    @RequestMapping(value={"RestoreDatabase"},method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public ModelAndView RestoreDatabase(HttpServletRequest req,HttpServletResponse response,@RequestParam(value = "file") MultipartFile file) throws IOException, URISyntaxException{
        
//       String file=req.getParameter("import");
//       System.out.println("path is :"+file);
       
        String outfile = "";
        String extension = "";
        String FileNAme = "";
        
//        response.setContentType("text/html");  
//        PrintWriter out = response.getWriter(); 
        byte[] bytes = file.getBytes();
        String rootPath = req.getServletContext().getRealPath("/");
        File dir = new File(rootPath + File.separator + "Uploads");
        if (!dir.exists()) {
          dir.mkdirs();
        }
//        outfile = file;
        extension = FilenameUtils.getExtension(outfile);
        FileNAme = extension;
        File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
        
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
        stream.write(bytes);
        stream.close();
      
//        System.out.println("serverFile :"+serverFile);
//        String currentPath = req.getRealPath("")+"\\";
//        System.out.println("currentPath :"+currentPath);
        String backupFile= "" +serverFile;
//        System.out.println("backupFile :"+backupFile);
        
        MySqlBackup m=new MySqlBackup();
        m.restoreDB(backupFile);
        serverFile.delete();
        ModelAndView model = new ModelAndView("redirect:/DBImportExport.html");
        return model;
    }
    
}
