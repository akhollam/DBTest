package com.app;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.exceptions.ServiceException;
import com.modals.Product;
import com.services.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ProductApp {

	@Autowired
	@Qualifier("productServiceDbImpl")
	private ProductService productService;

	private Scanner scanner;

	public static void main(String[] args) {

		ApplicationContext context = new AnnotationConfigApplicationContext(ProductAppConfig.class);
		ProductApp productApp = context.getBean(ProductApp.class);
		productApp.start();

	}

	public void start() {

		log.info("Product App started. ");

		scanner = new Scanner(System.in);
		String input = "";

		do {

			displayOptions();

			input = scanner.nextLine();
			log.info("User selected a {} option. ", input);

			switch (input) {

			case "1":
				addProduct();
				break;

			case "2":
				updateProduct();
				break;

			case "3":
				deleteProduct();
				break;

			case "4":
				displayAllProducts();
				break;

			case "5":
				displayProductCount();
				break;

			case "6":
				findProductById();
				break;

			case "x":
				return;

			default:
				log.warn("User entered invalid option. ");
				System.out.println("Invalid choice. Please try again. ");
				break;
			}

		} while (!input.equalsIgnoreCase("X"));

		scanner.close();

		log.info("Product App stopped. ");
	}

	private void findProductById() {

		int productId;

		while (true) {

			try {
				System.out.println("Enter product Id to be find - ");
				productId = Integer.parseInt(scanner.nextLine());
				break;
			} catch (NumberFormatException e1) {
				log.error("Invalid id entered. Please entera a valid id. ");
			}
		}

		Product p = productService.findById(productId);
		System.out.println("Product found is " + p);

	}

	private void displayProductCount() {
		System.out.println("Product count is - " + productService.productCount());
	}

	private void updateProduct() {

		log.debug("updateProduct called. ");

		Product product = new Product();

		System.out.println("Enter id of the product to be updated - ");
		displayAllProducts();

		System.out.println("------------------------------------");

		int productId = Integer.parseInt(scanner.nextLine());

		System.out.println("Product New Name - ");
		product.setName(scanner.nextLine());

		System.out.println("Product New Category - ");
		product.setCategory(scanner.nextLine());

		System.out.println("Product New Code - ");
		product.setCode(scanner.nextLine());

		System.out.println("Product New Qty - ");
		product.setQuantity(Integer.parseInt(scanner.nextLine()));

		log.trace("Product entered by user is - {}", product);

		try {
			product.setId(productId);
			productService.update(product);
			System.out.println("Product saved successfuly;");
		} catch (ServiceException e) {

			log.error("Service exception occured while saving product.", e);
			System.out.println(e.getLocalizedMessage());
		}

	}

	private void deleteProduct() {

		int productId;

		while (true) {

			try {
				System.out.println("Enter product Id to be deleted - ");
				productId = Integer.parseInt(scanner.nextLine());
				break;
			} catch (NumberFormatException e1) {
				log.error("Invalid id entered. Please entera a valid id. ");
			}
		}

		try {
			productService.delete(productId);
			System.out.println("Product has been removed.");
		} catch (ServiceException e) {
			System.out.println(e.getLocalizedMessage());
		}

	}

	private void displayAllProducts() {

		List<Product> allProducts;
		try {
			allProducts = productService.findAll();
			System.out.println("Product ID \t Name \t Category \t Quantity");
			for (Product product : allProducts) {
				System.out.println(product.getId() + "\t" + product.getName() + "\t" + product.getCategory() + "\t"
						+ product.getQuantity());
			}
		} catch (ServiceException e) {
			log.error("Service exception occured while saving product.", e);
			System.out.println(e.getLocalizedMessage());
		}

	}

	public void addProduct() {

		Product product = new Product();

		System.out.println("Product Name - ");
		product.setName(scanner.nextLine());

		System.out.println("Product Category - ");
		product.setCategory(scanner.nextLine());

		System.out.println("Product Code - ");
		product.setCode(scanner.nextLine());

		while (true) {

			try {
				System.out.println("Product Qty - ");
				product.setQuantity(Integer.parseInt(scanner.nextLine()));
				break;
			} catch (NumberFormatException e1) {
				log.error("Invalid qty entered. Please enter valid qty. ");
			}
		}

		try {
			productService.save(product);
			System.out.println("Product saved successfuly;");
		} catch (ServiceException e) {
			log.error("Error while saving the product.", e);
		}

	}

	public void displayOptions() {

		log.trace("Displaying available options. ");

		System.out.println("Enter your choice -");
		System.out.println("1. Add Product");
		System.out.println("2. Edit Product");
		System.out.println("3. Delete Product");
		System.out.println("4. Display All Product");
		System.out.println("5. Display product count");
		System.out.println("6. Find by id");
		System.out.println("x. Exit");
		System.out.println("-------------------------------------------");
	}
}
