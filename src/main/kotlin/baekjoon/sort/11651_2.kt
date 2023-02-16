package baekjoon.sort

import java.util.*

/*

input
- 점의 개수 N ( 1 ~ 100,000)
- N개의 x, y ( -100,000 ~ 100,000 )

output
- y 우선 정렬, 그 뒤에 x정렬


2트 - 예전꺼에서 수정해서 풀어야겠다..

이것도 2000ms넘어서 틀렸다고 나와야할거 같은데, 맞는걸로 쳐지네.
누군가는 퀵소트 + point객체를 만들어서 풀었다.

누군가는 리스트에 넣고, 리스트정렬을 시켰고, 출력할때는 StringBuilder로 출력했다.
StringBuilder로 한번만 출력하니까 시간이 반토막으로 줄어들었다.
 */

fun main(args: Array<String>) {
    val br = System.`in`.bufferedReader()

    val N = br.readLine().toInt()
    val array = Array(N) { IntArray(2) }

    for (i in 0 until N) {
        val st = StringTokenizer(br.readLine())
        val x = st.nextToken().toInt()
        val y = st.nextToken().toInt()

        array[i][0] = x
        array[i][1] = y
    } // 입력받기 끝
    // println(array.contentDeepToString())

    array.sortWith { o1, o2 ->
        if (o1[1] == o2[1]) {
            o1[0].compareTo(o2[0])
        } else {
            o1[1].compareTo(o2[1])
        }
    }

    /*for (row in array) {
        println(row.joinToString(" "))
    }*/

    val sb = StringBuilder()
    for (row in array) {
        for (col in row) {
            sb.append("$col ")
        }
        sb.append("\n")
    }
    println(sb)
}