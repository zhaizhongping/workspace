//Navigable key is automatically sorted?
package IPV6;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class Search_NavigatibleMap_ipv6
{
	
	public static void main(String args[]) throws IOException
	{
			System.out.println("Reading lkup...");			
			//read in lkup
			String csvFile = "/Users/US-JZhai/Desktop/selftest/GeoIP2-City-Blocks-IPv6_network_range.csv";
			//String csvFile = "/Users/US-JZhai/Desktop/selftest/header100";
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
					nmap.put(ipv6_to_dec(iplkup[0]), new Upper_ID(ipv6_to_dec(iplkup[1]), iplkup[2]));
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
			String csvFile1 = "/Users/US-JZhai/Desktop/selftest/random_ip.csv";
			
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
					double ip1=ipv6_to_dec(line);
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
//	public static double ip2dec(String ipstring )
//	{
//		String[] ipnum = ipstring.split("\\.");
//		return (double) (Double.valueOf(ipnum[0])*256*256*256+Double.valueOf(ipnum[1])*256*256+Double.valueOf(ipnum[2])*256+Double.valueOf(ipnum[3]));
//	}

	public static String ipv6_standardize(String ip_string )
	{


		int index=ip_string.indexOf("::");
		if(index<0)
		{
			return ip_string;
		}
		else			
		{
			int num_zero=0;
			int count_colon=ip_string.length()-ip_string.replace(":", "").length();
			if(index>0 && index<ip_string.length()-2)
			{
				num_zero=8-count_colon;
			}
			else
			{
				num_zero=9-count_colon;
			}
			
			String z=zeros(num_zero,index);
			String new_ip=ip_string.replace("::", z);
			return new_ip;
		}
	}
	public static String zeros(int n,int loc)
	{
		String zeros;
		if(loc==0)
		{
			zeros="0:";
		}
		else
		{
			zeros=":0:";
		}
		
		for(int i=2;i<=n;i++)
		{
			zeros=zeros+"0:";
		}				
		return zeros;		
	}
	public static double ipv6_to_dec(String ipv6_compressed)
	{
		String ipv6=ipv6_standardize(ipv6_compressed);		
		String[] ip_array=ipv6.split(":");
		double x=0;
		for (int i=1;i<=8;i++)
		{
			//System.out.println(ip_array[i-1]);
			Integer dec=Integer.parseInt(ip_array[i-1],16);
			//System.out.println(dec);
			x=x+dec*Math.pow(16,(8-i)*4);	
		}
		return x;		
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

