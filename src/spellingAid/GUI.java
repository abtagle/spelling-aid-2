package spellingAid;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GUI {
		public static final String WORDLIST = "wordlist";
		
		public static void main(String[] args) {
			//Schedule a job for the event-dispatching thread:
			//creating and showing this application's GUI.		

			
			try{
				BufferedReader fRead = new BufferedReader(new FileReader(WORDLIST));
				//while(fRead.readLine())
				javax.swing.SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						new GUI();
					}
				});
				
			} catch (FileNotFoundException e){
				JOptionPane.showMessageDialog(null, "Error: unable to load word list.");
				
			}
		}
	
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
