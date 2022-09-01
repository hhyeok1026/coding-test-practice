package officialhome.concepts.d_ClassAndObject

/*
    Type aliases

    Type aliases는 존재하는 타입에 대해서 대안적인 이름을 제공한다.
    만약 그 타입 이름이 너무 길다면, 너는 다른 짧은 이름을 도입할 수 있고, 새로운것을 대신 사용 할 수 있다.

    그것은 긴 제너릭 타입을 짧게 하기 위해서 유용하다.
    예를 들어, 종종 컬렉션 타입을 줄이는 것을 시도한다.

    typealias NodeSet = Set<Network.Node>

    typealias FileTable<K> = MutableMap<K, MutableList<File>>


    너는 함수타입에 대해서 다른 alias를 제공 할 수 있다.

    typealias MyHandler = (Int, String, Any) -> Unit

    typealias Predicate<T> = (T) -> Boolean


    너는 이너와 네스티드 클래스에 대해서 새로운 이름을 가질 수 있다.

    class A {
        inner class Inner
    }

    class B {
        inner class Inner
    }

    typealias AInner = A.Inner
    typealias BInner = B.Inner


    type aliases는 새로운 타입을 가지는게 아니다,
    그들은 기존 타입과 동일하게 일치한다.
    너가 typealias Predicate<T>를 추가하고, Predicate<Int>를 사용할 때,
    그 코틀린 컴파일러는 늘 그것을 (Int) -> Boolean으로 확장한다.
    그래서 너는 너의 타입을 제너릭 함수타입이 요구 될 때나, 그 반대에서도 사용가능하다.

    typealias Predicate<T> = (T) -> Boolean

    fun foo(P: Predicate<Int>) = p(42)

    fun main() {
        val f: (Int) -> Boolean = { it > 0 }
        println(foo(f)) // prints "true"

        val p: Predicate<Int> = { it > 0 }
        println(listOf(1, -2).filter(p)) // prints "[1]"
    }
 */