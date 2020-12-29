package models

import scala.annotation.tailrec

@SuppressWarnings(Array("org.wartremover.warts.Var"))
class Terrasse(var height: Int, var width: Int, var tondeuses: List[Movable]) {
  def move(tondeuse: Tondeuse): Unit = {
    val (x, y) = tondeuse.move()
    if (canMoveToPlace(x, y))
      tondeuse.setPositions(x, y)
  }

  def canMoveToPlace(x: Int, y: Int): Boolean = {
    @tailrec
    def mapTondeusesHelper(list: List[Movable]): Boolean = list match {
      case Nil => true
      case head :: tail =>
        !(head.getPos._1 == x && head.getPos._2 == y) && mapTondeusesHelper(
          tail
        )
    }
    x >= 0 && y >= 0 && x < width && y < height
  }

  def setSize(pos: (Int, Int)): Unit = {
    width = pos._1;
    height = pos._2;
  }

  def AddTondeuse(movable: Movable): Unit = {
    tondeuses = tondeuses.appendedAll(List(movable))
  }

  def start(): Unit = {
    @tailrec
    def helper(movables: List[Movable]): Unit = movables match {
      case Nil => {}
      case head :: tail => {
        head.startMoving()
        helper(tail)
      }
    }
    helper(tondeuses)
  }
}
