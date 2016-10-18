import java.io.{File, PrintWriter}

import scala.collection.mutable
import scala.io.Source
/**
  * Furniture, Alcohol, Toiletries, Shoes, Food, Jewelry
  * customer_id,date,credit_card,cvv,category
  * Created by jakefroeb on 10/18/16.
  */
object FileIO {
  def main(args: Array[String]): Unit = {
    val purchases = mutable.MutableList[(String, String, String, String, String)]()
    Source
      .fromFile("purchases.csv")
      .getLines.drop(1)
      .foreach(line => {
        val Array(customerId, date, creditCard, cvv, category) = line.split(",").map(_.trim)
        purchases.+=((customerId, date, creditCard, cvv, category))
      })

    def prompt(s: String) = {
      println(s);
      io.StdIn.readLine()
    }
    def sort(s: String) = {
      val pw = new PrintWriter(new File(s"filtered${s}purchases.prn") )
      purchases.foreach(x =>
        if ((x._5).equalsIgnoreCase(s)) {
          println(s"Customer:${x._4}, Date:${x._2}")
          pw.write(s"${x._1},${x._2},${x._3},${x._4},${x._5} \n")
        }
      )
      pw.close();
    }

    def menu = {
      val seq = Seq("furniture", "alcohol", "toiletries", "shoes", "food", "jewelry").mkString("\n");
      prompt(s"\nPlease enter a category :\n${seq}\n").toLowerCase()
    }

    var resp = menu match {
      case "furniture" => sort("furniture")
      case "alcohol" => sort("alcohol")
      case "toiletries" => sort("toiletries")
      case "shoes" => sort("shoes")
      case "food" => sort("food")
      case "jewelry" => sort("jewelry")
    }
  }
}
