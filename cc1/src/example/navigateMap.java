package example;

import java.util.NavigableMap;
import java.util.TreeMap;

public class navigateMap 
{
    public static void main(String[] args) {
           NavigableMap<Integer, String> nMap = new TreeMap<Integer, String>();
           nMap.put(1, "sunday");
           nMap.put(2, "monday");
           //nMap.put(3, "tuesday");
           //nMap.put(4, "wednesday");
           nMap.put(5, "thursday");
           nMap.put(6, "friday");
           nMap.put(7, "saturday");
           System.out.println("----"+nMap.get(5).toString());

           System.out.println("data in navigable map:" + nMap);
           // Returns the greatest key strictly less than the given key, or null if
           // there is no such key.
           System.out.println("Retrieving the greatest key strictly less than the given key: "
                                      + nMap.lowerEntry(6));

           // Returns the greatest key strictly less than the given key, or null if
           // there is no such key.
           System.out.println(nMap.lowerKey(4));

           // Returns a key-value mapping associated with the greatest key less
           // than or equal to the given key, or null if there is no such key.
           System.out.println("floor entry"+nMap.floorEntry(0));

           // Returns the greatest key less than or equal to the given key, or null
           // if there is no such key.
           System.out.println(nMap.floorKey(3));

           // Returns a key-value mapping associated with the least key greater
           // than or equal to the given key, or null if there is no such key.
           System.out.println(nMap.ceilingEntry(4));

           // Returns the least key greater than or equal to the given key, or null
           // if there is no such key.
           System.out.println(nMap.ceilingKey(8));

           // Returns a key-value mapping associated with the least key strictly
           // greater than the given key, or null if there is no such key.
           System.out.println(nMap.higherEntry(6));

           // Returns the least key strictly greater than the given key, or null if
           // there is no such key.
           System.out.println(nMap.higherKey(7));

           // Returns a key-value mapping associated with the least key in this
           // map, or null if the map is empty.
           System.out.println("First data:" + nMap.firstEntry());

           // Returns a key-value mapping associated with the greatest key in this
           // map, or null if the map is empty.
           System.out.println("last data:" + nMap.lastEntry());

           // Removes and returns a key-value mapping associated with the least key
           // in this map, or null if the map is empty.
           System.out.println("removing first entry:" + nMap.pollFirstEntry());

           // Removes and returns a key-value mapping associated with the greatest
           // key in this map, or null if the map is empty.
           System.out.println("removing last entry:" + nMap.pollLastEntry());
           // Returns a reverse order view of the mappings contained in this map.
           System.out.println("dispalying data:" + nMap.descendingMap());
           // a reverse order navigable set view of the keys in this map
           System.out.println(nMap.descendingKeySet());
           // Returns a NavigableSet view of the keys contained in this map.
           System.out.println(nMap.navigableKeySet());
           
           
           // Returns a view of the portion of this map whose keys range from
           // fromKey to toKey.
          System.out.println("nMap.subMap(2, 5)"+nMap.subMap(2, 5));
          
          
           // Returns a view of the portion of this map whose keys are less than
           // (or equal to, if inclusive is true) toKey.
           System.out.println(nMap.headMap(3, true));
           // Returns a view of the portion of this map whose keys are greater than
           // (or equal to, if inclusive is true) fromKey.
           System.out.println(nMap.tailMap(3, false));
    }
}

