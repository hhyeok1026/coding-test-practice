package baekjoon.oneDimensionArray

import java.util.StringTokenizer

/*

input
- N, M (1~100) // N번까지 바구니 1부터시작, M번 교환을 한다.
- i, j (1 <= i <= j <= N) // 교환할 바구니

output
- 공백으로 바구니에 든 공 출력

처음은 바구니에 같은번호가 적혀있다.

 */
fun main(args: Array<String>) {
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()

    val buket = IntArray(N) { i -> i + 1 } // 인덱스가 0시작, 바구니는 1로 시작하므로, 내용물 값도 보정

    repeat(M) {
        st = StringTokenizer(br.readLine())
        val i = st.nextToken().toInt() - 1 // 인덱스는 0부터 시작하므로, 인덱스 보정
        val j = st.nextToken().toInt() - 1

        val temp = buket[i]
        buket[i] = buket[j]
        buket[j] = temp
    }

    // println(buket.contentToString())
    println(buket.joinToString(" "))
}