package baekjoon.advanced1

import java.util.*

/*

input
- N, M (1 ~ 100)
- M번의 i, j, k (1 ≤ i ≤ k ≤ j ≤ N)

output
- 변경된 바구니를 공백으로 출력


// 배열의 특정범위를 찝어서, 그부분을 rotate시켜야함.
-> 지금 temp로 요소 하나만 받았는데,
이거를 배열로 받아서 for문에 함수안쓰고, 그냥 함수 하나로 끝낼 수도 있긴함.
 */

fun main(args: Array<String>) {
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    val buket = IntArray(n) { i -> i + 1}

    repeat(m) {
        st = StringTokenizer(br.readLine())
        val begin = st.nextToken().toInt() - 1
        val end = st.nextToken().toInt() - 1
        val mid = st.nextToken().toInt() - 1

        for (i in 0 until (mid - begin) ) { // 몇 번 옮겨가야하는지
            rotate(buket, begin, end)
        }
    }
    println(buket.joinToString(" "))
    br.close()
}

// 한 번 호출마다, begin~end까지의 범위를 왼쪽으로 한칸씩 밀기
fun rotate(buket: IntArray, begin: Int, end: Int) {
    val temp = buket[begin]
    for(i in begin until end) {
        buket[i] = buket[i+1]
    }
    buket[end] = temp
}