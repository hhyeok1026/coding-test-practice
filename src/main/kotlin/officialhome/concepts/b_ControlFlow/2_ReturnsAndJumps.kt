package officialhome.concepts.b_ControlFlow

/*
    Returns and jumps


    코틀린은 3가지 구조적 점프 표현식이 있다.
    - return : 가장 가까운 함수나 익명함수로 부터 리턴한다.
    - break : 가장 가까운 루프를 종료시킨다.
    - continue : 근처의 루프의 다음단계로 진행하게 한다.

    이러한 모든 표현식은 더 큰 표현식의 일부로 사용 될 수 있다.
   val s - person.name ?: return

   이러한 표현식의 유형은 Noting type이다. -> 다음문서인 Exception에 설명 되어 있음.



    // =============================
    // Break and continue labels
    // =============================
    어떤 표현식을 라벨을 가지는데, 라베을 @로 표현됨. abc@, foobar@같이.
    표현식을 라벨하려면, 그거 앞에 그냥 더하면 된다.

    loop@ for (i in 1..100) {
        // ...
    }

    이제 break와 continue를 라벨로 함께 쓸 수 있다.
    loop@ for (i in 1..100) {
        for (j in 1..100) {
            if (...) break@loop
        }
    }

    break는 레이블로 표시된 루프 바로 뒤의 실행 지점으로 점프하게 된다,
    continue로 쓰면, 계속해서 다음 반복으로 진행된다.



    // =============================
    // Break and continue labels
    // =============================

    함수는 함수리터럴, 로컬함수, 객체 표현식으로 중첩될 수 있다.
    return 키워드는 바깥함수로 리턴 할 수 있게한다.
    가장 중요점은 람다식으로 리턴하는것이다.

    우리가 다음을 작성할때, return 표현식은 가장 가까운 함수로 리턴된다는 것을 기억하라.


    주의해라, 비 로컬 리턴은 오직 인라인함수에 전달된 람다를 위해 지원 된다.
    람다식으로부터 리턴하기 위해 레이블 해라, 그리고 return을 써라.

    이제, 람다식에서 리턴 된다. 종종 더 편하다 암시적 레이블을 쓰는게,
    레이블은 람다를 전달한 함수와 같음 이름을 가진다.

    대안적으로 람다식을 익명 클래스로 변경할 수 있다.
    익명함수에서 리턴문은 익명함수 그 자체를 리턴 할 것이다.

    이전 예에서 지역 리턴의 사용은, 일반적인 루프에서 continue의 사용과 비슷하다.

    여기 break와 동등한것은 없다. 그러나 다른 중첩 람다에 더해지는것과, 그것으로 부터 비 지역적인 리턴을 시뮬레이트 할 수 있다.

    값을 반환할 때 파서는 정규화된 반환에 우선순위를 부혀한다.
    return@a 1
    이것은 레이블이 있는 표현식(@a 1)을 반환하는 것이 아니라, 레이블 @a에 1반환을 의미한다.
 */


fun foo4() {
    run loop@{
         listOf(1, 2, 3, 4, 5).forEach {
             if (it == 3) return@loop
                 print(it)
         }
        print(" done with nested loop")
    }
}

fun foo3() {
    listOf(1, 2, 3, 4, 5).forEach(fun(value: Int){
        if (value == 3) return //local return to the caller of the anonymous function - the forEach loop
        print(value)
    })
    print(" done with anonymous function")
}

fun foo2() {
    listOf(1, 2, 3, 4, 5).forEach {
        if (it == 3) return@forEach// local return to the caller of the lambda - the forEach loop
        print(it)
    }
    print(" done with explicit label")
}

fun foo1() {
    listOf(1, 2, 3, 4, 5).forEach lit@{
        if (it == 3) return@lit // local return to the caller of the lambda - the forEach loop
        print(it)
    }
    print(" done with explicit label")
}

fun foo() {
    listOf(1, 2, 3, 4, 5).forEach {
        if (it == 3) return // non-local return directly to the caller of foo()
        print("$it ")
    }
    println("this point is unreachable")
}

fun main() {
    foo4()
}