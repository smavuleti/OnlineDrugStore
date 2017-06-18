package com.npu.drugstore.domain;

public class Inventory {
	private int invId;
	private int prodId;
	private int initialCount;
	private int availableCount;
	private int soldCount;
	private String sellingDetails;

	public int getInvId() {
		return invId;
	}

	public void setInvId(int invId) {
		this.invId = invId;
	}

	public int getProdId() {
		return prodId;
	}

	public void setProdId(int prodId) {
		this.prodId = prodId;
	}

	public int getInitialCount() {
		return initialCount;
	}

	public void setInitialCount(int initialCount) {
		this.initialCount = initialCount;
	}

	public int getAvailableCount() {
		return availableCount;
	}

	public void setAvailableCount(int availableCount) {
		this.availableCount = availableCount;
	}

	public int getSoldCount() {
		return soldCount;
	}

	public void setSoldCount(int soldCount) {
		this.soldCount = soldCount;
	}

	public String getSellingDetails() {
		return sellingDetails;
	}

	public void setSellingDetails(String sellingDetails) {
		this.sellingDetails = sellingDetails;
	}
}
