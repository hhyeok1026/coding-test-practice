package baekjoon.string

/*

input
- 단어 S ( ~ 1000 ), 정수i (1 ~ 1000)

output
- S의 i번째 글자를 출력하는 프로그램을 작성!

*/

fun main(args: Array<String>) {

    val br = System.`in`.bufferedReader()
    val s = br.readLine()
    val i = br.readLine().toInt()

    println(s[i-1])

    br.close()
}