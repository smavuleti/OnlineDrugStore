package com.npu.drugstore.domain;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CategoryList implements Serializable {
	private static final long serialVersionUID = 1L;
	@XmlElement(name = "category")
	private List<Category> catList;

	public List<Category> getCatList() {
		return catList;
	}

	// set'CategoryList'() must match the 'class name'
	public void setCategoryList(List<Category> catList) {
		this.catList = catList;
	}

	public int numEntries() {
		if (catList == null)
			return 0;
		return catList.size();
	}

	public Category getCategory(int index) {
		return catList.get(index);
	}

	public String toString() {
		String listStr;

		listStr = "CategoryList{";
		for (Category entry : catList) {
			listStr = listStr + "\n\t" + entry;
		}

		listStr = listStr + "\n}";
		return listStr;
	}
}
