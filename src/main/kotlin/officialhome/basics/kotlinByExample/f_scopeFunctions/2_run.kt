package officialhome.basics.kotlinByExample.f_scopeFunctions

// run
/*
    기본적으로 let과 같다.
    코드블럭을 실행하고, 결과를 리턴한다.
    다른점은 run내부에서 object는 this를 통해접근한다.
    이것은 너가 object의 메소드를 호출을 원할때, argment를 넣는것보다 유용하다.
*/

fun main() {
    fun getNullableLength(ns: String?): Int? {

        println("for \"$ns\":")

        return ns?.run {
            println("\tis empty? " + isEmpty())
            println("\tlength = $length")
            length
            this.length // this가 생략가능한듯.
        }
    }

    getNullableLength(null)
    getNullableLength("")
    getNullableLength("some string with kotlin")

    println("=================================")

    println(getNullableLength(null))
    println(getNullableLength(""))
    println(getNullableLength("some string with kotlin"))
}
