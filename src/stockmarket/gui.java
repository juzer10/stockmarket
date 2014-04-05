package stockmarket;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.TextArea;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.List;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JLabel;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;

import java.awt.SystemColor;
import java.awt.Color;

import javax.swing.UIManager;

public class gui {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui window = new gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 653, 430);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Button button = new Button("Update PHV");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] arguments = new String[] {"123"};
			    try {
					PastHistoryData.main(arguments);
				} catch (ClassNotFoundException | SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		button.setBounds(34, 136, 121, 43);
		frame.getContentPane().add(button);
		
		Button button_1 = new Button("Get Real Time Data");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] arguments = new String[] {"123"};
			    try {
					RealTimeStreaming.main(arguments);
				} catch (ClassNotFoundException | SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		button_1.setBounds(34, 266, 121, 43);
		frame.getContentPane().add(button_1);
		
		Button button_2 = new Button("Show PHV");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					DisplayPHV.display();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_2.setBounds(34, 200, 121, 43);
		frame.getContentPane().add(button_2);
		
		JTextArea txtrResultsWillBe = new JTextArea();
		txtrResultsWillBe.setText("Results will be displayed here");
		txtrResultsWillBe.setEditable(false);
		txtrResultsWillBe.setFont(new Font("Calibri", Font.PLAIN, 12));
		txtrResultsWillBe.setBounds(331, 28, 279, 307);
		frame.getContentPane().add(txtrResultsWillBe);
		
		textField = new JTextField();
		textField.setBounds(34, 50, 150, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JTextPane txtpnEnterAStocks = new JTextPane();
		txtpnEnterAStocks.setEditable(false);
		txtpnEnterAStocks.setFont(new Font("Calibri", Font.PLAIN, 12));
		txtpnEnterAStocks.setBackground(UIManager.getColor("Spinner.foreground"));
		txtpnEnterAStocks.setText("Enter a Stock's symbol:");
		txtpnEnterAStocks.setBounds(34, 28, 137, 20);
		frame.getContentPane().add(txtpnEnterAStocks);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String symbol = textField.getText();
				System.out.println(symbol);
				try {
					Operate.search(symbol);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSearch.setBounds(194, 49, 89, 20);
		frame.getContentPane().add(btnSearch);
		
		Button button_3 = new Button("Show Stock List");
		button_3.setBounds(194, 136, 121, 43);
		frame.getContentPane().add(button_3);

	}
}
