package spellingAid;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStream;

public class NewQuiz extends Quiz implements ActionListener {
	public NewQuiz(String wordlist) {
		super(wordlist);
		setName("New Quiz");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		startQuiz();
		
	}

}
