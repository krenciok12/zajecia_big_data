import java.io._

var textFile = sc.textFile("wyniki/o37.txt",50)
var e_o = textFile.flatMap(line => line.split("\n")).map(x => x.split("[\\s]+")).map{case Array(x,y) => (x.toInt,y.toInt)}

var textFile2 = sc.textFile("wyniki/deg37.txt",50)
var e_deg = textFile2.flatMap(line => line.split("\n")).map(x => x.split("[\\s]+")).map{case Array(x,y) => (x.toInt,y.toInt)}

var e_cv = e_o.join(e_deg).map{ case (x,(y,z)) => if (z<2) (x,(0.toDouble,z)) else (x,(y.toDouble/(z.toDouble*(z.toDouble-1)),z)) }

var s = e_cv.map(x => x._2._1).sum
var c = e_deg.count

println(s/c)


val writer = new PrintWriter("wyniki/cv37.txt", "UTF-8")
writer.write(s/c + "\n")

for (el <- e_cv.collect){
  writer.write(el._1 + " " + el._2._1 + " " + el._2._2 + "\n")
}

writer.close()
