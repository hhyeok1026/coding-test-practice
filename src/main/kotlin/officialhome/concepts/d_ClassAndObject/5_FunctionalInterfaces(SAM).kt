package officialhome.concepts.d_ClassAndObject

/*
    Functional (SAM) interfaces


    abstract method가 하나 있는 인터페이스는,
    funtional interface라고 불린다.
    또는
    single abstract Method (SAM) interface.

    그 함수적 인터페이스는 여러 non-abstract members를 가질 수 있지만,
    하나의 abstract member만 가질 수 있다.

    funtional interface를 선언하려면,
    fun modifier를 써라.

    fun interface KRunnable {
        fun invoke()
    }



    // ====================
    // SAM conversions
    // ====================

    functional interfaces에 대해서,
    너는 SAM conversion을 사용할 수 있다, 코드를 더 간결하고 읽기 쉽게한다. lambda expression을 이용하여.

    함수적 인터페이스를 수동으로 구현하는 대신에,
    너는 람다식을 쓸 수 있다.
    SAM conversion과 함께,
    코틀린은 어떤 람다 표현식도 변경할 수 있다.
    시그네쳐가, SAM의 시그니처로 맞는 람다식에 대해서,
    그리고 그것은 동적으로 초기화 된다. interface 구현이.

    예를 들어서, 다음 함수적 인터페이스를 고려하라.

    fun interface InpPredicate {
         fun accept(i: Int): Boolean
    }

    만약 너가 SAM conversion을 사용하지 않는다면, 너는 이렇게 작성하는게 필요하다.

    val isEven = object : IntPredicate {
        override fun accept(i: Int): Boolean {
            return i % 2 == 0
        }
    }


    SAM conversion을 활용한다면,
    이 코드를 대신하여 사용 할 수 있다.

    // Creating an instance using lambda
    val isEven = IntPredicate { it % 2 == 0 }


    짧은 람다식은 모든 필요없는 코드를 대체한다.

    너는 또한 자바 인터페이스에 대한 SAM conversions문서를 볼 수 있다.
    https://kotlinlang.org/docs/java-interop.html#sam-conversions




    // ====================================================
    // Migration from an interface with constructor funtion
    // to a funtional interface
    // ====================================================

    코틀린 1.6.20부터, functional interface constructors에 대해 callabe reference를 지원한다,
    더한다. 소스 호환 방식으로 마이그레이션 하기 위해. constructor function의 interface에서 funtional interfcce로 마이그레이션 하기위해
    다음 코드를 고려해라.

    interface Printer {
        fun print()
    }

    fun Printer(block: () -> Unit): Printer = object : Printer { override fun print() = block() }

    functional interface constructors에 대한 callable reference가 가능해지면,
    이 코드는 functional interface 선언으로 대체 될 수 있다.

    fun interface Printer {
        fun print()
    }

    constructors는 암시적으로 생성되고, 그리고 어떤 코드는 ::Printer함수 참조를 사용하으로 컴파일 될 것이다.
    documentsStorage.addPrinter(::Printer)

    바이너리 호환성을 유지하기 하라, 레거시 함수 Printer를
    @Deprecated을 DeprecationLevel.HIDDEN:과 함께 마킹해서

    @Deprecated(message = "Your message about the deprecation", level = DeprecationLevel.HIDDEN)
    fun Printer(...) { ... }




    // ======================================
    // Funtional interfaces vs. type aliases
    // ======================================

    funtional type에 대해서 type alias를 사용할 수 있다.


    typealias IntPredicate = (i: Int) -> Boolean

    val is Even: IntPredicate = { it % 2 == 0 }

    fun main() {
        println("Is 7 even? - ${isEven(7)}")
    }


    하지만, funtional interface와 type aliaes의 다른 점이 있는데,
    type alias는 존재하는 타입의 이름이다. 그들은 새로운 타임을 만들지 않는다.

    반면에, funtional interface는 만든다.

    type alias를 적용할 수 없는 특정 funtional interface에 특정한 extension을 제공할 수 있다.

    type aliases 오직 하나의 멤버만 가질 수 있다,
    반면에 functional interface는 다양한 non abstract멤버와 하나의 abstract멤버를 갖는다.
    functional interfaces는 또한 구현할 수 있고 다른 인터페이스 확장 수 있다.

    functional interfaces는 더 확장성 있고 더 호환 할 수 있다.
    그러나 그들은 더 비용이 높고 문법적으로, 그리고 런타임 적으로
    그들은 특정한 인터페이스로 변환이 요구되기 때문에..

    너의 코드에서 하나의 사용을 고를때, 다음을 고려하라.

    - 몇몇의 특정한 파라미터와 리턴타입을 함께 있는 funtion이 필요하다면,
    simple functional type 이나, typealias를 정의 해라, 함수타입에 맞는 짧은 이름을 주고
    - 함수보다 더 복잡한것이 필요하다면, 예를들어 non-trivial contracts and opertion 과 같은 함수 서명에서 표현할 수 없는..
    선언해라 분리된 functional interface를..

 */


fun interface IntPredicate {
    fun accept(i: Int): Boolean
}

val isEven = IntPredicate { it % 2 == 0 }

fun main() {
    println("Is 7 even? - ${isEven.accept(7)}")
}


















