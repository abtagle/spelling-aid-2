package spellingAid;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Lists {
	public static final String WORDLIST = "wordlist";
	public static final String MASTERED = ".mastered";
	public static final String FAULTED = ".faulted";
	public static final String FAILED = ".failed";
	public static final String LAST_FAILED = ".lastFailed";
	private WordList _wordList;
	private WordList _mastered;
	private WordList _faulted;
	private WordList _failed;
	private WordList _lastFailed;
	private static Lists _thisList = null;
	
	private Lists(){
		_thisList = this;
		_wordList = readInFile(WORDLIST);
		_mastered = readInFile(MASTERED);
		_faulted = readInFile(FAULTED);
		_failed = readInFile(FAILED);
		_lastFailed = readInFile(LAST_FAILED);
	}
	protected static Lists getInstance(){
		if (_thisList == null){
			return new Lists();
		} else{
			return _thisList;
		}
	}
	private WordList readInFile(String filename){
		WordList words = new WordList();
		File wordList = new File(filename);
		if(wordList.exists() || filename.equals(WORDLIST)){
			try{
				BufferedReader wordListRead = new BufferedReader(new FileReader(wordList));
				String word;
				while((word = wordListRead.readLine()) != null){
					words.addWord(word);
				}
				wordListRead.close();		

			} catch (FileNotFoundException e){
				JOptionPane.showMessageDialog(null, "Error: unable to load " + filename + ".");

			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Error: unable to read from word list " + filename + ".");
			}
		}
		return words;
	}
	
	public WordList getWordList(){
		return _wordList;
	}
	public WordList getMastered(){
		return _mastered;
	}
	public WordList getFaulted(){
		return _faulted;
	}
	public WordList getFailed(){
		return _failed;
	}
	public WordList getLastFailed(){
		return _lastFailed;
	}
}
