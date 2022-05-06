package cardHandlerTest;

import bean.CardDetails;
import CardValidator.CreditCardValidator;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertTrue;

public class MasterCardHandlerTest {

	protected CardDetails checkCard = new CardDetails();
	
	@Before
    public void setUp() {
		
        checkCard.setCardNumber(Long.parseLong("5410000000000000"));
        checkCard.setExpirationDate(new Date(3/20/2030));
        checkCard.setNameOfCardholder("Alice");
    }

    @Test
    public void handleNumberTest() {
    	
    	CreditCardValidator cch = new CreditCardValidator.MasterCardValidator();
    	boolean testValue = cch.handleNumber(checkCard.getCardNumber());
//    	assertEquals(testValue.getType(), Constants.masterCard);
        assertTrue(testValue );
    }
}
