import java.util.List;
import java.util.Scanner;

import exceptions.ServiceException;
import modals.Product;
import services.ProductService;
import services.ProductServiceImpl;

public class ProductApp {

	private static ProductService productService = new ProductServiceImpl();

	public static void main(String[] args) {

		String input = "";

		do {

			displayOptions();

			Scanner scanner = new Scanner(System.in);
			input = scanner.next();

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

			case "x":
				return;

			default:
				System.out.println("Invalid choice. Please try again. ");
				break;
			}

		} while (!input.equalsIgnoreCase("X"));

	}

	private static void updateProduct() {

		Product product = new Product();
		Scanner scanner = new Scanner(System.in);

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

		try {
			product.setId(productId);
			productService.update(product);
			System.out.println("Product saved successfuly;");
		} catch (ServiceException e) {
			System.out.println(e.getLocalizedMessage());
		}

	}

	private static void deleteProduct() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter product Id to be deleted - ");
		int productId = scanner.nextInt();

		try {
			productService.delete(productId);
			System.out.println("Product has been removed.");
		} catch (ServiceException e) {
			System.out.println(e.getLocalizedMessage());
		}

	}

	private static void displayAllProducts() {

		List<Product> allProducts;
		try {
			allProducts = productService.findAll();
			System.out.println("Product ID \t Name \t Category \t Quantity");
			for (Product product : allProducts) {
				System.out.println(product.getId() + "\t" + product.getName() + "\t" + product.getCategory() + "\t"
						+ product.getQuantity());
			}
		} catch (ServiceException e) {
			System.out.println(e.getLocalizedMessage());
		}

	}

	public static void addProduct() {

		Product product = new Product();

		Scanner scanner = new Scanner(System.in);
		System.out.println("Product Name - ");
		product.setName(scanner.nextLine());
		System.out.println("Product Category - ");
		product.setCategory(scanner.nextLine());
		System.out.println("Product Code - ");
		product.setCode(scanner.nextLine());
		System.out.println("Product Qty - ");
		product.setQuantity(Integer.parseInt(scanner.nextLine()));

		try {
			productService.save(product);
			System.out.println("Product saved successfuly;");
		} catch (ServiceException e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

	public static void displayOptions() {

		System.out.println("Enter your choice -");
		System.out.println("1. Add Product");
		System.out.println("2. Edit Product");
		System.out.println("3. Delete Product");
		System.out.println("4. Display All Product");
		System.out.println("5. Sort Product ");
		System.out.println("x. Exit");
		System.out.println("-------------------------------------------");

	}

}
