package tr.cobanse.batak.server.deck;

public class BatakException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

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
