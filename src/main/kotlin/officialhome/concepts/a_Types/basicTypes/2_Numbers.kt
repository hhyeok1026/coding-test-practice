package officialhome.concepts.a_Types.basicTypes

/*

    =================
    // Integer types
    =================

    정수형에는 4가지 타입이 있다.
    Byte 1 byte
    Short 2 byte
    Int 4 byte
    Long 8 byte

    val one = 1 // Int
    val threeBillion = 3000000000 // Long
    val oneLong = 1L // Long
    val oneByte: Byte = 1

    기본적으로 Int로 추론되고, 사이즈를 넘으면 Long으로 알아서 추론함.
    Long은 숫자뒤에 L을 붙이면 알아서 추론됨.
    다른 타입을 명시적으로 쓰려면, 직접 적으면 된다.



    ========================
    // Floating-point types
    ========================

    실수를 표현하기 위해 제공된다.

    Float 4byte
    Double 8byte

    기본적으로 소숫점이 붙은 수에는 double로 추론되고, float를 쓰고 싶으면 f or F를 붙이면 된다.

    val pi = 3.14 // Double
    // val one: Double = 1 // Error: type mismatch
    val oneDouble = 1.0 // Double

    val e = 2.7182818284 // Double
    val eFloat = 2.7182818284f // Float, actual value is 2.7182817

    코틀린에서는 기본적으로 큰 변수로 확대시키지 않음.
    다른 변수 타입으로 변경하려면, explict conversion해야함.

    fun main() {
        fun printDouble(d: Double) { print(d) }

        val i = 1
        val d = 1.0
        val f = 1.0f

        printDouble(d)
        // printDouble(i) // Error: Type mismatch
        // printDouble(f) // Error: Type mismatch
    }



    =================================
    // Literal constants for numbers
    =================================

    Decimals: 123

    Longs are tagged by a capital L: 123L

    Hexadecimals: 0x0F

    Binaries: 0b00001011

    8진수 리터럴은 코틀린에서 지원하지 않음.

    Doubles by default: 123.5, 123.5e10

    Floats are tagged by f or F: 123.5f


    - You can use underscores to make number constants more readable:
    val oneMillion = 1_000_000
    val creditCardNumber = 1234_5678_9012_3456L
    val socialSecurityNumber = 999_99_9999L
    val hexBytes = 0xFF_EC_DE_5E
    val bytes = 0b11010010_01101001_10010100_10010010




    ======================================
    // Numbers representation on the JVM
    ======================================

    jvm에서 숫자는 원시형으로 저장됨.
    Int? 같은 nullable한 수나, 제러닉은 예외이다. -> 이 경우 Integer, Double같은 자바클래스로 Boxed 된다.


    같은 수에 대한 nullable한 참조는 다른 오브젝트를 참조할 수 있다. ->  nullable 변수를 여러개 생성해놓고 변수받으면, 다른 object로 생성 될 수 도 있다는 말인듯.

    val a: Int = 100
    val boxedA: Int? = a
    val anotherBoxedA: Int? = a

    val b: Int = 10000
    val boxedB: Int? = b
    val anotherBoxedB: Int? = b

    println(boxedA === anotherBoxedA) // true //-128~127내의 숫자면, jvm 메모리 최적화 때문에 같은 객체를 같는다.
    println(boxedB === anotherBoxedB) // false



    ===============================================
    // Explicit number conversions  명시적 숫자 변환
    ===============================================

    더 작은 유형은 큰 유형의 하위가 아니다.

    // Hypothetical code, does not actually compile:
    val a: Int? = 1 // A boxed Int (java.lang.Integer)
    val b: Long? = a // Implicit conversion yields a boxed Long (java.lang.Long)
    print(b == a) // Surprise! This prints "false" as Long's equals() checks whether the other is Long as well

    결과적으로 암시적으로는 변환할 수 없다.
    명시적으로 변환 해야함.

    val b: Byte = 1 // OK, literals are checked statically
    // val i: Int = b // ERROR
    val i1: Int = b.toInt()

    - All number types support conversions to other types:
    toByte(): Byte
    toShort(): Short
    toInt(): Int
    toLong(): Long
    toFloat(): Float
    toDouble(): Double


    // 많은 경우에 context와 산술연산에서 타입 추론이 되기 때문에 명시적으로 할 필요가 없을 수도 있음.
    val l = 1L + 3 // Long + Int => Long



    ==========================
    // Operations on numbers
    ==========================

    //+ - * / % 와 같은 기본적인 산술 연산을 제공한다.
    println(1 + 2)
    println(2_500_000_000L - 1L)
    println(3.14 * 2.71)
    println(10.0 / 3)


    //정수의 나눗셈
    나눴을때 항상 정수로 리턴함.
    소수부분은 삭제 된다.

    val x = 5 / 2
    //println(x == 2.5) // ERROR: Operator '==' cannot be applied to 'Int' and 'Double'
    println(x == 2)

    val x = 5L / 2
    println(x == 2L)

    //실수형으로 반환하려면 이렇게 명시적으로 써줘야한다.
    val x = 5 / 2.toDouble()
    println(x == 2.5)


    //비트 연산
    // 오직 Int와 Long타입에서 가능함.

    val x = (1 shl 2) and 0x000FF000

    shl(bits) – signed shift left
    shr(bits) – signed shift right
    ushr(bits) – unsigned shift right
    and(bits) – bitwise AND
    or(bits) – bitwise OR
    xor(bits) – bitwise XOR
    inv() – bitwise inversion


    // 부동 소수점 숫자 비교.

    Equality checks: a == b and a != b
    Comparison operators: a < b, a > b, a <= b, a >= b
    Range instantiation and range checks: a..b, x in a..b, x !in a..b

    Float, Double에서는 표준적으로 됨.

    그러나, Any, Comparable<...>같은 제너럭지원이나 순서를 제공하기 위해서 쓸때는
    equals, compareTo 를 사용한다.

    NaN is considered equal to itself
    NaN is considered greater than any other element including POSITIVE_INFINITY
    -0.0 is considered less than 0.0
*/


fun main() {

}




























