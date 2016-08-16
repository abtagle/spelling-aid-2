package spellingAid;

import javax.swing.JFrame;

public class GUI {
	Lists _allLists;
	public static void main(String[] args) {
		//Schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.		
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GUI();
			}
		});
	}
	public GUI(){
		createAndShowWelcomeScreen();
		
	}
	private void createAndShowWelcomeScreen() {
		//Create and set up the window.
		_allLists = Lists.setLists();
		JFrame frame = new JFrame("Spelling Aid");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Set up the content pane.
		new WelcomeScreen(frame.getContentPane(), _allLists);

		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}


}
