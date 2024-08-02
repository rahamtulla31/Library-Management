package pack2;

import javax.swing.*;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryMain {
    public static void main(String[] args) {
        new ConstLibrary();
    }
}

@SuppressWarnings("serial")
class  ConstLibrary extends JFrame implements ActionListener
{
    JButton AdminLog;
    JButton LibrarianLog;
    JButton StudentLog,FacultyLog;

    public ConstLibrary()
    {
        JLabel libTitle = new JLabel("Library Mangement");
        libTitle.setFont(new Font("Serif",Font.PLAIN,25));

        AdminLog = new JButton("Admin Login");
        AdminLog.setFocusable(false);
        AdminLog.addActionListener(this);

        LibrarianLog = new JButton("Librarian Login");
        LibrarianLog.setFocusable(false);
        LibrarianLog.addActionListener(this);

        StudentLog = new JButton("Student Login");
        StudentLog.setFocusable(false);

        FacultyLog = new JButton("Faculty Login");
        FacultyLog.setFocusable(false);

        
        setLayout(null);
        libTitle.setBounds(90,20,200,50);
        AdminLog.setBounds(100,80,150,50);
        LibrarianLog.setBounds(100,150,150,50);
        FacultyLog.setBounds(100,220,150,50);
        StudentLog.setBounds(100,290,150,50);
        
        add(libTitle);
        add(AdminLog);
        add(LibrarianLog);
        add(FacultyLog);
        add(StudentLog);
        setVisible(true);
        setSize(400,450);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==AdminLog)
        	new AdminLgForm();  //Admin class
        
        if(e.getSource()==LibrarianLog)
            new LibrarianLoginForm();  //Admin class
        
        dispose();
    }
}
