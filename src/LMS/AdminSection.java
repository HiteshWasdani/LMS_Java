package LMS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminSection  implements ActionListener {
    private JFrame f = new JFrame("Admin section ");
    private JButton addL = new JButton("Add Librarian ");
    private JButton ViewLib = new JButton("View Librarian ");
    private JButton DelLib = new JButton("Delete Librarian ");
    private JButton logout = new JButton("Logout ");
    private JLabel top = new JLabel("<html><u>Admin Section</u></html> ");


    public AdminSection()
    {
        top.setBounds(167,7,200,60);
        top.setForeground(Color.BLUE);
        top.setFont(new Font("Arial", Font.ITALIC, 20));

        addL.setBounds(170,80,150,40);
        ViewLib.setBounds(170,140,150,40);
        DelLib.setBounds(170,200,150,40);
        logout.setBounds(170,260,150,40);

        addL.addActionListener(this);   ViewLib.addActionListener(this);
        DelLib.addActionListener(this);   logout.addActionListener(this);

        f.add(top); f.add(addL); f.add(ViewLib); f.add(DelLib);  f.add(logout);

        f.setSize(500,400);
        f.setResizable(false);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == addL) {f.dispose(); new AddLibrarian();}
        if(e.getSource() == ViewLib) { new ViewLibrarian();}
        if(e.getSource() == DelLib) {f.dispose(); new DelLibrarian();}
        if(e.getSource() == logout) {f.dispose(); new Start();}
    }


    public static void main(String[] args)
    {
        new AdminSection();
    }
}



//Why this does not work


  /*      jp.add(addL);        //jp.add(Box.createVerticalStrut(10));
        jp.add(ViewLib);     //jp.add(Box.createVerticalStrut(10));
        jp.add(DelLib);      //jp.add(Box.createVerticalStrut(10));
        jp.add(logout);      //jp.add(Box.createVerticalStrut(10));

        jp.setLayout(new BoxLayout(jp,BoxLayout.Y_AXIS));


        jp.setBackground(Color.GREEN);

        jp.setBounds(100,100,300,200);
   */
