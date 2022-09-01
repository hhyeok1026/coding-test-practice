package officialhome.concepts.h_ThisExpressions

/*
    This expressions

    current receiver를 나타내기 위해서, this expressions를 사용하라.

    - 클래스의 멤버에서, this는 그 클래스의 현재 객체로 참조한다.
    - 확장함수나, receiver함수 리터럴 this는 나타낸다, 그 리시버 파라미터를,
    dot의 왼쪽에서 전달된.

    만약 this가 qualifiers를 가지지 않으면,
    그것은 참조한다, 가장 안쪽으로 닫힌 스코프를.
    다른 스코프에서 this로 나타내기 위해서, label qualifiers가 사용된다.




    // =================
    // Qualified this
    // =================

    outer scope
    (a class, extension function,
    or 리시버가 있는 labeled 함수 리터럴)로부터,

    this에 접근하기위해서,
    너는 this@label을 써야한다,
    @label은 scope에 대한 레이블이다.

    class A { // implicit label @A
        inner class B { // implicit label @B
            fun Int.foo() { // implicit label @foo
                val a = this@A // A's this
                val b = this@B // B's this

                val c = this // foo()'s receiver, an Int
                val c1 = this@foo // foo()'s receiver, an Int

                val funLit = lambda@ fun String.() {
                    val d = this // funLit's receiver, a String
                }

                val funLit2 = { s: String ->
                    // foo()'s receiver, since enclosing lambda expression
                    // doesn't have any receiver
                    val d1 = this
                }
            }
        }
    }




    // ===============
    // Implicit this
    // ===============

    너가 this에서 멤버함수를 호출 할 때, 너는 this를 생략 할 수 있다.
    만약 너가 non-member function을 같은 이름으로 가진다면, 사용하라, 이것을 주의해서
    왜냐면, 몇몇 케이스에서 그것은 대신 불려진다.

    fun printLine() { println("Top-level function") }

    class A {
        fun printLine() { println("Member function") }

        fun invokePrintLine(omitThis: Boolean = false) {
            if (omitThis) printLine()
            else this.printLine()
        }
    }

    A().invokePrintLine() // Member function
    A().invokePrintLine(omitThis = true) // Top-level function

 */