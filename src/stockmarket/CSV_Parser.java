package stockmarket;

import java.io.*;

public class CSV_Parser {
	
	public static Double []close;
	public static Double []open;
	public static Double []high;
	public static Double []low;
	public static Double []volume;
	public static String []date;

	public CSV_Parser() {
	}
	
	public static void parseFile(String Symbol) {
		BufferedReader br = null;
		BufferedReader cr = null;
		try {
 
			String line;
			int count = 0;
			br = new BufferedReader(new FileReader("E:\\Users\\Saurabh\\Documents\\GitHub\\stockmarket\\csv files\\"+Symbol+".csv"));
 
			while ((line = br.readLine()) != null) {
			//	System.out.println(line);
				count++;
			}
			count=count-1;
			System.out.println(count);
			close = new Double[count];
			open = new Double[count];
			high = new Double[count];
			low = new Double[count];
			volume = new Double[count];
			date = new String[count];			
			
						
			int i=0;
			int j=0;
			String readLine;
			cr = new BufferedReader(new FileReader("E:\\Users\\Saurabh\\Documents\\GitHub\\stockmarket\\csv files\\"+Symbol+".csv"));
			while (j<count) {
				readLine = cr.readLine();
				//(readLine = cr.readLine()) != null
				if(j==0)
				{
				}
				else
				{
					//System.out.println(j);
					String []temp = readLine.split(",");
					close[i] = Double.parseDouble(temp[4]);
					open[i] = Double.parseDouble(temp[1]);
					high[i] = Double.parseDouble(temp[2]);
					low[i] = Double.parseDouble(temp[3]);
					volume[i] = Double.parseDouble(temp[5]);
					date[i] = temp[0];	
					
					//System.out.println(close[i]);
					i++;
					
				}
				j++;
				
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
				cr.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	
	public static Double[] getClose(String Symbol) {
		parseFile(Symbol);
		return close;
	}
	
	public static Double[] getOpen(String Symbol) {
		parseFile(Symbol);
		return open;
	}
	
	public static Double[] getHigh(String Symbol) {
		parseFile(Symbol);
		return high;
	}
	
	public static Double[] getLow(String Symbol) {
		parseFile(Symbol);
		return low;
	}
	
	public static Double[] getVolume(String Symbol) {
		parseFile(Symbol);
		return volume;
	}
	
	public static String[] getDate(String Symbol) {
		parseFile(Symbol);
		return date;
	}
}
