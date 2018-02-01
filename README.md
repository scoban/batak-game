# PROJECT DOCUMENTATIONS

	This documentation explains game rules, user stories and technical design decision that is applied during development of the game application.  

## UML Diagrams

## User Stories

1. A user can connect to game server.
	
	Client GUI prompts server ip address, user's name. If a user name is valid, and not taken by another user, server returns a valid message
	Otherwise, server return user to enter different name 
	
	GameException
		- user name is choosen by another player
		- Game IP and Port number is not correct
		
2. A user can disconnect from game server
	
3. A user can leave a game.

4. A user can send a message to other player in the same game

5. A user can create a game up to 4 players

6. A user can enter a game.

7. A user guess a number which gives right to user to specify trump card in game turn

8. A user can draw a card.

	User sends a action message to the server. Server validates and operate this message and the final status will 
	broadcast to the all user connected to server.
	
	GameException
		- user can send a card that is not belongs to user
		- user should sends an card whose value is lower than the card which is top of the discarded cards if user has greater value 

9. A user can change his/her mates.
		
## Game Rules

	This section explains game rules and available actions in a game 
	
	1. To start a game, four player should be exist in a game.
	2. The game can be played both pairs and individuals.
	3. Each user initially has 13 cards, and these cards are distributed randomly to each user.
	4. Other rule that it is required to start a game, trump card should be specified. In order to specify a trump card,
	   each user tries to guess a number which she/he needs to gain in a game episode. The user who say max number has the right to
	   specify trump card.
	5. The game turn is ordered by counter clock-wise.
	6. The user draw card first if he receives cards in a game turn.
	7. At the beginning of game, the player who draw a card first, he starts the game turn.
	8. The game will be ended if there is no card that player has
	9. Score is incremental. So, if a user receive equal or greater turns as guessed at the beginning of game, receive points as much winning turns.
	   Otherwise, the loser user pairs or individuals receives negative point equal to guessed number.

	10. The game continues until a predefined winning points.
	
	
## Technical implementations and Design Decisions

	- Every user has a related thread in game server. So that each user receives its proper cards.
	- Once an action operated in game server, all thread receives actions