package tr.cobanse.batak.common;

import java.util.List;

public interface IBatakGame {

	public Player getClaimer();
	
	public void setClaimer(Player claimer);
	
	public Symbol getTrumpSymbol();
	
	public void setTrumpSymbol(Symbol trumpSymbol);
	
	public int getGuessWinnerPoint() ;
	
	public void setGuessWinnerPoint(int guessWinnerPoint) ;
	
	public void updatePlayerInTurn();
	
	public void addGameRound(Card card, Player player);
	
	public List<GameRound> getCurrentGameRound();
}
