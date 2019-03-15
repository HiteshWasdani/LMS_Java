package LMS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LibrarianSection  implements ActionListener {
    private JFrame f = new JFrame("Librarian section ");
    private JButton addB = new JButton("Add Book ");
    private JButton ViewB = new JButton("View Book ");
    private JButton IssueB = new JButton("Issue Book");
    private JButton ViewIssueB = new JButton("View Issue Book");
    private JButton ReturnB = new JButton("Return Book");
    private JButton logout = new JButton("Logout ");
    private JLabel top = new JLabel("<html><u>Librarian Section</u></html> ");
    private int id;

    public LibrarianSection(int id)
    {
        this.id = id;

        top.setBounds(167,7,200,60);
        top.setForeground(Color.BLUE);
        top.setFont(new Font("Arial", Font.ITALIC, 20));

        addB.setBounds(170,80,150,40);
        ViewB.setBounds(170,140,150,40);
        IssueB.setBounds(170,200,150,40);
        ViewIssueB.setBounds(170,260,150,40);
        ReturnB.setBounds(170,320,150,40);
        logout.setBounds(170,380,150,40);

        addB.addActionListener(this);   IssueB.addActionListener(this);  ViewIssueB.addActionListener(this);
        ViewB.addActionListener(this);   logout.addActionListener(this); ReturnB.addActionListener(this);

        f.add(top); f.add(addB); f.add(ViewB); f.add(IssueB);  f.add(ViewIssueB); f.add(ReturnB); f.add(logout);

        f.setSize(500,470);
        f.setResizable(false);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == addB)
        {
            f.dispose();
            new AddBook(id);
        }

        if(e.getSource() == ViewB)
        {

            int i=1;
            new ViewBook(1);
        }

        if(e.getSource() == ViewIssueB)
        {
            int i=2;
            new ViewBook(i);
        }

        if(e.getSource() == IssueB)
        {
            f.dispose();
            new IssueBook(id);
        }

        if(e.getSource() == logout)
        {
            f.dispose();
            new LibrarianLogin();
        }

        if(e.getSource()== ReturnB)
        {
            f.dispose();
            new ReturnBook(id);
        }
    }


    public static void main(String[] args)
    {
        int i=0;
        new LibrarianSection(i);
    }
}
