package tr.cobanse.batak.server.action;

import tr.cobanse.batak.common.RequestType;

public class ClientActionFactory {
	public static PlayerAction createAction(RequestType requestType) {
		switch (requestType) {
		case CHAT:
			return new SendMessage();
		case DISCARD:
			return new DiscardCard();
		case JOIN:
			return new JoinGame();
		case GUESSNUMBER:
			return new GuessNumber();
		case LEAVE:
			return new LeaveGame();
		case CREATEGAME:
			return new CreateGame();
		case LISTGAME:
			return new ListGame();
		}
		throw new IllegalArgumentException("request type is undefined" + requestType);
	}
}
