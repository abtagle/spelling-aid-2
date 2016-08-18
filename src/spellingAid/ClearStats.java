package spellingAid;

import javax.swing.JOptionPane;

public class ClearStats {
	public ClearStats(){
		//From http://stackoverflow.com/questions/8396870/joptionpane-yes-or-no-window
		int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to CLEAR ALL STATISTICS?", "Confirm Clear Statistics", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
        	Lists.getInstance().clearStats();
        	JOptionPane.showMessageDialog(null, "Statistics have been cleared.", "Clear Statistics", JOptionPane.INFORMATION_MESSAGE);
        }
	}
}
