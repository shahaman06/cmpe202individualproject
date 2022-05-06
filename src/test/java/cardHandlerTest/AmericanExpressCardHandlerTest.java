package cardHandlerTest;

import bean.CardDetails;
import CardValidator.CreditCardValidator;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertTrue;

public class AmericanExpressCardHandlerTest {
	
	protected CardDetails checkCard = new CardDetails();
	
	@Before
    public void setUp() {
		
        checkCard.setCardNumber(Long.parseLong("370000000000000"));
        checkCard.setExpirationDate(new Date(12/43/2021));
        checkCard.setNameOfCardholder("Bob");
    }

    @Test
    public void handleNumberTest() {
    	
    	
    	CreditCardValidator aeh = new CreditCardValidator.AmericanExpressCardValidator();
    	Boolean testValue = aeh.handleNumber(checkCard.getCardNumber());
//    	assertEquals(testValue.getType(), Constants.americanExpress);
        assertTrue(testValue);
    }

}
