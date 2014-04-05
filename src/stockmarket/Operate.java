package stockmarket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Operate {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/test";
    static final String USER = "root";
    static final String PASS = "Saurabh";
    
    public static void search(String Symbol) throws ClassNotFoundException, SQLException{
    	
    	Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			String q1 = "SELECT name FROM company WHERE symbol='"+Symbol+"'";
			//String q2 = "SELECT name from company WHERE EXISTS (SELECT "+Symbol+" FROM company)";
			stmt = conn.createStatement();
			ResultSet rs= stmt.executeQuery(q1);
            
            if(rs.next())
            {
            	System.out.print("aaa");
            }
            else
            	System.out.println("Stock does not exist.");
		}
		catch (SQLException e ) {
            
        }
        finally {
            if(stmt!=null){
                stmt.close();
            }
        }
    }
}