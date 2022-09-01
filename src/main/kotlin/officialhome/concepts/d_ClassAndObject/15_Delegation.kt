package officialhome.concepts.d_ClassAndObject

/*
    // Delegation

    Delegation pattern은 구현 상속에 좋은 대안으로 제공된다.
    그리고 코틀린은 보일러 코드없이 네이티브로 지원한다.

    Derived 클래스는 Base인터페이스를 구현할 수 있다,
    그것의 공용 멤버의 모든것을 구체화된 객체에  위임함으로써

    interface Base {
        fun print()
    }

    class BaseImpl(val x: Int) : Base {
        override fun print() { print(x) }
    }

    class Derived(b: Base) : Base by b

    fun main() {
        val b = BaseImpl(10)
        Derived(b).print()
    }


    Derived에 대한 수퍼 타입 리스트 안의 by절은 지시한다,
    b가 Derived 의 객체안에 내부적으르 저장될 것이라고,
    그리고 컴파일러는 b로 전달하는 Base의 모든 메서드를 생성할것이다.




    // =======================================
    // Overriding a member of an interface
    // implemented by delegation
    // =======================================

    Overrides는 너의 기대처럼 작동한다.
    컴파일러는 너의 override 구현을 사용 할 것이다, 위임한 객체의 그것들을 대신하여
    만약 너가 override fun printMessage() { print("abc") } 를 Derived에서 추가를 한다면,
    그 프로그램은 abc를 출력할 것이다, printMessase가 호출될때 10대신에.

    interface Base {
        fun printMessage()
        fun printMessageLine()
    }

    class BaseImpl(val x: Int) : Base {
        override fun printMessage() { print(x) }
        override fun printMessageLine() { println(x) }
    }

    class Derived(b: Base) : Base by b {
        override fun printMessage() { print("abc") }
    }

    fun main() {
        val b = BaseImpl(10)
        Derived(b).printMesaage()
        Derived(b).printMessageLine()
    }


    그러나 주의해라, 이 방법으로 오버리든된 멤버는 delegate object의 멤버로 호출 되지 않는다.
    그리고 그것은 인터페이스의 멤버의 그것의 구현만 오직 접근할 수 있다.

    interface Base {
        val message: String
        fun print()
    }

    class BaseImpl(val x: Int) : Base {
        override val message = "BaseImpl: x = $x"
        override fun print() { println(message) }
    }

    class Derived(b: Base) : Base by b {
        // This property is not accessed from b's implementation of 'print'
        override val message = "Message of Derived"
    }

    fun main() {
        val b = BaseImpl(10)
        val derived = Derived(b)
        derived.print()
        println(derived.message)
    }

*/


interface Base1 {
    val message: String
    fun print()
}

class BaseImpl(val x: Int) : Base1 {
    override val message = "BaseImpl: x = $x"
    override fun print() { println(message) }
}

class Derived1(b: Base1) : Base1 by b {
    // This property is not accessed from b's implementation of 'print'
    override val message = "Message of Derived"
}

fun main() {
    val b = BaseImpl(10)
    val derived = Derived1(b)
    b.print()
    derived.print()
    println(derived.message)
}