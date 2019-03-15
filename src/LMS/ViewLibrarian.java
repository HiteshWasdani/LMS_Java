package LMS;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ViewLibrarian{

    JFrame f = new JFrame("Librarian Data");
    JTable table = new JTable();

    DefaultTableModel model = new DefaultTableModel(0,0){
        public boolean isCellEditable(int row,int col)  {return  false;}
    };


    public ViewLibrarian()
    {
        String Header[] = new String[] {"Uid","Uname","UPassword","UEmail","UCity","Ucontact"};

        model.setColumnIdentifiers(Header);

        table.setModel(model);

        try {

            String url ="jdbc:mysql://localhost:3306/LMS";
            String name = "root";
            String pass = "root";

            String qry ="Select * from LMS.Lirarian_data";

            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection(url,name,pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(qry);

            while(rs.next())
                model.addRow(new Object[]{String.valueOf(rs.getInt(1)),rs.getString(2),
                        rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)});



            JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0, 0, 700, 300);

        f.add(sp);
        f.setSize(700,300);
        f.setResizable(false);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setVisible(true);

        }catch (Exception e)  {System.out.print(e);}

    }

    public static void main(String[] args){
        new ViewLibrarian();
    }

}