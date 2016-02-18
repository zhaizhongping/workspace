package IPV6;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Search_Binary_IPV6
{

	public static void main(String args[]) throws IOException
	{
			System.out.println("Binary search ...loading data");
			//read in lkup			
			ArrayList<long[]> ip_from=new ArrayList<long[]>();
			ArrayList<long[]> ip_to=new ArrayList<long[]>();
			ArrayList<String> geo_id=new ArrayList<String>();
			//String csvFile = "/Users/US-JZhai/Desktop/selftest/first1m.csv";
			String csvFile = "/Users/US-JZhai/work/IP lookup selftest/GeoIP2-City-Blocks-IPv6_network_range.csv";
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
					String[] iplkup = line.split(cvsSplitBy,3);
					ip_from.add(ipv6_to_long_array(iplkup[0]));
					ip_to.add(ipv6_to_long_array(iplkup[1]));
					geo_id.add(iplkup[2]);
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
			
			System.out.println(ip_from.size()+" rows of lkup data loaded");
			//reading random data
			String csvFile1 = "/Users/US-JZhai/work/IP lookup selftest/eleven_ipv6.txt";
																		
			
			BufferedReader br1 = null;
		 
			try 
			{		 
				br1 = new BufferedReader(new FileReader(csvFile1));
			    String filename= "/Users/US-JZhai/work/IP lookup selftest/matchup_binary.txt";
			    FileWriter fw = new FileWriter(filename,false); //the true will append the new data
			    long startTime = System.currentTimeMillis();
				
				while ((line = br1.readLine()) != null) 
				{		 
					//System.out.println(line+" converted to "+ip2dec(line));
					try
					{
						long[] ip1=ipv6_to_long_array(line);
						String matched=binarySearch(ip_from, ip_to, geo_id, ip1);
						fw.write(line +","+matched+"\n");//appends the string to the file
					}
					catch(Exception e)
					{
							System.out.println("This IP=> "+ line+ " has error while processing: "+e);
					}
				}
				fw.close();
				long estimatedTime = (System.currentTimeMillis() - startTime)/1000;
				System.out.println("Files created in " +estimatedTime + " seconds");
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

	  public static String binarySearch(ArrayList<long[]> a,ArrayList<long[]> b, ArrayList<String> al3,long[] key)
	   {
	       int m;
	       int lb=0;
	       int ub=a.size()-1;
	       String found = "";
	       // To start, find the subscript of the middle m.
	       m = ( lb + ub) / 2;	       
	       while((key[0]<a.get(m)[0]||(key[0]==a.get(m)[0] && key[1]<a.get(m)[1]) || key[0]>b.get(m)[0]||(key[0]==b.get(m)[0] && key[1]>b.get(m)[1])) && lb<=ub )
	       //while(((key<al1.get(m)) || key>al2.get(m)) && (lb <= ub))
	       {
			 //if (key<al1.get(m))             // If the number is > key, ..
	    	 if (key[0]<a.get(m)[0]||(key[0]==a.get(m)[0] && key[1]<a.get(m)[1]))
			 {                                              // decrease m by one. 
			      ub = m - 1;   
			 }                                                             
			 //else if (key<=al2.get(m))  
	    	 else if (key[0]<b.get(m)[0]||(key[0]==b.get(m)[0] && key[1]<=b.get(m)[1]))
			 {
				 break;
			 }
			 else
			 {                                                        
			      lb = m + 1;    // Else, if key is larger than the range, increase m by one. 
			 }
			 m = (lb + ub) / 2;
			       
		  }
		  if (lb <= ub)
		  {
			found=al3.get(m);
		  }
		  return found;
	
	   	}
		public static String ipv6_standardize(String ip_string )
		{
				
			int index=ip_string.indexOf("::");
			if(index<0)
			{
				if(ip_string.indexOf(":")<0)
				{
					return "0:0:0:0:0:0:0:0:0";//in case of incorrect ipv6
				}
				else
				{
					return ip_string;
				}
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
		public static long[] ipv6_to_long_array(String ipv6_compressed)
		{
			String ipv6=ipv6_standardize(ipv6_compressed);		
			String[] ip_array=ipv6.split(":");
			long x=0;
			for (int i=1;i<=4;i++)
			{
				Integer l=Integer.parseInt(ip_array[i-1],16);
				x= x+l*(long)Math.pow(16,(4-i)*4);	
			}
			
			long y=0;
			for (int i=5;i<=8;i++)
			{
				Integer l=Integer.parseInt(ip_array[i-1],16);
				y= y+l*(long)Math.pow(16,(8-i)*4);	
			}
			
			long[] ip2={x,y};
			return ip2;	
		}
}

