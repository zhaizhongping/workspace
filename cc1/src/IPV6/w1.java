package IPV6;

public class w1 {

	public w1() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		double y=123456789012345678d;
		double x=123456789012345679d;
//		System.out.printf("dexp: %f\n", dexp);
//		This will print dexp: 12345678.000000. If you don't want the fractional part, use

		System.out.printf("y: %19.0f\n", y);
		System.out.printf("x: %19.0f\n", x);
		double k=x-y;
		if(x==y)
		{
			System.out.println("x=y");
		}
		System.out.println(k);
		//String ip="2602:306:35fa:970:7022:3c15:1d1b:cd35";
		String ip="2001:470:1f08:415::2";
		double x1=ipv6_to_dec(ip);
		System.out.println(x1);
		long a=1234567890123456789L;
		long b=1234567890123456788L;
		long c=a-b;
		System.out.println("c="+c);
		String s="my,god,this,is";
		String w[]=s.split(",",2);
		System.out.println(w[1]);

	}
	
	public static String ipv6_standardize(String ip_string )
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
