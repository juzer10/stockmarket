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
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, ParseException {
    	   
        Class.forName("com.mysql.jdbc.Driver");
        
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        System.out.println("Connected database successfully...");
        
        int i=0;
        String query = "SELECT Symbol FROM test.companylist";
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next() && i<1) {
            String test=rs.getString("Symbol");
            System.out.println(test);
            i++;
            
            EMA obj = new EMA();
            obj.run(test);
        }
    }
    
    public void run(String Symbol) throws FileNotFoundException, IOException, SQLException, ParseException {
    	
    	//DateFormat df = new SimpleDateFormat("yyyy-MMM-dd");
    	//Date date;
    	double c[]=new double[50];
        String csvFile = "E:\\Users\\Saurabh\\Documents\\GitHub\\stockmarket\\csv files\\"+Symbol+".csv";
        BufferedReader br = null;
        String line = "";
        String split = ",";

        try {
            
        	br = new BufferedReader(new FileReader(csvFile));
            System.out.println("Connected database successfully...");
            for(int i=0;(line = br.readLine()) != null;i++) {
                
            	if(i==0)
            		continue;
            	else {
            	
                String[] data = line.split(split);
                //date = df.parse(data[0]);
                //String newDateString = df.format(date);
                //System.out.println(newDateString);
                //D[i]=newDateString;
                //System.out.println(D[i]);
                //String Open=data[1];
                //String High=data[2];
                //String Low=data[3];
                double Close=Double.parseDouble(data[4]);
                //System.out.println(Close);
                c[i-1]=Close;
                //System.out.println(c[i-1]);
                //String Volume=data[5];
            	}
            }
            
            int n=26;
            double ema[]=new double[26];
            for(int i=n-1;i>0;i--)
            {
            	if(i==n-1)
            	{
            		double sum=0;
            		double avg=0;
            		int count=0;
            		for(int j=i;j<c.length;j++)
            			{
            				sum=sum+c[j];
            				count++;
            			}
            		avg=sum/count;
            		ema[i]=avg;
            	}
            	else
            	{
            		double k=2.0/27.0;
            		ema[i]=c[i]*k+ema[i+1]*(1-k);
            	}
            		
            }
            for(int i=1;i<n;i++)
            	System.out.println(ema[i]);
            
            int n1=12;
            double ema1[]=new double[12];
            for(int i=n-1;i>0;i--)
            {
            	if(i==n-1)
            	{
            		double sum=0;
            		double avg=0;
            		int count=0;
            		for(int j=i;j<c.length;j++)
            			{
            				sum=sum+c[j];
            				count++;
            			}
            		avg=sum/count;
            		ema1[i]=avg;
            	}
            	else
            	{
            		double k=2.0/13.0;
            		ema1[i]=c[i]*k+ema1[i+1]*(1-k);
            	}
            		
            }
            for(int i=1;i<n1;i++)
            	System.out.println(ema1[i]);
            
            double emad[]=new double[12];
            for(int i=1;i<n1;i++)
            	emad[i]=ema1[i]-ema[i];
            
            int n2=9;
            double ema9[]=new double[12];
            for(int i=n-1;i>0;i--)
            {
            	if(i==n-1)
            	{
            		double sum=0;
            		double avg=0;
            		int count=0;
            		for(int j=i;j<emad.length && count<9;j++)
            			{
            				sum=sum+emad[j];
            				count++;
            			}
            		avg=sum/count;
            		ema9[i]=avg;
            	}
            	else
            	{
            		double k=2.0/10.0;
            		ema9[i]=emad[i]*k+ema9[i+1]*(1-k);
            	}
            		
            }
        }
        
        catch (IOException e) {
            //e.printStackTrace();
        }
    }

}
