package fileManager.Json

import play.api.libs.json.{Json, Writes}

case class Point(x: Int, y: Int)
object Point {
  def apply(x: Int, y: Int): Point = new Point(x, y)
  implicit val w: Writes[Point] = Json.writes[Point]
}
