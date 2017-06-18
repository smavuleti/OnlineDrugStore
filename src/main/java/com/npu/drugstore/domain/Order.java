package com.npu.drugstore.domain;

import java.util.Iterator;
import java.util.List;

public class Order {
	private int code;
	private Customer customer;
	private List<OrderItem> oiList;
	private double subtotal;
	private double tax;
	private double total;

	public Order() {
	}

	public Order(int newOrderCode) {
		code = newOrderCode;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<OrderItem> getOiList() {
		return oiList;
	}

	public void setOiList(List<OrderItem> oiList) {
		this.oiList = oiList;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public void addItem(OrderItem item) {
		boolean prodExists = false;
		for (OrderItem oi : this.oiList) {
			if (oi.getProduct().getName().equalsIgnoreCase(item.getProduct().getName())) {
				oi.setQuantity(oi.getQuantity() + item.getQuantity());
				prodExists = true;
			}
		}
		if (!prodExists)
			this.oiList.add(item);
	}

	public void removeProduct(Product prod) {
		Iterator<OrderItem> oiIter = this.oiList.iterator();
		while (oiIter.hasNext()) {
			if (oiIter.next().getProduct().getName().equalsIgnoreCase(prod.getName())) {
				oiIter.remove();
			}
		}
	}
}
