package TCPServer;

import java.awt.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.*;
import javax.swing.AbstractButton;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class paintWindow extends JPanel{
    
    int lastx, lasty;
    public Graphics graphics;
    protected static JFrame mainFrame = new JFrame("Server");
    
    paintWindow(){
       
    }
    
    public static void main(String[] args) {
        //оставлю на всякий случай
    }
    
    
    @Override
    public void paintComponent(Graphics ff) {
        super.paintComponent(graphics);
        ff.drawLine(lastx ,lasty, lastx, lasty);
            
    }
    
    protected void showWin(){
        
        
    
        mainFrame.setSize(400, 400);
        Graphics g = getGraphics();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);

        
    }
    
    
                
    
}
