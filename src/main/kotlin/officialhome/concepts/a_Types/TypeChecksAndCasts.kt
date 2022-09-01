package officialhome.concepts.a_Types

/*
    Type checks and casts



    =========================
    // is and !is operatiors
    =========================
    // 객체가 주어진 타입을 수행하는지 확인하는 연산자.
    if (obj is String) {
        print(obj.length)
    }

    if (obj !is String) { // same as !(obj is String)
        print("Not a String")
    } else {
        print(obj.length)
    }



    =========================
    // Smart casts
    =========================

    대부분 자동으로 컴파일러가 캐스트 하기 때문에 명시적으로 할 일은 별로 없다.

    컴파일러가 검사와 사용 사이에 변수가 변경되지 않음을 보장할 경우에만, 스마트캐스트 할 수 있음.

    val local variables -> 늘 가능. local위임properties를 제외하고.
    val properties -> private나 interal 이나, 같은 모듈에서 수행되는경우 가능, open properties나 커스텀 getter를 가지면 할 수 없다.
    var local variable -> 검사나, 사용에서 수정되지 않으면 됨. 람다에서도 수정되지 않으면. 그리고 local delegated property가 아니라면 가능.
    var properties -> 안됨. 변수는 언제든 다른 코드에 의해 수정 될 수 있기 때문에.



    =========================
    // "Unsafe" cast operator
    =========================

    캐스팅 할 때 as 연산자를 쓰면 된다.
    일반적으로 cast 가능하지 않은 경우 예외를 발생한다.
    그래서 unsafe라고 한다.

    그냥 as를 쓸 때는,
    null타입인지를 좌우측 맞춰서 써야함.

    val x: String = y as String
    val x: String? = y as String?



    ===================================
    // "Safe" (nullable) cast operator
    ===================================

    예외를 피하려면 as? 를 써라, 실패시 null로 리턴한다.
    우측 타입이 nullable이 아닌경우에도 null로 리턴 될 거라서 쓸 수 있음.

    val x: String? = y as? String



    ===================================
    // Generics type checks and casts
    ===================================
    -> 제너릭 문서를 보세요!

*/

fun main() {

    val obj = "sldfjlsdjf"

    if (obj is String) {
        println(obj.length)
    }

    if (obj !is String) { // same as !(obj is String)
        println("Not a String")
    } else {
        println(obj.length)
    }
}

