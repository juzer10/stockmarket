import java.io.*;

public class ReadCVS {
	 public static void main(String[] args) {
		ReadCVS obj = new ReadCVS();
		obj.run();
	}

	public void run() {
		String csvFile = "companylist.csv";
		BufferedReader br = null;
		String line = "";
		String split = ",";

		try {
			br = new BufferedReader(new FileReader(csvFile));
			int i=0;
			while((line = br.readLine()) != null) {
				if(i==20)
					break;
				String[] data = line.split(split);

				System.out.println("Symbol = "+data[0] + ", Name = "+data[1]);
				i++;
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
