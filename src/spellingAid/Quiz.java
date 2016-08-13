package spellingAid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.swing.JOptionPane;

public abstract class Quiz {
	private String _name;
	private String _wordlist = null;
	public Quiz(String wordlist){
		
		_wordlist = wordlist;
	}
	
	public void startQuiz(){
		ProcessBuilder quiz = new ProcessBuilder("/bin/bash", "source QuizScript.sh", getWordlist());
		Process p;
		try {
			p = quiz.start();
			InputStream stdout = p.getInputStream(); 
	        InputStream stderr = p.getErrorStream();
	        BufferedReader stdoutBuffered = new BufferedReader(new InputStreamReader(stdout));
	        BufferedReader stderrBuffered = new BufferedReader(new InputStreamReader(stderr));
	        OutputStream stdin = p.getOutputStream();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error: unable to start " + getName());
			e.printStackTrace();
		}
		
	}
	public String getWordlist(){
		return _wordlist;
	}

	public String getName() {
		return _name;
	}

	public void setName(String _name) {
		this._name = _name;
	}
}
