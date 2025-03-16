package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.Dao;
import dao.DaoImplJDBC;
import main.Shop;
import model.Amount;
import model.Product;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ProductView extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private int opcion;
	private Shop shop;
	private ArrayList<Product> inventory;
	private Dao dao;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
		    
			ProductView dialog = new ProductView(0, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setTitle("ProductView");

			

			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	

	
	/**
	 * Create the dialog.
	 */
	
	//Obtain the necessary parameters and initialise them
	public ProductView(int opcion, Shop shop) {
		
		 this.opcion = opcion;
		 this.shop = shop;
		 this.dao = new DaoImplJDBC();



  	   
  	   this.setTitle("Añadir Producto");
       getContentPane().setBackground(new Color(201, 213, 242));
       setForeground(new Color(0, 0, 0));
       getContentPane().setForeground(new Color(201, 213, 242));
       getContentPane().setLayout(null);
       
       JLabel lblNombreProducto = new JLabel("Nombre producto:");
       lblNombreProducto.setBounds(62, 29, 130, 16);
       getContentPane().add(lblNombreProducto);
       
       textField = new JTextField();
       textField.setEditable(true);
       textField.setColumns(10);
       textField.setBounds(199, 24, 130, 26);
       getContentPane().add(textField);
       
       JLabel lblStockProducto = new JLabel("Stock producto:");
       lblStockProducto.setBounds(62, 65, 130, 16);
       getContentPane().add(lblStockProducto);
       
       textField_1 = new JTextField();
       textField_1.setEditable(true);
       textField_1.setColumns(10);
       textField_1.setBounds(199, 62, 130, 26);
       getContentPane().add(textField_1);
       
       JLabel lblPrecioProducto = new JLabel("Precio producto:");
       lblPrecioProducto.setBounds(62, 103, 130, 16);
       getContentPane().add(lblPrecioProducto);
       textField_2 = new JTextField();
       textField_2.setEditable(true);
       textField_2.setColumns(10);
       textField_2.setBounds(199, 100, 130, 26);
       getContentPane().add(textField_2);
       //If opcion = 3 then hide textField_2 / lblPrecioProducto and set title "Añadir Stock"
       if(opcion == 3) {
    	   lblPrecioProducto.setVisible(false);
    	   textField_2.setVisible(false);	
    	   this.setTitle("Añadir Stock");
       }
       //If opcion = 9 then hide textField_2 / lblPrecioProducto , textField_1 / lblStockProducto and set title "Eliminar Producto"
       if(opcion == 9) {
    	   lblPrecioProducto.setVisible(false);
    	   textField_2.setVisible(false);		
    	   lblStockProducto.setVisible(false);
    	   textField_1.setVisible(false);
    	   this.setTitle("Eliminar Producto");

       }
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
					    if (e.getActionCommand().equals("OK")) {
					        if (opcion == 2) { // Option to add a producto
					        	
					        	

					            String productName = textField.getText();
					            int stock = Integer.parseInt(textField_1.getText());
					            double price = Double.parseDouble(textField_2.getText());
					            // Create a new instance of Amount for the price
					            Amount amount = new Amount(price);
					            
					           // Create a product and check if it exists with findProduct
					            Product productFind = shop.findProduct(productName);
					            if (productFind == null) {

						            Product product = new Product(productName, amount, true, stock, new Amount(amount.getValue() * 2));
						            product.setPrice(price);
						            //dao.addProduct(product);
						            shop.addProduct(product);			
						            
						            JOptionPane.showMessageDialog(null,"Producto añadido correctamente","Succes",JOptionPane.INFORMATION_MESSAGE);
					            } else {
						            JOptionPane.showMessageDialog(null,"Error, el producto ya existe","Error",JOptionPane.ERROR_MESSAGE);
					            }

					            
					            

					        } else if (opcion == 3) { // Option to add stock
					        	
					        	String productName = textField.getText();
					            int stock = Integer.parseInt(textField_1.getText());


					            Product product = shop.findProduct(productName);
					           
					            if (product != null) {
					                // Update the stock of the product
					                int updatedStock = product.getStock() + stock;
					          
					            	 shop.addStock(product, stock);
						            JOptionPane.showMessageDialog(null,"Se ha actualizado correctamente","Succes",JOptionPane.INFORMATION_MESSAGE);

					            } else {
						            JOptionPane.showMessageDialog(null,"Error, el producto no existe","Error",JOptionPane.ERROR_MESSAGE);
					            }
					        	
					        	
					        } else if (opcion == 9) { // Option to delete a product
					        	
					        	String productName = textField.getText();
					        	// Find the product in the inventory
					        	Product product = shop.findProduct(productName);
					        	int productId = product.getId();
					        			// Check if the product exists and is available
					            if (product != null && product.isAvailable()) {
					                // Set its availability to false, effectively removing it from the inventory
					            	//boolean productAvaible = true;
					            	//inventory = shop.getInventory();
					            	//inventory.remove(product);
					            	shop.deleteProduct(product,productId);
					            	 // If no more stock, set as not available to sale
					               // if (product.getStock() == 0) {
					                   // product.setAvailable(false);
					               // }
						            JOptionPane.showMessageDialog(null,"Producto eliminado correctamente","Succes",JOptionPane.INFORMATION_MESSAGE);
					            } else {
						            JOptionPane.showMessageDialog(null,"Producto no encontrado","Error",JOptionPane.ERROR_MESSAGE);

					            }

					           
					        	
					        	
					        }

					        // Close the window
					        dispose();
					    }
					}
					
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
				        // Close the window
				        dispose();

					}
					
				});
			}
		}
	}



	// En la clase ProductView
	//Set method to get inventory by the getInventory method
	public void setShop(Shop shop){
	    this.shop = shop;
	    this.inventory = shop.getInventory(); // Get shop inventory
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	
		
	}
}
