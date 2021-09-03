import scala.math.max
import scala.math.min
import java.io._

def func(x1:List[(String, Int)], x2:List[(String, Int)]): List[(String, Int)] = {
  var new1 = x1 ++ x2
  new1.sortWith(_._2 > _._2)
  return new1.take(5)
}

var stop = sc.textFile("stopwords_pl.txt").flatMap(line => line.split("[\\p{Punct}\\s]+")).collect ++ Array("","–")
var textFile = sc.textFile("filary_ziemi.txt")
var file = textFile.flatMap(line => line.split("[\\p{Punct}\\s\\-\\?\\–\\'\\Æ\\æ]+")).map(line => line.toLowerCase()).filter(line => !stop.contains(line))

var idx = file.zipWithIndex
var previous = idx.map(x => (x._2+1, x._1))
var current = idx.map(x => (x._2, x._1))

var val1 = previous.union(current).map{case (x,y) => (x, Array(y))}.reduceByKey(_ ++ _).map{case (x,y) => y}.filter(_.length>1).map { case Array(x,y) => (x,y) }
var val2 = val1.map(x => (x,1)).reduceByKey(_ + _).map{case (x,y) => (x._1, List((x._2,y)))}.reduceByKey( _ ++ _ ).map{case (x,y) => (x,y.sortWith(_._2 > _._2).take(5))}.collectAsMap




val writer = new PrintWriter("fz2.txt", "UTF-8")
val writer1 = new PrintWriter("fz1.txt", "UTF-8")

var slowo = "aliena"
var wzi = Array[String](slowo)
for (i <- 1 to 100){
  writer.write(slowo + " ")
  var li = val2(slowo)
  writer1.write(slowo + " " + li + "\n")
  slowo = li(0)._1
  var k = 1
  while(wzi.contains(slowo) && k<li.length){
    slowo = li(k)._1
    k=k+1
  }
  wzi = wzi ++ Array(slowo)

}
writer.close()
writer1.close()
