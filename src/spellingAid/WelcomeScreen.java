package spellingAid;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import spellingAid.options.NewQuiz;
public class WelcomeScreen implements ActionListener{

	//GridBagLayout structure from Oracle https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/layout/GridBagLayoutDemoProject/src/layout/GridBagLayoutDemo.java


	final static boolean shouldFill = true;
	final static boolean shouldWeightX = true;
	final static Dimension BUTTON_DIMENSIONS = new Dimension(250,40);
	final static int WEIGHT = 1;

	public WelcomeScreen (Container pane){
		addComponentsToPane(pane);
	}
	
	public void addComponentsToPane(Container pane) {

		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		if (shouldFill) {
			//natural height, maximum width
			c.fill = GridBagConstraints.HORIZONTAL;
		}

		
		if (shouldWeightX) {
			c.weightx = 0.5;
		}
		
		welcomeMessage(c, pane);
		buttons(c, pane);
		
		
		
	}
	private void setButtonConstraints(GridBagConstraints c, int x, int y){
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = WEIGHT;
		c.gridx = x;
		c.gridy = y;
	}


	/**
	 * Create the GUI and show it.  For thread safety,
	 * this method should be invoked from the
	 * event-dispatching thread.
	 */

	
	private static void welcomeMessage(GridBagConstraints c, Container pane){
		JLabel welcomeText = new JLabel();
		welcomeText.setText("Welcome to the Spelling Aid\n");
		welcomeText.setHorizontalAlignment(JLabel.CENTER);
		welcomeText.setVerticalAlignment(JLabel.CENTER);
		c.gridheight = 1;
		c.ipadx = 50;
		c.ipady = 50;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		pane.add(welcomeText, c);
	}
	
	protected void buttons(GridBagConstraints c, Container pane){
		JButton newQuiz = new JButton("New Quiz");
		setButtonConstraints(c, 1, 0);
		newQuiz.setPreferredSize(BUTTON_DIMENSIONS);
		pane.add(newQuiz, c);

		JButton review = new JButton("Review Mistakes");
		setButtonConstraints(c, 1, 1);
		review.setPreferredSize(BUTTON_DIMENSIONS);
		pane.add(review, c);

		JButton viewStats = new JButton("View Statistics");
		setButtonConstraints(c, 1, 2);
		viewStats.setPreferredSize(BUTTON_DIMENSIONS);
		pane.add(viewStats, c);

		JButton clearStats = new JButton("Clear Statistics");
		setButtonConstraints(c, 1, 3);
		clearStats.setPreferredSize(BUTTON_DIMENSIONS);
		pane.add(clearStats, c);

		JButton quit = new JButton("Quit");
		setButtonConstraints(c, 1, 4);
		quit.setPreferredSize(BUTTON_DIMENSIONS);
		pane.add(quit, c);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

