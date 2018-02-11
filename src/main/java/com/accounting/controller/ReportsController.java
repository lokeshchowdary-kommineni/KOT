
package com.accounting.controller;

import com.accounting.bean.ItemReport;
import com.accounting.service.ItemGroupZReportService;
import com.accounting.service.ItemReport_service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.Request;

/**
 *
 * @author shinelogics
 */
@Controller
public class ReportsController {
    
     @Autowired
     private ItemReport_service reportService;     
    
    
    
    @RequestMapping(value={"GatewayForReports"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView itemReport()
  {
      
    ModelAndView model = new ModelAndView("GatewayForReports");  
    return model;
  }

}