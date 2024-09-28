# Complejidad temporal de CountingSort
### Paso 1: 
Inicializar un array de conteo de tamaño k. Esto toma O(k) tiempo.
### Paso 2: 
Recorrer la lista de entrada (de tamaño n) y contar las frecuencias de los elementos, lo que toma O(n).
### Paso 3: 
Transformar el array de conteo en uno acumulado, lo cual toma O(k) tiempo.
### Paso 4: 
Colocar los elementos en su posición correcta usando el array acumulado, lo que toma O(n) tiempo.
Se deben de sumar todos estos pasos
O(k) + O(n) + O(k) + O(n)
Lo que nos da como resultado
O(n + k)
Por lo que O(n + k) es la complejidad temporal del algoritmo CountingSort.


## Complejidad temporal de InitList

> def initList(length: Int, acc: List[Int] = List()): List[Int] = {    
if (length <= 0) acc **costo constante de O(1) porque es una simple verificación y retorno del resultado.**    
else initList(length - 1, 0 :: acc) **Esta linea implica agregar un elemento al frente de la lista acc. Agregar un elemento al frente de una lista tiene un costo constante O(1).**    
}    

**Cada llamada recursiva tiene un costo de O(1).**   

**Dado que hay un total de n llamadas recursivas, la complejidad temporal total es:**    

> O(n) 

La función initList se llama recursivamente reduciendo length en 1 en cada llamada hasta que llega a 0.     
Si el valor inicial de length es k, habrá exactamente k llamadas recursivas.     
En nuestro contexto, cuando initList es llamada dentro de countOccurrences, length se inicializa como maxVal + 1. Por lo tanto, el número total de llamadas recursivas será maxVal + 1.     
Cada llamada recursiva tiene un costo de O(1)    
Hay un total de maxVal + 1 llamadas recursivas.    
Por lo tanto, la complejidad temporal total de initList es:     
> O(maxVal+1)=O(maxVal)



## Complejidad de countOccurrences
> def countOccurrences(lst: List[Int], maxVal: Int): List[Int] = {    
@tailrec    
def countHelper(lst: List[Int], counts: List[Int]): List[Int] = lst match {    
case Nil => **counts tiene un costo constante de O(1) porque es solo una comprobación y retorno del resultado.**    
case head :: tail =>    
val (before, after) = counts.splitAt(head) La función divide counts en dos partes usando splitAt(head). La operación splitAt tiene una complejidad de O(head) porque debe recorrer head elementos de la lista antes de dividirla.    
countHelper(tail, before ::: (after.head + 1) :: after.tail) **Esta operación de concatenación tiene un costo de O(head) debido a que es necesario recorrer la lista before completamente para concatenar.**     
}    

> countHelper(lst, initList(maxVal + 1)) **Si asumimos que el valor promedio de head es proporcional a maxVal, entonces el costo de cada llamada recursiva es O(maxVal).**
} 

**Como hay un total de n llamadas recursivas, la complejidad temporal total es:**    
>O(n⋅maxVal)   

## Complejidad temporal de rebuildSortedList
>def rebuildSortedList(counts: List[Int], acc: List[Int] = List(), num: Int = 0): List[Int] = counts match {    
case Nil => acc **costo constante O(1), ya que es solo una comprobación y un retorno del resultado.**     
case 0 :: tail => rebuildSortedList(tail, acc, num + 1) **La operación acc :+ num tiene un costo de O(k), donde k es la longitud actual de la lista acc. Esto se debe a que :+ (la operación de anexar al final de la lista) necesita recorrer toda la lista acc para agregar el nuevo elemento al final.**         
case head :: tail => rebuildSortedList((head - 1) :: tail, acc :+ num, num)     
}    

En cada llamada recursiva, el costo de agregar un elemento a acc con acc :+ num es O(k), donde k es la longitud actual de acc. En el peor de los casos, k varía de 0 a n.
Esto implica que la complejidad temporal total es:     

>O(1+2+3+… +n) = O(n(n+1)/2) = O(n^2)   

Complejidad temporal total:    
>O(n^2)


## Complejidad temporal total
1.	Función initList: O(maxVal)
2.	Función countOccurrences: O(n⋅maxVal)
3.	Función rebuildSortedList: O(n^2)    
      **Complejidad temporal total:**  
      *El costo dominante sigue siendo el máximo entre O(n⋅maxVal) y O(n^2):*
      - Si n>maxVal complejidad total es O(n^2).
      - Si maxVal>n, la complejidad total es O(n⋅maxVal)
      - La complejidad temporal combinada de las tres funciones es:
> O(max (n^2,n⋅maxVal))
