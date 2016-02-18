package IPV6;

import java.io.FileWriter;
import java.io.IOException;

public class generate_test_ip 
{

	public generate_test_ip() 
	{
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws IOException
	{
		// TODO Auto-generated method stub
	    String filename= "/Users/US-JZhai/Desktop/selftest/random_ip.csv";
	    FileWriter fw = null;
		try {
			fw = new FileWriter(filename,true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //the true will append the new data
		
		for (int i = 0; i<1000000; i++)
		{		 
			String ip1 = Integer.toHexString((int)(Math.random()*65535));
			String ip2 = Integer.toHexString((int)(Math.random()*65535));
			String ip3 = Integer.toHexString((int)(Math.random()*65535));
			String ip4 = Integer.toHexString((int)(Math.random()*65535));
			String ip5 = Integer.toHexString((int)(Math.random()*65535));
			String ip6 = Integer.toHexString((int)(Math.random()*65535));
			String ip7 = Integer.toHexString((int)(Math.random()*65535));
			String ip8 = Integer.toHexString((int)(Math.random()*65535));	
			double rand=Math.random()*100;
			String ip;
			if(rand<10)
			{
				ip="::"+ip2+":"+ip3;
			}
			else if(rand<20)
			{
				ip="::"+ip2+":"+ip3+":"+ip4+":"+ip5;
			}
			else if(rand<30)
			{
				ip=ip1+"::"+ip2+":"+ip3+":"+ip4+":"+ip5;
			}
			else if(rand<40)
			{
				ip=ip1+":"+ip2+":"+ip3+":"+ip4+"::"+ip5+":"+ip6;
			}
			else if(rand<50)
			{
				ip="2001"+":"+ip2+":"+ip3+":"+ip5+"::";
			}
			else
			{
				ip=ip1+":"+ip2+":"+ip3+":"+ip4+":"+ip5+":"+ip6+":"+ip7+":"+ip8;
			}
			fw.write(ip +"\n");//appends the string to the file
		}
		fw.close();
		System.out.println("Files wrote out");


	}

}
