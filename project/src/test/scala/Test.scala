import munit.FunSuite


class CountingSortFunctions {


  def initList(length: Int, acc: List[Int] = List()): List[Int] = {
    if (length <= 0) acc
    else initList(length - 1, 0 :: acc)
  }


  def countHelper(lst: List[Int], counts: List[Int]): List[Int] = lst match {
    case Nil => counts
    case head :: tail =>
      val updatedCounts = counts.updated(head, counts(head) + 1)
      countHelper(tail, updatedCounts)
  }


  def rebuildSortedList(counts: List[Int], acc: List[Int] = List(), num: Int = 0): List[Int] = counts match {
    case Nil => acc
    case 0 :: tail => rebuildSortedList(tail, acc, num + 1)
    case head :: tail => rebuildSortedList((head - 1) :: tail, acc :+ num, num)
  }
}


class HeapsortFunctions {

  @tailrec
  final def buildHeap(list: List[Int], heap: List[Int], compare: (Int, Int) => Boolean): List[Int] = list match {
    case Nil => heap
    case head :: tail => buildHeap(tail, siftUp(heap :+ head, heap.length, compare), compare)
  }

  @tailrec
  final def siftUp(heap: List[Int], index: Int, compare: (Int, Int) => Boolean): List[Int] = {
    if (index == 0) heap
    else {
      val parentIndex = (index - 1) / 2
      if (compare(heap(index), heap(parentIndex))) {
        siftUp(heap.updated(index, heap(parentIndex)).updated(parentIndex, heap(index)), parentIndex, compare)
      } else {
        heap
      }
    }
  }

  @tailrec
  final def siftDown(heap: List[Int], index: Int, endIndex: Int, compare: (Int, Int) => Boolean): List[Int] = {
    val leftChild = 2 * index + 1
    val rightChild = 2 * index + 2
    var largest = index

    if (leftChild < endIndex && compare(heap(leftChild), heap(largest))) largest = leftChild
    if (rightChild < endIndex && compare(heap(rightChild), heap(largest))) largest = rightChild

    if (largest != index) siftDown(heap.updated(index, heap(largest)).updated(largest, heap(index)), largest, endIndex, compare)
    else heap
  }

  @tailrec
  final def sort(heap: List[Int], sorted: List[Int], endIndex: Int, compare: (Int, Int) => Boolean): List[Int] = {
    if (endIndex <= 0) sorted
    else {
      val newHeap = siftDown(heap.updated(0, heap(endIndex - 1)).updated(endIndex - 1, heap(0)), 0, endIndex - 1, compare)
      sort(newHeap, heap.head :: sorted, endIndex - 1, compare)
    }
  }
}


class RadixSortFunctions {

  @tailrec
  final def sortByDigit(xs: List[Int], exp: Int, maxNum: Int): List[Int] = {
    if (exp > maxNum) xs
    else {
      val sortedByCurrentDigit = sortFunc(xs, exp)
      sortByDigit(sortedByCurrentDigit, exp * 10, maxNum) // Avanzar al siguiente dígito
    }
  }

  def sortFunc(xs: List[Int], exp: Int): List[Int] = {
    val counts = Array.fill(10)(0)
    countOccurrences(xs, exp, counts)
    accumulateCounts(0, counts)
    buildOutput(xs, counts.toList, exp)
  }

  @tailrec
  final def countOccurrences(xs: List[Int], exp: Int, count: Array[Int]): Unit = xs match {
    case Nil =>
    case head :: tail =>
      val digit = (head / exp) % 10
      count(digit) += 1
      countOccurrences(tail, exp, count)
  }

  @tailrec
  final def accumulateCounts(index: Int, count: Array[Int]): Unit = {
    if (index >= 10) ()
    else {
      if (index > 0) count(index) += count(index - 1)
      accumulateCounts(index + 1, count)
    }
  }

  final def buildOutput(xs: List[Int], counts: List[Int], exp: Int): List[Int] = {
    val output = Array.fill(xs.length)(0)
    xs.reverse.foldLeft(counts.toArray) { (updatedCounts, num) =>
      val digit = (num / exp) % 10
      updatedCounts(digit) -= 1
      output(updatedCounts(digit)) = num
      updatedCounts
    }
    output.toList
  }
}

// Tests para CountingSort
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

// Tests para HeapSort
class HeapSortTest extends FunSuite {
  val heapSort = new HeapsortFunctions()

  test("buildHeap debe construir un heap correctamente") {
    val list = List(4, 10, 3, 5, 1)
    val expectedHeap = List(10, 5, 3, 4, 1)
    val result = heapSort.buildHeap(list, List.empty[Int], _ > _)
    assertEquals(result, expectedHeap)
  }

  test("siftUp debe reorganizar el heap después de añadir un elemento") {
    val heap = List(10, 5, 3)
    val result = heapSort.siftUp(heap :+ 8, heap.length, _ > _)
    val expected = List(10, 8, 3, 5)
    assertEquals(result, expected)
  }

  test("siftDown debe reorganizar el heap correctamente después de un intercambio") {
    val heap = List(10, 5, 3, 1)
    val result = heapSort.siftDown(heap.updated(0, heap(3)), 0, 3, _ > _)
    val expected = List(5, 1, 3, 10)
    assertEquals(result, expected)
  }

  test("sort debe ordenar correctamente el heap") {
    val heap = List(10, 5, 3, 4, 1)
    val result = heapSort.sort(heap, List.empty[Int], heap.length, _ > _)
    val expected = List(1, 3, 4, 5, 10)
    assertEquals(result, expected)
  }
}

// Tests para RadixSort
class RadixSortTest extends FunSuite {
  val radixSort = new RadixSortFunctions()

  test("sortByDigit debe ordenar por el dígito más significativo") {
    val list = List(170, 45, 75, 90, 802, 24, 2, 66)
    val result = radixSort.sortByDigit(list, 1, 802)
    val expected = List(2, 24, 45, 66, 75, 90, 170, 802)
    assertEquals(result, expected)
  }

  test("countOccurrences debe contar correctamente las ocurrencias de dígitos") {
    val countArray = Array.fill(10)(0)
    radixSort.countOccurrences(List(170, 45, 75, 90), 1, countArray)
    val expected = Array(1, 1, 0, 0, 1, 2, 0, 0, 0, 0)
    assertEquals(countArray, expected)
  }

  test("accumulateCounts debe acumular correctamente los counts") {
    val countArray = Array(1, 1, 0, 0, 1, 2, 0, 0, 0, 0)
    radixSort.accumulateCounts(0, countArray)
    val expected = Array(1, 2, 2, 2, 3, 5, 5, 5, 5, 5)
    assertEquals(countArray, expected)
  }

  test("buildOutput debe construir la salida correcta a partir de los counts") {
    val list = List(170, 45, 75, 90)
    val counts = List(1, 2, 2, 2, 3, 5, 5, 5, 5, 5)
    val result = radixSort.buildOutput(list, counts, 1)
    val expected = List(170, 45, 75, 90)
    assertEquals(result, expected)
  }
}
