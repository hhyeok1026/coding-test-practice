package officialhome.concepts.d_ClassAndObject

/*
    // Enum classes (이뉴머레이션 클래스)

    이넘 클래스에 대한 가장 기초적인 사용은
    type-safe enums의 구현이다.

    enum class Direction {
        NORTH, SOUTH, WEST, EAST
    }

    각각의 이넘 상수는 객체이다. 이넘 상수는 콤마로 분리된다.

    각 이넘은 이넘클래스의 인스턴스 이기때문에,
    그것은 초기화 될 수 있다.

    eunum class Color(val rgb: Int) {
        RED(0xFF0000),
        GREEN(0x00FF00),
        BLUE(0x0000FF)
    }




    // ==========================
    // Anonymous classes
    // ==========================

    이넘 상수는 그들의 메서드오 함께, 그들의 익명클래스로 선언 될 수 있다.
    base methods 오버라이딩도 가능.

    enum class ProtocolState {
        WAITING {
            override fun signal() = TALKING
        },
        TALKING {
            override fun signal() = WAITING
        };

        abstract fun signal(): ProtocolState
    }


    만약 이넘클래스가 어떤 멤버로 정의 되면,
    세미콜론으로 멤버정의으로 부터 상수 정의을 분리해라.




    // =======================================
    // Implementing interfaces in enum classes
    // =======================================

    이넘 클래스는 인터페이스를 구현 할 수 있다. (그러나 그것은 클래스로부터 파생된게 아니다)
    모든 엔트리로부터 인터페이스 멤버의 공통구현 또한 제공한다.
    또는 그것의 익명클래스이내의 각각 엔트리에 대한 구현을 분리한다.
    이것은 다음을 따라 이넘 클래스 선언에 구현을 원하는 인터페이스를 추가함으로 된다.

    enum class IntArithmetics : BinaryOperator<Int>, IntBinaryOperator {
        PLUS {
            override fun apply(t: Int, u: Int): Int = t + u
        },
        TIMES {
            override fun apply(t: Int, u: Int): Int = t * u
        };

        override fun applyASInt(t: Int, u: Int) = apply(t, u)
    }

    모든 이넘클래스는 기본으로 Comparable(https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-comparable/) 인터페이스로 구현한다.
    이넘 클래스안의 상수는 자연순서로 정의 된다. 더 정보를 원하면 Ordering(https://kotlinlang.org/docs/collection-ordering.html)을 보라.




    // ===============
    // 이넘 상수로 작동
    // ===============

    코틀린 안의 이넘 클래스는 synthetic (합성) 함수를 가진다.
    정의된 이넘 상수를 리스팅하고, 그것의 이름의 이넘상수를 가져오는.
    이 메서드의 시그니처는 다음과 같다. (이넘 클래스의 이름을 EnumClass로 가정한다.)

    EnumClass.valueOf(value: String): EnumClass
    EnumClass.vlaues(): Array<EnumClass>

    valueOf() 메서드는 IllegalArgumentException을 던진다,
    만약 구체화된 이름이 클래스안의 정의된 이넘 상수의 어떤것과 매치되지 않는다면.

    너는 enumValues<T>()와 enumValueOf<T>() 함수를 사용하는 제너릭 방법으로
    이넘 클래스안에 상수에 접근 할 수 있다.


    enum class RGB { RED, GREEN, BLUE }

    inline fun <reified T : Enum<T>> printAllValues() {
        print(enumValues<T>().joinToString { it.name })
    }

    모든 이늄 상수는 이넘 클래스 선언안에서 이름과 위치(0 스타트)를 얻기위한 프로퍼티를 가진다.

    val name: String
    val ordinal: Int





 */




















