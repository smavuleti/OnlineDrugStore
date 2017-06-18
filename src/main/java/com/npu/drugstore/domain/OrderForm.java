package com.npu.drugstore.domain;

import java.util.List;

public class OrderForm {
	private Customer customer;
	private List<OrderItem> oiList;

	public List<OrderItem> getOiList() {
		return oiList;
	}

	public void setOiList(List<OrderItem> oiList) {
		this.oiList = oiList;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
