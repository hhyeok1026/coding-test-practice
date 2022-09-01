package officialhome.basics.kotlinByExample.h_ProductivityBoosters

// Named Arguments -> 이름 붙은 인수

// 순서대로 arguments를 넣을 수 있지만, 더 명확하고, 실수를 피하기 위해 named Arg를 제공함.

fun format(userName: String, domain: String) = "$userName@$domain"

fun main() {
    println(format("mario", "example.com"))
    println(format("domain.com", "username"))
    println(format(userName = "foo", domain = "bar.com"))
    println(format(domain = "frog.com", userName = "pepe"))
}

