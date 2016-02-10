package com.store.catalog.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.store.catalog.model.Category;
import com.store.catalog.model.Product;
import com.store.catalog.utils.ConstantUtils;

import static com.store.catalog.utils.ConstantUtils.*;

public class ProductDaoTest extends AbstractBaseDaoTestCase {

    
    @Autowired
    private ProductDao productDao ;
    
    @Autowired
    private CategoryDao categoryDao ;    
    
    private Product product = null;
    
    
    @Before
    public void setUp(){
    	loadProduct();
    }
    
    @After
    public void tearDown(){
    	categoryDao = null;
    	productDao = null;
    	product = null;
    }

    @Test
    public void testCreateProduct() throws Exception {
        productDao.save(product);
        assertTrue("primary key assigned", product.getId() != null);
    }    
   
    @Test
    public void testUpdateProduct() throws Exception {
        productDao.save(product);
        
        product.setName(ConstantUtils.PRODUCT_NAME + "MDF");
        product.setDescription(ConstantUtils.PRODUCT_DESCRIPTION + "MDF");
        product.setCategory(getCategory());
        
        productDao.save(product);
        
        Product prodMDF = productDao.findOne(product.getId());
        assertEquals(product, prodMDF);
    }    
    
    
    @Test
    public void testGetProduct() throws Exception {
        productDao.save(product);
        
        Product prod = productDao.findOne(product.getId());
        
        assertNotNull(prod);
        assertEquals(product,prod);
    }   

    
    @Test
    public void testRemoveProduct() throws Exception {
        productDao.save(product);
        
        Product prod = productDao.findOne(product.getId());
        
        assertNotNull(prod.getId());
        assertEquals(product,prod);
        
        productDao.delete(product.getId());
        
        assertTrue(getIterableSize(productDao.findAll()) == 0);
    }

    
    
    @Test
    public void testGetProducts() throws Exception {
        productDao.save(product);
        
        assertTrue(getIterableSize(productDao.findAll()) == 1);
        
        Product prod2 = new Product();
        
        prod2.setId(new Random().nextLong());
        prod2.setName(ConstantUtils.PRODUCT_NAME + "2");
        prod2.setDescription(ConstantUtils.PRODUCT_DESCRIPTION + "2");
        prod2.setCategory(getCategory());
        
        productDao.save(prod2);
        
        assertTrue(getIterableSize(productDao.findAll()) == 2);
    }    
    

    @Test
    public void testGetProductsWithCategoryId() throws Exception {
    	productDao.save(product);
        
        assertTrue(getIterableSize(productDao.findAll()) == 1);
        
        Product prod2 = new Product();
        
        prod2.setId(new Random().nextLong());
        prod2.setName(ConstantUtils.PRODUCT_NAME + "2");
        prod2.setDescription(ConstantUtils.PRODUCT_DESCRIPTION + "2");
        prod2.setCategory(product.getCategory());
        
        productDao.save(prod2);
        
        Product prod3 = new Product();
        
        prod3.setId(new Random().nextLong());
        prod3.setName(ConstantUtils.PRODUCT_NAME + "3");
        prod3.setDescription(ConstantUtils.PRODUCT_DESCRIPTION + "3");
        prod3.setCategory(getCategory2());
        
        productDao.save(prod3);
        
        assertTrue(getIterableSize(productDao.findByCategoryId(product.getCategory().getId())) == 2);
    }    

    
    @Test
    public void testGetProductsByCategoryName() throws Exception {
    	productDao.save(product);
        
        assertTrue(getIterableSize(productDao.findAll()) == 1);
        
        Product prod2 = new Product();
        
        prod2.setId(new Random().nextLong());
        prod2.setName(ConstantUtils.PRODUCT_NAME + "2");
        prod2.setDescription(ConstantUtils.PRODUCT_DESCRIPTION + "2");
        prod2.setCategory(product.getCategory());
        
        productDao.save(prod2);
        
        Product prod3 = new Product();
        
        prod3.setId(new Random().nextLong());
        prod3.setName(ConstantUtils.PRODUCT_NAME + "3");
        prod3.setDescription(ConstantUtils.PRODUCT_DESCRIPTION + "3");
        prod3.setCategory(getCategory2());
        
        productDao.save(prod3);
        
        assertTrue(getIterableSize(productDao.findByCategoryName(product.getCategory().getName())) == 2);
    }        
	
	
	private Category getCategory() {
		Category category = new Category();
		category.setId(new Random().nextLong());
        category.setName(CATEGOY_NAME);
        category.setDescription(CATEGORY_DESCRIPTION);

        categoryDao.save(category);
		return category;
	}    
	
	private Category getCategory2() {
		Category category = new Category();
		category.setId(new Random().nextLong());
        category.setName("catName2");
        category.setDescription("description2");

        categoryDao.save(category);
		return category;
	}    
    
	
    /**
     * 
     * create an instanciated object. The one declared as private field in the test class
     */	
	private void loadProduct() {
    	product = new Product();
    	product.setId(new Random().nextLong());
    	product.setName(PRODUCT_NAME);
    	product.setDescription(PRODUCT_DESCRIPTION);
    	product.setCategory(getCategory());
	}
	
}
