package officialhome.basics.kotlinByExample.e_collections

// flatMap
// -> 컬렉션의 각각의 요소를 반복가능한 오브족트로 변환하고, 변환결과의 한 리스트로 만든다. -> 리스트가 이중으로 들어간거를 한 리스트로 만든다는거네.
// 변환은 사용자 정의이이다.

fun main() {

    val fruitsBag = listOf("apple", "orage", "banana", "grapes")
    val clothesBag = listOf("shirts", "pants", "jeans")
    val cart = listOf(fruitsBag, clothesBag)

    val mapBag = cart.map { it } // -> 그냥 맵에 조건이 없으면 변하는게 없지만,
    val flatMapBag = cart.flatMap { it } // -> flatmap은 리스트를 1개로 바꿔버린다.

    println(fruitsBag)
    println(clothesBag)
    println(cart)

    println(mapBag)
    println(flatMapBag)
}