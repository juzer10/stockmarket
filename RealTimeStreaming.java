import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Root;

import java.io.*;
import java.sql.*;
import java.net.*;

import static java.util.jar.Pack200.Packer.PASS;

public class RealTimeStreaming {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
        static final String DB_URL = "jdbc:mysql://localhost/test";
        static final String USER = "root";
        static final String PASS = "password";

	 public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
	 	Connection conn = null;
   		Statement stmt = null;
   		String symbolName="";
   		
   		try{
      		Class.forName("com.mysql.jdbc.Driver");

     		System.out.println("Connecting to database...");
     		conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Extracting values...");
			String query = "SELECT Symbol FROM Company";
			stmt = conn.createStatement();
            System.out.println("Printing");
            ResultSet rs = stmt.executeQuery(query);
			 while (rs.next()) {
            symbolName = rs.getString("symbol");
            System.out.println(symbolName);
            RealTimeAPI(symbolName);
            try {
            	Thread.sleep(10000);
            } catch(InterruptedException e) {}
            
        }
    } catch (SQLException e ) {
    	System.out.println("Execution failed");
	}
	finally {
        if (stmt != null) { stmt.close(); }
    }
   		
}
	 
	 
	 
	 
	 
	public static void RealTimeAPI(String symbol) throws IOException, SQLException, ClassNotFoundException {
		
		String statement = "http://dev.markitondemand.com/Api/v2/Quote/jsonp?symbol="+symbol+"&callback=myFunction";
		URL url = new URL(statement);	
		URLConnection urlc = url.openConnection();	
		urlc.setDoOutput(true);
		PrintStream ps = new PrintStream(urlc.getOutputStream());
		ps.print(true);
		ps.close();		
		  BufferedReader br = new BufferedReader(new InputStreamReader(urlc
		            .getInputStream()));
		        String l = null;
		        while ((l=br.readLine())!=null) {
		            System.out.println(l);
		            ObjectParser(l);
		        }
		        br.close();
	}
	
	
	
	
	
	
	
	public static void ObjectParser(String response) throws SQLException, ClassNotFoundException {
		Connection conn = null;
		Statement stmt = null;
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		
		
		String []realData = response.split(",");
		/*
		 * Table name - realtime
		 * 1 - Status
		 * 2 - Name
		 * 3 - Symbol
		 * 4 - LastPrice
		 * 5 - ChangeValue
		 * 6 - ChangePercent
		 * 7 - Timestamp - Not Included
		 * 8 - MSDate - Not Included
		 * 9 - MarketCap
		 * 10 - Volume
		 * 11 - ChangeYTD
		 * 12 - ChangePercentYTD
		 * 13 - High
		 * 14 - Low
		 * 15 - Open
		 */
		
		if(realData[0].contains("SUCCESS"))
		{
		String []splitValue = new String[2];
		
		splitValue = realData[2].split(":");
		String Symbol = splitValue[1];
		Symbol = Symbol.replace("\"", "");
		System.out.println(Symbol);
		
		splitValue = realData[3].split(":");
		Double LastPrice = Double.parseDouble(splitValue[1]);
		//System.out.println(LastPrice);
		
		splitValue = realData[4].split(":");
		Double ChangeValue = Double.parseDouble(splitValue[1]);
		
		splitValue = realData[5].split(":");
		Double ChangePercent = Double.parseDouble(splitValue[1]);
		
		splitValue = realData[8].split(":");
		Double MarketCap = Double.parseDouble(splitValue[1]);
		
		splitValue = realData[9].split(":");
		Double Volume = Double.parseDouble(splitValue[1]);
		
		splitValue = realData[10].split(":");
		Double ChangeYTD = Double.parseDouble(splitValue[1]);
		
		splitValue = realData[11].split(":");
		Double ChangePercentYTD = Double.parseDouble(splitValue[1]);
		
		splitValue = realData[12].split(":");
		Double High = Double.parseDouble(splitValue[1]);
		
		splitValue = realData[13].split(":");
		Double Low = Double.parseDouble(splitValue[1]);
		
		splitValue = realData[14].split(":");
		int n = splitValue[1].indexOf("}");
		splitValue[1] = splitValue[1].substring(0, n);
		//System.out.println(splitValue[1]);
		Double Open = Double.parseDouble(splitValue[1]);
		
		
		
		
			
			String query = "UPDATE realtime SET LastPrice = '"+LastPrice+"', ChangeValue = '"+ChangeValue+"', ChangePercent = '"+ChangePercent+"', MarketCap = '"+MarketCap+"', Volume = '"+Volume+"', ChangeYTD = '"+ChangeYTD+"', ChangePercentYTD = '"+ChangePercentYTD+"', High = '"+High+"', Low = '"+Low+"', Open = '"+Open+"' WHERE Symbol = '"+Symbol+"'";
			stmt = conn.createStatement();
            stmt.executeUpdate(query);
		
		}
	}
}
