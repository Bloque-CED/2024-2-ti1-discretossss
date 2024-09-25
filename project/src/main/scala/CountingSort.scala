 import scala.annotation.tailrec

  object CountingSort {

    /**
     * Function that performs Counting Sort on a list of integers.
     * This function coordinates the entire sorting process using 4 recursive helper functions.
     *
     * @param input List of integers to sort.
     * @param maxValue Maximum value in the input list.
     * @return A sorted list of integers.
     */

    // First function: Count the occurrences of each element
    @tailrec
    def countOccurrences(input: List[Int], counts: Array[Int]): Array[Int] = input match {
      case Nil => counts
      case head :: tail =>
        counts(head) += 1
        countOccurrences(tail, counts)
    }

    // Second function: Calculate cumulative sums for each position
    @tailrec
    def computeCumulativeSums(counts: Array[Int], index: Int = 1): Array[Int] = {
      if (index >= counts.length) counts
      else {
        counts(index) += counts(index - 1)
        computeCumulativeSums(counts, index + 1)
      }
    }

    // Third function: Build the sorted output list
    @tailrec
    def buildSortedList(input: List[Int], counts: Array[Int], output: Array[Int]): Array[Int] = input match {
      case Nil => output
      case head :: tail =>
        counts(head) -= 1
        output(counts(head)) = head
        buildSortedList(tail, counts, output)
    }

    // Fourth function: Auxiliary recursive function to display the sorted array
    @tailrec
    def displaySortedList(lst: List[Int]): Unit = lst match {
      case Nil => println("End of sorted list")
      case head :: tail =>
        println(head)
        displaySortedList(tail)
    }
}
