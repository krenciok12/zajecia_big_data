


var textFile = sc.textFile("data/xS2.txt",50)
var edges = textFile.flatMap(line => line.split("\n")).map(x => x.split("[\\s]+")).map{case Array(x,y) => (x.toInt,y.toInt)}
var nodes = edges.union(edges.map(x => (x._2,x._1))).map(x => (x._1,Set(x._2))).reduceByKey(_ ++ _)


var e_o = edges.join(nodes).map{ case (x,(y,z)) =>(y,(x,z)) }.join(nodes).map{case (w,((x,y),z)) => (Array(w,x), y.intersect(z).size)}.flatMap{ case (x,y) => {x.map(z => (z,y))}}.reduceByKey(_ + _)
var e_deg = nodes.map{ case (x,y) => (x,y.size)}

var e_cv = e_o.join(e_deg).map{ case (x,(y,z)) => if (z<2) (x,(0.toDouble,z)) else (x,(y.toDouble/(z.toDouble*(z.toDouble-1)),z)) }

var s = e_cv.map(x => x._2._1).sum
var c = e_deg.count
