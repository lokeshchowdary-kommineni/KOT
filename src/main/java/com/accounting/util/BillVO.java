/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.util;

import java.util.List;
import java.util.Map;

/**
 *
 * @author MR
 */
public class BillVO {
    List<Map<String,String>> dataSource;
    Map parametersMap;

    public List<Map<String, String>> getDataSource() {
        return dataSource;
    }

    public void setDataSource(List<Map<String, String>> dataSource) {
        this.dataSource = dataSource;
    }

    public Map getParametersMap() {
        return parametersMap;
    }

    public void setParametersMap(Map parametersMap) {
        this.parametersMap = parametersMap;
    }
}
