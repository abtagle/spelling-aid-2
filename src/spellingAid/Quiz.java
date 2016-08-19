package spellingAid;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

public abstract class Quiz{
	protected String _name;
	protected boolean _isReview = false;
	protected ArrayList<String> _wordlist = null;
	protected JLabel _wordNumber = null;
	protected JLabel _title = null;
	protected JTextField _spellingBar = null;
	protected JButton _submit = null;
	protected JLabel _correct = null;
	protected int _attemptNumber;
	protected int _wordNumberInt;

	public Quiz(WordList wordlist, String name){
		_name = name;
		_wordlist = wordlist.returnTestlist();
		_wordNumberInt = 1;
		_attemptNumber = 1;
		addComponentsToPane();
		quizQuestion();
	}

	protected void addComponentsToPane() {
		Container pane = GUI.getInstance().getContentPane();
		pane.removeAll();
		pane.setLayout(new GridLayout(5,0));
		_title = new JLabel(_name, JLabel.CENTER);
		pane.add(_title);
		//From: http://stackoverflow.com/questions/2715118/how-to-change-the-size-of-the-font-of-a-jlabel-to-take-the-maximum-size
		_title.setFont(GUI.TITLE_FONT);
		_wordNumber = new JLabel("", JLabel.CENTER);
		pane.add(_wordNumber);
		_spellingBar = new JTextField();
		_spellingBar.setText("Spell words here");
		pane.add(_spellingBar);
		_correct = new JLabel();
		_submit = new JButton("Check Spelling");
		GUI.getInstance().getFrame().getRootPane().setDefaultButton(_submit);
		pane.add(_submit);
		GUI.getInstance().getFrame().repaint();
		_submit.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent event) {
			//Acts as template for spelling aloud functionality as well
			try{
				if( _spellingBar.getText().trim().equals("") == false){
					//make lower case and check for invalid cahracters
					String spelling = _spellingBar.getText().toLowerCase().trim();
					if(containsInvalidCharacters(spelling) == false){
						//first attempt
						if(_attemptNumber == 1){
							if(spelling.equals(_wordlist.get(_wordNumberInt-1).toLowerCase())){
								Lists.getInstance().getMastered().addWord(_wordlist.get(_wordNumberInt-1));
								if(Lists.getInstance().getLastFailed().contains(_wordlist.get(_wordNumberInt-1))){
									Lists.getInstance().getLastFailed().remove(_wordlist.get(_wordNumberInt-1));
								}
								_wordNumberInt++;
								new SayAnything("Correct").doInBackground();
							} else{
								_attemptNumber++;
								new SayAnything("Incorrect. Please try again.").doInBackground();
							}
							quizQuestion();
							//Second attempt- failed or faulted
						} else if (_attemptNumber == 2){
							if(spelling.equals(_wordlist.get(_wordNumberInt-1).toLowerCase())){
								new SayAnything("Correct").doInBackground();
								Lists.getInstance().getFaulted().addWord(_wordlist.get(_wordNumberInt-1));
								if(Lists.getInstance().getLastFailed().contains(_wordlist.get(_wordNumberInt-1))){
									Lists.getInstance().getLastFailed().remove(_wordlist.get(_wordNumberInt-1));
								}
								_attemptNumber = 1;
								_wordNumberInt++;
								quizQuestion();
							} else{
								new SayAnything("Incorrect.").doInBackground();
								Lists.getInstance().getFailed().addWord(_wordlist.get(_wordNumberInt-1));
								if(Lists.getInstance().getLastFailed().contains(_wordlist.get(_wordNumberInt-1))==false){
									Lists.getInstance().getLastFailed().addWord(_wordlist.get(_wordNumberInt-1));
								}
								spellAloud(_wordlist.get(_wordNumberInt-1));
							}
							//Third attempt for review - no change to word status
						} else{
							if(spelling.equals(_wordlist.get(_wordNumberInt-1).toLowerCase())){
								new SayAnything("Correct").doInBackground();
							} else{
								new SayAnything("Incorrect").doInBackground();
							}
							_attemptNumber = 1;
							_wordNumberInt++;
							quizQuestion();
						}
					}else {
						JOptionPane.showMessageDialog(null, "Only alphabetical characters (a-z/A-Z) may be used for spelling.", "Review", JOptionPane.ERROR_MESSAGE);
					}
				} 
			}catch (Exception e){

			}
		}
		});

	}
	protected final void quizQuestion(){
		_spellingBar.setText("");
		//Only quiz if there are words left to quiz
		if(_wordNumberInt <=_wordlist.size()){
			_wordNumber.setText("Spell word " + _wordNumberInt + " of " + _wordlist.size());
			_wordNumber.repaint();
			try {
				SayAnything w = new SayAnything(_wordlist.get(_wordNumberInt-1));
				if(_attemptNumber == 2){
					w.doInBackground();
				}
				w.doInBackground();
			} catch (Exception e) {
				e.printStackTrace();
				_correct.setText("Error saying word");
			}
			//If there are no words left to quiz, go back to main menu
		}else{
			GUI.getInstance().getFrame().setVisible(false);
			GUI.getInstance().getContentPane().removeAll();
			new WelcomeScreen();
		}
	}

	//Returns true if string has characters which are not letters
	protected final boolean containsInvalidCharacters(String word){
		char[] wordArray = word.toCharArray();
		for	(char i : wordArray){
			if(i < 'a' || i > 'z'){
				return true;
			}
		}
		return false;
	}

	//Hook method for spelling aloud implementation
	protected abstract void spellAloud(String word);

	//Says words in background (unnecessary SwingWorker, but implemented before I realised timing issues with festival
	//Hopefully can find a way to adapt this
	class SayAnything extends SwingWorker<Void, Void>{
		private String _word = null;
		private Process _process;
		public SayAnything(String anything){
			_word = anything;
		}

		@Override
		protected Void doInBackground() throws Exception {
			String sayCommand = "echo " + _word + "." + " | festival --tts";
			ProcessBuilder sayBuilder = new ProcessBuilder("/bin/bash", "-c", sayCommand);
			_process = sayBuilder.start();
			if(_wordNumberInt!=1 || _attemptNumber!=1){
				try {
					_process.waitFor();
				} catch (InterruptedException e) {
				}
			}
			return null;
		}

	}
}
