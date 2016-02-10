package shopping;

import java.util.ArrayList;

public class FinalCartDTO {
	
	ArrayList<ShoppingCartItemDTO> carts;
	double total;
	
	public FinalCartDTO(ArrayList<ShoppingCartItemDTO> carts){
		this.carts = carts;
	}
	
	public FinalCartDTO(){}
		
	
	public void setCartItems(ArrayList<ShoppingCartItemDTO> carts) {
		// TODO Auto-generated method stub
		this.carts = carts;
		
	}

	public void setTotal(double total) {
		// TODO Auto-generated method stub
		this.total = total;
	}

	public ArrayList<ShoppingCartItemDTO> getCartItems(){
		return carts;
	}

	public double getTotal(){
		return total;
	}
	
	public int getCartSize(){
		return carts.size();
	}
}
