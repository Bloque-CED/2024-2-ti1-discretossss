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
    // Get the digit in the current place (units, tens, hundreds, etc.)
    def getDigit(num: Int, exp: Int): Int = (num / exp) % 10

    // Initialize counting array for digits (0-9)
    val count = Array.fill(10)(0)

    // Count occurrences of each digit
    xs.foreach { num =>
      val digit = getDigit(num, exp)
      count(digit) += 1
    }

    // Accumulate counts to get positions in the output array
    for (i <- 1 until 10) {
      count(i) += count(i - 1)
    }

    // Output array to hold sorted numbers based on the current digit
    val output = Array.fill(xs.length)(0)

    // Build the output array by placing numbers in their correct positions
    for (i <- xs.length - 1 to 0 by -1) {
      val num = xs(i)
      val digit = getDigit(num, exp)
      count(digit) -= 1
      output(count(digit)) = num
    }

    // Convert the output array back to a list
    output.toList
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
