import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class CardGameController
{
	public static final int COMPUTER = 0;
	public static final int HUMAN = 1;
	private CardGameFramework 	model;
	private CardTable			view;
	private Card arenaComputerCard, arenaHumanCard;
	Integer[] scores;

	/*
		CardGameController(CardGameFramework,CardTable)
		Constructor for CardGameController class
		Sets everything up to start the game
	*/
	public CardGameController(CardGameFramework model, CardTable view)
	{
		if (model == null || view == null)
			return;
		this.model = model;
		this.view = view;
		this.arenaComputerCard = null;
		this.arenaHumanCard = null;		

		initialSetup();
		buttonSetup();
		updatePlayers();
		updateGameInfo();
	}

	/*
		start()
		starts the game and the threads
	*/
	public void start()
	{
		while(model.getHand(HUMAN).getNumCards() > -1)
		{
			try
			{
				Thread.sleep(50);
			} 
			catch(InterruptedException e){}
		}
		endGame();
	}
	/*
		endGame()
		display the score at the end of the game and clear the panels in the frame. 
	*/
	public void endGame()
	{
		String winningText = "";

		if (scores[HUMAN] > scores[COMPUTER])
			winningText = "YOU WIN! [SCORE: " + scores[HUMAN] +"]";
		else if (scores[COMPUTER] > scores[HUMAN])
			winningText = "COMPUTER WINS! [SCORE: " + scores[COMPUTER] +"]";
		else
			winningText = "TIE GAME! [SCORE: " + scores[HUMAN] +"]";
		view.demolishGame(winningText);
	}
	
	/*
		updateArena()
		Updates card for display in the play (arena) area
	*/
	public void updateArena()
	{
		view.updateArenaIcon(COMPUTER, GUICard.getIcon(this.arenaComputerCard));
		view.updateArenaIcon(HUMAN, GUICard.getIcon(this.arenaHumanCard));
	}

	/*
		updateGameInfo()
		updates score in the score panels after each play. 
	*/
	public void updateGameInfo()
	{
		view.updateHumanScore("   "+ scores[HUMAN].toString() + "   ");
		view.updateComputerScore("   " + scores[COMPUTER].toString() + "   ");
	}

	/*
		updatePlayers()
		method that upates the icons for the hand after each play
	*/
	public void updatePlayers()
	{
		for (int i = 0; i < this.model.getNumCardsPerHand(); ++i){
			if (i <= this.model.getHand(COMPUTER).getNumCards())
				this.view.updateBackIcon(i, GUICard.getBackCardIcon());
			else
				this.view.updateBackIcon(i,null);

			if (i <= this.model.getHand(HUMAN).getNumCards())
				this.view.updateCardIcon(i, GUICard.getIcon(this.model.getHand(HUMAN).inspectCard(i)));
			else
				this.view.updateCardIcon(i, null);
		}
	}
	
	/*
		buttonSetup()
		adds Listeners to the Timer and Play buttons,
		2 seperate threads
	*/
	public void buttonSetup()
	{
		//Model existence check needed
		this.view.addTimerListener(new TimerListener());
		this.view.addPlayListener(new PlayGameListener());
	}

	/*
		initialSetup()
		method used in the constructor to reset hands and scores. 
	*/
	private void initialSetup()
	{
		// other setup stuff
		model.deal();
		this.scores = new Integer[model.getNumPlayers()];
		this.scores[HUMAN] = 0;
		this.scores[COMPUTER] = 0;
	}

	/*
		addToScore(int,int)
		updates score for Human or Computer
	*/
	public void addToScore(int player, int points)
	{
		this.scores[player] += points;
	}

	/*
		PlayGameListener CLASS
		used to control game and game logic
		sorts the cards to play the highest card in the hand
		included logic to reset hand when hand is empty
	*/
	class PlayGameListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			model.sortHands();
			
			arenaHumanCard = model.playCard(HUMAN,0);
			arenaComputerCard = model.playCard(COMPUTER,0);
			
			//	game logic
			if(arenaComputerCard.getValue().asInt() < arenaHumanCard.getValue().asInt())
			{
				addToScore(HUMAN,1);
			}
			else if(arenaComputerCard.getValue().asInt() > arenaHumanCard.getValue().asInt())
			{
				addToScore(COMPUTER,1);
			}
			
			// if the Hand is empty, refill the hand
			if(model.getHand(HUMAN).getNumCards() == -1 || model.getHand(COMPUTER).getNumCards() == -1)
			{
				model.deal();
			}

			updateArena();
			updatePlayers();
			updateGameInfo();
		}
	}
	
	/*
		TimerListener CLASS
		used to control Timer
	*/
	class TimerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			view.toggleTimer();

			//THIS IS WHERE THE TIMER BUTTON FIRES
			//USE VEW.UPDATETIMER(STRING) TO UPDATE THE TIMER STRING
		}
	}
}// end class