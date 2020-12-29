package models

trait Movable {
  def getTerrasse(): Terrasse

  def move(): (Int, Int)
  def turnLeft(): Unit
  def turnRight(): Unit
  def setPositions(x: Int, y: Int): Unit
  def getPos: (Int, Int)
  def startMoving(): Unit
  def getOrientation: Char
  def getMoves: String
  def getInitialPlaces: (Int, Int)
  def getInitialOrientation: Char
}
