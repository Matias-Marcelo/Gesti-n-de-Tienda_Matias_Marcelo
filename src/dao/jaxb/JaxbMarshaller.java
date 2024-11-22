package dao.jaxb;

import java.io.File;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import model.Amount;
import model.Product;
import model.ProductList;


public class JaxbMarshaller {
    private ArrayList<Product> inventory;
    
		public boolean marshalXml (ArrayList<Product> inventory) {
			LocalDateTime date = LocalDateTime.now();
			DateTimeFormatter formatData = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			String formattedData = date.format(formatData);
			Scanner scanner = new Scanner(System.in);
			LocalDate data = LocalDate.now();
			try {
				TransformerFactory factory = TransformerFactory.newInstance();
				Transformer transformer = factory.newTransformer();
				// Configurar la salida para que sea indentada
		        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2"); // Establece el tamaño de la indentación
				JAXBContext context = JAXBContext.newInstance(ProductList.class);
				Marshaller marshaller = context.createMarshaller();
				System.out.println("marshalling... ");
				ProductList products = createXml(inventory);
				
				// Marshal a StringWriter para obtener el XML como String antes de escribir al archivo
				StringWriter stringWriter = new StringWriter();
				StreamResult stringResult = new StreamResult(stringWriter);
				marshaller.marshal(products, stringResult);
				
				// Ahora transformamos el StringWriter a formato con indentación
				StreamResult fileResult = new StreamResult(new File("jaxb/inventory_" + data + ".xml"));
				transformer.transform(new StreamSource(new java.io.StringReader(stringWriter.toString())), fileResult);

				return true;
			} catch (JAXBException e) {
				e.printStackTrace();
				return false;
			} catch (TransformerConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}

	private ProductList createXml(ArrayList<Product> inventory) {
		
		ArrayList<Product> products = new ArrayList<>();

//		products.add(new Product("Prod 1", new Price("Pound"), new Stock("12","orange"),units));
//		products.add(new Product("Prod 2", new Price("Dollar"), new Stock("56","brown"),units));
		
		for (Product product : inventory) {
			
			products.add(product);
			
		}
		
		
	

//		products.add(new Product("Prod 3", new Price("Euro"), new Stock("62","yellow"),units));
//		products.add(new Product("Prod 4", new Price("Dollar"), new Stock("806","green"),units));
//		products.add(new Product("Prod 5", new Price("Euro"), new Stock("50","dark blue"),units));
		
		// print products
		for (Product p : products) {
			System.out.println(p);
		}
		  ProductList productList = new ProductList(products);
		  productList.setTotal(products.size()); // Establece el total de productos
		return productList;
	}
}


