# Complejidad temporal de CountingSort
### Paso 1: 
- Inicializar un array de conteo de tamaño k. Esto toma O(k) tiempo.
### Paso 2: 
- Recorrer la lista de entrada (de tamaño n) y contar las frecuencias de los elementos, lo que toma O(n).
### Paso 3: 
- Transformar el array de conteo en uno acumulado, lo cual toma O(k) tiempo.
### Paso 4: 
- Colocar los elementos en su posición correcta usando el array acumulado, lo que toma O(n) tiempo.
Se deben de sumar todos estos pasos:   
>O(k) + O(n) + O(k) + O(n)   

Lo que nos da como resultado.    
>O(n + k)   

Por lo que O(n + k) es la complejidad temporal del algoritmo CountingSort.
