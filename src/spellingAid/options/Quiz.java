package spellingAid.options;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import spellingAid.GUI;
import spellingAid.Lists;
import spellingAid.WordList;

public abstract class Quiz implements ActionListener{
	private String _name;
	private ArrayList<String> _wordlist = null;
	private JLabel _wordNumber = null;
	private JLabel _title = null;
	private JTextField _spellingBar = null;
	private JButton _submit = null;
	private JLabel _correct = null;
	private String _spelling = null;
	private int _wordNumberInt;

	public Quiz(WordList wordlist, String name){
		_name = name;
		_wordlist = wordlist.returnTestlist();
		_wordNumberInt = 1;
		addComponentsToPane();
		quizQuestion();
	}

	protected void addComponentsToPane() {
		GUI.getInstance().getContentPane().removeAll();
		Container pane = GUI.getInstance().getContentPane();
		pane.setLayout(new GridLayout(5,0));
		_title = new JLabel(_name, JLabel.CENTER);
		pane.add(_title);
		_wordNumber = new JLabel("", JLabel.CENTER);
		pane.add(_wordNumber);
		_spellingBar = new JTextField();
		pane.add(_spellingBar);
		_submit = new JButton("Check Spelling");
		_submit.addActionListener(this);
		pane.add(_submit);
		_correct = new JLabel("", JLabel.CENTER);
		pane.add(_correct);
		GUI.getInstance().getFrame().setVisible(true);
	}

	//Acts as template for spelling aloud functionality
	protected final void quizQuestion(){

		_wordNumber.setText("Spell word " + _wordNumberInt + " of " + _wordlist.size());
		_wordNumber.repaint();
		try {
			SayAnything w = new SayAnything(_wordlist.get(_wordNumberInt-1));
			w.doInBackground();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			_correct.setText("Error saying word");
		}

		/*if(_spelling.equals(currentWord)){
				_wordNumber.setText("Correct");
			}
		 */
		_spelling = null;

	}

	//Hook method for spelling aloud implementation
	protected void spellAloud(String word){

	}

	public void actionPerformed(ActionEvent event) {
		try{
			System.out.println("Work");
			_spelling = _spellingBar.getText();
			_correct.setText(_spelling);
			_correct.repaint();
			if(_spelling.equals(_wordlist.get(_wordNumberInt-1))){
				new SayAnything("Correct").doInBackground();
				Lists.getInstance().getMastered().addWord(_wordlist.get(_wordNumberInt-1));
				_wordNumberInt++;
			} else{
				new SayAnything("Incorrect. Please try again.").doInBackground();
				quizQuestion();
			}

		} catch (Exception e){

		}
	}
	class SayAnything extends SwingWorker<Void, Void>{
		private String _word = null;
		public SayAnything(String anything){
			_word = anything;
		}

		@Override
		protected Void doInBackground() throws Exception {
			String sayCommand = "echo " + _word + " | festival --tts;";
			ProcessBuilder sayBuilder = new ProcessBuilder("/bin/bash", "-c", sayCommand);
			Process say = sayBuilder.start();
			return null;
		}

	}
}
