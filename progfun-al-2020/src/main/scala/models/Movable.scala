package models

trait Movable {
  def getTerrasse(): Terrasse

  def move(): (Int, Int)
  def turnLeft()
  def turnRight()
  def setPositions(x: Int, y: Int)
  def getPos: (Int, Int)
  def startMoving()
  def getOrientation: Char
  def getMoves: String
  def getInitialPlaces: (Int, Int)
  def getInitialOrientation: Char
}
