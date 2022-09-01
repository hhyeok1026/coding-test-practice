package officialhome.concepts.e_Functions.builders

/*
    Using builders with builder type inference


    코틀린은 지원한다, build type inference (or builder inference),
    그것은 제너릭 빌러로 작업 할 때, 유용할 수 있다.
    컴파일러가 빌더 호출,
    타입 정보에 베이스를둔,
    람바 argument 내부의 다른 호출에 대해서,
    type arguments 추론하는것을 돕는다.

    buildMap 사용의 예를 고려하라.

    fun addEntryToMap(baseMap: Map<String, Number>, additionalEntry: Pair<String, Int>?) {
        val myMap = buildMap {
            putAll(baseMap)
            if (additionalEntry != null) {
                put(additionalEntry.first, additionalEntry.second)
            }
        }
    }

    여기는 일반적으로 타입 arguemnt를 추론하기 위한 정보가 충분하지 않다.
    그러나 빌러 추론은 그 호출을 분석 할 수 있다, 람다 arugemnt 내부의.
    putAll과 put()호출에 대한 type information에 근거하여
    컴파일러는 자동적으로 type argument를 추론 할 수 있다, String과 Number로 builMap() 호출의,
    Builder inference는 생략한다, generic builder를 사용하는 동한 타입 argument를 생략한다.




    // ==============================
    // Writing your own builders
    // ==============================

     // Requirements for enabling builder inference

     // 코틀린 1.7.0 이전에서, 빌더 함수에 대해서 빌더 추론을 가능한것은,
     -Xenable-builder-inference 컴파일 옵션이 요구된다.
     그옵션이 1.7.0에서 기본으로 가능하다.

     빌러 추론을 너의 소유의 빌더를 위해 작동하기 위해서,
     그것의 선언이 리시버와 함수타입의 빌러 람다 파라미터를 가지는것인지 확인해라.
     리시버 타입에 대해서 또한 두가지 요구사항이 있다.

     - 1. 그것은 사용해야한다, 두 argument를,
     빌더 추론은 추론할 예정인
     예를 들어.

    fun <V> buildList(builder: MutableList<V>.() -> Unit) { ... }

    // 주의해라, 타입 파라미터의 타입을 즉시 전달하는 것을,
    fun <T> myBuilder(builder: T.() -> Unit)같은 아직 지원되지 않는다.

    - 2. 그것은 제공해야한다, public 멤버나 확장을,
    그들의 시그니처에서 타입 파라미터와 일치하는 것을 포함하는.
    아래의 예처럼

    class ItemHolder<T> {
        private val items = mutableListOf<T>()

        fun addItem(x: T) {
            items.add(x)
        }

        fun getLastItem(): T? = items.lastOrNull()
    }

    fun <T> ItemHolder<T>.addAllItems(xs: List<T>) {
        xs.forEach { addItem(it) }
    }

    fun <T> itemHolderBuilder(builder: ItemHolder<T>.() -> Unit): ItemHolder<T> =
        ItemHolder<T>().apply(builder)

    fun test(s: String) {
        val itemHolder1 = itemHolderBuilder { // Type of itemHolder1 is ItemHolder<String>
            addItem(s)
        }
        val itemHolder2 = itemHolderBuilder { // Type of itemHolder2 is ItemHolder<String>
            addAllItems(listOf(s))
        }
        val itemHolder3 = itemHolderBuilder { // Type of itemHolder3 is ItemHolder<String?>
            val lastItem: String? = getLastItem()
            // ...
        }
    }


    // 지원되는 특징
    Builder inference는 지원한다.

    - 몇몇 타입 arguments를 추론하는것.
    fun <K, V> myBuilder(builder: MutableMap<K, V>.() -> Unit): Map<K, V> { ... }

    - 상호보완적인 하나의 호출내에 몇몇 빌더 람다의 타입 arguemnt를 추론하는것.

    fun <K, V> myBuilder(
        listBuilder: MutableList<V>.() -> Unit,
        mapBuilder: MutableMap<K, V>.() -> Unit
    ): Pair<List<V>, Map<K, V>> =
        mutableListOf<V>().apply(listBuilder) to mutableMapOf<K, V>().apply(mapBuilder)

    fun main() {
        val result = myBuilder(
            { add(1) },
            { put("key", 2) }
        )
        // result has Pair<List<Int>, Map<String, Int>> type
    }




    // ==============================
    // How builder inference works
    // ==============================

    // Postponed type variable

    Builder inference는 작동한다, 연기된 타입 측면에서,
    그것은 나타난다, 빌러 람다의 내부에서,
    빌터 추론분석 동안
    그 연기된 타입 변수는 타입 argument's type이다,
    그것은 추론의 과정에 있다
    그 컴파일러는 그것을 모으기 위해 사용한다, 타입 argument에 대해서 타입정보를.

    buildList()의 예를 고려해라.

    val result = buildList {
        val x = get(0)
    }

    여기 x는 지연된 타입 변수의 종류이다:
    그 get호출은 타입 E의 값을 리턴한다,
    그러나 E 스스로는 아직 고정되지 않았다.
    이 순간, E에 대해서 구체적인 타입이 알려지지 않았다.

    연기된 타입 변수의 값이 구체화된 타입과 연관될때,
    빌더 추론은 이 정보를 모은다, 빌더추론 분석의 끝에, 일치하는 타입 argument이 결과 타입을 추론하기위해
    예를 들어:

    val result = buildList {
        val x = get()
        val y: String = x
    } // result has the List<String> type inferred

    연기된 타입 변수가 String 타입의 변수로 할당되는것 이후에,
    빌더 추론은 그 정보를 얻는다, x는 String의 타입이라는것.
    이 할당은 빌더 람다안에 마지막 문장이다, 그래서 그 빌더 추론분석 끝난다,
    타입 arguemnt E 에서 String으로 추론의 결과로.

    주의하라, 너는 늘 호출할 수 있다,
    equals(), hashCode(), toString() 함수를
    연기된 타입 변수를 리시버로서 함께.


    // Contributing to builder inference results
    빌더 추론은 모을 수 있다, 다른 변수를, 타입정보의, 그 분셕 결과에 기여하는
    그것은 고려한다:

    - 메서드를 호출하는것, 타입 파라미터이 파입을 사용한 람다 리시버에서,

    val result - buildList {
        // Type argument is inferred into String based on te passed "value" argument
        add("value")
    } // result has the List<String> type inferred

    - 구체화하는것, 타입 파라미터의 타입을 리턴하는 호출에 대해서 기대되는을

    val result = buildList {
        // Type argument is inferred into Float based on the expected type
        val x: Float = get(0)
    } // result has the List<Float> type
    class Foo<T> {
        val items = mutableListOf<T>()
    }

    fun <K> myBuilder(builder: Foo<K>.() -> Unit): Foo<K> = Foo<K>().apply(builder)

    fun main() {
        val result = myBuilder {
            val x: List<CharSequence> = items
            // ...
        } // result has the Foo<CharSequence> type
    }

    - 전달하는것, 연기된 타입 변수의 타입을 메서드로, 구체화가 기대되는 타입의

    fun takeMyLong(x: Long) { ... }

    fun String.isMoreThat3() = length > 3

    fun takeListOfStrings(x: List<String>) { ... }

    fun main() {
        val result1 = buildList {
            val x = get(0)
            takeMyLong(x)
        } // result1 has the List<Long> type

        val result2 = buildList {
            val x = get(0)
            val isLong = x.isMoreThat3()
        // ...
        } // result2 has the List<String> type

        val result3 = buildList {
            takeListOfStrings(this)
        } // result3 has the List<String> type
    }

    - 가지는것, 람다 리시버의 멤버에서 호출가능한 참조를.

    fun main() {
        val result = buildList {
            val x: KFunction1<Int, Float> = ::get
        } // result has the List<Float> type
    }
    fun takeFunction(x: KFunction1<Int, Float>) { ... }

    fun main() {
        val result = buildList {
            takeFunction(::get)
        } // result has the List<Float> type
    }

    분석의 끝에서, 빌더 추론은 고려한다, 모든 수집된 타입정보를, 그리고
    시도한다 그것들을 결과 타입으로
    다음 예를 보라.

    val result = buildList { // Inferring postponed type variable E
        // Considering E is Number or a subtype of Number
        val n: Number? = getOrNull(0)
        // Considering E is Int or a supertype of Int
        add(1)
        // E gets inferred into Int
    } // result has the List<Int> type

    결과 타입은 대게 추체적인 타입, 그 수집된 타입정보에 일치하는, 분석동안
    만약 그 주어진 타입정보가 모순되거나 머지될수 없으면,
    컴파일러는 에러를 발생한다.

    주의하라, 코틀린 컴파일러는 빌러 추론을 사용한다,
    오직 일반적인 타입 추론이 타입  argument를 추론 할 수 없으면.
    이것은 의미한다, 너가 기여할 수 있다, 타입 정보를, 빌더 람다의 바깥에,
    그리고 드때 빌러 추론 분석이 요구 되지 않을 때
    다음을 고려하라.

    fun someMap() = mutableMapOf<CharSequence, String>()

    fun <E> MutableMap<E, String>.f(x: MutableMap<E, String> { ... }

    fun main() {
        val x: Map<in String, String> = buildMap {
            put("", "")
            f(someMap()) // Type mismatch (required String, found CharSequence)
        }
    }

    여기 타입 미스매치는 나타난다, 맵의 기대되는 타입이 그 빌러 람다의 바깥에서 구체화된다.
    그 컴파일러는 분석한다 모든 그 구문을 그 고정된 리시버타입 Map<in String, String>의 내부에서.

 */


































