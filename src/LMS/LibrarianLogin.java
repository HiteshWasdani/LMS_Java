package LMS;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;


public class LibrarianLogin implements ActionListener{
    JFrame f = new JFrame("Librarian Login");

    JLabel top = new JLabel();
    JLabel uname = new JLabel("Enter User ID ");
    JLabel pass = new JLabel("Enter Password");

    JTextField tf = new JTextField();
    JPasswordField pf = new JPasswordField();

    JButton login = new JButton("Login");
    JButton back = new JButton("back");

    public LibrarianLogin()
    {
        top.setText("<html><u>Librarian Login Form</u></html>");
        top.setFont(new Font("Arial", Font.ITALIC,18));

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


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            f.dispose();
            new Start();
        }

        if (e.getSource() == login) {
            try {

                String url = "jdbc:mysql://localhost:3306/LMS";
                String name = "root";
                String pass = "root";

                Class.forName("com.mysql.jdbc.Driver");

                String qry ="SELECT * FROM LMS.Lirarian_data where Uid = "+ Integer.parseInt(tf.getText())+";";

                Connection con = DriverManager.getConnection(url,name,pass);
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(qry);

                rs.next();

                if(rs.getString(3).equals(String.valueOf(pf.getPassword())))
                {
                    JOptionPane.showMessageDialog(f,"welcome " + rs.getString(2));
                    f.dispose();
                    new LibrarianSection(Integer.parseInt(rs.getString(1)));
                }

                else
                {
                    JOptionPane.showMessageDialog(f,"Invalid Username or password");
                }

            } catch (Exception e1) {       JOptionPane.showMessageDialog(f,"Invalid username of password");}
        }
    }


    public static void main(String[] args){
        new LibrarianLogin();
    }
}
