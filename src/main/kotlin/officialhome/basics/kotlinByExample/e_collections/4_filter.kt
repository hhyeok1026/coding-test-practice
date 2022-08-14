package officialhome.basics.kotlinByExample.e_collections

// filter
// 이건 함수고(뒤에 람다를 붙여서 조건을 만들어준다.),
// 컬렉션에서 필터를 할 수 있게한다.

fun main() {
    val numbers = listOf(1, -2, 3, -4, 5, -6)
    val positives = numbers.filter { x -> x > 0 } //TODO inline함수는 뭘까?
    val negatives = numbers.filter { it < 0 }

    println(numbers)
    println(positives)
    println(negatives)
}

