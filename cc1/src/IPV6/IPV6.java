package IPV6;


public class IPV6 {

	public IPV6() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		String ip="::F";
		String standard_ip=ip6_standardize(ip);
		System.out.println(standard_ip);
		double ip6value=ipv6_to_dec(standard_ip);
		System.out.println(ip6value);

	}
	public static String ip6_standardize(String ip_string )
	{

		int num_zero=0;
		int index=ip_string.indexOf("::");
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
		//return (double) (Double.valueOf(ipnum[0])*256*256*256+Double.valueOf(ipnum[1])*256*256+Double.valueOf(ipnum[2])*256+Double.valueOf(ipnum[3]));
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
	public static double ipv6_to_dec(String ipv6)
	{
		
		String[] ip_array=ipv6.split(":");
		double x=0;
		for (int i=1;i<=8;i++)
		{
			//System.out.println(ip_array[i-1]);
			Integer dec=Integer.parseInt(ip_array[i-1],16);
			System.out.println(dec);
			x=x+dec*Math.pow(16,(8-i)*4);	
		}
		return x;
		
	}

}
