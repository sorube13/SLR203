package shopping;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import exceptions.CartIsEmptyException;
import exceptions.CartIsNullException;
import exceptions.CheckException;

public class ShoppingTest {
	
	private ShoppingCartServiceImpl shopping;
	
	@Before
	public void setUp(){
		shopping = new ShoppingCartServiceImpl();
	}
	
	@After
	public void tearDown(){
		shopping = null;
	}
	
	@Test(expected=CartIsNullException.class)
	public void verifyGetCartItemsWhenMapNullResultCartIsNullException() throws CheckException, CartIsNullException, CartIsEmptyException{
		shopping.getCartItems(null);
		
	}
	
	
	@Test(expected=CartIsEmptyException.class)
	public void verifyGetCartItemsWhenMapEmptyResultCartIsEmptyException() throws CheckException, CartIsNullException, CartIsEmptyException{
		Map<Long, Integer> shoppingMap = new HashMap<Long, Integer>();
		shopping.getCartItems(shoppingMap);
	}
	
	@Test
	public void verifyFindItemCalledWhenMapHasElementResultItem() throws CheckException, CartIsNullException, CartIsEmptyException{
		//GIVEN
		CatalogService cMock = mock(CatalogService.class);
		shopping.setCatalogService(cMock);
		Map<Long, Integer> shoppingMap = new HashMap<Long, Integer>();
		
		long key = 5; 
		int value = 2; 
		
		shoppingMap.put(key, value);
		Product p = new Product();
		ItemDTO item = new ItemDTO(key, "item", p, 2.0);
		
		//WHEN
		when(cMock.findItem(key)).thenReturn(item);
		shopping.getCartItems(shoppingMap);
		
		//THEN
		verify(cMock, times(1)).findItem(key);
		
	}
	
	@Test
	public void verifyFinalCartDTOWhenMapHasElementResultNotNull() throws CheckException, CartIsNullException, CartIsEmptyException{
		//GIVEN
		CatalogService cMock = mock(CatalogService.class);
		shopping.setCatalogService(cMock);
		Map<Long, Integer> shoppingMap = new HashMap<Long, Integer>();
		
		long key = 5; 
		int value = 2;
		
		shoppingMap.put(key, value);
		Product p = new Product();
		ItemDTO item = new ItemDTO(key, "item", p, 2.0);
		
		//WHEN
		when(cMock.findItem(key)).thenReturn(item);
		FinalCartDTO cart = shopping.getCartItems(shoppingMap);
		
		//THEN
		assertNotNull(cart);
	}
	
	@Test
	public void verifyFinalCartDTOWhenMapHasThreeElementsResultCartThreeElements() throws CheckException, CartIsNullException, CartIsEmptyException{
		//GIVEN
		CatalogService cMock = mock(CatalogService.class);
		shopping.setCatalogService(cMock);
		Map<Long, Integer> shoppingMap = new HashMap<Long, Integer>();
		
		long key1 = 1; 
		int value1 = 1;
		long key2 = 2; 
		int value2 = 2;
		long key3 = 3; 
		int value3 = 3;
		
		shoppingMap.put(key1, value1);
		shoppingMap.put(key2, value2);
		shoppingMap.put(key3, value3);
		Product p = new Product();
		ItemDTO item1 = new ItemDTO(key1, "item", p, 1.0);
		ItemDTO item2 = new ItemDTO(key2, "item", p, 2.0);
		ItemDTO item3 = new ItemDTO(key3, "item", p, 3.0);
		
		//WHEN
		when(cMock.findItem(key1)).thenReturn(item1);
		when(cMock.findItem(key2)).thenReturn(item2);
		when(cMock.findItem(key3)).thenReturn(item3);
		FinalCartDTO cart = shopping.getCartItems(shoppingMap);
		
		//THEN
		assertEquals(3, cart.getCartSize());
	}

}
