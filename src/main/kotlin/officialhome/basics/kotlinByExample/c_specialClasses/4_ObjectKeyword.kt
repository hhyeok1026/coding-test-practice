package officialhome.basics.kotlinByExample.c_specialClasses

import java.util.*


// Object -> 클래스와 유사한듯? class가 아니고, 생성자도 없고 오지 lazy한 인스턴스 하나를 갖는다고 한다.
// object keyword를 단일 구현된 데이터타입, 싱글톤 패턴 만들때 쓴다고 한다.

class LuckDispatcher {
    fun getNumber() {
        var objRandom = Random()
        println(objRandom.nextInt(90))
    }
}

fun main() {
    /*
    val d1 = LuckDispatcher()
    val d2 = LuckDispatcher()

    d1.getNumber()
    d2.getNumber()
    d2.getNumber()
    */

    //rentPrice(10, 2, 1)

    //DoAuth.takeParams("foo", "qwerty")

    BigBen.getBongs(12)
}

fun rentPrice(standardDays: Int, festivityDays: Int, specialDays: Int): Unit {

    // object Expression -> 자바의 익명함수와 비슷하다고 한다.
    val dayRates = object {
        var standard: Int = 30 * standardDays
        var festivity: Int = 50 * festivityDays
        var special: Int = 100 * specialDays
    }

    val total = dayRates.standard + dayRates.festivity + dayRates.special

    print("Total price: $$total")
}


//Object Declaration
//-> 표현식이 아니고, 변수할당을 할 수 없다? 곧바로 멤버에 접근해야한다.?
object DoAuth {
    fun takeParams(username: String, password: String) {
        println("input Auth parameters = $username:$password")
    }
}



//Companion Objects
class BigBen {
    companion object Bonger {
        fun getBongs(nTimes: Int) {
            for (i in 1..nTimes) {
                println("BONG")
            }
        }
    }
}










