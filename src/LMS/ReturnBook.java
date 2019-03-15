package LMS;

import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ReturnBook implements ActionListener{

    JFrame f = new JFrame("Return Book");
    JLabel BookId = new JLabel("Book Id");
    JLabel StdId = new JLabel("Student Id");
    JLabel top = new JLabel("<html><u><i>Return Book</i></u></html>");

    JTextField tf1 = new JTextField();
    JTextField tf2 = new JTextField();
    JButton ret = new JButton("Return");
    JButton back = new JButton("Back");
    private int id;


    public ReturnBook(int id)
    {
        this.id = id;
        top.setBounds(167,7,200,60);
        top.setForeground(Color.BLUE);
        top.setFont(new Font("Arial", Font.ITALIC, 20));


        BookId.setBounds(50,80,100,30);
        tf1.setBounds(220,80,180,30);

        StdId.setBounds(50,150,100,30);
        tf2.setBounds(220,150,180,30);


        back.setBounds(100,300,100,40);
        ret.setBounds(300,300,100,40);

        back.addActionListener(this);
        ret.addActionListener(this);

        f.add(top);  f.add(BookId);  f.add(StdId); f.add(tf1);  f.add(tf2); f.add(back); f.add(ret);
        f.setSize(500,450);
        f.setResizable(false);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }


    public void actionPerformed(ActionEvent e)
    {
      if(e.getSource() == back)
      {
          f.dispose();
          new LibrarianSection(id);
      }

      if(e.getSource() == ret)
      {
          try{

              String url ="jdbc:mysql://localhost:3306/LMS";
              String name = "root";
              String pass = "root";


              Class.forName("com.mysql.jdbc.Driver");

              String qry ="select exists(select * from LMS.IssuedBook where StudentId='"+tf2.getText()+"' and BookId='"+tf1.getText()+"');";
              Connection con = DriverManager.getConnection(url,name,pass);
              Statement st = con.createStatement();

             //if(st.execute(qry))  {JOptionPane.showMessageDialog(f,"Invalid Input"); return;}

             qry = "delete from LMS.IssuedBook where StudentId = '"+tf2.getText()+"';";
             st.executeUpdate(qry);

             qry ="Select * from LMS.Books where BookId='"+ tf1.getText()+"';";
             ResultSet rs = st.executeQuery(qry);

             rs.next();
             int count = Integer.parseInt(rs.getString(5));
             count = count+1;

            qry ="update LMS.Books set QTY ='" + String.valueOf(count)+"' where BookId = '"+tf1.getText()+"';";

            st.executeUpdate(qry);
            JOptionPane.showMessageDialog(f,"Return Successful");

            tf1.setText("");   tf2.setText("");

          }catch (Exception e1){System.out.println(e1);}
      }
    }

    public static void main(String[] args)
    {
        int i=0;
        new ReturnBook(i);
    }
}
