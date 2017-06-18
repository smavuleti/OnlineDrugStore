package com.npu.drugstore.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.npu.drugstore.dao.InventoryDao;
import com.npu.drugstore.domain.Inventory;
import com.npu.drugstore.domain.OrderItem;
import com.npu.drugstore.exceptions.ProductOutOfStockException;

@Repository("inventoryDao")
public class InventoryDaoImpl implements InventoryDao {
	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private InventoryRowMapper inventoryRowMapper;
	private NamedParameterJdbcTemplate namedTemplate;

	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		inventoryRowMapper = new InventoryRowMapper();
		namedTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Inventory> getInventory() {
		String sql = "SELECT * FROM INVENTORY";
		return jdbcTemplate.query(sql, inventoryRowMapper);
	}

	public void updateInventory(List<OrderItem> oiList) throws ProductOutOfStockException {
		String sql = "UPDATE INVENTORY SET availableCount = :newAvailableCount, soldCount = :newSoldCount WHERE prodId = :prodId";
		int newAvailableCount, newSoldCount, prodId;
		MapSqlParameterSource params;
		int rowsAffected = 0;
		List<Inventory> invList = getInventory();
		for (OrderItem oi : oiList) {
			prodId = oi.getProduct().getProdId();
			for (Inventory inv : invList) {
				newAvailableCount = 0;
				newSoldCount = 0;
				if (prodId == inv.getProdId()) {
					newSoldCount = inv.getSoldCount() + oi.getQuantity();
					newAvailableCount = inv.getAvailableCount() - oi.getQuantity();
					if (newAvailableCount < 0)
						throw new ProductOutOfStockException("You cannot order more than " + inv.getAvailableCount()
								+ " " + oi.getProduct().getName());
					params = new MapSqlParameterSource("prodId", prodId);
					params.addValue("newAvailableCount", newAvailableCount);
					params.addValue("newSoldCount", newSoldCount);
					rowsAffected += namedTemplate.update(sql, params);
				}
			}
		}
		System.out.println(rowsAffected);
	}

}
