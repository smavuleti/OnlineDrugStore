package com.npu.drugstore.test.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.npu.drugstore.dao.*;
import com.npu.drugstore.domain.*;

@ContextConfiguration("classpath:drugstore-test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class InventoryDAOTest {
	@Autowired
	private InventoryDao inventoryDao;

	@Test
	public void testOrderCount() {
		List<Inventory> invList = inventoryDao.getInventory();
		System.out.println(invList.size());
	}

	@Test
	public void testUpdateInventory() {
		OrderItem orderItem = new OrderItem();
		List<OrderItem> oiList = new ArrayList<OrderItem>();
		Product product = new Product();
		product.setCatId(1);
		product.setPrice(19.25);
		product.setProdId(1);
		orderItem.setProduct(product);
		orderItem.setQuantity(10);
		oiList.add(orderItem);
		int initialAvailable = 0;
		int initialSold = 0;
		List<Inventory> invList = inventoryDao.getInventory();
		for (Inventory inv : invList) {
			if (inv.getProdId() == product.getProdId()) {
				initialAvailable = inv.getAvailableCount();
				initialSold = inv.getSoldCount();
			}
		}
		try {
			inventoryDao.updateInventory(oiList);
		} catch (Exception e) {
			System.out.println("Out of Stock");
		}
		int finalAvailable = 0;
		int finalSold = 0;
		invList = inventoryDao.getInventory();
		for (Inventory inv : invList) {
			if (inv.getProdId() == product.getProdId()) {
				finalAvailable = inv.getAvailableCount();
				finalSold = inv.getSoldCount();
			}
		}
		assertEquals(initialAvailable - 10, finalAvailable);
		assertEquals(initialSold + 10, finalSold);
	}

}
