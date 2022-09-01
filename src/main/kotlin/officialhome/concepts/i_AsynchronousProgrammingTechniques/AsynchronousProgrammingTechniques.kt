package officialhome.concepts.i_AsynchronousProgrammingTechniques

/*
    Asynchronous programming
    techniques

    비동기 프로그래밍 기술

    수십년간, 개발자들로서 우리는 문제를 해결하기위해 직면해있다,
    blocking으로부터 우리의 프로그램을 어떻게 방지할지.

    우리가 데스크탑, 모바일, 심지어 서버사이드 프로그램을 개발하던
    우리는 우리의 유저가 기다리는것을 피하기를 원한다,
    또는 병목을 일으키는것을, 어플리케이션 확장을 방해하는.

    이 문제를 풀기위한 많은 접근이 있다.
    다음을 포함하여

    - Threading

    - Callbacks

    - Futures, promises, and others

    - Reactive Extensions

    - Coroutines

    코루틴 설명이전에, 다른 솔루션에대해서 간단하게 리뷰하자.




    // ===========
    // Threading
    // ===========

    스레드는 꽤 잘알려져 있다, 블로킹을 피하는 방법으로

    fun postItem(item: Item) {
        val token = preparePost()
        val post = submitPost(token, item)
        processPost(post)
    }

    fun preparePost(): Token {
        // make a request and consequently blocks the main thread
        return token
    }

    위코드에서 preparePost가 오래 러닝하는 프로세스라고 가정하고,
    그리고 끊임없이 유저 인터페이스를 블락 할 것이다.
    우리가 하기 원하는것은 분리된 스레드에서 그것을 실행하는거다.
    이거는 그때 우리가 블로킹으로부터 UI를 피할 것이다.
    이것은 매우 흔한 기술이다, 그러나 단점들이 있다.

    - 스레드는 싸지 않다. 스레드는 비용이 드는 컨텍스트 스위치르 요구한다.

    - 스레드는 무한이 아니다, 싱행된 스레드의 수는 스레드의 수는 기본 오퍼레이팅 시스템에의해 제한이 있다.
    서버사이드 어플리케이션에서, 이것은 주요 병목을 일으킨다.

    - 스레드는 늘 사용불가하다, 몇몇 플랫폼에서, 자바스크립트는 스레드를 지원하지 않는다.

    - 스레드는 쉽지 않다, 스레드 디버깅하는것이, 레이스 컨디션을 피하는 것은 우리가 멀티스레드된
    프로그래밍에서 겪는 흔한 문제이다.




    // ===========
    // Callbacks
    // ===========

    콜백과 함께 그 아이디어는 한 함수에 전달하는 것이다, 파라미터로서 다른 힘수에
    그리고 이것 하나가 프로세스가 완료되면 호출되는것이다.

    fun postItem(item: Item) {
        preparePostAsync { token ->
            submitPostAsync(token, item) { post ->
                processPost(post)
        }
    }

    fun preparePostAsync(callback: (Token) -> Unit) {
        // make request and return immediately
        // arrange callback to be invoked later
    }


    이것은 원칙적으로 더 엘레강스한 솔루션 같다, 그러나 몇가지 이슈가 있다.

    - 중첩된 콜백의 어려움. 콜백으로 사용된 함수는 대게,
    그것의 콜백이 필요할때 종종 끝난다.
    이것은 종종 자신의 콜백을 필요로 한다.
    이것은 이해 할 수 없는 코드로 중첩된 콜백으로 초래된다.
    이 패턴은 종종 타이틀된 크리스마스 트리로서 나타난다
    (괄호는 나타난다, 트리의 브랜치로서)

    - 오류처리가 복잡하다. 그 중첩된 모델은 에러핸들링을 만들고,
    이것에 대하 전파를 더 복잡하게 한다.

    콜백은 자바스크립트같은 이벤트루프 구조에서 흔히 쓰인다
    그러나 거기서도, 일반적으로 사람들은 다른 접근으로 옮겨갔다,
    promise나 reactive extensions같은거로




    // ==============================
    // Futures, promises, and others
    // ==============================

    future or promise뒤의 아이디어(이것은 또한 다른 용어로, 이것은 나타낼 수 있다, 랭귀지나 언어제 따라)
    는 우리가 호출을 만들때,
    우리는 promised한다, 몇몇 그것의 포인트를 Promise로 호출된 객체의 리턴이 될 것이라고,
    그리고 그것은 작동될수 있다.

   fun postItem(item: Item) {
        preparePostAsync()
            .thenCompose { token ->
                submitPostAsync(token, item)
            }
            .thenAccept { post ->
                processPost(post)
            }
   }

   fun preparePostAsync(): Promise<Token> {
        // makes request and returns a promise that is completed later
        return promise
   }

    이 접근은 요구한다, 우리의 프로그램이 일련의 변화를, 부분적으로

    - 다른 프로그래밍 모델. 유사하다 콜백하는것과, 그 프로그래밍 모델은
    프로그래밍 모델은 하향식 명령형 접근방식에서 연결 호출이 있는 구정 모델로 이동한다.
    전통적인 프로그램 구조, 루프, 예외핸들링 같은. 대게 이 모델에서 더 이상 타당하지 않다.

    - 다른 api. 있다 완전히 새로운 api를 배우기 위한게 필요할 수,
    thenCompose or thenAccept같은, 그것은 플랫폼에 따라 다양 할 수 있다.

    - 구체적인 리턴 타입.
    그 리턴 타입은 실제데이터에서 멀어지고,
    우리가 필요하고 자체 검사해야하는 새로운 타입 Promise를 대신 리턴한다.

    - 에러 핸들링이 복잡하다.
    그 전파 그리고 에러의 체이닝은 늘 간단한게 아니다.




    // =====================
    // Reactive extensions
    // =====================

    Reactive Extension (RX)는 C#에서 Erik Meijer에 의해 소개되었다.
    그것이 닷넷 플랫폼에서 사용되는 동안, 그것은 메인스트림으로 채택되지 못했다,
    넷플릭스가 그것을 RxJava라는 이름으로 자바로 이식하기 전까지는.

    그때부터,
    다양한 포트가 제공되었다, JavaScript (RxJS)를 포함한 다양한 플랫폼에 대해서

    Rx뒤의 아이디어는 옮기는것이다, ovservale streams라 불리는것을
    어디든지 우리가 데이터를 생각하는 것을 스트림으로 (데이터의 무한한 양) 그리고 이 스트림은 observed 될 수 있다.
    실용적인 관점에서 Rx는 단순히 Observer Pattern이다, 일련의 확장이 있는, 우리가 그 데이터를 운영하기 위한.

    그것은 Future와 비슷하게 접근된다, 그러나 Future의 한가지 생각 할 수 있는것은 이산 값을 리턴하는것이다,
    반면 Rx는 스트림을 리턴한다.
    그러나 이전에 유사하다, 그것은 또한 생각의 완전한 방식을 소개한다, 우리의 프로그래밍 모델에서, 유명한 구문으로

    "everything is a stream, and it's observable"

    이것은 다른 방법을 함시한다, 문제를 접근하는것과 꽤 중요한 이동, 우리가 사용했던것으로부터
    동기화 코드를 작성 할때

    Futures에 반대되는 하나의 이점으로서 너무 많은 플랫폼에 포팅된다,
    일반적으로 우리는 consistent API를 경험을 찾을 수 있다, 우리가 사용에 대한 문제 없이,
    C#, Java, JavaScript, 다른 랭기지도 Rx가 가능하다.

    게다가, Rx는 더 나는 에러핸들링에 대해서 소개한다.




    // ============
    // Coroutines
    // ============

    코틀린은 코루틴을 사용하여 비동기 코드에 작업하는것을 접근한다,
    그것은 중지될 수 있는 계산의 생각이다.
    예를들어 그 아이디어, 함수는 그것의 실행을 몇몇 포인트에서 중지 할 수 있고 나중에 재개된다.

    그러나 코루틴의 이점이 있다,
    개발자가 그것을 할때, non-blocking 코드를 스는것은 같다, bocking 코드를 쓰는것과
    그것 스스로 프로그래밍 모델은 실제로 변하지 않는다.

    예를들어 다음코드를 보세요.

    fun postItem(item: Item) {
        launch {
            val token = preparePost()
            val post - submitPost(token, item)
            processPost(post)
        }
    }

    suspend fun preparePost(): Token {
        // makes a request and suspends the coroutine
        return suspendcoroutine { ... }
    }


    이 코드는 메인스레드 블록킹 없이 오랜 작업을 실행할것이다.
    preparePost는 suspend fuction을 호출한다,
    그래서 그 키워드 suspend 가 prefixing된다.
    위에 보이는게 의미하는 것은, 함수는 실행될것이고, 중지 될 것이다, 그리고 몇몇 포인트에서 재개될 것이다.

    - 그 함수시그니처는 동일하게 유지도니다.
    그 오직 다른것은 suspend가 추가되는것이다.
    그러나 그 리턴 타입은 우리가 리턴 되기 원하는 타입이다.

    - 그 코드는 여전히 쓰여진다, 마치 우리가 동기 코드를 쓰듯이, 하향식코드, 어떤 특별한 문법 없이,
    함수 사용의 뒤편에 lauch가 불려진다, 그것은 필수적으로 코루틴을 실행한다 ( 다른 튜토리얼에서 다뤄진다)

    - 그 프로그래밍 모델과 api는 동일하게 유지된다.
    우리는 계속 루프를 사용 할 수 있다, 예외 처리도, 그리고 새 api의 완전한 set을 배울 필요가 없다.

    - 플랫폼 독립적이다. 우리가 JVM을 타겟팅하던, JS나 다른 플랫폼을 하던, 그 코드는 우리가 작성한거는 같다.
    내부적으로 컴파일러가 각가의 플랫폼에 적용하는것을 다룬다.


    코틀린은 새로운 컨셉이 아니다, 코틀린에 개발된것도 아니고,
    그들은 수세기동안 있었고,
    그리고 유명하다, 다른 프로그래밍 언어 GO같은곳에서
    하지만 중요점은 코틀린에서 구현하는 방식이다,
    함수적인 대부분은 라이브러리로 delegated된다.
    사실 suspend키워드 뒤에, 다른 키워드가 추가되는게 없다.
    이것은  C#같은 언어와 다른것이다, async await를 문법으로 가지는
    코틀린에서 이것은 단지 라이브러리 함수다



 */





























