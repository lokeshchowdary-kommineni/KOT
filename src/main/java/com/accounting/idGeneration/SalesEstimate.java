/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.idGeneration;

import java.io.Serializable;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author SHINELOGICS
 */
public class SalesEstimate implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor ssci, Object o) throws HibernateException {
      Connection connection = ssci.connection();
        String Id = "";
        HttpSession session1 = null;
        
        ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attr.getRequest();
        session1 = request.getSession(false);
        String primaryIdPE = (String) session1.getAttribute("primaryIdPE");
        session1.removeAttribute("primaryIdPE");
     return primaryIdPE;
    }
    
}
