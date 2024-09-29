import munit.FunSuite
class CountingSortFunctions {
  def rebuildSortedList(counts: List[Int], acc: List[Int] = List(), num: Int = 0): List[Int] = counts match {
    case Nil => acc
    case 0 :: tail => rebuildSortedList(tail, acc, num + 1)
    case head :: tail => rebuildSortedList((head - 1) :: tail, acc :+ num, num)
  }

  def countHelper(lst: List[Int], counts: List[Int]): List[Int] = lst match {
    case Nil => counts
    case head :: tail =>
      val (before, after) = counts.splitAt(head)
      countHelper(tail, before ::: (after.head + 1) :: after.tail)
  }

  def initList(length: Int, acc: List[Int] = List()): List[Int] = {
    if (length <= 0) acc
    else initList(length - 1, 0 :: acc)
  }
}


class CountingSortTest extends FunSuite {


  val countingSort = new CountingSortFunctions()

  test("initList debe devolver una lista de 3 ceros cuando la longitud es 3") {
    assert(countingSort.initList(3) == List(0, 0, 0))
  }

  test("countHelper debe retornar una lista de counts") {
    val input = List(1, 2, 2, 3)
    val counts = countingSort.initList(4)  
    val expected = List(0, 1, 2, 1)
    assert(countingSort.countHelper(input, counts) == expected)
  }

  test("rebuildSortedList debe reconstruir una lista de counts") {
    val input = List(0, 1, 2)
    val expected = List(1, 2, 2)
    assert(countingSort.rebuildSortedList(input) == expected)
  }
}
