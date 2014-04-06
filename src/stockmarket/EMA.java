package stockmarket;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
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
    String D[];
    String C[];
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, ParseException {
    	   
        Class.forName("com.mysql.jdbc.Driver");
        
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        System.out.println("Connected database successfully...");
        
        int i=0;
        String query = "SELECT Symbol FROM test.companylist";
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next() && i<5) {
            String test=rs.getString("Symbol");
            System.out.println(test);
            i++;
            
            EMA obj = new EMA();
            obj.run(test);
        }
    }
    
    public void run(String Symbol) throws FileNotFoundException, IOException, SQLException, ParseException {
    	
    	DateFormat df = new SimpleDateFormat("yyyy-MMM-dd");
    	Date date;
        String csvFile = "E:\\Users\\Saurabh\\Documents\\GitHub\\stockmarket\\csv files\\"+Symbol+".csv";
        BufferedReader br = null;
        String line = "";
        String split = ",";

        try {
            
        	br = new BufferedReader(new FileReader(csvFile));
            System.out.println("Connected database successfully...");
            for(int i=0;(line = br.readLine()) != null;i++) {
                
                String[] data = line.split(split);
                //date = df.parse(data[0]);
                //String newDateString = df.format(date);
                //System.out.println(newDateString);
                //D[i]=newDateString;
                //System.out.println(D[i]);
                String Open=data[1];
                String High=data[2];
                String Low=data[3];
                String Close=data[4];
                System.out.println(Close);
                C[i]=Close;
                System.out.println(C[i]);
                String Volume=data[5];
            }
            
            
        }
        catch (IOException e) {
            //e.printStackTrace();
        }
    }

}
