package com.npu.drugstore.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.npu.drugstore.dao.OrderDao;
import com.npu.drugstore.domain.Order;
import com.npu.drugstore.domain.OrderItem;
import com.npu.drugstore.exceptions.ProductRuntimeException;

@Repository("orderDao")
public class OrderDaoImpl implements OrderDao {
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert orderJdbcInsert;
	private SimpleJdbcInsert orderItemJdbcInsert;

	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		orderJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("orders").usingGeneratedKeyColumns("orderId");
		orderItemJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("orderitem")
				.usingGeneratedKeyColumns("orderItemId");
	}

	public void saveOrder(Order order) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("custId", order.getCustomer().getId());
		params.addValue("subtotal", order.getSubtotal());
		params.addValue("tax", order.getTax());
		params.addValue("total", order.getTotal());
		Number newId = orderJdbcInsert.executeAndReturnKey(params);
		order.setCode(newId.intValue());

		List<OrderItem> oiList = order.getOiList();

		for (OrderItem oi : oiList) {
			if (oi.getQuantity() <= 0) {
				throw new ProductRuntimeException("Product quantity cannot be less than or equal to ZERO!!");
			}
			params = new MapSqlParameterSource();
			params.addValue("orderId", order.getCode());
			params.addValue("prodId", oi.getProduct().getProdId());
			params.addValue("quantity", oi.getQuantity());
			newId = orderItemJdbcInsert.executeAndReturnKey(params);
		}
	}

	public int getOrdersCount() {
		String sql = "select count(*) FROM orders";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

}
