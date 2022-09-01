package officialhome.basics.kotlinByExample.g_Delegation

import java.util.*

// 예제
// https://play.kotlinlang.org/byExample/07_Delegation/01_delegationPattern

// 개념
// https://kotlinlang.org/docs/delegation.html


// DelegationPattern
// 코틀린은 어떠한 보일러플레이트(상용구) 코드 없이, 네이티브 수준의 delegate pattern을 쉽게 구현할 수 있게 한다.
// 직접 구현을 하지 않고, by 키워드 이용하여 다른 클래스에 위임하여, 필요한 기능을 사용하게 된다는것 같음.

interface SoundBehavior {
    fun makeSound()
}

class ScreamBehavior(val n:String): SoundBehavior {
    override fun makeSound() = println("${n.uppercase(Locale.getDefault())} !!!")
}

class RockAndRollBehavior(val n:String): SoundBehavior {
    override fun makeSound() = println("I'm The King of Rock 'N' Roll: $n")
}

// Tom Araya is the "singer" of Slayer
class TomAraya(n:String): SoundBehavior by ScreamBehavior(n)

// You should know ;)
class ElvisPresley(n:String): SoundBehavior by RockAndRollBehavior(n)

fun main() {
    val tomAraya = TomAraya("Thrash Metal")
    tomAraya.makeSound()
    val elvisPresley = ElvisPresley("Dancin' to the Jailhouse Rock")
    elvisPresley.makeSound()
}
