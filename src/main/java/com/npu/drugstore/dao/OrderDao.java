package com.npu.drugstore.dao;

import com.npu.drugstore.domain.Order;

public interface OrderDao {

	public void saveOrder(Order order);

	public int getOrdersCount();
}
