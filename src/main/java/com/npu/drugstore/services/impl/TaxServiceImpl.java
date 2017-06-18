package com.npu.drugstore.services.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.npu.drugstore.domain.Order;
import com.npu.drugstore.services.TaxService;

@Service("taxService")
public class TaxServiceImpl implements TaxService {

	Map<String, Integer> stateSalesTaxPercentageMap;

	@Autowired
	public TaxServiceImpl(@Value("${stateSalesTaxPercentage.map}") String stateSalesTaxPercentageMap) {
		this.stateSalesTaxPercentageMap = getMap(stateSalesTaxPercentageMap);
	}

	public double computeTax(Order order) {
		return stateSalesTaxPercentageMap.get(order.getCustomer().getState());
	}

	public Map<String, Integer> getMap(String str) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		String key = null;
		String value = null;
		StringTokenizer st = new StringTokenizer(str, ",");
		while (st.hasMoreElements()) {
			if (null == key)
				key = st.nextToken();
			else if (null != key)
				value = st.nextToken();
			if (null != key && null != value) {
				map.put(key, Integer.parseInt(value));
				key = null;
				value = null;
			}
		}
		return map;
	}

}
