import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Root;

import java.io.*;
import java.sql.*;
import java.net.*;

import static java.util.jar.Pack200.Packer.PASS;

public class RealTimeStreaming {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
        static final String DB_URL = "jdbc:mysql://localhost/";
        static final String USER = "root";
        static final String PASS = "Saurabh";

	 public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
	 	Connection conn = null;
   		Statement stmt = null;
   	/*	try{
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
            
        }
    } catch (SQLException e ) {

	}
	finally {
        if (stmt != null) { stmt.close(); }
    }*/
   		RealTimeAPI("aapl");
   		RealTimeStreaming obj = new RealTimeStreaming();
//		obj.run();
}
	public static void RealTimeAPI(String symbol) throws IOException {
		String test = "AAPL";
		//Replace Test with Symbol
		String statement = "http://dev.markitondemand.com/Api/v2/Quote/jsonp?symbol="+test+"&callback=myFunction";
		URL url = new URL(statement);	
		URLConnection urlc = url.openConnection();
		System.out.println("Stream ended");
		urlc.setDoOutput(true);
		System.out.println("AAPL");
		PrintStream ps = new PrintStream(urlc.getOutputStream());
		ps.print(true);
		ps.close();
		System.out.println("Stream ended");
		  BufferedReader br = new BufferedReader(new InputStreamReader(urlc
		            .getInputStream()));
		        String l = null;
		        while ((l=br.readLine())!=null) {
		            System.out.println(l);
		        }
		        br.close();
	}
}
