/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.accounting.service;
import com.accounting.bean.BuyerMaster;
import com.accounting.bean.UserMaster;
import com.accounting.dao.SignUpDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MR
 */
@Service("signUpService")
@Transactional
public class SignUpService {
    
    @Autowired
  private SignUpDao signUpDao;
    
     @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
  public int addUserDerails(UserMaster user)
  {
    return signUpDao.signUp(user);
  }
  public void buyermaster(String query1){
    signUpDao.buyermaster(query1);
}
   public void accountgroupmaster(String query2){
    signUpDao.buyermaster(query2);
}
     public void suppliermaster(String query3){
    signUpDao.buyermaster(query3);
}
     
     public void UnitMaster(String query4){
    signUpDao.buyermaster(query4);
}
     
     public void accesscontrol(String query5){
    signUpDao.buyermaster(query5);
}
     
     public void accesscontrolmastertable(String query6){
    signUpDao.buyermaster(query6);
}
     
     public void grouptable(String query7){
    signUpDao.buyermaster(query7);
}
     
     public void itcreversalmaster(String query8){
    signUpDao.buyermaster(query8);
     
     }
    /////////////////////////////////////////////////////////////////////
     public void unit1(String query9){
    signUpDao.unit1(query9);
    }
     public void unit2(String query10){
    signUpDao.unit2(query10);
    }
     public void Hsn1(String query11){
    signUpDao.Hsn1(query11);
    }
     public void Hsn2(String query12){
    signUpDao.Hsn2(query12);
    }
     public void itemGroup(String query13){
    signUpDao.itemGroup(query13);
    }
     public void itemaster1(String query14){
    signUpDao.itemaster1(query14);
    }
     public void itemaster2(String query15){
    signUpDao.itemaster2(query15);
    }
     public void itemaster3(String query16){
    signUpDao.itemaster3(query16);
    }
     public void itemaster4(String query17){
    signUpDao.itemaster4(query17);
    }
     public void itemaster5(String query18){
    signUpDao.itemaster5(query18);
    }
     public void itemaster6(String query19){
    signUpDao.itemaster6(query19);
    }
     public void itemaster7(String query20){
    signUpDao.itemaster7(query20);
    }
     public void accountGroup(String query21){
    signUpDao.accountGroup(query21);
    }
     public void Buyermaster1(String query22){
    signUpDao.Buyermaster1(query22);
    }
     public void Buyermaster2(String query23){
    signUpDao.Buyermaster2(query23);
    }
     public void Suppliermaster(String query24){
    signUpDao.Suppliermaster(query24);
    }
     public void ItcReversal(String query25){
    signUpDao.ItcReversal(query25);
    }
      public void VPR(String query26){
    signUpDao.VPR(query26);
    }
       public void VPR1(String query27){
    signUpDao.VPR1(query27);
    }
        public void CPR(String query28){
    signUpDao.CPR(query28);
    }
         public void CPR1(String query29){
    signUpDao.CPR1(query29);
    }
          public void JPR(String query30){
    signUpDao.JPR(query30);
    }
           public void JPR1(String query31){
    signUpDao.JPR1(query31);
    }
     public void Entry(String query32){
    signUpDao.Entry(query32);
    }
     
}
