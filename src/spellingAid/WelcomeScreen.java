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
	private JButton _newQuiz;
	private JButton _review;
	private JButton _viewStats;
	private JButton _clearStats;
	private JButton _quit;
	
	private Lists _allLists = null;

	public WelcomeScreen (Container pane, Lists lists){
		addComponentsToPane(pane);
		
		_allLists = lists;
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
	private void setButtonConstraints(JButton button, GridBagConstraints c, int x, int y, Container pane){
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = WEIGHT;
		c.gridx = x;
		c.gridy = y;
		pane.add(button, c);
	}


	/**
	 * Create the GUI and show it.  For thread safety,
	 * this method should be invoked from the
	 * event-dispatching thread.
	 */

	
	private void welcomeMessage(GridBagConstraints c, Container pane){
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
		_newQuiz = new JButton("New Quiz");
		setButtonConstraints(_newQuiz, c, 1, 0, pane);
		

		_review = new JButton("Review Mistakes");
		setButtonConstraints(_review, c, 1, 1, pane);
		pane.add(_review, c);

		_viewStats = new JButton("View Statistics");
		setButtonConstraints(_viewStats, c, 1, 2, pane);

		_clearStats = new JButton("Clear Statistics");
		setButtonConstraints(_clearStats, c, 1, 3, pane);

		_quit = new JButton("Quit");
		setButtonConstraints(_quit, c, 1, 4, pane);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == _newQuiz){
			new NewQuiz(_allLists.getWordList());
		} else if (event.getSource() == _review){
			
		}
		
	}

}

