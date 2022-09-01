package officialhome.concepts.d_ClassAndObject

/*
    Nested and inner classes

    클래스는 다른 클래스 안에 중첩 될 수 있다.


    class Outer {
        private val bar: Int = 1

        class Nested {
            fun foo() = 2
        }
    }

    val demo = Outer.Nested().foo() // == 2


    또한 중첩을 위해 interface를 슬 수 있다.

    클래스와 인터페이스 결합의 모든것은 가능하다.

    너는 클래스 안에 인터페이스, 인터페이스 안에 클래스, 인터페이스 안에 인터페이스를 중첩 할 수 있다.

    interface OuterInterface {
        class InnerClass
        interface InnerInterface
    }

    class OuterClass {
        class InnerClass
        interface InnerInterface
    }



    // ========================
    // Inner class
    // ========================

    inner로 마크 된 중첩된 클래스는 바깥 클래스의 멤버에 접근 할 수 있다.
    이너 클래스는 바깥 클래스의 객체에 대한 참조를 전달한다.

    class Outer {
        private val bar: Int = 1
        inner class Inner {
            fun foo() = bar
        }
    }

    val demo = Outer().Inner().foo() // == 1

    이너 클래스의 this에 대한 명확성으 보려면
    qualified this 표현식에 대한 문서를 보라
    https://kotlinlang.org/docs/this-expressions.html




    // ========================
    // Anonymous inner classes
    // ========================

    익명 이너 클래스 인스턴스는 object expression을 사용하여 만들어진다.

    window.addMouseListener(object : MouseAdapter() {
        override fun mouseClicked(e: MouseEvent) { ... }
        override fun mouseEntered(e: MouseEvent) { ... }
    })



    (위에는 클래스 설명이였고, 아래는 인터페이스 설명인듯?)
    // JVM에서, 만약 객체가 함수적 자바인터페이스의 인스턴스이면,
    너는 인터페이스의 타입으로 prefixed된 람다표현식을 사용하여 만들 수 있다.
    val listener = ActionListener { println("clicked") }

 */























