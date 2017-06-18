package com.npu.drugstore.dao;

import java.util.List;

import com.npu.drugstore.domain.*;
import com.npu.drugstore.exceptions.ProductOutOfStockException;

public interface InventoryDao {
	public List<Inventory> getInventory();

	public void updateInventory(List<OrderItem> oiList) throws ProductOutOfStockException;

}
