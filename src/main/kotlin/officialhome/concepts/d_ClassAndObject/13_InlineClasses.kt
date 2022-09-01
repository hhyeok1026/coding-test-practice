package officialhome.concepts.d_ClassAndObject

/*
    // Inline classes

    몇몇 타입으로 래퍼를 만들기 위한 비즈니스 로직이 때때로 필요하다.
    그러나 힙 할당 때문에 런타임 오버헤드가 발생한다.
    게다가, 만약 래퍼된 타입이 primitive이면,
    그 퍼포먼스 타격은 끔찍하다, 원시 타입은 대게 런타입에이해 크게 최적화 된다.
    반면 레퍼는 특별한 처리를 받지 않기 때문에

    이 같은 문제를 풀기위해서,
    코틀린은 인라인 클래스라 불리는 특별한 종류의 클래스를 도입했다.
    인라인 클래스는 value-based classes의 subset이다.
    그들은 아이덴티티를 가지지 않고, 오직 값을 가질 수 있다.

    인라인 클래스를 선언하기 위해, 클래스 이름이전에 value 수정자를 써라.

    value class Password(private val s: String)

    JVM 백엔드에 대해서 인라인 클래스를 선언하기 위해
    value수정자를 써라. @JvmInline어노테이션과 함께,
    클래스 선언 이전에

    // For JVM backends
    @JvmInline
    value class Password(private val s: String)

    //!! 인라인 클래스에 대해서 inline modifier는 디프리케이티드 되었다.

    인라인 클래스는 반드시 기본 생성자에서 초기화된 하나의 프로퍼티를 가지져야한다.
    런타인에서,
    인라인클래스의 인스턴스는 하나의 프로퍼티를 사용하면서 표시 될 것이다.
    (런타입 representation에 대한 아래 내용을 참고하라.)

    // No actual instantiation of class 'Password' happens
    // At runtime 'securePassword' contains just 'String'
    val securePassword = Password("Don't try this in production")

    이것은 인라인 클래스에서 이 주요특징으로,
    이름 인라인에서 영감을 얻었다.

    클래스의 데이터는
    사용벙에 인라인 된다.
    (인사인 함수의 내용이 호출사이트에 인라인 되는 방식과 유사하다)




    // ==================
    // Members
    // ==================

    인라인 클래스는 일반적 클래스의 몇몇 기능을 제공한다.
    일반적으로, 그들은 프로퍼티와 함수를 선언하는것이 허락된다. 그리고 init블록을 갖는것을.

    @JvmInline
    value className(val s: String) {
        init {
            require(s.length > 0 ) { }
        }

        val length: Int
            get() = s.length

        fun greet() {
            println("Hello, $s")
        }
    }

    fun main() {
        val name = Name("Kotlin")
        name.greet() // method 'greet' is called as a static method
        println(name.length) // property getter is called as a static method
    }

    인라인 클래스 프로퍼티는 backing fields를 가질 수 없다.
    그들은 오직 간단한 계산 할 수 있는 프로퍼티를 가진다. ( lateinit없다. delegated 프로퍼티도 없다.)



    // ================
    // Inheritance
    // ================
    인라인 클래스는 인터페이스로 상속이 가능하다

    interface Printable {
        fun pretty Print(): String
    }

    @JvmInline
    value class Name(val s: String) : Printable {
        override fun prettyPrint(): String = "Let's $s!"
    }

    fun main() {
        val name = Name("Kotlin")
        println(name.prettyPrint()) // Still called as a static method
    }


    인라인 클래스가 클래스 계층구조에 참여하는것은 금지되어있다.
    인라인 클래스는 다른 클래스로 extend할 수 없다. 그리고 항상 final이다.




    // ================
    // Representation
    // ================

    생성성 코드에서, 코틀린 컴파일러는 각각의 인라인 클래스에 대해서 래퍼를 유지한다.
    인라인 클래스 인스턴스는 런타임에서 래퍼나 underlying type으로 나타나질 수 있다.
    이것은 어떻게 Int가 원시타입 int로서 나타나는지 래퍼 Integer처럼.. 그것과 유사하다.

    그 코틀린 컴파일러는 래퍼 타입보다 근본적인 타입을 선호한다,
    가장 성능 좋고 최적화한 코드를 생성하기 위해
    그러나, 때때로 래퍼를 유지하는게 필요하다.
    경험상, 인라인 클래스는 박스드 된다, 그들이 다른 타입으로 사용 될 때마다.


    interface I

    @JvmInline
    value class Foo(val i: Int) : I

    fun asInline(f: Foo) {}
    fun <T> asGeneric(x: T) {}
    fun asInterface(i: T) {}
    fun asNullable(i: Foo?) {}

    fun <T> id(x: T): T = x

    fun main() {
        val f = Foo(42)

        asInline(f)  // unboxed: used as Foo itself
        asGeneric(f) // boxed: used as generic type T
        asInterface(f)  // boxed: used as type I
        asNullable(f)  // boxed: used as Foo?, which is different from Foo

        // below, 'f' first is boxed (while being passed to 'id') and then unboxed (when returned from 'id)
        // In the end, 'c' contains unboxed representation (just '42'), as 'f'
        val c = id(f)
    }

    인라인 클래스는 근본 값과 래퍼로서 둘다 나타내질 수 있기 때문에
    referential equality
    (참조 평등, https://kotlinlang.org/docs/equality.html#referential-equality)
    은 무의미 하고, 그러므로 금지된다.


    // Mangling (맹글링: 짓이기다, 망치다, 훼손하다)
    인라인 클래스는 그들의 근본타입으로 컴파일 되기 때문에,
    그들은 다양한 obscure (이해하기 어려운) error를 초래할 수 있다.
    예를 들어 예기치 않은 플랫폼 시그니처 클래시와 같은.

    @JvmInline
    value class UInt(val x: Int)

    // Represneted as 'public final void compute(int x)' on the JVM
    fun compute(x: Int) { }

    // Also represented as 'public final void compute(int x)' on the JVM!
    fun compute(x: UInt) { }

    이같은 이슈를 mitigate(완화시키기)위해,
    인라인 클래스를 사용한 함수는 몇몇 함수이름에 안정한 해시코드를 더함으로써 맹글링된다.
    그러므로, fun compute(x: UInt)은 나타내질 것이다, public final void compute-<hahcode>(int x),
    그리고 그것은 크래쉬 문제를 해결한다.

    // 맹글링 체계는 1.4.30에서 변했다.
    -Xuse-14-inline-classes-mangling-scheme 컴파일러 플래그를 사용하라,
    강제로 컴파일러에게 1.4.0 맹글링 스키마를 사용하게 하기 위해 그리고 바이너리 호환성을 유지하기 위해서


    // Calling from Java code
    너는 자바코드로부터 인라인클래스를 적용하기위해서 함수를 호출 할 수 있다.
    그렇게 하기 위해서,
    너는 수동적으로 맹글링을 불가하게 해야한다.
    함수 선언전에 @JvmName주석을 더하라.

    @JvmInline
    value class UInt(val x: Int)

    fun compute(x: Int) { }

    @JvmName("computeUInt")
    fun compute(x: UInt) { }




    // ===============================
    // Inline classes vs type aliases
    // ===============================

    첫눈에, 인라인 클래스는 type aliases와 매우 유사하게 보인다,
    실제로, 둘다 새로운 유형을 도입하는 것으로 보이고,
    둘다 런타임에 근본 타입으로 표시된다.

    그러나, crucial(중대한) 차이점은 타입 얼리어스는 assignment-compatible이다. 그들의 근본 타입과 함께.
    (그리고 다른 타입 얼라이어스 같으 근본 타입과 함께),
    반면 인라인 클래스는 아니다.


    typealias NameTypeAlias = String

    @JvmInline
    value class NameInlineClass(val s: String)

    fun acceptString(s: String) {}
    fun acceptNameTypeAlias(n: NameTypeAlias) {}
    fun acceptNameInlineClass(p: NameInlineClass) {}

    fun main() {
        val nameAlias: NameTypeAlias = ""
        val nameInlineClass: NameInlineClass = NameInlineClass("")
        val string: String = ""

        acceptString(nameAlias) // OK: pass alias instead of underlying type
        acceptString(nameInlineClass) // Not OK: can't pass inline class instead of underlying type

        // And vice versa:
        acceptNameTypeAlias(string) // OK: pass underlying type instead of alias
        acceptNameInlineClass(string) // Not OK: can't pass underlying type instead of inline class
    }




    // ===============================
    // Inline classes and delegation
    // ===============================

    인라스 클래스의 인라인 값에 대한 delegation에 의한 구현은 인터페이스에서 허용된다.

    interface MyInterface {
        fun bar()
        fun foo() = "foo"
    }

    @JvmInline
    value class MyInterfaceWrapper(val myInterface: MyInterface) : MyInterface by myInterface

    fun main() {
        val my = MyInterfaceWrapper(object : MyInterface {
            override fun bar() {
                // body
            }
        })

        println(my.foo()) // prints "foo"
    }
*/























