package fileManager.Json

import play.api.libs.json.{Json, Writes}

case class PointWithOrientation(x: Int, y: Int, direction: String) {}

object PointWithOrientation {
  def apply(x: Int, y: Int, direction: String): PointWithOrientation =
    new PointWithOrientation(x, y, direction)
  implicit val w: Writes[PointWithOrientation] =
    Json.writes[PointWithOrientation]

}
