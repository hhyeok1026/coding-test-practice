package officialhome.basics.kotlinByExample.f_scopeFunctions

// apply
// -> object에 대한 코드블록을 실행하고, object자체를 반환한다.
// 블록 내부에서 object는 this에 의해 참조 된다.
// 이 함수는 object초기화에 유용하다.

data class Person(var name: String, var age: Int, var about: String) {
    constructor() : this("", 0, "")
}

fun main() {
    val jake = Person()
    val stringDescription = jake.apply {
        name = "Jake"
        age = 30
        about = "Android developer"
    }.toString()
    println(stringDescription)
}

