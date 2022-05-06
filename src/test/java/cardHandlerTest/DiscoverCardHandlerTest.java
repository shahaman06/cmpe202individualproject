package cardHandlerTest;

import bean.CardDetails;
import CardValidator.CreditCardValidator;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class DiscoverCardHandlerTest {

	protected CardDetails checkCard = new CardDetails();
	
	@Before
    public void setUp() {
		
        checkCard.setCardNumber(Long.parseLong("6011000000000000"));
        checkCard.setExpirationDate(new Date(6/20/2030));
        checkCard.setNameOfCardholder("Richard");
    }

    @Test
    public void handleNumberTest() {
    	
    	CreditCardValidator aeh = new CreditCardValidator.DiscoverCardValidator();
    	boolean testValue = aeh.handleNumber(checkCard.getCardNumber());
//    	assertEquals(testValue.getType(), Constants.discover);
        assertEquals(true, testValue);
    }
}
