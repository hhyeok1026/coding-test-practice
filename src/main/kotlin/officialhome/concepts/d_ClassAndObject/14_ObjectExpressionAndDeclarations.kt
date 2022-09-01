package officialhome.concepts.d_ClassAndObject

/*
    // Object expreesions and declarations
    객체 표현식과 선언

    때때로 너는 필요하다 객체를 만드는것이, 몇몇 클래스의 약간의 수정이 있는
    그것에 대한 새로운 서브클래스의 명백한 선언 없이.
    코틀린은 object expression과 object declarations로 다룰 수 있다.



    // ======================
    // Object expressions
    // ======================

    Object expression은 익명클래스의 객체를 만든다.
    이 클래스는 class선언으로 명시적으로 선언된게 아니다.
    이 같은 클래스는 한번 사용 할 때 유용하다.
    너는 처음부터, 기존 클래스로 부터 상속, 또는 인터페이스의 구현으로 그것들을 정의 할 수 있다
    익명클래스의 인스턴스 또한 익명 객체라 불린다, 그들은 표현식으로 정의 되기 때문에, 이름이 아니라


    // Creating anonymous object from scratch
    Object expression은 object 키워드로 시작한다.

    만약 너가 중요하지 않은 상위유형을 가지지 않는 object가 필요하다면,
    그것을 object이후에 중과호로 그것의 멤버들을 써라.

    val helloWorld = object {
        val hello = "Hello"
        val world = "World"

        // object expressions extend Any, so 'override' is required on 'toString()'
        override fun toString() = "$hello $world"
    }


    // Inheriting anonymous objects from supertypes
    어떤 타입으로부터 상속하는 익명 클래스의 객체를 만들기 위해서,
    object키워드 뒤에 콜론으로 이 타입을 구체화해라.
    그런 다음 이 클래스에서 상속하는것 처럼 이 클래스의 멤버를 구현하거나 정의합니다.

    window.addMouseListener(object : MouseAdapter() {
        override fun mouseClicked(e: MouseEvent) { ... }
        override fun mouseEntered(e: MouseEvent) { ... }
    })

    만약 상위유형에 생성자가 있으면, 그것에 생성자 파라미터에 적절하게 넣어라.
    다양한 상위유형은 콜론이후에, 콤바 분리하여 구체화 될 수 있다.

    open class A(x: Int) {
        public open val y: Int = x
    }

    interface B { ... }

    val ab: A = object : A(1), B {
        override val y = 15
    }



    // Using anonymous object as return and value types
    로컬이나 private의 타입으로 익명 객체가 사용 될때, 그러나 인라인 선언(함수나 프로퍼티)이 아니고.
    모든 그것의 멤버는 이 함수나 프로퍼티를 통해서 접근이 가능하다.

    class C {
        private fun getObject() = object {
            val x: String = "x"
        }

        fun printX() {
            println(getObject().x)
        }
    }


    만약 이 함수나 프로퍼티가 public 이나 private inline이면, 그것의 실제타입은
    - Any 만약 익명 객체가 수퍼타입을 가지지 않는다면.
    - 익명객체의 선언된 수퍼타입, 만약 정확히 하나의 타입이 있다면
    - 명시적으로 선언된 타입, 만약 수퍼타입이 하나이상 선언 되었다면.

    (이게 중요하네!)
    이 모든 케이스에서, 익명 객체안의 더해진 멤버는 접근할 수 없다.
    overrideen 멤버는 접근 할 수 있다, 만약 그들이 함수나 프로퍼티의 실제 타입안에 선언되었다면.

    interface A {
        fun funFromA() {}
    }

    interface B

    class C {
        // The return type is Any. x is not accessible
        fun getObject() = object {
            val x: String = "x"
        }

        // The return type is A; x is not accessible
        fun getObjectA() = object: A {
            override fun funFromA() { }
            val x: String = "x"
        }

        // The return type is B; funFromA() and x are not accessible
        fun getObjectB(): B = object: A, B { // explicit return type is required
            override fun funFromA() {}
            val x: String = "x"
        }
    }



    // Accessing variable from anonymous objects
    객체표현식 안의 그 코드는 둘러쌓인 스코프로의 변수를 접근 할 수 있다.

    fun countClicks(window: JComponent) {
        var clickCount = 0
        var enterCount = 0

        window.addMouseLintener(object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent0 {
                clickCount++
            }

            override fun mouseEntered(e: MouseEvent) {
                enterCount++
            }
        })
    }



    // =====================
    // Object declarations
    // =====================

    싱글톤 패턴은 몇 몇 경우에 유용하게 사용 될 수 있다,
    그리고 코틀린은 싱글톤 선언하는것을 쉽게 만든다.

    object DataProviderManager {
        fun registerDataProvider(provider: DataProvider) {
            // ...
        }

        val allDataProviders: Collection<DataProvider>
            get() = // ...
    }

    이것은 object declaration이라 불린다. 그리고 그것은 늘 object키워드가 따른다.
    단지 변수 선언 같이, object declaration은 표현식이 아니며.
    그리고 그것은 대입문의 오른쪽에 사용 할 수 없다.

    object declaration의 초기화는 스레드세이프하고, 처음 액세스 할 때 수행된다.


    object를 나타내기 위해서, 그것의 이름을 써라.

    DataProviderManager.registerDataProvider(...)

    이러한 객체는 수퍼타입을 가질 수 있다.

    object DefaultListener : MouseAdapter() {
        override fun mouseClicked(e: MouseEvent) { ... }
        override fun mouseEntered(e: MouseEvent) { ... }
    }

    Object선언은 로컬이 될 수 없다. (그것은 그들은 함수내부에 직접적으로 nested될 수 없다.)
    그러나.
    그들은 nested 될 수 있다. 다른 객체 선언이나 non-inner classes에.



    // Compainon objects
    클래스 내부의 object declaration은 compatinon키워드로 마크 될 수 있다.

    class MyClass {
        companion object Factory {
            fun create(): MyClass = MyClass()
        }
    }

    companion object의 멤버는 간단하게 불려질 수 있다, 한정자로서 그 클래스의 이름을 사용함으로

    val instance = MyClass.create()

    컴패니언 객체의 이름은 생략 될 수 있다, 그같은 경우는 그 이름이 Compainon이 사용 되어서.

    class MyClass {
        companion object {}
    }

    val x = MyClass.Companion

    클래스 멤버는 컴패니언 객체의 private멤버에 접근 할 수 있다.

    그것 스스로 사용된 클래스의 이름은 (다른 이름에 한정자로서가 아니라)
    수행한다. 그 클래스(이름이 있던 없던)의 컴패니언 객체에 참조로서

    class MyClass1 {
        companion object Named { }
    }

    val x = MyClass1

    class MyClass2 {
        companion object { }
    }

    val y = MyClass2


    컴패니언 객체의 멤버가 비록 다른 언어에서 static멤버로 보일지라도,
    런타임에서 그것은 실제 객체의 멤버로 인스턴스 되고, 예를들어, 그리고 인터페이스를 구현 할 수 있다.

    interface Factory<T> {
        fun create(): T
    }

    class MyClass {
        compainon object : Factory<MyClass> {
            override fun create(): MyClass = MyClass()
        }
    }

    val f: Factory<MyClass> = MyClass

    그러나, JVM에서 너는 진짜 static method로서,
    그리고 만약 너가 @JvmStatic어노테이션을 사용한다면,
    생성된 컴패니언 객체의 멤버를 가질 수 있다.

    See the Java interoperability section for more detail.
    (https://kotlinlang.org/docs/java-to-kotlin-interop.html#static-fields)



    // ===============================================================
    // Semantic difference between obejct expression and declaractions
    // ===============================================================

    하나의 중요한 차이가 있음.

    - Object expressions은 즉각적으로 실행된다 (초기화되는거도), 그들이 사용 될 때
    - Object delcarations은 lazily하게 초기화 된다, 처음에 엑세스 할 때
    - companion obejct는 초기화 된다, 자바 static 초기화 프로그램의 의미체계와 일치하는 클래스가 로드(확인) 될 때,





 */









interface A {
    fun funFromA() {}
}

interface B

class C {
    // The return type is Any. x is not accessible
    fun getObject() = object {
        val x: String = "x"
    }

    // The return type is A; x is not accessible
    fun getObjectA() = object: A {
        override fun funFromA() { }
        val x: String = "x"
    }

    // The return type is B; funFromA() and x are not accessible
    fun getObjectB(): B = object: A, B { // explicit return type is required
        override fun funFromA() {}
        val x: String = "x"
    }
}

/*open class A(x: Int) {
    public open val y: Int = x
}

interface B {  }

val ab: A = object : A(1), B {
    override val y = 15
    private val x: String = "x"
}*/


fun main() {

}


















