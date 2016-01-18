package com.store.catalog.dao;

import com.store.catalog.model.Product;
import com.store.catalog.utils.ConstantUtils;
import com.store.catalog.model.Category;
import com.store.catalog.model.Item;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.store.catalog.utils.ConstantUtils.*;

import static org.junit.Assert.*;

import org.elasticsearch.common.recycler.Recycler.C;

public class ItemDaoTest extends AbstractBaseDaoTestCase {
    


	private Item item = null;

    @Autowired
    private ItemDao itemDao ;
    
    @Autowired
    private ProductDao productDao ;
    
    @Autowired
    private CategoryDao categoryDao ;    
   
    @Before
    public void setUp(){
    	loadItem();
    }
    
    @After
    public void tearDown(){
    	categoryDao = null;
    	productDao = null;
    	itemDao  = null;
    	item = null;
    }


    @Test
    public void testCreateItem() throws Exception {
        itemDao.save(item);
        assertTrue("primary key assigned", item.getId() != null);        
    }


    @Test
    public void testUpdateItem() throws Exception {
        itemDao.save(item);
        
        item.setName(ConstantUtils.ITEM_NAME + "MD");
        item.setUnitCost(ConstantUtils.ITEM_PRICE + 5);
        item.setImagePath(ConstantUtils.ITEM_IMAGE_PATH + "MD");
        
        itemDao.save(item);
        
        Item itemMdf = itemDao.findOne(item.getId());
        assertEquals(item, itemMdf);
    }

    @Test
    public void testGetItem() throws Exception {
        itemDao.save(item);
        
        Item item2 = itemDao.findOne(item.getId());
        
        assertNotNull(item2);
        assertEquals(item, item2);
    }


    @Test
    public void testRemoveItem() throws Exception {
        itemDao.save(item);
        
        Item item2 = itemDao.findOne(item.getId());
        
        assertNotNull(item2);
        assertEquals(item, item2);
        
        itemDao.delete(item.getId());
    }


    @Test
    public void testGetItems() throws Exception {
        itemDao.save(item);
        
        Iterable<Item> lst = itemDao.findAll();
        
        assertTrue(getIterableSize(itemDao.findAll()) == 1);
        
        Item item2 = getAnotherItem();
//        item2.setName(ConstantUtils.ITEM_NAME + "2");
//        item2.setUnitCost(ConstantUtils.ITEM_PRICE+ 2);
//        item2.setImagePath(ConstantUtils.ITEM_IMAGE_PATH + "2");
//        item2.setProduct(getProduct());
        
        itemDao.save(item2);
        assertTrue(getIterableSize(itemDao.findAll()) == 2);
    }


    @Test
    public void testGetItemsWithProductId() throws Exception {
    	itemDao.save(item);
        
        Iterable<Item> lst = itemDao.findAll();
        
        assertTrue(getIterableSize(itemDao.findByProductId(item.getProduct().getId())) == 1);
        
        Item item2 = getAnotherItem();
//        item2.setName(ConstantUtils.ITEM_NAME + "2");
//        item2.setUnitCost(ConstantUtils.ITEM_PRICE+ 2);
//        item2.setImagePath(ConstantUtils.ITEM_IMAGE_PATH + "2");
        item2.setProduct(item.getProduct());
        
        itemDao.save(item2);
        assertTrue(getIterableSize(itemDao.findByProductId(item.getProduct().getId())) == 2);
    }


    @Test
    public void testSearchItem() throws Exception {
    	itemDao.save(item);
        
        Iterable<Item> lst = itemDao.findByNameContaining("search");
        
        assertTrue(getIterableSize(itemDao.findAll()) == 1);
        
        Item item2 = new Item();
        item2.setName(ConstantUtils.ITEM_NAME + "search");
        item2.setUnitCost(ConstantUtils.ITEM_PRICE+ 2);
        item2.setImagePath(ConstantUtils.ITEM_IMAGE_PATH + "search");
        item2.setProduct(getProduct());
        
        itemDao.save(item2);
        assertTrue(getIterableSize(itemDao.findByNameContaining("search")) == 1);
    }
    
    
    /**
     * 
     * @return an instanciated object. The one declared as private field in the test class
     */    
	private void loadItem() {
	   	item = new Item();
        item.setName(ITEM_NAME);
        item.setImagePath(ITEM_IMAGE_PATH);
        item.setUnitCost(ITEM_PRICE);
        item.setProduct(getProduct());
	}
	
	
	private Item getAnotherItem() {
        Item item2 = new Item();
        item2.setName(ITEM_NAME + "2");
        item2.setImagePath(ITEM_IMAGE_PATH + "2");
        item2.setUnitCost(ITEM_PRICE + 10d); 
        item2.setProduct(getProduct());

        return item2;
	}	
    
    
	private Product getProduct() {
	   	Product product = new Product();
        product.setName(PRODUCT_NAME);
        product.setDescription(PRODUCT_DESCRIPTION);

        Category category = getCategory();
        product.setCategory(category);

        productDao.save(product);        
        
        return product;
	}    
	
	private Product getProduct(String name, String desc) {
	   	Product product = new Product();
        product.setName(name);
        product.setDescription(desc);

        Category category = getCategory();
        product.setCategory(category);

        productDao.save(product);        
        
        return product;
	}	
	
	private Category getCategory() {
		Category category = new Category();
        category.setName(CATEGOY_NAME);
        category.setDescription(CATEGORY_DESCRIPTION);

        categoryDao.save(category);
		return category;
	}    	
}
