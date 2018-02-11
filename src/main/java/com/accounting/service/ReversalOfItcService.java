
package com.accounting.service;


import com.accounting.bean.Purchase;
import com.accounting.bean.ReversalOfItc;
import com.accounting.bean.ReversalOfItcItem;
import com.accounting.dao.PurchseDao;
import com.accounting.dao.ReversalOfItcDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service("reversalOfItcservice")
@Transactional
public class ReversalOfItcService  {

    
    @Autowired
    private ReversalOfItcDao reversalOfItcDao;
    

    public String saveReversalOfItc(ReversalOfItc rev){
        return reversalOfItcDao.saveReversalOfItc(rev);
    }
    
   
    public int saveReversalOfItcItem(ReversalOfItcItem rev){
        return reversalOfItcDao.saveReversalOfItcItem(rev);

    }

 
    public List<ReversalOfItc> listReversalOfItc() {
        return reversalOfItcDao.listReversalOfItc();
    }
    
    public ReversalOfItc getReversalOfItcById(String id){
        return reversalOfItcDao.getReversalOfItcById(id);
    }
    
    public List<ReversalOfItcItem> getReversalOfItcItemByReversalOfItcId(String id){
       return reversalOfItcDao.getReversalOfItcItemByReversalOfItcId(id);
 
    }
    
   
    public void deleteReversalOfItc(String reversalId) {
        reversalOfItcDao.deleteReversalOfItc(reversalId);

    }
//Return Qty change 
     
     public double  getReversalReturnId(String code,String purchaseinvoiceNumner) {
            return reversalOfItcDao.getReversalReturnId(code,purchaseinvoiceNumner);
        }
    public List<Object[]> GetDatatableObject(String query){
    return reversalOfItcDao.GetDatatableObject(query);
}
public List<Object[]> GetDatatableCount(String query){
    return reversalOfItcDao.GetDatatableCount(query);
}
 
  public List<ReversalOfItc> ReversalOfItcReports(String gsQuery)
  {
    return reversalOfItcDao.ReversalOfItcReports(gsQuery);
  }


}

