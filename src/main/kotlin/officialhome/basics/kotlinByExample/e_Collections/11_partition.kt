package officialhome.basics.kotlinByExample.e_Collections

// partition
// -> 오리지널 컬렉션을 주어진 술어를 이용하여 리스트의 쌍으로 쪼갠다.

fun main() {
    val numbers = listOf(1, -2, 3, -4, 5, -6)

    val evenOdd = numbers.partition { it % 2 == 0 }
    val (positive, negative) = numbers.partition { it > 0 } // Pair destructuring(쌍 분해)이 된다.

    println(evenOdd)
    println(positive)
    println(negative)
}