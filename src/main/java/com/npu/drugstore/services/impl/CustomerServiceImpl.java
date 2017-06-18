package com.npu.drugstore.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.npu.drugstore.dao.CustomerDao;
import com.npu.drugstore.domain.Customer;
import com.npu.drugstore.services.CustomerService;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerDao customerDao;

	public List<Customer> getCustomers() {
		return customerDao.getCustomers();
	}

}
