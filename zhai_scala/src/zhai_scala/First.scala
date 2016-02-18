package zhai_scala
import scala.io.Source
import java.io._
object First {
   def main(args: Array[String]) 
   {
      val filename="/Users/US-JZhai/89.txt"
      val w = new BufferedWriter(new FileWriter("/Users/US-JZhai/output.txt"))
      for (line <- Source.fromFile(filename).getLines())
      {
        println(line)
        w.write(line+"\n")
      }
      
      //println("Thanks, you just typed: " + line)


      w.close
      
   }

}