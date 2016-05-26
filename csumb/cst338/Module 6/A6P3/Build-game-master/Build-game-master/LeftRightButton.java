import javax.swing.JButton;

@SuppressWarnings("serial")
public class LeftRightButton extends JButton{
	Boolean right;
	private final String LEFT = "Left";
	private final String RIGHT = "Right";

	public LeftRightButton(){
		this.setText(LEFT);
		this.right = false;
	}

	public void toggleLabel(){
		if (this.right){
			this.setText(LEFT);
			right = false;
			return;
		}
		this.setText(RIGHT);
		right = true;
	}
} 