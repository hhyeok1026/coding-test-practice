package officialhome.basics.kotlinByExample.i_KotlinJs

// Dynamic 은 kotlin/js의 특수한 유형이다.
// 기본적으로 코틀린 타입 체크를 끈다.
// 자바스크립트의 시스템처럼, 타입없고 루즈한 환경에 상호운용하기 위해서 필요하다.

//-> 빌드하는거를 js로 뭐 바꿔서 해야하는듯함..
//-> dynamic으로 하면 숫자로 되거나 문자나 문자열 등으로 적절하게 캐스팅 되면서 사용할 수 있는듯.

/*
fun main() {
    val a: dynamic = "abc"
    val b: String = a

    fun firstChar(s: String) = s[0]

    println("${firstChar(a)} == ${firstChar(b)}")

    println("${a.charCodeAt(0, "dummy argument")} == ${b[0].toInt()}")

    println(a.charAt(1).repeat(3))

    fun plus(v: dynamic) = v + 2

    println("2 + 2 = ${plus(2)}")
    println("'2' + 2 = ${plus("2")}")
}
*/
