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



