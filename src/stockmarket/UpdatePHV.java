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
		
		Connection conn = null;
		Statement stmt = null;
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		
		stmt = conn.createStatement();
        String q1="insert into test.phv values (1,2)";
        stmt.executeQuery(q1);
        System.out.print("it's working");
	}

}
