package IPV4;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Search_Sequential
{
	@SuppressWarnings("unchecked")

	
	public static void main(String args[]) throws IOException
	{
			System.out.println("this is  my test");
			

			//read in lkup
			
			ArrayList<Double> ip_from=new ArrayList<Double>();
			ArrayList<Double> ip_to=new ArrayList<Double>();
			ArrayList<String> geo_id=new ArrayList<String>();
			String csvFile = "/Users/US-JZhai/Desktop/selftest/first1m.csv";
			//String csvFile = "/Users/US-JZhai/Desktop/selftest/GeoIP2-City-Blocks-IPv4_converted_string.csv";
			BufferedReader br = null;
			String line = "";
			String cvsSplitBy = ",";
			

		 
			try 
			{
		 
				br = new BufferedReader(new FileReader(csvFile));
				br.readLine();
				
				while ((line = br.readLine()) != null) 
				{		 
				        // use comma as separator
					String[] iplkup = line.split(cvsSplitBy);
					ip_from.add(ip2dec(iplkup[0]));
					ip_to.add(ip2dec(iplkup[1]));
					geo_id.add(iplkup[3]);

				}
				
		 
			} 
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			} 
			finally
			{
				if (br != null) 
				{
					try 
					{
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}	 
			
			System.out.println(geo_id.get(1000));
			System.out.println(ip_from.size()+" rows of lkup data loaded");
			//readin random data
			String csvFile1 = "/Users/US-JZhai/Desktop/selftest/testip10k.csv";
			
			BufferedReader br1 = null;
		 
			try 
			{
		 
				br1 = new BufferedReader(new FileReader(csvFile1));
			    String filename= "/Users/US-JZhai/Desktop/selftest/matchup_sequential.txt";
			    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
			    long startTime = System.currentTimeMillis();
				
				while ((line = br1.readLine()) != null) 
				{		 
					//System.out.println(line+" converted to "+ip2dec(line));
					double ip1=ip2dec(line);
					String matched=found1(ip_from, ip_to, geo_id, ip1);
					fw.write(line +","+matched+"\n");//appends the string to the file
				}
				fw.close();
				System.out.println("Files wrote out");
				long estimatedTime = (System.currentTimeMillis() - startTime)/1000;
				System.out.println("Done in " +estimatedTime + " seconds");
				
		 
			} 
			catch(IOException ioe)
			{
			    System.err.println("IOException: " + ioe.getMessage());
			}
			finally
			{
				if (br1 != null) 
				{
					try 
					{
						br1.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}	 


			
			
			
			
	}
	public static double ip2dec(String ipstring)
	{
		String[] ipnum = ipstring.split("\\.");
		return (double) (Double.valueOf(ipnum[0])*256*256*256+Double.valueOf(ipnum[1])*256*256+Double.valueOf(ipnum[2])*256+Double.valueOf(ipnum[3]));
	}
	
	public static String found1(ArrayList al1,ArrayList al2, ArrayList al3,Double inputIP)
	{
		Iterator itr1=al1.iterator();
		Iterator itr2=al2.iterator();
		Iterator itr3=al3.iterator();
		String found = "";
		while(itr1.hasNext())		
		{
			Double ip1=(Double) itr1.next();
			Double ip2=(Double) itr2.next();
			String ip3= (String) itr3.next();

			
			if (inputIP<=ip2 && inputIP>=ip1)
			{
				found=ip3;

				break;
			}
		}
		return found;

	}
}

