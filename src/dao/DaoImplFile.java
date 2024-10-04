package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import main.Shop;
import model.Amount;
import model.Employee;
import model.Product;
import model.Sale;

public class DaoImplFile implements Dao {
	private ArrayList<Product> inventory;

	public DaoImplFile() {
		// TODO Auto-generated constructor stub
	}

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
		ArrayList<Product> inventoryLoaded = new ArrayList<Product>();
		try {
			File files = new File("files/inputinventory.txt"); // File object to read from inputinventory.txt file
			Scanner scanner = new Scanner(files); // Scanner object to read from the file
			while (scanner.hasNextLine()) {
				String data = scanner.nextLine(); // Read a line from the file
				String[] line1 = data.split(";"); // Split the line based on semicolon
				String nombre = "";
				double wholesalerPrice = 0.0;
				int stock = 0;
				double publicPrice = 0.0;
				// System.out.println(line1);
				for (int i = 0; i < line1.length; i++) {
					String[] line2 = line1[i].split(":"); // Split each part of the line based on colon

					switch (i) {
					case 0:
						nombre = line2[1]; // Assign name from the split line
						break;
					case 1:
						wholesalerPrice = Double.parseDouble(line2[1]); // Assign wholesaler price from the split line
						break;
					case 2:
						stock = Integer.parseInt(line2[1]); // Assign stock from the split line
						break;

					}
				}
				Amount amount = new Amount(wholesalerPrice);
				Product product = new Product(nombre, amount, true, stock, new Amount(amount.getValue() * 2)); // Crear
																												// un
																												// nuevo
																												// producto
				inventoryLoaded.add(product); // Añadir el producto a la lista

			}
			scanner.close(); // Close the scanner
		} catch (FileNotFoundException e) { // Catch FileNotFoundException
			System.out.println("An error occurred."); // Print error message
			e.printStackTrace(); // Print stack trace
		}

//	        addProduct(new Product("Manzana", 10.00, true, 10, 20.00));
//	        addProduct(new Product("Pera", 20.00, true, 20, 40.00));
//	        addProduct(new Product("Hamburguesa", 30.00, true, 30, 60.00));
//	        addProduct(new Product("Fresa", 5.00, true, 20, 10.00));

		return inventoryLoaded; // Devolver el ArrayList con los productos cargados
	}

	@Override
	public boolean writeInventory(ArrayList<Product> inventory) {
		// TODO Auto-generated method stub
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter formatData = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String formattedData = date.format(formatData);
		Scanner scanner = new Scanner(System.in);
		LocalDate data = LocalDate.now();

		try {
			// Create a new file with the current date in the filename
			File files = new File("files/inventory_" + data + ".txt");
			FileWriter write = new FileWriter(files, true);
			PrintWriter print = new PrintWriter(write);
			int counterProducts = 1;
			// Iterate through each sale and write its details to the file
			for (Product product : inventory) {
				// Write the client's name and the date and time of the sale
				StringBuilder line = new StringBuilder(counterProducts + ";Product:" + product.getName() + ";Stock:" + product.getStock()+";" + "\n");
				print.write(line.toString());

				// Write the list of products purchased with their prices
				// for (Product products : sale.getProducts()) {
				// product.append(";"+products.getName()+","+products.getPublicPrice());

				// }

				counterProducts++;

			}
			StringBuilder secondLine = new StringBuilder("Número total de productos: " + counterProducts+";");

			print.write(secondLine.toString());
			print.write("\n");
			print.close();
			write.close();
		} catch (IOException e) {
			// Handle errors if file creation or writing fails
			System.out.println("ERROR");
			e.printStackTrace();

			return false;
		}
		return true;

	}

}
