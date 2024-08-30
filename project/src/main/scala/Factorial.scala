object Factorial extends App {

  import scala.annotation.tailrec

  /**
   * Computes the factorial of each number in a list.
   * @param nums A list of integers for which to calculate the factorial.
   * @return A list of factorials corresponding to each integer in the input list.
   */
  def factorialList(nums: List[Int]): List[Int] = {

    /**
     * Helper function that computes the factorial using tail recursion.
     * @param n The integer for which to calculate the factorial.
     * @param acc The accumulator that stores the ongoing product (initially set to 1).
     * @return The factorial of the given integer.
     */
    @tailrec
    def factorialTailRec(n: Int, acc: Int = 1): Int = {
      n match {
        case 0 => acc
        case _ => factorialTailRec(n - 1, n * acc)
      }
    }

    // Apply factorialTailRec to each element in the list using pattern matching
    nums match {
      case Nil => Nil // Base case: If the list is empty, return an empty list
      case head :: tail => factorialTailRec(head) :: factorialList(tail) // Recursive case
    }
  }


}
