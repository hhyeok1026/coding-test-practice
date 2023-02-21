package baekjoon.inputOutput

import java.util.StringTokenizer

/*

input
- a b c (1~10의 12승) (10,000,000,000) (100억)

(부호있는 int가 21억까지 표현가능)
따라서, Long으로 변환해야함. (9천조까지 표현가능)

output
- a+b+c

 */
fun main(args: Array<String>) {
    val br = System.`in`.bufferedReader()
    val st = StringTokenizer(br.readLine())

    val a = st.nextToken().toLong()
    val b = st.nextToken().toLong()
    val c = st.nextToken().toLong()

    println(a+b+c)
}