package example;

import java.io.Console;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class test
{
     public static void main(String[] args)
    {
    	String x="1.0.0.0,1.0.0.255,2077456,2077456,,0,0,,-27.0000,133.0000";
    	// String x="2.62.4.128,2.62.4.128,,2017370,,1,0,,,";
    	 String[] y=x.split(",",3);
    	 System.out.println(y[2]);
    }
 	
//Binary search method:
//

  //Binary Search Method
  // This method accepts a pre-sorted array, the subscript of the starting element for the search,
  // the subscript of the ending element for the search,
  // and the key number for which we are searching.
   public static void binarySearch(int[ ] array, int lowerbound, int upperbound, int key)
   {
       int position;
       int comparisonCount = 1;    // counting the number of comparisons (optional)

     // To start, find the subscript of the middle position.
     position = ( lowerbound + upperbound) / 2;

     while((array[position] != key) && (lowerbound <= upperbound))
     {
         comparisonCount++;
         if (array[position] > key)             // If the number is > key, ..
         {                                              // decrease position by one. 
              upperbound = position - 1;   
         }                                                             
              else                                                   
        {                                                        
              lowerbound = position + 1;    // Else, increase position by one. 
        }
       position = (lowerbound + upperbound) / 2;
     }
     if (lowerbound <= upperbound)
     {
           System.out.println("The number was found in array subscript " + position);
           System.out.println("The binary search found the number after " + comparisonCount +" comparisons.");
           // printing the number of comparisons is optional
     }
     else
          System.out.println("Sorry, the number is not in this array.  The binary search made "
                 +comparisonCount  + " comparisons.");
  }

}
