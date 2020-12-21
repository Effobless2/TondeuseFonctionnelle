package fileManager

import models.{Movable, Terrasse}
import play.api.libs.json.{Json, Writes}
case class Point(x: Int, y: Int)

object Point {
  def apply(x: Int, y: Int): Point = new Point(x, y)
  implicit val w: Writes[Point] = Json.writes[Point]
}

case class PointWithOrientation(x: Int, y:Int, direction: String){
}

object PointWithOrientation{
  def apply(x: Int, y: Int, direction: String): PointWithOrientation = new PointWithOrientation(x, y, direction)
  implicit val w: Writes[PointWithOrientation] = Json.writes[PointWithOrientation]

}

case class TondeuseJSON(debut: PointWithOrientation, instructions: String, fin: PointWithOrientation)

object TondeuseJSON{
  implicit val w: Writes[TondeuseJSON] = Json.writes[TondeuseJSON]
  def apply(tondeuse: Movable): TondeuseJSON = TondeuseJSON(
    PointWithOrientation(
      tondeuse.getInitialPlaces._1,
      tondeuse.getInitialPlaces._2,
      tondeuse.getInitialOrientation.toString),
    tondeuse.getMoves,
    PointWithOrientation(
      tondeuse.getPos._1,
      tondeuse.getPos._2,
      tondeuse.getOrientation.toString
    ))
}

case class TerrasseJSON(limite: Point, tondeuses: List[TondeuseJSON])

object TerrasseJSON{
  implicit val w: Writes[TerrasseJSON] = Json.writes[TerrasseJSON]
  def apply(terrasse: Terrasse): TerrasseJSON = new TerrasseJSON(
    new Point(
      terrasse.width,
      terrasse.height),
    terrasse.tondeuses.map(t => TondeuseJSON(t)))
}
