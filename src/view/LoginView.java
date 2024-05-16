package view;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Employee;

import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;

public class LoginView extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField NúmeroEmpleado;
	private JPasswordField Password;
	private int contador = 0;
	//Attributes required for fields and exemption
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
					frame.setVisible(true);
					frame.setTitle("Login");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 832, 526);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(201, 213, 242));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane txtpnNmeroDeEmpleado = new JTextPane();
		txtpnNmeroDeEmpleado.setEditable(false);
		txtpnNmeroDeEmpleado.setEnabled(false);
		txtpnNmeroDeEmpleado.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		txtpnNmeroDeEmpleado.setBackground(new Color(201, 213, 242));
		txtpnNmeroDeEmpleado.setBounds(302, 83, 135, 16);
		txtpnNmeroDeEmpleado.setText("Número de empleado");
		contentPane.add(txtpnNmeroDeEmpleado);
		
		Password = new JPasswordField();
		Password.setBounds(302, 228, 244, 26);
		contentPane.add(Password);
		Password.setColumns(10);
		
		NúmeroEmpleado = new JTextField();
		NúmeroEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
			
		});
		

		
		NúmeroEmpleado.setBounds(302, 130, 244, 26);
		contentPane.add(NúmeroEmpleado);
		NúmeroEmpleado.setColumns(10);
		
		JButton Acceder = new JButton("Acceder");
		Acceder.setBounds(619, 416, 151, 36);
		
		//Add actionListener
		Acceder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//If button acceder is clicked then do everything below
			
			//Create object employee
			 Employee employee = new Employee();
			 //Save the information of the field in variables
			 int numEmpleado = Integer.parseInt(NúmeroEmpleado.getText());
			 String password = Password.getText(); 
	
			 //Logic for login authentication with correct values 
			 if(employee.login(numEmpleado, password)){
				 //If is correct then set shopView visible = true
				 ShopView shopView = new ShopView();
				 shopView.setVisible(true);
				 
			 }else{
				//Logic for login authentication with incorrect values 
				 //Calling Method for clean the fields after entering incorrect values
				 limpiarCampo();
				 //Attribute contador to count the number of times incorrect values have been entered
				 contador++;
				 //If contador is greater than or equal to 3 then show JOptionPane of exception
				 if(contador >= 3) {
					 
					 JOptionPane.showMessageDialog(null, "Se ha alcanzado el número máximo intentos");
					 dispose();
				 }else
				 JOptionPane.showMessageDialog(null,"Usuario o password incorrectos");

			 }
		}});
		this.dispose();
		contentPane.add(Acceder);
		
		JTextPane txtpnPassword = new JTextPane();
		txtpnPassword.setEnabled(false);
		txtpnPassword.setEditable(false);
		txtpnPassword.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		txtpnPassword.setBackground(new Color(201, 213, 242));
		txtpnPassword.setText("Password");
		txtpnPassword.setBounds(302, 182, 135, 16);
		contentPane.add(txtpnPassword);
		
		JTextPane txtpnLogin = new JTextPane();
		txtpnLogin.setEditable(false);
		txtpnLogin.setText("LOGIN");
		txtpnLogin.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		txtpnLogin.setBackground(new Color(201, 213, 242));
		txtpnLogin.setBounds(302, 28, 135, 16);
		contentPane.add(txtpnLogin);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("/Users/manana/Downloads/6426911.png"));
		lblNewLabel.setBounds(302, 266, 244, 232);
		contentPane.add(lblNewLabel);


	}
	
	//Method for cleaning the fields
	private void limpiarCampo() {
		
		
		NúmeroEmpleado.setText("");
		Password.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
