package tr.cobanse.client.card;

import tr.cobanse.batak.common.Card;
import tr.cobanse.batak.common.CardType;
import tr.cobanse.batak.common.Symbol;

public enum CardConstants {
	SINEK2(new Card(Symbol.SINEK,CardType.TWO),CardFileUrlConstants.SINEK2),
	SINEK3(new Card(Symbol.SINEK,CardType.THREE),CardFileUrlConstants.SINEK3),
	SINEK4(new Card(Symbol.SINEK,CardType.FOUR),CardFileUrlConstants.SINEK4),
	SINEK5(new Card(Symbol.SINEK,CardType.FIVE),CardFileUrlConstants.SINEK5),
	SINEK6(new Card(Symbol.SINEK,CardType.SIX),CardFileUrlConstants.SINEK6),
	SINEK7(new Card(Symbol.SINEK,CardType.SEVEN),CardFileUrlConstants.SINEK7),
	SINEK8(new Card(Symbol.SINEK,CardType.EIGHT),CardFileUrlConstants.SINEK8),
	SINEK9(new Card(Symbol.SINEK,CardType.NINE),CardFileUrlConstants.SINEK9),
	SINEK10(new Card(Symbol.SINEK,CardType.TEN),CardFileUrlConstants.SINEK10),
	SINEK11(new Card(Symbol.SINEK,CardType.GIRL),CardFileUrlConstants.SINEK11),
	SINEK12(new Card(Symbol.SINEK,CardType.VALE),CardFileUrlConstants.SINEK12),
	SINEK13(new Card(Symbol.SINEK,CardType.PAPAZ),CardFileUrlConstants.SINEK13),
	SINEK14(new Card(Symbol.SINEK,CardType.AS),CardFileUrlConstants.SINEK14),
	KARO2(new Card(Symbol.KARO,CardType.TWO),CardFileUrlConstants.KARO2),
	KARO3(new Card(Symbol.KARO,CardType.THREE),CardFileUrlConstants.KARO3),
	KARO4(new Card(Symbol.KARO,CardType.FOUR),CardFileUrlConstants.KARO4),
	KARO5(new Card(Symbol.KARO,CardType.FIVE),CardFileUrlConstants.KARO5),
	KARO6(new Card(Symbol.KARO,CardType.SIX),CardFileUrlConstants.KARO6),
	KARO7(new Card(Symbol.KARO,CardType.SEVEN),CardFileUrlConstants.KARO7),
	KARO8(new Card(Symbol.KARO,CardType.EIGHT),CardFileUrlConstants.KARO8),
	KARO9(new Card(Symbol.KARO,CardType.NINE),CardFileUrlConstants.KARO9),
	KARO10(new Card(Symbol.KARO,CardType.TEN),CardFileUrlConstants.KARO10),
	KARO11(new Card(Symbol.KARO,CardType.GIRL),CardFileUrlConstants.KARO11),
	KARO12(new Card(Symbol.KARO,CardType.VALE),CardFileUrlConstants.KARO12),
	KARO13(new Card(Symbol.KARO,CardType.PAPAZ),CardFileUrlConstants.KARO13),
	KARO14(new Card(Symbol.KARO,CardType.AS),CardFileUrlConstants.KARO14),
	MACA2(new Card(Symbol.MACA,CardType.TWO),CardFileUrlConstants.MACA2),
	MACA3(new Card(Symbol.MACA,CardType.THREE),CardFileUrlConstants.MACA3),
	MACA4(new Card(Symbol.MACA,CardType.FOUR),CardFileUrlConstants.MACA4),
	MACA5(new Card(Symbol.MACA,CardType.FIVE),CardFileUrlConstants.MACA5),
	MACA6(new Card(Symbol.MACA,CardType.SIX),CardFileUrlConstants.MACA6),
	MACA7(new Card(Symbol.MACA,CardType.SEVEN),CardFileUrlConstants.KARO7),
	MACA8(new Card(Symbol.MACA,CardType.EIGHT),CardFileUrlConstants.MACA8),
	MACA9(new Card(Symbol.MACA,CardType.NINE),CardFileUrlConstants.MACA9),
	MACA10(new Card(Symbol.MACA,CardType.TEN),CardFileUrlConstants.MACA10),
	MACA11(new Card(Symbol.MACA,CardType.GIRL),CardFileUrlConstants.MACA11),
	MACA12(new Card(Symbol.MACA,CardType.VALE),CardFileUrlConstants.MACA12),
	MACA13(new Card(Symbol.MACA,CardType.PAPAZ),CardFileUrlConstants.MACA13),
	MACA14(new Card(Symbol.MACA,CardType.AS),CardFileUrlConstants.MACA14),
	KUPA2(new Card(Symbol.KUPA,CardType.TWO),CardFileUrlConstants.KUPA2),
	KUPA3(new Card(Symbol.KUPA,CardType.THREE),CardFileUrlConstants.KUPA3),
	KUPA4(new Card(Symbol.KUPA,CardType.FOUR),CardFileUrlConstants.KUPA4),
	KUPA5(new Card(Symbol.KUPA,CardType.FIVE),CardFileUrlConstants.KUPA5),
	KUPA6(new Card(Symbol.KUPA,CardType.SIX),CardFileUrlConstants.KUPA6),
	KUPA7(new Card(Symbol.KUPA,CardType.SEVEN),CardFileUrlConstants.KARO7),
	KUPA8(new Card(Symbol.KUPA,CardType.EIGHT),CardFileUrlConstants.KUPA8),
	KUPA9(new Card(Symbol.KUPA,CardType.NINE),CardFileUrlConstants.KUPA9),
	KUPA10(new Card(Symbol.KUPA,CardType.TEN),CardFileUrlConstants.KUPA10),
	KUPA11(new Card(Symbol.KUPA,CardType.GIRL),CardFileUrlConstants.KUPA11),
	KUPA12(new Card(Symbol.KUPA,CardType.VALE),CardFileUrlConstants.KUPA12),
	KUPA13(new Card(Symbol.KUPA,CardType.PAPAZ),CardFileUrlConstants.KUPA13),
	KUPA14(new Card(Symbol.KUPA,CardType.AS),CardFileUrlConstants.KUPA14);
	
	private Card card;
	private String url;
	
	private CardConstants(Card card, String url) {
		this.card = card;
		this.url = url;
	}
	
	public Card getCard() {
		return card;
	}
	
	public String getUrl() {
		return url;
	}
}
