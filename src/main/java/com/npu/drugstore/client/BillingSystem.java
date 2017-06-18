package com.npu.drugstore.client;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.npu.drugstore.domain.*;
import com.npu.drugstore.services.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BillingSystem {
	public static void main(String[] args) {
		List<Product> prodList;
		List<OrderItem> oiList = new ArrayList<OrderItem>();
		OrderItem orderItem;
		Order order = new Order();
		Scanner scanner = new Scanner(System.in);
		int option;
		double subTotal = 0.0;
		AbstractApplicationContext container = new ClassPathXmlApplicationContext("root-context.xml");
		container.registerShutdownHook();
		ProductService productService = (ProductService) container.getBean("productService");
		List<Category> categoryList = productService.getCategories();
		do {
			System.out.println("\n*******************************************\n");
			System.out.println("Please select a Category : ");
			for (Category c : categoryList) {
				System.out.println(c.getCatId() + " : " + c.getCatName());
			}
			System.out.println("Enter 10 to quit!");
			System.out.println("\n*******************************************\n");

			option = scanner.nextInt();

			if (option >= 1 && option <= 6) {
				prodList = productService.getProducts(option);
				int prodOption;
				do {
					int serialNum = 1;
					System.out.println("\nPlease select a product to order");
					for (Product p : prodList) {
						System.out.println(serialNum + " : " + p.getName() + " : " + p.getPrice());
						serialNum++;
					}
					System.out.println("Enter 30 to quit!\n");
					prodOption = scanner.nextInt();
					orderItem = new OrderItem();
					if (prodOption >= 1 && prodOption < serialNum) {
						orderItem.setProduct(prodList.get(prodOption - 1));
						System.out.print("Please enter quantity : ");
						orderItem.setQuantity(scanner.nextInt());
						subTotal += orderItem.getProduct().getPrice() * orderItem.getQuantity();
						oiList.add(orderItem);
					} else if (prodOption == 30) {
						System.out.println("\nGOING BACK TO CATEGORY LIST\n");
					} else {
						System.out.print("\nPLEASE SELECT CORRECT PRODUCT\n");
					}
				} while (prodOption != 30);
			} else if (option == 10) {
				if (!oiList.isEmpty()) {
					order.setOiList(oiList);
					Customer customer = new Customer();
					customer.setId(1);
					customer.setName("John");
					customer.setState("California");
					order.setCustomer(customer);
					order.setSubtotal(subTotal);
					BillingService billingService = (BillingService) container.getBean("billingService");
					billingService.computeTotalPrice(order);
					System.out.println("************** YOUR ORDER IS **************");
					for (OrderItem oi : oiList) {
						System.out.println(oi.getProduct().getName() + " : " + oi.getProduct().getPrice() + " : "
								+ oi.getQuantity());
					}
					System.out.println("SubTotal : " + order.getSubtotal());
					System.out.println("Tax : " + order.getTax());
					System.out.println("Total : " + order.getTotal());
					try {
						billingService.processCustomerPurchase(order);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				System.out.println("\nTHANK YOU FOR SHOPPING WITH US\n");
			} else {
				System.out.print("\nPLEASE SELECT CORRECT CATEGORY\n");
			}
		} while (option != 10);
	}
}
