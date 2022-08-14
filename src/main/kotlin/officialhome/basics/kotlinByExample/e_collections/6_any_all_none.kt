package officialhome.basics.kotlinByExample.e_collections


// any, all, none
// 컬렉션에서 요소의 존재 유무를 확인하는 함수들.

fun main() {

    // any -> 주어진 조건에 '적어도 하나의' 요소가 있다면 true를 반환한다.
    val numbers = listOf(1, -2, 3, -4, 5, -6)

    val anyNegative = numbers.any { it < 0 }

    val anyGT6 = numbers.any { it > 6 }

    println(numbers)
    println(anyNegative)
    println(anyGT6)



    // all -> 모든 요소가 조건에 부합하면 true를 반환한다.
    val allEven = numbers.all { it % 2 == 0 }
    val allLess6 = numbers.all { it < 6 }

    println(allEven)
    println(allLess6)



    // none -> 요소가 없다면, true를 반환.
    val allEven2 = numbers.none { it % 2 == 1 }
    val allLess6_2 = numbers.none { it > 6 }

    println(allEven2)
    println(allLess6_2)
}