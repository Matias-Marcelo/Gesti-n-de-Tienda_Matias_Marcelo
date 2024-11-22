package model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "products") // Define el nodo raíz en el XML
public class ProductList {
	private int total;
	
	private ArrayList<Product> products;  // Initialize products
	
	public ProductList() {};
	
	public ProductList(ArrayList<Product> products) {
		this.products = products;
	}
	
	@XmlElement(name="product")
	public ArrayList<Product> getProducts() {
		return products;
	}
	
	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	@XmlAttribute(name="total")
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	

	


}
