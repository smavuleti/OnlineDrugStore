package com.npu.drugstore.exceptions;

public class ProductOutOfStockException extends Exception {
	private static final long serialVersionUID = 1L;

	public ProductOutOfStockException(String msg) {
		super(msg);
	}
}
