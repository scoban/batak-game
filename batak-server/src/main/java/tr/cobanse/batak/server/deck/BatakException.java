package tr.cobanse.batak.server.deck;

public class BatakException extends RuntimeException {

	public BatakException() {
		super();
	}
	
	public BatakException(String message) {
		super(message);
	}

	public BatakException(String message, Throwable e) {
		super(message,e);
	}
}
