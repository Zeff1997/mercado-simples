package main;

import java.util.ArrayList;
import java.util.HashMap;
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
		System.out.println("|-----------------------------Select an operation-------------------------------|");
		System.out.println("|-------------------------------------------------------------------------------|");
		System.out.println("|-------------------------| 1 - Resgister a product |---------------------------|");
		System.out.println("|-------------------------| 2 - List all products   |---------------------------|");
		System.out.println("|-------------------------| 3 - Buy                 |---------------------------|");
		System.out.println("|-------------------------| 4 - Cart                |---------------------------|");
		System.out.println("|-------------------------| 5 - Exit                |---------------------------|");
		System.out.println("|-------------------------------------------------------------------------------|");
		System.out.println("|_______________________________________________________________________________|");
		
		System.out.print("Operation: ");
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
			break;
			
		case 5:
			System.out.println("Thank you! Have a nice day!");

			break;
			
		default:
			System.out.println("Invalid operation!");
			break;
		}
	}
	
	private static void registerProduct() {
		/*
		System.out.print("Product name: ");
		String name = sc.next();
		System.out.print("Product price: ");
		Double price = sc.nextDouble();
		System.out.println("Product registered successfully!");
		*/
		// tests
		Product p1 = new Product("Xbox 360", 500.0);
		Product p2 = new Product("Playstation 3", 650.0);
		Product p3 = new Product("Pc Gamer", 8500.0);
		listProducts.add(p1);
		listProducts.add(p2);
		listProducts.add(p3);
		
		//listProducts.add(new Product(name, price));
		
		menu();
	}
	
	private static void listProducts() {
		if (listProducts.size() > 0) {
			System.out.println("Products List: ");
			int a = 1;
			for (Product p : listProducts) {
				System.out.println(a + " - " + p);
				a += 1;
			}
		}
		else {
			System.out.println("No registered products");
		}
		menu();
	}
	
	private static void buyProducts() {
		if (listProducts.size() > 0) {
			System.out.println("Products List: ");
		}
			
	}

}
