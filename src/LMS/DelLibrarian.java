package LMS;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class DelLibrarian implements ActionListener{

    String qry = new String();
    Connection con;
    Statement st;
    ResultSet rs;
    JLabel label = new JLabel("<html><u><i>Choose any one to delete Librarian</html></i></u>");
    JRadioButton uid = new JRadioButton("Id");
    JRadioButton uname = new JRadioButton("name");
    JRadioButton ucity = new JRadioButton("city");

    ButtonGroup bg = new ButtonGroup();

    JTable table = new JTable();

    DefaultTableModel model = new DefaultTableModel(0,0)
            {    public boolean isCellEditable(int row, int col){return false;}    };

    JTextField tf = new JTextField();
    JButton sumbit = new JButton("Submit");
    JButton back = new JButton("Back");       // it will redirect to admin section
    JButton pre = new JButton("Back");         //it will redirect to del Lib seciton
    JButton nxt = new JButton("Next");

    JFrame f = new JFrame("Del Librarian");


    public DelLibrarian()
    {

        label.setBounds(40,10,400,40);
        label.setForeground(Color.BLUE);
        label.setFont(new Font("Arial",Font.BOLD,16));
        f.add(label);

        uid.setBounds(55,60,90,30); uid.setSelected(true);
        uname.setBounds(55,95,90,30);
        ucity.setBounds(55,130,90,30);

        bg.add(uid); bg.add(uname); bg.add(ucity);

        f.add(uid); f.add(uname);  f.add(ucity);

        back.setBounds(100,350,80,40);
        pre.setBounds(100,350,80,40);
        nxt.setBounds(300,350,80,40);
        sumbit.setBounds(300,350,100,40);

        pre.setVisible(false);  pre.setEnabled(false);
        sumbit.setVisible(false);  sumbit.setEnabled(false);

        nxt.addActionListener(this);
        pre.addActionListener(this);
        back.addActionListener(this);
        sumbit.addActionListener(this);

        f.add(back); f.add(nxt); f.add(pre); f.add(sumbit);
        f.setSize(500,450);
        f.setResizable(false);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == nxt) {
            int temp = 0;
            String s = new String("");

            if (uid.isSelected()) temp = 1;      //print whole table

            if (uname.isSelected()) {temp =2 ;s = JOptionPane.showInputDialog("Enter name which you want to delete ");}

            if (ucity.isSelected()) {temp =3; s = JOptionPane.showInputDialog("Enter city from you want to delete ");}


            label.setText("<html><u><i>Enter Uid which you want to delete</u></i></html> ");
            back.setVisible(false);
            back.setEnabled(false);

            pre.setEnabled(true);
            pre.setVisible(true);

            uid.setVisible(false);
            uname.setVisible(false);
            ucity.setVisible(false);

            nxt.setVisible(false);
            nxt.setEnabled(false);

            sumbit.setEnabled(true);
            sumbit.setVisible(true);


            String Header[] = new String[]{"Uid", "Uname", "UPassword", "UEmail", "UCity", "Ucontact"};

            model.setColumnIdentifiers(Header);

            table.setModel(model);

            try {

                String url ="jdbc:mysql://localhost:3306/LMS";
                String name = "root";
                String pass = "root";

                String qry1 ="Select * from LMS.Lirarian_data";
                String qry2 ="SELECT * FROM LMS.Lirarian_data where Uname ='" + s +"';";
                String qry3 ="SELECT * FROM LMS.Lirarian_data where Ucity ='" + s +"';";

                Class.forName("com.mysql.jdbc.Driver");


                if(temp ==1) qry = qry1;
                if(temp ==2) qry = qry2;
                if(temp ==3) qry = qry3;

                 con = DriverManager.getConnection(url,name,pass);
                 st = con.createStatement();
                 rs = st.executeQuery(qry);

                while(rs.next())
                    model.addRow(new Object[]{String.valueOf(rs.getInt(1)),rs.getString(2),
                            rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)});



                JScrollPane sp = new JScrollPane(table);
                sp.setBounds(0, 70, 500, 100);

                f.add(sp);

                JLabel tfl = new JLabel("Enter id here");
                tfl.setBounds(40,230,100,20);
                tf.setBounds(40,270,100,20);
                f.add(tf); f.add(tfl);



            }catch(Exception e1) {System.out.println(e1);}
        }

        if(e.getSource() == pre)
        {
            f.dispose();
            new DelLibrarian();
        }

        if(e.getSource() == sumbit)
        {
            if(tf.getText().isEmpty()) {JOptionPane.showMessageDialog(f,"Enter id !"); return;}
            try {

                String qry1 = "Delete From LMS.Lirarian_data where Uid=" + tf.getText() + ";";
                st.executeUpdate(qry1);
                JOptionPane.showMessageDialog(f, "Deleted Successfully");
                 f.dispose();
                 new DelLibrarian();


            }catch (Exception e1) {System.out.print(e1);}
        }

        if(e.getSource() == back)
        {
            f.dispose();
            new AdminSection();
        }
    }

    public static void main(String[] args)
    {
        new DelLibrarian();
    }
}
