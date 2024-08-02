package pack2;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class Extra {

	public static void main(String[] args) {
		LibraryGUI gui = new LibraryGUI();
        gui.setVisible(true);

	}

}

class LibraryGUI extends JFrame implements ActionListener {


    private JButton showTableButton;
    private JTable table;

    public LibraryGUI() {
        setTitle("Library Table Viewer");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        showTableButton = new JButton("Show Library Table");
        showTableButton.addActionListener(this);
        panel.add(showTableButton);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);

        getContentPane().add(panel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == showTableButton) {
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
