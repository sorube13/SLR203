package com.store.catalog.ws.rest.view.rdbms;

import com.store.catalog.model.ProductDTO;
import com.store.catalog.service.catalog.CatalogService;
import com.store.catalog.model.CategoryDTO;
import com.store.catalog.model.Item;
import com.store.catalog.model.ItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by ZCadi on 26/10/2015.
 */
@Path("/catalog")
public class CatalogResource {



    @Autowired CatalogService catalogServiceImpl;

    public CatalogService getCatalogServiceImpl() {
        return catalogServiceImpl;
    }

    public void setCatalogServiceImpl(CatalogService catalogServiceImpl) {
        this.catalogServiceImpl = catalogServiceImpl;
    }
    


    @Path("/categories")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategories() throws Exception {
       return Response.ok(catalogServiceImpl.findCategories()).build();
    }
    //curl -i 'http://localhost:8080/catalog/catalog/categories' -X GET

    @Path("/category/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategory(@PathParam("id") Long id) throws Exception {
    	try{
    		CategoryDTO cdto = catalogServiceImpl.findCategory(id);
    		return Response.ok(cdto).build();
    	}catch(Exception e){
    		return Response.status(Response.Status.NOT_FOUND).build();

    	}    	
    }
    // curl -i 'http://localhost:8080/catalog/catalog/category/6' -X GET

    @Path("/category")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCategory(CategoryDTO categoryDTO) throws Exception {
    	try{
    		catalogServiceImpl.createCategory(categoryDTO);
        	return Response.ok().build();
    	}catch(Exception e){
    		return Response.status(Response.Status.NOT_FOUND).build();
    	}
    }
    // curl -i 'http://localhost:8080/catalog/catalog/category' -H "Content-Type:application/json" -X POST -d '{"id": 1, "name" : "SilviaCAT","description":"caegoryyyy", "products":null}'       
     

    @Path("/category")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCategory(CategoryDTO categoryDTO) throws Exception {
    	try{
    		catalogServiceImpl.updateCategory(categoryDTO);
    		return Response.ok().build();
    	}catch(Exception e){
    		return Response.status(404).build();
    	}   	
    	

    }
    //curl -i 'http://localhost:8080/catalog/catalog/category' -H "Content-Type:application/json" -X PUT -d '{"id": 1, "name" : "CATEGORY COOL","description":"caegoryyyy"}'
     
    

    @Path("/category/{id}")
    @DELETE
    public Response deleteCategory(@PathParam("id") Long id) throws Exception {
    	catalogServiceImpl.deleteCategory(id);
    	return Response.ok().build();

    }
    //curl -i 'http://localhost:8080/catalog/catalog/category/6' -X DELETE

    @Path("/products")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts() throws Exception {
    	try{
    		return Response.ok(catalogServiceImpl.findProducts()).build();
    	}catch(Exception e){
    		return Response.status(Response.Status.NOT_FOUND).build();

    	}	
    }
    //curl -i 'http://localhost:8080/catalog/catalog/products' -X GET
    
    
    @Path("/product/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("id") Long id) throws Exception {

    	try{
    		ProductDTO pdto = catalogServiceImpl.findProduct(id);
    		return Response.ok(pdto).build();
    	}catch(Exception e){
    		return Response.status(Response.Status.NOT_FOUND).build();

    	}
    }
    //curl -i 'http://localhost:8080/catalog/catalog/product/1' -X GET

    @Path("/product")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(ProductDTO productDTO) throws Exception {
    	catalogServiceImpl.createProduct(productDTO);
    	return Response.ok().build();
    }
    //curl -i 'http://localhost:8080/catalog/catalog/product' -H "Content-Type:application/json" -X POST -d '{"id": 5, "name" : "SilviaPRODUCT","description":"product COOL", "category": { "id": 6 , "name":"Phones", "description": "All kinds of phones"}}'

    
    @Path("/product")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProduct(ProductDTO productDTO) throws Exception {
    	try{
    		catalogServiceImpl.updateProduct(productDTO);
    		return Response.ok().build();	
    	}catch(Exception e){
    		return Response.ok(404).build();
    	}
    }
    //curl -i 'http://localhost:8080/catalog/catalog/product' -H "Content-Type:application/json" -X PUT -d '{"id": 5, "name" : "SPRODUCT","description":"product COOL - SILVIA", "category": { "id": 6 , "name":"Phones", "description": "All kinds of phones"}}'
   

    @Path("/product/{id}")
    @DELETE
    public Response deleteProduct(@PathParam("id") Long id) throws Exception {
    	try{
    		catalogServiceImpl.deleteProduct(id);
        	return Response.ok().build();
    	}catch(Exception e){
    		return Response.ok(404).build();
    	}
    	
    }
    //curl -i 'http://localhost:8080/catalog/catalog/product/5' -X DELETE

    @Path("/items")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItems() throws Exception {
    	try{
    		return Response.ok(catalogServiceImpl.findItems()).build();
    	}catch(Exception e){
    		return Response.ok(404).build();
    	}
    	
    }
    //curl -i 'http://localhost:8080/catalog/catalog/items' -X GET


    @Path("/item/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItem(@PathParam("id") Long id) throws Exception {
    	try{
    		ItemDTO idto = catalogServiceImpl.findItem(id);
    		return Response.ok(idto).build();
    	}catch(Exception e){
    		return Response.status(404).build();
    	}
    }
    //curl -i 'http://localhost:8080/catalog/catalog/item/1' -X GET

    @Path("/item")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createItem(ItemDTO itemDTO) throws Exception {
    	catalogServiceImpl.createItem(itemDTO);
    	return Response.ok().build();
    }
    //curl -i 'http://localhost:8080/catalog/catalog/item' -H "Content-Type:application/json" -X POST -d '{"id": 50, "name" : "ITEM_SIlvia","unitCost": "10.00","imagePath": "ajdfja", "product":{"id": 1}}' 
   

    @Path("/item")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateItem(ItemDTO itemDTO) throws Exception {
    	try{
    		catalogServiceImpl.updateItem(itemDTO);
        	return Response.ok().build();
    	}catch(Exception e){
    		return Response.ok(404).build();
    	}
    	
    }
    //curl -i 'http://localhost:8080/catalog/catalog/item/' -H "Content-Type:application/json" -X PUT -d '{"id": 50, "name" : "ITEM_SIlvia!!!!!!!1","unitCost": "10.00","imagePath": "ajdfja", "product":{"id": 1}}' 

    @Path("/item/{id}")
    @DELETE
    public Response deleteItem(@PathParam("id") Long id) throws Exception {
    	try{
    		catalogServiceImpl.deleteItem(id);
        	return Response.ok().build();
    	}catch(Exception e){
    		return Response.ok(404).build();
    	}
    }
    //curl -i 'http://localhost:8080/catalog/catalog/item/50' -X DELETE

    @Path("/item/list/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItemsByName(@PathParam("name") String name) throws Exception {
    	try{
    		return Response.ok(catalogServiceImpl.searchItems(name)).build();	
    	}catch(Exception e){
    		return Response.ok(404).build();
    	}
    }
    //curl -i 'http://localhost:8080/catalog/catalog/item/list/ITEM' -X GET

}
