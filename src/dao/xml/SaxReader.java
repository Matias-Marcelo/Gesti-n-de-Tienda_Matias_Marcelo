package dao.xml;

import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import model.Amount;
import model.Product;

public class SaxReader extends DefaultHandler{
	private ArrayList<Product> products;
	private Product product;
	private String value;
    private String currency; // Para almacenar la moneda
	private String parsedElement;

	
	/**
	 * @return the products
	 */
	public ArrayList<Product> getProducts() {
		return products;
	}
	
	@Override
	public void startDocument() throws SAXException {
		this.products = new ArrayList<>();
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		switch (qName) {
		case "product":
            this.product = new Product("", null, 0); // Crea un objeto vacío; estableceremos los valores con setters después
			this.product.setName(attributes.getValue("name")!= null ? attributes.getValue("name") : "empty");
            break;
		case "wholesalerPrice":
			currency = attributes.getValue("currency");
			
			break;
		case "stock":
			break;
		}
		this.parsedElement = qName;

	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub
		if (qName.equals("product")) {
			this.products.add(product);
			this.parsedElement = "";
			
		}else if (qName.equals("wholesalerPrice")) {
			
			double priceValue = Double.valueOf(value); // Convertir el valor a double
	        Amount wholesalerAmount = new Amount(priceValue); // Crear una instancia de Amount
	        wholesalerAmount.setCurrency(currency); // Establecer la moneda
	        product.setWholesalerPrice(wholesalerAmount); // Asignar a product
			
		} else if (qName.equals("stock")) {
	        this.product.setStock(Integer.valueOf(value)); // Asignar stock
	    }
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		value = new String(ch, start, length);

		
	
	

}
	@Override
	public void endDocument() throws SAXException {
		printDocument();
	}

	private void printDocument() {
		for (Product p : products) {
			System.out.println(p.toString());
		}
	}
	
	
}
