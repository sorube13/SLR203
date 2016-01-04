package com.store.catalog.service.catalog.impl;

import com.store.catalog.model.*;
import com.store.catalog.service.catalog.CatalogService;
import com.store.catalog.common.exception.CheckException;
import com.store.catalog.dao.CategoryDao;
import com.store.catalog.dao.ItemDao;
import com.store.catalog.dao.ProductDao;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public class CatalogServiceImpl implements CatalogService {

	Logger logger = LoggerFactory.getLogger(CatalogServiceImpl.class.getName());



	private CategoryDao categoryDao ;
	

	protected ProductDao productDao ;
	

	private ItemDao itemDao ;
	

	private Mapper dozerMapper;



	public Mapper getDozerMapper() {
		return dozerMapper;
	}

	public void setDozerMapper(Mapper dozerMapper) {
		this.dozerMapper = dozerMapper;
	}

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

    public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public ItemDao getItemDao() {
		return itemDao;
	}

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	



	// ======================================
	// = Product Business methoods =
	// ======================================
	public ProductDTO createProduct(ProductDTO productDto) throws CheckException {
		throw new RuntimeException("not yet implemented");
	}

	@Transactional(readOnly=true)
	public List<ProductDTO> findProducts() {
		throw new RuntimeException("not yet implemented");
	}

	@Transactional(readOnly=true)
	public List<ProductDTO>  findProducts(Long categoryId) throws CheckException {
		throw new RuntimeException("not yet implemented");
	}

	@Transactional(readOnly=true)
	public ProductDTO findProduct(Long productId) throws CheckException {
		throw new RuntimeException("not yet implemented");
	}	
	

	@Transactional(readOnly=true)
 	public List<ProductDTO> findProducts(String categoryName) throws CheckException {
		throw new RuntimeException("not yet implemented");
	}	
	
	@Transactional
	public void updateProduct(ProductDTO productDto) throws CheckException {
		throw new RuntimeException("not yet implemented");
	}

	@Transactional
	public void deleteProduct(Long productId) throws CheckException {
		throw new RuntimeException("not yet implemented");
	}
	
    // ======================================
    // =        Item Business methods       =
    // ======================================	
	
	@Transactional
	public ItemDTO createItem(ItemDTO itemDto) throws CheckException {
		throw new RuntimeException("not yet implemented");
	}

	@Transactional
	public void updateItem(ItemDTO itemDto) throws CheckException {
		throw new RuntimeException("not yet implemented");
	}


	@Transactional
	public void deleteItem(Long itemId) throws CheckException {
		throw new RuntimeException("not yet implemented");
	}

	@Transactional(readOnly=true)
	public List<ItemDTO> findItems() {
		throw new RuntimeException("not yet implemented");
	}

	@Transactional(readOnly=true)
	public List<ItemDTO> findItems(Long productId) throws CheckException {
		throw new RuntimeException("not yet implemented");
	}

	
	@Transactional(readOnly=true)
	public List<ItemDTO> searchItems(String keyword) throws CheckException{
		throw new RuntimeException("not yet implemented");
	}



	@Transactional(readOnly=true)
	public ItemDTO findItem(Long itemId) throws CheckException {
		throw new RuntimeException("not yet implemented");
	}

	
    // ======================================
    // =        Category Business methods       =
    // ======================================	
	
	
	@Transactional
	public CategoryDTO createCategory(CategoryDTO categoryDto) throws CheckException {
		if (categoryDto == null)
            throw new CheckException("Category object is null");
        
		logger.info("start");
		Category category = dozerMapper.map(categoryDto, Category.class);
		
		categoryDao.save(category);
		
		CategoryDTO categoryworkDto = dozerMapper.map(category, CategoryDTO.class);
		
		logger.info("end");
		return categoryworkDto;
	}

	@Transactional
	public void deleteCategory(Long categoryId) throws CheckException {
		if (categoryId == null || "".equals(categoryId))
            throw new CheckException("Invalid id");
        
		categoryDao.delete(categoryId);		
	}

	@Transactional
	public void updateCategory(CategoryDTO categoryDto) throws CheckException {
		if (categoryDto == null)
            throw new CheckException("Category object is null");
        
		if (categoryDto.getId() == null)
            throw new CheckException("Invalid id");


		Category foundCategory =  categoryDao.findOne(categoryDto.getId());

		if(foundCategory == null){
			throw new CheckException("unkown id");
		}

		Category category = dozerMapper.map(categoryDto, Category.class);
		categoryDao.save(category);
	}


	@Transactional(readOnly=true)
	public CategoryDTO findCategory(Long categoryId) throws CheckException {
		if (categoryId == null || "".equals(categoryId))
            throw new CheckException("Invalid id");


        Category category = categoryDao.findOne(categoryId);
		
		CategoryDTO categoryDto = dozerMapper.map(category,CategoryDTO.class);
		return categoryDto;
	}
	
	@Transactional(readOnly=true)
	public List<CategoryDTO> findCategories() {

        Iterable<Category> lst =  categoryDao.findAll();

		List<CategoryDTO> lstDto = new ArrayList<CategoryDTO>();

		for (Category obj:lst){
	        CategoryDTO categoryDto = dozerMapper.map(obj, CategoryDTO.class);
	        lstDto.add(categoryDto);
		}


		return lstDto;
	}


}
