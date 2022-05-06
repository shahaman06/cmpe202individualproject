package CardValidator;

public interface CreditCardValidator {

	boolean handleNumber(long ccNumber);
	void setNext(CreditCardValidator next);

    class AmericanExpressCardValidator implements CreditCardValidator {

        public CreditCardValidator next = null;

        public boolean handleNumber(long ccNumber) {

            String temp = String.valueOf(ccNumber);
            boolean isValid = false;

            if(temp != null && temp.length() == 15 && temp.charAt(0) == '3' && (temp.charAt(1) == '4' || temp.charAt(1) == '7')){

                isValid = true;
            }
            else if(next != null) {
                isValid= next.handleNumber(ccNumber);
            }
            else {
                new Factory.CreditCard.InvalidCreditCard(ccNumber);
            }

            return isValid;

        }

        public void setNext(CreditCardValidator next) {
            this.next = next;
        }


    }

    class DiscoverCardValidator implements CreditCardValidator {

        public CreditCardValidator next = null;

        public boolean handleNumber(long ccNumber) {

            String temp = String.valueOf(ccNumber);
            Factory.CreditCard card = null;
            boolean isValid = false;

            if(temp != null && temp.length() == 16 && temp.startsWith("6011")){
                card = new Factory.CreditCard.DiscoverCard(ccNumber);
                isValid = true;
            }
            else if(next != null) {
                isValid = next.handleNumber(ccNumber);
            }
            else {
                card = new Factory.CreditCard.InvalidCreditCard(ccNumber);
            }

            return isValid;

        }

        public void setNext(CreditCardValidator next) {
            this.next = next;
        }

    }

    class MasterCardValidator implements CreditCardValidator {

        public CreditCardValidator next = null;

        public boolean handleNumber(long ccNumber) {

            String temp = String.valueOf(ccNumber);
            Factory.CreditCard card = null;
            boolean isValid= false;

            if(temp != null && temp.length() == 16 && temp.charAt(0) == '5' && temp.charAt(1) >= '1' && temp.charAt(1) <= '5'){
                isValid = true;
            }
            else if(next != null) {
                isValid = next.handleNumber(ccNumber);
            }
            else {
                card = new Factory.CreditCard.InvalidCreditCard(ccNumber);
            }

            return isValid;

        }

        public void setNext(CreditCardValidator next) {
            this.next = next;
        }

    }

    class VisaCardValidator implements CreditCardValidator {

        public CreditCardValidator next = null;

        public boolean handleNumber(long ccNumber) {

            boolean isValid = false;
            String temp = String.valueOf(ccNumber);
            Factory.CreditCard card = null;

            if(temp != null && temp.charAt(0) == '4' && (temp.length() == 13 || temp.length() == 16)){
                card = new Factory.CreditCard.VisaCard(ccNumber);
                isValid = true;
            }
            else if(next != null) {
                isValid = next.handleNumber(ccNumber);
            }
            else {
                card = new Factory.CreditCard.InvalidCreditCard(ccNumber);
            }

            return isValid;

        }

        public void setNext(CreditCardValidator next) {
            this.next = next;
        }

    }
}
