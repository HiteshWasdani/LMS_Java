package LMS;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.regex.Pattern;

public class AddBook  implements ActionListener {
    private static JLabel l[] = new JLabel[5];
    private static JTextField tf[] = new JTextField[5];
    private static JFrame f = new JFrame("Add Book");
    private static JButton submit = new JButton("Submit");
    private static JButton back = new JButton("Back");
    private int id;


    public AddBook(int id) {

        this.id = id;

        l[0] = new JLabel("Book Id");
        l[1] = new JLabel("Name");
        l[2] = new JLabel("Author");
        l[3] = new JLabel("Publisher");
        l[4] = new JLabel("QTY");

        JLabel top = new JLabel("<Html><u>Add Book</u></html> ");

        top.setBounds(167, 7, 200, 60);
        top.setForeground(Color.BLUE);
        top.setFont(new Font("Arial", Font.ITALIC, 20));

        int h = 80;

        for (int i = 0; i < 5; i++) {
            l[i].setFont(new Font("Arial", Font.BOLD, 13));
            l[i].setBounds(50, h, 150, 20);
            h = h + 40;
            f.add(l[i]);
        }

        h = 80;

        for (int i = 0; i < 5; i++) {
            tf[i] = new JTextField();
            tf[i].setBounds(160, h, 300, 23);
            h = h + 40;
            f.add(tf[i]);
        }



        back.setBounds(100, 360, 100, 40);
        submit.setBounds(300, 360, 100, 40);

        submit.addActionListener(this);
        back.addActionListener(this);


        f.add(top);
        f.add(back);
        f.add(submit);
        f.setSize(500, 450);
        f.setResizable(false);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == submit)
        {
            if(!(Pattern.matches("\\d+",tf[4].getText())) || Integer.parseInt(tf[4].getText())==0 )
                    {JOptionPane.showMessageDialog(f,"invalid QTY"); return;}

            try{

                String url ="jdbc:mysql://localhost:3306/LMS";
                String name = "root";
                String pass = "root";


                Class.forName("com.mysql.jdbc.Driver");

                Connection con = DriverManager.getConnection(url,name,pass);
                Statement st = con.createStatement();

                String check ="Select exists(select * from LMS.Books where BookId ='"+tf[0].getText()+"');";

                //if(st.execute(check) == true) {JOptionPane.showMessageDialog(f,"BookId already exist"); return;}

                String sql = "INSERT INTO LMS.Books " +
                        "VALUES ("+tf[0].getText()+",'"+ tf[1].getText() +"','" +tf[2].getText() +"','"+ tf[3].getText() +"','"+tf[4].getText() +"')";
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(f, "Book added Successfully ");

                con.close();
                for(int i=0; i<5; i++)  tf[i].setText("");



            }catch (Exception e1)  {System.out.print(e1);}
        }

        if(e.getSource() == back)
        {
            f.dispose();
            new LibrarianSection(id);
        }
    }

    public static void main(String[] args)
    {  int i=0;
        new AddBook(i);
    }
}
