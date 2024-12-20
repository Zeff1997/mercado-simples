package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import Color.ConsoleColors;
import exceptions.BuyException;
import models.Product;

public class Application {

	private static Scanner sc = new Scanner(System.in);
	private static ArrayList<Product> listProducts;
	private static Map<Product, Integer> cart;

	public static void main(String[] args) {
		listProducts = new ArrayList<>();
		cart = new HashMap<>();
		menu();
		sc.close();
	}

	private static void menu() {

		int option = 1;
		do {
			System.out.println(ConsoleColors.GREEN + "_________________________________________________________________________________" + ConsoleColors.RESET);
			System.out.println(ConsoleColors.GREEN +"|-------------------------------------------------------------------------------|" + ConsoleColors.RESET);
			System.out.println(ConsoleColors.GREEN +"|--------------------------" + ConsoleColors.RESET + "Welcome to Daniel's Market"+ ConsoleColors.GREEN +"---------------------------|" + ConsoleColors.RESET);
			System.out.println(ConsoleColors.GREEN +"|-------------------------------------------------------------------------------|" + ConsoleColors.RESET);
			System.out.println(ConsoleColors.GREEN +"|-----------------------------" + ConsoleColors.RESET + "Select an option" + ConsoleColors.GREEN +"----------------------------------|" + ConsoleColors.RESET);
			System.out.println(ConsoleColors.GREEN +"|-------------------------------------------------------------------------------|" + ConsoleColors.RESET);
			System.out.println(ConsoleColors.GREEN +"|-------------------------|" + ConsoleColors.RESET+ " 1 - Resgister a product " + ConsoleColors.GREEN + "|---------------------------|" + ConsoleColors.RESET);
			System.out.println(ConsoleColors.GREEN +"|-------------------------|" + ConsoleColors.RESET + " 2 - List all products   "+ ConsoleColors.GREEN + "|---------------------------|" + ConsoleColors.RESET);
			System.out.println(ConsoleColors.GREEN +"|-------------------------|" + ConsoleColors.RESET + " 3 - Buy                 " + ConsoleColors.GREEN +"|---------------------------|" + ConsoleColors.RESET);
			System.out.println(ConsoleColors.GREEN + "|-------------------------|" + ConsoleColors.RESET + " 4 - Cart                " + ConsoleColors.GREEN + "|---------------------------|" + ConsoleColors.RESET);
			System.out.println(ConsoleColors.GREEN + "|-------------------------|" + ConsoleColors.RED +" 5 - Exit                "+ ConsoleColors.GREEN + "|---------------------------|" + ConsoleColors.RESET);
			System.out.println( ConsoleColors.GREEN +"|-------------------------------------------------------------------------------|" + ConsoleColors.RESET);
			System.out.println(ConsoleColors.GREEN + "|_______________________________________________________________________________|" + ConsoleColors.RESET);
			System.out.print("Option: ");

			try {
				option = sc.nextInt();
				System.out.println();
			}

			catch (InputMismatchException e) {
				System.out.println("Invalid Input, please enter a number.");
				sc.nextLine();
				continue;
			} 
			catch (RuntimeException e) {
				System.out.println("Unexpected error.");
				sc.nextLine();
				continue;
			}

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
				System.out.println(ConsoleColors.CYAN +"Thank you! Have a nice day!" + ConsoleColors.RESET);
				System.exit(0);
				//option = 0;
				break;

			default:
				System.out.println("Invalid number! Please enter a valid number.");
				break;
			}
		} 
		while (option != 0);
	}

	private static void registerProduct() {

		
		System.out.print("Product name: ");
		String name = null;
		sc.nextLine();
		name = sc.nextLine();
		Double price = null;
		boolean helper = true;
		do {
			try {
				System.out.print("Product price: ");
				price = sc.nextDouble();
				helper = true;
			} 
			catch (InputMismatchException e) {
				System.out.println("Invalid price! Please provide a valid price. \n");
				sc.nextLine();
				helper = false;
				continue;
			}
		}
		while (!helper);
		if (name != null && price != null) {
			System.out.println("Product registered successfully!");
			listProducts.add(new Product(name, price));
		}
		
		/* Tests
		 Product p1 = new Product("Xbox 360", 500.0); 
		 Product p2 = new Product("Playstation 3", 650.0); 
		 Product p3 = new Product("Pc Gamer", 8500.0); 
		 listProducts.add(p1); listProducts.add(p2); listProducts.add(p3);
		 */
		
	}

	private static void listProducts() {
		if (listProducts.size() > 0) {
			System.out.println("------Products List: ------");
			int a = 1;
			for (Product p : listProducts) {
				System.out.println(a + " - " + p);
				a += 1;
			}
		} 
		else {
			System.out.println("No registered products");
		}
		
	}

	private static void buyProducts() {
		if (listProducts.size() > 0) {
			System.out.println("------Available products: ------");
			int a = 1;
			for (Product p : listProducts) {
				System.out.println(a + " - " + p);
				a += 1;
			}

			int id = 1;
			int quantItem = 1;
			do {
				try {
					
					System.out.print("Select a product: ");
					id = Integer.parseInt(sc.next());
					if (id < 1 || id > listProducts.size()) {
						throw new BuyException("Please enter a valid Id product.\n");
					}
				
					System.out.print("Quantity you would like to add to cart: ");
					quantItem = Integer.parseInt(sc.next());
					if (quantItem <= 0) {
						throw new BuyException("Please enter a valid quantity.\n");	
					}
				}
				catch(NumberFormatException e) {
					System.out.println("Invalid Input! Please enter a number.\n");
					sc.nextLine();
					buyProducts();
				}
				catch (InputMismatchException e) {
					System.out.println("Invalid Input! Please enter a number.\n");
					sc.nextLine();
					buyProducts();	
				} 
				catch (BuyException e) {
					System.out.println(e.getMessage());
					buyProducts();
				}
				catch (RuntimeException e) {
					System.out.println("Unexpected Error.");
				}
			}
			while( quantItem <= 0);
			
			boolean isPresent = false;
			for (Product p : listProducts) {
				if (p.getId() == id) {
					
					try {
						quantItem = cart.get(p);
						cart.put(p, quantItem); // Checks if the product is in the cart.
					} 
					catch (NullPointerException e) {
						// if the product is the first in the cart
						cart.put(p, quantItem);
					}
					System.out.println(p.getName() + " has been added to cart");
					System.out.println();
					isPresent = true;

					if (isPresent) {
						OptionsBuyProducts();
					}
					else {
						System.out.println("Product not found.");
						
					}
				}
			}
		}
		else {
			System.out.println("Product not available.");
			
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
		
	}

	private static void OptionsBuyProducts() {
		System.out.println("Would you like to add another product to the cart?");
		System.out.println("Press 1 to Yes \nPress 2 to return to menu \nPress 3 to view your cart \nPress 4 to to finalize your purchase");
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
			cartItems();
			break;
			
		case 4:
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
