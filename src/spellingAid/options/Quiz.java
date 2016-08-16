package spellingAid.options;

import java.util.ArrayList;
import spellingAid.WordList;

public abstract class Quiz {
	private String _name;
	private ArrayList<String> _wordlist = null;
	
	public Quiz(WordList wordlist){
		_wordlist = wordlist.returnTestlist();
		startQuiz();
	}
	//Acts as template for spelling aloud functionality
	private void startQuiz(){
		for(String currentWord : _wordlist){
			
		}
		
	}
	
	private String getName() {
		return _name;
	}

	protected void setName(String _name) {
		this._name = _name;
	}
	//Hook method for spelling aloud implementation
	private void spellAloud(String word){
		
	}
}
