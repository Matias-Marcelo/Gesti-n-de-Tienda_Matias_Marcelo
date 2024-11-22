package dao;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import dao.jaxb.JaxbMarshaller;
import dao.jaxb.JaxbUnMarshaller;
import dao.xml.DomWriter;
import model.Employee;
import model.Product;
import model.ProductList;

public class DaoImplJaxb implements Dao{
	private ArrayList<Product> products = new ArrayList<>();  // Initialize products

	@Override
	public void connect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Employee getEmployee(int employeeId, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void disconnect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Product> getInventory() {
		// TODO Auto-generated method stub

		JaxbUnMarshaller unmarshal = new JaxbUnMarshaller();
		
		products = unmarshal.unMarshalXml();
		
		return products;
	}

	@Override
	public boolean writeInventory(ArrayList<Product> inventory) {
		// TODO Auto-generated method stub
		
		JaxbMarshaller marshal = new JaxbMarshaller();
		
		
		return marshal.marshalXml(inventory);
	}

}
