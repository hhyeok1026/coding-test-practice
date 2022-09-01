package officialhome.concepts.a_Types.basicTypes

/*
    Booleans -> true와 false를 표현하는 객체.


    // booleans를 위한 연산자.
    || – disjunction, 분리 (logical OR)
    && – conjunction, 접속사 (logical AND)
    ! – negation, 부정 (logical NOT)

    || and && work lazily.


    val myTrue: Boolean = true
    val myFalse: Boolean = false
    val boolNull: Boolean? = null

    println(myTrue || myFalse) -> true
    println(myTrue && myFalse) -> false
    println(!myTrue) -> false

    -> jvm에서 nullable한 boolean에 대한 참조는 number와 비슷하게, boxed된다.
*/