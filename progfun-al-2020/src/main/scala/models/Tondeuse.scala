package models{

  import scala.annotation.tailrec

  trait Movable {
    def getTerrasse(): Terrasse

    def move(): (Int, Int)
    def turnLeft()
    def turnRight()
    def setPositions(x: Int, y: Int)
    def getPos(): (Int, Int)
    def startMoving()
    def getOrientation(): Char
    def getMoves(): List[Char]
    def getInitialPlaces(): (Int, Int)
  }

  class Tondeuse(var x: Int, var y: Int, var orientation: Char, movements: List[Char], terrasse: Terrasse) extends Movable {
    val firstX = x
    val firstY = y

    override def startMoving(): Unit = {
      @tailrec
      def helper(nextMoves: List[Char]): Unit = nextMoves match {
        case Nil => {}
        case head::tail => {
          treatNextInput(head)
          helper(tail)
        }
      }
      helper(movements)
    }

    def treatNextInput(input: Char): Unit = input match {
      case 'G' => turnLeft();
      case 'D' => turnRight();
      case 'A' => terrasse.move(this);
    }

    override def move(): (Int, Int) = orientation match {
      case 'N' => (x, y+1);
      case 'S' => (x, y-1);
      case 'W' => (x-1, y);
      case 'E' => (x+1, y);
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

    override def getPos(): (Int, Int) = (x, y)

    override def getOrientation(): Char = orientation

    override def getMoves(): List[Char] = movements

    override def getTerrasse(): Terrasse = terrasse

    override def getInitialPlaces(): (Int, Int) = (firstX, firstY)
  }

  class Terrasse(var height: Int, var width: Int, var tondeuses: List[Movable]){


    def move(tondeuse: Tondeuse): Unit = {
      val (x, y) = tondeuse.move()
      if (canMoveToPlace(x,y))
        tondeuse.setPositions(x,y)
    }

    def canMoveToPlace(x: Int, y: Int): Boolean = {
      @tailrec
      def mapTondeusesHelper(list: List[Movable]): Boolean = list match {
        case Nil => true
        case head::tail => head.getPos() != (x,y) && mapTondeusesHelper(tail)
      }
      x >= 0 && y >= 0 && x < width && y < height
    }

    def setSize(pos: (Int, Int)): Unit ={
      width = pos._1;
      height = pos._2;
    }

    def AddTondeuse(movable: Movable): Unit ={
      tondeuses = tondeuses.appendedAll(List(movable))
    }

    def start(): Unit = {
      @tailrec
      def helper(movables: List[Movable]): Unit = movables match {
        case Nil => {}
        case head::tail => {
          head.startMoving()
          helper(tail)
        }
      }
      helper(tondeuses)
    }
  }
}




