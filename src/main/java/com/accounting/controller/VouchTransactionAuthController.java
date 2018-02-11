//  
//package com.accounting.controller;
//
//   
//import com.accounting.bean.DatatableObject;
//import com.accounting.bean.Entries;
//import com.accounting.bean.Entryitems;
//import com.accounting.bean.Purchase;
//import com.accounting.bean.ReversalOfItc;
//import com.accounting.bean.Sales;
//import com.accounting.bean.SalesItem;
//import com.accounting.bean.SalesReturn;
//import com.accounting.bean.SalesReturnItem;
//import com.accounting.service.CcodeService;
//import com.accounting.service.EntryService;
//import com.accounting.service.ItemReport_service;
//import com.accounting.service.LedgerAccount_Service;
//import com.accounting.service.LedgerBalanceService;
//import com.accounting.service.PurchaseService;
//import com.accounting.service.ReversalOfItcService;
//import com.accounting.service.SalesReturnService;
//import com.accounting.service.SalesService;
//import com.accounting.service.VouchTransactionAuthService;
//import com.accounting.service.VoucherBankClearanceService;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Date;
//import java.util.Iterator;
//import java.util.List;
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.ModelAndView;
//
///**
// *
// * @author MR
// */
//@RestController
//public class VouchTransactionAuthController {
//    @Autowired
//    private VouchTransactionAuthService vouchTransactionAuthService;
//    
//    @Autowired
//    private  VoucherBankClearanceService voucherBankClearanceService;
//    
//    @Autowired
//    private LedgerAccount_Service ledgerAccountService;
//    
//    @Autowired
//    private ItemReport_service itemReportService;
//    
//    @Autowired
//    private CcodeService cService;
//    
//    @Autowired
//    private LedgerBalanceService lbs;
//    
//    @Autowired
//    private SalesService salesService;
//    
//     @Autowired
//    private EntryService entryService;
//     
//     @Autowired
//    private SalesReturnService salesReturnService;
//     
//      @Autowired
//  private PurchaseService purchaseService;
//      
//      @Autowired
//    private ReversalOfItcService reversalOfItcService;
//    
//    @RequestMapping(value={"VoucherTransactionAuth"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
//    public ModelAndView voucherContra()
//    {
//        
//      List<Object[]> AllList=new ArrayList<Object[]>();
//      
//      List<Object[]> s=vouchTransactionAuthService.getAuthTransactionsSales();
//          
//      List<Object[]> sr=vouchTransactionAuthService.getAuthTransactionsSalesReturn();
//      
//      List<Object[]> p=vouchTransactionAuthService.getAuthTransactionsPurchase();
//       
//      List<Object[]> pr=vouchTransactionAuthService.getAuthTransactionsPurchaseReturn();
//      
//      s.addAll(sr);
//      s.addAll(p);
//      s.addAll(pr); 
//      
//      ModelAndView model = new ModelAndView("VoucherTransactionAuth");
//      model.addObject("AllList",s);
//      return model;
//    
//    }
//    
//    @RequestMapping(value={"authoriseTransactionSales"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
//    public ModelAndView voucherAuthoriseTransaction(HttpServletRequest req)
//    {
//      String v_id=req.getParameter("id");   
//        // SINGLE AUTHORISATION Sales,Purchase
//            if(v_id != null){
//              vouchTransactionAuthService.updateSalesById(String.valueOf(req.getParameter("id")));
//              //vouchTransactionAuthService.updateAuthoriseTransactionByIdS(String.valueOf(req.getParameter("id")));
//       
//       Sales list=salesService.getSalesById(v_id);
//       Integer entry1=entryService.getEntryListId(v_id,"Sales").getId();
//       SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
//       Date date = new Date();        
//       String CurrentDate= dmyFormat.format(date);
//       
//       int cashBuyer=6;       
//       List<Object[]> cashBuyer_currentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(cashBuyer), CurrentDate);
//      
//       double openingBalanceOfCashBuyer=0;
//       if( cashBuyer_currentBalance!=null && cashBuyer_currentBalance.size()>0)
//       {
//             for (Object[] column : cashBuyer_currentBalance) {
//                        openingBalanceOfCashBuyer = (Double)column[0]- (Double)column[1];
//                }
//           
//       }
//       else
//       {
//           List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(cashBuyer));
//           for (Object[] column : OpeningBalance) {
//                        openingBalanceOfCashBuyer = (Double)column[0]- (Double)column[1];
//                }
//       }   
//       Entryitems eItem0=new Entryitems();
//       Double currentCashBuyerBalance=openingBalanceOfCashBuyer-list.getInvoiceAmount();
//       eItem0.setEntryId(entry1);
//       eItem0.setAmount(list.getInvoiceAmount());
//       eItem0.setLedgerId(cashBuyer);
//       eItem0.setType("C");
//       if(currentCashBuyerBalance>=0)
//       eItem0.setClosingAmtDr(currentCashBuyerBalance);
//       else
//       eItem0.setClosingAmtCr( Math.abs(currentCashBuyerBalance));   
//       entryService.saveEntryItem(eItem0);     
//          
//       // Cash Ledger Entries    
//       int cashLedger=1;       
//       List<Object[]> currentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(cashLedger), CurrentDate);
//      
//       double openingBalanceOfCash=0;
//       if( currentBalance!=null && currentBalance.size()>0)
//       {
//             for (Object[] column : currentBalance) {
//                        openingBalanceOfCash = (Double)column[0]- (Double)column[1];
//                }
//           
//       }
//       else
//       {
//           List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(cashLedger));
//           for (Object[] column : OpeningBalance) {
//                        openingBalanceOfCash = (Double)column[0]- (Double)column[1];
//                }
//       }   
//       Entryitems eItem=new Entryitems();
//       Double currentCashBalance=openingBalanceOfCash+list.getInvoiceAmount();
//       eItem.setEntryId(entry1);
//       eItem.setAmount(list.getInvoiceAmount());
//       eItem.setLedgerId(cashLedger);
//       eItem.setType("D");
//       if(currentCashBalance>=0)
//       eItem.setClosingAmtDr(currentCashBalance);
//       else
//       eItem.setClosingAmtCr( Math.abs(currentCashBalance));      
//       entryService.saveEntryItem(eItem);
//
//              
//            }
//       ModelAndView model = new ModelAndView("redirect:VoucherTransactionAuth.html");
//        return model;
//    }
//    
//    @RequestMapping(value={"authoriseTransactionSalesReturn"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
//    public ModelAndView authoriseTransactionSalesReturn(HttpServletRequest req)
//    {
//          String v_id=(req.getParameter("id"));
//        // SINGLE AUTHORISATION Sales,Purchase
//            if(req.getParameter("id") != null){
//              //vouchTransactionAuthService.updateAuthoriseTransactionByIdSR(String.valueOf(req.getParameter("id")));
//               
//        vouchTransactionAuthService.updateSalesReturnById(String.valueOf(req.getParameter("id")));
//        SalesReturn list=salesReturnService.getSalesReturnById(v_id);  
//        Integer entry1=entryService.getEntryListId(v_id,"SalesReturn").getId();
//        SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = new Date();        
//        String CurrentDate= dmyFormat.format(date);
//        
//        int cashBuyer=6;       
//       List<Object[]> cashBuyer_currentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(cashBuyer), CurrentDate);
//      
//       double openingBalanceOfCashBuyer=0;
//       if( cashBuyer_currentBalance!=null && cashBuyer_currentBalance.size()>0)
//       {
//             for (Object[] column : cashBuyer_currentBalance) {
//                        openingBalanceOfCashBuyer = (Double)column[0]- (Double)column[1];
//                }
//           
//       }
//       else
//       {
//           List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(cashBuyer));
//           for (Object[] column : OpeningBalance) {
//                        openingBalanceOfCashBuyer = (Double)column[0]- (Double)column[1];
//                }
//       }   
//       Entryitems eItem0=new Entryitems();
//       Double currentCashBuyerBalance=openingBalanceOfCashBuyer+list.getInvoiceAmount();
//       eItem0.setEntryId(entry1);
//       eItem0.setAmount(list.getInvoiceAmount());
//       eItem0.setLedgerId(cashBuyer);
//       eItem0.setType("D");
//       if(currentCashBuyerBalance>=0)
//       eItem0.setClosingAmtDr(currentCashBuyerBalance);
//       else
//       eItem0.setClosingAmtCr( Math.abs(currentCashBuyerBalance));   
//       entryService.saveEntryItem(eItem0);     
//          
//       // Cash Ledger Entries    
//       int cashLedger=1;       
//       List<Object[]> currentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(cashLedger), CurrentDate);
//      
//       double openingBalanceOfCash=0;
//       if( currentBalance!=null && currentBalance.size()>0)
//       {
//             for (Object[] column : currentBalance) {
//                        openingBalanceOfCash = (Double)column[0]- (Double)column[1];
//                }
//           
//       }
//       else
//       {
//           List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(cashLedger));
//           for (Object[] column : OpeningBalance) {
//                        openingBalanceOfCash = (Double)column[0]- (Double)column[1];
//                }
//       }   
//       Entryitems eItem=new Entryitems();
//       Double currentCashBalance=openingBalanceOfCash-list.getInvoiceAmount();
//       eItem.setEntryId(entry1);
//       eItem.setAmount(list.getInvoiceAmount());
//       eItem.setLedgerId(cashLedger);
//       eItem.setType("C");
//       if(currentCashBalance>=0)
//       eItem.setClosingAmtDr(currentCashBalance);
//       else
//       eItem.setClosingAmtCr( Math.abs(currentCashBalance));      
//       entryService.saveEntryItem(eItem);
//        
//        
//            }
//            
//        ModelAndView model = new ModelAndView("redirect:VoucherTransactionAuth.html");
//        return model;
//    }
//    
//    @RequestMapping(value={"authoriseTransactionPurchase"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
//    public ModelAndView authoriseTransactionPurchase(HttpServletRequest req)
//    {
//        String v_id=(req.getParameter("id"));  
//        // SINGLE AUTHORISATION Sales,Purchase
//        if(req.getParameter("id") != null)
//        {
//        //vouchTransactionAuthService.updateAuthoriseTransactionByIdP(String.valueOf(req.getParameter("id")));
//        vouchTransactionAuthService.updatePurchaseById(String.valueOf(req.getParameter("id")));
//
//        Purchase list=purchaseService.getPurchaseById(v_id);               
//        Integer entry1=entryService.getEntryListId(v_id,"Purchase").getId();
//        SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = new Date();        
//        String CurrentDate= dmyFormat.format(date);
//       
//        int cashSupplier=8;       
//       List<Object[]> cashSupplier_currentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(cashSupplier), CurrentDate);
//      
//       double openingBalanceOfCashSupplier=0;
//       if( cashSupplier_currentBalance!=null && cashSupplier_currentBalance.size()>0)
//       {
//             for (Object[] column : cashSupplier_currentBalance) {
//                        openingBalanceOfCashSupplier = (Double)column[0]- (Double)column[1];
//                }
//           
//       }
//       else
//       {
//           List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(cashSupplier));
//           for (Object[] column : OpeningBalance) {
//                        openingBalanceOfCashSupplier = (Double)column[0]- (Double)column[1];
//                }
//       }   
//       Entryitems eItem0=new Entryitems();
//       Double currentCashSupplierBalance=openingBalanceOfCashSupplier+list.getInvoiceAmount();
//       eItem0.setEntryId(entry1);
//       eItem0.setAmount(list.getInvoiceAmount());
//       eItem0.setLedgerId(cashSupplier);
//       eItem0.setType("D");
//       if(currentCashSupplierBalance>=0)
//       eItem0.setClosingAmtDr(currentCashSupplierBalance);
//       else
//       eItem0.setClosingAmtCr( Math.abs(currentCashSupplierBalance));   
//       entryService.saveEntryItem(eItem0);   
//          
//       // Cash Ledger Entries    
//       int cashLedger=1;       
//       List<Object[]> currentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(cashLedger), CurrentDate);
//      
//       double openingBalanceOfCash=0;
//       if( currentBalance!=null && currentBalance.size()>0)
//       {
//             for (Object[] column : currentBalance) {
//                        openingBalanceOfCash = (Double)column[0]- (Double)column[1];
//                }
//           
//       }
//       else
//       {
//           List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(cashLedger));
//           for (Object[] column : OpeningBalance) {
//                        openingBalanceOfCash = (Double)column[0]- (Double)column[1];
//                }
//       }   
//       Entryitems eItem=new Entryitems();
//       Double currentCashBalance=openingBalanceOfCash-list.getInvoiceAmount();
//       eItem.setEntryId(entry1);
//       eItem.setAmount(list.getInvoiceAmount());
//       eItem.setLedgerId(cashLedger);
//       eItem.setType("C");
//       if(currentCashBalance>=0)
//       eItem.setClosingAmtDr(currentCashBalance);
//       else
//       eItem.setClosingAmtCr( Math.abs(currentCashBalance));      
//       entryService.saveEntryItem(eItem);
//              
//            }
//
//            
//         ModelAndView model = new ModelAndView("redirect:VoucherTransactionAuth.html");
//        return model;
//    }
//    
//    @RequestMapping(value={"authoriseTransactionPurchasereturn"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
//    public ModelAndView authoriseTransactionPurchasereturn(HttpServletRequest req)
//    {
//        String v_id=(req.getParameter("id"));
//        // SINGLE AUTHORISATION Sales,Purchase
//        if(req.getParameter("id") != null)
//        {
//          //vouchTransactionAuthService.updateAuthoriseTransactionByIdPR(String.valueOf(req.getParameter("id")));
//          vouchTransactionAuthService.updatePurchaseReturnById(String.valueOf(req.getParameter("id")));
//          ReversalOfItc list=reversalOfItcService.getReversalOfItcById((v_id));
//          Integer entry1=entryService.getEntryListId(v_id,"ReversalOfItc").getId();
//          SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
//          Date date = new Date();        
//          String CurrentDate= dmyFormat.format(date);
//          
//          int cashSupplier=8;       
//       List<Object[]> cashSupplier_currentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(cashSupplier), CurrentDate);
//      
//       double openingBalanceOfCashSupplier=0;
//       if( cashSupplier_currentBalance!=null && cashSupplier_currentBalance.size()>0)
//       {
//             for (Object[] column : cashSupplier_currentBalance) {
//                        openingBalanceOfCashSupplier = (Double)column[0]- (Double)column[1];
//                }
//           
//       }
//       else
//       {
//           List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(cashSupplier));
//           for (Object[] column : OpeningBalance) {
//                        openingBalanceOfCashSupplier = (Double)column[0]- (Double)column[1];
//                }
//       }   
//       Entryitems eItem0=new Entryitems();
//       Double currentCashSupplierBalance=openingBalanceOfCashSupplier-list.getDebtAmount();
//       eItem0.setEntryId(entry1);
//       eItem0.setAmount(list.getDebtAmount());
//       eItem0.setLedgerId(cashSupplier);
//       eItem0.setType("C");
//       if(currentCashSupplierBalance>=0)
//       eItem0.setClosingAmtDr(currentCashSupplierBalance);
//       else
//       eItem0.setClosingAmtCr( Math.abs(currentCashSupplierBalance));   
//       entryService.saveEntryItem(eItem0);     
//          
//       // Cash Ledger Entries    
//       int cashLedger=1;       
//       List<Object[]> currentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(cashLedger), CurrentDate);
//      
//       double openingBalanceOfCash=0;
//       if( currentBalance!=null && currentBalance.size()>0)
//       {
//             for (Object[] column : currentBalance) {
//                        openingBalanceOfCash = (Double)column[0]- (Double)column[1];
//                }
//           
//       }
//       else
//       {
//           List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(cashLedger));
//           for (Object[] column : OpeningBalance) {
//                        openingBalanceOfCash = (Double)column[0]- (Double)column[1];
//                }
//       }   
//       Entryitems eItem=new Entryitems();
//       Double currentCashBalance=openingBalanceOfCash+list.getDebtAmount();
//       eItem.setEntryId(entry1);
//       eItem.setAmount(list.getDebtAmount());
//       eItem.setLedgerId(cashLedger);
//       eItem.setType("D");
//       if(currentCashBalance>=0)
//       eItem.setClosingAmtDr(currentCashBalance);
//       else
//       eItem.setClosingAmtCr( Math.abs(currentCashBalance));      
//       entryService.saveEntryItem(eItem);
//              
//            }
//
//            
//          ModelAndView model = new ModelAndView("redirect:VoucherTransactionAuth.html");
//        return model;
//    }
//    
//    @RequestMapping(value={"authoriseTransactionAll"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
//    public ModelAndView authoriseTransactionAll(HttpServletRequest req)
//    {
//
//        // Multiple AUTHORISATION Sales,Purchase
//        
//        String authId[] = req.getParameterValues("authValues");
//        int selectCount = authId.length;      
//        if(selectCount>0)
//        {
//        SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = new Date();        
//        String CurrentDate= dmyFormat.format(date);    
//        for(int i=0; i < selectCount; i++)
//        {
//           String[] invoice_type = authId[i].split(",");    
//         
//           if(invoice_type[1].equalsIgnoreCase("Sales"))
//           { vouchTransactionAuthService.updateSalesById(invoice_type[0]);
//             Sales list=salesService.getSalesById(invoice_type[0]);
//            Integer entry1=entryService.getEntryListId(invoice_type[0],"Sales").getId();
//            int cashBuyer=6;       
//            List<Object[]> cashBuyer_currentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(cashBuyer), CurrentDate);
//
//            double openingBalanceOfCashBuyer=0;
//            if( cashBuyer_currentBalance!=null && cashBuyer_currentBalance.size()>0)
//            {
//                  for (Object[] column : cashBuyer_currentBalance) {
//                             openingBalanceOfCashBuyer = (Double)column[0]- (Double)column[1];
//                     }
//
//            }
//            else
//            {
//                List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(cashBuyer));
//                for (Object[] column : OpeningBalance) {
//                             openingBalanceOfCashBuyer = (Double)column[0]- (Double)column[1];
//                     }
//            }   
//            Entryitems eItem0=new Entryitems();
//            Double currentCashBuyerBalance=openingBalanceOfCashBuyer-list.getInvoiceAmount();
//            eItem0.setEntryId(entry1);
//            eItem0.setAmount(list.getInvoiceAmount());
//            eItem0.setLedgerId(cashBuyer);
//            eItem0.setType("C");
//            if(currentCashBuyerBalance>=0)
//            eItem0.setClosingAmtDr(currentCashBuyerBalance);
//            else
//            eItem0.setClosingAmtCr( Math.abs(currentCashBuyerBalance));   
//            entryService.saveEntryItem(eItem0);     
//
//            // Cash Ledger Entries    
//            int cashLedger=1;       
//            List<Object[]> currentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(cashLedger), CurrentDate);
//
//            double openingBalanceOfCash=0;
//            if( currentBalance!=null && currentBalance.size()>0)
//            {
//                  for (Object[] column : currentBalance) {
//                             openingBalanceOfCash = (Double)column[0]- (Double)column[1];
//                     }
//
//            }
//            else
//            {
//                List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(cashLedger));
//                for (Object[] column : OpeningBalance) {
//                             openingBalanceOfCash = (Double)column[0]- (Double)column[1];
//                     }
//            }   
//            Entryitems eItem=new Entryitems();
//            Double currentCashBalance=openingBalanceOfCash+list.getInvoiceAmount();
//            eItem.setEntryId(entry1);
//            eItem.setAmount(list.getInvoiceAmount());
//            eItem.setLedgerId(cashLedger);
//            eItem.setType("D");
//            if(currentCashBalance>=0)
//            eItem.setClosingAmtDr(currentCashBalance);
//            else
//            eItem.setClosingAmtCr( Math.abs(currentCashBalance));      
//            entryService.saveEntryItem(eItem);
//           }
//           else if(invoice_type[1].equalsIgnoreCase("SalesReturn"))
//           {
//             vouchTransactionAuthService.updateSalesReturnById(invoice_type[0]);
//              SalesReturn list=salesReturnService.getSalesReturnById(invoice_type[0]);  
//                Integer entry1=entryService.getEntryListId(invoice_type[0],"SalesReturn").getId();
//                int cashBuyer=6;       
//               List<Object[]> cashBuyer_currentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(cashBuyer), CurrentDate);
//
//               double openingBalanceOfCashBuyer=0;
//               if( cashBuyer_currentBalance!=null && cashBuyer_currentBalance.size()>0)
//               {
//                     for (Object[] column : cashBuyer_currentBalance) {
//                                openingBalanceOfCashBuyer = (Double)column[0]- (Double)column[1];
//                        }
//
//               }
//               else
//               {
//                   List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(cashBuyer));
//                   for (Object[] column : OpeningBalance) {
//                                openingBalanceOfCashBuyer = (Double)column[0]- (Double)column[1];
//                        }
//               }   
//               Entryitems eItem0=new Entryitems();
//               Double currentCashBuyerBalance=openingBalanceOfCashBuyer+list.getInvoiceAmount();
//               eItem0.setEntryId(entry1);
//               eItem0.setAmount(list.getInvoiceAmount());
//               eItem0.setLedgerId(cashBuyer);
//               eItem0.setType("D");
//               if(currentCashBuyerBalance>=0)
//               eItem0.setClosingAmtDr(currentCashBuyerBalance);
//               else
//               eItem0.setClosingAmtCr( Math.abs(currentCashBuyerBalance));   
//               entryService.saveEntryItem(eItem0);     
//
//               // Cash Ledger Entries    
//               int cashLedger=1;       
//               List<Object[]> currentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(cashLedger), CurrentDate);
//
//               double openingBalanceOfCash=0;
//               if( currentBalance!=null && currentBalance.size()>0)
//               {
//                     for (Object[] column : currentBalance) {
//                                openingBalanceOfCash = (Double)column[0]- (Double)column[1];
//                        }
//
//               }
//               else
//               {
//                   List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(cashLedger));
//                   for (Object[] column : OpeningBalance) {
//                                openingBalanceOfCash = (Double)column[0]- (Double)column[1];
//                        }
//               }   
//               Entryitems eItem=new Entryitems();
//               Double currentCashBalance=openingBalanceOfCash-list.getInvoiceAmount();
//               eItem.setEntryId(entry1);
//               eItem.setAmount(list.getInvoiceAmount());
//               eItem.setLedgerId(cashLedger);
//               eItem.setType("C");
//               if(currentCashBalance>=0)
//               eItem.setClosingAmtDr(currentCashBalance);
//               else
//               eItem.setClosingAmtCr( Math.abs(currentCashBalance));      
//               entryService.saveEntryItem(eItem);
//           }
//           else if(invoice_type[1].equalsIgnoreCase("Purchase"))
//           {
//             vouchTransactionAuthService.updatePurchaseById(invoice_type[0]);  
//              Purchase list=purchaseService.getPurchaseById(invoice_type[0]);               
//                Integer entry1=entryService.getEntryListId(invoice_type[0],"Purchase").getId();
//              
//
//                int cashSupplier=8;       
//               List<Object[]> cashSupplier_currentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(cashSupplier), CurrentDate);
//
//               double openingBalanceOfCashSupplier=0;
//               if( cashSupplier_currentBalance!=null && cashSupplier_currentBalance.size()>0)
//               {
//                     for (Object[] column : cashSupplier_currentBalance) {
//                                openingBalanceOfCashSupplier = (Double)column[0]- (Double)column[1];
//                        }
//
//               }
//               else
//               {
//                   List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(cashSupplier));
//                   for (Object[] column : OpeningBalance) {
//                                openingBalanceOfCashSupplier = (Double)column[0]- (Double)column[1];
//                        }
//               }   
//               Entryitems eItem0=new Entryitems();
//               Double currentCashSupplierBalance=openingBalanceOfCashSupplier+list.getInvoiceAmount();
//               eItem0.setEntryId(entry1);
//               eItem0.setAmount(list.getInvoiceAmount());
//               eItem0.setLedgerId(cashSupplier);
//               eItem0.setType("D");
//               if(currentCashSupplierBalance>=0)
//               eItem0.setClosingAmtDr(currentCashSupplierBalance);
//               else
//               eItem0.setClosingAmtCr( Math.abs(currentCashSupplierBalance));   
//               entryService.saveEntryItem(eItem0);   
//
//               // Cash Ledger Entries    
//               int cashLedger=1;       
//               List<Object[]> currentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(cashLedger), CurrentDate);
//
//               double openingBalanceOfCash=0;
//               if( currentBalance!=null && currentBalance.size()>0)
//               {
//                     for (Object[] column : currentBalance) {
//                                openingBalanceOfCash = (Double)column[0]- (Double)column[1];
//                        }
//
//               }
//               else
//               {
//                   List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(cashLedger));
//                   for (Object[] column : OpeningBalance) {
//                                openingBalanceOfCash = (Double)column[0]- (Double)column[1];
//                        }
//               }   
//               Entryitems eItem=new Entryitems();
//               Double currentCashBalance=openingBalanceOfCash-list.getInvoiceAmount();
//               eItem.setEntryId(entry1);
//               eItem.setAmount(list.getInvoiceAmount());
//               eItem.setLedgerId(cashLedger);
//               eItem.setType("C");
//               if(currentCashBalance>=0)
//               eItem.setClosingAmtDr(currentCashBalance);
//               else
//               eItem.setClosingAmtCr( Math.abs(currentCashBalance));      
//               entryService.saveEntryItem(eItem);
//           }
//           else if(invoice_type[1].equalsIgnoreCase("PurchaseReturn"))
//           {
//             vouchTransactionAuthService.updatePurchaseReturnById(invoice_type[0]);  
//             ReversalOfItc list=reversalOfItcService.getReversalOfItcById(invoice_type[0]);
//             Integer entry1=entryService.getEntryListId(invoice_type[0],"ReversalOfItc").getId();
//             int cashSupplier=8;       
//             List<Object[]> cashSupplier_currentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(cashSupplier), CurrentDate);
//
//             double openingBalanceOfCashSupplier=0;
//             if( cashSupplier_currentBalance!=null && cashSupplier_currentBalance.size()>0)
//             {
//                   for (Object[] column : cashSupplier_currentBalance) {
//                              openingBalanceOfCashSupplier = (Double)column[0]- (Double)column[1];
//                      }
//
//             }
//             else
//             {
//                 List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(cashSupplier));
//                 for (Object[] column : OpeningBalance) {
//                              openingBalanceOfCashSupplier = (Double)column[0]- (Double)column[1];
//                      }
//             }   
//             Entryitems eItem0=new Entryitems();
//             Double currentCashSupplierBalance=openingBalanceOfCashSupplier-list.getDebtAmount();
//             eItem0.setEntryId(entry1);
//             eItem0.setAmount(list.getDebtAmount());
//             eItem0.setLedgerId(cashSupplier);
//             eItem0.setType("C");
//             if(currentCashSupplierBalance>=0)
//             eItem0.setClosingAmtDr(currentCashSupplierBalance);
//             else
//             eItem0.setClosingAmtCr( Math.abs(currentCashSupplierBalance));   
//             entryService.saveEntryItem(eItem0);     
//
//             // Cash Ledger Entries    
//             int cashLedger=1;       
//             List<Object[]> currentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(cashLedger), CurrentDate);
//
//             double openingBalanceOfCash=0;
//             if( currentBalance!=null && currentBalance.size()>0)
//             {
//                   for (Object[] column : currentBalance) {
//                              openingBalanceOfCash = (Double)column[0]- (Double)column[1];
//                      }
//
//             }
//             else
//             {
//                 List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(cashLedger));
//                 for (Object[] column : OpeningBalance) {
//                              openingBalanceOfCash = (Double)column[0]- (Double)column[1];
//                      }
//             }   
//             Entryitems eItem=new Entryitems();
//             Double currentCashBalance=openingBalanceOfCash+list.getDebtAmount();
//             eItem.setEntryId(entry1);
//             eItem.setAmount(list.getDebtAmount());
//             eItem.setLedgerId(cashLedger);
//             eItem.setType("D");
//             if(currentCashBalance>=0)
//             eItem.setClosingAmtDr(currentCashBalance);
//             else
//             eItem.setClosingAmtCr( Math.abs(currentCashBalance));      
//             entryService.saveEntryItem(eItem);
//           }
//        
//        
//                }
//            }
//        ModelAndView model = new ModelAndView("redirect:VoucherTransactionAuth.html");
//        return model;
//    }
//    
//    
//  @RequestMapping(value={"GetDatatablevGrid"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
//   
//    public DatatableObject getDatatable( HttpServletRequest req)
//    {
//      DatatableObject db =new DatatableObject();
//           String[] cols = {"invoice_no", "date","date","date","date"};
//
//    int amount = 10;
//    int start = 0;
//    int echo = 0;
//    int col = 0;
//    
//     
//    String engine = "";
//    String browser = "";
//    String platform = "";
//    String version = "";
//    String grade = "";
// 
//    String dir = "asc";
//    String sStart = req.getParameter("start");
//    String sAmount = req.getParameter("length");
//    String sEcho = req.getParameter("sEcho");
//    String sCol = req.getParameter("order[0][column]");
//    String sdir = req.getParameter("order[0][dir]");
//
//    String individualSearch = "";
//
//    if (sStart != null) {
//        start = Integer.parseInt(sStart);
//        if (start < 0)
//            start = 0;
//    }
//    if (sAmount != null) {
//        amount = Integer.parseInt(sAmount);
//        if (amount < 10 || amount > 100)
//            amount = 10;
//    }
//    if (sEcho != null) {
//        echo = Integer.parseInt(sEcho);
//    }
//    if (sCol != null) {
//        col = Integer.parseInt(sCol);
//        if (col < 0 || col > 6)
//            col = 0;
//    }
//    if (sdir != null) {
//        if (!sdir.equals("asc"))
//            dir = "desc";
//    }
//    String colName = cols[col];
//    int total = 0;
//   
//    try {
//        String sql = "";
//        List<Object[]> list = vouchTransactionAuthService .GetDatatableObject(sql);
//        total=list.size();
//
//    }catch(Exception e){
//         
//    }
//    int totalAfterFilter = total;
//    //result.put("sEcho",echo);
// 
//    try {
//        String searchSQL = "";
//        String sql = "SELECT sa.invoice_no AS saleI,'Sales',sa.date,sa.name_of_buyer,sa.invoice_amount,sa.category FROM(SELECT s.invoice_no,s.date,s.name_of_buyer,s.invoice_amount,'Sales',s.category FROM sales AS s WHERE s.mode='Cash' AND s.Authid='0')AS sa ";
//        String searchTerm =req.getParameter("search[value]");
//         String globeSearch =  " where (sa.invoice_no like '"+searchTerm+"%')";
//        if(searchTerm!=""&&individualSearch!=""){
//            searchSQL = globeSearch + " and " + individualSearch;
//        }
//        else if(individualSearch!=""){
//            searchSQL = " where " + individualSearch;
//        }else if(searchTerm!=""){
//            searchSQL=globeSearch;
//        }
//        sql += searchSQL;
//        sql += " order by " + "sa.invoice_no" + " " + dir;
//        sql += " limit " + start + ", " + amount;
//        // For aData
//         List<Object[]> list2 = vouchTransactionAuthService.GetDatatableObject(sql);
//         // For Filter Count 
//        String sql2 = "SELECT sa.invoice_no AS saleI,'Sales',sa.date,sa.name_of_buyer,sa.invoice_amount,sa.category FROM(SELECT s.invoice_no,s.date,s.name_of_buyer,s.invoice_amount,'Sales',s.category FROM sales AS s WHERE s.mode='Cash' AND s.Authid='0')AS sa";
//       if (searchTerm != "") {
//            sql2 += searchSQL;
//          List<Object[]> count = vouchTransactionAuthService.GetDatatableCount(sql2);
//          totalAfterFilter=count.size();
//        }
//  
//   db.setiTotalRecords(total);
//   db.setiTotalDisplayRecords(totalAfterFilter);
//   db.setAaData(list2);
//    } catch (Exception e) {
// 
//    }
//      return db;
//	}  
//    
//}
