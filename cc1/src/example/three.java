package example;


import java.util.ArrayList;
import java.util.Iterator;

public class three
{
    public static void main(String a[]){
        
        ArrayList<String> myList = new ArrayList<String>();
        myList.add("Java");
        myList.add("Unix");
        myList.add("Oracle");
        myList.add("C++");
        myList.add("Perl");
        Iterator<String> itr = myList.iterator();
        while(itr.hasNext())
        {
        	String x = itr.next();
        	
            System.out.println(x);
            if (x=="Oracle")
            	{
            		break;
            	}     
        }
    }


}
