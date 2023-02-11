package baekjoon.string

/*
특정값이 입력으로 들어올 때,
아스키값으로 출력해주기
 */

fun main(args: Array<String>) {
    val input = readlnOrNull()!!.toCharArray()
    // val input = 'a'

    println(input[0].code)
}