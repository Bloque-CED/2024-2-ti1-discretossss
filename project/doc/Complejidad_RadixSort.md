# Complejidad temporal de RadixSort
Radix Sort es un algoritmo que ordena los números dígito a dígito. Se realiza d veces, donde d es el número de dígitos del número más largo. En cada pasada, se usa un algoritmo de ordenamiento estable como Counting Sort para ordenar los números de acuerdo con el dígito actual.

Para cada uno de los d dígitos Counting Sort se utiliza para ordenar los números por el dígito en cuestión.
El algoritmo realiza d pasadas de Counting Sort, una por cada dígito del número más largo.
Por lo tanto, el costo total será el costo de Counting Sort multiplicado por el número de dígitos d.

Ya que Counting Sort tiene una complejidad de O(n + k) y se ejecuta d veces

Por lo tanto, la complejidad temporal de Radix Sort es:   
>O(d (n + k))

## Complejidad temporal de SortByDigit
>def sortByDigit(xs: List[Int], exp: Int): List[Int] = {    
    if (exp > maxNum) xs **En este caso, la función simplemente devuelve xs (la lista original), y esta operación tiene un costo constante de O(1) ya que sólo implica una comparación y una devolución del resultado.**     
    else {    
     val sortedByCurrentDigit = sortFunc(xs, exp) **Esta funcion es invocada, cuyo costo es O(n) si asumimos que sortFunc tiene una complejidad lineal. Esto es común en algunos algoritmos de ordenamiento como la ordenación por conteo.**    
      sortByDigit(sortedByCurrentDigit, exp * 10) // **Move to the next digit**    
     }   
    }
sortByDigit(xs, 1) // Start sorting by the least significant digit
}

Cada llamada recursiva tiene un costo de O(n) y hay d llamadas recursivas.    
Por lo tanto, la complejidad temporal total es O(d⋅n) donde d es el número de dígitos y n es el tamaño de la lista.     

## Complejidad temporal de countOccurrences
>def countOccurrences(xs: List[Int]): Unit = xs match {    
case Nil => **Esto tiene un costo constante de O(1), ya que es una simple comprobación y retorno.**    
case head :: tail =>    
    val digit = getDigit(head, exp) **Extraer un dígito tiene un costo de O(1)**    
    count(digit) += 1   
    countOccurrences(tail)   
}

Cada llamada recursiva tiene un costo de O(1) (por las operaciones getDigit y el incremento del array count).
Dado que hay n llamadas recursivas, la complejidad temporal total es O(n)

## Complejidad temporal de accumulateCounts
>@tailrec
def accumulateCounts(index: Int): Unit = {
if (index >= 10) () **En este caso, la función simplemente retorna () (valor unitario), lo cual tiene un costo constante de O(1) ya que es una simple comprobación y retorno.**    
else {    
if (index > 0) count(index) += count(index - 1) **Esta es una operación de acceso e incremento en un array, y tiene un costo de O(1)**    
    accumulateCounts(index + 1)    
 }    
}   

Cada llamada recursiva tiene un costo de O(1), y hay un total de 10 llamadas recursivas.
Por lo tanto, la complejidad temporal total es O(10), que se simplifica a O(1) ya que es una constante.
Complejidad temporal total: 
>O(1)

## Complejidad temporal de buildOutput
>@tailrec    
def buildOutput(xs: List[Int], output: Array[Int], i: Int): Array[Int] = xs match {    
case Nil => output **Esta operación tiene un costo constante de O(1), ya que implica una simple comprobación y devolución del resultado.**    
case head :: tail =>        
val digit = getDigit(head, exp) **La obtención de un dígito es una operación de O(1)**
    count(digit) -= 1 Esto también tiene un costo de O(1)    
    output(count(digit)) = head **Esta es una operación de acceso y asignación en un array, que también tiene un costo de O(1)**    
    buildOutput(tail, output, i - 1)    
}    

Cada llamada recursiva tiene un costo de O(1)    
Dado que hay n llamadas recursivas (una por cada elemento de la lista), la complejidad temporal total es O(n).    


**countOccurrences tiene un costo de O(n)**   
**accumulateCounts tiene un costo de O(1)**   
**buildOutput tiene un costo de O(n)**     

La complejidad temporal para countingSortForRadix es entonces:
>O(n)+O(1)+O(n)=O(n)

Como radixSort llama a countingSortForRadix para cada uno de los d dígitos, la complejidad temporal total de radixSort es:
>d⋅O(n)=O(d⋅n)

