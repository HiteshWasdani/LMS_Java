package LMS;

import java.util.regex.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;


public class AddLibrarian  implements ActionListener{
    private static JLabel l[] = new JLabel[6];
    private static JTextField tf[] = new JTextField[5];
    private static JPasswordField pf = new JPasswordField();
    private static JFrame  f = new JFrame("Add Librarian");
    private static JButton submit = new JButton("Submit");
    private static JButton back = new JButton("Back");


     public AddLibrarian()
    {
        l[0] = new JLabel("Id");
        l[1] = new JLabel("Name");
        l[2] = new JLabel("Password");
        l[3] = new JLabel("email");
        l[4] = new JLabel("City");
        l[5] = new JLabel("Contact");

        JLabel top = new JLabel("<Html><u>Add Librarian</u></html> ");

        top.setBounds(167,7,200,60);
        top.setForeground(Color.BLUE);
        top.setFont(new Font("Arial", Font.ITALIC, 20));

        int h =80;

        for(int i=0; i<6; i++)
        {
            l[i].setFont(new Font("Arial", Font.BOLD, 14));
            l[i].setBounds(50,h,150,20);
            h = h+40;
            f.add(l[i]);
        }

         h = 80;

        for(int i=0; i<2; i++)
        {
                tf[i] = new JTextField();
                tf[i].setBounds(160,h,300,20);
                h = h + 40;
                f.add(tf[i]);
        }


        pf.setBounds(160,h,300,20);
        f.add(pf);
        h = h + 40;

        for(int i=2; i<5; i++)
        {
            tf[i] = new JTextField();
            tf[i].setBounds(160,h,300,20);
            h = h + 40;
            f.add(tf[i]);
        }

        back.setBounds(100,360,100,40);
        submit.setBounds(300,360,100,40);

        submit.addActionListener(this);
        back.addActionListener(this);


        f.add(top); f.add(back);  f.add(submit);
        f.setSize(500,450);
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

            if(tf[0].getText().equals(""))
                    {  JOptionPane.showMessageDialog(f,"Id can not to be blank"); return;}

            if(!Pattern.matches(".*[a-zA-Z]+.*[a-zA-Z]", tf[1].getText()))
                    {  JOptionPane.showMessageDialog(f,"Name contain only alphabets"); return;}

             if(pf.getPassword().length<6)
                   {  JOptionPane.showMessageDialog(f,"Password must contain atleast 6 digit"); return;}

             if(!(tf[2].getText().contains("@") && tf[2].getText().contains(".")))
                   {  JOptionPane.showMessageDialog(f,"Invalid email address"); return;}

            if(!Pattern.matches(".*[a-zA-Z]+.*[a-zA-Z]", tf[3].getText()))
                   {  JOptionPane.showMessageDialog(f,"City name only contain Alphabets "); return;}

            if(!(Pattern.matches("\\d+",tf[4].getText())) || tf[4].getText().length()!=10)
                   {  JOptionPane.showMessageDialog(f,"Contact Number is incorrect"); return;}


            try {

                String url ="jdbc:mysql://localhost:3306/LMS";
                String name = "root";
                String pass = "root";


                Class.forName("com.mysql.jdbc.Driver");

                Connection con = DriverManager.getConnection(url,name,pass);
                Statement st = con.createStatement();

                //String check ="Select exists(select * from LMS.Lirarian_data where Uid ='"+tf[0].getText() +"');" ;
                //if(st.execute(check) == true)  throw  new ArithmeticException();


                String sql = "INSERT INTO LMS.Lirarian_data " +
                        "VALUES ("+tf[0].getText()+",'"+ tf[1].getText() +"','"+String.valueOf(pf.getPassword())+"','" +tf[2].getText() +"','"+ tf[3].getText() +"','"+tf[4].getText() +"')";
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(f, "Librarian added Successfully ");

                con.close();

                for(int i=0; i<5; i++)    tf[i].setText("");
                pf.setText(null);


            }  catch (Exception ex) {JOptionPane.showMessageDialog(f,"Id already exist");}
        }

        if(e.getSource() == back)
        {
            f.dispose();
            new AdminSection();
        }
    }


    public static void main(String[] args)
    {
        new AddLibrarian();
    }

}
