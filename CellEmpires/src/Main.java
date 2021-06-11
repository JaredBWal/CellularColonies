import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 * 
 *  @author 
 *  @version 
 */
public class Main extends JFrame implements MouseListener, MouseMotionListener {
    
    public MainBoardGui panel;
    JLabel label;


    public static int windowWidth = 1500;
    public static int windowHeight = 1000;
    
    public static void main(String[] args) throws IOException, InterruptedException {
        Main frame = new Main();
        while(true){
            frame.getPanel().repaint();
            //The program waits a while before re-rendering
            Thread.sleep(100);
        }
        
    }
    
    public Main() {
    	
    	this.setSize(windowWidth, windowHeight);

		
        this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.white);

		label = new JLabel();
		label.setBounds(0,0,windowWidth,windowHeight);
		label.setBackground(Color.blue);
		label.setOpaque(true);
		label.addMouseListener(this);
		label.addMouseMotionListener(this);
		
		this.add(label);
        
        
        // creates a new MainEngine Panel
        panel = new MainBoardGui();
        panel.setBackground(Color.lightGray);
        

        //add Panel to Frame
        this.add(panel);
       

        this.setVisible(true);
    }
    
    
    
      //gets the panel 
    public MainBoardGui getPanel(){
        return panel;
    }

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		int mouseXPos = e.getX();
		int mouseYPos = e.getY();
		
		MainBoardGui.mouseX = mouseXPos;
		MainBoardGui.mouseY = mouseYPos;
		
//		if (mouseXPos > windowWidth -100) {
//			panel.changeXOffset(1);
//
//		}
//		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
