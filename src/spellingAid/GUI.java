package spellingAid;
import javax.swing.JFrame;

public class GUI {
	public GUI(){
		createAndShowWelcomeScreen();
	}
	private void createAndShowWelcomeScreen() {
		//Create and set up the window.
		JFrame frame = new JFrame("Spelling Aid");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Set up the content pane.
		new WelcomeScreen(frame.getContentPane());

		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}

}
