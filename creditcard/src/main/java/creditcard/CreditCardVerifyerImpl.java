package creditcard;

import exceptions.NumberFormatException;

public class CreditCardVerifyerImpl {
	
	public enum Result {
	    OK, KO;
	}

	
	public Result verifyCard(String creditCardNumber) throws NumberFormatException{
		int sum = 0;
        boolean alternate = false;
        creditCardNumber = creditCardNumber.replaceAll("\\s+","");
        for (int i = creditCardNumber.length() - 1; i >= 0; i--){
	        int n = Integer.parseInt(creditCardNumber.substring(i, i + 1));
	        if (alternate){
	            n *= 2;
	            if (n > 9){
	            	n = (n % 10) + 1;
	            }
	        }
	        sum += n;
	        alternate = !alternate;
        }	
		if(sum % 10 == 0)
			return Result.OK;
		else
			return Result.KO;
	}
}
