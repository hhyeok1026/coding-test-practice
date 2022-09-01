package officialhome.concepts.b_ControlFlow

/*
    // ==========================
    // Conditions and loops
    // ==========================

    코틀린에서는 삼항 연산자가 없다.
    그냥 if문으로 쓰면 되니까!

    var max = a
    if ( a < b ) max = b

    // With else
    var max: Int
    if ( a > b ) {
        max = a
    } else {
        max = b
    }

    // As expression -> 표현식을 사용하는 경우, 값을 할당하거나 리턴한다면 else구문도 필수이다.
    val max = if ( a > b ) a else b

    val max = if ( a > b ) {
        print("Choose a")
        a
    } else {
        print("Choose b")
        b
    }


    // ==========================
    // When expression
    // ==========================

    when (x) {
        1 -> print("x == 1")
        2 -> print("x == 2")
        else -> {
            print("x is neither 1 nor 2")
        }
    }

    이것도 if와 마찬가지로 표현식으로 쓸 때, else구문은 필수이다.
    enum클래스에서 모든 경우의 수를 가져온다면 else구현을 안 할 수도 있음.

    enum class Bit {
        ZERO, ONE
    }

    val numericValue = when (getRandomBit()) {
        Bit.ZERO -> 0
        Bit.ONE -> 1
        // 'else' is not required because all case are covered
    }


    // 다양한 케이스를 한 라인으로 작성 할 수 있다.
    when (x) {
        0, 1 -> print("x == 0 or x == 1")
        else -> print("otherwise")
    }

    // 임의의 표현식을 넣을 수도 잇음.
    when (x) {
        s.toInt() -> print("s encodes x")
        else -> print("s does not encode x")
    }

    // in or !in을 이용하여 range를 체크할 수 있다.
    when (x) {
        in 1..10 -> print(" xis in the range")
        in validNumbers -> print("x is valid")
        !in 10..20 -> print("x is outside the range")
        else -> print("none of the above")
    }

    // is or !is 로 타입을 체크 할 수 있음.
    fun hasPrefix(x: Any) = when(x) {
        is String -> x.startsWith("prefix")
        else -> false
    }

    // if-else if구문을 대체하여 사용 할 수 있음.
    그리고 제공되는 argument가 없으면, boolean표현시긍로 간단히 할 수 있다.
   when {
        x.isOdd() -> print("x is odd")
        y.isEven() -> print("y is even")
        else -> print("x+y is odd")
   }


   //다음 구문을 이용하여, 변수의 subject를 capture할 수 있다.
   // 변수의 scope? 값이, 이 when에 의해서 제한된다.
   fun Request.getBody() =
        when (val reponse = excuteRequest()) {
            is Success -> response.body
            is HttpError -> throw HttpException(response.status)
        }




    // ===========================
    // For loops
    // ===========================

    for루프는 iterator를 제공하는 것을에 대해서 반복한다.
    그것은 foreach와 동등하다.

    // 문법
    for (item in collection) print(item)

    // for의 body는  blcok이 될 수 있다.
    for (item: Int in ints) {
        // ...
    }

    이전 언급 처럼 for 제공한 iternator에 모든 것을 통해 반복한다. 이것의 의미는 다음과 같다.
    iterator() 함수를 가지고, Iterator<>를  return 한다.
    member나 확장함수 next()를 가진다.
    member나 확장함수 hasNext()를 가지고, boolean을 리턴한다.

    이 모든 함수는 operator로 marked되는게 필요하다.

    수의 범위를 반복하기 위해, range expression을 사용하라.

    for (i in 1..3) {
        println(i)
    }

    for (i in 6 downTo 0 step 2) {
        println(i)
    }


    범위나 배열에 대한 for루프는 반복기 object를 생성하는게 아니라 인덱스 기반 루프로 컴파일 된다.

    // index로 접근하기 위해서 이렇게 사용 할 수 있다.
    for (i in array.indicies) {
        print(array[i])
    }

    // 대안적으로, withIndex함수를 사용할 수 있다.
    for ((index, value) in array.withIndex()) {
        println("the element at $index is $value")
    }



    // ===========================
    // While loops
    // ===========================

    조건에 만족되고 있으면, 계속해서 돌게 된다.

    while (x > 0) {
        x--
    }

    do {
        val y = retrieveData()
    } while (y != null) // y is visible here!


    // ============================
    // Break and continue in loops
    // ============================
    전통적인 break와 continue를 제공한다.
    다음에 있는 Returns and jump 문서를 보세요!


*/

enum class Color {
    RED, GREEN, BLUE
}

fun main() {

    when ( Color.RED ) {
        Color.RED -> println("red")
        Color.GREEN -> println("green")
        Color.BLUE -> println("blue")
        // 'else' is not required because all cases are covered
    }

    when ( Color.RED ) {
        Color.RED -> println("red") // no branches for GREEN and BLUE
        else -> println("not red") // 'else' is required
    }



}














