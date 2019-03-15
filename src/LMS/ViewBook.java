package LMS;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ViewBook{

    JFrame f = new JFrame("Librarian Data");
    JTable table = new JTable();

    DefaultTableModel model = new DefaultTableModel(0,0){
        public boolean isCellEditable(int row,int col)  {return  false;}
    };


    public ViewBook(int i)
    {

        if(i==1)
            {
                String Header[] = new String[] {"Book Id","Name","Author","Publisher","QTY"};
                model.setColumnIdentifiers(Header);
            }
        if(i==2)
        {
            String Header[] = new String[] {"Student Id","Book Id","Student Name","Contact","IssueDate"};
            model.setColumnIdentifiers(Header);
        }


        table.setModel(model);

        try {



            String url ="jdbc:mysql://localhost:3306/LMS";
            String name = "root";
            String pass = "root";

            String qry = new String() ;
            if(i==1)      qry ="Select * from LMS.Books";
            else if(i==2) qry ="Select * from LMS.IssuedBook";


            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection(url,name,pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(qry);

            while(rs.next())
                model.addRow(new Object[]{String.valueOf(rs.getString(1)),rs.getString(2),
                        rs.getString(3),rs.getString(4),rs.getString(5)});



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
        int i=0;
        new ViewBook(i);
    }

}