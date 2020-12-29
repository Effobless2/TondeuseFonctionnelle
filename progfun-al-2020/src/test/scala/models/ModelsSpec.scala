package models

import org.scalatest.funsuite.AnyFunSuite

class ModelsSpec extends AnyFunSuite {
  test("Init Terrasse") {
    val terrasse = new Terrasse(5, 8, List())
    val tondeuse = new Tondeuse(4, 3, 'S', "", terrasse)
    terrasse.AddTondeuse(tondeuse)
    assert(terrasse.height == 5)
    assert(terrasse.width == 8)
    assert(terrasse.tondeuses.length == 1)
    assert(tondeuse.firstDir == 'S')
    assert(tondeuse.orientation == 'S')
    assert(tondeuse.firstX == 4)
    assert(tondeuse.firstY == 3)
    assert(tondeuse.getPos._1 == 4)
    assert(tondeuse.getPos._2 == 3)
    assert(tondeuse.getTerrasse() == terrasse)
  }

  test("Move tondeuse") {
    val terrasse = new Terrasse(5, 8, List())
    val tondeuse = new Tondeuse(4, 3, 'S', "A", terrasse)
    terrasse.AddTondeuse(tondeuse)
    terrasse.start()
    assert(tondeuse.getPos._2 == 2)
    assert(tondeuse.firstX == 4)
    assert(tondeuse.firstY == 3)
    assert(tondeuse.firstDir == 'S')
  }

  test("Not Move tondeuse") {
    val terrasse = new Terrasse(5, 8, List())
    val tondeuse = new Tondeuse(7, 3, 'S', "GA", terrasse)
    terrasse.AddTondeuse(tondeuse)
    terrasse.start()
    assert(tondeuse.orientation == 'E')
    assert(tondeuse.getPos._1 == 7)
    assert(tondeuse.firstX == 7)
    assert(tondeuse.firstY == 3)
    assert(tondeuse.firstDir == 'S')
  }
}
