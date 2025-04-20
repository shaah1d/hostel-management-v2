package MetroTicket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conn {

    Connection con;
    Statement stmt;
    Conn(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/metrosystem", "root", "Vaidehi@2005");
            stmt = con.createStatement();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
