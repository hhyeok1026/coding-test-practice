package officialhome.concepts.b_ControlFlow

/*
    Exceptions


    코틀린의 모든 exception classes는 Throwable클래스를 상속한다.
    모든 에외는 message, stack trace, optional cause를 가진다.


    예외 객체를 throw하기 위해서,
    throw 표현식을 사용하라.

    throw Exception("Hi There!")


    예외를 catch하기 위해서, try...catch문을 써라.

    try {
        // some code
    } catch (e: SomeException) {
        // handler
    } finally {
        // optional finally block
    }

    catch가 없거나 많을 수도 있고, finally블록이 생략 될 수도 있다.
    하지만 최소한 하나이 catch나 finally이 요구된다.



    val a: Int? = try { input.toInt() } catch (e: NumberFormatException) { null }

    try표현식의 반환값은 try블록의 마지막 표현식이거나, catch 블록의 마지막 표현식이다.
    finally의 컨텐츠는 표현식의 결과에 영향을 주지 않는다.




    // ====================
    // Checked exceptions
    // ====================

    코틀린에는 checked excetion이 없다.
    이것에는 많은 이유가 있다.
    그러나 우리는 이 이유를 증명할 간단한 예를 제공할 것이다.

    Appendable append(CharSequence csq) throws IOException;

    try {
        log.append(message)
    } catch (IOException e) {
        // Must be safe
    }

    -> 대충 소규모 프로젝트에서는 좋았지만, 대규모 프로젝트로 갈 수록 않좋았다고함.


    다른 언어에서 코틀린 코드를 호출 할 때,
    가능한 예외에 대해서 호출자에 경로를 주려면 @Throws 어노테이션을 사용하라.




    // ====================
    // The Nothing type
    // ====================

    throw 는 코틀린 표현식이다, 그래서 너는 사용할 수 있다. 예로 엘비스 표현식의 부분으로..

    val s = person.name ?: throw IllegalArgmentException("Name requied")


    throw표현식에는 nothing type이 있다.
    이 유형에는 값이 없고, 도달 할 수 없는 코드 위치를 표시하는데 사용된다.
    당신의 코드에서 nothing마크를 쓸 수 있다. 결코 리턴하지 않을 function에..

    fun fail(message: String): Nothing {
        throw IllegalArgumentException(message)
    }

    너가 이 함수를 사용할때, 컴파일러는 이 호출 뒤 계속 실행되지 않을거라는 것을 안다.
    val s = person.name ?: fail("Name requied")
    println(s)  // 's' is known to be initialized at this point


    너는 이 타입 추론을 다룰 때, 이 유형이 생길 수 있다.
    type의 nullable 변형인 Nothing?에는 정확이 하나의 null이라는 값을 가질 수 있게 된다.
    만약 추론된 타입의 값에 초기화에 null을 사용하고, 구체적으로 타입을 결정할 수 있는
    다른 정보가 없는 경우 컴파일러는 Nothing?타입으로 추론할것이다.

    val x = null    // 'x' has type `Nothing?`
    val l = listOf(null)    // `1` has type `List<Nothing?>


    // =======================
    // Java interoperability
    // =======================
    자바 상호운용성은 이 문서를 참고 하세요.
    doc - Platforms - JVM - calling Java from Kotlin
    https://kotlinlang.org/docs/java-interop.html

*/
