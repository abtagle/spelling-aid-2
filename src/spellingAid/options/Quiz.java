package spellingAid.options;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import spellingAid.WordList;

public abstract class Quiz {
	private String _name;
	private ArrayList<String> _wordlist = null;
	
	public Quiz(WordList wordlist){
		_wordlist = wordlist.returnTestlist();
	}
	//Acts as template for spelling aloud functionality
	public void startQuiz(){
		for(String currentWord : _wordlist){
			
		}
		
	}
	
	public String getName() {
		return _name;
	}

	public void setName(String _name) {
		this._name = _name;
	}
	//Hook method for spelling aloud implementation
}
