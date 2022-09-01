package officialhome.concepts.m_Reflection

/*
    Reflection

    리플렉션은 셋이다, language와 라이브러리 특징이다,
    너를 검사하기위한 런타입에서 너의프로그램의 구조를.

    함수와 프로퍼티는 코틀린에서 일급시민이다,
    그리고 함수적 또는 반응적 스타일을 사용할 때 이들을 내성하는기능
    (예: 런타임 속성이나 함수의 이름이나 유형학습)은 필수적이다.

    // Kotlin/JS는 리플렉션 기능에 제한이 있다.
    js문서를 보라
    https://kotlinlang.org/docs/js-reflection.html




    // ================
    // JVM dependency
    // ================

    JVM 플랫폼에서, 코틀린 컴파일러 배포판에는 포함한다, 런타임 컴포넌트를 분리된 아티팩트로서
    리플렉션 특징을 사용하는것에 대해서 요구되는, kotlin-reflect.jar
    이것은 줄인다, 어플리케이션에 대해서 런타임 라이브러리의 사이즈를,
    리플렉션 특징을 사용하지 않는.

    그래들이나 메이븐 프로젝트에서 리플렉션을 사용하기위해서,
    kotlin-reflect 디펜던시를 추가하라.

    // In Gradle - Kotlin
    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-reflect:1.7.10")
    }

    만약 너가 그레이들이나 메이븐을 사용하지 않는다면,
    너는 확인해야한다, 너의 프로젝트의 클래스경로안에 kotlin-reflection.jar가 있는지
    다른 지원되는 케이스(인텔리j idea 프로젝트는 사용한다, 커맨드라인 컴파일러나 Ant를),
    그것은 기본적으로 추가된다.
    커맨드라인 컴파일러나 Ant는, 너는 사용할 수 있다, -no-reflect 컴파일러 옵션을,
    kotlin-reflect.jar를 실행하기위해, 클래스패스에서




    // =================
    // Class references
    // =================

    가장 기초의 리플렉션 특징은 코틀린 클래스에서 런타임 레퍼런스를 얻는것이다.
    정적으로 알려진 코틀린 클래스에서 레퍼런스를 획득하는것은,
    너는 클래스 리터럴 문법을 사용가능하다.

    val c = MyClass::class

    그 레퍼런스는 KClass 타입 값이다.

    //On JVM에서: 코틀린 클래스 레퍼런스는 자바 클래스 레퍼런스 같은게 아니다.
    자바 클래스 레퍼런스를 획득하기 위해서, .java property를 사용하라, KClass 인스턴스에서

    // Bound class references
    너는 특정한 객체의 클래스에 레퍼런스를 얻을 수 있다, ::class같은 문법으로 리시버로서 그 객체를 사용하여

    val widget: Widget = ...
    assert(widget is GoodWidget) { "Bad widget: ${widget::class.qualifiedName}" }

    너는 객체의 정확한 클래스에서 그 레퍼런스를 얻기위해서,
    예를 들어,
    GoodWidget or BadWidget,
    관계없이, 리시버 표현식의 타입에, (Widget)



    // =====================
    // Callable references
    // =====================

    레퍼런스, 함수, 프로퍼티, 그리고 생성자에서, 호출될 수 있다, 또는
    함수 타입의 인스턴스로서 사용될 수 있다.

    그 공통의 수퍼타입 모든 호출할 수 있는 레퍼런스에 대해서는
    KCallable<out R>,
    R은 그 리턴 밸류 타입이다.
    그것은 프로퍼티에 대한 프로퍼티 타입이다,
    그리고 생성자에 대한 생성된 유형입니다.


    // Function references
    너가 아래 선언된 이름있는 함수를 가질때,
    너는 호출 할 수 있다 그것을 직접적으로
    (isOdd(5)):

    fun isOdd(x: Int) = x % 2 != 0

    대안적으로, 너는 함수를 사용할 수 있다, 함수타입값으로, 그것은 전달한다, 다른 함수에서 그것으로
    이렇게 하려면, :: operator를 사용하라.

    val numbers = listOf(1, 2, 3)
    println(numbers.filter(::isOdd))

    여기 ::isOdd는 함수타입의 값이다. (Int) -> Boolean.

    함수레퍼런스는 KFunction<out R> 서브타입의 하나에 속한다,
    파라미터 카운터에 의존되는
    예를 들어서, KFuntion3<T1, T2, T3, R>.

    :: 사용될 수 있다, 오버로드된 함수로, 기대되는 타입이 컨택스트로 부터 알려질때.
    예를 보라.

    fun isOdd(x: Int) = x % 2 != 0
    fun isOdd(s: String) = s == "brilling" || s == "slithy" || s == "tove"

    val numbers = listOf(1, 2, 3)
    println(numbers.filter(::isOdd)) // refer to isOdd(x: Int)

    대안적으로, 너는 필수적인 컨택스트를 제공 할 수 있다, 저장함으로서, 그 메서드 레퍼런스를,
    밸류안에서, 명확하게 주체화된:

    val predicate: (String) -> Boolean = ::isOdd // refer to isOdd(x: String)

    만약 너가 사용하는게 필요하다면, 클래스의 멤버나, 확장 함수가,
    그것은 필요하는 한정자가 되는게, String::toCharArray.

    비록 너가 변수를 초기화하더라도, 확장함수에 참조의변수를, 그 추론된 함수 타입은 늘 리시버가 없다,
    그러나 그것은 추가적인 파라미터를 가지는 리시버오브젝트를 가질 것이다.
    리시버 대신에 함수타입을 가지기 위해서, 타입을 명확하게 구체화하라.

    val isEmpltyStringList: List<String>.() -> Boolean = List<String>::isEmpty

    // Example: funtion composition
    고려하라, 다음함수를
    fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C {
        return { x -> f(g(x)) }
    }

    그것은 리턴한다, 그것에 전달되는 두개의 함수의 컴포지션을: compose(f, g) = f(g(*)).
    너는 이 함수를 호출가능한 레퍼런스로 적용할 수 있다.

    fun length(s: String) = s.length

    val oddLength = compose(::isOdd, ::length)
    val strings = listOf("a", "ab", "abc")

    println(strings.filter(oddLength))



    // Property references
    프로퍼티에 접근하기 위해서, 퍼스트 클래스 객체로, 코틀린에서,
    사용하라 ::operaotr를

    val x = 1

    fun main() {
        println(::x.get()
        println(::x.name)
    }

    그 표현식 ::x는 평가한다, KProperty<Int> 타입 프로퍼티 객체로
    너는 그것의 값을 get을 사용하려 읽을 수 있다, 또는 반환할 수 있다,
    그 프로퍼티 이름을 그 이름 프로퍼티를 사용하여
    더 정보에 대해서 보라, KProperty 클래스 문서를를
    https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-property/

    변경할 수 있는 프로퍼티, var y = 1, ::y같은 리턴한다 값을 KMutableProperty<Int> type을
    그리고 그것은 set()메서드를 가진다.

    var y = 1

    fun main() {
        ::y.set(2)
        println(y)
    }


    그 프로퍼티 레퍼런스는 사용될 수 있다, 함수에서, 싱글 제너릭 파라미터가 기대되는 함수에서

    val strs = listOf("a", "bc", "def")
    println(strs.map(String::length))

    프로퍼티에 접근하기위해서, 클래스의 멤버를, 다음과 같은 자격이 따라온다.
    class A(val p: Int)
    val prop  = A::p
    println(prop.get(A(1))

    확장프로퍼티에 대해서

    val String.lastChar: Char
        get() = this[length - 1]

    fun main() {
        println(String::lastChar.get("abc"))
    }



   // Interoperability with Java reflection
   JVM 플랫폼에서, 스탠다느 라이브러리는 포함한다, 확장함수를, 리플랙션 클래스를 위해서,
   그리고 그것은 제공한다, 매핑하는에서, 자바 리플렉션 객체까지 (보라 패키지 kotlin.reflect.jvm)
   예를들어, 백킹필드를 찾기위해서, 또는 자바메서드를, 제공한다, 코틀린 프로퍼티에 대해서 게터를,
   너는 할수 있는 이겉은것을 쓰는것을.

   import kotlin.reflect.jvm.*

   class A(val p: Int)

   fun main() {
        println(A::p.javaGetter) // prints "public final int A.getP()"
        println(A::p.javaFiled) // prints "private final int A.p"
   }

   코틀린 클래스를 얻기위해서, 자바클래스에 일치하는, 사용하라, .kotlin 확장 프로퍼티를

   fun getKClass(o: Any): KClass<Any> = o.javaClass.kotlin


    // Constructor references
    생성자는 레퍼런스드 될 수 있다, 메서드와 프로퍼티 같이.
    너는 그들을 사용할 수 있다, 그 프로그램이 함수타입 객체를 기대할때마다,
    같은 파라미터를 생성자로서 가지는, 그리고 적절한 타입의 객체를 리턴하는.

    생성자는 ::연산자를 사용하여 레퍼런스된다, 그리고 클래스 이름을 추가한다.
    코라하라 다음 함수를, 기대하는 함수 파라미터를, 파라미터가 없고, 타입 Foo를 리턴하는:

    class Foo

    fun function(factory: () -> Foo) {
        val x: Foo = factory()
    }

    ::Foo를 사용하는것은, 클래스 Foo의 zero-argument 생성자에서, 너는 호출 할 수 있다 그것을 다음처럼.

    function(::Foo)

    생성자에서 호출가능한 레퍼런스는, 타입이 된다,
    파라미터의 갯수에 따라 KFunction<out R> 서브타입의 하나로



    // Bound function and property references
    너는 참조 할 수 있다, 부분 객체의 인스턴스 메서드로

    val numberRegex = "\\d+".toRegex()
    println(numberRegex.matches("29"))

    val isNumber = numberRegex::matches
    println(isNumber("29"))

    그 메서드 match를 직접적으로 호출하는 대신에,
    예제는 사용한다, 그것을 레퍼런스로
    그같은 레퍼런스는 그것의 리시버와 연결되어 있다.
    그것은 즉각 불릴 수 있다, (위의 예제와 같이)
    또는 사용된다, 함수 타입 표현식이 기되되는 언제든지

    val numberRegex == "\\d+".toRegex()
    val strings = listOf("abc", "124", "a70")
    println(strings.filter(numberRegex::matches))

    비교하라, 바인드의 타입과 언바운드 레퍼런스를.
    그 바인드된 호출가능한 레퍼런스는 그것의 리시저를 가진다, 그것에 연결되는,
    그래서 그 리시버의 타입은 파라미터가 아니다.

    프로퍼티 레퍼런스는 또한 바인딩 될 수 있다.

    val prop = "abc"::length
    println(prop.get())

    너는 그 리시버로서 this를 구체화 할 필요가 없다.
    this::foo와 ::foo은 동등하다.



    // Bound constructor reference
    바인드 호출가능한 레퍼런스는 이너 클래스의 생성자에서, 획득 될 수 있다,
    제공함으로써, 아우터 클래스의 인스턴스를

    class Outer {
        inner class Inner
    }

    val o = Outer()
    val boundInnerCtor = o::Inner

 */





































