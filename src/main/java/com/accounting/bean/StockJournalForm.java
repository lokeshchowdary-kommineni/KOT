
package com.accounting.bean;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.list.LazyList;


public class StockJournalForm {
    
    private List<StockJournalItem> stocks;
    private List<StockFjournalItem> stocksF; 
    private StockJournal stock;

    public List<StockFjournalItem> getStocksF() {
        return stocksF;
    }

    public void setStocksF(List<StockFjournalItem> stocksF) {
        this.stocksF = stocksF;
    }

    public List<StockJournalItem> getStocks() {
        return stocks;
    }

    public void setStocks(List<StockJournalItem> stocks) {
        this.stocks = stocks;
    }

    public StockJournal getStock() {
        return stock;
    }

    public void setStock(StockJournal stock) {
        this.stock = stock;
    }

   
   
  
    
}
