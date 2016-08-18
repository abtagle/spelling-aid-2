package spellingAid;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

public abstract class Quiz{
	protected String _name;
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
		GUI.getInstance().getContentPane().removeAll();
		Container pane = GUI.getInstance().getContentPane();
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

			try{
				if( _spellingBar.getText().trim().equals("") == false){
					String spelling = _spellingBar.getText().trim();
					if(_attemptNumber == 1){
						if(spelling.toLowerCase().equals(_wordlist.get(_wordNumberInt-1).toLowerCase())){
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
					} else{
						if(spelling.toLowerCase().equals(_wordlist.get(_wordNumberInt-1).toLowerCase())){
							new SayAnything("Correct").doInBackground();
							Lists.getInstance().getFaulted().addWord(_wordlist.get(_wordNumberInt-1));
							if(Lists.getInstance().getLastFailed().contains(_wordlist.get(_wordNumberInt-1))){
								Lists.getInstance().getLastFailed().remove(_wordlist.get(_wordNumberInt-1));
							}
						} else{
							new SayAnything("Incorrect.").doInBackground();
							Lists.getInstance().getFailed().addWord(_wordlist.get(_wordNumberInt-1));
							if(Lists.getInstance().getLastFailed().contains(_wordlist.get(_wordNumberInt-1))==false){
								Lists.getInstance().getLastFailed().addWord(_wordlist.get(_wordNumberInt-1));
							}
							spellAloud(_wordlist.get(_wordNumberInt-1));
						}
						_attemptNumber = 1;
						_wordNumberInt++;
						quizQuestion();
					}
				}

			} catch (Exception e){

			}
		}
		});

	}

	//Acts as template for spelling aloud functionality
	protected final void quizQuestion(){
		_spellingBar.setText("");
		if(_wordNumberInt <=_wordlist.size()){
			_wordNumber.setText("Spell word " + _wordNumberInt + " of " + _wordlist.size());
			_wordNumber.repaint();
			try {
				SayAnything w = new SayAnything(_wordlist.get(_wordNumberInt-1));
				if(_attemptNumber == 2){
					w.doInBackground();
				} else{
					_correct.setText("");
				}
				w.doInBackground();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				_correct.setText("Error saying word");
			}
		}else{
			GUI.getInstance().getFrame().setVisible(false);
			GUI.getInstance().getContentPane().removeAll();
			new WelcomeScreen();
		}
	}

	//Hook method for spelling aloud implementation
	protected abstract void spellAloud(String word);


	class SayAnything extends SwingWorker<Void, Void>{
		private String _word = null;
		public SayAnything(String anything){
			_word = anything;
		}

		@Override
		protected Void doInBackground() throws Exception {
			String sayCommand = "echo " + _word + "." + " | festival --tts";
			ProcessBuilder sayBuilder = new ProcessBuilder("/bin/bash", "-c", sayCommand);
			Process say = sayBuilder.start();
			if(_wordNumberInt!=1 || _attemptNumber!=1){
				say.waitFor();
			}
			return null;
		}

	}
}
