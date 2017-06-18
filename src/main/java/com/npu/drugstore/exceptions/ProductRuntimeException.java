package com.npu.drugstore.exceptions;

public class ProductRuntimeException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ProductRuntimeException(String msg) {
		super(msg);
	}
}
