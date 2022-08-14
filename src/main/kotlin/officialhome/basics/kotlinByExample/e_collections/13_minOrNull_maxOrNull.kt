package officialhome.basics.kotlinByExample.e_collections

// minOrNull, maxOrNull
// -> 가장 작거나 큰 값을 찾아내는 함수.

fun main() {
    val numbers = listOf(1, 2, 3)
    val empty = emptyList<Int>()
    val only = listOf(3)

    println("Numbers: $numbers, min = ${numbers.minOrNull()} max = ${numbers.maxOrNull()}")
    println("Empty: $empty, min = ${empty.minOrNull()} max = ${empty.maxOrNull()}")
    println("Only: $only, min = ${only.minOrNull()} max = ${only.maxOrNull()}")
}