package com.store.catalog.ws.rest.view.rdbms;

import com.store.catalog.model.ProductDTO;
import com.store.catalog.service.catalog.CatalogService;
import com.store.catalog.model.CategoryDTO;
import com.store.catalog.model.ItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by ZCadi on 26/10/2015.
 */

public class CatalogResource {



    @Autowired CatalogService catalogServiceImpl;

    public CatalogService getCatalogServiceImpl() {
        return catalogServiceImpl;
    }

    public void setCatalogServiceImpl(CatalogService catalogServiceImpl) {
        this.catalogServiceImpl = catalogServiceImpl;
    }




    public Response getCategories() throws Exception {

        List<CategoryDTO> categories = catalogServiceImpl.findCategories();

        return Response.status(200).entity(categories).build();
    }


    public Response getCategory(@PathParam("id") Long id) throws Exception {

        CategoryDTO categoryDTO = catalogServiceImpl.findCategory(id);

        return Response.status(200).entity(categoryDTO).build();
    }


    public Response createCategory(CategoryDTO categoryDTO) throws Exception {

        CategoryDTO createdCategoryDTO =  catalogServiceImpl.createCategory(categoryDTO);

        return Response.status(201).entity(createdCategoryDTO).build();

    }


    public Response updateCategory(CategoryDTO categoryDTO) throws Exception {

        catalogServiceImpl.updateCategory(categoryDTO);

        return Response.status(201).build();

    }


    public Response deleteCategory(@PathParam("id") Long id) throws Exception {

        catalogServiceImpl.deleteCategory(id);

        return Response.status(204).build();

    }


    public Response getProducts() throws Exception {

        List<ProductDTO> products = catalogServiceImpl.findProducts();

        return Response.status(200).entity(products).build();
    }


    public Response getProduct(@PathParam("id") Long id) throws Exception {

        ProductDTO productDTO = catalogServiceImpl.findProduct(id);

        return Response.status(200).entity(productDTO).build();
    }


    public Response createProduct(ProductDTO productDTO) throws Exception {

        ProductDTO createdProductDTO =  catalogServiceImpl.createProduct(productDTO);

        return Response.status(201).entity(createdProductDTO).build();

    }


    public Response updateProduct(ProductDTO productDTO) throws Exception {

        catalogServiceImpl.updateProduct(productDTO);

        return Response.status(201).build();

    }


    public Response deleteProduct(@PathParam("id") Long id) throws Exception {

        catalogServiceImpl.deleteProduct(id);

        return Response.status(204).build();

    }


    public Response getItems() throws Exception {

        List<ItemDTO> items = catalogServiceImpl.findItems();

        return Response.status(200).entity(items).build();
    }


    public Response getItem(@PathParam("id") Long id) throws Exception {

        ItemDTO itemDTO = catalogServiceImpl.findItem(id);

        return Response.status(200).entity(itemDTO).build();
    }


    public Response createItem(ItemDTO itemDTO) throws Exception {

        ItemDTO createdItemDTO =  catalogServiceImpl.createItem(itemDTO);

        return Response.status(201).entity(createdItemDTO).build();

    }


    public Response updateItem(ItemDTO itemDTO) throws Exception {

        catalogServiceImpl.updateItem(itemDTO);

        return Response.status(201).build();

    }


    public Response deleteItem(@PathParam("id") Long id) throws Exception {

        catalogServiceImpl.deleteItem(id);

        return Response.status(204).build();

    }


    public Response getItemsByName(@PathParam("name") String name) throws Exception {

        List<ItemDTO>  items = catalogServiceImpl.searchItems(name);

        return Response.status(200).entity(items).build();
    }

}
