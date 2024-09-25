import scala.annotation.tailrec

object HeapSort {

  /**
   * Sorts a list of integers using the HeapSort algorithm.
   *
   * @param list List of integers to be sorted.
   * @param compare A comparison function that defines the sorting order.
   * @return A sorted list of integers.
   */
  def heapSort(list: List[Int], compare: (Int, Int) => Boolean): List[Int] = {
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

    def swap(list: List[Int], i: Int, j: Int): List[Int] = {
      list.updated(i, list(j)).updated(j, list(i))
    }

    val heapifiedList = buildHeap(list, List.empty[Int])
    sort(heapifiedList, List.empty[Int], heapifiedList.length)
  }

  def ascendingOrder(a: Int, b: Int): Boolean = a > b

  def descendingOrder(a: Int, b: Int): Boolean = a < b



}