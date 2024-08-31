import munit.FunSuite

class FactorialListTest extends FunSuite {

  // Test 1: Verificar el factorial de 3
  test("Factorial of 3 should be 6") {
    assertEquals(Factorial.factorialList(List(3)), List(6))
  }

  // Test 2: Verificar que la lista vacía retorna lista vacía
  test("Factorial of empty list should be empty list") {
    assertEquals(Factorial.factorialList(List()), List())
  }

  // Test 3: Verificar factoriales de múltiples números
  test("Factorial of [0, 1, 2, 3, 4] should be [1, 1, 2, 6, 24]") {
    assertEquals(Factorial.factorialList(List(0, 1, 2, 3, 4)), List(1, 1, 2, 6, 24))
  }

  // Test 4: Verificar comportamiento con números negativos
  test("Factorial of negative numbers should not throw an error") {
    assertEquals(Factorial.factorialList(List(-1, -2)), List(1, 1)) // Suponiendo que devuelve 1 para negativos
  }

  // Test 5: Verificar factoriales de números grandes
  test("Factorial of large numbers should be correct") {
    assertEquals(Factorial.factorialList(List(5, 6)), List(120, 720))
  }
}