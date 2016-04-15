/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCPServer;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

class serverTCP extends Thread {
    
    protected final static int PORT = 60000;
    protected static int streamsCount = 0;
    protected static DataOutputStream streams[] = new DataOutputStream[10];
    
    
    public static void main(String args[]) throws Exception{
        //initializing stream
        InetAddress serverIP = null;
        serverIP = InetAddress.getByName("localhost");
        ServerSocket sSocket = new ServerSocket(PORT, 0,serverIP);
        System.out.println("Server is starting...");
        Risunok.main(args);
       //гетфайлконтент; стрингреплейс
        
        while (true){
            Socket cSocket = sSocket.accept();
            
            System.out.println("To server connected " + cSocket.getInetAddress() + ":" + cSocket.getPort());
            streams[streamsCount] = new DataOutputStream(cSocket.getOutputStream());
            streamsCount++;
            new ThreadClient(cSocket).start();
        }
              
        
        
    }
     
}
