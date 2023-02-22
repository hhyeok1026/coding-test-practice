package baekjoon.oneDimensionArray

import java.util.StringTokenizer

/*

N 개의 바구나,
1-N이 적힌 공,

M번 공을 넣으려고 한다.
이미 공이 들어가 있으면 공을 빼고 새로 넣는다.

input
- N, M (1~100, 1~100)
- M개의 공을 넣는 방법, i j k   i에서부터 j바구니까지, k번호의 공을 넣는다.  ( 1 ~ N )


output
- 1번바구니부터 N번까지 공의 번호를 공백으로 출력. 공이 없으면 0으로 출력

바구니가 1번부터 시작됨.


문제 설명이 좀 복잡해서 그리 좋은 문제는 아닌 듯 하다.
 */
fun main(args: Array<String>) {
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N =  st.nextToken().toInt()
    val M = st.nextToken().toInt()

    val buket = IntArray(N)

    for (index in 0 until M) {

        val line = br.readLine()
        st = StringTokenizer(line)

        val i = st.nextToken().toInt() - 1 // 인덱스 조정
        val j = st.nextToken().toInt() - 1 // 인덱스 조정
        val k = st.nextToken().toInt()

        for (index2 in i..j) {
            buket[index2] = k
        }
    }

    println(buket.joinToString(" "))
}