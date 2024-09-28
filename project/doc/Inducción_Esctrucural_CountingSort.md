# Inducción Estructural
Ahora, para la inducción estructural:
## Caso base:
Si la lista está vacía (`Nil`), la función `countOccurrences` no hace nada, y `buildSortedList` devuelve la lista vacía. El algoritmo es correcto para este caso trivial.
## Hipótesis inductiva:
Supongamos que el algoritmo es correcto para una lista de tamaño `n`. Es decir, para cualquier lista de tamaño `n`, el algoritmo ordena correctamente los elementos.
## Paso inductivo:
Ahora, debemos demostrar que el algoritmo sigue siendo correcto al agregar un elemento adicional a la lista (lo que da como resultado una lista de tamaño `n+1`). La función `countOccurrences` incrementa el conteo del nuevo elemento correctamente, y `buildSortedList` lo coloca en la posición adecuada basada en el conteo acumulado. Como el algoritmo es correcto para tamaño `n` y coloca correctamente el nuevo elemento, podemos concluir que también es correcto para listas de tamaño `n+1`.
