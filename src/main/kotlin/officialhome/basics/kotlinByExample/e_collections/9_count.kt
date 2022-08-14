package officialhome.basics.kotlinByExample.e_collections

// count
// -> 술어의 조건이 매칭되는, 컬렉션안의 요소의 수를 리턴한다.

fun main() {
    val numbers = listOf(1, -2, 3, -4, 5, -6)

    val totalCount = numbers.count()
    val evenCount = numbers.count() { it % 2 == 0 }

    println(numbers)
    println(totalCount)
    println(evenCount)
}
