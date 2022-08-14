package officialhome.basics.kotlinByExample.f_scopeFunctions


// also
// apply와 유사하다.
// 주어진 블록을 실행하고, 호출된 object를 리턴한다.
// 내부블록에서 object는 it으로 참조된다, 그래서 인수로 그것을 넣기 쉽다.
// 로깅과 같은 부가적인 액션을 추가하는데 유용하다


data class Person1(var name: String, var age: Int, var about: String) {
    constructor() : this("", 0, "")
}

fun writeCreationLog(p: Person1) {
    println("A new person ${p.name} was created.")
}

fun main() {

    val jake = Person1("Jake", 30, "Android developer")
        .also {
            writeCreationLog(it)
        }

}