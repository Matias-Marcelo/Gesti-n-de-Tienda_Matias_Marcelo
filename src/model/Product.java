// The Product class represents a product in the store
package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

// Product class with properties id, name, publicPrice, wholesalerPrice, available, stock, and totalProducts
@XmlRootElement(name = "product") // Indica que esta clase será el nodo raíz en el XML
@XmlType(propOrder = { "available", "wholesalerPrice", "publicPrice", "stock"})
public class Product {
    private int id;
    private String name;
    private Amount publicPrice;
    private Amount wholesalerPrice;
    private boolean available = true ;
    private int stock;
    private static int totalProducts;

    // Constant representing the expiration rate for products
    static double EXPIRATION_RATE = 0.40;

    // Constructor to initialize a Product object with name, wholesalerPrice, availability, stock, and publicPrice
    public Product(String name, Amount wholesalerPrice, boolean available, int stock, Amount publicPrice) {
        super();
        this.id = totalProducts + 1;
        this.name = name;
        this.wholesalerPrice = wholesalerPrice;
        this.available = available;
        this.stock = stock;
        this.publicPrice = publicPrice;
        totalProducts++;
    }
    
    public Product() {
    	super();
    	//this.id = ++totalProducts;
    }



	// Getter for the id property
    @XmlAttribute(name="id")
    public int getId() {
        return id;
    }

    // Setter for the id property
    public void setId(int id) {
        this.id = id;
    }

    // Getter for the name property
	@XmlAttribute(name="name")
    public String getName() {
        return name;
    }

    // Setter for the name property
    public void setName(String name) {
        this.name = name;
    }

    // Getter for the publicPrice property
    @XmlElement(name="publicPrice")
    public Amount getPublicPrice() {
        return publicPrice;
    }

    // Setter for the publicPrice property
    public void setPublicPrice(Amount publicPrice) {
        this.publicPrice = publicPrice;
    }

    // Getter for the wholesalerPrice property
    @XmlElement(name = "wholesalerPrice")
    public Amount getWholesalerPrice() {
        return wholesalerPrice;
    }

    // Setter for the wholesalerPrice property
    public void setWholesalerPrice(Amount wholesalerPrice) {
        this.wholesalerPrice = wholesalerPrice;
        this.publicPrice = new  Amount(wholesalerPrice.getValue() * 2);
    }

    // Getter for the available property
    @XmlElement(name="available")
    public boolean isAvailable() {
        return available;
    }

    // Setter for the available property
    public void setAvailable(boolean available) {
        this.available = available;
    }

    // Getter for the stock property
    @XmlElement(name = "stock")
    public int getStock() {
        return stock;
    }

    // Setter for the stock property
    public void setStock(int stock) {
        this.stock = stock;
    }

    // Getter for the totalProducts property
    public static int getTotalProducts() {
        return totalProducts;
    }

    // Setter for the totalProducts property
    public static void setTotalProducts(int totalProducts) {
        Product.totalProducts = totalProducts;
    }

    // Method to simulate expiration and reduce the publicPrice
    public double expire() {
        
    	this.publicPrice.setValue(this.getPublicPrice().getValue() * EXPIRATION_RATE);
    	return this.getPublicPrice().getValue();
    }

    // Override toString method to provide a string representation of the Product object
    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", publicPrice=" + publicPrice + ", wholesalerPrice="
                + wholesalerPrice + ", available=" + available + ", stock=" + stock + "\n]";
    }

	
}