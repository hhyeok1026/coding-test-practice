package officialhome.concepts.d_ClassAndObject

/*
    Properties



    // ===========================
    // Declaring properties
    // ===========================

    mutable로 선언할 수도 있음. var
    read-only로 선언할 수도 있음 val


    property의 full syntax는 다음과 같다.

    var <propertyName>[: <PropertyType>] [= <property_initializer>]
        [<getter>]
        [<setter>]



    // ===========================
    // Getter and setters
    // ===========================

    getter와 setter는 옵션이다.
    initializer or getter에서 값이 추론 되면, property type도 옵션이다.


    read-only property는 mutable과 2가지 다르다.
    var 대신 val키워드를 사용해야하고, setter가 없다.


    custom getter를 지정할 수 있고,
    property에 접근할때마다 호출될 것이다.

    class Rectangle(val width: Int, val height:Int) {
        val area: Int //property 타입은 getter의 리턴값으로 추론 될 수 있으므로 옵션이다.
            get() = this.width * this.height
    }

    custom setter를 구현하게 되면, 초기화를 제외하고
    값을 할당 할때마다, 매번 setter가 호출 될 것이다.

    setter에서 파라미터는 편의상 value로 받는데, 니가 바꿀 수 있다.


    // Backing field
    코틀린에서 field는 메모리안에 프로퍼티의 값을 잡고 있는 부분으로 사용된다.
    field는 직접적으로 선언 될 수 없다.
    하지만, 프로퍼티가 backing field를 필요로 할때, 코틀린은 자동적으로 제공함.
    field라는 식별자로 참조될 수 있다.

    접근자 중 하나 이상의 기본구현을 사용하거나,
    사용자가 지정접근자 field 식별자를 통해 참조하는 경우. backingfield가 생성된다.



    // ===========================================
    // 연습좀 더 해보고 진짜 완전히 이해가 안되면 질문을 해봐야할 듯.
    // TODO property의 val, var의 초기값과
        setter, getter, field에 대해서 완벽하게 이해가 되지 않네..?
    // val에서 초기값을 안적어도, get만 구현하면 초기값 선언을 안해도 되는것 같고,
    // var에서 초기값을 안적어도, get,set을 둘다 구현하면 초기값을 안적어도 되는것 같음.
    // get이나 set에서 그냥 자기값을 쓰면, Recursive property accessor 라고 린트 뜨는거 부터가 이해가 안됨.
    // -> 왜 또 field라는 지원필드를 써야하나? 그냥 지 변수에 넣으면 될 것을..
    // 애초에 defalut getter, setter가 어떻게 구현되어 있는가 구경을 한번 해보고 싶은데
    // ===========================================


    // Backing properties
    암시적으로 지원필드 스키마와 맞지 않는다면,
    항상 가지는, backing property를 만들면 된다.

    private var _table: Map<String, Int>? = null //얘가 baking property인듯.
    public val table: Map<String, Int>
        get() {
            if (_table == null) {
                _talbe = HashMap()
            }
            return _table ?: throw AssertionError("Set to null by another thread")
        }



    // ===========================
    // Compile-time constants
    // ===========================

    read-only property는 컴파일 타임에 알려지려면,
    const 수정자로 컴파일타임 상수가 되게 마크해라.

    그 같은 property는 다음과 같은 요구사항을 충족해야한다.
    - 최상위 프로퍼티거나, object선언의 멤머이거나 compainon object이어야한다.
    - String이나 primitive타입의 값으로 초기화 되야한다.
    - custom getter를 쓰면 안된다.

    컴파일러는 그 상수의 사용을 인라인 할 것이고, 그것의 참조를 실제값 상수로 바꿀것이다.

    그러나, field는 제거되지 않는다. 그러므로 reflection을 사용하여 상호작용 할 수 있다.

    property는 또한 annotation에서 사용 될 수 있다.

    const val SUBSYSTEM_DEPRECATED: String = "This subsystem is deprecated"
    @Deprecated(SUBSYSTEM_DEPRECATED) fun foo() { ... }



    // ==========================================
    // Late-initialized properties and variables
    // ==========================================

    일반적으로 non-null type의 properties는 생성자 안에서 반드시 초기화 해야한다.
    그러나 종종 그렇게 하는것은 편하지 않다.
    에를 들어, properties는 의존주입으로 초기화 될 수 있다.
    또는, 유닛 테스트의 초기설정 메서드에서..
    이 경우,
    너는 생성자 안에서 non-null initializer를 충족 할 수 없다.
    그러나 너는 여전이 property가 참조 될 때, null check를 피하기를 원한다.

    이것을 피하려면,
    lateinit modifier을  써라.

    public class MyTest {
        lateinit var subject: TestSubject

        @SetUp fun setup() {
            subject = TestSubject()
        }

        @Test fun test() {
            subject.method() //dereference directly
        }
    }

    이 수정자는 var properties에서 사용된다,
    기본생성자내에서 아니라, 클래스의 바디에서 사용 될 때,
    그리고 only 커스텀 get, set을 안가질때.

    뿐만 아니라, 탑레벨 프로퍼티와 로컬 변수에서. 사용된다.

    property의 타입은 반드시 non-null이어야하고,
    primitive type이면 안된다.


    lateinit property가 초기화 되기 이전에 접근하는 것은, 특별한 예외를 발생시킨다.
    분명히 확인한다. 그 프로퍼티에 접근하는것과, 그것이 초기화 되지 않았다는 사실을.


    // lateinit var가 초기화 되었는지 아닌지 체크하는 법.

    .isInitialized를 사용해라

    if (foo::bar.isInitialized) {
        println(foo.bar)
    }



    // ==========================================
    // Overriding properties
    // ==========================================
    Overriding properties 문서를 보세요. - Inheritance에 기술되어있음.



    // ==========================================
    // Delegated properties
    // ==========================================
    가장 일반적인 종류의 properties는 단순지 backing field에서 읽거나 쓰지만,
    custom getter, setter를 사용하면 properties를 원하는 종류의 동작을 구현할 수 있다.
    첫번째의 단순함과 두번째의 다양성 사이 어딘가에 property가 수행 할 수 있는 작업에 대한 공통 패턴이 있다.
    A few examples: lazy values, reading from a map by a given key, accessing a database, notifying a listener on access.

    이 같은 행동은 delegated properties를 사용한 라이브러리로 구현 될 수 있다. -> delegated properties문서도 따로 있음.



*/

class Rectangle(val width: Int, var height: Int) {
    val area: Int // property type is optional since it can be inferred from the getter's return type
        get() = this.width * this.height

    var stringRepresentation: String = "0"
        get() = field
        set(value) {
            field = value
        }

    var setterVisibility: String = "abc"
        private set //set을 private로 만들 수 있음.

    var setterWithAnnotation: Any? = null
    //@Inject set  //set 앞에 annotate를 붙일 수 있음.

    var counter = 0 // the initializer assigns the backing field directly
        set(value) {
            if (value >= 0)
                field = value
            // counter = value // ERROR StackOverflow: Using actual name 'counter' would make setter recursive
        }

    var isEmpty: Boolean = true
        get() = this.setterVisibility == "abc"
        set(value) {
            field = false
        }

}

fun main() {
    val rectangle = Rectangle(3, 4)
    println("Width=${rectangle.width}, height=${rectangle.height}, area=${rectangle.area}")

    rectangle.height = 5

    rectangle.stringRepresentation = "123"

    println("area= ${rectangle.area}") // 바꿔도 getter때문에 바로 값이 다시 계산되네.

    println("stringRepresentation= ${rectangle.stringRepresentation}")

    rectangle.counter = 10
    println("counter: ${rectangle.counter}")

    rectangle.setterWithAnnotation = "test"
    println(rectangle.setterWithAnnotation)

    rectangle.isEmpty = false
    println(rectangle.isEmpty)


}