package LMS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// for Admin password is hitesh and username is hitesh;

public class AdminLogin implements ActionListener {
    JFrame f = new JFrame("Admin Login");

    JLabel top = new JLabel();
    JLabel uname = new JLabel("Enter Username ");
    JLabel pass = new JLabel("Enter Password");

    JTextField tf = new JTextField();
    JPasswordField pf = new JPasswordField();

    JButton login = new JButton("Login");
    JButton back = new JButton("back");


    public AdminLogin()
    {

        top.setText("<html><u>Admin Login Form</u></html>");
        top.setFont(new Font("Arial", Font.ITALIC,20));

        top.setBounds(150,10,200,60);
        top.setForeground(Color.BLUE);

        uname.setBounds(30,90,150,30);
        tf.setBounds(210,90,240,30);

        pass.setBounds(30,150,150,30);
        pf.setBounds(210,150,240,30);

        back.setBounds(100,250,80,40);
        login.setBounds(300,250,80,40);

        back.addActionListener(this); login.addActionListener(this);

        f.add(uname); f.add(top); f.add(tf); f.add(pass); f.add(pf); f.add(back); f.add(login);
        f.setSize(500,400);
        f.setResizable(false);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == back )  {f.dispose(); new Start(); }
        if(e.getSource() == login)
        {

           if(tf.getText().equals("hitesh") && String.valueOf(pf.getPassword()).equals("hitesh"))
                {   JOptionPane.showMessageDialog(f, "Welcocme Admin ");
                    f.dispose();   new AdminSection();}
            else
                JOptionPane.showMessageDialog(f,"Invalid username or password ");
        }
    }

    public static void main(String[] args)
    {
        new AdminLogin();
    }

}
