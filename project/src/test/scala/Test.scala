import munit.FunSuite

// CountingSort tests
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

// Heapsort test ----------------------------------------------------

class HeapsortFunctions {
   @tailrec
    def buildHeap(list: List[Int], heap: List[Int]): List[Int] = list match {
      case Nil => heap
      case head :: tail => buildHeap(tail, siftUp(heap :+ head, heap.length, compare))
    }

    @tailrec
    def siftUp(heap: List[Int], index: Int, compare: (Int, Int) => Boolean): List[Int] = {
    if (index == 0) heap
    else {
      val parentIndex = (index - 1) / 2
      if (compare(heap(index), heap(parentIndex))) {
        siftUp(swap(heap, index, parentIndex), parentIndex, compare)
      } else {
        heap
      }
    }
  }

  @tailrec
  def siftDown(heap: List[Int], index: Int, endIndex: Int, compare: (Int, Int) => Boolean): List[Int] = {
    val leftChild = 2 * index + 1
    val rightChild = 2 * index + 2
    var largest = index

    if (leftChild < endIndex && compare(heap(leftChild), heap(largest))) largest = leftChild
    if (rightChild < endIndex && compare(heap(rightChild), heap(largest))) largest = rightChild

    if (largest != index) siftDown(swap(heap, index, largest), largest, endIndex, compare)
      else heap
    }
  @tailrec
  def sort(heap: List[Int], sorted: List[Int], endIndex: Int): List[Int] = {
    if (endIndex <= 0) sorted
    else {
      val newHeap = siftDown(swap(heap, 0, endIndex - 1), 0, endIndex - 1, compare)
      sort(newHeap, heap.head :: sorted, endIndex - 1)
    }
   }
}

class HeapSortTest extends FunSuite {

  test("buildHeap debe construir un heap correctamente") {
    val list = List(4, 10, 3, 5, 1)
    val expectedHeap = List(10, 5, 3, 4, 1)
    val result = HeapSort.buildHeap(list, List.empty[Int])(HeapSort.ascendingOrder)
    assertEquals(result, expectedHeap)
  }

  test("siftUp debe reorganizar el heap después de añadir un elemento") {
    val heap = List(10, 5, 3)
    val result = HeapSort.siftUp(heap :+ 8, heap.length, HeapSort.ascendingOrder)
    val expected = List(10, 8, 3, 5)
    assertEquals(result, expected)
  }

  test("siftDown debe reorganizar el heap correctamente después de un intercambio") {
    val heap = List(10, 5, 3, 1)
    val result = HeapSort.siftDown(HeapSort.swap(heap, 0, 3), 0, 3, HeapSort.ascendingOrder)
    val expected = List(5, 4, 3, 1)
    assertEquals(result, expected)
  }

  test("sort debe ordenar correctamente el heap") {
    val heap = List(10, 5, 3, 4, 1)
    val result = HeapSort.sort(heap, List.empty[Int], heap.length)(HeapSort.ascendingOrder)
    val expected = List(1, 3, 4, 5, 10)
    assertEquals(result, expected)
  }
}

// RadixSort  test ----------------------------------
class RadixSortFunctions {
  @tailrec
    def sortByDigit(xs: List[Int], exp: Int): List[Int] = {
      if (exp > maxNum) xs
      else {
        val sortedByCurrentDigit = sortFunc(xs, exp)
        sortByDigit(sortedByCurrentDigit, exp * 10) // Move to the next digit
      }
    }def countOccurrences(xs: List[Int]): Unit = xs match {
      case Nil => // Base case: nothing left to process
      case head :: tail =>
        val digit = getDigit(head, exp)
        count(digit) += 1
        countOccurrences(tail)
    }

    // Tail-recursive function to accumulate the counts
    @tailrec
    def accumulateCounts(index: Int): Unit = {
      if (index >= 10) () // Base case: end of counting array
      else {
        if (index > 0) count(index) += count(index - 1)
        accumulateCounts(index + 1)
      }
    }

    // Tail-recursive function to build the output list
    @tailrec
    def buildOutput(xs: List[Int], output: Array[Int], i: Int): Array[Int] = xs match {
      case Nil => output
      case head :: tail =>
        val digit = getDigit(head, exp)
        count(digit) -= 1
        output(count(digit)) = head
        buildOutput(tail, output, i - 1)
    }@tailrec
  def getDigit(num: Int, exp: Int): Int = {
    if (exp == 1) num % 10
    else getDigit(num / 10, exp / 10)
  }
}
class RadixSortTest extends FunSuite {
  
  test("sortByDigit debe ordenar por el dígito más significativo") {
    val list = List(170, 45, 75, 90, 802, 24, 2, 66)
    val result = RadixSort.sortByDigit(list, 1)
    val expected = List(170, 90, 802, 2, 24, 45, 66, 75)
    assertEquals(result, expected)
  }

  test("countOccurrences debe contar correctamente las ocurrencias de dígitos") {
    val countArray = Array.fill(10)(0)
    RadixSort.countOccurrences(List(170, 45, 75, 90), 1, countArray)
    val expected = Array(1, 1, 0, 0, 1, 2, 0, 0, 0, 1)
    assertEquals(countArray, expected)
  }

  test("accumulateCounts debe acumular correctamente los valores") {
    val countArray = Array(1, 1, 0, 0, 1, 2, 0, 0, 0, 1)
    RadixSort.accumulateCounts(0, countArray)
    val expected = Array(1, 2, 2, 2, 3, 5, 5, 5, 5, 6)
    assertEquals(countArray, expected)
  }

  test("buildOutput debe construir correctamente la lista final") {
    val input = List(170, 45, 75, 90, 802, 24, 2, 66)
    val countArray = Array(1, 2, 3, 4, 5)
    val output = RadixSort.buildOutput(input, new Array[Int](input.length), countArray, 3)
    val expected = List(2, 24, 45, 66, 75, 90, 170, 802)
    assertEquals(output.toList, expected)
  }

