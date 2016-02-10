package customer;

import exception.InvalidPasswordException;

public class Customer {
	
	int id;
	String password;
	
	
	public Customer(int id, String password){
		this.id = id;
		this.password = password;
	}
	
	private String getPassword() {
		return password;
	}

	
	public boolean matchPassword(final String password1) throws InvalidPasswordException {
		if (password1 == null || "".equals(password1)){
			throw new InvalidPasswordException();
		};
		// The password entered by the customer is not the same stored in database
		return password1.equals(getPassword());
	}


}
