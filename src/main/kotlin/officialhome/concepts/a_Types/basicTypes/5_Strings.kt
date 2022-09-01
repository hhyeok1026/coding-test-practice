package officialhome.concepts.a_Types.basicTypes

/*
    String

    키워드 String
    쌍따옴표로 값을 나타냄 ""

    val str = "abcd 123"

    String의 요소는 char이다. index를 이용해서 접근 할 수 있음.
    s[i]
    for 루프를 해서 반복 할 수 있다.

    for (c in str) {
        println(c)
    }

    String은 immutable불변이다.
    한 번 초기화 하면, 값을 변경하거나 새로운 값을 할당 할 수 없음.
    문자열 변환하면, 모든 작업은 새 String객체로 반환됨. 원래 문자열은 변경 되지 않은 상태로 유지.

    val str = "abcd"
    println(str.uppercase()) // Create and print a new String object
    println(str) // The original string remains the same


    // 문자열 연결에 + 연산자를 사용, 다른 타입과도 연결가능함.
    -> 문자열 연결에는 문자열 템플릿이나, 원시 String을 쓰는게 좋다.



    //=====================
    // String literals
    //=====================

    코틀린에는 String에서 2가지 리터럴 타입이 있다.

    - Escaped strings
    - Raw strings


    // Escaped strings는 이스케이프 문자열을 포함 될 수 있다.
    val s = "Hello, world!\n"

    // Raw strings 는 원시문자열인데, 줄바꿈과 임의의 텍스트가 포함 될 수 있다.
    삼중 따옴표를 사용한다, 이스케이프 포함하지 않으며, 줄바꿈 및 기타문자를 포함 할 수 있다.

    val text = """
    for (c in "foo")
        print(c)
    """

    val text2 = """
    |Tell me and I forget.
    |Teach me and I remember.
    |Involve me and I learn.
    |(Benjamin Franklin)
    """.trimMargin()

    trimMargin() 함수를 이용하여 앞의 여백을 없앨 수 있음.
    | 파이프 기호는 기본 여백 접두사로 사용되지만, 다른 문자를 선택할 수 있다. trimMargin(">") 처럼.



    //=====================
    // String templates
    //=====================

    $를 이용하여 사용한다.

    val i = 10
    println("i = $i") // Prints "i = 10"

    또는, 중괄호를 이용.

    val s = "abc"
    println("$s.length is ${s.length}") // Prints "abc.length is 3"

    // raw String에서도 사용가능.
    val price = """
    ${'$'}_9.99
    """


*/

fun main() {

    val text = """
for (c in "foo")
    print(c)
    """

    println(text)

    val text2 = """
    |Tell me and I forget.
    |Teach me and I remember.
    |Involve me and I learn.
    >(Benjamin Franklin)
    """.trimMargin(">")

    println(text2)

    val price = """
    |${'$'}_9.99
    """.trimMargin()

    println(price)
}