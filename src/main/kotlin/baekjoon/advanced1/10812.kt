package baekjoon.advanced1

import java.util.*

/*

input
- N, M (1 ~ 100)
- M번의 i, j, k (1 ≤ i ≤ k ≤ j ≤ N)

output
- 변경된 바구니를 공백으로 출력


// 배열의 특정범위를 찝어서, 그부분을 rotate시켜야함.
rotate하는 알고리즘을 알아봐야겠는데, 컬렉션은 통째로 되서.. 직접 구현을 해야할듯.
어려우니, 나중에 집에 가서 해봐야겠음.

 */

fun main(args: Array<String>) {
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    val buket = IntArray(n) { i -> i + 1}.toList()

    repeat(m) {
        st = StringTokenizer(br.readLine())
        val begin = st.nextToken().toInt() - 1
        val end = st.nextToken().toInt() - 1
        val mid = st.nextToken().toInt() - 1

        /*println("==============")
        println("repeat: $it")
        println("begin: $begin")
        println("end: $end")
        println("mid: $mid")*/

        /*
        i~j까지의 순서에서
        k를 기준으로 다시 "k~j~i"로 순서를 바꿔야함.
         */
        // (k-i만큼 왼쪽으로 한번식 옮겨가야하는 문제다)
        for (i in begin until (mid - begin) ) {
            // rotate(buket, begin, end)
            Collections.rotate(buket,-1)
        }
    }
    println(buket.joinToString(" "))
    br.close()
}

// begin~end까지의 범위를 왼쪽으로 한칸씩 밀어야한다.
fun rotate(buket: IntArray, begin: Int, end: Int) {

    for(i in begin until end) {
        buket[i] = buket[i+1]
    }
}