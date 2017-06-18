package com.npu.drugstore.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.npu.drugstore.dao.InventoryDao;
import com.npu.drugstore.domain.Inventory;
import com.npu.drugstore.domain.OrderItem;
import com.npu.drugstore.exceptions.ProductOutOfStockException;
import com.npu.drugstore.services.InventoryService;

@Service("inventoryService")
public class InventoryServiceImpl implements InventoryService {
	@Autowired
	InventoryDao inventoryDao;

	public void setInventoryDao(InventoryDao inventoryDao) {
		this.inventoryDao = inventoryDao;
	}

	public List<Inventory> getInventory() {
		return inventoryDao.getInventory();
	}

	@Override
	public void updateInventory(List<OrderItem> oiList) throws ProductOutOfStockException {
		inventoryDao.updateInventory(oiList);
	}

}
