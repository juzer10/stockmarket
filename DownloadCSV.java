/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Root;
import java.io.*;
import java.sql.*;
import java.net.*;
import static java.util.jar.Pack200.Packer.PASS;
/**
 *
 * @author Saurabh
 */
public class DownloadCSV {
    
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/";
    static final String USER = "root";
    static final String PASS = "Saurabh";

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        
        Connection conn = null;
        Statement stmt = null;
        
        try{
      		Class.forName("com.mysql.jdbc.Driver");

     		System.out.println("Connecting to database...");
     		conn = DriverManager.getConnection(DB_URL, USER, PASS);
                System.out.println("Connected");
                RealTimeAPI("AAPL");
                
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
    public static void RealTimeAPI(String Symbol) throws IOException {
        
        String statement = "http://www.quandl.com/api/v1/datasets/GOOG/NASDAQ_GOOG.csv?&trim_start=2004-08-19&trim_end=2014-03-14&sort_order=desc";
        URL url = new URL(statement);	
        URLConnection urlc = url.openConnection();
        System.out.println("Stream ended");
        urlc.setDoOutput(true);
        System.out.println("AAPL");
        PrintStream ps = new PrintStream(urlc.getOutputStream());
        ps.print(true);
        ps.close();
        System.out.println("Stream ended");
        InputStream x;
        x = urlc.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(x));
        String l = null;
        while ((l=br.readLine())!=null) {
            System.out.println(l);
        }
        br.close();
   }
}
