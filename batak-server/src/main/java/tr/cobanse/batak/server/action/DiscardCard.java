package tr.cobanse.batak.server.action;

import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.ResponseMessage;
import tr.cobanse.batak.server.GameContext;
import tr.cobanse.batak.server.game.controller.GameRuleController;

public class DiscardCard implements RequestCommand {

	private GameRuleController gameRuleController;
	
	@Override
	public ResponseMessage execute(RequestMessage requestMessage, GameContext gameContext) {
		return null;
	}

}
