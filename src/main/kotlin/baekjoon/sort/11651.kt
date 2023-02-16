package baekjoon.sort

import java.util.StringTokenizer

/*
(시간초과 1초인데, 시간초과로 걸림)
(로직이 맞는지도 약간 의문임)

input
- 점의 개수 N ( 1 ~ 100,000)
- N개의 x, y ( -100,000 ~ 100,000 )

output
- y 우선 정렬, 그 뒤에 x정렬

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

    // y정렬
    for (i in array.indices) {
        for (j in array.indices) {
            if (j < array.size - 1) {
                if (array[j][1] > array[j+1][1]) {
                    val tempY = array[j][1]
                    array[j][1] = array[j+1][1]
                    array[j+1][1] = tempY

                    val tempX = array[j][0] // 좌표는 같이 셋트니까 x좌표도 같이 옮겨가야함
                    array[j][0] = array[j+1][0]
                    array[j+1][0] = tempX
                 }
            }
        }
    }
    // x정렬
    for (i in array.indices) {
        for (j in array.indices) {
            if (j < array.size - 1) {
                if (array[j][1] == array[j+1][1]) { // y값은 같은 상황에서, y같은 같은 상황에서, x값만 바꿔주면 됨.
                    if (array[j][0] > array[j+1][0]) { // x 값 정렬
                        val temp = array[j][0]
                        array[j][0] = array[j + 1][0]
                        array[j+1][0] = temp
                    }
                }
            }
        }
    }

    // 출력
    /*for (i in array) {
        for (j in i) {
            print("$j ")
        }
        println()
    }*/
    val sb = StringBuilder()
    for (i in array) {
        for (j in i) {
            sb.append("$j ")
        }
        sb.append("\n")
    }
}