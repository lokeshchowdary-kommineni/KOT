package com.accounting.service;


import com.accounting.dao.ItemGroupReportDao;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("ItemGroupZReportService")
@Transactional
public class ItemGroupZReportService
{
  @Autowired
  private ItemGroupReportDao igrd;
  
 
  
  public String returnChildids(String parentid)
  {
    return igrd.listChildCategoryIds(parentid);
  }
  public String returnChildItemids(String parentid)
  {
    return igrd.listChildItemCategoryIds(parentid);
  }
  public List<Object[]> getGroupOprningAndClosingBalance(String groupids, String Date)
  {
      return igrd.getGroupOpeningAndClosingBalance(groupids, Date);
  }
   public List<Object[]> getGroupOprningAndClosingBalanceEnddate(String groupids, String Date)
  {
      return igrd.getGroupOpeningAndClosingBalanceEnddate(groupids, Date);
  }
   public List<Object[]> getGroupOprningAndClosingBalanceByGroupByEach(String groupids, String Date)
  {
      return igrd.getGroupOpeningAndClosingBalanceByGroupBy(groupids, Date);
  }
  public List<Object[]> getGroupOprningAndClosingBalanceByGroupByEachEnddate(String groupids, String Date)
  {
      return igrd.getGroupOpeningAndClosingBalanceByGroupByEnddate(groupids, Date);
  } 
   
    public List<Object[]> getItemOprningAndClosingBalance(String itemid, String Date)
  {
      return igrd.getItemOpeningAndClosingBalance(itemid, Date);
  }
    public List<Object[]> getSingleItemOpenigBalance(String itemid)
  {
      return igrd.getSingleItemOpenigBalance(itemid);
  }  
   public List<Object[]> getAllItemOpeningBalanceFromItemMaster(String FromDate,String groupids)
  {
      return igrd.getAllItemOpeningBalanceFromItemMaster(FromDate,groupids);
  }    
  public Set<String> setChildCategoryIds(String id ) {
     return igrd.setChildCategoryIds(id);
  }
  // for profit and loss
   public List<Object[]> getALLGroupOpeningAndClosingBalanceByGroupBy(String Date)
  {
      return igrd.getALLGroupOpeningAndClosingBalanceByGroupBy( Date);
  }
     public List<Object[]> getAllItemOpeningBalanceFromItemMaster_Profit_loss_every_Transcation(String FromDate)
  {
      return igrd.getAllItemOpeningBalanceFromItemMaster_Profit_loss_every_Transcation(FromDate);
  }
   public List<Object[]> getAllItemOpeningBalanceFromItemMaster_Profit_loss(String FromDate)
  {
      return igrd.getAllItemOpeningBalanceFromItemMaster_Profit_loss(FromDate);
  }   
  public List<Object[]> getAllGroupOpeningAndClosingBalanceByGroupByEnddate(String Fdate,String Date)
  {
      return igrd.getAllGroupOpeningAndClosingBalanceByGroupByEnddate(Fdate, Date);
  }
  
    public List<Object[]> getAllStockJournalsOutValueFromtoEndDate(String fDate,String endDAte)
  {
      return igrd.getAllStockJournalsOutValueFromDate_endDate(fDate,endDAte);
  }
    
  public List<Object[]> getALLGroupOpeningBalanceByGroupBy_everySales_purchase(String fDate)
  {
      return igrd.getALLGroupOpeningBalanceByGroupBy_everySales_purchase(fDate);
  }  
  
   public List<Object[]> getAllStockSummartBasedOnPRIMARYGroup(String MainGroupID,String endDate)
  {
      return igrd.getAllStockSummartBasedOnPRIMARYGroup(MainGroupID,endDate);
  } 
    public List<Object[]> getGroupWiseOpeningBalance(String MainGroupID,String endDate)
  {
      return igrd.getGroupWiseOpeningBalance(MainGroupID,endDate);
  } 
}
  