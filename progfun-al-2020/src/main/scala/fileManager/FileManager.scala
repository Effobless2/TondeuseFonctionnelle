package fileManager

import better.files.File
import fileManager.Json.TerrasseJSON
import play.api.libs.json.JsValue
import models.{Terrasse, Tondeuse}

import scala.annotation.tailrec;

object FileManager {
  @SuppressWarnings(
    Array("org.wartremover.warts.Throw", "org.wartremover.warts.Var")
  )
  def getFromFile(filePath: String): Terrasse = {
    val terrasse: Terrasse = new Terrasse(0, 0, List())
    var currentTondeusePosX: Int = -1
    var currentTondeusePosY: Int = -1
    var currentTondeuseOrientation: Char = 'X'
    @tailrec
    def helper(lines: List[String]): Unit = {
      if (lines.nonEmpty) {
        val currentLine = lines.headOption.getOrElse(default = "")
        val lineToList = currentLine.split(" ")
        if (lineToList.isEmpty) {
          helper(lines.drop(1))
        } else {
          lineToList.length match {
            case 0 => helper(lines.drop(1))
            case 1 => {
              if (currentTondeusePosX == -1 || currentTondeusePosY == -1 || currentTondeuseOrientation == 'X')
                throw DonneesIncorectesException()
              else {
                lineToList(0).foreach(x => {
                  if (List('G', 'D', 'A').contains(x)) {} else {
                    throw DonneesIncorectesException()
                  }
                })
                terrasse.AddTondeuse(
                  new Tondeuse(
                    currentTondeusePosX,
                    currentTondeusePosY,
                    currentTondeuseOrientation,
                    lineToList(0),
                    terrasse
                  )
                )
              }
              currentTondeusePosX = -1
              currentTondeusePosY = -1
              currentTondeuseOrientation = 'X'
              helper(lines.drop(1))
            }
            case 2 => {
              try {
                val width = lineToList(0).toInt
                val height = lineToList(1).toInt
                terrasse.setSize((width, height))
              } catch {
                case _: Exception => throw DonneesIncorectesException()
              }
              helper(lines.drop(1))
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
              helper(lines.drop(1))
            }
            case _ => throw DonneesIncorectesException()
          }
        }
      } else {}
    }
    val file = File(filePath)
    val linesInFile = file.lines.toList
    if (linesInFile.isEmpty)
      throw DonneesIncorectesException()
    helper(linesInFile)

    if (currentTondeusePosX != -1 || currentTondeusePosY != -1 || currentTondeuseOrientation != 'X')
      throw DonneesIncorectesException()
    else
      terrasse
  }

  def createJson(terrasse: Terrasse): JsValue = {

    val result = TerrasseJSON.w.writes(TerrasseJSON(terrasse))
    result
  }

}
