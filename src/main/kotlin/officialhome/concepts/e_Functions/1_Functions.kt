package officialhome.concepts.e_Functions

/*
    // Functions

    코틀린 함수는 fun keyword로 선언된다.

    fun double(x: Int): Int {
        return 2 * x
    }


    // =================
    // Functions usage
    // =================

    함수는 표준 접근을 사용하여 불려진다.

    val result = double(2)

    멤버함수를 호출은 점 표기법을 사용한다.

    Stream().read() // create instance of class Stream and call read()


    // Parameters
    함수 파라미터는 파스칼 표기법을 사용하여 정의 된다. 이름: 타입
    파라미터는 콤마로 나눠지고, 각각 파라미터는 명백한 타입이 되어야한다.

    fun powerOf(number: Int, exponent: Int): Int { ... }

    너는 trailing comma를 사용할 수 있다, 너가 함수 파라미터를 사용 할 때,

    fun powerOf(
        number: Int,
        exponent: Int, // trailing comma
    ) { ... }


    // Default argument

    함수 파라미터는 기본 값을 가질 수 있다, 그리고 그 argument를 스킵할때 사용 된다.
    이것은 오버로드를 줄인다.

    fun read(
        b: ByteArray,
        off: Int = 0,
        len: Int = b.size,
    ) { ... }

    기본 값은 그 타입 이후로 =을 사용하여 정의된다.

    오버라이딩 메서드는 늘 기본메서드와 같은 기본값 파라미터 밸류를 사용한다.
    오버라이딩 메서드가 기본 파라미터 값을 가질떄,
    그 기본 파라미터 값을 반드시 기본 매개변수값을 시그니처에서 생략되어야한다.

    open class A {
        open fun foo(i: Int = 10) { ... }
    }

    class B : A() {
        override fun foo(i: Int) { ... } // No default value is allowed.
    }


    만약 기본값이 기본값이 없는 파라미터보다 선행된다면
    그 기본값은 명명된 인수로 함수를 호출 함으로 사용 될 수 있다.
    -> 설명을 드럽게 못해뒀네

    fun foo(
        bar: Int = 0,
        baz: Int,
    ) { ... }

    foo(baz = 1) // The default value bar is used


    만약 기본값 이후의 마지막 argument가 람다라면,
    너는 그것을 명명된 인수나 바깥 소괄호에에 넣을 수 있다.

    fun foo(
        bar: Int = 0,
        baz: Int = 1,
        qux: () -> Unit,
    ) { ... }

    foo(1) { println("hello") } // Use the default value baz = 1
    foo(qux = { println("hello") }) // Use both default values bar = 0, and baz = 1
    foo { println("hello") } // Use both default values bar 0, and baz = 1


    // Named arguments

    함수를 호출 할 때, 너는 이름을 하나나 그것의 argument를 더 사용 할 수 있다.
    이것은 함수가 많은 argument를 가질때 유용하고, 그것이 argument와 값이 연관이 어려울경우,
    특기 만약 불린이거나 null값일때.

    너가 함수안에서 명명된 argument를 사용할 때,
    너는 그들의 리스트안의 순서를 자유롭게 변경 할 수 있다,
    그리고 만약 너가 그들의 기본값을 사용하기를 원하면, 너는 그냥 이 argument를 완전히 생략할 수 있다.

    다음 함수를 고려하라, reformat(), 그리고 그것은 4개의 argument를 기본값과 가진다.

    fun reformat(
        str: String,
        normalizeCase: Boolean = true,
        upperCaseFirstLetter: Boolean = true,
        divideByCamelHumps: Boolean = false,
        wordSeparator: Char = ' ',
    ) { ... }

    이 함수를 호출 할때, 너는 그것의 모든 이름을 가질 필요는 없다.

    reformat(
        "String!",
        false,
        upperCaseFirstLetter = false,
        divideByCamelHumps = true,
        '_'
    )

    너는 기본값으로 된 모든것들을 스킵 할 수 있다.

    reformat("This is a long String")

    너는 또한 기본값으로 특정 argu를 생략 할 수 있다.
    그들 모든 것들을 생략하기보다,
    그러나 첫번째 argu가 생략되고나서, 너는 반드시 모든 argu의 이름을 써야한다.

    reformat("This is a short String!", upperCaseFirstLetter = false, wordSeparator = '_')


    너는 variable number of argument( vararg )를 이름과 함께,
    spread 연산자로 넣을 수 있다.

    fun foo(vararg strings: String) { ... }
    foo(strings = *arrayOf("a", "b", "c"))

    // JVM에서, 너는 자바 함수가 불려 질 때, 명명된 이름 신택스를 사용 할 수 없다,
    자바 바이트 코드는 늘 함수 파라미터의 이름을 보존하지 않기 때문이다.


    // Unit-returning functions
    만약 함수가 유용한 값을 리턴하지 않는다면,
    그것은 타입은 Unit이다.
    Unit은 오직 하나의 값인 타입이다 - Unit
    이 값은 명시적으로 반환될 필요가 없다.

    fun printHello(name: String?): Unit {
        if (name != null)
            println("Hello $name")
        else
            println("Hi there!")

        // 'return Unit' or 'return' is optional
    }

    The Unit return type declaration is also optional,
    위의 코드와 동일하다.

    fun printHello(name: String?) { ... }


    // Single-expression functions
    함수가 하나의 표현식을 리턴할때, 그 중괄호는 생략 될 수 있다.
    그리고 그 바디는 = 뒤에 구체화 될 수 있다.

    fun double(x: Int): Int = x * 2

    리턴 타입을 명확하게 선언하는 것은 옵션이다, 이것이 컴파일러에 의해서 추론될 때는

    fun double(x: Int) = x * 2


    // Explicit return types
    block body의 함수는 반드시 특정 타입으로 구체화해야한다,
    그것이  Unit으로 리턴하지 않는한, 그리고 이 케이스는 리턴 타입이 옵션이다.?

    코틀린은 블로 바디로 된 함수의 리턴 타입을 추론하지 않는다,
    함수는 바디 안에 컨트롤 플로우가 복잡 할 수 있기 때문이다, 그리고
    리턴 타입은 불분명하다, 읽는것에 따라.. ( 그리고 때때로 컴파일러에 대해서)


    // Variable number of arguments (varargs) (가변 인수 인자)
    너는 함수의 파라미터를 vararg modifier로 지정 할 수 있다. (보통 마지막 인자로)

    fun <T> asList(vararg ts: T): List<T> {
        val result = ArrayList<T>()
        for (t in ts) // ts is an Array
            result.add(t)
        return result
    }

    이 경우에, 너는 함수에 argument의 다양한갯수를 넣을 수 있다.

    val list = asList(1, 2, 3)

    내부함수에서, T타입의 가변인자는 T의 배열로 보인다, 위의 예에서 처럼,
    ts변수는 Array<out T>타입을 가진다.

    오직 하나의 파라미터만 vararg를 마크 할 수 있다.
    만약 vararg파라미터가 리스트에서 마지막에 있는게 아니면,
    연속하는 파라미터의 값에 대해서 명명된 이름 문법을 사용하여 넣을 수 있다.
    또는 만약 그 파라미터가 함수타입을 가지면, 소괄호 밖에 람다에 의해 넣을 수 있다.

    너가 vararg함수를 호출 할 때, 너는 argument를 개별적으로 넣을 수 있다.
    예를 들어서 asList(1,2,3). 만약 너가 이미 배열을 가지고, 그것을 함수에 내용으로서 넣기를 원하면,
    spread 연산자를 사용하여라, (배열앞에 *)

    val a = arrayOf(1, 2, 3)
    val list = asList(-1, 0, *a, 4)

    만약 너가 원시타입의 배열을 vararg로 넣기를 원한다면,
    너는 toTypedArray()함수를 사용하는 타입있는 배열로 변경하는 것이 필요하다.



    // infix notation 중위 표기법
    infix 키워드로 마크된 함수는 infix 표기법을 사용하여 불려질 수 있다.
    (그 호출에 대하 소괄호와 점을 생략하고)
    infix함수는 반드시 다음 요구를 충족해야한다.

    - 그들은 멤버함수이거나 확장함수여야한다.
    - 그들은 반드시 하나의 파라미터를 가져야한다.
    - 그 파라미터는 만드시 arguments의 다양한 수를 지원하면 안되고, 그리고 반드시 기본값이 없어야한다.

    infix fun Int.shl(x: Int): Int { ... }

    // calling the funtion using the infix notation
    1 shl 2

    // is the same as
    1.shl(2)


    // 중위 함수 호출은 산술연산자, 타입 캐스트, 그리고 범위 연산자보다 우선순위가 낫다.
    그 표현식은 다음과 동등하다.

    - 1 shl 2 + 3은 1 shl (2 + 3)
    - 0 until n * 2은 0 until (n * 2)
    - xs union ys as Set<*>은 xs union (ys as Set<*>)

    반면에, 중위함수 호출의 우선순위는 불린 연산 && ||, is, in, 그리고 몇몇 오퍼레이터 보다 높다.
    이 표현식은 다음과 같다.

    - a && b xor c는 a && (b xor c)
    - a xor b in c는 (a xor b) in c


    주의하라, 중위함수는 항상 리시버와 구체화된 파라미터를 필요로 한다.
    너가 중위 표기식을 사용하여 현재 리시버에서 메서드를 호출 할때,
    this를 명시적으로 사용하라.
    이것은 명확한 파싱을 확실히 하기위해서 필요로한다.

    class MyStringCollection {
        infix fun add(s: String) { ... }

        fun build() {
            this add "abc" // Correct
            add("abc") // Correct
            // add "abc" // Incorrect: the receiver must be specified
        }
    }




    // =================
    // Functions scope
    // =================

    코틀린 함수는 파일의 최상위에 선언 될 수 있다,
    그 자바, C#, Scala같은 언어처럼 함수를 잡아 놓기 위해서,
    클래스를 생성할 필요가 없다는 것을 의미한다

    탑레벨 함수에 더해서,
    코틀린 함수는 멤버함수로서 로컬로 선언될 수 있고, 확장함수로 될 수 있다.

    // Local funtions
    코틀린은 로컬 함수를 지원한다, 다른 함수의 내부에 있는 함수.

    fun dfs(graph: Graph) {
        fun dfs(current: Vertex, visited: MutableSet<Vertex>) {
            if (!visited.add(current)) return
            for (v in current.neighbors)
                dfs(v, visited)
        }

        dfs(graph.vertices[0], HashSet())
    }

    로컬 함수는 또한 바깥 함수의 로컬 값을 접근 할 수 있다.
    위의 경우에, visited는 로컬함수가 될 수 있다.

    fun dfs(graph: Graph) {
        val visited = HashSet<Vertex>()

        fun dfs(current: Vertex) {
            if(!visited.add(current)) return
            for (v in current.neighbors)
                dfs(V)
        }

        dfs(graph.vertices[0])
    }


    // Member functions
    멤버 함수는 클래스나 객체 내부에 정의된 함수이다.

    class Sample {
        fun foo() { print("Foo") }
    }

    멤버 함수는 점 표기법으로 불려진다.

    Sample().foo() // create instance of class Sample and calls foo




    // ==================
    // Generic functions
    // ==================
    함수는 제너릭 파라미터를 가질 수 있다,
    그리고 그것은 함수 이름 이전에  <>을 사용하여 구체화 될 수 있다.

    fun <T> singletonList(item: T): List<T> { ... }




    // ========================================
    // Tail recursive functions - 후행 재귀 함수.
    // ========================================

    코틀린은 tail recursion으로 알려진 함수적 프로그래밍의 스타일을 지원한다.
    몇몇 알고리즘에 대해서 일반적으로 루프를 사용할 것이다,
    너는 스택오버플로우의 위험없이, recursive funtion을 사용 할 수 있다.
    함수가 tailrec modifier로 마크 될 때, 그리고 일반적인 조건식을 충족 할 때,
    그 컴파일러는 그 재귀를 최적화하여, 빠르고 효율적인 루프기반 버전을 남긴다.

    val eps = 1E-10 // "good enough", could be 10^-15

    tailrec fun findfixPoint(x: Double = 1.0): Double =
        if (Math.abs(x - Math.cos(x)) < eps) x else findFixPoint(Math.cos(x))

    이 코드는 코사인의 fixpoint를 계산한다, 그리고 그것은 수학적인 상수이다.
    그것은 간단히 Math.cos로 반복적으로 호출된다, 1.0부터 시작하여, 결과가 더 이상 변경되지 않을 때까지
    지정된 eps 정밀도에 대해 0.7390851332151611의 결과를 산출합니다.
    그 결과 코드는 아래의 전통적 스타일과 동등하다.

    val eps = 1E-10 // "good enough", could be 10^-15

    private fun findFixPoint(): Double {
        var x = 1.0
        while (true) {
            val y = Math.cos(x)
            if (Math.abs(x - y) < eps) return x
            x = Math.cos(X)
        }
    }

    trailrec modifier를 사용하려면,
    함수는 반드시 그것 스스로 호출해야한다, 함수는 수행하는 마지막 작업으로 자신을 호출해야한다.
    너는 사용 할수 없다, tail recursion을 재귀호출이후에 코드가 더 있을 때,
    그리고, try, catch, finally내부에, 그리고 open함수에,
    tail recursion은 현재 JVM, Koltlin/Native에 대해서 코틀린이 지원한다.
 */




open class A {
    open fun foo(i: Int = 15) { print(i) }
}

class B : A() {
    override fun foo(i: Int) { print(i) } // No default value is allowed.
}


fun main() {

    fun foo(
        bar: Int = 0,
        baz: Int = 1,
        qux: () -> Unit,
    ) {
        print(bar)
        print(baz)
        qux()
    }

    foo(1,2, { println("Hello") })
    foo(1) { println("hello") } // Use the default value baz = 1, bar가 1로 들어감.
    foo(qux = { println("hello") }) // Use both default values bar = 0, and baz = 1
    foo { println("hello") } // Use both default values bar 0, and baz = 1



    fun printHello(name: String?): Unit {
        if (name != null)
            println("Hello $name")
        else
            println("Hi there!")

        // 'return Unit' or 'return' is optional
    }


    var printHello = printHello("asdasd")
    println(printHello)
}



















