package officialhome.concepts.f_NullSafety

/*
    Null safety

    // Nullable type and non-null types

    코틀린의 타입 시스템은 널 참조의 위험을 제거하는것에 중점을 둔다,
    또한 Billion Dollar Mistake로 알려진,

    자바를 포함한,
    많은 프로그래밍 언어의 대부분의 함정은
    널 참조의 멤버에 접근하면 널참조 예외가 발생한다는 것이다.

    자바에서 이것은
    NullPointerException, or NPE이와 동일하다.

    코틀린에서 NPE의 원인은 다음과 같다
    - 명백한 throw NullPointerException()의 호출
    - 아래 설명된 !! 연산자의 사용
    - 데이터의 불일치, 초기화와 관련하여, 다음 같을 때:
        - 생성자에서 초기화 되지 않은 this는 전달되고, 사용된다 어디선가 (leaking this)
        - superclass 생성자는 open member를 호출한다, 파생된 클래스가 초기화되지 않은 상태 사용에서에서 구현한

    - 자바 상호운용
        - 시도하는것, platform type의 널 레퍼런스의 멈베서 접근을.
        - generic type의 널허용 문제가 자바 상호운용에서 사용되는 것.
            예를 들어서, 자바코드의 조각은 null을 더 할 수 있다, 코틀린 MutableList<String>에서,
            그러므로 MutableList<String?>이 요구 된다, 그것을 작동하기 위해서
        - 외부 자바 코드가 일으키는 다른 문제.

    코틀린에서, type 시스템은 참조를 구별한다, null이 올 수 있는것과, 널이 올 수 없는것에대해서
    예를 들어, String type의 일반변수는 null일 수 없다.

    var a: String = "abc" // Regular initialization means non-null by default
    a = null // compilation error

    널을 허용하기위해서,
    너는 String?를 써서 널이 올 수 있는 변수를 선언할 수 있다.

    var b: String? = "abc" // can be set to null
    b = null // ok
    print(b)

    이제, 만약 너가 a 프로퍼티에서 메서드나 접근을 호출 할 수 있다.
    그것은 NPE을 발생하지 않는 것을 보증한다.
    너는 안전하다고 말 할 수 있다.

    val l = a.length

    그러나 만약 너가 b 같은 프로퍼티에 접근을 원한다면,
    그것은 안전하지 않을 수 있다, 그리고 컴파일러는 에러를 일으킨다.

    val = b.length // error: variable 'b' can be null

    그러나 너는 여전히 프로퍼티에 접근할 수 있다. 여기 그렇게 하는 몇가지 방법이 있다.



    // ================================
    // Checking for null in conditions
    // ================================

    우선, 너는 명확하게 b가 널인지 체크할 수 있다.
    그리고 두 옵션을 분리하여 다룰 수 있다.

    val l = if (b != null) b.length else -1

    그 컴파일러는 너가 수행한 확인에 대한 정보를 추적한다,
    그리고 그 호출을 if내부에서 length하게 한다.
    더 복잡한 컨디션도 잘 지원된다.

    val b: String? = "Kotlin"
    if (b != null && b.length > 0) {
        print("String of length ${b.length}")
    } else {
        print("Empty string")
    }

    주의하라, 이것은 b가 불변일 때 작동한다,
    ( 그 체크와 그것의 사용에서 수정되지 않은  지역변수라는것을 의미한다, 또는
    그것이 val가 backing field를 가지고, 재정의 할 수 없는 멤버를),
    그렇지 않으면 그것은 경우가 될 수 있다, b가 확인 이후에 null로 변하는.




    // ============
    // Safe calls
    // ============

    너의 널 변수에 접근하느 프로퍼티를 위한 두번째 옵션은,
    safe call operator ?. 를 사용하는 것이다.

    val a = "Kotlin"
    val b: String? = null
    println(b?.length)
    println(a?.length) // Unnecessary safe call

    이것은 리턴한다, b.length를, 만약 b가 null이 아니면,
    그리고 그렇지 않으면 null을 반환한다.
    이 표현식의 타입은 Int?이다.

    safe call은 체인에서 유용하다.
    예를들어,
    밥은 직원이다, 부서에 할당되어 있거나, 그맇지 않을 수도 있다.
    그 부서는 차례로 다른 직원을 가질 수 있다, 부서의 장으로서
    밥의 부서장의 이름을 없기위해(만약 있다면),
    너는 다음과 같이 쓴다.

    bob?.dapartment?.head?.name

    이런 체인은 null을 리턴한다, 만약 그 프로퍼티내의 어떤게 null이라면

    non-null value에 대해서 오퍼레이션을 수행하기 위해서,
    너는 let과 함께 safe call을 사용 할 수 있다.

    val listWithNulls: List<String?> = listOf("Kotlin", null)
    for (item in listWithNulls) {
        item?.let { println(it) } // prints Kotlin and ignores null
    }

    safe cakk은 또한 할당의 왼쪽에 놓인다.
    그때, 그 safe call에서 리시버중 하나가 null이면,
    그 할당흔 스킵되고, 그 표현식, 우측편은 모두 평가되지 않는다.

    // If either 'person' or 'person.department' is null, the function is not call
    person?.department?.head = managersPool.getManager




    // ================
    // Elvis operator
    // ================

    너가 널이 올 수 있는 레퍼런스를 가질때, b,
    너는 if b가 널이 아니면, 그것을 사용한다고 말 할 수 있다.
    그렇지 않으면 null이 아닌값을 사용.

    val l: Int = if (b != null) b.length else -1

    완전한 if표현식을 사용하는 대신에,
    너는 또한 엘비스 연산자를 사용 할 수 있다.

    val l = b?.length ?: -1

    만약 왼쪽식이 널이 아니라면, 엘비스 연산자는 그것을 리턴한다,
    그렇지 않으면 그것은 우측편을 리턴한다.
    주의하라. 우측식은 오직 왼쪽편이 null일때만 평가된다.

    throw와 return은 코틀린에서 표현식이기 때문에,
    그들은 또한 사용된다, 엘비스 연산자의 우측편에서
    이것은 손쉽다, 예를들어 함수 인수를 체크할때.

    fun foo(node: Node): String? {
        val parent = node.getParent() ?: return null
        val name = node.getName() ?: throw IllegalArgumentException("name expected")
    }




    // ================
    // The !! operator
    // ================

    세번째 옵션은 NPE-lover를 위한것이다. not-null assertion operator !!는 변경한다, 어떤 값을
    non-null타입으로, 그리고 예외를 던진다, 그 값이 null이면
    너는 쓸 수 있다 b!!, 그리고 이것은 리턴한다, b의 non-null 값을.
    (예를 들어, 우리 예제의 String값을) 또는 b가 null이면 NPE를 던진다.

    val l = b!!.length

    그래서, 만약 너가 NPE를 원한다면, 너는 그것을 가질 수 있다,
    그러나 너는 그것을 명백하게 물어야한다, 그것이 갑자기 나타나지 않는다.




    // ================
    // Safe casts
    // ================

    일반적인 캐스트는 ClassCastException에서 결과를 낼 수 있다, 만약 객체가 target의 타입이 아니라면,
    다른 옵션은 safe cast를 사용하기 위한, 만약 시도가 성공하지 않으면 null을 리턴하는 것이다.

    val aInt: Int? = a as? Int




    // ===============================
    // Collections of a nullable type
    // ===============================

    만약 너가 널이 가능한 타입의 요소의 컬렉션을 가진다면,
    그리고 non-null요소를 필터하기 원한다면,
    너는 filterNotNull을 사용 할 수 있다.

    val nullableList: List<Int?> = listOf(1, 2, null, 4)
    val intList: List<Int> = nullableList.filterNotNull()





 */


































