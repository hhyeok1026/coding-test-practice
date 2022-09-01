package officialhome.basics.kotlinByExample.h_ProductivityBoosters

// Destructuring Declarations 구조분해 선언

// -> 변수 선언할때 여러개 묶어서 하는거를 말하는듯.

// 인스턴스에서 멤버의 접근만 필요할때, 매우 편하게 쓸 수게 해준다.
// 특별한 이름없이 인스턴스를 정의하고, 그러므로 코드라인이 줄어든다.


fun main() {

    val (x, y, z) = arrayOf(5, 10, 15)

    val map = mapOf("Alice" to 21, "Bob" to 25)

    for ((name, age) in map) {
        println("$name is $age years old")
    }

    val (min, max) = findMinMax(listOf(100, 90, 50, 98, 76, 83)) // 함수의 리턴값에 대해서도 받을 수 있다.

    println(x)
    println(y)
    println(z)
    println(min)
    println(max)

    //=========================================

    val user = getUser()

    val (username, email) = user
    println(username == user.component1())

    val (_, emailAddress) = getUser()

    println(username)
    println(email)
    println(emailAddress)

    //=========================================

    val (num, name) = Pair(1, "one")
    println("num = $num, name = ${name}")
}

//===================================================================

fun findMinMax(list: List<Int>): Pair<Int, Int> {
    // do the math
    return Pair(50, 100)
}

//===================================================================

data class User(val username: String, val email: String)

fun getUser() = User("Mary", "mary@somewhere.com")


//===================================================================

class Pair<K, V>(val first: K, val second: V) {
    operator fun component1(): K {
        return first
    }

    operator fun component2(): V {
        return second
    }
}