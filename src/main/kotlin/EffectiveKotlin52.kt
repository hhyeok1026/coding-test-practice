
/*
이펙티브 코틀린책 52번 아이템에서,
immutable에서 add는 안되고, plus는 된다.
*/

fun main(args: Array<String>) {
    val immutable = listOf<Int>(1, 2, 3, 4, 5)
    val immutableExtra = listOf(6, 7, 8, 9, 10)

    val newImmutable = immutable + immutableExtra
    val newImmutableMap = immutable.map { it * 2 }

    println(immutable)
    println(immutableExtra)
    println(newImmutable)
    println(newImmutableMap)
}