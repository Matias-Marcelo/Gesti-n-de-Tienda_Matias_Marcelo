package dao.xml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import dao.Dao;
import dao.DaoImplXml;
import model.Product;

public class DomWriter {
    private ArrayList<Product> inventory;

	private Document document;




	public boolean writeToXML(ArrayList<Product> inventory) {	
		
	    if (inventory == null) {
	        System.out.println("La lista de productos es null");
	        return false; // Maneja el error
	    }
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.newDocument();
			
			//PARENT NODE
			// Crear el elemento raíz
			Element productsRoot = document.createElement("products");
			document.appendChild(productsRoot);
			int counter = 0;
			for (Product product : inventory) {
				
			counter++;

			Element productElement = document.createElement("product");
			productElement.setAttribute("id", String.valueOf(product.getId()));
			productsRoot.appendChild(productElement);
			
			//Add Name
			Element name = document.createElement("name");
			name.setTextContent(product.getName());
			productElement.appendChild(name);
			
			// Añadir Price
            Element price = document.createElement("Price");
            String currency = product.getWholesalerPrice().getCurrency(); // Obtiene la moneda
            price.setAttribute("currency",currency);
            double wholesalerPriceValue = ((Product) product).getWholesalerPrice().getValue(); // Obtiene el valor
            price.setTextContent(String.valueOf(wholesalerPriceValue)); // Establece el texto del elemento
            productElement.appendChild(price);
            
            // Añadir stock
            Element stockElement = document.createElement("stock");
            stockElement.appendChild(document.createTextNode(String.valueOf(((Product) product).getStock())));
            productElement.appendChild(stockElement);
            
			}
	        productsRoot.setAttribute("total", String.valueOf(counter));
	        
			
	        // Llamada a generateXML para escribir en el archivo
            generateXML();
		} catch (ParserConfigurationException e) {
			
			System.out.println("ERROR generating document");
			return false;
		}
		
		return true;
	}
	
	public boolean generateXML() {
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
	        
			Source source = new DOMSource(document);
			File file = new File("xml/inventory_" + data + ".xml");
			FileWriter fw = new FileWriter(file);
			PrintWriter pw = new PrintWriter(fw);
			Result result = new StreamResult(pw);
			transformer.transform(source, result);

		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;

		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
