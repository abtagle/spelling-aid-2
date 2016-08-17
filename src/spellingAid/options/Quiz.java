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
			
	public Quiz(WordList wordlist, String name){
		_name = name;
		GUI.getInstance().getContentPane().removeAll();
		_wordlist = wordlist.returnTestlist();
		addComponentsToPane();
		startQuiz();
	}
	
	protected void addComponentsToPane() {
		Container pane = GUI.getInstance().getContentPane();
		pane.setLayout(new GridLayout(5,0));
		_title = new JLabel(_name, JLabel.CENTER);
		pane.add(_title);
		_wordNumber = new JLabel("", JLabel.CENTER);
		pane.add(_wordNumber);
		_spellingBar = new JTextField();
		pane.add(_spellingBar);
		_submit = new JButton("Check Spelling");
		pane.add(_submit);
		_correct = new JLabel("", JLabel.CENTER);
		pane.add(_correct);
		GUI.getInstance().getFrame().setVisible(true);;
	}
	
	//Acts as template for spelling aloud functionality
	protected final void startQuiz(){
		int wordNumber = 1;
		for(String currentWord : _wordlist){
			_wordNumber.setText("Spell word " + wordNumber + " of " + _wordlist.size());
			/*while(_spelling == null){
				
			}*/
			if(_spelling.equals(currentWord)){
				_wordNumber.setText("Correct");
			}
			_spelling = null;
		}
		
	}
	
	//Hook method for spelling aloud implementation
	protected void spellAloud(String word){
		
	}
	@Override
	public void actionPerformed(ActionEvent event) {
		try{
			_spelling = _spellingBar.getText();
		} catch (NullPointerException e){
			
		}
	}
	class sayWord extends SwingWorker<Void, Void>{
		private String _word = null;
		public sayWord(String word){
			_word = word;
		}

		@Override
		protected Void doInBackground() throws Exception {
			ProcessBuilder sayWordBuilder = new ProcessBuilder("/bin/bash", "echo", _word, "|", "festival", "--tts");
			Process sayWord = sayWordBuilder.start();
			return null;
		}
		
	}
}
