package view;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.Shop;
import model.Product;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class ShopView extends JFrame implements ActionListener, KeyListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private Shop shop; // Declaration of the variable shop as a member variable
    private boolean keyAlreadyPressed = false; //boolean to check that it is pressed only once
	private ArrayList<Product>inventory; //Attribute of the arraylist inventory of the products on class shop

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopView frame = new ShopView();
					frame.setVisible(true);
					frame.setTitle("ShopView");

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ShopView() {
		Shop shop = new Shop();
		shop.loadInventory(); // Call method loadInventory();
		this.shop = shop;
        this.addKeyListener(this);
        this.setFocusable(true); // Ensure that the window can receive keyboard events.
        

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 829, 467);
		contentPane = new JPanel();
		contentPane.setAlignmentY(Component.TOP_ALIGNMENT);
		contentPane.setBackground(new Color(201, 213, 242));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("1. Contar caja");

		btnNewButton.setBounds(280, 83, 147, 48);
		btnNewButton.addActionListener(this);
		btnNewButton.setActionCommand("Cash");
		btnNewButton.addActionListener(new ActionListener(){
			

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				
				
			}
			
			
		});
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("2. Añadir producto");
		btnNewButton_1.setBounds(280, 160, 147, 48);
		btnNewButton_1.setActionCommand("");
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setActionCommand("AddProduct");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("3. Añadir stock");
		btnNewButton_2.setBounds(280, 244, 147, 48);
		btnNewButton_2.addActionListener(this);
		btnNewButton_2.setActionCommand("AddStock");
		btnNewButton_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			
			
		});
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("9. Eliminar producto");
		btnNewButton_3.setBounds(280, 332, 147, 41);
		btnNewButton_3.addActionListener(this);
		btnNewButton_3.setActionCommand("DeleteProduct");
		btnNewButton_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		contentPane.add(btnNewButton_3);
		
		JTextPane txtpnRewr = new JTextPane();
		txtpnRewr.setEditable(false);
		txtpnRewr.setBackground(new Color(201, 213, 242));
		txtpnRewr.setBounds(128, 22, 287, 16);
		txtpnRewr.setText("Seleccione o pulse una opción:");
		contentPane.add(txtpnRewr);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("/Users/manana/Downloads/cestafinal.png"));
		lblNewLabel.setBounds(503, 102, 262, 230);
		contentPane.add(lblNewLabel);
		this.addKeyListener(this);
		
	}


	public void openCashView(){
		//Method to open CashView passing object shop
		CashView cashDialog = new CashView(shop);
		//Show CashView
		cashDialog.setVisible(true);
		cashDialog.setTitle("CashView");

	}
	public void openProductView(int opcion, Shop shop) {
		//Method to open ProductView passing objects option, shop
	    ProductView productDialog = new ProductView(opcion, shop);
		//Show ProductView
		productDialog.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//ActionPerformed method for click-through menu operation
		 String comando = e.getActionCommand();
		 //Save in string variable the commands with their previously edited names
	        switch (comando) {
	       //Switch to call the corresponding method according to the command in the string
	            case "Cash":
	                openCashView();
	                break;
	            case "AddProduct":
	                setTitle("Añadir Producto");
	                openProductView(2,shop);
	                break;
	            case "AddStock":
	            	setTitle("Añadir Stock");
	            	openProductView(3,shop);
	            	break;
	            case "DeleteProduct":
	                setTitle("Eliminar Producto");
	                openProductView(9,shop);
	                break;
	        }	
	}
		
	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		 System.out.println("Escribió una tecla");
	}

    @Override
    public void keyPressed(KeyEvent e) {
    	//KeyPressed method for operating the menu with buttons.
        int key = e.getKeyCode();
        //Boolean to check that it is pressed only once
        if (!keyAlreadyPressed) {
 	       //Switch to call the corresponding method according to the command in the int.

        switch (key) {
            case KeyEvent.VK_1: // Opción 1: Contar caja
                openCashView();
                break;
            case KeyEvent.VK_2: // Opción 2: Añadir producto
                openProductView(2,shop);
                break;
            case KeyEvent.VK_3: // Opción 3: Añadir stock
                openProductView(3,shop);
                break;
            case KeyEvent.VK_9: // Opción 9: Eliminar producto
                openProductView(9,shop);
                break;
            default:
                break;
        }
        keyAlreadyPressed = true;

        }
        
    }

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
        keyAlreadyPressed = false;

		System.out.println("Soltó una tecla");
		
	}
}
