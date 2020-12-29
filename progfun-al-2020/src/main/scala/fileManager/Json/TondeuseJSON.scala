package fileManager.Json

import models.Movable
import play.api.libs.json.{Json, Writes}

case class TondeuseJSON(
    debut: PointWithOrientation,
    instructions: String,
    fin: PointWithOrientation
)

object TondeuseJSON {
  implicit val w: Writes[TondeuseJSON] = Json.writes[TondeuseJSON]
  def apply(tondeuse: Movable): TondeuseJSON =
    TondeuseJSON(
      PointWithOrientation(
        tondeuse.getInitialPlaces._1,
        tondeuse.getInitialPlaces._2,
        tondeuse.getInitialOrientation.toString
      ),
      tondeuse.getMoves,
      PointWithOrientation(
        tondeuse.getPos._1,
        tondeuse.getPos._2,
        tondeuse.getOrientation.toString
      )
    )
}
