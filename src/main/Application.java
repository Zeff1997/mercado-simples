package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import models.Product;

public class Application {

	private static Scanner sc = new Scanner(System.in);
	private static ArrayList<Product> listProducts;
	private static Map<Product, Integer> cart;

	public static void main(String[] args) {
		listProducts = new ArrayList<>();
		cart = new HashMap<>();
		menu();
	}

	private static void menu() {
		
		System.out.println("_________________________________________________________________________________");
		System.out.println("|-------------------------------------------------------------------------------|");
		System.out.println("|--------------------------Welcome to Daniel's Market---------------------------|");
		System.out.println("|-------------------------------------------------------------------------------|");
		System.out.println("|-----------------------------Select an option----------------------------------|");
		System.out.println("|-------------------------------------------------------------------------------|");
		System.out.println("|-------------------------| 1 - Resgister a product |---------------------------|");
		System.out.println("|-------------------------| 2 - List all products   |---------------------------|");
		System.out.println("|-------------------------| 3 - Buy                 |---------------------------|");
		System.out.println("|-------------------------| 4 - Cart                |---------------------------|");
		System.out.println("|-------------------------| 5 - Exit                |---------------------------|");
		System.out.println("|-------------------------------------------------------------------------------|");
		System.out.println("|_______________________________________________________________________________|");

			System.out.print("Option: ");
			try {
			int option = sc.nextInt();
			System.out.println();
			
			switch (option) {
			case 1:
				registerProduct();
				break;

			case 2:
				listProducts();
				break;

			case 3:
				buyProducts();
				break;

			case 4:
				cartItems();
				break;

			case 5:
				System.out.println("Thank you! Have a nice day!");
				break;

			default:
				System.out.println("Invalid number!");
				break;
			}
		}
		catch (InputMismatchException e) {
			System.out.println("Invalid Input, please type a number.");
		}
	}

	private static void registerProduct() {
		/*
		 * System.out.print("Product name: "); String name = sc.next();
		 * System.out.print("Product price: "); Double price = sc.nextDouble();
		 * System.out.println("Product registered successfully!");
		 */
		
		// tests
		Product p1 = new Product("Xbox 360", 500.0);
		Product p2 = new Product("Playstation 3", 650.0);
		Product p3 = new Product("Pc Gamer", 8500.0);
		listProducts.add(p1);
		listProducts.add(p2);
		listProducts.add(p3);
		
		// listProducts.add(new Product(name, price));
		menu();
	}

	private static void listProducts() {
		if (listProducts.size() > 0) {
			System.out.println("------Products List: ------");
			int a = 1;
			for (Product p : listProducts) {
				System.out.println(a + " - " + p);
				a += 1;
			}
		} else {
			System.out.println("No registered products");
		}
		menu();
	}

	private static void buyProducts() {
		if (listProducts.size() > 0) {
			System.out.println("------Available products: ------");
			int a = 1;
			for (Product p : listProducts) {
				System.out.println(a + " - " + p);
				a += 1;
			}

			System.out.print("Select a product: ");
			
			int id = Integer.parseInt(sc.next());
			boolean isPresent = false;

			for (Product p : listProducts) {
				if (p.getId() == id) {
					int quant = 0;
					try {
						quant = cart.get(p);
						cart.put(p, quant +1); // Checks if the product is in the cart.
					} catch (NullPointerException e) {
						// if the product is the first in the cart
						cart.put(p, 1);
					}
					System.out.println(p.getName() + " has been added to cart");
					System.out.println();
					isPresent = true;

					if (isPresent) {
						OptionsBuyProducts();
					} 
					else {
						System.out.println("Product not found.");
						menu();
					}
				}
			}
		} 
		else {
			System.out.println("Product not available.");
			menu();
		}
	}

	private static void cartItems() {
		System.out.println("------ Products in your cart: ------");
		if (cart.size() > 0) {
			for (Product p : cart.keySet()) {
				System.out.println("Product: " + p + " Amount: " + cart.get(p));
			}
			OptionsCartItems();
		}
		else {
			System.out.println("------Your cart is empty------");
			menu();
		}
		
	}

	private static void finalizePurchase() {
		Double purchaseValue = 0.0;
		System.out.println("------Your products: ------");

		for (Product p : cart.keySet()) {
			int quant = cart.get(p);
			purchaseValue += p.getPrice() * quant;
			System.out.println(p);
			System.out.println("Amount: " + quant);
			System.out.println("------------------------");
		}
		
		System.out.println("The purchase price is: " + utils.Format.doubleToString(purchaseValue));
		System.out.println("Thank you for your purchase, come back soon.");
		cart.clear();
		menu();
	}
	
	private static void OptionsBuyProducts() {
		System.out.println("Would you like to add another product to the cart?");
		System.out.println("Press 1 to YES, 2 to return to menu or 3 to finalize your purchase");
		int option = Integer.parseInt(sc.next());
		System.out.println();
		switch (option) {
		case 1:
			buyProducts();
			break;
		case 2:
			menu();
			break;
		case 3:
			finalizePurchase();
			break;
		default:
			System.out.println("Invalid option, returning to Menu.");
			break;
		}
		
	}
	
	private static void OptionsCartItems() {
		System.out.println("------What would you like to do?------");
		System.out.println("1 - Add another product");
		System.out.println("2 - Return to menu");
		System.out.println("3 - Finalize your purchase");
		System.out.println("4 - Remove a product");
		int option = Integer.parseInt(sc.next());
		System.out.println();
		switch (option) {
		case 1:
			buyProducts();
			break;
		
		case 2:
			menu();
			break;
		
		case 3:
			finalizePurchase();
			break;
		
		case 4:
			System.out.println("Enter the ID of the product to be removed from the cart:");
			int id = Integer.parseInt(sc.next());
			for (Product p : listProducts) {
				if (p.getId() == id) {
					cart.remove(p);
				}
			}
			cartItems();
			break;
		default:
			System.out.println("Invalid option, returning to Menu.");
			break;
		}
	}

}
