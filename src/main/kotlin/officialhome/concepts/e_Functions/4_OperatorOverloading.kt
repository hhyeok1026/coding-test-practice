package officialhome.concepts.e_Functions

/*
    Operator overloading

    코틀린은 제공한다, 커스텀 구현을, 타입에 사전정의된 연산자의 집합을
    이 오퍼레이터는 사전정의되어 있다 심볼릭 표현으로 ( +, * 같은) 그리고
    (precedence)우선 순위가 있다.
    오퍼레이터를 구현하기 위해서, 멤버함수를 제공하거나, 확장함수를 제공하라
    그 타입에 일치하는 구체적인 이름과 함께.

    이 타입은 left-side type이 된다, 이항 연산자에 대해서, 그리고
    unary (단항)의 파라미터 타입으로.

    오퍼레이터를 오버로드 하기 위해서,
    일치하는 함수를 operator modifier로 마크해라

    interface Indexedcontainer {
        operator fun get(index: Int)
    }

    너의 오퍼레이터 오버로드가 오버라이딩 할 때,
    너는 operator를 생략 할 수 있다.

    class OrdersList: IndexedContainer {
        override fun get(index: Int) { ... }
    }




    // ==================
    // Unary operations
    // ==================

    // Unary prefix operators

    +a  a.unaryPlus()
    -a  a.unaryMinus()
    !a  a.not()

    이 테이블은 말한다, 컴파일러가 처리할때, 예를 들어,
    표현식 +a, 그것은 수행한다 다음 스텝을.

    - a의 타입을 결정한다, T가 되고
    - operator modifier와 함게 unaryPlus() 함수를 보라,
    receiver T에 대해서 파라미터가 없다, 그것은 멤버함수거나 확장함수를 의미한다.
    - 만약 그 함수가 없거나, 모호하면, 컴파일러는 에러를 발생한다.
    - 만약 그 함수가 존재하거나 그것의 리턴 타입이 R이면, 그 +a표현식은 R타입을 가진다.

    // 이 연산자들은 다른것 뿐만아니라, basic type에 최적화 된다, 그리고
    그들을 위한 함수호출의 오버헤드를 도입하지 마세요.

    예를 들어, 너는 unary minus operator를 오버로드 할 수 있다.

    data class Point(val x: Int, val y: Int)

    operaotr fun Point.unaryMinus() = Point(-x, -y)

    val point = Point(10, 20)

    fun main() {
        println(-point) // prints "Point(x=-10, y=-20)"
    }


    // Increments and decrements

    a++,  a.inc()
    a--,  a.dec()

    그 inc()와 dec() 함수는 반드시 값을 리턴한다, 그리고 그것은 변수에 할당된다,
    ++ , -- 연산자가 사용된 변수에, 그들은 변경되어서는 안된다, 객체가 inc or dec가 호출된.

    그 객체는 수행한다, postfix form안의 오퍼레이터의 해결에 대한 스텝을 따라.

    - a의 타입을 결정한다, T가 되면
    - 함수 inc()를 보라, operaotor modifier와 함께, 파라미터가 없다,
    타입 T의 리시버에 적용가능한한
    - T의 서브타입의 함수 리턴타입을 확인하라

    그 계산된 표현식은 영향은 :
    - 저장, a의 초기값을 임시저장 a0로.
    - 할당 a0.inc()의 결과를 a로.
    - 리턴, a0을 표현식의 결과로.

    a--스탭에 대해서 완전하게 유사하다.

    prefix 형식 ++a와 --a 해결방법과 동일하게 작동한다, 그리고 그 효과는:
    - 할당, a.inc()의 결과를 a로
    - 리턴, a의 새로운 값을 표현식의 결과로.




    // ==================
    // Binary operations
    // ==================

    // Arithmetic operaotr

    a + b,  a.plus(b)
    a - b,  a.minus(b)
    a * b,  a.times(b)
    a / b,  a.div(b)
    a % b,  a.rem(b)
    a..b,   a.rangeTo(b)

    이 테이블의 연산자에 대해서,
    컴파일러는 단지 Translated to 열의 표현식만 확인한다.

    아래는 Counter class의 예이다, 주어진 값과 증가될 수 있는 오버로드된 + 연산자로 시작한다.
    data class Counter(val dayIndex: Int) {
        operator fun plus(increment: Int): Counter {
            return Counter(dayIndex + increament)
        }
    }


    // in operator

    a in b,   b.contains(a)
    a !in b,  !b.contains(a)

    in과 !in 에 대해서 절차는 같지만, argument의 순서는 반대이다.


    // Indexed access operaor

    a[i],                  a.get(i)
    a[i, j],               a.get(i, j)
    a[i_1, ..., i_n]       a.get(i_1, ..., i_n)
    a[i] = b               a.set(i, b)
    a[i, j] = b            a.set(i, j, b)
    a[i_1, ..., i_n] = b   a.set(i_1, ..., i_n, b)

    대괄호는 get 과  set으로 호출되기 위해 변경된다, argument의 적절한 수와 함께.


    // invoke operator

    a(),                a.invoke()
    a(i),               a.invoke(i)
    a(i, j),            a.invoke(i,j)
    a(i_1, ..., i_n),   a.invoke(i_1, ..., i_n)

    소괄호는 invoke를 argument의 적절한 수로 호출하기 위해서 변경된다.


    // Augmented assignments (증강 할당)
    a += b  a.plusAssign(b)
    a -= b  a.minusAssign(b)
    a *= b  a.timesAssign(b)
    a /= b  a.divAssign(b)
    a %= b  a.remAssign(b)

    이 할당 연산자에 대해서, 예를들어 a += b, 컴파일러는 다음 단계로 수행한다.
    - 만약 함수가 오른쪽 컬럼으로부터 가능하다면,
        - 만약 그 일치하는 바이너리 함수 (그것은 plusAssign()에 대한 plus를 의미한다)도 사용할 수 있으면,
        a는 변하는 변수이다.
        그리고 plus의 리턴타입은 a의 타입의 서브타입이고, 에러(모호성)를 리포트한다.
        - 그것의 리턴타입이 Unit인것을 확인하고, 그리고 그렇지 않으면 에러를 리포트한다.
        - a.plusAssign(b)에 대해서 코드를 생성한다.

    - 그렇지 않으면, a = a + b에 대해서 코드를 생성하라. (이것은 타입체크를 포함한다, a + b의 타입은 반드시 a의 서브 타입이어야한다.

    // 할당은 코틀린에서 표현식이 아니다.


    // Equality and inequality operators

    a > b,      a.compareTo(b) > 0
    a < b,      a.compareTo(b) < 0
    a >= b,     a.compareTo(b) >= 0
    a <= b,     a.compareTo(b) <= 0

    모든 비교는 해석된다, compareTo에 호출로, 이것은 return Int 가 요구된다.


   // Property delegation operators
   provideDelegate, getValue, setValue 연산자 함수는 Delegated properties에서 설명되어있다.




    // =================================
    // Infix calls for named functions
    // =================================

    너는 중위함수 호출을 사용하여, 커스텀 infix 연산자를 시뮬레이트 할 수 있다.

 */
























