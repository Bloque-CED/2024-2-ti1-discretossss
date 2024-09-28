

  object CountingSort {

    import scala.annotation.tailrec

    /**
     * Function to get the maximum value in a list
     * @param lst the input list
     * @return the maximum value in the list
     */
    def getMax(lst: List[Int]): Int = lst match {
      case Nil => throw new NoSuchElementException("List is empty")
      case head :: tail => tail.foldLeft(head)((max, num) => if (num > max) num else max)
    }

    /**
     * Tail-recursive function to initialize a list of zeros of a given length
     * @param length the length of the list
     * @return a list of zeros of the specified length
     */
    @tailrec
    def initList(length: Int, acc: List[Int] = List()): List[Int] = {
      if (length <= 0) acc
      else initList(length - 1, 0 :: acc)
    }

    /**
     * Function to count the occurrences of each element in the input list
     * @param lst the input list
     * @param maxVal the maximum value in the list
     * @return a list of counts where the index represents the number and the value represents the count
     */
    def countOccurrences(lst: List[Int], maxVal: Int): List[Int] = {
      @tailrec
      def countHelper(lst: List[Int], counts: List[Int]): List[Int] = lst match {
        case Nil => counts
        case head :: tail =>
          val (before, after) = counts.splitAt(head)
          countHelper(tail, before ::: (after.head + 1) :: after.tail)
      }

      countHelper(lst, initList(maxVal + 1))
    }

    /**
     * Function to rebuild the sorted list based on the counts
     * @param counts the list of counts
     * @param acc accumulator for the sorted list
     * @return the sorted list
     */
    @tailrec
    def rebuildSortedList(counts: List[Int], acc: List[Int] = List(), num: Int = 0): List[Int] = counts match {
      case Nil => acc
      case 0 :: tail => rebuildSortedList(tail, acc, num + 1)
      case head :: tail => rebuildSortedList((head - 1) :: tail, acc :+ num, num)
    }

    /**
     * Function to sort the input list using the counting sort algorithm
     * @param lst the input list
     * @return the sorted list
     */
    def countingSort(lst: List[Int]): List[Int] = {
      val maxVal = getMax(lst)
      val counts = countOccurrences(lst, maxVal)
      rebuildSortedList(counts)
    }

    /**
     * Main function with an example to test countingSort
     */
    object CountingSortExample {
      def main(args: Array[String]): Unit = {
        val unsortedList = List(4, 2, 2, 8, 3, 3, 1)
        println(s"Unsorted List: $unsortedList")
        val sortedList = countingSort(unsortedList)
        println(s"Sorted List: $sortedList")
      }
    }

  }
