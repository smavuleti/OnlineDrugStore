package com.npu.drugstore.services;

import com.npu.drugstore.domain.Order;
import com.npu.drugstore.domain.Product;

public interface OrderService {
	public void saveOrder(Order order);

	public int getOrdersCount();

	public void addProduct(Order order, Product product, int quantity);
}
