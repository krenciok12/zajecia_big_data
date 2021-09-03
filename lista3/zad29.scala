import scala.math.max
import scala.math.min

import java.io._

def func(x1:(Long, Int), x2:(Long, Int)): (Long, Int) = {
  return (x1._1+x2._1, x1._2+x2._2)
}

def func2(x1:Array[String], x2:Array[String]): Array[String] = {
  var arr = x1
  for (el <- x2){
    if (!x1.contains(el)){
      arr = arr ++ Array(el)
    }
  }
  return arr
}

var textFile = sc.textFile("num.txt")
var file = textFile.flatMap(line => line.split("\n"))
var ints = file.map {case x => (x.toInt % 1000, x.toInt)}
var ints2 = file.map {case x => (x.toInt % 1000, (x.toLong,1))}
var ints3 = file.map {case x => (x.toInt % 1000, Array(x))}

var maximums = ints.reduceByKey(_ max _).map(x => x._2)
var maximum = maximums.collect().reduceLeft(_ max _)

var minimums = ints.reduceByKey(_ min _).map(x => x._2)
var minimum = minimums.collect().reduceLeft(_ min _)

var sums = ints2.reduceByKey((x,y) => func(x,y)).map(x => x._2)
var sum = sums.map(x => x._1).collect().reduceLeft(_ + _)
var count = sums.map(x => x._2).collect().reduceLeft(_ + _)
var av = sum / count

var wr = ints3.reduceByKey((x,y) => func2(x,y))
var n_uniq = wr.map{case (x,y) => y.length}.collect.reduceLeft(_ + _)
var uniq = wr.map{case (x,y) => y}.collect.reduceLeft(_ ++ _)
//var wr2 = ints3.reduceByKey(_ ++ _).map{case (x,y) => (x,y.length)}.collect
