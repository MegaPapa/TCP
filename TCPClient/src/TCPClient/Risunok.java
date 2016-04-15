package TCPClient;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;


public class Risunok{
    final static PadDraw drawPad = new PadDraw();
	public static void main(String[] args){
		Icon iconB = new ImageIcon("blue.gif");
		//the blue image icon
		Icon iconM = new ImageIcon("magenta.gif");
		//magenta image icon
		Icon iconR = new ImageIcon("red.gif");
		//red image icon
		Icon iconBl = new ImageIcon("black.gif");
		//black image icon
		Icon iconG = new ImageIcon("green.gif");
		//finally the green image icon
		//These will be the images for our colors.
		
		JFrame frame = new JFrame("Paint It");
		//Creates a frame with a title of "Paint it"
		
		Container content = frame.getContentPane();
		//Creates a new container
		content.setLayout(new BorderLayout());
		//sets the layout
		
		//----------
		//creates a new padDraw, which is pretty much the paint program
		
		content.add(drawPad, BorderLayout.CENTER);
		//sets the padDraw in the center
		
		JPanel panel = new JPanel();
		//creates a JPanel
		panel.setPreferredSize(new Dimension(32, 68));
		panel.setMinimumSize(new Dimension(32, 68));
		panel.setMaximumSize(new Dimension(32, 68));
		//This sets the size of the panel
		
		JButton clearButton = new JButton("Clear");
		//creates the clear button and sets the text as "Clear"
		clearButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				drawPad.clear();
			}
		});
		//this is the clear button, which clears the screen.  This pretty
		//much attaches an action listener to the button and when the
		//button is pressed it calls the clear() method
		
		JButton redButton = new JButton(iconR);
		//creates the red button and sets the icon we created for red
		redButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				drawPad.red();
			}

		});
		//when pressed it will call the red() method.  So on and so on =]
		
		JButton blackButton = new JButton(iconBl);
		//same thing except this is the black button
		blackButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				drawPad.black();
			}
		});
		
		JButton magentaButton = new JButton(iconM);
		//magenta button
		magentaButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				drawPad.magenta();
			}
		});
		
		JButton blueButton = new JButton(iconB);
		//blue button
		blueButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				drawPad.blue();
			}
		});
		
		JButton greenButton = new JButton(iconG);
		//green button
		greenButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				drawPad.green();
			}
		});
		blackButton.setPreferredSize(new Dimension(16, 16));
		magentaButton.setPreferredSize(new Dimension(16, 16));
		redButton.setPreferredSize(new Dimension(16, 16));
		blueButton.setPreferredSize(new Dimension(16, 16));
		greenButton.setPreferredSize(new Dimension(16,16));
		//sets the sizes of the buttons
		
		panel.add(greenButton);
		panel.add(blueButton);
		panel.add(magentaButton);
		panel.add(blackButton);
		panel.add(redButton);
		panel.add(clearButton);
		//adds the buttons to the panel
		
		content.add(panel, BorderLayout.WEST);
		//sets the panel to the left
		
		frame.setSize(300, 300);
		//sets the size of the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//makes it so you can close
		frame.setVisible(true);
		//makes it so you can see it
	}
}


class PadDraw extends JComponent{
	Image image;
	//this is gonna be your image that you draw on
	static Graphics2D graphics2D;
	//this is what we'll be using to draw on
	int currentX, currentY, oldX, oldY;
	//these are gonna hold our mouse coordinates

        
        public static final byte[] intToByteArray(int value) {
    return new byte[] {
            (byte)(value >>> 24),
            (byte)(value >>> 16),
            (byte)(value >>> 8),
            (byte)value};
}
  //ВОЗМОЖНО ВЫ ПОДУМОЛИ ЧТО ТУТ СДЕЛАНО, НО ТУТ НЕ ДАДЕЛАНО, ДАДЕЛАЙ ЕТО, ТЫ НЕ СДЕЛАЛ ПЕРЕДАЧУ СЕРВУРУ
  public void sendCoordinates(int x, int y,int oldX, int oldY) {
      try{
        byte messagex[] = new byte[1024];
        byte messagey[] = new byte[1024];
        byte message[] = new byte[1024];
       // message[0] = (byte) x;
       // message[1] = (byte) y;
        //for (int i = 0; i < serverTCP.streamsCount; i++){
            DataOutputStream outputReader = new DataOutputStream(client.cSocket.getOutputStream());
            System.out.println("server intx: " + x + " inty: " + y + " oldx: " + oldX + " oldY: " + oldY);
            messagex = intToByteArray(x);
            messagey = intToByteArray(y);
            message[0] = messagex[0];
            message[1] = messagex[1];
            message[2] = messagex[2];
            message[3] = messagex[3];
            message[4] = messagey[0];
            message[5] = messagey[1];
            message[6] = messagey[2];
            message[7] = messagey[3];
            messagex = intToByteArray(oldX);
            messagey = intToByteArray(oldY);
            message[8] = messagex[0];
            message[9] = messagex[1];
            message[10] = messagex[2];
            message[11] = messagex[3];
            message[12] = messagey[0];
            message[13] = messagey[1];
            message[14] = messagey[2];
            message[15] = messagey[3];
            outputReader.write(message);
            
            //outputReader.writeByte(y);
        }
         
      
      catch (IOException e){
          System.out.println(e);
      }
  }
        
        
        
        
        
        
	//Now for the constructors
	public PadDraw(){
		setDoubleBuffered(false);
		addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				oldX = e.getX();
				oldY = e.getY();
			}
		});
		//if the mouse is pressed it sets the oldX & oldY
		//coordinates as the mouses x & y coordinates
		addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseDragged(MouseEvent e){
				currentX = e.getX();
				currentY = e.getY();
				if(graphics2D != null)
				graphics2D.drawLine(oldX, oldY, currentX, currentY);
                                sendCoordinates(currentX, currentY, oldX, oldY);
				repaint();
				oldX = currentX;
				oldY = currentY;
			}

		});
		//while the mouse is dragged it sets currentX & currentY as the mouses x and y
		//then it draws a line at the coordinates
		//it repaints it and sets oldX and oldY as currentX and currentY
	}
        
        
        
        public void drawServerLine(int x,int y,int oldX,int oldY){
            graphics2D.drawLine(x, y, oldX, oldY);
            repaint();   
        }
        

	public void paintComponent(Graphics g){
		if(image == null){
			image = createImage(getSize().width, getSize().height);
			graphics2D = (Graphics2D)image.getGraphics();
			graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			clear();

		}
		g.drawImage(image, 0, 0, null);
	}
	//this is the painting bit
	//if it has nothing on it then
	//it creates an image the size of the window
	//sets the value of Graphics as the image
	//sets the rendering
	//runs the clear() method
	//then it draws the image


	public void clear(){
		graphics2D.setPaint(Color.white);
		graphics2D.fillRect(0, 0, getSize().width, getSize().height);
		graphics2D.setPaint(Color.black);
		repaint();
	}
	//this is the clear
	//it sets the colors as white
	//then it fills the window with white
	//thin it sets the color back to black
	public void red(){
		graphics2D.setPaint(Color.red);
		repaint();
	}
	//this is the red paint
	public void black(){
		graphics2D.setPaint(Color.black);
		repaint();
	}
	//black paint
	public void magenta(){
		graphics2D.setPaint(Color.magenta);
		repaint();
	}
	//magenta paint
	public void blue(){
		graphics2D.setPaint(Color.blue);
		repaint();
	}
	//blue paint
	public void green(){
		graphics2D.setPaint(Color.green);
		repaint();
	}
	//green paint

}


//good job, you've made your paint program =]