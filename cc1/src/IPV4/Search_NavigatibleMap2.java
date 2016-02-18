//Navigable key is automatically sorted?
package IPV4;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class Search_NavigatibleMap2
{
	
	public static void main(String args[]) throws IOException
	{
			System.out.println("Reading lkup...");
			
			//read in lkup
			//String csvFile = "/Users/US-JZhai/Desktop/selftest/GeoIP2-City-Blocks-IPv4_converted_string.csv";
			String csvFile = "/Users/US-JZhai/Desktop/selftest/first1m.csv";
			
			BufferedReader br = null;
			String line = "";
			String cvsSplitBy = ",";
			NavigableMap<Double,Upper_ID2> nmap=new TreeMap<Double,Upper_ID2>();
			try 
			{
		 
				br = new BufferedReader(new FileReader(csvFile));
				br.readLine();
				
				int n=1;
				
				while ((line = br.readLine()) != null) 
				{		 
				        // use comma as separator
					n++;
					//System.out.println("this is "+n);
					String[] iplkup = line.split(cvsSplitBy,10);
					nmap.put(ip2dec(iplkup[0]), new Upper_ID2(ip2dec(iplkup[1]),iplkup[2],iplkup[3],
							iplkup[4], iplkup[5],iplkup[6],
							iplkup[7], iplkup[8],iplkup[9]));
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
			String csvFile1 = "/Users/US-JZhai/Desktop/selftest/testip1million.csv";
			
			BufferedReader br1 = null;
		 
			try 
			{
		 
				br1 = new BufferedReader(new FileReader(csvFile1));
			    String filename= "/Users/US-JZhai/Desktop/selftest/matchup_nmap.txt";
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
	private static String search_map(NavigableMap<Double, Upper_ID2> m,double key) 
	{
		Map.Entry<Double,Upper_ID2> entry = m.floorEntry(key);
		String found="";
		if (entry == null) 
		{
		    // too small
		} 
		else if (key <= entry.getValue().upper) 
		{
		    found=entry.getValue().geo_id+","+entry.getValue().post_code+","+entry.getValue().latitude+","+entry.getValue().longitude;
		} 
		return found;
	}
	public static double ip2dec(String ipstring )
	{
		String[] ipnum = ipstring.split("\\.");
		return (double) (Double.valueOf(ipnum[0])*256*256*256+Double.valueOf(ipnum[1])*256*256+Double.valueOf(ipnum[2])*256+Double.valueOf(ipnum[3]));
	}
	
}
class Upper_ID2
{
   public double upper;
   String geo_id;
   String registered_id;
   String represented_id;
   String is_anonymous;
   String is_satellite;  
   String post_code;
   String latitude;
   String longitude;
   
   public Upper_ID2(double a,String b,String c, String d, String e,String f, String g, String h, String i)
   {
	   upper=a;
	   geo_id=b;
	   registered_id=c;
	   represented_id=d;
	   is_anonymous=e;
	   is_satellite=f;  
	   post_code=g;
	   latitude=h;
	   longitude=i;
   }
}

