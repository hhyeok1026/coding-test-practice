package officialhome.concepts.d_ClassAndObject

/*
    Interface

    코틀린의 인터페이스는
    추상메서드의 선언과 메서드 구현이 포함 될 수 있다.

    그것이 추상 클래스와 다른컷은 인스턴스는 상태를 저장 할 수 없다.

    그것은 properties를 가진다.
    그러나 이것들은 abstracted 되거나 또는 접근자 구현을 제공해야한다.


    interface MyInterface {
        fun bar()
        fun foo() {
            // optional body
        }
    }



    // =========================
    // Implementing Interfaces
    // =========================

    class Child : MyInterface {
        override fun bar() {
            // body
        }
    }



    // =========================
    // Properties in interfaces
    // =========================

    너는 인터페이스 안에 properties를 선언할 수 있다.

    선언된 프로퍼티는 추상화 되거나 접근자로 구현을 제공해야한다.

    선언된 프로퍼티는 backing field를 가질 수 없다.
    그리고 그러므로 선전된 접근자는 그들(backing filed?)를 참조할 수 없다.

    interface MyInterface {
        val prop: Int // abstract

        val propertyWithImplementation: String
            get() = "foo"

       fun foo() {
            print(prop)
       }
    }

    class Child : MyInterface {
        override val prop: Int = 29
    }




    // =========================
    // Interfaces Inheritance
    // =========================

    인터페이스는 다른 인터페이스로 부터 파생될 수 있다.
    그것은 둘다 멤버와 새로운 함수, properties에 대해서 구현을 제공 할 수 있다는 것을 의미한다.

    자연스럽게, 인터페이스 같은 클래스 구현은 missing한 구현을 정의하기 위해서만 사용된다.


    interface Named {
        val name: String
    }

    interface Person : Named {
        val firstName: String
        val lastname: String

        override val name: String get() = "$firstName $lastName"
    }

    data class Employee(
        // implementing 'name' is not required
        override val firstName: String,
        override val lastname: String,
        val position: Position
    ) : Persion



    // =========================
    // Interfaces Inheritance
    // =========================

    너의 subtype list에서 만은 타입을 선언 할 때,
    너는 같은 메서드의 하나보다 더 많은 것들을 상속 할지도 모른다.

    interface A {
        fun foo() { print("A") }
        fun bar()
    }

    interface B {
        fun foo() { print("B") }
        fun bar() { print("bar") }
    }

    class C : A {
        override fun bar() { print("bar") }
    }

    class D : A, B {
        override fun foo() {
            super<A>.foo()
            suepr<B>.foo()
        }

        override fun bar() {
            super<B>.bar()
        }
    }

*/


















