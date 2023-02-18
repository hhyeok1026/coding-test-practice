package baekjoon.recursion

/*
피보나치 수 5

피보나치수는 0과 1로 시작한다

0번째는 0
1번째는 1
2번째는 바로 앞 두수의 합 (1)


fn = fn-1 + fn-2 (n > 2)

============================

input
- n번째 피보나치 수 (0 ~ 20)

output
- n번째 피보나치 수를 출력

 */
fun main() {
    val n = readln().toInt()

    println(fibonacci(n))
}

fun fibonacci(n: Int): Int {

    return if (n == 0) {
        0
    } else if (n == 1) {
        1
    } else {
        fibonacci(n - 1) + fibonacci(n - 2)
    }
}