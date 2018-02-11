/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.accounting.dao;
import com.accounting.bean.BuyerMaster;
import com.accounting.bean.UserMaster;
import javax.validation.ConstraintViolationException;
import org.hibernate.JDBCException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MR
 */

@Repository("signUpDao")
public class SignUpDao {
    
    @Autowired
  private SessionFactory sessionFactory;
 
     public int signUp(UserMaster user)
  {
    this.sessionFactory.getCurrentSession().saveOrUpdate(user);
    return user.getUserid();
  }
      public void buyermaster(String query1){
  try{
       sessionFactory.getCurrentSession().createSQLQuery(query1).executeUpdate();
    
              
              } catch(JDBCException e) {}
    }
      
      public void accountgroupmaster(String query2){
  try{
       sessionFactory.getCurrentSession().createSQLQuery(query2).executeUpdate();
   
              
              }catch(JDBCException e) {}
    }
        public void suppliermaster(String query3){
  try{
       sessionFactory.getCurrentSession().createSQLQuery(query3).executeUpdate();
    
              
              }catch(JDBCException e) {}
    }
        public void UnitMaster(String query4){
  try{
       sessionFactory.getCurrentSession().createSQLQuery(query4).executeUpdate();
    
              
              }catch(JDBCException e) {}
    }
        public void accesscontrol(String query5){
  try{
       sessionFactory.getCurrentSession().createSQLQuery(query5).executeUpdate();
    
              
              }catch(JDBCException e) {}
    }
        public void accesscontrolmastertable(String query6){
  try{
       sessionFactory.getCurrentSession().createSQLQuery(query6).executeUpdate();
    
              
              }catch(JDBCException e) {}
    }
     public void grouptable(String query7){
  try{
       sessionFactory.getCurrentSession().createSQLQuery(query7).executeUpdate();
    
              
              }catch(JDBCException e) {}
    }
     public void itcreversalmaster(String query8){
  try{
       sessionFactory.getCurrentSession().createSQLQuery(query8).executeUpdate();
    
              
              }catch(JDBCException e) {}
    }
     
     /////////////////////////////////////////////////////////////////////////////////////////////////////
     public void unit1(String query9){
  try{
       sessionFactory.getCurrentSession().createSQLQuery(query9).executeUpdate();
    
              
              }catch(JDBCException e) {}
    }
     public void unit2(String query10){
  try{
       sessionFactory.getCurrentSession().createSQLQuery(query10).executeUpdate();
    
              
              }catch(JDBCException e) {}
    }
     public void Hsn1(String query11){
  try{
       sessionFactory.getCurrentSession().createSQLQuery(query11).executeUpdate();
    
              
              }catch(JDBCException e) {}
    }
     public void Hsn2(String query12){
  try{
       sessionFactory.getCurrentSession().createSQLQuery(query12).executeUpdate();
    
              
              }catch(JDBCException e) {}
    }
     public void itemGroup(String query13){
  try{
       sessionFactory.getCurrentSession().createSQLQuery(query13).executeUpdate();
    
              
              }catch(JDBCException e) {}
    }
     public void itemaster1(String query14){
  try{
       sessionFactory.getCurrentSession().createSQLQuery(query14).executeUpdate();
    
              
              }catch(JDBCException e) {}
    }
     public void itemaster2(String query15){
  try{
       sessionFactory.getCurrentSession().createSQLQuery(query15).executeUpdate();
    
              
              }catch(JDBCException e) {}
    }
     public void itemaster3(String query16){
  try{
       sessionFactory.getCurrentSession().createSQLQuery(query16).executeUpdate();
    
              
              }catch(JDBCException e) {}
    }
     public void itemaster4(String query17){
  try{
       sessionFactory.getCurrentSession().createSQLQuery(query17).executeUpdate();
    
              
              }catch(JDBCException e) {}
    }
     public void itemaster5(String query18){
  try{
       sessionFactory.getCurrentSession().createSQLQuery(query18).executeUpdate();
    
              
              }catch(JDBCException e) {}
    }
     public void itemaster6(String query19){
  try{
       sessionFactory.getCurrentSession().createSQLQuery(query19).executeUpdate();
    
              
              }catch(JDBCException e) {}
    }
     public void itemaster7(String query20){
  try{
       sessionFactory.getCurrentSession().createSQLQuery(query20).executeUpdate();
    
              
              }catch(JDBCException e) {}
    }
     public void accountGroup(String query21){
  try{
       sessionFactory.getCurrentSession().createSQLQuery(query21).executeUpdate();
    
              
              }catch(JDBCException e) {}
    }
     public void Buyermaster1(String query22){
  try{
       sessionFactory.getCurrentSession().createSQLQuery(query22).executeUpdate();
    
              
              }catch(JDBCException e) {}
    }
     public void Buyermaster2(String query23){
  try{
       sessionFactory.getCurrentSession().createSQLQuery(query23).executeUpdate();
    
              
              }catch(JDBCException e) {}
    }
     public void Suppliermaster(String query24){
  try{
       sessionFactory.getCurrentSession().createSQLQuery(query24).executeUpdate();
    
              
              }catch(JDBCException e) {}
    }
     public void ItcReversal(String query25){
  try{
       sessionFactory.getCurrentSession().createSQLQuery(query25).executeUpdate();
    
              
              }catch(JDBCException e) {}
    }

         public void VPR(String query26){
  try{
       sessionFactory.getCurrentSession().createSQLQuery(query26).executeUpdate();
    
              
              }catch(JDBCException e) {}
    }
           public void VPR1(String query27){
  try{
       sessionFactory.getCurrentSession().createSQLQuery(query27).executeUpdate();
    
              
              }catch(JDBCException e) {}
    }
             public void CPR(String query28){
  try{
       sessionFactory.getCurrentSession().createSQLQuery(query28).executeUpdate();
    
              
              }catch(JDBCException e) {}
    }
               public void CPR1(String query29){
  try{
       sessionFactory.getCurrentSession().createSQLQuery(query29).executeUpdate();
    
              
              }catch(JDBCException e) {}
    }
                 public void JPR(String query30){
  try{
       sessionFactory.getCurrentSession().createSQLQuery(query30).executeUpdate();
    
              
              }catch(JDBCException e) {}
    }
                  public void JPR1(String query31){
  try{
       sessionFactory.getCurrentSession().createSQLQuery(query31).executeUpdate();
    
              
              }catch(JDBCException e) {}
    }
                   public void Entry(String query32){
  try{
       sessionFactory.getCurrentSession().createSQLQuery(query32).executeUpdate();
    
              
              }catch(JDBCException e) {}
    }
     
}
