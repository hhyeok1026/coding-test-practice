package officialhome.basics.kotlinByExample.a_introduction

fun main(){

    //==========================================
    // Default Parameter Values and Named Arguments

    /*fun printMessage(message: String): Unit {
        println(message)
    }

    fun printMessageWithPrefix(message: String, prefix: String = "Info") {
        println("[$prefix] $message")
    }

    fun sum(x: Int, y: Int): Int {
        return x + y
    }

    fun multiply(x: Int, y: Int) = x * y

    printMessage("Hello")
    printMessageWithPrefix("Hello", "Log")
    printMessageWithPrefix("Hello")
    printMessageWithPrefix(prefix = "Log", message = "Hello")
    println(sum(1, 2))
    println(multiply(2, 4))*/



    //==========================================
    // Infix Functions (Infix : 삽입사, 단어 중간에 들어가서 의미를 바꾸는 접사)
    // infix 함수를 이용하여, 이항 연산자역할을 하는 함수를 만들 수 있다.
    class Person(val name: String) {
        val likedPeople = mutableListOf<Person>()
        infix fun likes(other: Person) { likedPeople.add(other) }
    }

    infix fun Int.times1(str: String) = str.repeat(this)
    println(2 times1 "Bye ")

    val pair = "Ferrari" to "Katrina"
    println(pair)

    infix fun String.onto(other: String) = Pair(this, other)
    val myPair = "McLaren" onto "Lucas"
    println(myPair)

    val sophia = Person("Sophia")
    val claudia = Person("Claudia")
    sophia likes claudia



    //===========================================
    //Operator Functions -> 어떤 특정 이름의 함수들은 연산자로 업그레이드 할 수 있다.

    operator fun Int.times(str: String) = str.repeat(this)
    println(2 * "Bye ")

    operator fun String.get(range: IntRange) = substring(range)
    val str = "Always forgive your enemies; nothing annoys them so much."
    println(str[0..14])



    //===========================================
    //Functions with vararg Parameters, 콤마를 이용해서 많은 값을 넣을 수 있는 인수를 만든다.

    fun printAll(vararg messages: String) {
        for(m in messages) print("$m ")
    }
    printAll("Hello", "Hallo", "Salut", "Hola", "你好")


    fun printAllWithPrefix(vararg messages: String, prefix: String) {
        for (m in messages) println(prefix + m)
    }
    printAllWithPrefix(
        "Hello", "Hallo", "Salut", "Hola",
        prefix = "Greeting: "
    )

    fun log(vararg entries: String) {
        printAll(*entries) //런타임에서 varagrg는 배열인데, 이거를 또 인자로 넘기려면 특수 스프레드 오퍼레이트인 *를 사용해야함
    }
}