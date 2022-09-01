package officialhome.basics.kotlinByExample.e_Collections

// map Element Access
// -> []에 key를 넣어 value에 접근할 수 있다. 값이 없으면 null값이 나옴.
// -> getValue에 key를 넣으면, 값이나오고, 해당 값이 없으면 예외를 던진다.
// withDefault로 map을 만들어서 getValue를 쓰면, 값이 없으면 예외 대신에 기본값을 리턴한다.

fun main() {
    val map = mapOf("key" to 42)

    val value1 = map["key"]
    val value2 = map["key2"]

    val value3: Int = map.getValue("key")

    val mapWithDefault = map.withDefault { k -> k.length }

    val value4 = mapWithDefault.getValue("key2")

    try {
        map.getValue("anotherKey")
    } catch (e: NoSuchElementException) {
        println("Message: $e")
    }

    println(value1)
    println(value2)
    println(value3)
    println(mapWithDefault)
    println(value4)
}
