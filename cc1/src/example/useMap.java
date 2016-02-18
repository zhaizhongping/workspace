package example;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class useMap 
{

	public static void main(String[] args) 
	{
		NavigableMap<Integer, Range> map = new TreeMap<Integer, Range>();
		map.put(0, new Range(3, 0));       // 0..3     => 0
		map.put(5, new Range(10, 1));      // 5..10    => 1
		map.put(100, new Range(200, 2));   // 100..200 => 2
		
		// To do a lookup for some value in 'key'
		int key=7;
		Map.Entry<Integer,Range> entry = map.floorEntry(key);
		System.out.println("thisiii  " + entry.getValue().value);
		if (entry == null) 
		{
		    // too small
		} 
		else if (key <= entry.getValue().upper) 
		{
		    System.out.println("found "+ entry.getValue().value);
		} else {
		    // too large or in a hole
		}
	}


}
class Range
{
   public int upper, value;
   public Range(int a,int b)
   {
	   upper=a;
	   value=b;
   }
   

}
