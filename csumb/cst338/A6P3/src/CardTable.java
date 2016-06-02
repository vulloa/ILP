import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.text.*;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class CardTable extends JFrame{
	static final int WIDTH = 1100;
	static final int HEIGHT = 700;
	private int numCardsPerHand;
	private int numPlayers;
	private JPanel pnlHuman, pnlComputer, pnlArena, pnlGameInfo, pnlTimer, pnlComputerScore, pnlHumanScore;
	private GridLayout gridHuman, gridComputer, gridArena, gridGameInfo, gridTimer;
	private JLabel[] computerLabels;
	private JLabel arenaLeftLabel, arenaRightLabel;
	private JLabel computerScoreLabel, humanScoreLabel;
	private TimerLabel timerLabel;
	TimerButton timerButton;
	CardButton[] cardButtons;
	LeftRightButton[] leftRightButtons;
	DoNothingButton doNothingButton;


	public CardTable(String title, int numCardsPerHand, int numPlayers){
		super(title);
		this.numPlayers = numPlayers;
		this.numCardsPerHand = numCardsPerHand;


		setSize(WIDTH,HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		TitledBorder computerTitle, arenaTitle, humanTitle, gameInfoTitle, timerTitle, computerScoreTitle, humanScoreTitle;
		computerTitle = BorderFactory.createTitledBorder("Computer");
		arenaTitle = BorderFactory.createTitledBorder("Arena");
		humanTitle = BorderFactory.createTitledBorder("You");
		gameInfoTitle = BorderFactory.createTitledBorder("Game Info");
		timerTitle = BorderFactory.createTitledBorder("Timer");
		computerScoreTitle  = BorderFactory.createTitledBorder("Computer Score");
		humanScoreTitle = BorderFactory.createTitledBorder("Your Score");

		gridHuman = new GridLayout(2,numCardsPerHand);
		gridComputer = new GridLayout(1,numCardsPerHand);
		gridArena = new GridLayout(1,2);
		gridGameInfo = new GridLayout(3,1);
		gridTimer = new GridLayout(2,1);

		pnlComputer = new JPanel();
		pnlComputer.setLayout(gridComputer);
		pnlComputer.setBorder(computerTitle);
		add(pnlComputer, BorderLayout.NORTH);

		pnlArena = new JPanel();
		pnlArena.setLayout(gridArena);
		pnlArena.setBorder(arenaTitle);
		add(pnlArena, BorderLayout.CENTER);

		pnlHuman = new JPanel();
		pnlHuman.setLayout(gridHuman);
		pnlHuman.setBorder(humanTitle);
		add(pnlHuman, BorderLayout.SOUTH);

		pnlTimer = new JPanel();
		pnlTimer.setLayout(gridTimer);
		pnlTimer.setBorder(timerTitle);
		add(pnlTimer, BorderLayout.EAST);

		pnlComputerScore = new JPanel();
		pnlComputerScore.setBorder(computerScoreTitle);

		pnlHumanScore = new JPanel();
		pnlHumanScore.setBorder(humanScoreTitle);

		pnlGameInfo = new JPanel();
		pnlGameInfo.setLayout(gridGameInfo);
		pnlGameInfo.setBorder(gameInfoTitle);
		pnlGameInfo.add(pnlComputerScore);
		pnlGameInfo.add(pnlHumanScore);
		add(pnlGameInfo, BorderLayout.WEST);

		createButtons();
		createLabels();

		setVisible(true);
	}

	private void createButtons(){
		leftRightButtons = new LeftRightButton[numCardsPerHand];
		cardButtons = new CardButton[numCardsPerHand];
		doNothingButton = new DoNothingButton();
		timerButton = new TimerButton();

		for (int i = 0; i < numCardsPerHand; ++i){
			this.cardButtons[i] = new CardButton(i);
			pnlHuman.add(cardButtons[i]);
		}

		for (int i = 0; i < numCardsPerHand; ++i){
			this.leftRightButtons[i] = new LeftRightButton();
			pnlHuman.add(leftRightButtons[i]);
		}

		pnlGameInfo.add(doNothingButton);
		pnlTimer.add(timerButton);
	}

	private void createLabels(){
		computerLabels = new JLabel[numCardsPerHand];
		for (int i = 0; i < numCardsPerHand; ++i){
			computerLabels[i] = new JLabel("", JLabel.CENTER);
			pnlComputer.add(computerLabels[i]);
		}

		arenaLeftLabel = new JLabel("", JLabel.CENTER);
		arenaRightLabel = new JLabel("", JLabel.CENTER);
		pnlArena.add(arenaLeftLabel);
		pnlArena.add(arenaRightLabel);

		computerScoreLabel = new JLabel("", JLabel.CENTER);
		computerScoreLabel.setFont(new Font("Serif", Font.PLAIN, 55));
		pnlComputerScore.add(computerScoreLabel);

		humanScoreLabel = new JLabel("", JLabel.CENTER);
		humanScoreLabel.setFont(new Font("Serif", Font.PLAIN, 55));
		pnlHumanScore.add(humanScoreLabel);

		timerLabel = new TimerLabel();
		timerLabel.setFont(new Font("Serif", Font.PLAIN, 55));
		pnlTimer.add(timerLabel, 0);
	}


	public void toggleTimer() {
		if (this.timerButton.toggleLabel())
			this.timerLabel.startTimer();
		else
			this.timerLabel.stopTimer();
		setVisible(true);
	}

	public void updateTimer(String timerText) {
		this.timerLabel.setText(timerText);
		setVisible(true);
	}

	public void addTimerListener(ActionListener action) {
		this.timerButton.addActionListener(action);
	}


	public void addLeftRightListener(int index, ActionListener action){
		this.leftRightButtons[index].addActionListener(action);
	}

	public void addCardButtonListener(int index, ActionListener action){
		this.cardButtons[index].addActionListener(action);
	}

	public void addDoNothingListener(ActionListener action) {
		this.doNothingButton.addActionListener(action);
	}


	public void toggleLeftRightButton(int index){
		this.leftRightButtons[index].toggleLabel();
		setVisible(true);
	}

	public String getLeftRightButtonLabel(int index){
		return this.leftRightButtons[index].getText().toString();
	}

	public void updateBackIcon(int index, Icon image){
		this.computerLabels[index].setIcon(image);
		setVisible(true);
	}

	public void updateCardIcon(int index, Icon image){
		this.cardButtons[index].setIcon(image);
		setVisible(true);
	}



	public void updateArenaIcon(String position, Icon image){
		position.trim();
		position.toLowerCase();
		if (position.equals("left"))
			arenaLeftLabel.setIcon(image);
		else
			arenaRightLabel.setIcon(image);
		setVisible(true);
	}

	public void updateComputerScore(String score){
		this.computerScoreLabel.setText(score);
	}

	public void updateHumanScore(String score){
		this.humanScoreLabel.setText(score);
	}


	void demolishGame(String winningText){
		for (int i = 0; i < numCardsPerHand; ++i){
			pnlComputer.removeAll();
			pnlHuman.removeAll();
		}
		pnlArena.removeAll();
		pnlGameInfo.removeAll();
		pnlTimer.removeAll();

		JLabel winningLabel = new JLabel(winningText, JLabel.CENTER);
		winningLabel.setFont(new Font("Serif", Font.PLAIN, 55));
		pnlArena.add(winningLabel);
		setVisible(true);
	}
}