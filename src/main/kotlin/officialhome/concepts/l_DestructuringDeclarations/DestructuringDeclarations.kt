package officialhome.concepts.l_DestructuringDeclarations




/*
    Destructuring declarations

    때때로 그것은 편리하다,
    객체를 분해라는것 변수의 수로,
    다음 예를 보라.

    val (name, age) = person

    이 문맥은 구조분해라고 불린다.
    그 구조적 분해는 다양한 변수를 한번에 만든다.
    너는 두 새로운 변수를 선언해야한다. 이름과 나이를,
    그리고 그것을 독립적으로 사용 할 수 있다.

    println(name)
    println(age)

    구조분해를 하는것은 다음 코드로 컴파일 된다.

    val name = person.component1()
    val age = person.component2()

    component1() 과 component2() 함수는 코틀린에서 사용되는 컨벤션의 원칙의 또 다른 예이다.
    (보라, + and *, for 루프를 예제로서)
    어떤것은 구조분해의 우측편에 사용 할 수 있다,
    컴포넌스 함수의 요구되는 수 만큼 그것이 불려지는, 물론
    component3() 과 component4() 그리고 계속 될 수 있다.

    그 componentN() 함수는 operator로 마크되는게 필요하다,
    그것들을 구조분해로 사용하기 위해.

    구조분해 선언은 또학 for문에서도 작동한다.

    for ((a, b) in collection) { ... }

    변수 a, b는 component1()과 component2()로, 컬렉션의 요소에서 호출된




    // ========================================
    // Example
    // : returning two values from a function
    // ========================================
    함수로부터 두 값을 리턴하는것. 데이터 클래스를 만들어서 리턴하기.

    함수로부터 두 값을 리턴하는것을 가정하라.
    예를들어, 결과 객체와 어떤 정렬의 상태를.
    이것을 하는 간결한 방법은 data class를 선언하는것이다.
    그리고 그것의 인스턴스를 리턴하는것이다.

    data class Result(val result: Int, val status: Status)

    fun function(...): Result {

        // computations

        return Result(result, status)
    }

    // Now, to use this function:
    val (result, status) = function(...)


    데이터 클래스는 자동적으르 componentN() 함수로 선언되기 때문에,
    구조적 선언은 여기서 작동한다.

    // 너는 또한 스탠다는 클래스 Pari를 선언해야한다, 그리고 함수를 가져야한다,
    Pair<Int, Status>를 리턴하는,
    그러나 그것은 종종 너의 데이터를 이름을 적절하게 하는것이.




    // ========================================
    // Example
    // : destructuring declarations and maps
    // ========================================

    구조분해선언과 맵

    아마도 가장좋은 방법은, 맵을 여행하는. 이거다

    for ((key, value) in map) {
        // do something with the key and the value
    }

    이 작업을 만들기 위해서, 너는 해야한다.

    - iterator() 함수를 제공함으로써, 값의 시퀀스로서 맵을 표시한다.

    - component1()과 component2() 함수를 제공함으로써 pair로서 엘리먼트의 각각을 표시한다.

    실제로, 그 스탠다드 라이브러리는 제공하다 확장 같은.

    operator fun <K, V> Map<K, V>.iterator(): Iterator<Map.Entry<K, V>> = entrySet().iterator()
    operator fun <K, V> Map.Entry<K, V>.component1() = getKey()
    operator fun <K, V> Map.Entry<K, V>.component2() = getValue()

    그래서 너는 구조분해 선언을 자유롭게 사용 할 수 있다, for문에서, 맵과 함께
    ( 데이터 클래스의 컬렉션 뿐만아니라 유사한거도)




    // =================================
    // Underscore for unused variables
    // =================================

    만약 너가 구조분해선언에서 변수가 필요없다면,
    너는 그것의 이름 대신에 언더스코어를 넣을 수 있다.

    val (_, status) = getResult()

    그 componentN() 연산자 함수는 이 방법에서 생략되는 컴포넌트에 대해서 호출하지 않는다.




    // =========================
    // Destructuring in lambdas
    // =========================

    람다에서 구조분해

    너는 할수 있다 분해선언 분법을 람다 파라미터를 위해서.
    만약 람다가 Pair type의 파라미터를 가지면. ( or Map.Entry, or 다른 타입, 적절한 componetN 함수를 가지는)
    그래서 너는 몇몇 새로운 파라미터를 도입 할 수 있다, 소괄호안에 그들을 넣는것 대신에

    map.mapValues { entry -> "${entry.value}!" }
    map.mapValues { (key, value) -> "$value!" }

    차이가 있다, 두파라미터를 선언하는것과 파라미터 대신에 분해 pair를 선언하는것.

    { a -> ... } // one parameter
    { a, b -> ... } // two parameters
    { (a, b) -> ... } // a destructured pair
    { (a, b), c -> ... } // a destructured pair and another parameter

    만약 분해된 파라미터의 컴포넌트가 사용되지 않았다면,
    너는 그것의 이름조사하는 것을 피하기 위해서, 언더스코어로 쓸 수 있다.

    map.mapValues { (_, value) -> "$value!" }

    너는 구체화 할 수 있다, 그 타입을, 전체 분해된 파라미터에 대해서
    또는 구체화된 분리된 컴포넌트에 대해서

    map.mapValues { (_, value): Map.Entry<Int, String> -> "$value!" }
    map.mapValues { (_, value: String) -> "$value!" }
 */















