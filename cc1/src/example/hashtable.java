package example;

import java.util.Hashtable;

public class hashtable {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Hashtable<String, String> ht=new Hashtable<String, String>();
		ht.put("key1", "this is a testing");
		ht.put("key2", "Silidfasdcon");

		ht.put("key5", "Silsficon");
		ht.clear();
		System.out.println(ht.size());
	}

}
