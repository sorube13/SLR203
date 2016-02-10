package shopping;

import exceptions.CheckException;

public interface CatalogService {
	
	ItemDTO findItem(Long itemId) throws CheckException;

}
