package officialhome.concepts.k_Annotations

/*
    Annotations

    어노테이션은 코드에 메타데이터를 붙이는 것을 의미한다.
    어노테이션을 선언하는것은
    클래스의 앞에 annotation modifier를 붙이는것이다.

    annotation class Fancy

    어노테이션의 추가적인 속성은 구체화되는것이다,
    메타 어노테이션으로 어노테이션 클래스를 어노테이팅하여

    - @Target element의 가능한 종류를 구체화한다,
    그 어노테이션으로 어노테이티드 될 수 있는,
    (클래스, 함수, 프로퍼티, 표현식 같은)

    - @Retention
    구체화한다,
    어노테이션이 컴파일된 크래스 파일에 저장된건지,
    런타임에 리플렉션을 통해서 보이는 건지
    (기본으로, 둘다 true이다)

    - @Repeatable
    같은 어노테이션을 사용하게 한다,
    같은 요소에 여러번

    - @MustBeDocumented
    구체화한다,
    어노테이션이 public api의 부분이라는것,
    그리고 클래스안에 포함되거나, 메서드 시그니처가 생성된 api 문서에서 보여진다는걸

    @Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION,
        AnnotationTarget.TYPE_PARAMETER, AnnotationTarget.VALUE_PARAMETER,
        AnnotationTarget.EXPRESSION)
    @Retention(AnnotationRetention.SOURCE)
    @MustBeDocumented
    annotation class Fancy




    // =========
    // Usage
    // =========

    @Fancy class Foo {
        @Fancy fun baz(@Fancy foo: Int): Int {
            return (@Fancy 1)
        }
    }

    만약 너가 클래스의 기본 생성자를 어노테이스 하는게 필요하다면,
    너는 constructor 키워드를 추가하는게 필요하다, 생성자 선언에다
    그리고 그것이전에 어노테이션을 추가하라.

    clss Foo @Inject constructor(dependency: MyDependency) { ... }

    너는 또한 프로퍼티 액세서에 어노테이트 할 수 있다.

    class Foo {
        var x: MyDependency? = null
            @Inject set
    }




    // =============
    // Constructor
    // =============

    어노테이션은 파라미터를 가지는 생성자를 가질 수 있다.

    annotation class Special(val why: String)

    @Special("example") class Foo {}


    허용된 파라미터는 :
    - 자바 원시타입에 일치하는 타입 (Int, Long etc..)
    - String
    - Class (Foo::class)
    - Enums
    - Other annotations
    - Arrays of the types listed above

    어노테이션 파라미터는 nullable 타입을 가질 수 없다,
    JVM은 null을 저장하는것을 지원하지 않는다, 어노테이션 속성의 값으로서

    만약 어노테이션이 다른 어노테이션의 파라미터로 사용되면,
    그것의 이름은 @ 로 prefixed되지 않는다.

    annotation class ReplaceWith(val expression: String)
    annotation class Deprecated(
            val message: String,
            val replaceWith: ReplcaeWith = ReplaceWith(""))

    @Deprecated("This function is deprecated, use === instead", Replacewith("this === other"))

    만약 어노테이션의 arguments로서 클래스를 구체화하기 위해서 너는 필요하다,
    코틀린 클래스(KClass)를 사용하는것이.
    그 코틀린 컴파일러는 자동적으로 그것을 자바 클래스로 변경할 것이다,
    그 자바 코드를 어노테이션과 일반적 어귀먼트로 접근하기 위해서

    import kotlinreflect.kClass

    annotation class Ann(val arg1: KClass<*>, val arg2: KClass<out Any>)

    @Ann(String::class, Int::class) class MyClass




    // =========================
    // Instantiation - 인스턴스화
    // =========================

    자바에서, 어노테이션 타입은 인터페이스의 형식이다,
    그래서 너는 그것을 구현할 수 있다, 그리고 인스턴스로 사용 할 수 있다.
    이 메커니즘의 대안적으로,
    코틀린은 너가 임의의 코드안의 어노테이션 클래스의 모든 생성자 호출을 하게 한다,
    그리고 비슷한 결과적인 인스턴스를 사용한다.

    annotation class InfoMarker(val info: String)

    fun processInfo(marker: InfoMarker): Unit = 투두()

    fun main(args: Array<String>) {
        if (args.isNotEmpty())
            processInfo(getAnnotationReflective(args))
        else
            processInfo(InfoMarker("default"))
    }




    // ========
    // Lambdas
    // ========

    어노테이션은 또한 람다에서 사용될 수 있다.
    그들은 늘 그 invoke()메서드로 적용된다, 람다가 생성된 바디로

    이것은 Quasar같은 프레임워크에 대해서 유용하다,
    그것은 동시성 제어를 위한 어노테이션으로 사용된다.

    annotation class Suspendable

    val f = @Suspendable { Fiber.sleep(10) }



    // ================================
    // Annoatation use-site targets
    // ================================

    너의 어노테이팅할때, 프로퍼티나 기본 생성자 파라미터를,
    다양한 자바 요소가 있다, 코틀린 요소에 일치하여 생성되는

    그리고 그러므로 다양한 가능한 위치한다, 어노테이션에 대해, 자바 바이트 코드가 생성된
    구체화하기위해, 정확한 어노테이션을 생성된, 다음 문법을 사용하라.

    class Example(@field:Ann val foo, // annotate Java field
                @get:Ann val bar,   // annotate Java getter
                @param:Ann val quux)    // annotate Java constructor parameter


    그 같은 문법은 전체 파일을 어노테이스하기위해 사용된다.
    이렇게 하기위해서,
    파일의 최상위레벨에서 target file을 어노테이션으로 넣어라,
    패지키 이전에,
    파일이 기본 패키지에 있는 경우 모든 import 전에.

    @file:JvmName("Foo")
    package org.jetbrains.demo

    만약 너가 같은 타켓에 여러 어노테이션을 가진다면,
    너는 그 타겟 반복을 피할 수 있다,
    타겟이후에 괄호를 추가하여,
    그리고 괄호 내부에 모든 어노테이션을 넣을 수 있다.

    class Example {
        @set:[Inject VisibleForTesting]
        var collaborator: Collaborator
    }


    // 지원 되는 use-site target의 전체목록은 아래와 같다.

    - file
    - property (이 타겟 어노테이션은 자바에서 보이지 않는다)
    - filed
    - get (property getter)
    - set (property setter)
    - receiver (확장 함수나 프로퍼티의 리시버 파라미터)
    - param (생성자 파라미터)
    - setparam (프로퍼티 setter파라미터)
    - delegate (그 delegate property에 대해 delegate 인스턴스를 저장하는 필드)

    확장함수의 리시버 파라미터를 어노테이트 하기위해서, 다음 문법을 사용하라.

    fun @receriver:Fancy String.myExtension() { ... }

    만약 use-site target이 구체화되지않는다면,
    그 타겟은 어노테이션이 사용된 @Target어노테이션에 따르는게 선택된다.
    만약 다양한 적용가능한 target이 있다면,
    그 따르는 리스트에서 첫번째 적용가능한 타겟이 사용된다.

    - param
    - property
    - field




    // ================================
    // Java annotations
    // ================================

    자바 어노테이션은 코틀린과 100% 호환된다.

    import org.junit.Test
    import org.junit.Assert.*
    import org.junit.Rule
    import org.junit.rules.*

    class Tests {
        // apply @Rule annotation to property getter
        @get:Rule val tempFolder = TemporaryFolder()

        @Test fun simple() {
            val f = tempFolder.newFile()
            assertEquals(42, getTheAnswer())
        }
    }


    자바에서 쓰인 어노테이션에 대한 파라미터의 순서는 정의되지 않았기 때문에,
    너는 사용 할 수 없다, 일반적인 함수 호출 문법을, argument를 전달하는,
    대신에 너는 명명된 arguemnt문법을 사용해야한다.

    // Java
    public @interface Ann {
        int intValue();
        String stringValue();
    }


    // Kotlin
    @Ann(intValue = 1, stringValue = "abc") class C


    단지 자바처럼, 특별한 케이스는 value파라미터이다,
    그것의 값은 명확한 이름 없이 구체화 될 수 있다.

    // Java
    public @interface AnnWithValue {
        String value();
    }

    // Kotlin
    @AnnWithValue("abc") class C



    // Arrays as annotation parameters
    만약 자바에서 value argument가 배열 타입을 가지면,
    그것은 코틀린에서 vararg 파라미터가 된다.

    // Java
    public @interface AnnWithArrayValue {
        String[] value();
    }

    // Kotlin
    @AnnWithArrayValue("abc", "foo", "bar") class C


    어레이 타입을 가지는 다른 argument엗 ㅐ해서,
    너는 필요하는 그 어레이 리터럴 신택스를 또는 arrayOf(...)

    // Java
    public @interface AnnWithArrayMethod {
        String[] names();
    }

    @AnnWith Array Method(names = ["abc", "foo", "bar"])
    class C



    // Accessing properties of an annotation instance
    어노테이션 인스턴스의 값은 코틀린 코드에서 프로퍼티로 노출된다.

    // Java
    public @interface Ann {
        int value();
    }

    // Kotlin
    fun foo(ann: Ann) {
        val i = ann.value
    }




    // ================================
    // Repeatable annotations
    // ================================

    자바에서와 마찬가지로, 코틀린은 반복적인 어노테이션을 가진다.
    그리고 적용될 수 있다, 하나의 코드요소로 여러번.

    너의 어노테이션을 반복할 수 있게 만들기 위해서,
    그것의 선언을 @kotlin.annotation.Repeatable 메터 어노테이션으로.

    이것은 코틀린과 자바 둘 다 반복가능하게 만들 것이다.

    자바 repeatable annotation은 또는 코틀린 사이드로부터 지원된다.

    그 주요차이점, 자바에서 사용된 스키마와, containing annotation의 부재이다,
    그리고 그것은 코틀린 컴파일러는 생성한다, 자동적으로 미리 정의된 이름과 함께.
    아래 예의 어노테이션에 대해서, 그것은 @Tag.Container를 포함하는 어노테이션을 만들것이다.


    @Repeatable
    annotation class Tag(val name: String)

    // The compiler generates the @Tag.Container containing annotation

    너는 포함하는 어노테이션에 대해서 커스텀 이름을 set할 수 있다,
    @kotlin.jvm.JvmRepeatable 메터 어노테이션을 적용함으로써,
    그리고 전달한다, 명확하게 선언된 어노테이션 클래스가 포함된, argument로서

    @JvmRepeatable(Tags::class)
    annotation class Tag(val name: String)

    annotation class Tags(val value: Array<Tag>)


    코틀린이나, 자바 어노테이션을 리플렉셔은 통해 추출하기 위해서,
    사용하라, KAnnotatedElement.findAnnotations() 함수로








 */


































