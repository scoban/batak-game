package tr.cobanse.batak.action;

import org.junit.jupiter.api.Test;

import tr.cobanse.batak.server.action.LeaveGame;
import tr.cobanse.batak.server.util.RequestCommandValidator;

public class LeaveGameTest {

	private LeaveGame leaveGame = new LeaveGame(new RequestCommandValidator());
	
	@Test
	public void testLeaveGame() {
//		String gameToBeJoined = requestMessage.getGameId();
//		if(StringUtils.isBlank(gameToBeJoined)) {
//			return new ResponseMessage(GameExceptionMessage.INVALID_GAME_ROOM, ResponseType.ERROR);
//		}
//		List<GameRoom> availableGames = gameContext.listGames();
//		GameRoom gameRoom = availableGames.stream().filter(g->g.getGameId().equals(requestMessage.getGameId())).findFirst().orElse(null);
//		if(gameRoom == null) {
//			return new ResponseMessage(GameExceptionMessage.INVALID_GAME_ROOM, ResponseType.ERROR);
//		}
//		Player existingPlayerName = gameRoom.getPlayers().stream().filter(p->p.getPlayerName().equalsIgnoreCase(requestMessage.getPlayerName())).findFirst().orElse(null);
//		if(existingPlayerName == null) {
//			return new ResponseMessage(GameExceptionMessage.USER_NOT_FOUND, ResponseType.ERROR);
//		}
//		gameRoom.drawPlayer(requestMessage.getPlayerName()); 
//		return new ResponseMessage(MESSAGE, Arrays.asList(gameRoom.getGameId()), new ArrayList<Card>(), gameRoom.getGameId(), 
//				gameRoom.getPlayers().stream().map(Player::getPlayerName).collect(Collectors.toList()), ResponseType.LEAVEGAME);
	}
}
