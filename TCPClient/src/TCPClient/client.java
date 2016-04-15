package TCPClient;

import java.io.*;
import java.net.*;

class client extends Thread {
    
public static Socket cSocket;
    
public static int byteArrayToInt(byte[] b,int i) {
    return   b[3+i] & 0xFF |
            (b[2+i] & 0xFF) << 8 |
            (b[1+i] & 0xFF) << 16 |
            (b[0+i] & 0xFF) << 24;
}
    

    
	public static void main(String args[]) throws Exception {
            //initializing stream
                cSocket = new Socket("localhost",60000);
                BufferedInputStream inFromServer = new BufferedInputStream(cSocket.getInputStream());
                byte buf[] = new byte[1024];
                Risunok.main(args);
              //  Risunok.();
                
                
                while (true){
                    inFromServer.read(buf);
                    int x = byteArrayToInt(buf,0);
                    int y = byteArrayToInt(buf,4);
                    int oldX = byteArrayToInt(buf,8);
                    int oldY = byteArrayToInt(buf,12);
                    Risunok.drawPad.drawServerLine(x,y,oldX,oldY);
                   // Risunok.mypan.drawSended(x, y);
                    System.out.println("FROM SERVER X: " + x + " Y: " + y + " OLDX: " + oldX + " OLDY: " + oldY);
                }

	}
        
        
}