package com.npu.drugstore.services;

import com.npu.drugstore.domain.Order;
import com.npu.drugstore.exceptions.ProductOutOfStockException;

public interface BillingService {
	public void computeTotalPrice(Order order);

	public void processCustomerPurchase(Order order) throws ProductOutOfStockException;
}
