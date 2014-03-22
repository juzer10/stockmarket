
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Root;

import java.io.*;
import java.sql.*;
import java.net.*;

//import org.omg.CORBA.portable.ResponseHandler;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import static java.util.jar.Pack200.Packer.PASS;

public class PastHistoryData {
    
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/test";
    static final String USER = "root";
    static final String PASS = "password";

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        
        Connection conn = null;
        Statement stmt = null;
        
        try{
      		Class.forName("com.mysql.jdbc.Driver");

     		System.out.println("Connecting to database...");
     		conn = DriverManager.getConnection(DB_URL, USER, PASS);
                System.out.println("Connected");
                PastHistoryAPI("AAPL");
                
                /**int i=0;
                String query = "SELECT Symbol FROM test.company";
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next() && i<20) {
                    i++;
                    String test=rs.getString("Symbol");
                }**/
        }
        catch (SQLException e ) {
            
        }
        finally {
            if(stmt!=null){
                stmt.close();
            }
        }
    }
    public static void PastHistoryAPI(String Symbol) throws IOException {
        
//        String statement = "http://www.quandl.com/api/v1/datasets/PRAGUESE/PX.json";
//        URL url = new URL(statement);	
//        URLConnection urlc = url.openConnection();
//        System.out.println("Stream ended");
//        urlc.setDoOutput(true);
//        System.out.println("AAPL");
//        PrintStream ps = new PrintStream(urlc.getOutputStream());
//        ps.print(true);
//        ps.close();
//        System.out.println("Stream ended");
//        InputStream x;
//        x = urlc.getInputStream();
//        BufferedReader br = new BufferedReader(new InputStreamReader(x));
//        String l = null;
//        while ((l=br.readLine())!=null) {
//            System.out.println(l);
//        }
//        br.close();
    	
    	
    	 HttpClient httpclient = new DefaultHttpClient();
    	 String output="";
         try {
             HttpGet httpget = new HttpGet("http://www.quandl.com/api/v1/datasets/GOOG/NASDAQ_AAPL.csv?&trim_start=2007-03-11&trim_end=2014-03-21&sort_order=desc");

             System.out.println("Executing Request: " + httpget.getURI());

             ResponseHandler<String> responseHandler = new BasicResponseHandler();

             output = httpclient.execute(httpget, responseHandler);

         } catch (IOException e) {
             e.printStackTrace();
             //return e.getMessage();
         } finally {

             httpclient.getConnectionManager().shutdown();
         }
    	System.out.println(output);
   }
}
