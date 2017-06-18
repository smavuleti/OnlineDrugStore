package com.npu.drugstore.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.npu.drugstore.domain.*;
import com.npu.drugstore.services.BillingService;
import com.npu.drugstore.services.CustomerService;
import com.npu.drugstore.services.ProductService;

@Controller
public class DrugStoreController {
	@Autowired
	private ProductService productService;
	@Autowired
	private BillingService billingService;
	@Autowired
	private CustomerService customerService;
	private static final Logger logger = LoggerFactory.getLogger(DrugStoreController.class);

	@RequestMapping(value = { "/", "home" }, method = RequestMethod.GET)
	public ModelAndView home(HttpSession session) {
		session.setAttribute("orderform", new OrderForm());
		List<Customer> custList = customerService.getCustomers();
		Map<Integer, Customer> custMap = new HashMap<Integer, Customer>();
		for (Customer c : custList) {
			custMap.put(c.getId(), c);
		}
		session.setAttribute("custMap", custMap);
		ModelAndView modelView;
		modelView = new ModelAndView("customerHome");
		modelView.addObject("custList", custList);
		modelView.addObject("customer", new Customer());
		return modelView;
	}

	@RequestMapping(value = "/categoryList", method = RequestMethod.GET)
	public ModelAndView categoryList(@ModelAttribute("customer") Customer customer, HttpSession session) {
		OrderForm sesOrderForm = (OrderForm) session.getAttribute("orderform");
		if (null == sesOrderForm.getCustomer()
				|| (customer.getId() != sesOrderForm.getCustomer().getId() && customer.getId() > 0)) {
			Map<Integer, Customer> custMap = (Map<Integer, Customer>) session.getAttribute("custMap");
			Set<Integer> cusIds = (Set<Integer>) custMap.keySet();
			Customer ctemp = new Customer();
			for (Integer id : cusIds) {
				if (customer.getId() == id) {
					ctemp = custMap.get(id);
				}
			}
			OrderForm orderform = (OrderForm) session.getAttribute("orderform");
			orderform.setCustomer(ctemp);
			session.setAttribute("orderform", orderform);
		}
		ModelAndView modelView;
		List<Category> catList = productService.getCategories();
		modelView = new ModelAndView("categoryList");
		modelView.addObject("catList", catList);
		return modelView;
	}

	@RequestMapping(value = "/displayProductsForm", method = RequestMethod.GET)
	public ModelAndView displayProductsForm(@RequestParam(value = "catId", required = true) int catId,
			HttpSession session) {
		ModelAndView modelView;
		OrderForm sessOrderform = (OrderForm) session.getAttribute("orderform");
		List<Product> prodList = productService.getProducts(catId);

		OrderForm orderform = new OrderForm();
		List<OrderItem> oiList = new ArrayList<OrderItem>();
		OrderItem oi;
		for (Product p : prodList) {
			oi = new OrderItem();
			oi.setProduct(p);
			oi.setQuantity(0);
			oiList.add(oi);
		}
		orderform.setOiList(oiList);
		if (null != sessOrderform && null != sessOrderform.getOiList()) {
			boolean exist = false;
			for (OrderItem sesOi : sessOrderform.getOiList()) {
				exist = false;
				for (OrderItem oi1 : oiList) {
					if (!exist && oi1.getProduct().getName().equalsIgnoreCase(sesOi.getProduct().getName())) {
						oi1.setQuantity(sesOi.getQuantity());
						exist = true;
					}
				}
			}
		}
		modelView = new ModelAndView("displayProductsForm");
		modelView.addObject("orderform", orderform);
		return modelView;
	}

	@RequestMapping(value = "/addProductsForm", method = RequestMethod.POST)
	public ModelAndView addNewProduct(@ModelAttribute("orderform") OrderForm orderform, HttpSession session) {

		OrderForm sesOrderform = (OrderForm) session.getAttribute("orderform");
		Iterator<OrderItem> itr = orderform.getOiList().iterator();
		OrderItem oi1;
		while (itr.hasNext()) {
			oi1 = (OrderItem) itr.next();
			if (oi1.getQuantity() == 0)
				itr.remove();
		}
		List<OrderItem> oiList = sesOrderform.getOiList();
		if (null == oiList) {
			sesOrderform.setOiList(orderform.getOiList());
		} else {
			for (OrderItem newOi : orderform.getOiList()) {
				boolean exist = false;
				for (OrderItem sesOi : oiList) {
					if (!exist && sesOi.getProduct().getName().equalsIgnoreCase(newOi.getProduct().getName())) {
						sesOi.setQuantity(newOi.getQuantity());
						exist = true;
					}
				}
				if (!exist) {
					oiList.add(newOi);
				}
			}
		}
		session.setAttribute("orderform", sesOrderform);
		return new ModelAndView("addProductSuccess");
	}

	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public ModelAndView showCart(HttpSession session) {
		return new ModelAndView("cart");
	}

	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
	public ModelAndView checkOut(HttpSession session) {
		OrderForm orderform = (OrderForm) session.getAttribute("orderform");
		Order order = new Order();
		double subtotal = 0.0;
		order.setOiList(orderform.getOiList());
		order.setCustomer(orderform.getCustomer());
		for (OrderItem oi : orderform.getOiList()) {
			subtotal += oi.getProduct().getPrice() * oi.getQuantity();
		}
		order.setSubtotal(subtotal);
		billingService.computeTotalPrice(order);
		ModelAndView modelview = new ModelAndView("checkOut");
		modelview.addObject("order", order);
		return modelview;
	}

	// Payment successful page
	@RequestMapping(value = "/paymentSuccessful", method = RequestMethod.GET)
	public ModelAndView paymentSuccessful(HttpSession session) {
		session.setAttribute("orderform", null);
		return new ModelAndView("paymentSuccessful");
	}
}
