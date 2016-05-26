import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.text.*;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class CardTable extends JFrame
{
	static final int WIDTH = 1100;
	static final int HEIGHT = 700;
	private int numCardsPerHand;
	private int numPlayers;
	private JPanel pnlHuman, pnlComputer, pnlArena, pnlGameInfo, pnlTimer, pnlComputerScore, pnlHumanScore;
	private GridLayout gridHuman, gridComputer, gridArena, gridGameInfo, gridTimer;
	private JLabel[] computerLabels;
	private JLabel[] humanLabels;
	private JLabel arenaLeftLabel, arenaRightLabel;
	private JLabel computerScoreLabel, humanScoreLabel;
	private TimerLabel timerLabel;
	TimerButton timerButton;
	PlayButton playButton;

	/*
		CardTable(String,int,int)
		Constructor for CardTable class
		Instantiates JComponent objects for the game

	*/
	public CardTable(String title, int numCardsPerHand, int numPlayers)
	{
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

		gridHuman = new GridLayout(1,numCardsPerHand);
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
	
	/*
		createButtons()
		Creates buttons for timer and to play the card game
	*/
	private void createButtons()
	{
		timerButton = new TimerButton();
		playButton = new PlayButton();	   
		pnlGameInfo.add(playButton);
		pnlTimer.add(timerButton);
	}

	/*
		creatLabels()
		Instantiates the labels for the Computer and Human's hands
		Adds labels for Play Area, Score and Timer
	*/
	private void createLabels()
	{
		computerLabels = new JLabel[numCardsPerHand];
		for (int i = 0; i < numCardsPerHand; ++i)
		{
			computerLabels[i] = new JLabel("", JLabel.CENTER);
			pnlComputer.add(computerLabels[i]);
		}
		
		humanLabels = new JLabel[numCardsPerHand];
		for (int i = 0; i < numCardsPerHand; ++i)
		{
			humanLabels[i]= new JLabel("", JLabel.CENTER);
         	pnlHuman.add(humanLabels[i]);
		}

		arenaLeftLabel = new JLabel("Computer: ", JLabel.CENTER);
		arenaRightLabel = new JLabel(":Human", JLabel.CENTER);
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

	/*
		toggleTimer()
		Controls button to start and stop timer
	*/
	public void toggleTimer() 
	{
		if (this.timerButton.toggleLabel())
			this.timerLabel.startTimer();
		else
			this.timerLabel.stopTimer();
		setVisible(true);
	}

	/*
		updateTImer(String)
		Updates timer text
	*/
	public void updateTimer(String timerText) 
	{
		this.timerLabel.setText(timerText);
		setVisible(true);
	}

	/*
		methods to add ActionListeners to buttons
	*/
	public void addTimerListener(ActionListener action) 
	{
		this.timerButton.addActionListener(action);
	}

	public void addPlayListener(ActionListener action)
	{
		this.playButton.addActionListener(action);
	}
	
	/*
		methods to update appropriate card in Human and Computer hand
	*/
	public void updateBackIcon(int index, Icon image)
	{
		this.computerLabels[index].setIcon(image);
		setVisible(true);
	}

	public void updateCardIcon(int index, Icon image)
	{
		this.humanLabels[index].setIcon(image);
		setVisible(true);
	}

	public void updateArenaIcon(int position, Icon image)
	{
		if(position == CardGameController.COMPUTER)
			arenaLeftLabel.setIcon(image);
		else
			arenaRightLabel.setIcon(image);
		
		setVisible(true);
	}

	/*
		methods to update Score after play
	*/
	public void updateComputerScore(String score)
	{
		this.computerScoreLabel.setText(score);
	}

	public void updateHumanScore(String score)
	{
		this.humanScoreLabel.setText(score);
	}

	/*
		demolishGame(String)
		method to clear the board at the end of the game
	*/
	void demolishGame(String winningText)
	{

		for (int i = 0; i < numCardsPerHand; ++i)
		{
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
}	//	end CardTable Class