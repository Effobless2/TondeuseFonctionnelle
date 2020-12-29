package models

import scala.annotation.tailrec

@SuppressWarnings(Array("org.wartremover.warts.Var"))
class Tondeuse(
    var x: Int,
    var y: Int,
    var orientation: Char,
    movements: String,
    terrasse: Terrasse
) extends Movable {
  val firstX = x
  val firstY = y
  val firstDir = orientation

  override def startMoving(): Unit = {
    @tailrec
    def helper(nextMoves: List[Char]): Unit = nextMoves match {
      case Nil => {}
      case head :: tail => {
        treatNextInput(head)
        helper(tail)
      }
    }
    helper(movements.toCharArray.toList)
  }

  def treatNextInput(input: Char): Unit = input match {
    case 'G' => turnLeft();
    case 'D' => turnRight();
    case 'A' => terrasse.move(this);
  }

  override def move(): (Int, Int) = orientation match {
    case 'N' => (x, y + 1);
    case 'S' => (x, y - 1);
    case 'W' => (x - 1, y);
    case 'E' => (x + 1, y);
  }

  override def turnLeft(): Unit = orientation match {
    case 'N' => orientation = 'W';
    case 'W' => orientation = 'S';
    case 'S' => orientation = 'E';
    case 'E' => orientation = 'N';
  }

  override def turnRight(): Unit = orientation match {
    case 'N' => orientation = 'E';
    case 'W' => orientation = 'N';
    case 'S' => orientation = 'W';
    case 'E' => orientation = 'S';
  }

  override def setPositions(posX: Int, posY: Int): Unit = {
    x = posX
    y = posY
  }

  override def getPos: (Int, Int) = (x, y)

  override def getOrientation: Char = orientation

  override def getMoves: String = movements

  override def getTerrasse(): Terrasse = terrasse

  override def getInitialPlaces: (Int, Int) = (firstX, firstY)

  override def getInitialOrientation: Char = firstDir
}
