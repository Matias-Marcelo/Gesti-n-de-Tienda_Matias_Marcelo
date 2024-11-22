package dao.jaxb;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import model.Product;
import model.ProductList;

public class JaxbUnMarshaller {
	public ArrayList<Product> unMarshalXml() {

		// read from xml to java object
				ProductList products = null;
				try {
					JAXBContext context = JAXBContext.newInstance(ProductList.class);
					Unmarshaller unmarshaller = context.createUnmarshaller();
					System.out.println("unmarshalling...");
					products = (ProductList) unmarshaller.unmarshal(new File("jaxb/inputInventory.xml"));
				} catch (JAXBException e) 	{
					e.printStackTrace();
				}

				// print products
				if (products == null)
					System.out.println("Error unmarshalling");
				else {
				    System.out.println("Productos deserializados correctamente");

					for (Product p : products.getProducts()) {
						System.out.println(p);
					}
				}
		        return (products != null) ? products.getProducts() : new ArrayList<>();
	}
	

}
