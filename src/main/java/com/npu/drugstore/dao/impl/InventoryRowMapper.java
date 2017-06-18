package com.npu.drugstore.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.npu.drugstore.domain.Inventory;

public class InventoryRowMapper implements RowMapper<Inventory> {
	public Inventory mapRow(ResultSet rs, int row) throws SQLException {
		Inventory inventory = new Inventory();
		inventory.setInvId(rs.getInt(1));
		inventory.setProdId(rs.getInt(2));
		inventory.setInitialCount(rs.getInt(3));
		inventory.setAvailableCount(rs.getInt(4));
		inventory.setSoldCount(rs.getInt(5));
		inventory.setSellingDetails(rs.getString(6));
		return inventory;
	}
}
