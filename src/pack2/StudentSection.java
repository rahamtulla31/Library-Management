package pack2;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class StudentSection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new WhatStudentDo();
	}

}

class WhatStudentDo extends JFrame implements ActionListener{
	
	JButton AddStudent, ViewBooks;
	JButton Logout;
	public WhatStudentDo()
	{
		JLabel libTitle=new JLabel("Student Section");
		libTitle.setFont(new Font("Serif",Font.PLAIN,25));
		
		AddStudent=new JButton("Add Student");
		AddStudent.setFocusable(false);
		AddStudent.addActionListener(this);
		
		ViewBooks = new JButton("View Books");
		ViewBooks.setFocusable(false);
		ViewBooks.addActionListener(this);
		
		Logout = new JButton("Logout");
		Logout.setFocusable(false);
		Logout.addActionListener(this);
		
		setLayout(null);
		libTitle.setBounds(120, 10, 200, 50);
		AddStudent.setBounds(100,100,200,50);
		ViewBooks.setBounds(100,170,200,50);
		Logout.setBounds(100,240,200,50);
		
		add(libTitle);
		add(AddStudent);
		add(ViewBooks);
		add(Logout);
		
		setVisible(true);
		setSize(400,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==AddStudent)
		new addStudent(this);
		//if(e.getSource()==ViewBooks)
		
		if(e.getSource()==Logout)
			new ConstLibrary();
		
		dispose();
	}
}