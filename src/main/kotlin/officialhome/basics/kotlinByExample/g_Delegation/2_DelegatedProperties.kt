package officialhome.basics.kotlinByExample.g_Delegation

import kotlin.reflect.KProperty

// Delegated Properties

// 코틀린은 어떤 object에 set과 get메서드 호출을 위임 할 수 있도록, delegate Properties의 매커니즘을 제공한다.
// 클래스안의 delegate object는 getValue메서드를 가져야한다.
// 변하는 properties에 대하여 너는 또한 setValue 메서드가 필요하다.

class Example {
    // p 가 Delegate Properties이다, by뒤에 오는게 대리자 object.
    var p: String by Delegate()

    override fun toString() = "Example Class"
}

class Delegate() {
    operator fun getValue(thisRef: Any?, prop: KProperty<*>): String { // 메서드 선언은 항상 이러한 형식이라는듯.
        return "$thisRef, thank you for delegating '${prop.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, prop: KProperty<*>, value: String) { //delegate propertiy가 var일 경우. setValue도 구현해야함.
        println("$value has been assigned to ${prop.name} in $thisRef")
    }
}

fun main() {

    // Delegated Properties

    val e = Example()
    println()

    //===========================

    // Standard Delegates

    val sample = LazySample()
    println("lazyStr = ${sample.lazyStr}")
    println(" = ${sample.lazyStr}") // 윗줄에서 값을 저장해둔거를 바로 가져다씀.

    //===========================

    //Storing Properties in a Map
    val user = User(mapOf(
        "name" to "John Doe",
        "age" to 25,
    ))

    println("name = ${user.name}, age = ${user.age}")
}


// Standard Delegates

// 코틀린 표준 라이브러리는 유용한 델리게이트들을 갖고 있다. lazy, obserble같은.
//  lazy의 사용법을 보라.

// blockingLazy() 스레드 안정성을 위해서는 이거를 써주면 됨.

class LazySample {
    init {
        println("created!")
    }

    val lazyStr: String by lazy {
        println("computed!")
        "my lazy"
    }
}



//Storing Properties in a Map -> 맵에 속성 저장.

// property delegation은 map으로 properties를 저장하기 위해서 사용될 수 있다.?
// (-> map을 이용해서 변수들을 저장한다는 뜻인듯? 굳이 이렇게 구현하는게 더 편할까.? 파싱할때도 map이 있어야 쓸만한거 아닌가 싶은데)

// 이것은 JSON을 파싱하거나, 동적인 작업을 수행할 때, 유용하다.

// 변경가능한 속성도 map에 위임할 수 있다. 이경우 map은 수정 될 수도 있음. -> 이때 map(read-only)대신에 MutableMap을 써야함.

class User(val map: Map<String, Any?>) {
    val name: String by map
    val age: Int by map
}

