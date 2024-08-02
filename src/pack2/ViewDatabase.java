package pack2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ViewDatabase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	        dataBase gui = new dataBase();
	        gui.setVisible(true);

	}

}
class dataBase extends JFrame implements ActionListener
{
	 	private JButton ShowAdmindata,ShowLibrariandata,ShowBooks,backButton;
	    private JTable table;

	    public dataBase() 
	    {
	        setTitle("Library Table Viewer");
	        setSize(600, 400);
	        setDefaultCloseOperation(EXIT_ON_CLOSE);

	        JPanel panel = new JPanel();
	        ShowAdmindata = new JButton("Show Admin Table");
	        ShowAdmindata.addActionListener(this);
	        
	        ShowLibrariandata = new JButton("Show Librarian Table");
	        ShowLibrariandata.addActionListener(this);
	        
	        ShowBooks = new JButton("Show Books");
	        ShowBooks.addActionListener(this);
	        
	        backButton = new JButton("Back");
	        backButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                dispose();
	                new WhatAdminDo();
	            }
	        });
	        panel.add(ShowAdmindata);
	        panel.add(ShowLibrariandata);
	        panel.add(backButton);

	        table = new JTable();
	        JScrollPane scrollPane = new JScrollPane(table);

	        getContentPane().add(panel, BorderLayout.NORTH);
	        getContentPane().add(scrollPane, BorderLayout.CENTER);
	    }

	    public void actionPerformed(ActionEvent e) {
	        if (e.getSource() == ShowAdmindata) {
	            try {
	            	Class.forName("oracle.jdbc.OracleDriver");
	                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "SYSTEM");
	                Statement statement = connection.createStatement();
	                ResultSet resultSet = statement.executeQuery("SELECT * FROM LIBRARYADMIN");

	                // Populate JTable with ResultSet
	                table.setModel(buildTableModel(resultSet));

	                resultSet.close();
	                statement.close();
	                connection.close();
	            } catch (SQLException | ClassNotFoundException ex) {
	                ex.printStackTrace();
	                JOptionPane.showMessageDialog(null, "Error occurred", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	        if (e.getSource() == ShowLibrariandata) {
	            try {
	            	Class.forName("oracle.jdbc.OracleDriver");
	                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "SYSTEM");
	                Statement statement = connection.createStatement();
	                ResultSet resultSet = statement.executeQuery("SELECT * FROM LIBRARIAN");

	                // Populate JTable with ResultSet
	                table.setModel(buildTableModel(resultSet));

	                resultSet.close();
	                statement.close();
	                connection.close();
	            } catch (SQLException | ClassNotFoundException ex) {
	                ex.printStackTrace();
	                JOptionPane.showMessageDialog(null, "Error occurred", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	        if (e.getSource() == ShowBooks) {
	            try {
	            	Class.forName("oracle.jdbc.OracleDriver");
	                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "SYSTEM");
	                Statement statement = connection.createStatement();
	                ResultSet resultSet = statement.executeQuery("SELECT * FROM BOOK");

	                // Populate JTable with ResultSet
	                table.setModel(buildTableModel(resultSet));

	                resultSet.close();
	                statement.close();
	                connection.close();
	            } catch (SQLException | ClassNotFoundException ex) {
	                ex.printStackTrace();
	                JOptionPane.showMessageDialog(null, "Error occurred", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    }

	    public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
	        ResultSetMetaData metaData = rs.getMetaData();

	        // Column names
	        int columnCount = metaData.getColumnCount();
	        String[] columnNames = new String[columnCount];
	        for (int i = 1; i <= columnCount; i++) {
	            columnNames[i - 1] = metaData.getColumnName(i);
	        }

	        // Data of the table
	        DefaultTableModel model = new DefaultTableModel();
	        model.setColumnIdentifiers(columnNames);
	        while (rs.next()) {
	            Object[] rowData = new Object[columnCount];
	            for (int i = 1; i <= columnCount; i++) {
	                rowData[i - 1] = rs.getObject(i);
	            }
	            model.addRow(rowData);
	        }
	        return model;
	    }

}
