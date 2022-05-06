package cardHandlerTest;

import bean.CardDetails;
import CardValidator.CreditCardValidator;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertTrue;

public class VisaCardHandlerTest {
	
	protected CardDetails checkCard = new CardDetails();
	
	@Before
    public void setUp() {
		
        checkCard.setCardNumber(Long.parseLong("4120000000000"));
        checkCard.setExpirationDate(new Date(4/20/2030));
        checkCard.setNameOfCardholder("Eve");
    }

    @Test
    public void handleNumberTest() {
    	
    	CreditCardValidator cch = new CreditCardValidator.VisaCardValidator();
    	boolean testValue = cch.handleNumber(checkCard.getCardNumber());
//    	assertEquals(testValue.getType(), Constants.visa);
        assertTrue(testValue);
    }

}
