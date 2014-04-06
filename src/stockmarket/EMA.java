package stockmarket;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EMA {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/TEST";
    static final String USER = "root";
    static final String PASS = "Saurabh";
    static Connection conn = null;
    static Statement stmt = null;
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, ParseException {
    	   
        Class.forName("com.mysql.jdbc.Driver");
        
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        System.out.println("Connected database successfully...");
        
        int i=0;
        String query = "SELECT Symbol FROM test.companylist";
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next() && i<2) {
            String test=rs.getString("Symbol");
            System.out.println(test);
            i++;
            
            EMA obj = new EMA();
            obj.run(test);
        }
    }
    
    public void run(String Symbol) throws FileNotFoundException, IOException, SQLException, ParseException {
    	
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MMM-dd");
        String csvFile = "E:\\Users\\Saurabh\\Documents\\GitHub\\stockmarket\\csv files\\"+Symbol+".csv";
        BufferedReader br = null;
        String line = "";
        String split = ",";
        
        String query="Drop table "+Symbol; 
        stmt = conn.createStatement();
        stmt.executeUpdate(query);
        
        String query1="CREATE table "+Symbol+" (Date DATE,Open DOUBLE,High DOUBLE,Low DOUBLE,Close DOUBLE,Volume DOUBLE)"; 
        stmt = conn.createStatement();
        stmt.executeUpdate(query1);
        
        try {
            
            br = new BufferedReader(new FileReader(csvFile));
            System.out.println("Connected database successfully...");
            while((line = br.readLine()) != null) {
                
                String[] data = line.split(split);
                Date date = formatter.parse(data[0]);
                String Open=data[1];
                String High=data[2];
                String Low=data[3];
                String Close=data[4];
                String Volume=data[5];
                
                String query2 = "Insert into "+Symbol+" values('"+date+"','"+Open+"','"+High+"','"+Low+"','"+Close+"','"+Volume+"',PRIMARY KEY("+date+")";
                stmt = conn.createStatement();
                stmt.executeUpdate(query2);
            }
            
            
        }
        catch (IOException e) {
            //e.printStackTrace();
        }
    }

}
