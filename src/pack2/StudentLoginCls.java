package pack2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class StudentLoginCls {

	public static void main(String[] args) {
		new StuLog();
	}

}

class StuLog extends JFrame implements ActionListener{

	JLabel namelabel, passwordlabel,title;
    JTextField nametext,passwordtext;
    JButton button,backButton;
    public StuLog() {
    	title= new JLabel("Student Login Form");
        namelabel= new JLabel("Enter Name: ");
        passwordlabel = new JLabel("Enter password: ");
        nametext = new JTextField(15);
        passwordtext = new JTextField(15);

        button = new JButton("Login");
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
        passwordlabel.setBounds(10,70,100,50);
        add(passwordlabel);
        passwordtext.setBounds(110,85,150,25);
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
                String sql = "SELECT password FROM STUDENT WHERE username = ?";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, username);
                ResultSet resultSet = preparedStatement.executeQuery();

                // Check if the username exists in the database
                if (resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");

                    // Compare the retrieved password with the one entered by the user
                    if (password.equals(retrievedPassword)) {
                        JOptionPane.showMessageDialog(null, "Hi, " + username, "Title", JOptionPane.INFORMATION_MESSAGE);
                        new WhatStudentDo();
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
