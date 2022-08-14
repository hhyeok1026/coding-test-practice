package officialhome.basics.kotlinByExample.h_productivityBoosters

// String Template

// -> strings 내부에 $기호와 중괄호를 사용하여, 변수와 표현식을 넣을 수 있게한다.
// 문자열이 요청되면, 변수와 표현식이 실제값으로 들어가게 된다.

fun main() {
    val greeting = "Kotliner"

    println("Hello $greeting")
    println("Hello ${greeting.toUpperCase()}")
}