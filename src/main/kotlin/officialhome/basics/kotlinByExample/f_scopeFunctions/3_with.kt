package officialhome.basics.kotlinByExample.f_scopeFunctions

// with
// 인수의 멤버에 간결하게 접근할 수 있는, 비 확장 함수이다.
// 멤버를 참조 할 때, 인스턴스의 이름을 생략 할 수 있다.

class Configuration(var host: String, var port: Int)

fun main() {

    val configuration = Configuration(host = "127.0.0.1", port = 9000)

    with(configuration) {
        println("$host:$port")
    }

    // instead of:
    println("${configuration.host}:${configuration.port}")
}