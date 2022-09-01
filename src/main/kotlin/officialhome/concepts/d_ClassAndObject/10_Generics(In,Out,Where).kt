package officialhome.concepts.d_ClassAndObject

/*
    Generics: in, out, where
    // TODO 설명을 더럽게 못해놨는지,
        내가 모자란건지 모르겠는데 제너릭은 꼭 다시 한번 봐야할 듯.

    코틀린 클래스는 type parameters를 가진다. 자바처럼.

    class Box<T>(t: T) {
        var value = t
    }

    같은 클래스의 인스턴스를 만들기 위해, 타입 argument를 간단하게 제공해라.

    val box: Box<Int> = Box<Int>(1)

    그러나 만약 파라미터가 추론이 가능하면, 기본생성자로부터, 너는 type argument를 생략 할 수 있다.

    val box = Box(1) // 1 has type Int, so the compiler figures out that it is Box<Int>



    // ==============
    // Variance - 변화
    // ==============

    자바 타입의 까다로운 측면중 하나가 와일드 카드 타입이다.
    코틀린은 이것을 가지지 않는다.
    대신에 코틀린은 declaration-site variance와 type projections이 있다.

    왜 자바가 이런 미스터리한 와일드카드가 있는지 생각해보라.
    이 문제는 이펙티브 자바 3편에 잘 설명되어 있다.
    먼저 자바안에서 제러닉타입은 불변이다. 그것은 List<String>은 List<Object>의 하위 타입이 아니라는 의미다.
    만약, List가 불변이 아니라면, 자바의 배열보다 나을게 없다. 컴파일 된 코드는 런타임에서 예외를 일으킬것이기 때문에.

    //Java
    List<String> strs = new ArrayList<String>();
    List<Object> objs = strs; // !!! A compile-time error here saves us from a runtime exception later.
    objs.add(1); //Put an Integer into a list of Strings
    String s = strs.get(0) // !!! ClassCastException: Cannot cast Integer to String


    자바는 이같은 것을 런타입 세이프를 위해서 금지한다.
    그러나 이것은 의미가 있다.
    예를 들어, Collection 인터페이스에서, addAll()메서드를 고려해라
    이 메서드의 시그니처가 무엇인가.
    직관적으로 다음과 같다.

    // Java
    interface Collection<E> ... {
        void addAll(Collection<E> items);
    }

    그러나, 너는 다음을 수행 할 수 없다. (완벽하게 안전함)
    // Java
    void copyAll(Collection<Object> to, Collection<String> from) {
        to.addAll(from);

        // !!! Would not compile with the navie declaration of addAll:
        // Collection<String> is not a subtype of Collection<Object>
    }

    이같은 이유는 addAll()의 시그니처는 다음과 같다.
    //Java
    interface Collection<E> ... {
        void addAll(Collection<? extends E> items);
    }

    와일드카드 타입 argument <? extends E> 가르킨다.
    이 메서드는 수행한다. E의 객체의 컬렉션이나, E의 하위타입을. 그러나 단지 E는 아니다.
    이것은 의미한다. 너는 안전하게 아이템의E를 읽을 수 있다.
    그러나 너는 그것을 쓸 수 없다. 너는 그 객체가 따르는 E의 서브타입을 알 수 없기 때문에.
    이 제약의 대가로, 너는 바라는 동작을 할 수 있다.
    Collection<String> 은 Collection<? extends Object>의 하위타입이다.
    다시말해, extends-bound(upper bound)와 함께 있는 와일드 카드는 그 타입을 covariant(공변, 함께변하는)으로 만든다.

    왜 이렇게 작동하는지에 대한 키는 다소 간단하다.
    만약 너가 컬렉션으로 아이템을 가진다면 String의 컬렉션의 사용과 그것의 Object의 읽기는 좋다.
    반대로, 만약 너가 컬렉션에 아이템을 넣을 수 있다면, Object의 컬렉션을 가지고, 그것으로 String을 넣어라.
    자바안에서 List<Object>의 상위타입인 List<? super String>이 있다.

    후자는 반공성(contravariance)라고 하며,
    너는 오직 호출 할 수 있다.
    String을 가지는 메서드를 List<? super String>의 argument로서
    (예를 들어 너는 호출 할 수 있다. add(String)을, 또는 set(int, String)을)
    만약 너가 어떤 것을 호출한다면, List<T>안에 T로 리턴하는것을, 너는 String을 얻을 수 없다.
    그러나 Oject를 얻는다.

    Joshua Bloch는 읽기만 하는 개체에는 생산자라는 이름을 부여하고,
    쓰기만 하는 개체에는 소비자라는 이름을 부여합니다. 그는 다음을 권장합니다.

    // 최대 유연성을 위해 생산자 또는 소비자를 나타내는 입력 매개변수에 와일드 카드 유형을 사용하고 다음 니모닉을 제안합니다.
    PECS 약어이다. for Producer-Extends, Consumer-Super.

    // 만약 너가 Producer-object를 사용한다면, 말해라 List<? extends Foo>, 너는 할 수 없다. add()나 set()을 콜하는 것을 객체에서,
    그러나 이 것은 의미하지 않는다. 그것이 불변이라는 것을
    에를 들어, clear()를 호출하는 방해가 없다. 리스트로부터 모든 아이템을 제거하기 위해서.
    clear()는 어떠한 파라미터도 가지지 않기 때문에.
    와일트 카드에 의해(또는 variance의 다른 타입)에 의해 보장되는 유일한 것은 타입 안정성이다.
    불변성은 완전히 다른 얘기다.




    // Declaration-site variance
    가정하자. Source<T> 제너릭 인터페이스가 있다고,
    그리고 그것은 T파라미터를 갖는 어떠한 메서드도 갖지 않는다. 오직 T를 리턴하는 메서드이다.

    // Java
    interface Source<T> {
        T nextT();
    }

    그땐, 완벽하게 안전하다. Source<Object> 타입의 변수안에 Source<String>의 인스턴스에 대한, 레퍼런스를 저장하는게
    이건 consumer-method가 아니다 호출하기 위한.
    그러나 자바는 이것을 알지못한다. 그리고 그것은 막혀있다.

    // Java
    void demo(Source<String> strs) {
        Source<Object> objects = strs; // !!! Not allowed in Java
    }

    이것을 수정하기 위해. 너는 Source<? extends Object>타입의 객체를 선언해야한다.
    이렇게 하는 것은 의미가 없다. 너는  이전 같은 변수에 대해 같은 모든 메서드를 호출 할 수 있기때문이다.
    그래서 추가 되는 값이 없다. 더 복잡한 타입에 대해서
    그러나 컴파일러는 그것을 알지 못한다.

    코틀린에서, 컴파일러에서 그것의 종류를 설명하는 방법이있다.
    이것은 declaration-site vaiance라 불린다.
    너는 Source의 T 타입 파라미터를 어노테이트 할 수 있다. 확실히 하기 위해,
    Source<T>의 멤버로 부터 리턴되는(생산되는)거라고.
    그리고 결코 consumed되지 않는다고,
    이렇게 하기 위해서
    out 수정자를 사용하라.

    interface Source<out T> {
        fun nextT() : T
    }

    fun demo(strs: Source<String>) {
        val objects: Source<Any> = strs // This is ok, since T is an out-parameter
    }

    일반적 규칙은 이러하다.
    클래스C의 타입파라미터T가 out으로 선언될때,
    그것은 발생할 수도 있다, 오직 C의 멤버안의 out-position안에서,
    그러나 C<Base>리턴 안에서 C<Derived>의 서브타입은 안전할 수 있다.

    다시말해서, 너는 말할 수 있다. 클래스 C는 파라미터T에서 covariant이다. 또는
    T는 covariant type parameter라고,
    너는 C는 T의 생상자라고 생각 할 수 있다. T이 소비자가 아니라.

    out 수정자는 variance annotation이라고 불린다.
    그리고 그것은 type parameter declaration site에서 제공하므로,
    그것은 declaration-site variance를 제공한다.
    이것은 자바의 이 타입이 사용되는 와일드카드가 만드는 type covariant의 use-site variance와 대조된다.

    out 외에도, 코틀린은 보완적인 variance annotation 'in'을 제공한다.
    그것은 type parameter contravariant를 만든다. 그것은 의미한다. 소비될 수 있고, 결코 생산될 수 없다는 것을.
    contravariant type의 좋은 예는 Comparable이다.

    interface Comparable<in T> {
        operator fun compareTo(other: T): Int
    }

    fun demo(x: Comparable<Number>) {
        x.compareTo(1.0) // 1.0 has type Double, which is a subtype of Number
        // Thus, you can assign x to a variable of type Comparable<Double>
        val y: Comparable<Double> = x // OK!
    }

    그 단어 in, out은 self-expanatory(자명한것)처럼 보인다.
    (그들이 이미 성공적으로 꽤 많이 C#안에서 사용되었기 때문에)
    그리고 위에서 언급한 mnemonic은 실제로 필요하지 않다.
    실제로 더 높은 수준의 추상화 단계에서 다음과 같이 바꾸어 말할 수 있다.

    - The Existential Transformation: Consumer in, Producer out!




    // ===================
    // Type projections
    // ===================

    //Use-site vaiance: type projections

    type parameter T를 out으르 선언하는것은 매우 쉽다. 그리고 use-site에서 subtyping으로 문제를 피하는것도.
    그러나 몇몇 클래스는 실제로 T만 반환 하도록 제한 할 수 없다.
    좋은 예는 이 Array이다.

    class Array<T>(val size: Int) {
        operator fun get(index: Int): T { ... }
        operator fun set(index: Int, value: T) { ... }
    }

    이 클래스는 또한 T내의 co- nor contravariant이 될 수 없다.
    그리고 이것은 특정 inflexibilites를 부과한다.
    다음 함수를 고려하라.

    fun copy(from: Array<Any>, to: Array<Any>) {
        assert(from.size == to.size)
        for (i in from.indices)
            to[i] = from[i]
    }

    그 함수는 한 array에서 다른거로 copy해야한다.
    다음 예제를 시도해보자.

    val ints: Array<Int> = arrayOf(1, 2, 3)
    val any = Array<Any>(3) { "" }
    copy(ints, any)
    // ^ type is Array<Int> but Array<Any> was expected

    여기서 친숙한 문제가 있다.
    Array<T>는 T에서 불변이다.
    그리고 Array<Int>도 Array<Any>도 서로의 하위 유형이 아니다.

    왜 안되나? 이것은 기대되지 않은 행동을 할 수 있기 때문이다.
    예를 들어, String을 from에 쓰기를 시도할 수 있다. 그리고 만약
    너가 실제로 Int배열에 전달하면 나중에 ClassCastExcetion이 발생한다.

    copy함수를 from에 쓰는것을 금지하기 위해,
    너는 다음을 할 수 있다.

    fun copy(from: Array<out Any>, to: Array<Any>) { ... }

    이것은 type projection이다.
    그리고 그것은 from이 간단한 array가 아니라는 것을 의미한다.
    그러나 차라리 제한된 (투영된) 배열임을 말한다.

    너는 오직 호출 할 수 있다. 메서드를. 타입파라미터 T를 리턴하는.
    그리고 이 경우에서 그것은 의미한다. 너는 오직 get()을 호출 할 수 있다고.
    이것은 우리의접근이다. use-site variance하기 위한, 그리고 그것은 자바의 Array<? extends Object>와 상응한다.
    약간 간단하지만.

    너는 in을 사용하여 project할 수 있다.

    fun fill(dest: Array<in String>, value: String) { ... }

    Array<in String>은 자바의 Array<? super String>과 상응된다.
    이것은 의미한다. 너는 CharSequence의 array나 Object의 array를 fill()함수에 넣을 수 있다는것과.




    // Star-projections
    떄로는 너는 type parameter에 대해서 모르지만, 안전하게 쓰고 싶다.
    그 안전한 방법은 제너릭 타입의 프로젝션과 같은것을 정의 하는 것이다.
    그 제너릭 유형의 모든 구체적인 인스턴스화는 해당 프로젝션의 하위가 될 것이다.


    코틀린은 star projection이라는 문법을 제공한다.

    - Foo<out T: TUpper>에 대해, T는 upper bound TUpper가 있는 covariant type이다,
    Foo<*>는 Foo<out TUpper>와 동일하다.
    이것은 T를 알 수 없을 때, Foo<*>에서 TUpper의 값을 안전하게 읽을 수 있음을 의미한다.

    - Foo<in T>에 대해서 T는 contravariant type parameter이다, Foo<*>는 Foo<in Nothing>과 동일하다.
    이것은 의미한다. T가 알려지지 않았을때, 안전한 방법으로 Foo<*>에 쓸 수 있는 것이 없음을 의미한다.

    - Foo<T : TUpper>, T는 upper bound TUpper가 있는 invariant불변 type parameter이다.
    Foo<*>는 Foo<out TUpper>와 읽는 거에 대해서 동일하고, Foo<in Nothing>은 쓰는 값에 대해서 동일하다.


    만약 제러닉 타입이 몇몇 타입 파라미터를 가지면,
    그들 각각은 독립적으로 projected된다.
    예를 들어, 만약 타입이 interface Funtion<in T, out U> 선언되면,
    너는 다음과 같이 star-projection을 사용할 수 있다.
    - Function<*, String> means Function<in Nothing, String>
    - Function<Int, *> means Function<Int, out Any?>
    - Function<*, *> means Function<in Nothing, out Any?>




    // ====================
    // Generic functions
    // ====================

    Calss는 type parameter를 갖는 유일한 선언식이 아니다.
    함수 또한 할 수 있다.

    type parameter는 함수의 이름 이전에 놓인다.

    fun <T> singletonList(item: T): List<t> {
        // ...
    }

    fun <T> T.basicToString(): String { // extension function
        // ...
    }


    제너릭 함수를 호출하기 위해,
    type parameter를 구체화해라, 호출부분에서 함수의 이름 뒤에.

    val l = singletonList<Int>(1)

    type arguments는 생략 될 수 있다. 컨택스트로 부터 추론된다면.
    그래서 다음 예는 잘 작동한다.

    val l = singletonList(1)




    // ====================
    // Generic constraints - 제너릭 제약.
    // ====================

    주어진 type parameter에 대한 대체할 수 있는, 모든 가능한 타입의 집합은
    제너릭 제약에 의해 제한 될 수 있다.


    // Upper bounds
    제약의 대부분 일반적 타입은 upper bound이다. 그리고 그것은 자바의 extends키워드와 같다.

    fun <T : Comparable<T>> sort(list: List<T>) { ... }

    콜론 이후의 구체화된 타입이 upper bound이다,
    Comparable<T>의 하위타입만 T를 대체할 수 있음을 나타낸다.
    예를들어,

    sort(listOf(1, 2, 3)) // Ok, Int is a subtype of Comparable<Int>
    sort(listOf(HashMap<Int, String>())) // Error: HashMap<Int, String> is not subtype of Comparable<HashMap<Int, String>>

    만약 구체화 된게 없으면, 기본 upper bound는 Any?이다.
    오직 하나의 upper bound만 <>안에 구체화 될 수 있다.
    만약 같은 type parameter가 하나의 upper bound보다 더 필요하다면,
    너는 seperate where-clause가 필요하다.

    fun <T> copyWhenGreater(list: List<T>, threshold: T): List<String>
        where T : CharSeqeunce,
              T : Compareable<T> {

        return list.filter { it > threshold }.map { it.toString() }
   }

    통과한 type은 만드시 where 절의 모든 조건을 동시에 충족해야한다.
    위의 예를 들어, T type은 반드시 CharSequence와 Comparable을 둘 다 구현해야한다.





    // ========================
    // Type erasure - 타입 삭제
    // ========================

    코틀린이 제러닉 선언 사용에 대해서 수행하는, 그 타입 안정성 체크는
    컴파일 타임에 수행된다.

    런타임에서 제러닉 타입의 인스턴스는 그들의 실제 타입 argument에 대해서 어떠한 정보도 갖지 않는다.

    그 타입 정보는 지워졌다고 말한다.

    예를 들어, Foo<Bar> 및 Foo<Baz?>의 인스턴스는
    Foo<*>로 지워진다.


    // generics type checks and casts
    type 삭제로 인해,
    제너릭 타입의 인스턴스가 런타임에 어떤 타입의 매개변수로 생성되었는지,
    일반적으로 확인할 방법이 없다.

    그리고 컴파일러는
    ints is List<Int>, list is T (type parameter)와 같은 is-checks를 금지한다.

    그러나 start-projected type에 대한 인스턴스 체크는 할 수 있다.

    if (something is List<*>) {
        something.forEach { println(it) } // The items are typed as 'Any?'
    }

    마찬가지로, 컴파일타임에 너가 이미 정적으로 체크된 인스턴스의 type arguments를 가지고 있으면,
    너는 만들 수 있다,
    비 제너릭한 타입의 부분을 포합하는 is-check or 캐스트를 만들 수 있다.

    이 경우 <>이 생략된다.

    fun handleStrings(list: MutableList<String>) {
        if (list is ArrayList) {
            // 'list' is smart-cast to 'ArrayList<String>'
        }
    }

    같은 문법이지만, type arguments가 생력된 경우.
    type aramuments를 가지지 않는 account로 캐스트를 위해 사용 될 수 있다.
    : list as Arraylist

    제너릭 함수의 type argument 또한 컴파일 시간에 확인 될 수 있다.

    함수 바디의 내부에, type parameters는 타입 체크를 위해 사용 될 수 없다.
    그리고 타입 파라미터에서 타입 캐스트는 (foo as T), 체크 되지 않는다.

    유일한 제외는 구체화된 type parameter르 갖는 인라인 함수다.
    그리고 그것은 그들의 인라인된 실제 type arguments가 각 호출에 있는거다.

    이것은 가능하게 한다. 타입체크와 캐스트를 type parameter를 위해서

    그러나, 위에 설명된 제한은 여전히 내부 체크 또는 캐스트에 사용된 제너릭 타입의 인스턴스를 위해 적용된다.

    예를 들어, 타입 체크에서 arg is T, 만약 arg가 제러닉 타입의 인스턴스에 해당되면, 그것의 타입은 여전히 지워진다.


    inline fun <reified A, reified B> Pair<*, *>.asPairOf(): Pair<A, B>? {
        if (first !is A || second !is B) return null
        return first as A to second as B
    }

    val somePair: Pair<Any?, Any?> = "items" to listOf(1, 2, 3)

    val stringToSomething = somePair.asPairOf<String, Any>()
    val stringToInt = somePair.asPairOf<String, Int>()
    val stringToList = somePair.asPairOf<String, List<*>>()
    val stringToSTringList = somePair.asPairOf<String, List<String>>() // Compiles but breaks type safety!
    // Expand the sample for more details



    // Unchecked casts

    List<String>과 같은 구체적인 유형 인수가 있는 제너릭 타입으로 타입 캐스트는
    런타임에 체크될 수 없다.

    이러한 검사되지 않은 캐스트는, 사용될 수 있다.
    타입 안정성이 암시되는 고급레벨 프로그램 로직에서, 그러나 컴파일러에 의해 직접적으로 추론 될 수는 없다.

    아래를 봐라.

    fun readDictionary(file: File): Map<String, *> = file.inputStream().use {
        // Read a mapping of strings to arbitrary elements.
    }

    // We saved a map with `Int`s into this file
    val intsFile = File("ints.dictionary")

    // Warning: Unchecked cast: `Map<String, *>` to `Map<String, Int>`
    val intsDictionary: Map<String, Int> = readDictionary(intsFile) as Map<String, Int>


    마지막 줄에 대해서 경고가 나타난다.

    그 컴파일러는 완벽하게 런타임에 체크할 수 없고, 보장하지 않는다.. map안에 그 값이 int라는 것을.

    검사되지 않는 캐스트를 피하기 위해서
    너는 프로그램 디자인을 바꿀 수 있다.

    위의 예에서 너는 사용 할 수 있다. DictionaryReader<T>와
    DictionaryWriter<T> 인터페이스를 다른타입에 대한 타입안전구현과 함께

    너는 도입 할 수 있다. 합리적인 추상화를 언체크된 캐스르를 구현디테일의 호출부분으로
    제너릭 variance를 적절하게 사용하는게 또한 도움이될 수 있다.

    제너릭 함수에 대해서
    구체화된 type parameter를 사용하는 것은 시킨다.캐스트를 arg as T 가 체크된거 같은.
    arg의 type이 자체적으로 사라지지 않는한.

    확인되지 않은 캐스트 경고는
    @Suppress("UNCCHECKED_CAST")로 발생하는 문 또는 선언에
    주석을 추가하여 억제할 수 있다.

    inline fun <reified T> List<*>.asListOfType(): List<T>? =
        if (all { it is T } )
            @Suppress("UNCHECKED_CAST")
            this as List<T> else
            null

    // jvm에서,
    배열타입 (Array<Foo>는 유지한다 정보를 그들의 지원진 타입에 대한
    그리고 배열 타입에 대한 타입 캐스트는 부분적으로 확인된다.
    element타입의 널 허용 여부 및 실제 타입 arguments는 여전히 지원진다.
    예를 들어,
    foo를 Array<List<String>?>로 캐스트는 성공이다.
    만약 foo가 array List<*>를 포함하는 배열이라면, 그게 널이든 아니든 관계없이.





    // ========================================
    // Underscore operator for type arguments - type argument에 대한 언더스코어 연산자.
    // ========================================

    언더스코어 연산자는 type arguments를 위해 사용 될 수 있다.

    다른 타입이 명시적으로 지정된 경우 argument의 유형을 자동적으로 추론하려면 이것을 사용해라,

    abstract class SomeClass<T> {
        abstract fun execute() : T
    }

    class SomeImplementation : SomeClass<String>() {
        override fun execute(): String = "Test"
    }

    class OtherImplementation : SomeClass<Int>() {
        override fun execute(): Int = 42
    }

    object Runner {
        inline fun <reified S: SomeClass<T>, T> run() : T {
            return S::class.java.getDeclaredConstructor().newInstance().execute()
        }
    }

    fun main() {
        // T is inferred as String because SomeImplementation derives from SomeClass<String>
        val s = Runner.run<SomeImplementation, _>()
        assert(s == "Test")

        // T is inferred as Int because OtherImplementation derives from SomeClass<Int>
        val n = Runner.run<OtherImplementation, _>()
        assert(n == 42)
    }
 */