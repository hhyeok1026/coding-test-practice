package officialhome.basics.kotlinByExample.d_Functional

// Higher-Order Function :
//  다른 함수를 파라미터로 받아서 함수를 리턴한다.

fun calculate(x: Int, y: Int, operation: (Int, Int) -> Int): Int {
    return operation(x, y)
}

fun sum(x: Int, y: Int) = x + y




// Returning Functions
fun operation(): (Int) -> Int {
    return ::square
}

fun square(x: Int) = x * x


fun main() {
    /*
    val sumResult = calculate(4, 5, ::sum)  // ::는 이름으로 함수를 참조하는 표기이다.
    val mulResult = calculate(4, 5) { a, b -> a * b }
    println("sumResult $sumResult, mulResult $mulResult")
    */

    val func = operation()
    println(func(2))
}