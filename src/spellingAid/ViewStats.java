package spellingAid;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ViewStats {
	
	final static boolean shouldFill = true;
	final static boolean shouldWeightX = true;
	final static int COLUMNS = 4;
	private TreeSet<String> _testedWordsSet = null;
	
	public ViewStats(){
		_testedWordsSet = new TreeSet<String>();
		_testedWordsSet.addAll(Lists.getInstance().getFailed().returnArrayList());
		_testedWordsSet.addAll(Lists.getInstance().getFaulted().returnArrayList());
		_testedWordsSet.addAll(Lists.getInstance().getFaulted().returnArrayList());
		addComponentsToPane();
	}
	private void addComponentsToPane(){
		GUI.getInstance().getContentPane().setVisible(false);
		GUI.getInstance().getContentPane().removeAll();
		GUI.getInstance().getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		if (shouldFill) {
			//natural height, maximum width
			c.fill = GridBagConstraints.HORIZONTAL;
		}
		if (shouldWeightX) {
			c.weightx = 0.5;
		}
		title();
		table();
		button();
		GUI.getInstance().getContentPane().setVisible(true);
	}
	private void title(){
		GridBagConstraints c = new GridBagConstraints();
		JLabel welcomeText = new JLabel();
		welcomeText.setText("Statistics");
		welcomeText.setFont(GUI.TITLE_FONT);
		welcomeText.setHorizontalAlignment(JLabel.CENTER);
		welcomeText.setVerticalAlignment(JLabel.CENTER);
		c.gridheight = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 50;
		c.gridx = 0;
		c.gridy = 0;
		GUI.getInstance().getContentPane().add(welcomeText, c);
	}
	private void table(){
		GridBagConstraints c = new GridBagConstraints();
		c.gridheight = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 50;
		c.gridx = 50;
		c.gridy = 1;
		String[] columnNames = {"Word", "Mastered Frequency", "Faulted Frequency", "Failed Frequency"};
		String[][] statisticsArray = new String[_testedWordsSet.size()][COLUMNS];
		int row = 0;
		for(String i :_testedWordsSet){
			statisticsArray[row][0] = i;
			statisticsArray[row][1] = ""+Lists.getInstance().getMastered().frequencyOf(i);
			statisticsArray[row][2] = ""+Lists.getInstance().getFaulted().frequencyOf(i);
			statisticsArray[row][3] = ""+Lists.getInstance().getFailed().frequencyOf(i);
			row++;
		}
		TableModel statisticsModel = new DefaultTableModel(statisticsArray, columnNames);
		//From: http://stackoverflow.com/questions/1990817/how-to-make-a-jtable-non-editable
		JTable statisticsTable = new JTable(statisticsModel){
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		GUI.getInstance().getContentPane().add(statisticsTable, c);
	}
	private void button(){
		GridBagConstraints c = new GridBagConstraints();
		JButton returnHome = new JButton("Return Home");
		c.gridheight = 1;
		c.ipadx = 10;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		GUI.getInstance().getContentPane().add(returnHome, c);
		returnHome.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				GUI.getInstance().getFrame().setVisible(false);
				GUI.getInstance().getContentPane().removeAll();
				new WelcomeScreen();
			}
		});
	}
}
