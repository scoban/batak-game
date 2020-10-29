package tr.cobanse.batak.common;

import java.util.Arrays;
import java.util.List;

public enum Symbol {
	SINEK, KARO, KUPA, MACA, EMPTY; 
	
	public static List<Symbol> asList(){
		return Arrays.asList(Symbol.KUPA,Symbol.KARO,Symbol.MACA,Symbol.SINEK);
	}
}
