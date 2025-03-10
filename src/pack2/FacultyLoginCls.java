package pack2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

public class FacultyLoginCls {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FacultyLoginForm();
	}

}

class FacultyLoginForm extends JFrame implements ActionListener
{
	JLabel namelabel, passwordlabel, title;
	JTextField nametext, passwordtext;
	JButton button,backButton;
	public FacultyLoginForm()
	{
		title =new JLabel("Faculty Login Form");
		namelabel= new JLabel("Enter Username: ");
		passwordlabel=new JLabel("Enter password: ");
		nametext=new JTextField(15);
		passwordtext=new JTextField(15);
		
		button=new JButton("Login");
		button.addActionListener(this);
		backButton= new JButton("← Back");
		backButton.addActionListener(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(350,250);
		setLayout(null);
		
		title.setBounds(80,0,150,20);
		add(title);
		namelabel.setBounds(10,20,100,50);
		add(namelabel);
		nametext.setBounds(130,35,150,25);
		add(nametext);
		passwordlabel.setBounds(10,70,100,50);
		add(passwordlabel);
		passwordtext.setBounds(130,85,150,25);
		add(passwordtext);
		
		button.setBounds(150,155,80,25);
		backButton.setBounds(40,155,80,25);
		
		add(button);
		add(backButton);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
    	if (e.getSource() == button) {
            String username = nametext.getText();
            String password = passwordtext.getText();

            try {
                // Establish database connection
            	Class.forName("oracle.jdbc.OracleDriver");
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "SYSTEM");
                // Execute SQL query to retrieve user credentials based on username
                String sql = "SELECT password FROM FACULTY WHERE username = ?";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, username);
                ResultSet resultSet = preparedStatement.executeQuery();

                // Check if the username exists in the database
                if (resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");

                    // Compare the retrieved password with the one entered by the user
                    if (password.equals(retrievedPassword)) {
                        JOptionPane.showMessageDialog(null, "Hi, " + username, "Title", JOptionPane.INFORMATION_MESSAGE);
                        new WhatLibrarianDo();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid password", "Title", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Username not found", "Title", JOptionPane.ERROR_MESSAGE);
                }

                // Close resources
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error occurred", "Title", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == backButton) {
            new ConstLibrary();
            dispose();
        }
    }

}