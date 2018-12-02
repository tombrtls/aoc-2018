package tombrtls.adventofcode.day2

object BoxIdFinder {
  def main(args: Array[String]): Unit = {
    val similarBoxIds = findSimilarBoxIds(PackageData.boxIds)
    print(s"Output: ${common(similarBoxIds._1, similarBoxIds._2)}")
  }

  def findSimilarBoxIds(boxIds: Seq[String]): (String, String) = {
    val pairs = boxIds
      .map { boxId =>
        (boxId, boxIds.diff(boxId))
      }
      .flatMap { case (boxId, others) =>
        others.filter(singleCharacterDifferent(boxId)).headOption match {
          case Some(similarBoxId) => Some((boxId, similarBoxId))
          case None => None
        }
      }

    pairs.head
  }

  def singleCharacterDifferent(string1: String)(string2: String): Boolean = {
    string1.zip(string2).count { (chars) => chars._1 != chars._2} == 1
  }

  def common(string1: String, string2: String): String = {
    string1.intersect(string2)
  }
}
