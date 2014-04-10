package stockmarket;
import java.io.*;
import java.sql.*;

public class ChartPatterns {
	 static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	 static final String DB_URL = "jdbc:mysql://localhost/test";
	 static final String USER = "root";
     static final String PASS = "password";
     public static Connection conn = null;
 	 public static Statement stmt = null;
	public static void main(String args[])throws IOException, SQLException, ClassNotFoundException {
	
        //everyCompany();
        oneCompany("AAPL");
       
	}
	
	public static void everyCompany() throws ClassNotFoundException, SQLException {
		 try{
	      		Class.forName("com.mysql.jdbc.Driver");
	     		conn = DriverManager.getConnection(DB_URL, USER, PASS);
	     		 String query = "SELECT Symbol FROM test.companylist";
	             stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(query);
	             while (rs.next()) {
	                 String Symbol=rs.getString("Symbol");
	                 CSV_Parser.getClose(Symbol);
	             }
	        } catch (SQLException e ) {
	            
	        }
	        finally {
	            if(stmt!=null){
	                stmt.close();
	            }
	        }
	}
	
	public static void oneCompany(String Symbol) {
		Double []close;
		close = CSV_Parser.getClose(Symbol);
		int i=0;
		/*while(i<close.length)
		{
			System.out.println(close[i]);
			++i;
		}*/
		Algorithm.HeadAndShoulders(close);
		Algorithm.InverseHeadAndShoulders(close);
		Algorithm.DoubleTops(close);
	}
}
