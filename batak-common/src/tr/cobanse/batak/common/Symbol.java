package tr.cobanse.batak.common;

import java.util.Arrays;
import java.util.List;

public enum Symbol {
	SINEK, KARO, KALP, MACA, EMPTY; 
	
	public static List<Symbol> asList(){
		return Arrays.asList(Symbol.KALP,Symbol.KARO,Symbol.MACA,Symbol.SINEK);
	}
}
