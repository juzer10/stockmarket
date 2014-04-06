package stockmarket;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.sql.*;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;


public class CSV_database_test {

			static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	            static final String DB_URL = "jdbc:mysql://localhost/TEST";
	            static final String USER = "root";
	            static final String PASS = "password";
	            static Connection conn = null;
	            static Statement stmt = null;
			 public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, InterruptedException {
			 	Class.forName("com.mysql.jdbc.Driver");

	     		System.out.println("Connecting to database...");
	     		conn = DriverManager.getConnection(DB_URL, USER, PASS);
	                System.out.println("Connected database successfully...");
	            
	                CSV_database_test obj = new CSV_database_test();
				obj.run();
			}
		
			public void run()throws ClassNotFoundException, SQLException, IOException, InterruptedException {
				String csvFile = "\\Users\\Juzer\\Documents\\GitHub\\stockmarket\\src\\stockmarket\\new_company_list1.csv";
				BufferedReader br = null;
				String line = "";
				String split = ",";
		
				try {
					br = new BufferedReader(new FileReader(csvFile));
					while((line = br.readLine()) != null) {
						System.out.print("HERE");
						//String[] data = line.split(split);
						Thread.sleep(5000);
						System.out.println("Symbol = "+line);
						//RealTimeAPI(line);
						PastHistory(line);
						String Symbol=line;
	                                        //String Name=data[1];
	                                        //String query = "Insert into newtable (Symbol)" + "values('"+Symbol+"')";
	                                        //stmt = conn.createStatement();
	                                       // stmt.executeUpdate(query);
					
					}
				}   catch (IOException e) {
						//e.printStackTrace();
					}   finally {
						if(br != null) {
							try {
								br.close();
							} catch(IOException e) {
							e.printStackTrace();
							}
						}
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
				        }
				        br.close();
			}
			
			public static void PastHistory(String symbol)
			{
			 HttpClient httpclient = new DefaultHttpClient();
	    	 String output="";
	         try {
	             HttpGet httpget = new HttpGet("http://www.quandl.com/api/v1/datasets/GOOG/NASDAQ_"+symbol+".csv?&auth_token=Ts7H6ayVewy4B9sqnbkz&trim_start=2014-02-21&trim_end=2014-03-21&sort_order=desc");

	             //System.out.println("Executing Request: " + httpget.getURI());

	             ResponseHandler<String> responseHandler = new BasicResponseHandler();

	             output = httpclient.execute(httpget, responseHandler);
	             
	         } catch (IOException e) {
	             e.printStackTrace();
	             //return e.getMessage();
	         } 
	        // catch(org.apache.http.client.HttpResponseException es) {
	        	// es.printStackTrace();
	        // }
	         finally {

	             httpclient.getConnectionManager().shutdown();
	         }
	        
	        System.out.println(output);
		
			}
}
