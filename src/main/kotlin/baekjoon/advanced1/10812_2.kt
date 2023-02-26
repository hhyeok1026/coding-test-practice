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
함수하나로 끝내게 만들어보자.


5 1

1 5 1
ik      j
1 2 3 4 5
1 2 3 4 5


1 5 2
i k     j
1 2 3 4 5
2 3 4 5 1


1 5 3
i   k   j
1 2 3 4 5
3 4 5 1 2


1 5 4
i     k j
1 2 3 4 5
4 5 1 2 3


1 5 5
i       kj
1 2 3 4 5
5 1 2 3 4

 */

fun main(args: Array<String>) {
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    val buket = IntArray(n) { i -> i + 1}

    repeat(m) {
        st = StringTokenizer(br.readLine())
        val begin = st.nextToken().toInt() - 1 // i
        val end = st.nextToken().toInt() - 1  // j
        val mid = st.nextToken().toInt() - 1 // k

        rotate2(buket, begin, end, mid)
    }
    println(buket.joinToString(" "))
    br.close()
}

// 한 번 호출마다, mid-begin만큼 인덱스 밀어주는 로직
// kotlin range 사용하는게 헷갈리고 어렵다.
fun rotate2(buket: IntArray, begin: Int, end: Int, mid: Int) {

    val interval = mid - begin

    val temp = IntArray(interval)

    // temp에 덮어써놔야 할 것들 미리 저장.
    for ((tempIndex, i) in (begin until mid).withIndex()) {
        temp[tempIndex] = buket[i]
    }
    // println(temp.contentToString())

    for(i in begin .. end - interval) {
        buket[i] = buket[i + interval]
    }
    // println("밀어낸후.")
    // println(buket.contentToString())

    var tempIndex = 0
    for(i in end - interval + 1 .. end) {
        buket[i] = temp[tempIndex++]
    }
    // println("덮어쓴후.")
    // println(buket.contentToString())
}