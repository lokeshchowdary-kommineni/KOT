package com.accounting.service;

import com.accounting.dao.AccountingDBO;
import java.io.IOException;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("AccountDBOService")
@Transactional
public class AccountDBOService 
{
  @Autowired
  private AccountingDBO dbo;
  
  public String GetYearRange()
  {
    return dbo.getFinacialYearRange();
  }
   public long getPreviousIdBasedStartAndEndYear(String Column,String Table,String yearRange){
       
   return  dbo.getPreviousIdBasedStartAndEndYear(Column,Table,yearRange);
   }
    public double calculateNetProfit_Every_transcation(String gsFromDate,String gsToDate)
    {
        return dbo.calculateNetProfit_Every_transcation(gsFromDate,gsToDate);
    }
    public double roundUpService(double toRound)
    {
       return dbo.RoundUp(toRound);
    }
   
      public boolean CheckValidLicens(String propPath) throws IOException, ParseException
      {
          return dbo.CheckValidLicens(propPath); 
      }
       public  boolean netIsAvailable() {
           return dbo.netIsAvailable(); 
       }
}
