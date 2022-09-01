package officialhome.concepts.g_Equality

/*
    Equality

    코틀린에서 Equality이 두가지 타입이 있다.

    - 구조적 equality ( == - a check for equals())
    - referential equality ( === - 두 레퍼런스 포인트를, 같은 객체에서)




    // =====================
    // Structural equality
    // =====================

    구조적 평등은 == 연산자로 체크된다,
    그리고 그것의 부정대응 =!으로,
    컨벤션에 의해, a == b 같은 식은 다음처럼 해석된다.

    a?.equals(b) ?: (b === null)

    만약 a가 null이 아니면,
    그것은 equals(Any?)함수를 호출한다.
    그렇지 않으면, (a는 널이면)
    그것은 체크한다, b가 null에 대해서 참조적 평등인지.

    주의하라, 너의 코드를 최적화에 주요점이 아니다, null로 명확하게 비교할때.
    a == null은 자동적으로 해석된다. a === null

    커스텀 평등 확인구현을 제공하기위해서,
    equals(other: Any?): Boolean을 오러바이드해라.
    같은 이름과 같은 시그니처 함수, equals(other: Foo)는
    ==, != 연산자가 있는 평등 체크에 영향을 주지 않는다.

    구조적 평등은 Compare<...> 인터페이스에 의해 정의된 비교를 하는것을
    가지지 않는다,
    그래서 오직 커스텀 equals(Any?)구현이 연산자의 행동에 영향이 있다.




    // =====================
    // Referential equality
    // =====================

    참조평등은 === 연산자에 의해서 체크된다, 그리고 그것의 부정대응은 !==이다.
    a === b 는 평가한다, true인지, 그리고 만약 a and b가 같은 객체를 가르키는지.
    예를들어, Int로 런타임에 원시타입으로 나타나는 값에 대해서,
    그 === 평등 체크는 == 체크와 동일하다.




    // ================================
    // Floating-point numbers equality
    // ================================

    평등체크 피연산자가 Float or Double로 알려질때,
    그 체크는 Floating-Pint를 위한 IEEE754 스탠다드를 따른다.
    ( https://en.wikipedia.org/wiki/IEEE_754 )

    그렇지 않으면,
    그 구조적 평등은 사용된다, 표준과 일치하지 않도록.
    NaN이 자체와 같고, -0.0이 0.0과 같지 않도록

    See: Floating-point numbers comparison.
    https://kotlinlang.org/docs/numbers.html#floating-point-numbers-comparison



 */































