package CardValidator;

import bean.CardDetails;


public class ChainValidator {
	
	
	public boolean handleChain(CardDetails cardDetails) {
		
		boolean isValid = false;
		
		CreditCardValidator.AmericanExpressCardValidator ah = new CreditCardValidator.AmericanExpressCardValidator();
		CreditCardValidator.DiscoverCardValidator dh = new CreditCardValidator.DiscoverCardValidator();
		CreditCardValidator.MasterCardValidator mh = new CreditCardValidator.MasterCardValidator();
		CreditCardValidator.VisaCardValidator vh = new CreditCardValidator.VisaCardValidator();
		
		ah.setNext(dh);
		dh.setNext(mh);
		mh.setNext(vh);
		

		isValid = ah.handleNumber(cardDetails.getCardNumber());

		
		return isValid;
	}

}
