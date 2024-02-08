import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Form {

	private JFrame frame;
	private JTextField idfield;
	private JTextField Addressfield;
	private JTextField Contactfield;
	private JTable table;
	private JTextField Namefield;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form window = new Form();
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
	public Form() {
		initialize();
		connection();
		loaddata();
	}
	Connection cn= null;
	public void connection()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3308/register","root","");
		}
		catch (ClassNotFoundException | SQLException e)
		{
		
			e.printStackTrace();
		}
	}
	
	public void loaddata()
	{
		try 
		{
			PreparedStatement ps = cn.prepareStatement("select * from registration_data");
			
			ResultSet rs = ps.executeQuery();
		
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch (SQLException e)
		{
			
			e.printStackTrace();
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 554, 504);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel IDlable = new JLabel("ID");
		IDlable.setBounds(10, 62, 38, 19);
		frame.getContentPane().add(IDlable);
		
		JLabel Namelable = new JLabel("Name");
		Namelable.setBounds(10, 92, 46, 14);
		frame.getContentPane().add(Namelable);
		
		JLabel Genderlable = new JLabel("Gender");
		Genderlable.setBounds(10, 125, 46, 14);
		frame.getContentPane().add(Genderlable);
		
		JLabel Addresslable = new JLabel("Address");
		Addresslable.setBounds(10, 161, 46, 14);
		frame.getContentPane().add(Addresslable);
		
		JLabel Contactlabel = new JLabel("Contact");
		Contactlabel.setBounds(10, 195, 61, 14);
		frame.getContentPane().add(Contactlabel);
		
		idfield = new JTextField();
		idfield.setBounds(66, 56, 124, 23);
		frame.getContentPane().add(idfield);
		idfield.setColumns(10);
		
		Addressfield = new JTextField();
		Addressfield.setColumns(10);
		Addressfield.setBounds(66, 157, 124, 23);
		frame.getContentPane().add(Addressfield);
		
		Contactfield = new JTextField();
		Contactfield.setColumns(10);
		Contactfield.setBounds(66, 191, 124, 23);
		frame.getContentPane().add(Contactfield);
		
		JRadioButton malebutton = new JRadioButton("Male");
		malebutton.setBounds(62, 125, 53, 14);
		frame.getContentPane().add(malebutton);
		
		JRadioButton FemaleButton = new JRadioButton("Female");
		FemaleButton.setBounds(119, 125, 71, 14);
		frame.getContentPane().add(FemaleButton);
		
		ButtonGroup bg=new ButtonGroup();
		bg.add(malebutton);
		bg.add(FemaleButton);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(212, 14, 316, 342);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel Registrationlabel = new JLabel("Registration Form");
		Registrationlabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		Registrationlabel.setBounds(37, 14, 130, 25);
		frame.getContentPane().add(Registrationlabel);
		
		JButton RegisterButton = new JButton("Reset");
		RegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idfield.setText(" ");
				Namefield.setText(" ");
				Addressfield.setText(" ");
				Contactfield.setText(" ");
				
			}
		});
		RegisterButton.setBounds(37, 333, 89, 23);
		frame.getContentPane().add(RegisterButton);
		
		JButton Deletebutton = new JButton("Delete");
		Deletebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Enter the ID :- ");
				String id = idfield.getText().trim();
				int idfield = Integer.parseInt(id);
				try {
					PreparedStatement ps = cn.prepareStatement("delete from registration_data where id=?");
					ps.setString(1, id);
					
					int i = ps.executeUpdate();
					
					if(i>0)
					{
						JOptionPane.showMessageDialog(frame, "Data Deleted..!!!!");
						loaddata();
					}
				}
				catch (SQLException e1) 
				{
				
					e1.printStackTrace();
				}
			}
		});
		Deletebutton.setBounds(10, 299, 89, 23);
		frame.getContentPane().add(Deletebutton);
		
		JButton UpdateButton = new JButton("Update");
		UpdateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Enter the id:- ");
				String id = idfield.getText();
				int idfield1 = Integer.parseInt(id);
				String name = Namefield.getText();
				String Address = Addressfield.getText();
				String Contact = Contactfield.getText();
				int Contactfield1 = Integer.parseInt(Contact);
				String Gender = null;
				if(malebutton.isSelected())
				{
					Gender = "Male";
				}
				else if(FemaleButton.isSelected())
				{
					Gender = "Female";
				}
				try
				{
					PreparedStatement ps = cn.prepareStatement("update registration_data set Name=?,gender=?,Adress=?,Contact=? where id=?");
					ps.setInt(5, idfield1);
					ps.setString(1, name);
					ps.setString(2, Gender);
					ps.setString(3, Address);
					ps.setString(4, Contact);
					
					
					int i = ps.executeUpdate();
					
					if(i>0)
					{
						JOptionPane.showMessageDialog(frame, "Data Updated..!!!");
					}
					else
					{
						System.out.println("Error.!!!");
					}
					
				}
				catch (SQLException e1) 
				{
				
					e1.printStackTrace();
				}
			}
		});
		UpdateButton.setBounds(113, 299, 89, 23);
		frame.getContentPane().add(UpdateButton);
		
		JButton Exitbutton = new JButton("Exit");
		Exitbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		Exitbutton.setBounds(10, 263, 89, 25);
		frame.getContentPane().add(Exitbutton);
		
		JButton Registerbutton = new JButton("Register");
		Registerbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String id = idfield.getText();
				int idfield1 = Integer.parseInt(id);
				String Name = Namefield.getText();
				String Address = Addressfield.getText();
				String Contact = Contactfield.getText();
				int Contactfield1 = Integer.parseInt(Contact);
				String Gender = null;
				if(malebutton.isSelected())
				{
					Gender = "Male";
				}
				else if(FemaleButton.isSelected())
				{
					Gender = "Female";
				}
				
				
				try
				{
					PreparedStatement ps1 = cn.prepareStatement("select * from registration_data where id=?");
					ps1.setInt(1, idfield1);
					ResultSet rs = ps1.executeQuery();
					
					if(rs.next())
					{
						JOptionPane.showMessageDialog(frame, "User Already Exist..!!!!");
					}
					else
					{
					PreparedStatement ps = cn.prepareStatement("insert into registration_data values(?,?,?,?,?,?)");
					ps.setInt(1, 0);
					ps.setInt(2, idfield1);
					ps.setString(3, Name);
					ps.setString(4, Gender);
					ps.setString(5, Address);
					ps.setInt(6, Contactfield1);
					
					int i = ps.executeUpdate();
				
					if(i>0)
					{
						JOptionPane.showMessageDialog(frame, "Registration Successfully....!!");
						Form.this.idfield.setText(" ");
						Namefield.setText(" ");
						Addressfield.setText(" ");
						Form.this.Contactfield.setText(" ");
						loaddata();
					}
					}
				}
				catch (SQLException e1)
				{
				
					e1.printStackTrace();
				}
			
			}
		});
		Registerbutton.setBounds(117, 264, 89, 24);
		frame.getContentPane().add(Registerbutton);
		
		Namefield = new JTextField();
		Namefield.setColumns(10);
		Namefield.setBounds(66, 89, 124, 23);
		frame.getContentPane().add(Namefield);
	}
}
