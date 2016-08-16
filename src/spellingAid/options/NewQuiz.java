package spellingAid.options;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import spellingAid.WordList;

public class NewQuiz extends Quiz implements ActionListener {
	public NewQuiz(WordList wordlist) {
		super(wordlist);
		setName("New Quiz");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

}
