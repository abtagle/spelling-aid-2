package spellingAid;

import spellingAid.Quiz.SayAnything;

public class Review extends Quiz {

	public Review(WordList wordlist, String name) {
		super(wordlist, name);
	}

	protected void spellAloud(String word) {
		char[] wordAsCharArray = _wordlist.get(_wordNumberInt-1).toCharArray();

		try {
			new SayAnything(_wordlist.get(_wordNumberInt-1)+" is spelt.").doInBackground();
			for(char i : wordAsCharArray){
				if(i=='a'){
					new SayAnything("ay").doInBackground();
				} else {
					new SayAnything(i+"").doInBackground();
				}
			}
			new SayAnything(_wordlist.get(_wordNumberInt-1)+".").doInBackground();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

