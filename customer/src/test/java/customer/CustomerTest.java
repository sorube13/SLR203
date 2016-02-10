package customer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exception.InvalidPasswordException;

public class CustomerTest {
	
	private Customer customer;
	
	@Before
	public void setUp(){
		customer = new Customer(1, "123");
	}
	
	
	@Test(expected = InvalidPasswordException.class)
	public void verifyMatchPasswordWhenPasswordNull() throws InvalidPasswordException{
		customer.matchPassword(null);
	}
	
	@Test(expected = InvalidPasswordException.class)
	public void verifyMatchPasswordWhenPasswordEmptyChain() throws InvalidPasswordException{
		customer.matchPassword("");
	}
	
	@Test
	public void verifyMatchPasswordWhenPasswordWrong() throws InvalidPasswordException{
		boolean result = customer.matchPassword("456");
		assertFalse(result);
	}
	
	@Test
	public void verifyMatchPasswordWhenPasswordRight() throws InvalidPasswordException{
		boolean result = customer.matchPassword("123");
		assertTrue(result);		
	}
	
	

}
