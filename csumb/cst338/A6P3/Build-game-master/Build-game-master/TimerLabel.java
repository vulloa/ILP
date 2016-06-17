import javax.swing.JLabel;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
class TimerLabel extends JLabel{
	final char DIVIDER = ':';
	Integer minutes;
	Integer seconds;
	Timer timer;
	Thread thread = new Thread();

	public TimerLabel(){
		this.minutes = 0;
		this.seconds = 0;
		updateText();
		this.timer = new Timer(1000, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e){
				//Add minutes logic
				seconds++;
				if (seconds > 60){
					minutes++;
					seconds = 0;
				}
				updateText();
			}});
	}

	public void updateText(){
		String minute = this.minutes.toString();
		String second = "";
		if (this.seconds < 10)
			second = "0" + this.seconds.toString();
		else
			second = this.seconds.toString();

		setText(minute + DIVIDER + second);
		setVisible(true);
	}


	public void startTimer(){
		this.timer.start();
	}

	public void stopTimer(){
		this.timer.stop();
	}








}