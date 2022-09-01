package officialhome.concepts.d_ClassAndObject

/*
    // Delegated properties -> TODO 제너릭 급으로 어렵네, 나중에 다시 봐야할듯.

    프로퍼티의 몇몇 공통 종류와 함께,
    비록 너는 너가 그것들이 필요할 때 수동으로 그것들을 구현 할 수 있지만,
    그것은 더 유용하다 그것들을 한번 구현하기 위해서, 그것들으 라이브러리에 추가하거나,
    그리고 나중에 재사용하기위해서

    몇가지 예가 있다.

    - Lazy properties : 오직 첫번째 엑세스에서 값이 계산된다.
    - Observable properties : 리스너가 알아챈다, 이 프로퍼티가 변경 되었다는것을
    - 각각의 프로퍼티에 대해서 분리된 필드 대신에, map 안에 저장된 프로퍼티

    이 케이스들을 다루기 위해서, 코틀린은 delegated properties를 지원한다.

    class Example {
        var p: String by Delegate()
    }

    그 문법 val/var <property name>: <Type> by <expression>.
    by 이후의 표현식은 delegate이다, 떄문이다, get(), set()은 그 프로퍼티에 일치한다,
    그것의 getValue()와 setValue()에서 위윔될 프로퍼티에,
    property delegates는 구현하기위한 인터페이스를 가지지 않는다.
    그러나 그들은 제공해야한다, getValue()함수와, var에 대해서는 setValue()를


    예를들어,

    import kotlin.reflect.KProperty

    class Delegate {
        operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
            return "$thisRef, thank you for delegating '${property.name}' to me!"
        }

        operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
            println("$value has been assigned to '${property.name}' in $thisRef.")
        }
    }


    p로 부터 읽을때, 그리고 그것은 delegate한다, Delegate의 인스턴스에서,
    그리고 Delegate가 불려진 getValue()함수에서,
    그것의 첫번째 파라미터는 너가 읽은 p이고, 그리고 두번째 파라미터는 p그것 스스로의 description을 hold한다.
    (예를들어, 너는 그것의 이름을 가질 수 있다.)

    val e = Example()
    println(e.p)

    이것은 프린트 한다.

    Example@33a17727, thank you for delegating 'p' to me!

    유사하게, 너는 p를 할당할때, 그 setValue()불려진다.
    그 첫번째 두 매개변수는 같다, 그리고 세번째는 그값이 할당되는것을 hold한다.

    e.p = "NEW"

    이것은 출력한다.

    NEW has been assigned to 'p' in Example@33a17727.

    delegated object에서 요구되는 구체화는 아래에서 발견 할 수 있다.
    너는 선언 할 수 있다, 함수나 코드블록 내부에 delegated proerty를,
    그것은 클래스의 멤버일 필요는 없다, 아래의 에제에서 찾아봐라.




    // =========================
    // Standard delegates
    // =========================

    코틀린 표준 함수는  몇가지 유용한 종류의 대리자를 위한 팩토리 함수를 제공한다,

    // Lazy properties
    lazy()는 함수이다, 람다와 Lazy<T>의 인스턴스를 리턴하는,
    그리고 그것은 lazy property 구현을 위한 delegate로서 제공할 수 잇다.

    그 get()에서 첫번째 호출은 lazy()에 들어간 람다를 실행한다, 그리고 결과를 기억한다.
    get()에 대한 후속 호출은 간단하게 기억된 결과를 리턴한다.

    val lazyValue: String by lazy {
        println("computed!")
        "Hello"
    }

    fun main() {
        println(lazyValue)
        println(lazyValue)
    }

    ->
    computed
    Hello
    Hello


    기본적으로, lazy properties의 evaluation은 synchronized이다.
    그 값은 한 스레드안에서 계산된다, 그러나 모드 스레드는 같은 값을 볼 수 있다.
    만약 그 초기화 대리자의 동기화가 그것을 동시에 다양한 스레드에서 실행 될 수 있게, 필요하지않으면
    LazyThreadSafetyMode.PUBLICATION을 넣어라, lazy()에 파라미터로

    만약 너가 확신한다면, 초기화가  프로퍼티가 사용된 늘 같은 한 스레드에서 발생한다면,
    너는 LazyThreadSafetyMode.NONE을 사용 할 수 있다.
    그것은 thread-safety guarantees 와 관련된 오버헤드를 발생키시지 않는다.



    // Observable properties

    Delegates.observable()은 두개의 arguments를 가진다.
    그 초기화 값과 modification에 대한 핸들러를.

    그 핸들러는 그 프로퍼티에 너가 할당 할 때마다 호출된다(할당이 수행된 이후에)
    그것은 세가지 파라미터가 있다.
    그 할당 되는 프로퍼티, 옛날값, 새값


    import kotlin.properties.Delegates

    class User {
        val name: String by Delegates.observable("<no name>") {
            prop, old, new ->
            println("$old -> $new")
        }
    }

    fun main() {
        val user = User()
        user.name = "first"
        user.name = "second"
    }

    만약 너가 할당을 intercept하기 원한다면, 그리고 그것들을 거부하려면,
    observable()대신에 vetoable()을 사용하라,
    그 vetoable에 통과된 핸들러는 새 프로퍼티 값의 할당 이전에 불려진다.




    // ================================
    // Delegating to another property
    // ================================

    프로퍼티는 그것의 게터와 세터를 다른 프로퍼티에 위임할 수 있다.
    그같은 위임은 top-level과 class properties에 대해서 가능하다 (member and extension).

    그 delegate property는 할 수 있다
    - top-level property
    - 같은 클래스의 member or extension
    - 다른 클래스의 member or extension

    프로퍼티를 다른 프로퍼티로 위임하기 위해서,
    delegate name안에 ::한정자를 사용하라
    예를 들어, this::delegate or MyClass::delegate.


    var topLevelInt: Int = 0
    class ClassWithDelegate(val anotherClassInt: Int)

    class MyClass(var memberInt: Int, val anotherClassInstance: ClassWithDelegate) {
        var delegatedToMember: Int by this::memberInt
        var delegatedToTopLevel: Int by ::topLevelInt

        val delegatedToAnotherClass: Int by anotherClassInstance::anotherClassInt
    }

    var MyClass.extDelegated: Int by ::topLevelInt

    예를 들어, 이것은 유용할 수 있다,
    너가 backword-compatible way ( 이전버전 호환성)로 프로퍼티 이름을 rename하려할때,
    새로운 프로퍼티 도입하고, @Deprecated를 오래된것에 어노테이트하고, 그것의 구현을 위임한다.

    class MyClass {
        var newName: Int = 0
        @Deprecated("Use 'newName' instead", ReplaceWith("newName"))
        var oldName: Int by this::newName
    }

    fun main() {
        val myClass = MyClass()
        // Notification: 'oldName: Int' is deprecated.
        // Use 'newName' instead
        myClass.oldName = 42
        println(myClass.newName) // 42
    }




    // ================================
    // Storing properties in a map
    // ================================

    하나의 일반적인 사용 케이스는 map안에 프로퍼티의 값을 저장하는 것이다.
    이것은 종종 발생한다, json파싱, 다른 동적인 일을 수행하는 어플리케이션에서
    이러한 경우, 너는 map instance  그것스스로를 사용할 수 있다, delegate property를 위한 대리자로서

    class User(val map: Map<String, Any?>) {
        val name: String by map
        val age: Int by map
    }

    예를 들어, 생성자는 map을 가진다
    val user = User(mapOf(
        "name" to "John Doe",
        "Age to 25
    ))

    위임된 프로퍼티는 string key를 통해서 이 map으로 부터 값을 가진다,
    그리고 그것은 프로퍼티의 이름과 연관이 있다.

    println(user.name) // Prints "John Doe"
    println(user.age) // Prints 25

    이것은 또한 var의 프로퍼티를 위해서 작동한다,
    만약 너가 MutableMap을 사용한다면, read-only map을 대신하여.

    class MutableUser(val map: MutableMap<String, Any?>) {
        var name: String by map
        var age: Int by map
    }




    // ================================
    // Local delegated properties
    // ================================

    로컬 변수를 선언할 수 있다, delegated properties로서.
    예를 들어,
    너는 local 변수를 lazy로 만들 수 있다.

    fun example(computeFoo: () -> Foo) {
        val memoizedFoo by lazy(computeFoo)

        if (someCondition && memoizedFoo.isValid()) {
            memoizedFoo.doSomething()
        }
    }

    memoized 변수는 오직 처음 접근할때 계산 될 것이다.
    만약 someCondition이 실패하면, 변수는 전혀 계산 되지 않을 것이다.




    // ================================
    // Property delegate requirements
    // ================================

    오직 읽기 프로퍼티에 대해서 (val),
    그 delegate는 operator function getValue를 제공해야한다.
    다음 파라미터와 함께

    - thisRef는 반드시 속성의 소유자와 같은 타입이어야한다, 또는 supertype,
    (확장 프로퍼티에 대해서는, 반드시 extended된 타입이어야한다.)

    - property는 반드시 KProperty<*>의 타입이거나 그것의 supertype이어야한다.

    getValue()는 반드시 같은타입을 리턴해야한다. (또는 그것의 supertype을)

    class Resource

    class Owner {
        val valResource: Resource by ResorceDelegate()
    }

    class ResourceDelegate {
        operator fun getValue(thisRef: Owner, property: KProperty<*>): Resource {
            return Resource()
        }
    }


    mutable property (var)에 대해서, 그 대리자는 추가적인 오퍼레이터 함수 setValue를 가져야한다,
    다음 파라미터와 함께.

    - thisRef 는 프로퍼티의 소유자와 반드시 같은 타입이거나 상위타입
    (확장함수에 대해서 그것은 반드시 확장이 되어야한다)

    - property는 반드시 KProperty<*>의 타입이거나 그것의 상위타입이어야한다.

    - value은 반드시 같은 타입이거나 그것의 상위타입

    class Resource

    class Owner {
        var varResource: Resource by ResourceDelegate()
    }

    class ResourceDelegate(private var resource: Resource = Resource()) {
       operator fun getValue(thisRef: Owner, property: KProperty<*>): Resource {
            return resource
       }

       operator fun setValue(thisRef: Owner, property: KProperty<*>, value: Any?) {
        if (value is Resource) {
            resource = value
        }
    }


    getValue()와 setValue()함수는 그 delegate클래스의 멤버함수로서 제공된다.
    또는 확장함수로,
    그 후자는 손쉽다, 너가 이 함수가 오르지널로 제공되지 않는 객체에 프로퍼티에 대리자를 필요로 할때,
    이 함수는 둘다 operator로 마크되는게 필요하다.

    너는 만들 수 있다 대리자를 익명객체로서 새로운 클래스를 만드는것 없이,
    인터페이스 ReadOnlyProperty와 ReadWriteProperty를 코틀린 스탠다드 라이브러리에서 사용함으로써,
    그들은 요구되는 메서드를 제공한다.
    getValue()는 ReadOnlyProperty안에 선언되었다.
    ReadWriteProperty는 그것을 확장하고, setValue()를 더한다
    이것은 너가 ReadOnlyProperty가 기대되는 언제든지 ReadWriteProperty를 넣을 수 잇다는 것을 의미한다.

    fun resourceDelegate(): ReadWritePRoperty<Any?, Int> =
        object : ReadWriteProperty<Any?, Int> {
            var curValue = 0
            override fun getValue(thisRef: Any?, property: KProperty<*>): Int = curValue
            override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
                curValue = value
            }
        }

    val readOnly: Int by resourceDelegate() // ReadWriteProperty as val
    var readWrite: Int by resourceDelegate()




    // ===========================================
    // Translation rule for delegated properties
    //  - 위임된 속성에 대한 변환 규칙.
    // ===========================================

    hood아래서, 코틀린 컴파일러는 모든 대리된 프로퍼티에 대해서 보조자 프로퍼티를 생성한다,
    그리고 그것에 delegate한다.
    예를들어, 프로퍼티 prop에 대해서 그것은 히든 property prop$delegate를 생성한다,
    그리고 접근자의 코드는 이 추가적인 프로퍼티에 간단하게 위임한다.

    class C {
        var prop: Type by MyDelegate()
    }

    // this code is generated by the compiler instead:
    class C {
        private val prop$delegate = MyDelegate()
        var prop: Type
            get() = prop$delegate.getValue(this, this::prop)
            set(value: Type) = prop$delegate.setValue(this, this::prop, value)
    }

    코틀린 컴파일러는 argument내에서 prop에 대해서 필요한 정보를 모두 제공하낟.
    첫번째 argu this는 outer class C의 인스턴스로 나타난다.
    그리고 this::prop는 prop자체를 설명하는 KProperty 타입의 reflection객체이다.


    // Translation rules when delegating to another property
    다른 프로퍼티에 위임 할 때, 그 코틀린 컴파일러는 레퍼런스된 프로퍼티에
    즉각적인 접근을 생성한다.
    이것은 컴파일러는 field prop$delegate를 생성하지 않는다는것을 의미한다.
    최적하는 메모리 save를 돕늗다.

    다음 예제를 보라

    class C<Type> {
        private var impl: Type = ...
        var prop: Type by ::impl
    }

    prop 변수의 프로퍼티 접근자는 impl변수를 즉각 발생시킨다,
    위임된 프로퍼티의 getValue와 setValue연산자를 스킵한다,
    그리고 그래서 그 KProperty 레퍼런스객체는 필요가 없다.

    위의 코드에 대해서, 컴파일러는 다음과 같은 코드를 생성한다.

    class C<Type> {
        private var impl: Type = ...

        var prop: Type
            get() = impl
            set(value) {
                impl = value
            }

        fun getProp$delegate(): Type = impl // This method is needed only for reflection
    }




    // =======================
    // Providing a delegate
    // =======================

    그 provideDelegate operator를 정의함으로써,
    너는 그 프로퍼티 구현이 위임되는것에서 그 객체가 만들어지는 로직에 대해서 extend할 수 있다.
    만약 그 사용된 객체가 by 우측에서 멤버로서 provideDelegate나 확장함수를 정의한다면,
    그 함수는 property delegate instance를 만드기 위해서 호출 될 것이다.

    provideDelegate의 가능한 사용케이스중 하나는 그 초기화 위의 프로퍼티의 일관성을 체크하는것이다.

    예를 들어, binding 이전에 property name을 확인하기 ㅜ이해,
    너는 다임과 같이 슬 수 있다.

    class ResourceDelegate<T> : ReadOnlyProperty<MyUI, T> {
        override fun getValue(thisRefL MyUI, property: KProperty<*>): T {
            ...
        }
    }

    class ResourceLoader<T>(id: ResourceID<T>) {
        operator fun provideDelegate(
            thisRef: MyUI,
            prop: KProperty<*>
        ): ReadOnlyProperty<MyUI, T> {
            checkProperty(thisRef prop.name)
            // create delegate
            return ResourceDelegate()
        }

        private fun checkProperty(thisRef: MyUI, name: String) { ... }
    }

    class MyUI {
        fun <T> bindResource(id: ResourceID<T>): ResourceLoader<T> { ... }

        val image by bindResource(ResourceID.image_id)
        val text by bindResource(ResourceID.text_id)
    }

    provideDelegate의 파라미터는 getValue()의 것과 같다.

    - thisRef는 그 프로퍼티의 오너와 반드시 같은 타입, 상위타입같거나
    (확장 프로퍼티는 그것은 확장타입이 되어야한다.)

    - property는 반드시 KPRoperty<*>의 타입이거나 그것의 상위타입이어야한다.

    그 provideDelegate메서드는 각각 프로퍼티에 대해서 호출된다, MyUI인스턴스의 생성동안,
    그리고 그것은 수행한다, 유효성 검사를 즉시.

    이 기술 없이 프로퍼티와 그것의 delegate사이에 바인딩하는것을 intercept하는것은,
    동일한 기능을 달성하려면, 속성이름을 명시적으로 전달해야한다.
    , 그리고 그것은 매우 불편하다.


    // Checking the property name without "provideDelegate" functionality
    class MyUI {
        val image by bindResource(ResourceID.image_id, "image")
        val text by bindResource(ResourceID.text_id, "text)
    }

    fun <T> MyUI.bindResource(
        id: ResourceID<T>,
        propertyName: String
    ): ReadOnlyProperty<MyUI, T> {
        checkProperty(this, property)
        //create delegate
    }

    생성된 코드에서, provideDelegate 메서드가 불려진다,
    초기화 하기위해서, 보조의 prop$delegate프로퍼티를

    비교하라, 위의 코드와 함께 생성된 val prop: Type by MyDelegate() 프로퍼티 선언에 대한 생성된 코드를
    (provideDelegate메서드가 나타나지 않을때)

    class C {
        var prop: Type by MyDelegate()
    }

    // this code is generated by the compiler
    // whem the 'provideDelegate' function is available:
    class C {
        // calling 'provideDelegate' to create the additional "delegate" property
        private val prop$delegate = MyDelegate().provideDelegate(this, this::prop)
        var prop: Type
            get() = prop$delegate.getValue(this, this::prop)
            set(value: Type = prop$delegate.setValue(this, this::prop, value)
    }


    주의하라, provideDelegate 메서드는 오직 보조 프로퍼티의 생성에 영향을 준다.
    그리고 getter나 setter에는 영향이 없다.

    표준 라이브러리의 PropertyDelegateProvider 인터페이스와 함께,
    너는 새 클래스의 생성없이 delegate providers를 생성할 수 있다.

    val provider = PropertyDelegateProvider
            { thisRef: Any?, property -> ReadOnlyProperty<Any?, Int> {_, property -> 42 }
    }

    val delegate: Int by provider

 */


class MyClass {
    var newName: Int = 0
    @Deprecated("Use 'newName' instead", ReplaceWith("newName"))
    var oldName: Int by this::newName
}

fun main() {
    val myClass = MyClass()
    // Notification: 'oldName: Int' is deprecated.
    // Use 'newName' instead
    myClass.oldName = 42
    println(myClass.newName) // 42
}

















