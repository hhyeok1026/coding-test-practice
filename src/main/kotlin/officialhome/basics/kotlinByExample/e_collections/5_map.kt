package officialhome.basics.kotlinByExample.e_collections

// map

// map 확장 함수는 컬렉션안의 모든 요소들을 변환시킬 수 있다.



fun main() {

    val numbers = listOf(1, -2, 3, -4, 5, -6)

    val doubled = numbers.map { x -> x * 2 }

    val tripled = numbers.map { it * 3 }

    println(numbers)
    println(doubled)
    println(tripled)
}