package officialhome.concepts.d_ClassAndObject

/*
    Class

    Class Person



    // =================
    // Constructors
    // =================

    class Person constructor(firstName: String) { ... }

    first constructor가 어떠한 어노테이션이나 수정자를 가지지 않는다면 생략가능

    class Person(firstName: String) { ... }



   첫번째 생성자에서는 어떠한 코드도 포함 될 수 없다?
   초기화 코드는 init 블록에서 하면 된다.

   init블록과 초기화한 프로퍼티는 순서대로 적용된다?

   class Customer(name: String) {
        val customerKey = name.uppercase()
   }

   class Person(val firstName: String, val lastName: String, var age: Int)

   class Person(val firstName: String, val lastName: String, var isEmployed: Boolean = true)


   // 어노테이션이나 수정자가 있는 경우,
   class Customer public @Inject constructor(name: String) { ... }



    // =======================
    // Secondary constructors
    // =======================

    constructor 접두사로 두번재 생성자 만들 수 있다.

    class Person(val pets: MutableList<Pet> = mutableListOf())

    class Pet {
        constructor(owner: Person) {
            owner.pets.add(this)
        }
    }


    첫번재 생성자가 있고,
    두번째 생성자를 만들면,
    this키워드를 이용해서 첫번째 생성자에 위임해야함.

    class Person(val name: String) {
        val children: MutableList<Person> = mutableListOf()
        constructor(name: String, parent: Person) : this(name) {
            parent.children.add(this)
        }
    }

    모든 initialize하는 블록과, 프로퍼티들은 기본생성자의 일부가 됨.
    그리고 두번째 생성자가 더 늦게 생성되게 된다.


    비 추상 클래스가 생성자를 선언하지 않으면,
    자동으로 생성된 매개변수 없는 기본생성자를 가진다.
    그때, 생성자의 가시성은 public 이 된다.


    만약 pulbic 한 생성자 선언을 원하지 않는다면,
    빈 기본 생성자를 선언해라.
    class DontCreateMe private constructor() { ... }



    jvm에서 생성자는 기본값을 가지게 된다.
    class Customer(val customername: String = "")



    // ==============================
    // Creating instances of classes
    // ==============================

    val invoice = Invoice()

    val customer = Customer("Joe Smith")

    // 코틀린은 new키워드를 사용하지 않는다.



    // ==============================
    // Class members
    // ==============================

    클래스가 포함하는 것들
    - Constructors and initializer blocks
    - Functions
    - Properties
    - Nested and inner classes
    - Object declarations


    // ==============================
    // Inheritance
    // ==============================

    클래스는 다른 것으로 파생되어 상속 계층을 형성할 수 있음.



    // ==============================
    // Abstract classes
    // ==============================

    abstract키워드로 추상 클래스를 만들 수 있다.
    추상클래스는 그 안에 구현하지 않는다.
    추상클래스는 open키워드를 쓸 필요가 없다

    하지만, 비 추상 open member를 추상의 하나로, 오버라이드 할 수 있다. -> 뭔소리인지 모르겠군;



    // ==============================
    // Companion objects
    // ==============================
    클래스 인스턴스 없이, 클래스 내부에 액세스 해야하는 함수를 작성해야하는 경우(팩토리 메서드 같은).
    해당 클래스 내부의 컴페니언 객체?로 작성 할 수 있다.

    클래스 내부에 컴패니언 개체를 선언하면, 클래스 이름만 한정자로 사용하여 해당 멤버에 액세스 할 수 있음.



 */

fun main() {
    InitOrderDemo("Hello")
}

class InitOrderDemo(name: String) {
    val firstProperty = "First property: $name".also(::println)

    init {
        println("First initializer block that prints $name")
    }

    val secondProperty = "Second property: ${name.length}".also(::println)

    init {
        println("Second initializer block that prints ${name.length}")
    }
}




