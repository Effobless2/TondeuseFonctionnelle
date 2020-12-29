package fileManager

import java.nio.file.NoSuchFileException

import org.scalatest.funsuite.AnyFunSuite

class FileManagerSpec extends AnyFunSuite {
  test("File must be found") {
    val fileLocation =
      System.getProperty("user.dir").concat("\\tondeuses\\1.txt")
    val _ = FileManager.getFromFile(fileLocation)
    assert(true)
  }

  test("Excetion thrown if file not found") {
    val fileLocation =
      System.getProperty("user.dir").concat("\\tondeuses\\NotExistingFile.txt")
    assertThrows[NoSuchFileException](
      FileManager.getFromFile(fileLocation)
    )
  }

  test("Excetion thrown if file incorrect") {
    val fileLocation =
      System.getProperty("user.dir").concat("\\tondeuses\\Incorrect.txt")
    assertThrows[DonneesIncorectesException](
      FileManager.getFromFile(fileLocation)
    )
  }
}
