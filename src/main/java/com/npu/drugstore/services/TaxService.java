package com.npu.drugstore.services;

import com.npu.drugstore.domain.Order;

public interface TaxService {
	public double computeTax(Order order);
}
