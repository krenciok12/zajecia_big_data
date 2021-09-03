import java.io._


var textFile = sc.textFile("data/g1.txt")
var edges = textFile.flatMap(line => line.split("\n")).map(x => x.split(";")).map {case Array(x,y) => (x,y.split(","))}

var edges2 = edges.flatMap{ case (x,y) => {y.map(z => (x,z))}}
var r_edges = edges2.map(x => (x._2,Array(x._1))).reduceByKey(_ ++ _).collect







val writer = new PrintWriter("wyniki/w31.txt", "UTF-8")
for (el <- r_edges){
  writer.write(el._1 + ":( ")
  for (el2 <- el._2){
    writer.write(el2 + " ")
  }
  writer.write(")\n")
}
writer.close()
