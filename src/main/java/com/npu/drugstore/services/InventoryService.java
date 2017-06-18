package com.npu.drugstore.services;

import java.util.List;

import com.npu.drugstore.domain.Inventory;
import com.npu.drugstore.domain.OrderItem;
import com.npu.drugstore.exceptions.ProductOutOfStockException;

public interface InventoryService {
	public List<Inventory> getInventory();

	public void updateInventory(List<OrderItem> oiList) throws ProductOutOfStockException;
}
