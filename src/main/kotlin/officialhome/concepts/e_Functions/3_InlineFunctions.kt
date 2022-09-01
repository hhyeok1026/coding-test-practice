package officialhome.concepts.e_Functions

/*
    Inline functions

    고차함수를 사용하는것은 어떤 런타임 패널티를 부과한다, 함수는 오브젝트이고,
    그것은 클로저를 포착하는것 같은.
    클로저는 변수의 스코프이다, 함수의 바디내에서 접근 할 수 있는.
    메모리 할당 (함수객체와 클래스 둘다) 과 가상 호출은 런타임 오버헤드를 유발한다.

    그러나 그것은 나타난다, 많은 케이스에서 이 오버헤드의 종류가, 인라잉 람다식으로 제거 될 수 있는.
    아래 보여진 함수는 이 상황의 좋은 예이다.
    그 lock() 함수는 쉽게 호출부에서 인라인 될 수 있다.
    다음 케이스를 고려하라.

    lock(l) { foo() }

    파라미터에 대한 함수객체를 만드는것과 호출을 생성하는것 대신에,
    컴파일러는 다음코드를 생성 할 수 있다.

    l.lock()
    try {
        foo()
    } finally {
        l.unlock()
    }

    컴파일러를 이렇게 만들기 위해서,
    lock() 함수를 마크 할 수 있다, inline modifier로

    inline fun <T> lock(lock: Lock, body: () -> T): T { ... }

    그 inline 수정자는 영향을 준다, 그것 자신의 함수와, 그것에 전달되는 람다에,
    그것들의 모든것은 호출부로 인라인 될 것이다.

    인라인은 생성된 코드를 증가시킬 수 있다, 그러나 만약 너가 그것을 합리적인 이유에서 한다면,
    (큰 함수를 인라인 하는것을 피하라), 그것은 성능이 향상된다, 특히
    루프내의 megamorphic(변형) 호출 사이트에서.




    // ==============
    // noinline
    // ==============

    만약 너가 전달된 람다의 모든것을 원하지 않는다면, 인라인함수에서 인라인 되는것을,
    너의 함수 파라미터의 몇몇을 noinline modifier로 마크해라.

    inline fun foo(inlined: () -> Unit, noinline notInline: () -> Unit) { ... }

    인라인 할 수 있는 람다는 오직 호출 된다, 인라인 함수 내에서, 또는 전달된 인라인 할 수 있는 argument에서/
    noinline 람다는 그러나, 조작 되어질 수 있다, 니가 좋아하는 방식으로, 필드에 저장되거나 주위에 전달되는는

    // 만약 인라인 함수가 no inlinable 함수와 no reified type파라미터를 가지면, 그 컴파일러는 이슈를 만들것이다,
    함수같은것을 인라인하는 것은 이점이 매우 없기 때문이다. (너는 @Suppress("NOTHING_TO_INLINE") 주석을
    너가 인라인이 필요할때 경고를 막기 위해서 어노테이션 할 수 있다.




    // ===================
    // Non-local returns
    // ===================

    코틀린에서,
    너는 오직 nomal만 사용할 수 있다,
    제한되지 않은 return을 나가기위해서, 이름있거나 없는 함수에 대해서

    람다를 나가기 위해서, 사용해라 label을.
    bare (없는) return은 금지되어 있다, 람다내부에서,
    람다는 둘러싸는 함수를 반환 할 수 없기 때문에,

    fun foo() {
        ordinaryFunction {
            return // ERROR: cannot make 'foo' return here
        }
    }

    그러나 만약 그 람다가 전달되는 함수가 인라인이면,
    return 도 인라인 될 수 있다.
    따라서 다음이 가능하다.

    fun foo() {
        inlined {
            return // OK: the lambda is inlined
        }
    }

    그런 return (람다에 위치된, 그러나 닫힌 함수가 존재하는) 불려진다, non-local return으로.
    이러한 종류의 구성은 대게 루프에서 발생한다, 그리고 인라인 함수는 종종 enclose이다.

    fun hasZeros(ints: List<Int>): Boolean {
        ints.forEach {
            if (it == 0) return true // returns from hasZero
        }
        return false
    }

    주의하라, 몇몇 인라인 함수는 함수 바디로부터 직접 전달되지 않은
    매개변수로 전달된 람다를 호출 할 수 있다.
    그러나 다른 실행 컨텍스트로부터, 로컬 객체나 중첩된 함수 같은.
    이 같은 케이스에서, non-local 컨트롤 플로우는 또한 람다에서 허락되지 않는다.
    표시하기위해서, 인라인 함수의 람다파라미터가 non-local returns로 사용되지 않는것을,
    마크해라, 람다파라미터를 crossinline modifier로.

    inline fun f(crossinline body: () -> Unit) {
        val f = object: Runnable {
            override fun run() = body()
        }
    }

    // break와 continue는 아직 인라인 람다에서 불가하다,
    그러나 우리는 그것들을 역시 지원할 예정이다.




    // =========================
    // Reified type parameters
    // =========================

    때때로 너는 파라미터로서 전달된 타입에 접근하는것이 필요하다.

    fun <T> Tree.Node.findParentOfType(clazz: Class<T>: T? {
        var p = parent
        while (p != null && !clazz.inInstance(p)) {
            p = p.parent
        }
        @Suppress("UNCHECKED_CAST")
        return p as T?
    }

    여기, 너는 나무로 올라가, 노드가 어떤 타입을 가지는지 체크하기 위해서
    리플렉션을 사용한다.
    그것은 다 좋다, 그러나 그 호출부는 별로 예쁘지 않다.

    treeNode.findParentOfType(MyTreeNode::class.java)

    더 나은 솔루션은 간단하게 타입이 이 함수로 전달 될 수 있다.
    너는 그것을 다음과 같이 호출 할 수 있다.

    treeNode.findParentOfType<MyTreeNode>()

    이것을 해결하기 위해서, 인라인 함수는 reified type파라미터를 지원한다,
    그래서 너는 이것 처럼 어떤것을 쓸 수 있다.

    inline fun <refied T> TreeNode.findParentOfType(): T? {
        var p = parent
        while (p != null && p !is T) {
            p = p.parent
        }
        return p as t?
    }

    위의 코드는 qualifies한다 그 타입 파라미터를 reified modifier로, 만들기 위해 그것을 접근가능하게
    함수 내부에서,
    대게 일반 클래스인것처럼. 함수는 인라인이기 때문에,
    리플렉션이 필요로 하지 않다, 그리고 노멀 operator, !is , as 같은 너가 사용이 가능하다.
    또한, 너는 함수를 호출할 수 있다, 위에 보이는것 처럼
    myTree.findParentOfType<MyTreeNodeType>().

    비록 리플렉션은 많은 경우 필요없을 수 있다,
    너는 여전히 그것을 refied type파라미터와 함께 사용 할 수 있다.

    inline fun <refied T> membersOf() = T::class.members

    fun main(s: Array<String>) {
        println(membersOf<StringBuilder>().joinToString("\n"))
    }

    일반 함수 ( 인라인으로 마크된게 아닌 )는 reified 파라미터를 가질 수 없다.
    런타임 표현을 가지지 않는 타입은 (예를들어, non-refied type parameter or a fictious type like Nothing)
    refied type parameter로 사용 될 수 없다.




    // ==================
    // Inline properties
    // ==================

    그 inline modifier는 사용될 수 있다, 프로퍼티의 accessors로, backing fields를 가져서는 안된다.
    너는 독집적으로 프로퍼티 액세서를 어노테이트 할 수 있다.

    val foo: Foo
        inline get() = Foo()

    var bar: Bar
        get() = ...
        inline set(v) { ... }

    너는 또한 전체 프로퍼티에 어노테이트 할 수 있다, 그것은 inline으로 그것의 엑세서의 둘다에 마크한다.

    inline var bar: Bar
        get() = ...
        set(v) { ... }

    호출부에서, 인라인 엑세서는 인라인 된다, regular 인라인 함수로.




    // ==============================================
    // Restrictions for public API inline functions
    // ==============================================

    인라인 함수가 public이나 protected일때,
    그러나 priave나 internal선언이 아니고,
    그것은 고려된다, 모듈의 public api로
    그것은 호출 될 수 있다, 다른 모듈에서, 그리고 이러한 호출 사이트에서도 인라인 된다.

    이것은 증가한다, 그 호출 모듈이 변경이후에 다시 컴파일 되지 않는 케이스에서,
    인라인 함수로 선언된, 모듈의 변경에 의해 바이너리 불호환을 일으키는 어떤 리스크가

    그 같은 불호환의 리스크를 제거하려면,, 모듈의 non-public한 변경에 의해,

    public api 인라인 함수는 non-public-api 선언으로 사용되는거,
    즉, private 와 internal선언, 그리고 그들의 parts, 그들의 바디에서
    허락되지 않는다

    interanl declaration은 @PublishedApi로 주석을 달 수 있고,
    이를 통해 public api inline functions에서 사용 될 수 있다.

    interanl function이 @PublishedApi로 마크 될 때, 그것의 바디는
    체크된다, 마치 public인것 처럼.







 */





















