package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import main.Shop;

public class CashView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private Shop shop;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
	        Shop shop = new Shop(); // Create an instance of Shop
			CashView dialog = new CashView(shop);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setTitle("CashView");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * Create the dialog.
	 */
	public CashView(Shop shop) {
	    this.shop = shop; // Save the instance of Shop for later use.
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(201, 213, 242));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FlowLayout());		
		JLabel lblNewLabel = new JLabel("Dinero en caja:");
		lblNewLabel.setBounds(40, 44, 130, 16);
		contentPanel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setEditable(false); // We set the text field as non-editable.
		textField.setBounds(182, 39, 130, 26);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		 // Get the money in cash from the Shop class
        double cash = shop.getCash();
		textField.setText(Double.toString(cash)); // Show money in the JTextField.

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
				        dispose();

					}
					
				});
			}
		}
	}
}
