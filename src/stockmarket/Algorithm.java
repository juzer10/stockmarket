package stockmarket;

import java.util.Arrays;

public class Algorithm {
	public Algorithm() {
		
	}
	
	public static String HeadAndShoulders(Double []close) {
		String answer= "false";
		int number = 10; // Number of days
		double [][]low = new double[close.length][3];
		double n[] = new double[close.length]; // neckline
		int ff=0;
		int days[] =new int[close.length];
		
		
		/////////CHECK FOR POTENTIAL NECKLINE///////////////////////////////////////
		for(int i=0; i<close.length-30;i++)
		{
			if(close[i] > close[i+1])
			{	double values[] = new double[10];
				//check if price plunged below neckline
				int no_of_cluster = 10;
				int count = 0;
				double temp = close[i];
				
				double lowest_value = i;
				
				n[i] = close[i];// Current price
				days[i] = i;//Check for zero
				//System.out.println(close[i] + ff++);
				//check formation of head and shoulder pattern
				
				int limit = i+(no_of_cluster);
				if(limit > close.length)
					limit = close.length;
				
				//low[i][0] = value;
				//low[i][1] = value;
				//low[i][2] = value;
				int x=0;
				
				
				//////////////////////////           CLUSTER 1      /////////////////////////////////////////////////////////////////
				for(int j=i+1, k=0; j<limit;j++)
				{	//store lowest value among past 3 clusters
					
					if(close[j] <= temp*1.03 && close[j] >= temp*0.97 )
					{
						//lowest_value = close[j];
						values[k++] = close[j];
						//System.out.println(values[k-1]);
					}
				}
				
				double min = values[0];
				//System.out.println(values[0]);
				while (x < values.length) {
		            if (values[x] <  min && values[x]  != 0.0) {
		              min = values[x];
		             //System.out.println("min = "+min);
		            }
		            x++;
		        }
		    
				Arrays.fill(values, 0.0);
				low[i][count] = min;
				count++;
				
				
				
				//////////////////////////           CLUSTER 2   /////////////////////////////////////////////////////////////////				
				
				for(int j=limit, k=0; j<limit+no_of_cluster;j++)
				{	//store lowest value among past 3 clusters
					
					if(close[j] <= temp*1.03 && close[j] >= temp*0.97 )
					{
						//lowest_value = close[j];
						values[k++] = close[j];
						//System.out.println(values[k-1]);
					}
				}				
				
				min = values[0];
				while (x < values.length) {
		            if (values[x] < min && values[x]  != 0.0) {
		              min = values[x];
		              //System.out.println(min);
		            }
		            x++;
		        }
		    
				Arrays.fill(values, 0.0);
				low[i][count] = min;
				count++;
				
				
				
				//////////////////////////           CLUSTER 3             /////////////////////////////////////////////////////////////////
				
				for(int j=limit+no_of_cluster, k=0; j<limit+2*no_of_cluster;j++)
				{	//store lowest value among past 3 clusters
					
					if(close[j] <= temp*1.03 && close[j] >= temp*0.97 )
					{
						//lowest_value = close[j];
						values[k++] = close[j];
						//System.out.println(values[k-1]);
					}
				}

				min = values[0];
				
				while (x < values.length) {
		            if (values[x] < min && values[x]  != 0.0) {
		              min = values[x];
		             // System.out.println(min);
		            }
		            x++;
		        }
		    
				Arrays.fill(values, 0.0);
				low[i][count] = min;
				
				

			}
			//System.out.println(low[i][0]+"\t"+low[i][1]+"\t"+low[i][2]+"\t"+n[i]);
			//System.out.println(days[i]);
		}
		ff=0;
		//double final_values[][] = new double[close.length][3];
		
		double[] shoulder_price = new double[close.length];
		double[] shoulder_price_2 = new double[close.length];
		double[] head_price = new double[close.length];
		
		
		for(int i=0; i<close.length-30;i++)
		{
			double price = n[i];
			if(days[i] != 0)
			{
				for(int j=i+1, k=0; j<=i+10; j++)
				{
					if(close[j] >= 1.2*price)
						shoulder_price[k++] = close[j];
				}
				//HEAD
				for(int j=i+11, k=0; j<=i+20; j++)
				{
					if(close[j] >= 1.4*price)
						head_price[k++] = close[j];
				}
				
				for(int j=i+21, k=0; j<i+30; j++)
				{
					if(close[j] >= 1.2*price)
						shoulder_price_2[k++] = close[j];
				}
			}
			
			//System.out.println(days[i]+"\t"+n[i]);
			//System.out.println(shoulder_price[i]+"\t"+shoulder_price_2[i]);
		}
		int flag = 0;
		for(int i = 0; i<close.length; i++)
		{
			if(head_price[i] !=0.0 && shoulder_price[i] !=0.0 && shoulder_price_2[i] !=0.0)
			{	answer = "true";
			flag = i;
			System.out.println(flag);
			System.out.println(head_price[i] +"\t" + shoulder_price[i] +"\t"+ shoulder_price_2[i]);}
		}
		
		
		return answer;
	}
	
	
	public static String InverseHeadAndShoulders(Double []close) {
		String answer= "false";
		int number = 10; // Number of days
		double [][]low = new double[close.length][3];
		double n[] = new double[close.length]; // neckline
		int ff=0;
		int days[] =new int[close.length];
		
		
		/////////CHECK FOR POTENTIAL NECKLINE///////////////////////////////////////
		for(int i=0; i<close.length-30;i++)
		{
			if(close[i] > close[i+1])
			{	double values[] = new double[10];
				//check if price plunged below neckline
				int no_of_cluster = 10;
				int count = 0;
				double temp = close[i];
				
				double lowest_value = i;
				
				n[i] = close[i];// Current price
				days[i] = i;//Check for zero
				//System.out.println(close[i] + ff++);
				//check formation of head and shoulder pattern
				
				int limit = i+(no_of_cluster);
				if(limit > close.length)
					limit = close.length;
				
				//low[i][0] = value;
				//low[i][1] = value;
				//low[i][2] = value;
				int x=0;
				
				
				//////////////////////////           CLUSTER 1      /////////////////////////////////////////////////////////////////
				for(int j=i+1, k=0; j<limit;j++)
				{	//store lowest value among past 3 clusters
					
					if(close[j] <= temp*1.03 && close[j] >= temp*0.97 )
					{
						//lowest_value = close[j];
						values[k++] = close[j];
						//System.out.println(values[k-1]);
					}
				}
				
				double min = values[0];
				while (x < values.length) {
		            if (values[x] > min && values[x]  != 0.0) {
		              min = values[x];
		             // System.out.println("min = "+min);
		            }
		            x++;
		        }
		    
				Arrays.fill(values, 0.0);
				low[i][count] = min;
				count++;
				
				
				
				//////////////////////////           CLUSTER 2   /////////////////////////////////////////////////////////////////				
				
				for(int j=limit, k=0; j<limit+no_of_cluster;j++)
				{	//store lowest value among past 3 clusters
					
					if(close[j] <= temp*1.03 && close[j] >= temp*0.97 )
					{
						//lowest_value = close[j];
						values[k++] = close[j];
						//System.out.println(values[k-1]);
					}
				}				
				
				min = values[0];
				while (x < values.length) {
		            if (values[x] > min && values[x]  != 0.0) {
		              min = values[x];
		              //System.out.println(min);
		            }
		            x++;
		        }
		    
				Arrays.fill(values, 0.0);
				low[i][count] = min;
				count++;
				
				
				
				//////////////////////////           CLUSTER 3             /////////////////////////////////////////////////////////////////
				
				for(int j=limit+no_of_cluster, k=0; j<limit+2*no_of_cluster;j++)
				{	//store lowest value among past 3 clusters
					
					if(close[j] <= temp*1.03 && close[j] >= temp*0.97 )
					{
						//lowest_value = close[j];
						values[k++] = close[j];
						//System.out.println(values[k-1]);
					}
				}

				min = values[0];
				
				while (x < values.length) {
		            if (values[x] > min && values[x]  != 0.0) {
		              min = values[x];
		             // System.out.println(min);
		            }
		            x++;
		        }
		    
				Arrays.fill(values, 0.0);
				low[i][count] = min;
				
				

			}
			//System.out.println(low[i][0]+"\t"+low[i][1]+"\t"+low[i][2]+"\t"+n[i]);
			//System.out.println(days[i]);
		}
		ff=0;
		//double final_values[][] = new double[close.length][3];
		
		double[] shoulder_price = new double[close.length];
		double[] shoulder_price_2 = new double[close.length];
		double[] head_price = new double[close.length];
		
		
		for(int i=0; i<close.length-30;i++)
		{
			double price = n[i];
			if(days[i] != 0)
			{
				for(int j=i+1, k=0; j<=i+10; j++)
				{
					if(close[j] <= 0.8*price)
						shoulder_price[k++] = close[j];
				}
				//HEAD
				for(int j=i+11, k=0; j<=i+20; j++)
				{
					if(close[j] <= 0.6*price)
						head_price[k++] = close[j];
				}
				
				for(int j=i+21, k=0; j<i+30; j++)
				{
					if(close[j] <= 0.8*price)
						shoulder_price_2[k++] = close[j];
				}
			}
			
			//System.out.println(days[i]+"\t"+n[i]);
			//System.out.println(shoulder_price[i]+"\t"+shoulder_price_2[i]);
		}
		int flag = 0;
		for(int i = 0; i<close.length; i++)
		{
			if(head_price[i] !=0.0 && shoulder_price[i] !=0.0 && shoulder_price_2[i] !=0.0)
			{	 answer  = "true";
			flag = i;
			System.out.println(head_price[i] +"\t" + shoulder_price[i] +"\t"+ shoulder_price_2[i]);
			}
		}
	//System.out.println("Inverse "+flag);
		
		
		return answer;
	}

	public static String DoubleTops(Double[] close) {
		String answer= "false";
		int number = 22; // Number of days
		double n[] = new double[close.length]; // neckline
		int ff=0;
		int days[] =new int[close.length];
		
		for(int i=0; i<close.length-30;i++)
		{
			if(close[i] > close[i+1])
			{
				double values[] = new double[22];
				//check if price plunged below neckline
				int no_of_cluster = 22;
				int count = 0;
				double temp = close[i];
				double lowest_value = i;

				n[i] = close[i];// Current price
				days[i] = i;//Check for zero
				
				int limit = i+(no_of_cluster);
				if(limit > close.length)
					limit = close.length;
				
				int x=0;
				
				for(int j=i+1, k=0;j<limit;j++)
				{
					if(close[j] <= temp*1.01 && close[j] >= temp*0.99 )
					{
						values[k++]=close[j];
					}
				}
					
					
			}
				
		
		}
		return answer;
	}
}
