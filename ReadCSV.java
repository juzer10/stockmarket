/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Saurabh
 */
	import java.io.*;
	import java.sql.*;
	
	public class ReadCSV {
		static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
            static final String DB_URL = "jdbc:mysql://localhost/TEST";
            static final String USER = "root";
            static final String PASS = "Saurabh";
            static Connection conn = null;
            static Statement stmt = null;
		 public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		 	Class.forName("com.mysql.jdbc.Driver");

     		System.out.println("Connecting to database...");
     		conn = DriverManager.getConnection(DB_URL, USER, PASS);
                System.out.println("Connected database successfully...");
            
			ReadCSV obj = new ReadCSV();
			obj.run();
		}
	
		public void run()throws ClassNotFoundException, SQLException, IOException {
			String csvFile = "companylist-2.csv";
			BufferedReader br = null;
			String line = "";
			String split = ",";
	
			try {
				br = new BufferedReader(new FileReader(csvFile));
				while((line = br.readLine()) != null) {
					
					String[] data = line.split(split);
	
					System.out.println("Symbol = "+data[0] + ", Name = "+data[1]);
					String Symbol=data[0];
                                        String Name=data[1];
                                        String query = "Insert into company (Symbol,Name)" + "values('"+Symbol+"','"+Name+"')";
                                        stmt = conn.createStatement();
                                        stmt.executeUpdate(query);
				
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
	}
