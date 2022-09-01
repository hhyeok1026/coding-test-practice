package officialhome.concepts.a_Types.basicTypes

/*
    코틀린에서 Array는
    Array 클래스는 이용하여 나타낸다.

    get() set()메서드를 가진다, [] 연산자로 바꿀 수 있다.

    size property도 있음.

    // Array class
    class Array<T> private constructor() {
        val size: Int
        operator fun get(index: Int): T
        operator fun set(index: Int, value: T): Unit

        operator fun iterator(): Iterator<T>
        // ...
    }


    arrayOf() 함수를 이용하여 생성 할 수 있다.
    arrayOf(1, 2, 3)
    arrayOfNulls() - null요소를 포함 할 수 있는 메서드.

    다른 방법으로는 Array사이즈를 가지는 생성자와,
    인덱스가 지정된 배열 요소의 값을 반환하는 함수를 사용한다.
    fun main() {
        // Creates an Array<String> with values ["0", "1", "4", "9", "16"]
        val asc = Array(5) { i -> (i * i).toString() }
        asc.forEach { println(it) }
    }

    //코틀린 배열은 불변이다.


    //==========================
    // Primitive type arrays
    //==========================

    박싱 오버헤드 없이 기본원시타입의 배열도 있음.
    ByteArray, ShortArray, IntArray and so on..
    이런 것들은 Array클래스와 상속 관련이 없지만, 동일한 메서드와 프로퍼티의 셋을 가진다.
    그리고 각각 팩토리 function을 또한 가진다.

    // Array of int of size 5 with values [0, 0, 0, 0, 0]
    val arr = IntArray(5)

    // Example of initializing the values in the array with a constant
    // Array of int of size 5 with values [42, 42, 42, 42, 42]
    val arr = IntArray(5) { 42 }

    // Example of initializing the values in the array using a lambda
    // Array of int of size 5 with values [0, 1, 2, 3, 4] (values initialized to their index value)
    var arr = IntArray(5) { it * 1 }

 */


