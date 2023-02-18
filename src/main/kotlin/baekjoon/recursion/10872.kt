package baekjoon.recursion

/*

input
- 첫째줄에 정수 N (0 ~ 12)

output
- N!를 출력

 */
fun main(args: Array<String>) {

    val N = readln().toInt()

    println(factorial(N))
}

fun factorial(N: Int): Int {

    return if (N == 0 || N == 1) {
        1
    } else {
        N * factorial(N - 1)
    }
}

