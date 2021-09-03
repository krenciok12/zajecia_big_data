import java.io._

def func(x1:(Int, Int), x2:(Int, Int)): (Int, Int) = {
  return (x1._1+x2._1, x1._2+x2._2)
}


var textFile = sc.textFile("data/web-Stanford.txt")
var edges_out = textFile.flatMap(line => line.split("\n")).map(x => x.split("[\\s]+")).map{case Array(x,y) => (x.toInt,y.toInt)}
var edges_in = edges_out.map(x => (x._2,x._1))

var results = edges_out.map( x => (x._1, (1, 0))).union(edges_in.map( x => (x._1, (0, 1)))).reduceByKey((x,y) => func(x,y))//.map(x => (x._1,x._2._1,x._2._2))

def param = (x:(Int,Int,Int), y:(Int,Int)) =>(x._1 + 1, x._2 + y._1, x._3 + y._2)
def param2 = (x:(Int,Int,Int), y:(Int,Int,Int)) =>(x._1 + y._1, x._2 + y._2, x._3 + y._3)
var sums = results.map(x => x._2).aggregate((0, 0, 0))(param, param2)
var s1 = sums._3.toFloat/sums._1.toFloat
var s2 = sums._2.toFloat/sums._1.toFloat


val writer = new PrintWriter("wyniki/w36.txt", "UTF-8")
writer.write(s1 + "\n")
writer.write(s2 + "\n\n")

for (el <- results.collect){
  writer.write(el + "\n")

}
writer.close()
