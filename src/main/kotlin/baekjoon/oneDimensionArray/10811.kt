package baekjoon.oneDimensionArray

import java.util.StringTokenizer

/*

input
- N 바구니 크기(초기화는 1부터), M 바꿀횟수 (1~100)
- i, j (1 ≤ i ≤ j ≤ N)


output
- 왼쪽바구니부터, 순서대로 공백으로 출력

 */
fun main(args: Array<String>) {
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()

    val buket = IntArray(N) { i -> i + 1 }

    repeat(M) {
        st = StringTokenizer(br.readLine())
        val i = st.nextToken().toInt() - 1
        val j = st.nextToken().toInt() - 1

        var endIndex = j

        // 여기서 범위를 i~j까지 다 돌려버리면 다시 원상복구 되므로, 반만 돌려야함.
        // (j - i) 2의 배수일때는 짝수로 그냥 교환 되면 되고, 홀수면 중간값은 그냥 고정되어 있으면 되서 이렇게 2로 나누면 된다.?
        // 이게 j-i가 아니라, j+i가 되야하구나.. 이 부분이 어렵네
        // 범위 이렇게 구하는게 맞는지도 모르겠고...
        // 다른 사람 해둔거보니까 while문으로 start올리고, end내려서 start <end 비교해서 탈출하게 짜뒀는데,
        // 그게 훨씬 이해가 잘 된다.
        for (index in i .. (j + i) / 2) {
            val temp = buket[index]
            buket[index] = buket[endIndex]
            buket[endIndex] = temp
            endIndex--
        }
    }

    println(buket.joinToString(" "))
}