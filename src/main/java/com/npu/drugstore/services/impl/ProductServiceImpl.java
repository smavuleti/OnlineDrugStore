package com.npu.drugstore.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.npu.drugstore.dao.ProductDao;
import com.npu.drugstore.domain.*;
import com.npu.drugstore.services.ProductService;

@Service("productService")
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public List<Category> getCategories() {
		return productDao.getCategories();
	}

	public List<Product> getProducts(int catId) {
		return productDao.getProducts(catId);
	}

	public void updatePrice(int prodId, double newPrice) {
		productDao.updatePrice(prodId, newPrice);
	}

	public Product getProduct(int prodId) {
		return productDao.getProduct(prodId);
	}

	public void addNewProduct(Product product) {
		productDao.addNewProduct(product);
	}

	public void deleteProduct(int prodId) {
		productDao.deleteProduct(prodId);
	}
}
