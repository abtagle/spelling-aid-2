package spellingAid;

import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ViewStats {
	private static final int COLUMNS = 4;
	private TreeSet<String> _testedWordsSet = null;
	private JLabel _title =  new JLabel("Statistics");
	private JScrollPane _stats = null;
	private JButton _back = new JButton("Back to menu");
	
	public ViewStats(){
		_testedWordsSet = new TreeSet<String>();
		_testedWordsSet.addAll(Lists.getInstance().getFailed().returnArrayList());
		_testedWordsSet.addAll(Lists.getInstance().getFaulted().returnArrayList());
		_testedWordsSet.addAll(Lists.getInstance().getMastered().returnArrayList());
		addComponentsToPane();
	}
	private void addComponentsToPane(){
		GUI.getInstance().getContentPane().setVisible(false);
		Container pane = GUI.getInstance().getContentPane();
		pane.removeAll();
		pane.setLayout(new BorderLayout());
		_title.setFont(GUI.TITLE_FONT);
		_title.setHorizontalAlignment(JLabel.CENTER);
		pane.add(_title, BorderLayout.NORTH);	
		_back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				GUI.getInstance().getFrame().setVisible(false);
				GUI.getInstance().getContentPane().removeAll();
				new WelcomeScreen();
			}
		});
        pane.add(_back, BorderLayout.AFTER_LAST_LINE);
        JTable statsTable = table();
        _stats = new JScrollPane(statsTable);
        pane.add(_stats, BorderLayout.CENTER);
        GUI.getInstance().getContentPane().setVisible(true);
	}
	private JTable table(){
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
		return statisticsTable;
	}
}
