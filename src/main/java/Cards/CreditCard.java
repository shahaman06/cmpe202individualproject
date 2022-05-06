package Factory;

import utilities.Constants;

public abstract class CreditCard {

	private long cardNumber;
	private String type;
	private String error;

	public CreditCard(long cardNumber, String type, String error) {
	
		this.cardNumber = cardNumber;
		this.type = type;
		this.error = error;
	}
	
	public long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

    public static class AmericanExpressCard extends CreditCard{

        public AmericanExpressCard(long cardNumber) {

            super(cardNumber, Constants.americanExpress,Constants.noError);
        }
    }

	public static class DiscoverCard extends CreditCard{

		public DiscoverCard(long cardNumber) {

			super(cardNumber,Constants.discover,Constants.noError);
		}
	}

	public static class InvalidCreditCard extends CreditCard{

		public InvalidCreditCard(long ccNumber) {

			super(ccNumber,Constants.invalidCardType,Constants.error);
		}
	}

	public static class MasterCard extends CreditCard{

		public MasterCard(long cardNumber) {

			super(cardNumber,Constants.masterCard,Constants.noError);
		}
	}

	public static class VisaCard extends CreditCard{

		public VisaCard(long cardNumber) {

			super(cardNumber,Constants.visa,Constants.noError);
		}
	}
}
