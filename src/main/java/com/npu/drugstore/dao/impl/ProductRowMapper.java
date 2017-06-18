package com.npu.drugstore.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.npu.drugstore.domain.Product;

public class ProductRowMapper implements RowMapper<Product> {
	public Product mapRow(ResultSet rs, int row) throws SQLException {
		Product product = new Product();
		product.setProdId(rs.getInt(1));
		product.setName(rs.getString(2));
		product.setPrice(rs.getDouble(3));
		product.setCatId(rs.getInt(4));
		return product;
	}
}
