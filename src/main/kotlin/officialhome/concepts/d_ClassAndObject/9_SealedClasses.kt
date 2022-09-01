package officialhome.concepts.d_ClassAndObject

/*
    Sealed classes

    이 클래스나 인터페이스는 상속이 제한됨.
    컴파일 타임에 알려짐.

    모듈이 컴파일 되면, 더이상 새로운 구현이 나타나지 않음.

    enum클래스와 유사하다.

    enum의 값의set또한 제한되어 있다.
    그러나 enum상수는 싱글인스턴스로 존재한다.
    반면에 sealed class의 subclass는 다양한 인스턴스를 가질 수 있다.

    sealed interface Error

    sealed class IOError(): Error

    class FileReadError(val file: File): IOError()
    class DatabaseError(val source: DataSource): IOError()

    object RuntimeError : Error



    sealed class는 추상적이다.
    그것들은 직접적으로 인스턴스 될 수 없다.
    그리고 abstract멤버를 가질 수 있다.

    sealed class의 생성자는 두개의 가시성을 가질 수 있다.
    protected(default) or private를.

    sealed class IOError {
        constructor() { ... }
        private constructor(description: String): this() { ... } // private is OK
        // public constructor(code: Int): this() {} // Error: public and internal are not allowed
    }



    // ==============================
    // Location of direct subclasses
    // ==============================

    sealed class의 direct subclass와 인터페이스는 같은 패키지에 선언되어야한다.
    그것들은 최상위 수준이거나 다른 명명된 클래스, 명명된 인터페이스, 명명된 개체 내부에 중첩 될 수 있다.
    하위 클래스는 kotlin의 일반 상속 규칙과 호환되는 모든 가시성을 가질 수 있다.

    sealed class의 하위클래스는 적절한 이름을 가져야한다.
    그들은 로컬이나 익명 객체가 될 수 없다.

    // enum클래스는 sealed클래스로 확장 할 수 없다. 뿐만아니라 다른 클래스로.
    그러나 그들은 sealed interface로 구현 할 수 있다.

    이 제약은 간접적인 subclass에 적용 되지않는다.
    만약 sealed class의 직접적인 서브클래스가 sealed로서 마크되지 않았다면
    그것은 그것의 수정자가 허락하는 되로 확장 될 수 있다.

    sealed interface Error // has implementations only in same package and module

    sealed class IOError(): Error // extended only in same package and module
    open class CustomError(): Error // can be extended wherever it's visible


    // 다중 플랫폼에서의 상속.
    다중 플랫폼 프로젝트에서 하나 더 많은 상속제한이 있다.
    sealed class의 직접하위 클래스는 반드시 같은 소스셋안에 있어야한다.
    expect와 actual 수정자가 없는 sealed class에 적용된다.

    만약 sealed클래스가 일반적인 소스셋안에서 expect로 적용된다면, 그리고 플랫폼내의 actual implementations을 가진다면,
    expect와 actual버전은 그들의 소스셋안에 subclass를 가질 수 있다.
    게다가, 만약 너가 계층 구조를 사용하면, 너는 expect와 actual선언 내의 소스셋 안에 subclass를 만들 수 있다.

    다중 플랫폼 프로젝트의 계층 구조에 대해 자세히 알아보세요 .
    ->  https://kotlinlang.org/docs/multiplatform-share-on-platforms.html#share-code-on-similar-platforms




    // ==============================
    // Sealed classes 와 when 표현식
    // ==============================

    sealed class의 사용의 주요 이점은, when절 안에서 그것들을 사용할때 나타난다.
    모든 케이스가 증명된다면, 너는 else구문을 넣을 필요가 없다.
    하지만, 너가 오직 when을 표현식으로 사용할 때만 작동한다, statement가 아니라.

    fun log(e: Error) = when(e) {
        is FileReadError -> { println("Error while reading file ${e.file}") }
        is DatabaseError -> { println("Error while reading from database ${e.source}") }
        is RuntimeError ->  { println("Runtime error") }
        // the `else` clause is not required because all the cases are covered
    }


    // sealed class에 대한 when 절은
    멀티플랫폼 프로젝트의 일반적 코드에서,
    여전히 else분기를 필요로 한다.
    이것은 actual 플랫폼 구현의 서브클래스가 일반 코드를 모르기 때문에 일어난다.


 */




















