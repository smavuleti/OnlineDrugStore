package com.npu.drugstore.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.npu.drugstore.services.BillingService;
import com.npu.drugstore.domain.Order;
import com.npu.drugstore.exceptions.ProductOutOfStockException;
import com.npu.drugstore.services.*;

@Service("billingService")
public class BillingServiceImpl implements BillingService {

	@Autowired
	private TaxService taxService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private InventoryService inventoryService;

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public void setInventoryService(InventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}

	public void setTaxService(TaxService taxService) {
		this.taxService = taxService;
	}

	public void computeTotalPrice(Order order) {
		System.out.println("2222222");

		double totalTax = order.getSubtotal() * taxService.computeTax(order) / 100;
		order.setTax(totalTax);
		order.setTotal(order.getSubtotal() + totalTax);
		System.out.println(order.getTotal());
	}

	@Transactional(readOnly = false, rollbackForClassName = "ProductOutOfStockException")
	public void processCustomerPurchase(Order order) throws ProductOutOfStockException {
		orderService.saveOrder(order);
		inventoryService.updateInventory(order.getOiList());
	}
}
