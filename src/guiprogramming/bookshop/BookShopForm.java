package guiprogramming.bookshop;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class BookShopForm {

	private JFrame frame;
	private JTextField txtAuthor;     
	private JTextField txtBookName;
	private JTextField txtEdition;
	private JLabel lblPrice;
	private JTextField txtPrice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookShopForm window = new BookShopForm();
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
	private BookDao daoBook;
	private JTable table;

	public BookShopForm() {
		
		daoBook = new BookDao();
		initialize();
		loadTable();
	}
	
	public void clearForm() {
		
		txtAuthor.setText("");
		txtBookName.setText("");  
		txtEdition.setText(""); 
		txtPrice.setText("");
		
		
	}
	
	public void loadTable() {
		ResultSet  rs = daoBook.loadTableData();
		
		table.setModel(DbUtils.resultSetToTableModel(rs));
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 998, 673);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblRegistration = new JLabel("Book Shop Management");
		lblRegistration.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblRegistration.setBounds(236, 11, 516, 68);
		frame.getContentPane().add(lblRegistration);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Book Registration Form", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 90, 450, 262);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblAuthor = new JLabel("Author Name :-");
		lblAuthor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAuthor.setBounds(20, 33, 147, 25);
		panel.add(lblAuthor);
		
		txtAuthor = new JTextField();
		txtAuthor.setBounds(170, 33, 200, 26);
		panel.add(txtAuthor);
		txtAuthor.setColumns(10);
		
		JLabel lblBook = new JLabel("Book Name  :-");
		lblBook.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBook.setBounds(20, 70, 147, 25);
		panel.add(lblBook);
		
		txtBookName = new JTextField();
		txtBookName.setColumns(10);
		txtBookName.setBounds(170, 69, 200, 26);
		panel.add(txtBookName);
		
		JLabel lblEdition = new JLabel("Edition  :-");
		lblEdition.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEdition.setBounds(20, 106, 147, 25);
		panel.add(lblEdition);
		
		txtEdition = new JTextField();
		txtEdition.setColumns(10);
		txtEdition.setBounds(170, 106, 200, 26);
		panel.add(txtEdition);
		
		lblPrice = new JLabel("Price :-");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPrice.setBounds(20, 143, 147, 25);
		panel.add(lblPrice);
		
		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(170, 142, 200, 26);
		panel.add(txtPrice);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String author = txtAuthor.getText();
				String book = txtBookName.getText();  
				String edition = txtEdition.getText(); 
				String price = txtPrice.getText();
				
				Book bookObject = new Book(author,book,edition,price);
				
				daoBook.insertBook(bookObject);				
				clearForm();	
			}
		});
		btnRegister.setBounds(168, 201, 93, 33);
		panel.add(btnRegister);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				clearForm();
				loadTable();
			}
		});
		btnReset.setBounds(271, 201, 93, 33);
		panel.add(btnReset);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(497, 100, 463, 252);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
	}
}
