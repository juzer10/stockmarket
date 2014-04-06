package stockmarket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DisplayPHV {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/test";
    static final String USER = "root";
    static final String PASS = "password";
	
	public static void display() throws ClassNotFoundException, SQLException{
		
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			//stmt = conn.createStatement();
			System.out.println("Connected to Database");
			String q1 = "SELECT * FROM test.phv";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(q1);
			int i=0;
			
			while (rs.next() && i<20) {
                
	            String symbolName = rs.getString("Symbol");
	            String phv= rs.getString("phvvalue");
	            System.out.println(symbolName+"    "+phv);
	            i++;
			}
			System.out.print("it's working");
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