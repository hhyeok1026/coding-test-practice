package officialhome.concepts.d_ClassAndObject

/*
    Extensions

    코틀린 클래스나 인터페이스에 새로운 함수를 쓸 수 있는 기능을 제공함.
    3th파티 라이브러리꺼도 쓸 수 있다.





    // ======================
    // Extension functions
    // ======================

    receiver type을 접두사로 써야함, 그리고 그게 확장되는 타입임.

    fun MutableList<Int>.swap(index1: Int, index2: Int) {
        val tmp = this[index1] //'this' corresponds to the list
        this[index1] = this[index2]
        this[index2] = tmp
    }

    this 키워드는 reciver object로 상응된다.
    이제, 이러한 함수를 호출 할 수 있다.

    val list - mutableList(1, 2, 3)
    list.swap(0, 2)


    generic type parameter를 사용 할 수 있다. 함수이름의 이전에. reciver type expression을 이용하기 위해
    제너릭 function문서를 보라.

    fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
        val tmp = this[index1] // 'this' corresponds to the list
        this[index1] = this[index2]
        this[index2] = tmp
    }




    // ====================================
    // Extensions are resolved statically - 확장은 정적으로 해석된다.
    // ====================================

    extensions은 실제로 그 클래스를 수정하는게 아니다.
    변수를 그 클래스로 넣을 수 없고
    오직 호출 할 수 있는 새로운 함수를 만들 수 있다.

    확장은 정적으로 전달된다.
    receiver type에 의한 가상이 아니다.

    확장함수는
    런타임에 해당 표현식을 평가한 결과의 유형이 아니라,
    함수가 호출되는 표현식의 유형에 따라 결정된다.

    open class Shape
    class Rectangle: Shape()

    fun Shape.getName() = "Shape"
    fun Rectangle.getName() = "Rectangle"

    fun printClassName(s: Shape) {
        println(s.getName())
    }

    printClassName(Rectangle())

    ->
    Shape이 출력됨.
    확장함수는 파라미터s의 타입에 의존하게 된다. 그리고 그것의 class는 shape이다.

    만약 클래스가 멤버 함수를 가진다면, 그 확장함수가 같은 리시버 타입으로, 같은 이름으로, 같은 어귀먼트로 된다면,
    멤버가 항상 이긴다.

    class Example {
        fun printFunctionType() { println("Class method") }
    }

    fun Example.printFunctionType() { println("Extension function") }

    Example().printFunctionType()

    -> Clas method를 출력한다.


    그러나 오버로드한 메서드에 대해서는 같은이름을 가지지만, 다른 시그니처라서 된다.

    Class Example {
        fun printFunctionType() { println("Class method") }
    }

    fun Example.printFunctionType(i: Int) { println("Extension function #$i") }

    Example().printFunctionType(1)




    // ======================
    // Nullable receiver
    // ======================

    확장 함수는 nullable receiver type으로 정의 될 수 있다.
    이 확장은 만약 object value가 null이면, 호출 될 수 있다.
    그리고 그것은 this == null을 body내부에서 체크한다.

    이렇게하면, toString을 호출할 수 있다. null체크없이,
     함수 내부에서 체크가 이루어졌기에,

     fun Any?.toString(): String {
        if (this == null) return "null"

        // after the null check, 'this' is autocast to a non-null type
        // resolves to the member function of the Any class
     }




    // ======================
    // Extension properties
    // ======================

    확장 함수는 properties는 함수에서 지원하는 것과 같다.

    val <T> List<T>.lastIndex: Int
        get() = size - 1

    실제로 멤버에 값을 넣는게 아니라서, 백킹필드를 가지는것에 대해서 효과가 없다.
    초기화가 확장함수에서 허락되지않는 이유이다.
    그들은 getter와 setter를 통해 오직 정의된다.

    val House.number = 1 // error: initializers are not allowed for extension properties




    // =============================
    // Companion object extensions
    // =============================

    만약 클래스가 컴패니언 객체를 정의 한다면, 너는 또한 컴패니언 객체에 대한 프로퍼티와 확장함수를 정의할 수 있다.
    컴패니언 객체의 일반적인 멤버와 같다. 그들은 한정자처럼 클래스이름으로 불려진다.

    clss MyClass {
        companion objct { }
    }

    fun MyClass.Companion.printCompanion() { println("companion") } // 여기서 중간에 Companion을 써야 컴패니언 객체가 된다는듯?

    fun main() {
        MyClass.printCompanion()
    }




    // ======================
    // Scope of extensions
    // ======================

    대부분의 클래스에서 너는 top level에 정의 한다. 패키지 아래 직접적으로

    package org.example.declarations

    fun List<String>.getLongestString() { ... }


    패키지 선언의 외부에서 선언을 사용하려면. 호출 사이트에서 확장을 import해야한다.

    package org.example.usage

    import org.example.declarations.getLongestString

    fun main() {
        val list = listOf("red", "green", "blue")
        list.getLongestString()
    }




    // ================================
    // Declaring extensions as members
    // ================================

    멤버로서 확장을 선언하기

    다른 클래스 내에서 한 클래스의 확장을 선언할 수 있다.
    이러한 확장내부에는 한정자 없이 멤버에 액세스할 수 있는 개체인 여러 암시적 수신기가 있다.
    확장이 선언된 클래스의 인스턴스를 디스패처 수신기라고 하고,
    확장 메서드의 수시기 유형 인스턴스를 확장 수신기라고 한다???,;;;



     class Host(val hostname: String) {
        fun printHostname() { print(hostname) }
    }

    class Connection(val host: Host, val port: Int) {
        fun printPort() { print(port) }

        fun Host.printConnectionString() {
            printHostname()   // calls Host.printHostname()
            print(":")
            printPort()   // calls Connection.printPort()
        }

        fun connect() {
            ...
            host.printConnectionString()   // calls the extension function
        }
    }

    fun main() {
        Connection(Host("kotl.in"), 443).connect()
        //Host("kotl.in").printConnectionString()  // error, the extension function is unavailable outside Connection
    }


    디스패치 수신자와 확장 수신자 사이에 이름 충돌이 있는 경우 확장 수신자가 우선한다.
    디스패치 수신자의 멤버를 참조하려면 정규화된 this구문을 사용할 수 있다.

    class Connection {
        fun Host.getConnectionString() {
            toString()         // calls Host.toString()
            this@Connection.toString()  // calls Connection.toString()
        }
    }


    멤버로서 선언된 확장은 open으로 선언 될 수 있다. 그리고 서브클래스에서 오바이라든 된다.
    이는 이런한 함수의 디스패치가
    디스패치 수신자 유형에 대해서는 가상이지만,
    확장 수신자 유형에 대해서는 정적임을 의미한다.

    open class Base { }

    class Derived : Base() { }

    open class BaseCaller {
        open fun Base.printFunctionInfo() {
            println("Base extension function in BaseCaller")
        }

        open fun Derived.printFunctionInfo() {
            println("Derived extension function in BaseCaller")
        }

        fun call(b: Base) {
            b.printFunctionInfo()   // call the extension function
        }
    }

    class DerivedCaller: BaseCaller() {
        override fun Base.printFunctionInfo() {
            println("Base extension function in DerivedCaller")
        }

        override fun Derived.printFunctionInfo() {
            println("Derived extension function in DerivedCaller")
        }
    }

    fun main() {
        BaseCaller().call(Base())   // "Base extension function in BaseCaller"
        DerivedCaller().call(Base())  // "Base extension function in DerivedCaller" - dispatch receiver is resolved virtually
        DerivedCaller().call(Derived())  // "Base extension function in DerivedCaller" - extension receiver is resolved statically
    }


    -> print되는거
    Base extension function in BaseCaller
    Base extension function in DerivedCaller
    Base extension function in DerivedCaller

    -> 뭐라는지 모르겠다




    // ======================
    // Note on visibility
    // ======================

    확장함수는 그냥 함수와 같은 가시성 수정자를 사용한다.

    - 파일의 최상위 선언한 확장은, 같은 파일내 다른 private top-level선언에 접근 할 수 있다.
    - 리시버 타입의 바깥에 선언된다면, 그 리시버의 private or protected멤버에 접근 할 수 없다.

 */