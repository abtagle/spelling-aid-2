package spellingAid;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class representing a word list
**/
public class WordList {
	private ArrayList<String> _words;
	
	public WordList(){
		_words = new ArrayList<String>();
	}
	
	public void addWord(String word){
		_words.add(word);
	}
	
	public ArrayList<String> returnTestlist(){
		ArrayList<String> shufWords = (ArrayList<String>) _words.clone();
		Collections.shuffle(shufWords);
		ArrayList<String> returnList = new ArrayList();
		int i = 0;
		while(i < 3 && i < shufWords.size()){
			returnList.add(shufWords.get(i));
			i++;
		}
		return returnList;
	}
}
