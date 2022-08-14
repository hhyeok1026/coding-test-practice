package officialhome.basics.kotlinByExample.e_collections

// zip
// -> 두개의 컬렉션을 하나로 합친다.
// 기본적으로 같은 인덱스끼리 합치고, 어떻게 합칠지 커스텀가능함.

fun main() {
    val A = listOf("a", "b", "c")
    val B = listOf(1, 2, 3, 4)

    val resultPairs = A zip B
    val resultReduce = A.zip(B) { a, b -> "$a$b"}

    println(resultPairs)
    println(resultReduce)
}