import scala.annotation.tailrec

/**
 * Object containing radix sort implementation.
 */
object RadixSort {

  /**
   * Radix sort algorithm.
   * It sorts a list of integers using a provided digit-sorting function.
   *
   * @param xs List of integers to be sorted
   * @param sortFunc Function used to sort the integers by digit
   * @return Sorted list of integers
   */
  def radixSort(xs: List[Int], sortFunc: (List[Int], Int) => List[Int]): List[Int] = {
    // Find the maximum number to know the number of digits
    val maxNum = xs.max

    // Process each digit position
    @tailrec
    def sortByDigit(xs: List[Int], exp: Int): List[Int] = {
      if (exp > maxNum) xs
      else {
        val sortedByCurrentDigit = sortFunc(xs, exp)
        sortByDigit(sortedByCurrentDigit, exp * 10) // Move to the next digit
      }
    }

    sortByDigit(xs, 1) // Start sorting by the least significant digit
  }

  /**
   * Counting sort algorithm adapted for Radix Sort.
   * It sorts based on the digit represented by the exp factor (units, tens, hundreds, etc.).
   *
   * @param xs List of integers to be sorted by a specific digit
   * @param exp Exponent representing the current digit to sort by
   * @return List sorted by the specific digit
   */
  def countingSortForRadix(xs: List[Int], exp: Int): List[Int] = {
    // Initialize counting array for digits (0-9)
    val count = Array.fill(10)(0)

    // Tail-recursive function to count occurrences of each digit
    @tailrec
    def countOccurrences(xs: List[Int]): Unit = xs match {
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
    }

    // Count digit occurrences
    countOccurrences(xs)

    // Accumulate counts to get positions
    accumulateCounts(0)

    // Create output array
    val output = Array.fill(xs.length)(0)

    // Build output array based on the current digit
    buildOutput(xs.reverse, output, xs.length - 1).toList
  }

  /**
   * Function to get the digit in the current place (units, tens, hundreds, etc.).
   * @param num The number to extract the digit from
   * @param exp The current exponent (position of the digit)
   * @return The digit at the current position
   */
  @tailrec
  def getDigit(num: Int, exp: Int): Int = {
    if (exp == 1) num % 10
    else getDigit(num / 10, exp / 10)
  }

  /**
   * Test the radix sort with example input.
   */
  def main(args: Array[String]): Unit = {
    val unsortedList = List(170, 45, 75, 90, 802, 24, 2, 66)
    val sortedList = radixSort(unsortedList, countingSortForRadix)
    println(s"Sorted List: $sortedList")
  }
}
