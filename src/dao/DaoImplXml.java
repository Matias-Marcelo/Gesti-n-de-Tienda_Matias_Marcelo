package dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import model.Employee;
import model.Product;
import dao.xml.DomWriter;
import dao.xml.SaxReader;


public class DaoImplXml implements Dao{

	private SaxReader saxReader = new SaxReader();
	private ArrayList<Product> products;



	@Override
	public void connect() {
		// TODO Auto-generated method stub	
	}



	@Override
	public void disconnect() {
		// TODO Auto-generated method stub
	}

	@Override
	public Employee getEmployee(int employeeId, String password) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ArrayList<Product> getInventory() {
		// TODO Auto-generated method stub
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser;
    	try {
			parser = factory.newSAXParser();
			File file = new File("xml/inputInventory.xml");
			SaxReader saxReader = new SaxReader();
			parser.parse(file, saxReader);
			products = saxReader.getProducts();
			
		} catch (ParserConfigurationException | SAXException e) {
			System.out.println("ERROR creating the parser");
		} catch (IOException e) {
			System.out.println("ERROR file not found");
		}
		return products;

	}
	
	@Override
	public boolean writeInventory(ArrayList<Product> inventory) {
		// TODO Auto-generated method stub
	    DomWriter domWritter = new DomWriter();
	    
	    return domWritter.writeToXML(inventory); 
	}



	@Override
	public void addProduct(Product product) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void updateProduct(Product product) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void deleteProduct(int productId) {
		// TODO Auto-generated method stub
		
	}

}
