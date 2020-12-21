package fileManager

import better.files.File
import models.{Terrasse, Tondeuse}

import scala.annotation.tailrec;

object FileManager {
  def getFromFile(filePath: String) : Terrasse = {
    val terrasse: Terrasse = new Terrasse(0,0, List())
    var currentTondeusePosX: Int = -1
    var currentTondeusePosY: Int = -1
    var currentTondeuseOrientation: Char = 'X'
    @tailrec
    def helper(lines: List[String]): Unit = {
      if (lines.nonEmpty) {
        val currentLine = lines.head
        val lineToList = currentLine.split(" ")
        if (lineToList.isEmpty) {
          helper(lines.tail)
        } else {
          lineToList.length match {
            case 0 => helper(lines.tail)
            case 1 => {
              if (currentTondeusePosX == -1 || currentTondeusePosY == -1 || currentTondeuseOrientation == 'X')
                throw DonneesIncorectesException()
              else {
                lineToList(0).foreach(x => {
                  if (List('G', 'D', 'A').contains(x)) {

                  } else {
                    throw DonneesIncorectesException()
                  }
                })
                terrasse.AddTondeuse(
                  new Tondeuse(
                    currentTondeusePosX,
                    currentTondeusePosY,
                    currentTondeuseOrientation,
                    lineToList(0).toCharArray.toList,
                    terrasse)
                )
              }
              currentTondeusePosX = -1
              currentTondeusePosY = -1
              currentTondeuseOrientation = 'X'
              helper(lines.tail)
            }
            case 2 => {
              try {
                val width = lineToList(0).toInt
                val height = lineToList(1).toInt
                terrasse.setSize((width, height))
              } catch {
                case _: Exception => throw DonneesIncorectesException()
              }
              helper(lines.tail)
            }
            case 3 => {
              try {
                currentTondeusePosX = lineToList(0).toInt
                currentTondeusePosY = lineToList(1).toInt
                if (List('N', 'W', 'E', 'S').contains(lineToList(2).head) &&
                  currentTondeusePosX >= 0 &&
                  currentTondeusePosX < terrasse.width &&
                  currentTondeusePosY >= 0 &&
                  currentTondeusePosY < terrasse.height)
                  currentTondeuseOrientation = lineToList(2).head
                else {
                  throw DonneesIncorectesException()
                }
              } catch {
                case _: Exception => throw DonneesIncorectesException()
              }
              helper(lines.tail)
            }
            case _ => throw DonneesIncorectesException()
          }
        }
      } else {
      }
    }
    val f = File(filePath);
    val linesInFile = f.lines.toList;
    helper(linesInFile)

    if (currentTondeusePosX != -1 || currentTondeusePosY != -1 || currentTondeuseOrientation != 'X')
      throw DonneesIncorectesException()
    else
      terrasse
  }

  def createJson(terrasse: Terrasse): String = {
    ""
  }

}
