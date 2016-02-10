package shopping;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import exceptions.CartIsEmptyException;
import exceptions.CartIsNullException;
import exceptions.CheckException;

public class ShoppingCartServiceImpl {

	CatalogService catalogService;
	
	public CatalogService getCatalogService(){
		return catalogService;
	}
	
	public void setCatalogService(CatalogService catalogService){
		this.catalogService = catalogService;
	}
	
	public FinalCartDTO getCartItems(Map<Long,Integer> map) throws CheckException, CartIsNullException, CartIsEmptyException{
		if(map == null){
			throw new CartIsNullException();
		}
		if(map.keySet().size()==0){
			throw new CartIsEmptyException();
		}
		final ArrayList<ShoppingCartItemDTO> carts = new ArrayList<ShoppingCartItemDTO>();
		double total=0.0;
		FinalCartDTO finalCart = new FinalCartDTO();
		finalCart.setCartItems(carts);
		
		final Iterator<Long> it = map.keySet().iterator();
		while (it.hasNext()) {
			final Long key = it.next();
			
			final Integer value = map.get(key);
			
			ShoppingCartItemDTO ciDTO ;
			ItemDTO itemDTO = catalogService.findItem(key);
			
			// convert catalog item to cart item
			ciDTO = new ShoppingCartItemDTO(itemDTO.getId(),
											itemDTO.getName(),
											itemDTO.getProduct().getDescription(),
											value.intValue(),
											itemDTO.getUnitCost());
			
			carts.add(ciDTO);
			total = total + (value.doubleValue() * itemDTO.getUnitCost());
		}
		finalCart.setTotal(total);
		
		return finalCart;
	}

}
