package officialhome.concepts.d_ClassAndObject

/*
    Data classes


    데이터를 보관하기 위한 클래스를 만드는 것은 드문일이 아니다.
    이 같은 클래스에서 몇몇 표준함수, 그리고 유틸 함수는 종종 데이터로부터 기계적으로 파생된다.
    코틀린에서 이것은 data 클래스라고 불린다.

    data class User(val name: String, val age: Int)


    컴파일러는 기본생성자안에 선언된 모든 프로퍼티들로부터 다음과 같은 멤버를 파생한다.

    - equals() / hashCode() pair
    - toString() of the form "User(name=John, age=42)"
    - componetN() function corresponding to the properties in their order of declaration.
    - copy() function (see below)

    생성된 코드의 일관성과 의미를 유지하기 위해, 데이터 클래스는 다음과 같은 것들을 유지해야한다.

    - 기본 생성자는 최소한 하나의 파라미터가 있어야함
    - 모든 기본 생성자 파라미터는 val이나 var로 마크되야한다.
    - 데이터 클래스는 abstract, open, sealed, inner가 될 수 없다.

    또한, 데이터 클래스 멤버의 생성은 멤머의 상속에 관하여 다음과 같은 규칙을 따른다.

    - 만약 데이터 클래스내의 or 서브클래스내에서 fianl구현안에서
     equals(), hashCode(), toString의 명시적인 구현이 있는 경우. 이러한 함수는 생성되지 않는다.
     그리고 존재하는게 새용된다. -> 구현한거를 쓴다는건지, 기본생성자가 만든게 쓴다는건지 애매하게 써놨네..

     - 만약 subtype이 open과 호환되는 타입을 반환하는 componetN()함수를 가지면,
     그 상응되는 함수는 데이터클래스로 부터 만들어진다, 그리고 서브타입의 그것들을 오버라이드 한다.
    만약, 서브타입의 함수가 서명이 맞기 않거나, final이 안되기 때문에 오버라이드 될 수 없다면, 에러가 발생한다.

    - componetN()과 copy()에 대해서 명시적으로 제공하는 것은 허락되지 않는다.

    - 데이터클래스는 다른 클래스를 확장 할 수 있다. (Sealed class의 예를 보아라)

    JVM에서 만들어진 클래스가 매개변수가 없는 생성자가 있어야하는 경우,
    속성에 대한 기본값을 지정해야한다.
    data class User(val name: String = "", val age: Int = 0)




    // ======================================
    // Properties declared in the class body
    // ======================================

    컴파일러는 자동적으로 생성된 함수에 대한 기본생성자 내부의 정의된 프로퍼티를 사용한다.
    생성된 프로퍼티를 배제하기 위해서는
    클래스의 body내에 속성을 선언해라.

    data class Person(val name: String) {
        var age: Int = 0
    }

    name property는 toString(), equals(), hashCode(), copy()내부에서 사용될 것이다.
    그리고 componet1() 함수의 컴포넌트가 될 것이다.
    두 Person객체는 다른 나이를 가질 수 있지만, 그들은 같은 것으로 다뤄질것이다.




    // ========
    // Copying
    // =========

    객체를 copy하기위해서 copy()함수를 사용해라,
    이 기능을 사용하면 나머지는 변경하지 않고, 몇몇 프로퍼티가 대체된다.

    User클래스에 대한 함수의 구현은 다음과 같다.

    fun copy(name: String = this.name, age:Int = this.age) = User(name, age)

    너는 다음과 같이 쓸 수 있다.

    val jack = User(name = "Jack", age = 1)
    val olderJack = jack.copy(age = 2)




    // ============================================
    // Data classes and destructuring declarations
    // ============================================

    데이터 클래스와 구조분해 선언.

    Component함수는 구조분해선언 안에서 그것들을 사용가능하게 만든다.

    val jane = User("Jane", 35)
    val (name, age) = jane
    println("$name, $age years of age") // prints "Janme, 35 years of age"




    // ======================
    // Standard data classes
    // ======================

    표준 라이브러리는 Pair와 Triple클래스를 제공한다.
    하지만, 대부분의 경우 named data classes는
    그 프로퍼티에 대한 의미있는 이름으로 더 읽기 좋은 코드를 만들기 때문에 더 나은 디자인 선택이다.

 */