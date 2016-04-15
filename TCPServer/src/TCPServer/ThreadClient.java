package TCPServer;

import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;

public class ThreadClient extends Thread{
    private Socket socket;
    
    ThreadClient(Socket clientSocket) {
        this.socket = clientSocket;
    }   
    
public static int byteArrayToInt(byte[] b,int i) {
    return   b[3+i] & 0xFF |
            (b[2+i] & 0xFF) << 8 |
            (b[1+i] & 0xFF) << 16 |
            (b[0+i] & 0xFF) << 24;
}
    
    
    public void run(){
        byte buf[] = new byte[1024];
        
        try{
            while (true){ 
               BufferedInputStream inputReader = new BufferedInputStream(socket.getInputStream());
               inputReader.read(buf);
               int x = byteArrayToInt(buf,0);
               int y = byteArrayToInt(buf,4);
               int oldX = byteArrayToInt(buf,8);
               int oldY = byteArrayToInt(buf,12);
               Risunok.drawPad.drawClientLine(x, y, oldX, oldY);
               System.out.println("FROM CLIENT x: " + x + "y: " + y);    
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        
        
    }
    
}
