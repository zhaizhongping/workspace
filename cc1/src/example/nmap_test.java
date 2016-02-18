package example;

import java.util.NavigableMap;
import java.util.TreeMap;

public class nmap_test {

	public nmap_test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub

		NavigableMap<String,Integer> navigableMap=new TreeMap<String, Integer>();
		
		 
		navigableMap.put("008", 500);
		navigableMap.put("009",78);
	//	navigableMap.put("B", 600);
	//	navigableMap.put("A", 700);
	//	navigableMap.put("T", 800);
	//	navigableMap.put("Y", 900);
	//	navigableMap.put("Z", 200);
	 
	
	 
		System.out.printf("Last Key : %s%n",navigableMap.lastKey());
	 
		System.out.printf("First Key : %s%n",navigableMap.firstKey());

	}

}
