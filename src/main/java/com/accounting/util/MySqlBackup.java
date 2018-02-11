/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.sql.SQLException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;



/**
 *
 * @author MR
 */
public class MySqlBackup {

    public void backUpDB(String path){

        String executeCmd = "C:/wamp/bin/mysql/mysql5.1.36/bin/mysqldump -u root -B kot_db -r " + path;

//        System.out.println(executeCmd);
 
        Process runtimeProcess;
 
        try
        {
           runtimeProcess = Runtime.getRuntime().exec(new String[] { "cmd.exe", "/c", executeCmd });

           int processComplete = runtimeProcess.waitFor();

//           System.out.println(processComplete);

           if(processComplete == 0)
           {
              System.out.println("Backup Created Successfully !");
           }
           else
           {
              System.out.println("Couldn't Create the backup !");
           }
        }
        catch(Exception ex)
        {
           ex.printStackTrace();
        }

    }
    
    public void restoreDB(String path) throws URISyntaxException{
 
//        System.out.println(" path :"+path);
        String executeCmd = "C:/wamp/bin/mysql/mysql5.1.36/bin/mysql -u root kot_db < " +path;
//        String home=System.getProperty("user.home");
//        File file=new File(home+"/Downloads/"+path);
//        System.out.println("file :"+file);
//        String executeCmd = "C:/wamp/bin/mysql/mysql5.1.36/bin/mysql -u root kot_db < " +file;
//        System.out.println(executeCmd);

        Process runtimeProcess;

        try
        {
            runtimeProcess = Runtime.getRuntime().exec(new String[] { "cmd.exe", "/c", executeCmd });

            int processComplete = runtimeProcess.waitFor();

//            System.out.println(processComplete);

            if(processComplete == 0)
            {
                System.out.println("Restored the Backup !");
            }
            else
            {
                System.out.println("Couldn't Restore the backup !");
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
