import scala.math.max
import scala.math.min

import java.io._

def func(x:(Double,Int), y:(Double,Int)): (Double,Int) = {
  var a = (x._1*x._2 + y._1*y._2) / (x._2+y._2)
  return (a,x._2+y._2)
}

def func2(x:(Array[Int],Int), y:(Array[Int],Int)): (Array[Int],Int) = {
  var arr = x._1
  var k = x._2
  for (el <- y._1){
    if (!arr.contains(el)){
      k=k+1
      arr = arr ++ Array(el)
    }
  }
  return (arr,k)
}

def func3(x:(Array[Int],Int), y:(Array[Int],Int)): (Array[Int],Int) = {
  return (x._1 ++ y._1, x._2 + y._2)
}

var textFile = sc.textFile("data/num.txt")
var file = textFile.flatMap(line => line.split("\n"))
var ints = file.map {case x => (x.toInt % 10000,x.toInt)}


def param = (y:(Int,Int,(Double,Int),(Array[Int],Int)), x: Int) => (max(x,y._1),min(x,y._2),func(y._3,(x.toDouble,1)), func2(y._4,(Array(x),1)))
def param2 = (x:(Int,Int,(Double,Int),(Array[Int],Int)),y:(Int,Int,(Double,Int),(Array[Int],Int))) => (max(x._1,y._1),min(x._2,y._2),func(y._3,x._3), func2(y._4,x._4))
def param3 = (x:(Int,Int,(Double,Int),(Array[Int],Int)),y:(Int,Int,(Double,Int),(Array[Int],Int))) => (max(x._1,y._1),min(x._2,y._2),func(y._3,x._3), func3(y._4,x._4))
var results1 = ints.aggregateByKey((0, 10000000, (0.toDouble,0),(Array[Int](),0)))(param, param2).map(x => x._2)
var results2 = results1.aggregate((0, 10000000, (0.toDouble,0),(Array[Int](),0)))(param3, param3)









val writer = new PrintWriter("wyniki/w29.txt", "UTF-8")
writer.write("max "+ results2._1 + "\n")
writer.write("max "+ results2._2 + "\n")
writer.write("srednia "+ results2._3._1 + "\n")
writer.write("liczba unikalnych el "+ results2._4._2 + "\n\n")

writer.write("lista unikalnych el: \n")
for (el <- results2._4._1){
  writer.write(el + "\n")
}

writer.close()
