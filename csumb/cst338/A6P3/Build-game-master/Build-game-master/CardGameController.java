import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class CardGameController{
	public static final int COMPUTER = 0;
	public static final int HUMAN = 1;
	private CardGameFramework 	model;
	private CardTable			view;
	private Card arenaLeftCard, arenaRightCard;
	Integer[] scores;
	int doNothingCount;

	public CardGameController(CardGameFramework model, CardTable view){
		if (model == null || view == null)
			return;
		this.model = model;
		this.view = view;
		this.arenaLeftCard = null;
		this.arenaRightCard = null;

		initialSetup();
		buttonSetup();
		updatePlayers();
		updateArena();
		updateGameInfo();
	}


	public void start(){
		while(model.getNumCardsRemainingInDeck() > 0){
			try{
				Thread.sleep(50);
			} catch(InterruptedException e){}
		}
		endGame();
	}

	public void endGame(){
		String winningText = "";

		if (scores[HUMAN] > scores[COMPUTER])
			winningText = "YOU WIN! [SCORE: " + scores[HUMAN] +"]";
		else if (scores[COMPUTER] > scores[HUMAN])
			winningText = "COMPUTER WINS! [SCORE: " + scores[COMPUTER] +"]";
		else
			winningText = "TIE GAME! [SCORE: " + scores[HUMAN] +"]";
		view.demolishGame(winningText);
	}



	public void updateArena(){
		//needs null card check

		view.updateArenaIcon("left", GUICard.getIcon(this.arenaLeftCard));
		view.updateArenaIcon("right", GUICard.getIcon(this.arenaRightCard));
	}


	public void updateGameInfo(){
		view.updateHumanScore("   "+ scores[HUMAN].toString() + "   ");
		view.updateComputerScore("   " + scores[COMPUTER].toString() + "   ");
	}


	public void updatePlayers(){
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


	public void buttonSetup(){
		//Model existence check needed
		for (int i = 0; i < 7; ++i){
			this.view.addLeftRightListener(i, new LeftRightListener(i));
			this.view.addCardButtonListener(i, new PlayCardListener(i));
		}

		this.view.addDoNothingListener(new DoNothingListener());
		this.view.addTimerListener(new TimerListener());
	}


	private void initialSetup(){
		// other setup stuff
		model.deal();
		this.scores = new Integer[model.getNumPlayers()];
		this.scores[HUMAN] = 0;
		this.scores[COMPUTER] = 0;
		resetDoNothingCount();
		resetArena();
	}



	private Boolean executeTurn(int player, int index, Boolean automate) {
		// check validity of card here
		// play turn
		
		if (automate){
			//automation logic
			for (int i = 0; i <= this.model.getHand(player).getNumCards(); ++i){
				if (checkPlayValidity(player,i,arenaLeftCard.getValue().asInt())) {
					this.arenaLeftCard = model.playCard(player,i);
					this.model.takeCard(player, i);
					resetDoNothingCount();
					return true;
				} else if (checkPlayValidity(player,i,arenaRightCard.getValue().asInt())) {
					this.arenaRightCard = model.playCard(player,i);
					this.model.takeCard(player, i);
					resetDoNothingCount();
					return true;
				} else {
					continue;
				}
			}
			addToScore(HUMAN, 1);
			return true;
		}


		//non automation logic
		if (view.getLeftRightButtonLabel(index).equals("Left")){
			if (checkPlayValidity(player,index,arenaLeftCard.getValue().asInt())){
				this.arenaLeftCard = model.playCard(player,index);
				this.model.takeCard(player, index);
				resetDoNothingCount();
			}
			else
				return false;
		} else {
			if (checkPlayValidity(player,index,arenaRightCard.getValue().asInt())){
				// do shit here
				this.arenaRightCard = model.playCard(player,index);
				this.model.takeCard(player, index);
				resetDoNothingCount();
			}
			else
				return false;
		}
		return true;
	}



	private Boolean checkPlayValidity(int player, int index, int value){
		if (Math.abs( model.getHand(player).inspectCard(index).getValue().asInt() - value) == 1)
			return true;
		return false;
	}


	public void addToScore(int player, int points) {
		this.scores[player]+=points;
		addToDoNothingCount();
	}


	public void addToDoNothingCount(){
		this.doNothingCount++;
		if (this.doNothingCount == 2){
			this.doNothingCount = 0;
			resetArena();
		}
	}

	public void resetDoNothingCount(){
		this.doNothingCount = 0;
	}


	public void resetArena(){
		this.arenaLeftCard = model.getCardFromDeck();
		this.arenaRightCard = model.getCardFromDeck();
	}


	class PlayCardListener implements ActionListener{

		int index;

		public PlayCardListener(int index){
			this.index = index;
		}

		public void actionPerformed(ActionEvent e) {
			if (executeTurn(HUMAN, index, false)) // If the human sucessfully executes his/her turn
				executeTurn(COMPUTER, 0, true); // execute the computer's turn
			else
				return; // save yourself from extra computing

			updateArena();
			updatePlayers();
			updateGameInfo();
		}
	}


	class LeftRightListener implements ActionListener{
		int index;

		public LeftRightListener(int index){
			this.index = index;
		}

		public void actionPerformed(ActionEvent e) {
			view.toggleLeftRightButton(index);
		}
	}


	class DoNothingListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			addToScore(COMPUTER, 1);
			executeTurn(COMPUTER, 0, true);
			updateGameInfo();
			updateArena();
		}
	}

	class TimerListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			view.toggleTimer();

			//THIS IS WHERE THE TIMER BUTTON FIRES
			//USE VEW.UPDATETIMER(STRING) TO UPDATE THE TIMER STRING
 
		}
	}







}