/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.event;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StartupHousekeeper {

  @EventListener(ContextRefreshedEvent.class)
  public void contextRefreshedEvent() throws IOException {
    // do whatever you need here 
    System.out.println(" testedt in application startup");
    String url = "http://localhost:8080/Accounting/Index.html";

//        if(Desktop.isDesktopSupported()){
//            Desktop desktop = Desktop.getDesktop();
//            try {
//                desktop.browse(new URI(url));
//            } catch (IOException | URISyntaxException e) {
//                try { 
//               Runtime rt = Runtime.getRuntime();         
//               rt.exec( "rundll32 url.dll,FileProtocolHandler " + url);
//                }
//                catch (IOException handle)
//                {
//                 System.out.println(handle.getMessage());   
//                }
//            }
//        }else{
//         
//            try {
//                Runtime rt = Runtime.getRuntime();  
//                rt.exec( "rundll32 url.dll,FileProtocolHandler " + url);
//            } catch (IOException e) {
//                System.out.println(e.getMessage());   
//            }
//        }
  }
}