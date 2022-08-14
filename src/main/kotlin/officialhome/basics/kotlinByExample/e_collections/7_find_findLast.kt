package officialhome.basics.kotlinByExample.e_collections

// find, findLast
// -> 주어진 식에서, 각각 첫번째와 마지막 요소를 찾는다. 만약 요소가 없다면 null값을 반환함.


fun main() {

    val words = listOf("Lets", "find", "something", "in", "collection", "somehow")

    val first = words.find { it.startsWith("some") }
    val last = words.findLast { it.startsWith("some") }

    val nothing = words.find { it.contains("nothing") }

    println(first)
    println(last)
    println(nothing)
}
