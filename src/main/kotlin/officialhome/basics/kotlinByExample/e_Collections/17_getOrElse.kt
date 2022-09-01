package officialhome.basics.kotlinByExample.e_Collections

// getOrElse
// -> 이 함수는 elements에 대한 안전한 접근을 제공한다.
// 인덱스를 가지고, 인덱스 밖의 범위는 기본값을 제공한다.

fun main() {
    val list = listOf(0, 10, 20)
    println(list.getOrElse(1) { 42 })
    println(list.getOrElse(10) { 42 })


    // map에도 사용할 수 있다.
    val map = mutableMapOf<String, Int?>()
    println(map.getOrElse("x") { 1 })

    map["x"] = 3
    println(map.getOrElse("x") { 1 })

    map["x"] = null
    println(map.getOrElse("x") { 1 })
}