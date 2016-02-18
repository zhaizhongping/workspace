package IPV4;

import java.io.Console;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class binary_search_range_match
{
     public static void main(String[] args)
    {
        int key = 35; 
        int[ ] num1 = new int [7];
        int[ ] num2=new int[7];
        // Fill the array
        num1[0]=1;
        num1[1]=8;
        num1[2]=11;
        num1[3]=15;
        num1[4]=35;
        num1[5]=51;
        num1[6]=90;
        
        num2[0]=5;
        num2[1]=10;
        num2[2]=15;
        num2[3]=30;
        num2[4]=50;
        num2[5]=89;
        num2[6]=100;        
        
       
        //The binary search method   
        binarySearch (num1, num2, 0, 6, key);  
    }
 	
//Binary search method:
//

  //Binary Search Method
  // This method accepts a pre-sorted array, the subscript of the starting element for the search,
  // the subscript of the ending element for the search,
  // and the key number for which we are searching.
   public static void binarySearch(int[ ] a1, int[] a2, int lb, int ub, int key)
   {
       int m;
       int comparisonCount = 1;    // counting the number of comparisons (optional)

     // To start, find the subscript of the middle m.
     m = ( lb + ub) / 2;
     

     while(((key<a1[m]) || key>a2[m]) && (lb <= ub))
     //while((lb < ub))
     {
         comparisonCount++;
         System.out.println("middle="+m);
         if (key<a1[m])             // If the number is > key, ..
         {                                              // decrease m by one. 
              ub = m - 1;   
         }                                                             
         else if (key<=a2[m])   
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
           System.out.println("The number was found in array subscript " + m);
           System.out.println("The binary search found the number after " + comparisonCount +" comparisons.");
           // printing the number of comparisons is optional
     }
     else
          System.out.println("Sorry, the number is not in this array.  The binary search made "
                 +comparisonCount  + " comparisons.");
  }

}
