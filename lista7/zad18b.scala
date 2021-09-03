import scala.io.Source
import scala.util.Random

def jaccard(f1:String,f2:String,k:Integer): Double = {

  //var stop = Source.fromFile("stopwords_pl.txt", "UTF-8").mkString.split("[\\p{Punct}\\s]+")

  var roz1 = Source.fromFile(f1, "UTF-8").mkString.toLowerCase().split("[\\p{Punct}\\s]+")
  var roz2 = Source.fromFile(f2, "UTF-8").mkString.toLowerCase().split("[\\p{Punct}\\s]+")

  var s1 = roz1.slice(0,k-1)
  var set1 = scala.collection.mutable.Set[String]()

  for (i <- k-1 to roz1.length-1){
    s1 = s1 :+ roz1(i)
    set1+=s1.mkString(" ")
    s1 = s1.tail
  }

  var s2 = roz2.slice(0,k-1)
  var set2 = scala.collection.mutable.Set[String]()

  for (i <- k-1 to roz2.length-1){
    s2 = s2 :+ roz2(i)
    set2+=s2.mkString(" ")
    s2 = s2.tail
  }


  return set1.intersect(set2).size.toDouble/set1.union(set2).size.toDouble//.size

}


def minhash(f1:String,f2:String,k:Integer,h:Integer): Double = {

  //var stop = Source.fromFile("stopwords_pl.txt", "UTF-8").mkString.split("[\\p{Punct}\\s]+")

  var roz1 = Source.fromFile(f1, "UTF-8").mkString.toLowerCase().split("[\\p{Punct}\\s]+")
  var roz2 = Source.fromFile(f2, "UTF-8").mkString.toLowerCase().split("[\\p{Punct}\\s]+")

  var s1 = roz1.slice(0,k-1)
  var set1 = scala.collection.mutable.Set[String]()

  for (i <- k-1 to roz1.length-1){
    s1 = s1 :+ roz1(i)
    set1+=s1.mkString(" ")
    s1 = s1.tail
  }

  var s2 = roz2.slice(0,k-1)
  var set2 = scala.collection.mutable.Set[String]()

  for (i <- k-1 to roz2.length-1){
    s2 = s2 :+ roz2(i)
    set2+=s2.mkString(" ")
    s2 = s2.tail
  }

  var sum = 0
  for (i <- 0 to h-1){
    val r = scala.util.Random
    var n = 104205701
    var a = Random.nextInt(n)
    var b = Random.nextInt(n)
    var min1 = n
    var min2 = n
    for (el <- set1){
      var s = (a*(el.hashCode % n) + b)% n
      if (min1 > s){
        min1=s
      }
    }
    for (el <- set2){
      var s = (a*(el.hashCode % n) + b)% n
      if (min2 > s){
        min2=s
      }
    }

    if (min1==min2){
      sum=sum+1
    }



  }


  return sum.toDouble/h.toDouble

}

var i = 8
var j = 13
var n = 2

println(i,j,minhash("filary_ziemi/r" + i.toString + ".txt", "filary_ziemi/r" + j.toString + ".txt", n,50))
println(i,j,minhash("filary_ziemi/r" + i.toString + ".txt", "filary_ziemi/r" + j.toString + ".txt", n,100))
println(i,j,minhash("filary_ziemi/r" + i.toString + ".txt", "filary_ziemi/r" + j.toString + ".txt", n,250))
println(i,j,jaccard("filary_ziemi/r" + i.toString + ".txt", "filary_ziemi/r" + j.toString + ".txt", n))

var n = jaccard("filary_ziemi/r10.txt", "filary_ziemi/r13.txt", 3)
