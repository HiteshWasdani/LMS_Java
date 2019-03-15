package LMS;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.regex.Pattern;


public class IssueBook  implements ActionListener {
    private static JLabel l[] = new JLabel[4];
    private static JTextField tf[] = new JTextField[4];
    private static JFrame f = new JFrame("Issue Book");
    private static JButton submit = new JButton("Submit");
    private static JButton back = new JButton("Back");
    private static JTextField date = new JTextField();
    private static JLabel datel = new JLabel("Issued Date");
    private int id;

    public IssueBook(int id) {

        this.id = id;

        l[0] = new JLabel("Student Id");
        l[1] = new JLabel("Book Id");
        l[2] = new JLabel("Student Name");
        l[3] = new JLabel("Contact");


        JLabel top = new JLabel("<Html><u>Add Book</u></html> ");

        top.setBounds(167, 7, 200, 60);
        top.setForeground(Color.BLUE);
        top.setFont(new Font("Arial", Font.ITALIC, 20));

        int h = 80;

        for (int i = 0; i < 4; i++) {
            l[i].setFont(new Font("Arial", Font.BOLD, 13));
            l[i].setBounds(50, h, 150, 20);
            h = h + 40;
            f.add(l[i]);
        }

        datel.setBounds(50,h,150,20);

        h = 80;

        for (int i = 0; i < 4; i++) {
            tf[i] = new JTextField();
            tf[i].setBounds(160, h, 300, 23);
            h = h + 40;
            f.add(tf[i]);
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();

        date.setBounds(160,h,150,20);
        date.setText(dtf.format(now));
        date.setEditable(false);

        back.setBounds(100, 360, 100, 40);
        submit.setBounds(300, 360, 100, 40);

        submit.addActionListener(this);
        back.addActionListener(this);


        f.add(top); f.add(date);  f.add(datel);
        f.add(back);
        f.add(submit);
        f.setSize(500, 450);
        f.setResizable(false);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            f.dispose();
            new LibrarianSection(id);
        }

        if (e.getSource() == submit)
        {
            if(!Pattern.matches(".*[a-zA-Z]+.*[a-zA-Z]", tf[2].getText()))
                {  JOptionPane.showMessageDialog(f,"Name contain only alphabets"); return;}

            if(!(Pattern.matches("\\d+",tf[3].getText())) || tf[3].getText().length()!=10)
                {  JOptionPane.showMessageDialog(f,"Contact Number is incorrect"); return;}


            try {
                String url = "jdbc:mysql://localhost:3306/LMS";
                String name = "root";
                String pass = "root";


                String qry = "Select * from LMS.Books";
                String qry1 = "Select * from LMS.IssuedBook";


                Class.forName("com.mysql.jdbc.Driver");

                Connection con = DriverManager.getConnection(url, name, pass);
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(qry);


                int flag = 1, count = 0;
                while (rs.next())
                {
                    if (rs.getString(1).equals(tf[1].getText()))
                    {
                        flag = 0;
                        count = Integer.parseInt(rs.getString(5));
                        break;
                    }
                }

                if (flag == 1)
                {
                    JOptionPane.showMessageDialog(f, "Book not exist");
                    return;
                }


                if (count == 0) {

                    JOptionPane.showMessageDialog(f,"No book availabale");


                }

                if (count >= 1) {

                    String sql = "INSERT INTO LMS.IssuedBook " +
                            "VALUES ('" + tf[0].getText() + "','" + tf[1].getText() + "','" + tf[2].getText()
                            + "','" + tf[3].getText()+"','"+date.getText()+"','"+id+"');";
                    st.executeUpdate(sql);
                    JOptionPane.showMessageDialog(f, "Book added Successfully ");
                    for(int i=0; i<4; i++)  tf[i].setText("");

                    sql = "update LMS.Books set QTY ='" + String.valueOf(count - 1) + "' where BookId='" + tf[1].getText() + "';";
                    st.executeUpdate(sql);
                }

                st.close();
                con.close();




            } catch (Exception e1) {        System.out.println(e1);         }
        }
    }

    public static void main(String[] args)
    {
        int i=0;
        new IssueBook(i);
    }
}