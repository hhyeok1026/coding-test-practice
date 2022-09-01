package officialhome.basics.kotlinByExample.e_Collections

import kotlin.math.abs

// sorted
// -> 오름차순으로 정렬하여 반환.

// sortedBy
// -> 특정한 셀렉터 함수에 의해 정렬하여 반환.


fun main() {
    val shuffled = listOf(5, 4, 2, 1, 3, -10)
    val natural = shuffled.sorted()
    val inverted = shuffled.sortedBy { -it }
    val descending = shuffled.sortedDescending()
    val descendingBy = shuffled.sortedByDescending { abs(it) }
    val sortedByAbs = shuffled.sortedBy { abs(it) }

    println(shuffled)
    println(natural)
    println(inverted)
    println(descending)
    println(descendingBy)
    println(sortedByAbs)
}