import fileManager.FileManager;

package projetal2020 {

  import fileManager.DonneesIncorectesException

  object Main extends App {
    println("Hello !")
    val x = System.getProperty("user.dir");

    try {
      val myTerrasse = fileManager.FileManager.getFromFile(x.concat("\\tondeuses\\1.txt"))
      println("File Read")
      println("Begin")

      myTerrasse.start()

      println("End")
      println(FileManager.createJson(myTerrasse))
    } catch {
      case _: DonneesIncorectesException => println("Fichier Incorrecte")
    }
  }

}

