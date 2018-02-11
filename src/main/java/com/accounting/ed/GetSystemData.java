/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.ed;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * The Class SystemInfoTest.
 *
 * @author dblock[at]dblock[dot]org
 */
public class GetSystemData {
    

    /**
     * The main method.
     *
     * @param args
     *            the arguments
     */
    public static void main(String[] args) throws SocketException {
      
     System.out.println(getBaseboardSerialnumber());
   System.out.println(getMacAddress());


    }

 


  
public static String getMacAddress() throws SocketException {
    String firstInterface = null;        
    Map<String, String> addressByNetwork = new HashMap<>();
    Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();

    while(networkInterfaces.hasMoreElements()){
        NetworkInterface network = networkInterfaces.nextElement();

        byte[] bmac = network.getHardwareAddress();
        if(bmac != null){
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bmac.length; i++){
                sb.append(String.format("%02X%s", bmac[i], (i < bmac.length - 1) ? "-" : ""));        
            }

            if(sb.toString().isEmpty()==false){
                addressByNetwork.put(network.getName(), sb.toString());
              //  System.out.println("Address = "+sb.toString()+" @ ["+network.getName()+"] "+network.getDisplayName());
            }

            if(sb.toString().isEmpty()==false && firstInterface == null){
                firstInterface = network.getName();
            }
        }
    }

    if(firstInterface != null){
        return addressByNetwork.get(firstInterface);
    }

    return null;
}
 private static String getWindowsMotherboard_SerialNumber() {
        String result = "";
        try {
            File file = File.createTempFile("realhowto",".vbs");
            file.deleteOnExit();
            FileWriter fw = new java.io.FileWriter(file);

            String vbs =
            "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
              + "Set colItems = objWMIService.ExecQuery _ \n"
              + "   (\"Select * from Win32_BaseBoard\") \n"
              + "For Each objItem in colItems \n"
              + "    Wscript.Echo objItem.SerialNumber \n"
              + "    exit for  ' do the first cpu only! \n"
              + "Next \n";

            fw.write(vbs);
            fw.close();

            Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
               result += line;
            }
            input.close();
        }
        catch(Exception E){
             System.err.println("Windows MotherBoard Exp : "+E.getMessage());
        }
        return result.trim();
    } 
    
    
    /**
     * Method for get Linux Machine MotherBoard Serial Number
     * @return 
     */
    private static String GetLinuxMotherBoard_serialNumber() {
        String command = "dmidecode -s baseboard-serial-number";
        String sNum = null; 
        try {   
            Process SerNumProcess = Runtime.getRuntime().exec(command);
            BufferedReader sNumReader = new BufferedReader(new InputStreamReader(SerNumProcess.getInputStream()));
            sNum = sNumReader.readLine().trim();
            SerNumProcess.waitFor();
            sNumReader.close();
        }
        catch (Exception ex) {
            System.err.println("Linux Motherboard Exp : "+ex.getMessage());
            sNum =null;
        }
        return sNum; 
    }
     public static String getBaseboardSerialnumber(){
        try{
            String OSName=  System.getProperty("os.name");
            if(OSName.contains("Windows")){
                return (getWindowsMotherboard_SerialNumber());
            }
            else{
                return (GetLinuxMotherBoard_serialNumber());
            }
        }
        catch(Exception E){
            System.err.println("System MotherBoard Exp : "+E.getMessage());
            return null;
        }
    }
  
}