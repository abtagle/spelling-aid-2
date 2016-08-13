package spellingAid;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GUI {
	public GUI(){
		createAndShowWelcomeScreen();
		fileSetup();
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
	private void fileSetup() {
		ProcessBuilder builder = new ProcessBuilder("/bin/bash", "source FileSetup.sh");
		try {
			Process setup = builder.start();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error: unable to setup statistics files");
			//e.printStackTrace();
		}
	}

}
