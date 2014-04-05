package stockmarket;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Root;

import java.io.*;
import java.sql.*;
import java.net.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

//import org.omg.CORBA.portable.ResponseHandler;






import java.text.SimpleDateFormat;

import static java.util.jar.Pack200.Packer.PASS;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

public class PastHistoryData {
    
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/test";
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
                
                int i=0;
                //String query = "SELECT Symbol FROM test.company";
                String query = "SELECT Symbol FROM test.companylist";
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String test=rs.getString("Symbol");
                    System.out.println(test);
                    PastHistoryAPI(test);
                   }
        }
        catch (SQLException e ) {
            
        }
        finally {
            if(stmt!=null){
                stmt.close();
            }
        }
    }
    public static void PastHistoryAPI(String Symbol) throws IOException, SQLException, ClassNotFoundException {
    	
    	
        
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
    	
    	 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
         //get current date time with Date()
         Date date = new Date();
         Calendar cal = Calendar.getInstance();
         cal.setTime(date);
         cal.add(Calendar.DAY_OF_YEAR, -1);
         Date currentDate = cal.getTime();
         //System.out.println(dateFormat.format(date)); don't print it, but save it!
         String endDate = dateFormat.format(currentDate);
         //System.out.println(endDate);
         Calendar cal1 = Calendar.getInstance();
         cal1.setTime(date);
         cal1.add(Calendar.DAY_OF_YEAR, -28);
         Date previousDate = cal1.getTime();
         String startDate= dateFormat.format(previousDate);
         //System.out.println(startDate);
    	
    	 HttpClient httpclient = new DefaultHttpClient();
    	 String output="";
         try {
             HttpGet httpget = new HttpGet("http://www.quandl.com/api/v1/datasets/GOOG/NASDAQ_"+Symbol+".csv?&auth_token=Ts7H6ayVewy4B9sqnbkz&trim_start="+startDate+"&trim_end="+endDate+"&sort_order=desc");

             //System.out.println("Executing Request: " + httpget.getURI());

             ResponseHandler<String> responseHandler = new BasicResponseHandler();

             output = httpclient.execute(httpget, responseHandler);
             
         } catch (IOException e) {
             e.printStackTrace();
             //return e.getMessage();
         } finally {

             httpclient.getConnectionManager().shutdown();
         }
        
        System.out.println(output);
        double phv= calcPHV(output);
        UpdatePHV.update(Symbol,phv); 
   }

    private static double calcPHV(String output) {
        
       DecimalFormat df = new DecimalFormat();
       df.setMaximumFractionDigits(5);
       double ph;
       double count=0;
       
       String []realData = output.split(",");
             
             int i=6;
             int j=9;
             while(i<realData.length && j<realData.length) {
             
                 String o=realData[i];
                 double op=Double.valueOf(o);
                 String c=realData[j];
                 double cp=Double.valueOf(c);
                 
                 if(cp>op) 
                    count=count+1;
                 else if(cp<=op && count==0)
                    count=0;
                 else
                    count=count-1;
                 
                 i=i+5;
                 j=j+5;
             }
             ph=count/28;
             //System.out.println(count);
             System.out.println(ph);
             return(ph);
    }
}
