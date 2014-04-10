package stockmarket;

public class ProbabilityFactor {
	public static void main(String args[])
	{
		
	}
	public ProbabilityFactor() {
		
	}
	
	public static double calculation(String input) {
		double phv = 0.0;
		
		/*Date,Open,High,Low,Close,Volume
2014-03-21,65.19,65.73,64.01,64.11,445999.0
2014-03-20,63.97,65.6,63.97,64.94,233894.0
2014-03-19,64.02,64.44,63.38,64.03,245488.0
2014-03-18,61.57,64.8,61.29,63.92,381292.0
2014-03-17,60.84,61.51,60.46,61.1,126879.0
2014-03-14,59.29,60.61,59.29,60.42,97184.0
2014-03-13,60.48,60.74,59.08,59.52,143985.0
2014-03-12,59.8,60.31,59.2,60.24,106480.0
2014-03-11,60.94,61.71,59.6,60.26,180713.0
2014-03-10,60.75,61.2,60.43,60.99,171334.0
2014-03-07,61.0,61.26,60.36,60.83,104633.0
2014-03-06,60.44,60.87,60.21,60.71,109860.0
2014-03-05,60.62,60.77,60.14,60.41,121532.0
2014-03-04,58.73,61.12,58.47,60.71,260523.0
2014-03-03,58.57,58.97,57.95,58.06,147616.0
2014-02-28,59.89,60.31,58.96,58.98,245818.0
2014-02-27,59.56,59.93,58.05,59.89,263273.0
2014-02-26,58.27,59.97,58.27,59.93,255819.0
2014-02-25,57.75,58.26,57.4,58.04,122380.0
2014-02-24,57.25,58.0,57.24,57.81,163848.0
2014-02-21,56.45,57.49,55.52,56.97,138908.0
*/
		
	String []temp = input.split("\\r?\\n");	
	
	int n = temp.length;
	double []close = new double[n];
	
	for(int i=1, k=0, j=4; i<n; i++, k++)
	{
		String []values = temp[i].split(",");
		//Storing close values of stock
		System.out.println(values[j]);
		
		close[k] = Double.parseDouble(values[j]);
	}
	
	double final_closing_price = close[n-1];
	
		Double []divided = new Double[n-1];
		
		for(int k=0;k<n-1;k++)
		{
			divided[k] = close[k]/close[k+1];
			divided[k] = (divided[k]-1)*100;
		}
		
		double swap = 0.0;
		for (int c = 0; c < ( n - 1); c++) {
			for (int d = 0; d < n - 2; d++) {
		        if (divided[d] > divided[d+1]) 
		        {
		          swap       = divided[d];
		          divided[d]   = divided[d+1];
		          divided[d+1] = swap;
		        }
	        }
			
	   }
		
		for(int i=0;i<n-1;i++)
		{
			System.out.println(divided[i]);
		}
		
		
		//Commutative Distributive Function
		
		double []cdf = new double[temp.length];
		double no = (double) n;
		for(int i=0;i<n;i++)
		{	
			double m = i+1;
			cdf[i] = m/no;
			System.out.println(cdf[i]);
		}
		int count = 0;
		
		for(int i=0; i<n; i++)
		{
			if(cdf[i] <= 0.1 && cdf[i]>=0.09)	
			{
				count = i;
				break;
			}
		}
		
		phv = divided[count];
		
		double temp_value = final_closing_price*phv;
		
		if(phv > 0)
		{
			
			double predicted_value = final_closing_price + temp_value; 
		}
		else
		{
			double predicted_value = final_closing_price - temp_value; 
		}
		
		return phv;
	}
	
	
	
}
