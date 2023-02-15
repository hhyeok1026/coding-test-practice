package baekjoon.sort

import java.util.*

/*

input
- 점의 갯수 N (1 ~ 100,000)
- 좌표들 (x, y) (-100,000 ~ 100,000)

output
- x 좌표 먼저 정렬, 그다음 y좌표 순으로 출력

시간 타임아웃 되었는데 왜 맞았다고 나오는지 모르겠다.
2차원배열 compare쓰는거 약간 이해안됨. 오히려 그냥 내가 반복문으로 정렬하는게 나을듯하고..(시간이 더 걸리려나?)
 */
fun main(args: Array<String>) {

    val br = System.`in`.bufferedReader()

    val N = br.readLine().toInt()

    val array = Array(N) { IntArray(2) }

    for (i in 0 until N) {
        val st = StringTokenizer(br.readLine())
        array[i][0] = st.nextToken().toInt()
        array[i][1] = st.nextToken().toInt()
    }

    array.sortWith { o1, o2 ->
        if (o1[0] == o2[0]) {
            o1[1].compareTo(o2[1])
        } else {
            o1[0].compareTo(o2[0])
        }
    }

    // println(array.contentDeepToString())

    for (i in array) {
        for (j in i) {
            print("$j ")
        }
        println()
    }
}