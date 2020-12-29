package fileManager.Json

import models.Terrasse
import play.api.libs.json.{Json, Writes}

case class TerrasseJSON(limite: Point, tondeuses: List[TondeuseJSON])

object TerrasseJSON {
  implicit val w: Writes[TerrasseJSON] = Json.writes[TerrasseJSON]
  def apply(terrasse: Terrasse): TerrasseJSON =
    new TerrasseJSON(
      new Point(terrasse.width, terrasse.height),
      terrasse.tondeuses.map(t => TondeuseJSON(t))
    )
}
