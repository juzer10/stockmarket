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
    	double c[];
    	String d[];
    	
        String csvFile = "E:\\Users\\Saurabh\\Documents\\GitHub\\stockmarket\\csv files\\"+Symbol+".csv";
        BufferedReader br = null;
        String line = "";
        String split = ",";
        //System.out.println(Symbol);

        try {
            
        	int i;
            int x=0;
        	br = new BufferedReader(new FileReader(csvFile));
            //System.out.println("Connected database successfully...");
            for(i=0;(line = br.readLine())!= null;i++) {
            	x=x+1;
            }
            int count=x-1;
            //System.out.println(count);
            c=new double[x];
            d=new String[x];
            d = CSV_Parser.getDate(Symbol);// Getting all dates from csv file in variable d[]
            /*for(int j=0;j<d.length;j++)
        		System.out.println(d[j]);*/
            br = new BufferedReader(new FileReader(csvFile));
            for(i=0;(line = br.readLine())!= null && i<count;i++) {
                if(i==0)
            		continue;
            	else {
            		String[] data = line.split(split);
            		double Close=Double.parseDouble(data[4]);
            		//System.out.println(Close);
            		c[i-1]=Close;
            		//System.out.println(c[i-1]);
            		//String Volume=data[5];
            	}
            }
            count=i-1;
            System.out.println(count);
            /*for(i=0;i<count;i++)
            	System.out.println(c[i]);*/
            
            //CALCULATING 26 DAY EMA
            int n26=26;
            int count26=count-n26;
            double ema26[]=new double[count26];
            for(i=count26-1;i>=0;i--)
            {
            	if(i==count26-1)
            	{
            		double sum=0;
            		double avg=0;
            		double y=0;
            		for(int j=i;j<c.length;j++)
            			{
            				sum=sum+c[j];
            				y++;
            			}
            		avg=sum/y;
            		ema26[i]=avg;
            	}
            	else
            	{
            		double k=2.0/27.0;
            		ema26[i]=c[i]*k+ema26[i+1]*(1-k);
            	}
            		
            }
            System.out.println("26 DAY EMA");
            for(i=0;i<count26;i++)
            	System.out.println(ema26[i]);
            //CALCULATION OF 26 DAY EMA ENDS HERE
            
            //CALCULATING 12 DAY EMA
            int n12=12;
            int count12=count-n12;
            double ema12[]=new double[count12];
            for(i=count12-1;i>=0;i--)
            {
            	if(i==count12-1)
            	{
            		double sum=0;
            		double avg=0;
            		double y=0;
            		for(int j=i;j<c.length;j++)
            			{
            				sum=sum+c[j];
            				y++;
            			}
            		avg=sum/y;
            		ema12[i]=avg;
            	}
            	else
            	{
            		double k=2.0/13.0;
            		ema12[i]=c[i]*k+ema12[i+1]*(1-k);
            	}
            		
            }
            System.out.println("12 DAY EMA");
            for(i=0;i<count12;i++)
            	System.out.println(ema12[i]);
            //CALCULATION OF 12 DAY EMA ENDS HERE
            
            //CALCULATING DIFFERENCE BETWEEN THE TWO EMAs
            double emadiff[]=new double[count26];
            for(i=0;i<count26;i++)
            	emadiff[i]=ema12[i]-ema26[i];
            /*System.out.println();
            for(i=0;i<count26;i++)
            	System.out.println(emadiff[i]);*/
            
            //CALCULATING 9 DAY EMA OF THE DIFFERENCE
            int n9=9;
            int count9=count26-n9;
            double ema9[]=new double[count9];
            for(i=count9-1;i>=0;i--)
            {
            	if(i==count9-1)
            	{
            		double sum=0;
            		double avg=0;
            		int y=0;
            		for(int j=i;j<emadiff.length;j++)
            			{
            				sum=sum+emadiff[j];
            				y++;
            			}
            		avg=sum/y;
            		ema9[i]=avg;
            	}
            	else
            	{
            		double k=2.0/10.0;
            		ema9[i]=emadiff[i]*k+ema9[i+1]*(1-k);
            	}
            }
            System.out.println("9 DAY EMA");
            for(i=0;i<count9;i++)
            	System.out.println(ema9[i]);
        }
        
        catch (IOException e) {
            //e.printStackTrace();
        }
    }

}
