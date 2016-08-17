package spellingAid;

import java.awt.Container;

import javax.swing.JFrame;

public class GUI {
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
		

}
