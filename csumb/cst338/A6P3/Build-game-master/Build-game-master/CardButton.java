import javax.swing.JButton;

@SuppressWarnings("serial")
public class CardButton extends JButton{
	private int index;

	public CardButton(int index){
		if (index < 0)
			index = 0;
		this.index = index;
	}
}