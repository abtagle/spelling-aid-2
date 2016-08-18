package spellingAid;

public class NewQuiz extends Quiz {
	public NewQuiz(WordList wordlist, String name) {
		super(wordlist, name);
	}

	@Override
	protected void spellAloud(String word) {
		_wordNumberInt++;
		_attemptNumber = 1;
	}

}
