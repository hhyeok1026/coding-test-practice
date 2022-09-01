package officialhome.concepts.d_ClassAndObject

/*
    Inheritance

    Any 라는 클래스가 최고 조상임.

    그리고 supertype이 없는 클래스는 기본으로 Any임(Any는 생략되지만)
    class Example // Implicitly inherits from Any


    Any는 3가지 메서드, equals(), hashCode(), toString()이 있다,
    그래서 이 세 메서드는 모든 클래스에 정의된다.


    기본적으로 코틀린 클래스는 final이다.
    그들은 상속 될 수 없다.
    상속할 수 있는 클래스를 만드려면 open키워드를 써야한다.

    open class Base // Class is open for inheritance


    명시적으로 수퍼 타입을 정하기 위해서, 클래스 헤더의 뒤에 콜론으로 타입을 놓아라.

    open class Base(P: Int)

    class Derived(p: Int) : Base(p)

    파생된 클래스가 기본 생성자를 가진다면,
    base클래스는 파생클래스의 기본생성자를 따라서 초기화 될 수도 있다.


    기본생성자가 없다면,
    second constructor에 super키워드를 붙여서 할 수 있다.
    다른 second constructor는 base타입의 다른 생성자를 호출할 수 있다.



    // =====================
    // Overriding methods
    // =====================

    open class Shape {
        open fun draw() { ... }
        fun fill() { ... }
    }

    class Circle() : Shape() {
        override fun draw() { ... }
    }

    open class Rectangle() : Shape() {
        // override 키워드는 알아서 open이 되는데, 이것을 방지하려면, fianl키워드를 사용해야함.
        final override fun draw() { ... }
    }



    // ======================
    // Overriding properties
    // ======================
    open class Shape {
        open val vertexCount: Int = 0
    }

    class Rectangle : Shape() {
        override val vertexCount = 4
    }

    메서드와 같은 메커니즘을 가짐.
    반드시 호환되는 타입이여야한다는데..?

    val을 var로 override할 수 는 있는데, 반대로는 불가능.-> val은 필수적으로get메서드를 만들고, var는 set메서드를 만들기 때문이라는데.


    -------------


    interface Shape {
        val vertexCount: Int
    }

    class Rectangle(override val vertexCount: Int = 4) : Shape // Always has 4 vertices

    class Polygon : Shape {
        override var vertexCount: Int = 0  // Can be set to any number later
    }


    // 기본 생성자 내에도 override키워드를 사용 할 수 있음.



    // ===================================
    // Derived class initialization order
    // ===================================

    base클래스 부터 먼저 초기화 된다.
    -> 이것 때문에 base클래스 설계할 때는 open member를 넣는거를 기본생성자 안에  안되고,
    property초기화도 안되고, init 블록에도 안된다는데..?

    뭔 소린지 모르겠네



    // ===================================
    // Calling the superclass implementation
    // ===================================

    super키워드를 이용해서 조상꺼를 쓸 수 있다.


    open class Rectangle {
        open fun draw() { println("Drawing a rectangle") }
        val borderColor: String get() = "black"
    }

    class FilledRectangle: Rectangle() {
        override fun draw() {
            val filler = Filler()
            filler.drawAndFill()
        }

        inner class Filler {
            fun fill() { println("Filling") }
            fun drawAndFill() {
                super@FilledRectangle.draw() // Calls Rectangle's implementation of draw()
                fill()
                println("Drawn a filled rectangle with color ${super@FilledRectangle.borderColor}") // Uses Rectangle's implementation of borderColor's get()
            }
        }
    }

    fun main() {
        val fr = FilledRectangle()
            fr.draw()
    }



    // ===================================
    // Overriding rules
    // ===================================

    상속이 여러개 있는데,
    이중에서 메서드 하나가 상속이 여러개 걸려있으면 오버라이드 해서 써야하고, 자체구현을 제공해야한다? -> 모호성을 제거하기 위해서.

    super에 꺾쇠를 이용해서 어느 부모 꺼인지 명시할 수 있다.
    super<Base>

    open class Rectangle {
        open fun draw() { /* ... */ }
    }

    interface Polygon {
        fun draw() { /* ... */ } // interface members are 'open' by default
    }

    class Square() : Rectangle(), Polygon {
        // The compiler requires draw() to be overridden:
        override fun draw() {
            super<Rectangle>.draw() // call to Rectangle.draw()
            super<Polygon>.draw() // call to Polygon.draw()
        }
    }





*/


open class Base(val name: String) {

    init { println("Initializing a base class") }

    open val size: Int =
        name.length.also { println("Initializing size in the base class: $it") }
}

class Derived(
    name: String,
    val lastName: String,
) : Base(name.replaceFirstChar { it.uppercase() }.also { println("Argument for the base class: $it") }) {

    init { println("Initializing a derived class") }

    override val size: Int =
        (super.size + lastName.length).also { println("Initializing size in the derived class: $it") }
}

fun main() {
    println("Constructing the derived class(\"hello\", \"world\")")
    Derived("hello", "world")
}