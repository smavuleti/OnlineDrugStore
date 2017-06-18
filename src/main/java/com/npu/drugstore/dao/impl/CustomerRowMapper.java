package com.npu.drugstore.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.npu.drugstore.domain.Customer;

public class CustomerRowMapper implements RowMapper<Customer> {
	@Override
	public Customer mapRow(ResultSet rs, int row) throws SQLException {
		Customer customer = new Customer();
		customer.setId(rs.getInt(1));
		customer.setName(rs.getString(2));
		customer.setState(rs.getString(3));
		return customer;
	}

}
