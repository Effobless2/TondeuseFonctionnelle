import fileManager.FileManager;

package projetal2020 {
  object Main extends App {
    println("Ici le programme principal")
    // Le code suivant ne compilera pas.
    // var tmp = null;
    // var tmp2 = if (tmp == 1) "yes" else 1

    // println(s"tmp: $tmp, tmp2: $tmp2")
    val x = System.getProperty("user.dir");
    println(x);

    val myTerrasse = fileManager.FileManager.getFromFile(x.concat("\\tondeuses\\1.txt"))
    println(myTerrasse);
    println(myTerrasse.width)
    println(myTerrasse.height)

    myTerrasse.tondeuses.map(x => {
      println(x.getPos())
      println(x.getOrientation())
      println(x.getMoves())
      println(x.getTerrasse())
    })

    myTerrasse.start()

    println("Ended")
    println(myTerrasse.width)
    println(myTerrasse.height)

    myTerrasse.tondeuses.map(x => {
      println(x.getPos())
      println(x.getOrientation())
      println(x.getMoves())
      println(x.getTerrasse())
    })
  }
}

