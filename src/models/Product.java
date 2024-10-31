package models;

import java.util.Objects;

public class Product {
	private static int count = 1;
	private int id;
	private String name;
	private Double price;
	
	public Product() {
	}
	
	public Product(String name, Double price) {
		this.id = count;
		this.name = name;
		this.price = price;
		Product.count += 1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Product id: " + id + ", name: " + name + ", price: " + String.format("%.2f", price);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(name, other.name);
	}

	

}
