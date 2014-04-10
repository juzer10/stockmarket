package stockmarket;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class EMAInput extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EMAInput frame = new EMAInput();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EMAInput() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(29, 103, 216, 32);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Enter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Symbol=textField.getText();
				EMA x=new EMA();
				try {
					x.run(Symbol);
				} catch (IOException | SQLException| ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				textField.setText("");
			}
		});
		btnNewButton.setBounds(255, 103, 89, 32);
		contentPane.add(btnNewButton);
		
		JTextArea txtrEnterStockSymbol = new JTextArea();
		txtrEnterStockSymbol.setFont(new Font("Calibri", Font.PLAIN, 14));
		txtrEnterStockSymbol.setBackground(UIManager.getColor("Spinner.foreground"));
		txtrEnterStockSymbol.setEditable(false);
		txtrEnterStockSymbol.setText("Enter Stock Symbol:");
		txtrEnterStockSymbol.setBounds(29, 71, 156, 21);
		contentPane.add(txtrEnterStockSymbol);
	}
}
