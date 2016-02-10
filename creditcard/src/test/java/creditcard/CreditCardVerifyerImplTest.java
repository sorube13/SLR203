package creditcard;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class CreditCardVerifyerImplTest {
	
private CreditCardVerifyerImpl creditCard;

	
	@Before
	public void setUp(){
		creditCard = new CreditCardVerifyerImpl();
	}
	
	@After
	public void tearDown(){
		creditCard = null;
	}

	@Test(expected=NumberFormatException.class)
	public void verifyExceptionWhenAAADEResultNumberFormatException() throws exceptions.NumberFormatException {
		creditCard.verifyCard("AAADE");	
	}
	
	@Test(expected=NumberFormatException.class)
	public void verifyExceptionWhenNotNumberOrSpaceResultNumberFormatException() throws exceptions.NumberFormatException {
		creditCard.verifyCard("/*!$%");
	}
	
	@Test
	public void verifyOKWhenNumberIs0ResultOK() throws exceptions.NumberFormatException {
		assertEquals(creditCard.verifyCard("0"), creditcard.CreditCardVerifyerImpl.Result.OK);	
	}
	
	@Test
	public void verifyOKWhenNumberIs34ResultOK() throws exceptions.NumberFormatException {
		assertEquals(creditCard.verifyCard("34"), creditcard.CreditCardVerifyerImpl.Result.OK);
	}
	
	@Test
	public void verifyOKWhenNumberIs042ResultOK() throws exceptions.NumberFormatException {
		assertEquals(creditCard.verifyCard("042"), creditcard.CreditCardVerifyerImpl.Result.OK);
	}
	
	@Test
	public void verifyOKWhenNumberIs972487086ResultOK() throws exceptions.NumberFormatException {
		assertEquals(creditCard.verifyCard("972487086"), creditcard.CreditCardVerifyerImpl.Result.OK);
	}

	@Test
	public void verifyOKWhenNumberIs927487086ResultKO() throws exceptions.NumberFormatException {
		assertEquals(creditCard.verifyCard("927487086"), creditcard.CreditCardVerifyerImpl.Result.KO);
	}
	
	@Test
	public void verifyOKWhenNumberIs6219_745ResultOK() throws exceptions.NumberFormatException {
		assertEquals(creditCard.verifyCard("6219 745"), creditcard.CreditCardVerifyerImpl.Result.OK);
	}
	
	@Test
	public void verifyOKWhenNumberIs219_745ResultKO() throws exceptions.NumberFormatException {
		assertEquals(creditCard.verifyCard("219 745"), creditcard.CreditCardVerifyerImpl.Result.KO);
	}
	
}
