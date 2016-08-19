package spellingAid;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	public WelcomeScreen (){
		addComponentsToPane();
		GUI.getInstance().getFrame().setVisible(true);
	}

	private void addComponentsToPane() {
		GUI.getInstance().getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		if (shouldFill) {
			//natural height, maximum width
			c.fill = GridBagConstraints.HORIZONTAL;
		}
		if (shouldWeightX) {
			c.weightx = 0.5;
		}
		welcomeMessage(c);
		buttons(c);
	}
	private void setButtonConstraints(JButton button, GridBagConstraints c, int x, int y){
		button.addActionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = WEIGHT;
		c.gridx = x;
		c.gridy = y;
		GUI.getInstance().getContentPane().add(button, c);
	}

	private void welcomeMessage(GridBagConstraints c){
		c.ipadx = 50;
		c.ipady = 50;
		
		c.fill = GridBagConstraints.HORIZONTAL;
		JLabel spellingAid = createJLabel("Spelling Aid");
		
		JLabel welcome = createJLabel("Welcome!");
		c.gridx = 0;
		c.gridy = 1;
		GUI.getInstance().getContentPane().add(welcome, c);
		
		JLabel select = createJLabel("Please select an option.");
		c.gridx = 0;
		c.gridy = 2;
		GUI.getInstance().getContentPane().add(select, c);
		c.gridx = 0;
		c.gridy = 0;
		spellingAid.setFont(GUI.TITLE_FONT);
		GUI.getInstance().getContentPane().add(spellingAid, c);
	}

	private void buttons(GridBagConstraints c){
		_newQuiz = new JButton("New Quiz");
		setButtonConstraints(_newQuiz, c, 1, 0);


		_review = new JButton("Review Mistakes");
		setButtonConstraints(_review, c, 1, 1);

		_viewStats = new JButton("View Statistics");
		setButtonConstraints(_viewStats, c, 1, 2);

		_clearStats = new JButton("Clear Statistics");
		setButtonConstraints(_clearStats, c, 1, 3);
	}

	//Menu listener- new action option object created depending on the option pressed
	@Override
	public void actionPerformed(ActionEvent event) {
		if((JButton)event.getSource() == _newQuiz){
			if(Lists.getInstance().getWordList() == null || Lists.getInstance().getWordList().length() == 0){
				JOptionPane.showMessageDialog(null, "Error: no words for New Quiz loaded. Please restart application and load a file of the correct format.", "New Quiz", JOptionPane.ERROR_MESSAGE);
			} else{
				new Review(Lists.getInstance().getWordList(), "New Quiz");
			}
		} else if (event.getSource() == _review){
			if(Lists.getInstance().getLastFailed().length() == 0){
				JOptionPane.showMessageDialog(null, "There are no words available to review. Please try starting a new quiz.", "Review", JOptionPane.ERROR_MESSAGE);
			} else{
				new Review(Lists.getInstance().getLastFailed(), "Review");
			}
		} else if (event.getSource() == _viewStats){
			new ViewStats();

		} else if (event.getSource() == _clearStats){
			new ClearStats();
		}
	}
	/**
	 * creates JLabel including all formatting
	 * @param text
	 * @return
	 */
	private JLabel createJLabel(String text){
		JLabel label = new JLabel();
		label.setText(text);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		return label;
	}
}

