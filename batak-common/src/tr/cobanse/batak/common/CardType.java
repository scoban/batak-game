package tr.cobanse.batak.common;

import java.util.Arrays;
import java.util.List;

public enum CardType {
	ONE(1),TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), GIRL(11), VALE(12), PAPAZ(13), AS(14), NONE(0);
	
	private int value;
	
	private CardType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public static List<CardType> asList(){
		return Arrays.asList(CardType.ONE,CardType.TWO,CardType.THREE,CardType.FOUR,CardType.FIVE,CardType.SIX,CardType.SEVEN,CardType.EIGHT,CardType.NINE,CardType.TEN, CardType.GIRL, CardType.VALE, CardType.PAPAZ, CardType.AS);
	}
}
