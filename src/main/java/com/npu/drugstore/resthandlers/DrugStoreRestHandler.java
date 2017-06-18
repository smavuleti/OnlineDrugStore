package com.npu.drugstore.resthandlers;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;

import com.npu.drugstore.domain.Category;
import com.npu.drugstore.domain.CategoryList;
import com.npu.drugstore.domain.Product;
import com.npu.drugstore.domain.ProductList;
import com.npu.drugstore.services.ProductService;

@Path("/drugstorerestapp")
public class DrugStoreRestHandler {

	@Autowired
	private ProductService productService;

	@GET
	@Path("/category")
	@Produces("application/xml")
	public CategoryList getCategories() {
		List<Category> catList = productService.getCategories();
		CategoryList cList = new CategoryList();
		cList.setCategoryList(catList);
		return cList;
	}

	@GET
	@Path("/category/{catId}/product")
	@Produces("application/xml")
	public ProductList getAllProducts(@PathParam(value = "catId") int catId) {
		List<Product> prodList = productService.getProducts(catId);
		ProductList pList = new ProductList();
		pList.setProductList(prodList);
		return pList;
	}

	@GET
	@Path("/product/{prodId}")
	@Produces("application/xml")
	public ProductList getProduct(@PathParam(value = "prodId") int prodId) {
		Product prod = productService.getProduct(prodId);
		ProductList pList = new ProductList();
		List<Product> prodList = new ArrayList<Product>();
		prodList.add(prod);
		pList.setProductList(prodList);
		return pList;
	}

	@PUT
	@Path("/product/{prodId}/price/{newPrice}")
	public void updateProductPrice(@PathParam(value = "prodId") int prodId,
			@PathParam(value = "newPrice") double newPrice) {
		productService.updatePrice(prodId, newPrice);
	}

	@POST
	@Path("/product")
	@Produces("application/xml")
	@Consumes("application/xml")
	public Response addNewProduct(Product newProduct) {
		ResponseBuilder respBuilder;
		try {
			productService.addNewProduct(newProduct);
		} catch (Exception ex) {
			throw new WebApplicationException(ex.getMessage(), Status.INTERNAL_SERVER_ERROR);
		}
		respBuilder = Response.status(Status.CREATED);
		respBuilder.entity(newProduct);
		return respBuilder.build();
	}

	@DELETE
	@Path("/product/{prodId}")
	public void deleteProduct(@PathParam(value = "prodId") int prodId) {
		productService.deleteProduct(prodId);
	}

}
