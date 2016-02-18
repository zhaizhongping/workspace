//Navigable key is automatically sorted?
package Why_Wrong;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

//import cc1.Upper_ID;

public class Search_NavigatibleMap
{
	
	public static void main(String args[]) throws IOException
	{
			System.out.println("Reading lkup...");
			
			//read in lkup
			//String csvFile = "/Users/US-JZhai/work/ip_range_lkup/first6m.csv";
			String csvFile = "/Users/US-JZhai/work/ip_range_lkup/GeoIP2-City-Blocks-IPv4_converted_string.csv";
			//String csvFile = "/Users/US-JZhai/Desktop/selftest/first1m.csv";
			BufferedReader br = null;
			String line = "";
			String cvsSplitBy = ",";
			NavigableMap<Double,Upper_ID> nmap=new TreeMap<Double,Upper_ID>();
			try 
			{
		 
				br = new BufferedReader(new FileReader(csvFile));
				br.readLine();
				
				
				while ((line = br.readLine()) != null) 
				{		 
				        // use comma as separator
					String[] iplkup = line.split(cvsSplitBy,3);
					nmap.put(ip2dec(iplkup[0]), new Upper_ID(ip2dec(iplkup[1]), iplkup[2]));
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
			
			//System.out.println(geo_id.get(1000));
			System.out.println(nmap.size()+" rows of lkup data loaded");
			//readin random data
			String csvFile1 = "/Users/US-JZhai/work/ip_range_lkup/2ips.txt";
			
			BufferedReader br1 = null;
		 
			try 
			{
		 
				br1 = new BufferedReader(new FileReader(csvFile1));
			    String filename= "/Users/US-JZhai/work/ip_range_lkup/matchup_nmap_why_wrong.txt";
			    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
			    long startTime = System.currentTimeMillis();
				
				while ((line = br1.readLine()) != null) 
				{		 
					//System.out.println(line+" converted to "+ip2dec(line));
					double ip1=ip2dec(line);
					String matched=search_map(nmap, ip1);
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
	private static String search_map(NavigableMap<Double, Upper_ID> m,double key) 
	{
		Map.Entry<Double,Upper_ID> entry = m.floorEntry(key);
		String found="";
		if (entry == null) 
		{
		    // too small
		} 
		else if (key <= entry.getValue().upper) 
		{
		    found=entry.getValue().value;
		} 
		return found;
	}
	public static double ip2dec(String ipstring )
	{
		String[] ipnum = ipstring.split("\\.");
		return (double) (Double.valueOf(ipnum[0])*256*256*256+Double.valueOf(ipnum[1])*256*256+Double.valueOf(ipnum[2])*256+Double.valueOf(ipnum[3]));
	}
	
}
class Upper_ID
{
   public double upper;
   String value;
   public Upper_ID(double a,String b)
   {
	   upper=a;
	   value=b;
   }
}

