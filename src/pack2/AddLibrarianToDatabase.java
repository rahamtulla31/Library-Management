package pack2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddLibrarianToDatabase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new addLib();
	}

}

class addLib extends JFrame implements ActionListener{

	JLabel namelabel,username,passwordlabel,title;
	JTextField nametext,passwordtext,usernametext;
	JButton button,backButton;
	public addLib() {
		
		title= new JLabel("Add Librarian");
        namelabel= new JLabel("Enter Name: ");
        username=new JLabel("Enter username");
        passwordlabel = new JLabel("Enter password: ");
        nametext = new JTextField(15);
        passwordtext = new JTextField(15);
        usernametext= new JTextField(15);

        button = new JButton("Submit");
        button.addActionListener(this);

        backButton=new JButton("← Back");
        backButton.addActionListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,250);
        setLayout(null);
        title.setBounds(80, 0, 150, 20);
        add(title);
        namelabel.setBounds(10,20,80,50);
        add(namelabel);
        nametext.setBounds(110,35,150,25);
        add(nametext);
        username.setBounds(10,60,100,50);
        add(username);
        
        usernametext.setBounds(110,75,150,25);
        add(usernametext);
        
        passwordlabel.setBounds(10,100,100,50);
        add(passwordlabel);
        passwordtext.setBounds(110,115,150,25);
        add(passwordtext);

        button.setBounds(150,155,80,25);
        backButton.setBounds(40,155,80,25);
        add(button);
        add(backButton);
        setVisible(true);

	}
   
	public void actionPerformed(ActionEvent e) {
	    if (e.getSource() == button) {
	        String name = nametext.getText();
	        String username = usernametext.getText();
	        String password = passwordtext.getText();

	        try {
	            // Establish database connection
	            Class.forName("oracle.jdbc.OracleDriver");
	            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "SYSTEM");

	            // Check if the username already exists
	            PreparedStatement checkStatement = connection.prepareStatement("SELECT username FROM LIBRARIAN WHERE username = ?");
	            checkStatement.setString(1, username);
	            ResultSet resultSet = checkStatement.executeQuery();

	            if (resultSet.next()) {
	                // Username already exists
	                JOptionPane.showMessageDialog(null, "Username already exists", "Error", JOptionPane.ERROR_MESSAGE);
	            } else {
	                // Username is unique, proceed with insertion
	                String sql = "INSERT INTO LIBRARIAN (name, username, password) VALUES (?, ?, ?)";
	                PreparedStatement preparedStatement = connection.prepareStatement(sql);
	                preparedStatement.setString(1, name);
	                preparedStatement.setString(2, username);
	                preparedStatement.setString(3, password);

	                // Execute the SQL statement
	                int rowsAffected = preparedStatement.executeUpdate();
	                if (rowsAffected > 0) {
	                	JOptionPane.showMessageDialog(null, "User Added Successfuly", "Success", JOptionPane.INFORMATION_MESSAGE);
	                } else {
	                	JOptionPane.showMessageDialog(null, "Failed to add user", "Error", JOptionPane.ERROR_MESSAGE);

	                }

	                // Close resources
	                preparedStatement.close();
	            }

	            // Close resources
	            resultSet.close();
	            checkStatement.close();
	            connection.close();
	        } catch (SQLException | ClassNotFoundException ex) {
	            ex.printStackTrace();
	            System.out.println("Error occurred: " + ex.getMessage());
	        }
	    } else if (e.getSource() == backButton) {
	        new WhatAdminDo();
	        dispose();
	    }
	}

}
