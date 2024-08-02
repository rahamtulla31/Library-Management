package pack2;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class LibrarianSection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new WhatLibrarianDo();

	}

}
class WhatLibrarianDo extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JButton AddLibrarian;
    JButton ViewDatabase,Logout;
    JButton DeleteUser,AddStudent,AddFaculty;

    public WhatLibrarianDo()
    {
        JLabel libTitle = new JLabel("Librarian Section");
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
        AddFaculty= new JButton("Add Faculty");
        AddFaculty.setFocusable(false);
        AddFaculty.addActionListener(this);
        
        AddStudent = new JButton("Add Student");
        AddStudent.setFocusable(false);
        AddStudent.addActionListener(this);
        
        Logout= new JButton("Logout");
        Logout.setFocusable(false);
        Logout.addActionListener(this);

        
        setLayout(null);
        libTitle.setBounds(100,10,200,50);
        AddLibrarian.setBounds(100,100,150,50);
        ViewDatabase.setBounds(100,170,150,50);
        AddFaculty.setBounds(100,240,150,50);
        //2nd column
        Logout.setBounds(350,240,150,50);
        AddStudent.setBounds(350,100,150,50);
      //DeleteUser.setBounds(100,280,150,50);
        
        add(libTitle);
        add(AddLibrarian);
        add(ViewDatabase);
        add(AddFaculty);
        add(AddStudent);
        //add(DeleteUser);
        add(Logout);
        setVisible(true);
        setSize(600,500);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    
    public void actionPerformed(ActionEvent e) {
    	
    	if(e.getSource()==AddLibrarian)
    		new addLib(); //Add Librarian to database class
    	if(e.getSource()==ViewDatabase)
    	{
    		dataBaseLibrarian gui = new dataBaseLibrarian();  //View Database class for librarian changes required
            gui.setVisible(true);
    	}
    	if(e.getSource()==AddFaculty)
    		new addFaculty(WhatLibrarianDo.this);
    	
//        if(e.getSource()==AddStudent)
//        new AddStudent();
        
        if(e.getSource()==Logout)
        	new ConstLibrary(); //Library main class
        
        
        dispose();
    }
	
}

