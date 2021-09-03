object Ob {

  var map = scala.collection.mutable.Map[String, Integer]()
  var l: Integer = 0
  var t: Integer = 0

  def add(x:String):Unit = {
    if (map.contains(x)){
      map(x) = map(x) + 1
      t+=1
    }
    else if (map.size < l-1) {
      map(x) = 1
      t+=1
    }
    else{
      for (k <- map.keys){
        map(k) = map(k) -1
        t+=1
        if (map(k) == 0){
          map.remove(k)
          t+=1
        }
      }

    }
  }

  def get():String = {
    return "str";
  }
  def setL(i:Integer): Unit = {
    l = i
  }
}


def func(list:List[String],k:Integer) : scala.collection.mutable.Map[String, Integer] = {
  Ob.setL(k)
  list.foreach(Ob.add)
  println(Ob.t)
  return Ob.map
}

var l = List[String]("1","4","5","4","4","5","4","4")
/*
for (i <- 1 to 7995){
  l = Random.alphanumeric(0).toString :: l
}
for (i <- 1 to 2005){
  l = "aa" :: l
}
*/
var m = func(l,2)
