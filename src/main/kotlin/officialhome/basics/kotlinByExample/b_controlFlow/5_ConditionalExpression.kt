package officialhome.basics.kotlinByExample.b_controlFlow

// Conditional Expression


// 코틀린에는 ternary operator(삼항연산자)가 없어서,
// 아래와 같이 그 기능을 써야함.

fun main() {
    fun max(a: Int, b: Int) = if (a > b) a else b

    println(max(99, -42))
}