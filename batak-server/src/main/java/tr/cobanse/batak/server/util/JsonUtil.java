package tr.cobanse.batak.server.util;

import com.google.gson.Gson;

public class JsonUtil {

	private static Gson gson = new Gson();
	
	public static String toJson(Object object) {
		return gson.toJson(object);
	}
}
