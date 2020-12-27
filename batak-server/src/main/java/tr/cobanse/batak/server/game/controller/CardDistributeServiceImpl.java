package tr.cobanse.batak.server.game.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.cobanse.batak.common.Player;
import tr.cobanse.batak.server.deck.Deck;
import tr.cobanse.batak.server.game.GameRoom;
import tr.cobanse.batak.server.util.JsonUtil;

public class CardDistributeServiceImpl implements CardDistributeService {

	private static final Logger logger = LoggerFactory.getLogger(CardDistributeServiceImpl.class);

	private static final int MAX_PLAYER = 4;
	
	//TODO not yet implemented
	private DistributeSizeType distributeSizeType = DistributeSizeType.FOUR;
	
	@Override
	public void distributeCards(GameRoom gameRoom) {
		logger.debug("{}", JsonUtil.toJson(gameRoom)); 
		List<Player> players = gameRoom.getPlayers();
		Deck deck = new Deck();
		while(deck.remainingCardSize()!=0) {
			players.get(0).drawCard(deck.getCard());
			players.get(1).drawCard(deck.getCard());
			players.get(2).drawCard(deck.getCard());
			players.get(3).drawCard(deck.getCard());
		}
	}
}
