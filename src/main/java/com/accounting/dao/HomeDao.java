/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.accounting.dao;

import com.accounting.bean.AccessControlMasterTable;
import com.accounting.bean.UserMaster;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MR
 */
@Repository("homeDao")
public class HomeDao {
    
  @Autowired
  private SessionFactory sessionFactory;
  
     
//    public boolean CheckDatabase(String email,String password) {
//            
//     List<UserMaster> list = null;
//
//        list= (List<UserMaster>) sessionFactory.getCurrentSession().createQuery("FROM UserMaster WHERE  Email='"+email+"'").list();
//               if ((list != null &&  list.size()>0 ) ) {
//                        Iterator itr=list.iterator();
//                        while(itr.hasNext()){
//                            UserMaster tr=(UserMaster)itr.next();
//                            String userPassword=tr.getPassword();
//                            int status =tr.getStatus();
//                                PasswordEncrypt_Decrypt dec= new PasswordEncrypt_Decrypt();
//                                String decryptedPassword=dec.decrypt(userPassword);
//                                 System.out.print("decryptedPassword:"+decryptedPassword);
//                                    if(decryptedPassword!=null && status==1){
//                                    if(decryptedPassword.equals(password))
//                                    {  
//                                    return true;
//                                    }
//                                    else{
//                                      return false;                       
//                                    }
//                    
//                                    }
//                        }
//                 }
//          return false;
//        }   

//    public boolean ChkStatus(int status) {
//            
//             List<UserMaster> list = null;
//            
//		list= (List<UserMaster>) sessionFactory.getCurrentSession().createQuery("SELECT Status FROM user_master WHERE  Status='"+status+"'").list();
//	if(list.size()>0)
//        {
//        return true;
//        }else
//        {
//          return false;
//        }     
//}
    
    public boolean ChkRole(String role) {
            
             List<UserMaster> list = null;
            
		list= (List<UserMaster>) sessionFactory.getCurrentSession().createQuery("SELECT role FROM UserMaster WHERE  role='"+role+"'").list();
	if(list.size()>0)
        {
        return true;
        }else
        {
          return false;
        }     
}
   
    
       public List<UserMaster> getDbEmail(String email) {
            List<UserMaster> list= sessionFactory.getCurrentSession().createQuery("FROM UserMaster WHERE email='"+email+"'").list();
                return list;
       }
       
       
       public List<AccessControlMasterTable> chkGroupId(String groupid ,String role) {
           
                List<AccessControlMasterTable> listgroup = new ArrayList();
                List<Object[]> list = null;
                try
                {
                    System.out.print("list b4 :"+list);
                  list = sessionFactory.getCurrentSession().createQuery("SELECT s.accessControlid,s.accessid,s.createvalue,s.deletevalue,s.editvalue,s.groupid,s.moduleName,s.viewvalue FROM AccessControlMasterTable s INNER JOIN  AccessControl a ON s.accessid=a.accessid WHERE s.groupid="+ Integer.parseInt(groupid) ).list();
                   System.out.print("list a4 :"+list);
                }
                catch (HibernateException e)
                {
                  e.printStackTrace();
                }
                
                System.out.print("list  Iterator:"+list);
                Iterator iterator = list.iterator();
                 System.out.print(" Iterator    :"+iterator);
                while (iterator.hasNext())
                {
                  AccessControlMasterTable acm = new AccessControlMasterTable();
                  Object[] objects = (Object[])iterator.next();
                  acm.setAccessControlid(((Integer)objects[0]));
//                  acm.setAccessid(objects[1].toString());
                  acm.setAccessid(((Integer)objects[1]));
                  acm.setCreatevalue(objects[2].toString());
                  acm.setDeletevalue(objects[3].toString());
                  acm.setEditvalue(objects[4].toString());
                  acm.setGroupid(((Integer)objects[5]));
                  acm.setModuleName(objects[6].toString());
                  acm.setViewvalue(objects[7].toString());
                  listgroup.add(acm);
                }
                
                return listgroup;
  }

}