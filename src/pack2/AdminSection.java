package pack2;

import java.awt.Dimension;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AdminSection {

	public static void main(String[] args) {
		new WhatAdminDo();
	}

}

class WhatAdminDo extends JFrame implements ActionListener
{
	JButton AddLibrarian;
    JButton ViewDatabase,Logout;
    JButton DeleteUser,AddAdmin;

    public WhatAdminDo()
    {
        JLabel libTitle = new JLabel("Admin Section");
        libTitle.setFont(new Font("Serif",Font.PLAIN,25));

        AddLibrarian = new JButton("Add Librarian");
        AddLibrarian.setFocusable(false);
        AddLibrarian.addActionListener(this);

        ViewDatabase = new JButton("View Database");
        ViewDatabase.setFocusable(false);
        ViewDatabase.addActionListener(this);

		/*
		 * DeleteUser = new JButton("Delete a User"); DeleteUser.setFocusable(false);
		 * DeleteUser.addActionListener(this);
		 */
        AddAdmin = new JButton("Add Admin");
        AddAdmin.setFocusable(false);
        AddAdmin.addActionListener(this);
        
        Logout= new JButton("Logout");
        Logout.setFocusable(false);
        Logout.addActionListener(this);

        
        setLayout(null);
        libTitle.setBounds(90,10,200,50);
        AddLibrarian.setBounds(90,70,150,50);
        ViewDatabase.setBounds(90,140,150,50);
        AddAdmin.setBounds(90,210,150,50);
        //DeleteUser.setBounds(90,280,150,50);
        Logout.setBounds(90,280,150,50);
        
        add(libTitle);
        add(AddLibrarian);
        add(ViewDatabase);
        add(AddAdmin);
        //add(DeleteUser);
        add(Logout);
        setVisible(true);
        setSize(350,400);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    
    public void actionPerformed(ActionEvent e) {
    	
    	if(e.getSource()==AddLibrarian)
    		new addLib(); // Add Librarian to database class
    	if(e.getSource()==ViewDatabase)
    	{
    		dataBase gui = new dataBase();  //View database class
            gui.setVisible(true);
    	}
        if(e.getSource()==AddAdmin)
        new addAdmin();  //Add admin to database class
        
        if(e.getSource()==Logout)
        	new ConstLibrary(); //Librarian class
        
        
        dispose();
    }
	
}
