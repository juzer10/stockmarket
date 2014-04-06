package stockmarket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdatePHV {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/test";
    static final String USER = "root";
    static final String PASS = "Saurabh";
	
	public static void update(String Symbol, Double phv) throws ClassNotFoundException, SQLException{
		System.out.println("Reached PHV+"+ phv);
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			//stmt = conn.createStatement();
			//System.out.println("Connected to Database");
			String q1 = "UPDATE phv SET phvvalue = '"+phv+"' WHERE Symbol = '"+Symbol+"'";
			stmt = conn.createStatement();
            stmt.executeUpdate(q1);
			//System.out.print("it's working");
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