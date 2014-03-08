import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Root;
import java.io.*;
import java.sql.*;
import static java.util.jar.Pack200.Packer.PASS;

public class ReadCVS {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
        static final String DB_URL = "jdbc:mysql://localhost/";
        static final String USER = "root";
        static final String PASS = "Saurabh";

	 public static void main(String[] args) throws ClassNotFoundException, SQLException {
	 	Connection conn = null;
   		Statement stmt = null;
   		try{
      		Class.forName("com.mysql.jdbc.Driver");

     		System.out.println("Connecting to database...");
     		conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Extracting values...");



			String query = "SELECT Symbol FROM Company";
			stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
			 while (rs.next()) {
            String symbolName = rs.getString("Symbol");
            System.out.println(symbolName);
            //RealTimeAPI(symbolName);
        }
    } catch (SQLException e ) {

	}
	finally {
        if (stmt != null) { stmt.close(); }
    }
	ReadCVS obj = new ReadCVS();
//		obj.run();
}
	public void RealTimeAPI(String symbol) {
		String statement = "http://dev.markitondemand.com/Api/v2/Quote/jsonp?symbol="+symbol+"&callback=myFunction";

	}
}
