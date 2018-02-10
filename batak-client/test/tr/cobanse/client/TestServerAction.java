package tr.cobanse.client;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import tr.cobanse.batak.common.Card;
import tr.cobanse.batak.common.CardType;
import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.RequestType;
import tr.cobanse.batak.common.Symbol;

public class TestServerAction {

	@Test
	public void testDiscardAction() {
		Card card = new Card(Symbol.SINEK, CardType.AS);
		Gson gson = new Gson();
		RequestMessage message = new RequestMessage(card, "selami", RequestType.DISCARD);
		String json = gson.toJson(message);
		Assert.assertNotNull("cannot create json object", json);
		Assert.assertFalse("cannot create json object", json.isEmpty());
		System.out.println(json);
	}
	
}
