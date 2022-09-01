package officialhome.concepts.a_Types.basicTypes

/*
    ===============================
    // Unsigned integer types
    ===============================

    integer 외 추가적으로 unsigned 타입의 변수를 제공한다.

    UByte: an unsigned 8-bit integer, ranges from 0 to 255
    UShort: an unsigned 16-bit integer, ranges from 0 to 65535
    UInt: an unsigned 32-bit integer, ranges from 0 to 2^32 - 1
    ULong: an unsigned 64-bit integer, ranges from 0 to 2^64 - 1


    Unsigned 는
    singed class와 동일한 프로퍼티와 싱글 스토리지의 inline클래스로 구현되지만,
    이진 호환 되지 않는다고 한다.


    ===============================
    // Unsigned arrays and ranges
    ===============================

    UByteArray: an array of unsigned bytes
    UShortArray: an array of unsigned shorts
    UIntArray: an array of unsigned ints
    ULongArray: an array of unsigned longs

    -> 부호 있는 정수 배열과 같이 box오버헤드 없이 쓸 수 있다.

    unsigned 타입은 불안정하다고 한다.

    UInt, ULong에서 Range와 progressions가 제공되는데,
    UIntRange,UIntProgression, ULongRange, and ULongProgression 라는게 있음.
    이런 것들을 같이 써주면 안정적으로 쓸 수 있다함.



    =================================
    // Unsigned integers literals
    =================================

    리터럴의 크기에 따라, Unit 또는 ULong을 사용하게 된다함.

    val b: UByte = 1u  // UByte, expected type provided
    val s: UShort = 1u // UShort, expected type provided
    val l: ULong = 1u  // ULong, expected type provided

    val a1 = 42u // UInt: no expected type provided, constant fits in UInt
    val a2 = 0xFFFF_FFFF_FFFFu // ULong: no expected type provided, constant doesn't fit in UInt

    // 명시적으로 타입과, unsigned를 같이 써줄 수 있음.
    val a = 1UL // ULong, even though no expected type provided and constant fits into UInt



    ================
    // Use cases
    ================

    data class Color(val representation: UInt)
    val yellow = Color(0xFFCC00CCu)

    val byteOrderMarkUtf8 = ubyteArrayOf(0xEFu, 0xBBu, 0xBFu)


    // Non-goals
    -> 양수와 0을 표현하지만,
    컬렉션의 사이즈나 인덱스에서 쓰라고 만든건 아니다.

    오버플로우 및 신호조건 오류에서 -1을 감지하는데 도움이 될 수 있다.

    signed 와 unsigned는 서로 하위 집합의 개념이 아니다. 범위 제한 버전으로 취급 할 수 없다.


 */