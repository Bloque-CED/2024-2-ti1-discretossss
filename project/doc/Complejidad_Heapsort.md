# Complejidad temporal de HeapSort
**Heapsort consta de dos fases principales:**
- construir un max-heap a partir del array 
- luego extraer repetidamente el máximo para ordenar el array.   

Para transformar el array de entrada en un max-heap, el algoritmo recorre la mitad inferior del array (los nodos que tienen hijos) y aplica una operación llamada heapify para garantizar que el árbol binario cumpla con la propiedad de heap. Todo esto se hace para asegurar que el nodo padre sea mayor o igual a sus hijos.    

El costo de construir el heap es O(n), porque la cantidad de trabajo disminuye para los niveles inferiores del árbol.

Una vez que se ha construido el max-heap, el siguiente paso es extraer repetidamente el elemento máximo (que está en la raíz del heap) y colocarlo al final del array, reduciendo el tamaño del heap en 1 en cada paso.   

- Se extraen n elementos en total, y cada uno de estos requiere una operación heapify de costo O(\log n).   

- Como se realizan n extracciones, y cada una tiene un costo de O(\log n), el costo total de esta fase es O(n log n).   

- Para calcular la complejidad temporal de HeapSort debemos de sumar el costo de las dos fases anteriormente mencionadas:

  - >O(n) + O(n log n).    
  
Lo que nos da como resultado una complejidad temporal de:    
>O(n \log n) 

## Complejidad temporal de siftUp
> def siftUp(heap: List[Int], index: Int, compare: (Int, Int) => Boolean): List[Int] = {
if (index == 0) **heap costo constante de O(1), ya que es una simple comparación y retorno del resultado.**    
else {
    val parentIndex = (index - 1) / 2 **operación aritmética de costo constante O(1)**   
      if (compare(heap(index), heap(parentIndex))) { **La comparación también tiene un costo de O(1)**
    
     siftUp(swap(heap, index, parentIndex), parentIndex, compare) costo de O(1) ya que es una lista de tamaño fijo donde sólo se intercambian dos elementos.
    } else {
      heap                                              
        }    
      }    
    }
El número total de llamadas recursivas es, por lo tanto, el número de veces que podemos dividir index por 2 antes de llegar a 0. Esto corresponde a O(log n), donde n es el tamaño de la lista heap.
Cada llamada recursiva tiene un costo de O(1)
Dado que hay un total de O(log n) llamadas recursivas, la complejidad temporal total es
O(log n)

# Complejidad temporal de siftDown
>@tailrec   
def siftDown(heap: List[Int], index: Int, endIndex: Int, compare: (Int, Int) => Boolean): List[Int] = {    
val leftChild = 2 * index + 1 **costo constante O(1)**    
val rightChild = 2 * index + 2 **costo constante O(1)**    
var largest = index **costo constante de O(1), ya que es una simple comprobación y retorno del resultado.**        
if (leftChild < endIndex && compare(heap(leftChild), heap(largest))) largest = leftChild    
if (rightChild < endIndex && compare(heap(rightChild), heap(largest))) largest = rightChild    
if (largest != index) siftDown(swap(heap, index, largest), largest, endIndex, compare)
else heap    
}



