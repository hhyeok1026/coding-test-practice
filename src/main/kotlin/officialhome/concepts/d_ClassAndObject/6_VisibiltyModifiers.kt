package officialhome.concepts.d_ClassAndObject

/*
    Visibility modifiers

    클래스, 객체, 인터페이스, 생성자, 함수, 프로퍼티, 그들의 세터까지도
    visibility modifier를 가질 수 있다.

    getter는 늘 같은 visibility를 가진다.


    4가지의 visibility modifier를 가진다.
    - private
    - protected
    - internal
    - public

    기본 visibility는 public이다.

    이 페이지에서, 너는 어떻게 수정자가 선언 범위의 다양한 종류에 적용되는지를 배운다.



    // ============
    // Packages
    // ============

    함수, 프로퍼티, 클래스, 객체, 그리고 인터페이스는 패키지 내부에, top-level 직접적으로 선언될 수 있다.

    //file name: example.kt
    package foo

    fun baz() { ... }
    class Bar { ... }

    - 만약 너가, visibility modifier를 쓰지 않는다면, public이 기본으로 사용된다.
    그리고 그것은 너의 선언이 어디서나 볼 수 있다는 의미다.
    - private로 선언하면, 그것은 오직 그 선언을 포함하는 파일 내부에서만 볼 수 있다.
    - interal로 선언하면, 그것은 같은 모듈내에서 볼 수 있다.
    - protected은 top-level 선언에 사용불가하다. -> 지금 패키지, 최상위 선언에 대해서 얘기 하는거라서 설명이 여기는 이렇게 되어있는듯.

    다른 패키지에서 보이는 최상이 선언을 사용하려면, import를 해야함.


    //file name: example.kt
    package foo

    private fun foo() { ... } // visible inside example.kt

    public var bar: Int = 5 // property is visible everywhere
        private set // setter is visible only in example.kt

    interal val baz = 6 // visible inside the same module



    // ========================
    // class members
    // ========================

    class 내부에 선언된 멤버에 대하여,

    private : 오직 클래스내에서 보이는 멤버를 의미한다.
    protected : private과 같은 visibilty, 하지만 subclass에서도 볼 수 있다.
    internal : 이 선언된 클래스를 보는 모듈내에서 어떤 클라이언트든 interanl의 멤버를 볼 수 있다.
    public : 선언된 클래스를 보는 어떤 클라이언트든 이 public 멤버를 볼 수 있다.

    코틀린에서, 아우터 클래스는 그것의 이너클래스의 private멤버를 볼 수 없다.

    만약 너가 protected나 interanl을 오버라이드 했는데,
    아무 수정자를 안쓰면,
    origin과 같은 visibility를 같게 된다.


    open class Outer {
        private val a = 1
        protected open val b = 2
        interal open val c = 3
        val d = 4 // public by default

        protected class Nested {
            public val e: Int = 5
        }
    }

    class Subclass : Outer() {
        // a is not visible
        // b, c is visible
        // Nested and e are visible

        override val b = 5 // 'b' is protected
        override val c = 7 // 'c' is internal
    }

    class Unrelated(o: Outer) {
        // o.a, o.b not visible
        // o.c and o.d are visible (same module)
        // Outer.Nested is not visible, and Nested::e is not visible either
    }


    // Constructors
    다음 구문을 사용하세여
    constructor 키워드를 써야 사용할 수 있습니다.

    class C private constructor(a: Int) { ... }

    기본적으로 모든 constructor는 public이다. (효율적으로 쓰려고..)


    // Local declarations
    로컬 변수, 함수, 클래스는 가시성 수정자를 가질 수 없다.




    // ========================
    // Modules
    // ========================

    internal은 같은 모듈내에서 볼 수 있다.
    다시 말해서, 모듈은 코틀린파일의 컴파일된 한 셋트이다.

    예를 들어,
    - 인텔리제이의 모듈
    - 메이븐 프로젝트
    - 그래들 소스셋 (test 소스셋이 내부선언에 엑세스할 수 있다는것은 제외)
    - <kotlinc> Ant 태스크를 한 번 호출하여 컴파일된 파일 세트
 */
