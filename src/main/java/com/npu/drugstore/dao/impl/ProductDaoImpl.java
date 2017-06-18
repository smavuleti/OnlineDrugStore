package com.npu.drugstore.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.npu.drugstore.dao.ProductDao;
import com.npu.drugstore.domain.Category;
import com.npu.drugstore.domain.Product;

@Repository("productDao")
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private CategoryRowMapper categoryRowMapper;
	private ProductRowMapper productRowMapper;
	private NamedParameterJdbcTemplate dbTemplate;
	private SimpleJdbcInsert prodJdbcInsert;

	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		dbTemplate = new NamedParameterJdbcTemplate(dataSource);
		categoryRowMapper = new CategoryRowMapper();
		productRowMapper = new ProductRowMapper();
		prodJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("product").usingGeneratedKeyColumns("prodId")
				.usingColumns("catId", "name", "price");
	}

	public List<Category> getCategories() {
		String sql = "SELECT * FROM CATEGORY";
		return jdbcTemplate.query(sql, categoryRowMapper);
	}

	public List<Product> getProducts(int catId) {
		String sql = "SELECT * FROM PRODUCT WHERE CATID = ?";
		return jdbcTemplate.query(sql, productRowMapper, catId);
	}

	public void updatePrice(int prodId, double newPrice) {
		String sql = "UPDATE PRODUCT SET price=:price WHERE prodId=:prodId";
		MapSqlParameterSource params = new MapSqlParameterSource("price", newPrice);
		params.addValue("prodId", prodId);
		dbTemplate.update(sql, params);
	}

	public Product getProduct(int prodId) {
		String sql = "SELECT * FROM PRODUCT WHERE PRODID = ?";
		return jdbcTemplate.queryForObject(sql, productRowMapper, prodId);
	}

	public void addNewProduct(Product product) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("catId", product.getCatId());
		params.addValue("name", product.getName());
		params.addValue("price", product.getPrice());
		Number newId = prodJdbcInsert.executeAndReturnKey(params);
		product.setProdId(newId.intValue());
	}

	public void deleteProduct(int prodId) {
		String sql = "DELETE FROM PRODUCT WHERE PRODID = ?";
		jdbcTemplate.update(sql, prodId);
	}
}
