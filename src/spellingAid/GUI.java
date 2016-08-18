package spellingAid;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class GUI implements WindowListener{
	//From: http://stackoverflow.com/questions/7140248/get-system-default-font
	public static final Font TITLE_FONT = new Font(new JLabel().getName(), 1, 20);
	private JFrame _frame = null;
	private static GUI _gui = null;
	public static void main(String[] args) {
		//Schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.		
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GUI.getInstance().createAndShowWelcomeScreen();
			}
		});
	}
	private GUI(){
		//Create and set up the window.
		_frame = new JFrame("Spelling Aid");
		_frame.addWindowListener(this);
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private void createAndShowWelcomeScreen() {
		//Set up the content pane.
		
		new WelcomeScreen();
		_frame.pack();
		_frame.setVisible(true);
	}
	
	public static GUI getInstance(){
		if (_gui == null){
			_gui = new GUI();
		}
		return _gui;
	}
	public JFrame getFrame(){
		return _frame;
	}
	public Container getContentPane(){
		return _frame.getContentPane();
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent arg0) {
		Lists.getInstance().writeAllStats();
	}
	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent e) {
		
	}
		

}
