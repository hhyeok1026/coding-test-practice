package officialhome.concepts.e_Functions

/*
    High-order functions and lambdas

    코틀린 함수는 first-class이다,
    그것은 변수와 데이터구조 안에 저장 될 수 있다는 것을 의미한다.
    그리고 argument로서 들어갈 수있고 다른 고차함수로부터 리턴 될 수 있다.
    너는 비 함수값에 대해서 가능한 함수에서 어떤 오퍼레이션을 수행할 수 있다.

    코틀린에서, 이것을 용이하게 하기 위해, 정적으로 유형이 지정된 프로그래밍 언어로서
    함수타입의 family사용하여, 함수를 나타낸다.
    그리고 제공하라, 람다식과 같은 적문화된 언어 구성의 집합을 제공한다.




    // ============================
    // Higher-order functions
    // ============================

    고차함수는 파라미터로서 함수를 가지는 함수이다, 또는 함수를 리턴함.

    고차함수의 좋은 예는 컬렉션에 대한 함수형 프로그래밍 관용구 fold이다.
    그것은 초기 누산값과, 결함된 함수를 사용하고, 현재 누산값과 각 컬렉션 요소와 연속적으로 결합하여
    매번 누산기 값을 교체하여 반환값을 빌드한다.

    fun <T, R> Collection<T>.fold(
        initial: R,
        combine: (acc: R, nextElement: T) -> R
    ): R {
        var accumulator: R = initial
        for (element: T in this) {
            accumulator = combine(accumulator, element)
        }
        return accumulator
    }


    위의 코드에서, combine은 함수타입 파라미터 (R, T) -> R 를 가진다
    그래서 그것은, argument R, T 그리고 타입R의 값을 리턴하는 함수를 가진다.
    그것은 invoked된다, for loop 내부에서, 그리고 accumulator에 할당된 값이 리턴된다.

    fold를 호출하기위해,
    argument로서 그것에 함수타입의 인스턴스를 넣는게 필요하다,
    그리고 람다표현식이 (아래에 더 설명되어 있다) 고차함수 호출부에서 이 목적을 위해 주로 사용된다.

    val items = listOf(1, 2, 3, 4, 5)

    // Lambdas are code blocks enclosed in curly braces.
    items.fold(0, {
        // When a lambda has parameters, they go first, followed by '->'
        acc: Int, i: Int ->
        print("acc = $acc, i = $i, ")
        val result = acc + i
        println("result = $result")
        // the last expression in a lambda is considered the return value:
        result
    })

    // Parameter type in a lambda are optional if they can be inffered:
    val joinedToString = items.fold("Elements:", { acc, i -> acc + " " + i }

    // Function reference can alse be used for higher-order function calls:
    val product = items.fold(1, Int::times)




    // ===============
    // Function types
    // ===============

    코틀린은 함수 타입을 사용한다, (Int) -> String 같은, 함수 선언을 다루는 경우.
    val onClick: () -> Unit = ...

    이 타입들은 특별한 표기를 가진다, 함수(파라미터와 리턴턴 값)의 시그니처에 상응하는,

    - 모든 함수 타입은 소활호로 된 파라미터 타입의 리스트와, 리턴타입 :
    (A, B) -> C 타입을 나타낸다, 함수가 A, B의 두개의 타입을 가지고,
    C의 타입을 반환한다.
    파라미터 타입의 리스트는 빈값일지모른다, () -> A 처럼.
    그 Unit리턴 타입은 생략 될 수 없다.

    - 함수 타입은 옵션적으로 추가적인 receiver type을 가질 수 있다,
    표기법에서 점 이전에 구체화 될 수 있다.
    그 타입 A.(B) -> C 는 함수를 나타낸다, 리시버 객체 A를 파라미터 B로,
    그리고 리턴한다 C타입을.
    리시버와 함수리터럴은 이 타입을 따라 종종 사용된다.

    그 함수 타입 표기법은 온션적으로 그 함수 파라미터에 대해서 이름을 포함할 수 있다.
    (x: Int, y: Int) -> Point
    이 이름은 파라미터의 의미를 문서화 하는데 사용될 수 있다.

    nullable한 함수타입을 구체화하기 위해서,
    다음과 같은 소괄호를 사용하라.
    ((Int, Int) -> Int)?

    함수타입은 또한 소괄호로 결 합 될 수 있다.
    (Int) -> ((Int) -> Unit).

    // 화살표 표기법은 오른쪽으로 연관이다,
    (Int) -> (Int) -> (Int) 는
    이전 예제와 동일하다,
    그러나
    ((Int) -> (Int)) -> Unit는 아니다.

    너는 또한 함수타입을 가질 수 있다, type alias를 사용하여 대체 이름의

    typealias ClickHandler = (Button, ClickEvent) -> Unit



    // 함수타입을 인스턴트화.

    여기 몇몇 방법이 있다, 함수 타입의 인스턴스를 획득하는.

    - 함수 리터럴 내에 코드블록을 사용하라, 다음 폼안에서
        - 람다식 { a, b -> a + b }
        - 익명 함수 fun(s: String): Int { return s.toIntOrNull() ?: 0 }

        receiver가 있는 람수 리터럴은 receiver와 함께 함수타입의 값으로 사용 될 수 있다.

    - 존재하는 선언에, 호출 가능한 레퍼런스를 사용하라
        - top-level, local, member, extension funtion: ::isOdd, String::toInt,
        - top-level, member, or extension property: List<Int>::size,
        - constructor: ::Regex

        이것들은 포함한다, bound callabe reference(바인딩된 호출 가능 참조)를, 특정 인스턴스의 멤버를 가르키는:
        foo:: toString

    그 컴파일러는 만약 정보가 충분하다면, 변수에 대해서 함수타입을 추론 할 수 있다.
    val a { i: Int -> i + 1 } // The inferred type is (Int) -> Int

    receiver의 유뮤에 상관없이 함수타입의 non-literal값은 교환 할 수 있으므로,
    리시버가 첫번째 파라미터를 대신 할 수있고, 그 반대의 경우도 같다.
    예를들어, (A, B) -> C 타입의 값은 전달 되거나, 할당될 수 있다 A.(B) -> C가 기대되는 타입의 값에,
    그리고 다른 방법으로도

    val repeatFun: String.(Int) -> String = { times -> this.repeat(times) }
    val twoParameters: (String, Int) -> String = repeatFun // OK

    fun runTransformation(f: (String, Int) -> String): String {
        return f("hello", 3)
    }

    val result = runTransformation(repeatFun) // OK


    리시버가 없는 함수타입은 기본값에 의해 추론 될 수 있다,
    비록 변수가 확장함수에서 레퍼런스로 초기화 되어도.
    이를 변경하려면, 변수타입을 명확하게 구체화해라.


    // Invoking a function type instance

    함수 타입의 값은 호출 될 수 있다, 그것의 invoke(...) 오퍼레이스틑 사용 함으로써,
    f.invoke(x) 또는 그냥 f(x)

    만약 값이 리시버타입을 가지면, 그 리시버 객체는 첫번째 파라미터로 전달 되어야한다.
    다른방법으로, 리시버가 있는 함수타입의 값을 호출하는,
    리시버객체 앞에 추가하는 것이다, 값이 확장함수인것 처럼
    1.foo(2)

    예를들어

    val stringPlus: (String, String) -> String = String::plus
    val intPlus: Int.(Int) -> Int = Int::plus

    println(stringPlus.invoke("<-", "->"))
    println(stringPlus("Hello , "world!"))

    println(intPlus.invoke(1, 1))
    println(intPlus(1, 2))
    println(2.intPlus(3)) // extension-like call


    // Inline functions
    때떄로 그것은 인라인 함수를 사용하는 것은 유용하다, 그리고 그것은 효율적인 컨트롤 플로우를 제공한다,
    고차 함수에 대해서




    // ==========================================
    // Lambda expressions and anonymous function
    // ==========================================

    람다 표현식과 익명함수는 함수리터럴이다.
    함수 리터럴은 함수이다, 함수 선언이 없고, 전달 된다 즉시, 표현식으로서
    다음 예를 고려해라

    max(strings, { a, b -> a.length < b.length })

    그 함수 max는 고차 함수이다, 그것이 함수 값을 가진다, 그것의 두번째 파라미터처럼
    그 두번째 argument는 표현식이다 그것스스로 함수인, 그리고 함수리터럴로 불려지는,
    그것은 이름있는 함수와 동등하다.

    fun compare(a: String, b: String): Boolean = a.length < b.length

    // Lambda expression syntax
    람다식의 전체 구문 형식은 다음과 같다.

    val sum: (Int, Int) -> Int = { x: Int, y: Int -> x + y }

    - 람다식은 늘 중괄호로 둘러쌓여 있다.
    - 전체 문맥형식 안의 파라미터 선언은 중괄호 내부로 간다, 그리고 타입 표기를 옵션으로 가진다.
    - body는 -> 이후이다.
    - 만약 추론 되는 람다의 리턴타입이  Unit이 아니라면,
        람다 바디 내부의 마지막 표현식이 리턴 값으로 처리된다.

    만약 너가 선택적 주석으로 모두 생략한다면,
    다음과 같다.

    val sum = { x: Int, y: Int -> x + y }


    // Passing trailing lambdas
    코틀린 컨벤션에 따르면, 만약 함수의 마지막 파라미터가 함수이면, 그때
    람다식은 전달 될수 있다, 일치하는 arguemnt로서, 바깥 소괄호에 위치하게 되는

    val product = items.fold(1) { acc, e -> acc * e }

    이 같은 문법은 또한 trailing lambda로 알려져 있다.

    만약 람다가 오직 저 호출안에서 유일한 argument이면, 그 소괄호는 완전히 생략 될 수 있다.


    // it: implicit name of a single parameter
    싱글 파라미터의 암시적인 이름.

    람다식에 대해서 일반적이다, 오직하나의 파라미터를 가지는 것은.

    만약 컴파일러가 어떤 파라미터도 없는 시그니처를 파싱한다면,
    그 파라미터는 선언이 필요가 없고, -> 화살표도 생략 될 수 있다.
    그 파라미터는 암시적으로 선언된다, 이름이 it아래에
    ints.filter { it > 0 } // this literal is of type '(it: Int) -> Boolean'


    // Returning a value from a lambda expression
    // 람다식에서 값을 반환하는 것.

    너는 return을 사용하여 람다에서 명시적으로 리턴 할 수 있다,
    그렇지 않으면, 마지막 표현식이 암시적으로 리턴된다.

    그러므로 두 스니펫은 동등하다.

    ints.filter {
        val shouldFilter = it > 0
        shouldFilter
    }

    ints.filter {
        val shouldFilter = it > 0
        return@filter shouldFilter
    }

    이 컨벤스션 소괄호 밖에 람다식을 전달하는것을 따른다,
    함께, LINQ 스타일 코드를 따른다.

    strings.filter { it.length == 5 }.sortedBy { it }.map { it.uppercase() }


    // Underscore for unused variables
    // 쓰이지 않은 변수에 대하여 언더스코어 하라.

    만약 람다 파라미터가 사용되지 않으면, 너는 그것의 이름 대신에 언더스코어로 대체할 수 있다.
    map.forEach { (_, value) -> println("$value!") }


    // Destructuring in lambdas
    람다안의 구조분해해는 destructuring declarations의 파트로서 설명된다.


    // Annonymous functions
    위의 람다 표현식 문법은 한가지가 빠져있다 - 함수의 리턴타입을 구체화하는 기술에 대해
    대부분의 케이스에서, 이것은 불필요하다, 리턴타입은 자동으로 추론되기 때문에
    그러나, 만약 너가 명시적으로 구체화하려면,
    너는 대안적인 문법을 사용할 수 있다, 익명 함수

    fun(x: Int, y: Int): Int = x + y

    익명함수는 일반적인 함수 선언같이 보인다, 그것의 이름이 생략된것을 제외하고.
    그것의 바디는 또 한 표현식이거나 블록일 수 있다.

    fun(x: Int, y: Int): Int {
        return x + y
    }

    그 파라미터와 리턴 타입은 일반함수에 대한 것 처럼 같은 방식으로 구체화된다,
    그 파라미터 타입이 생략 될 수 있다는 것을 제외하고 만약 그들이 그 컨텍스트로부터 추론 된다면

    ints.filter(fun(item) = item > 0)

    익명 함수에 대한 그 리턴 타입 유추는 일반 함수처럼 작동한다:
    그 리턴 타입은 추론된다 자동적으로, 익명함수에 대해, 표현식 바디와 함께
    그러나 그것은 명확하게 구체화 되어야한다 (또는 Unit이 된다고 가정되야한다), 익명함수에 대해, 블록 바디와 함께

    // 파라미터로 익명함수를 전달할때, 그 소괄호 내부에 그것들을 위치시켜라.
    그 짧은 문법(약식구문은), 허락한다, 너에게, 그 함수를, 소괄호의 밖에, 그 문법은 람다 함수를 위해서만 오직 작동한다.

    람다표현식과 익명함수 사이의 다른 차이점은 non-local returns의 행동이다.
    리턴구문은, 라벨없이, 늘 fun키워드로 선언된 함수로부터 리턴한다.
    이것은 의미한다, 리턴, 람다식의 내부의, 닫혀식 함수로부터 리턴 할 것이다,
    반면에, 리턴, 익명하수의, 익명함수 그자신으로부터 리턴 될 것이다.


    // Closures (폐쇄? 가까운이라는 의미같은데..그냥 클로저라고 불러야할듯)
    그 람다식 또는 익명함수는 (로컬 함수나 객체표현식으로 알려진)
    그것의 클로저에 접근 할 수 있다.

    var sum = 0
    ints.filter { it > 0 }.forEach {
        sum += it
    }
    print(sum)


    // Function literals with receiver

    리시버와 함께 함수 타입은. A.(B) -> C 같은, 초기화 될 수 있다, 함수리터럴의 특별함 폼으로
    -  function literals with receiver.

    위에서 언급한것 처럼, 코틀린은 제공한다, 함수타입의 인스턴스를 호출하는 기술을, 리시버와 함께,
    리시버 객체가 제공되는 동안

    함수 리터럴의 바디 내부에,
    그 리시버 객체는 전달된다 호출하기위해서, 된다, 암시적인 this가
    너가 접근 하기위해서, 리시버 객체의 멤버에, 어떤 부가적인 한정자 없이,
    또는 접근해라 리시버 객체를 this표현식을 사용하여서

    그 행동은 유사하다, 확장함수의 것과,
    그리고 또한 너는 함수바디의 내부에 리시버객체의 멤버에 접근 가능하다.

    여기 함수리터럴의 예, 리시버와 이것의 타입과 함께, plus는 호출된다, 리시버 객체에서

    val sum: Int.(Int) -> Int = { other -> plus(other) }

    익명 함수 문법은 너가 함수리터럴의 리시버 타입을 구체화를 직접적으로 하게 한다.
    이것은 유용할 수 있다, 만약 너가 선언이 필요하다면, 리시버와 함수타입의 변수 선언이,
    그때 그것을 나중에 사용하기 위해서

    val sum = fun Int.(other: Int): Int = this + other

    람다식은 함수 리터럴로서 리시버와 함께 사용될 수 있다, 리시버 타입이 컨택스트로 부터 추론 할 수 있을때
    그들의 사용법의 가장중요한것중 하나는, type-safe builder이다.

    class HTML {
        fun body() { ... }
    }

    fun html(init: Html.() -> Unit): HTML {
        val html = HTML() // create the receiver object
        html.init() // pass the receiver object to the lambda
        return html
    }

    html {  // lambda with receiver begins here
        body() // calling a method on the receiver object
    }


 */
























