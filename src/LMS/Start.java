package LMS;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Start implements ActionListener{
    private JFrame f = new JFrame("LMS");
    private JButton b1 = new JButton("Admin Login");
    private JButton b2 = new JButton("Librarian Login");
    private JLabel  l = new JLabel("<html><u>Welcome to Library Mangement System</u></html>");

    public Start()
    {
        l.setBounds(40,10,380,40);
        l.setForeground(Color.BLUE);
        l.setBackground(Color.YELLOW);
        l.setFont(new Font("Arial",Font.ITALIC,18));
        b1.setBounds(160,90,150,60);
        b2.setBounds(160,190,150,60);

        b1.addActionListener(this);  b2.addActionListener(this);

        f.add(b1);  f.add(b2); f.add(l);
        f.setSize(500,400);
        f.setResizable(false);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == b1 )  { f.dispose(); new AdminLogin(); }
        if(e.getSource() == b2 )  { f.dispose(); new LibrarianLogin();}
    }

    public static void main(String[] args)
    {
        new Start();
    }
}
