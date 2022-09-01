package officialhome.basics.kotlinByExample.e_Collections

fun main() {

    // first, last
    // -> 컬렉션에서 첫번째와 마지막 요소를 찾는다.
    // 찾을게 없으면 NoSuchElementException을 발생시킨다.

    val numbers = listOf(1, -2, 3, -4, 5, -6)

    val first = numbers.first()
    val last = numbers.last()

    val firstEven = numbers.first { it % 2 == 0 }
    val lastOdd = numbers.last { it % 2 != 0 }

    println(first)
    println(last)
    println(firstEven)
    println(lastOdd)

    // firstOrNull, lastOrNull
    // -> first, last와 유사한 기능이지만, 매칭되는게 없을때 null을 반환한다.

    val words = listOf("foo", "bar", "baz", "faz")
    val empty = emptyList<String>()

    val first2 = empty.firstOrNull()
    val last2 = empty.lastOrNull()

    val firstF = words.firstOrNull { it.startsWith('f') }
    val firstZ = words.firstOrNull { it.startsWith('z') }
    val lastF = words.lastOrNull { it.endsWith('f') }
    val lastZ = words.lastOrNull { it.endsWith('z') }

    println(first2)
    println(last2)
    println(firstF)
    println(firstZ)
    println(lastF)
    println(lastZ)
}
