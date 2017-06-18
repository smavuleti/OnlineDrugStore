package com.npu.drugstore.domain;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProductList implements Serializable {
	private static final long serialVersionUID = 1L;

	@XmlElement(name = "product")
	private List<Product> prodList;

	public List<Product> getProdList() {
		return prodList;
	}

	public void setProductList(List<Product> prodList) {
		this.prodList = prodList;
	}

	public int numEntries() {
		if (prodList == null)
			return 0;
		return prodList.size();
	}

	public Product getProduct(int index) {
		return prodList.get(index);
	}

	public String toString() {
		String listStr;
		listStr = "ProductList{";
		for (Product entry : prodList) {
			listStr = listStr + "\n\t" + entry;
		}
		listStr = listStr + "\n}";
		return listStr;
	}
}
