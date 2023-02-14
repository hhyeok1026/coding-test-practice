package baekjoon.twoDimensionArray

import java.io.BufferedReader
import java.io.InputStreamReader

/*

// input
- 9 * 9 행렬의 요소값 (0~99)

// output
- 최대값 몇인지
- 행 열


split보다 StreamTokenizer, StringTokenizer를 쓰는게 속도가 빠른듯.
 */
fun main(args: Array<String>) {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val array = Array(9) { IntArray(9) }
    var max = 0
    var rowIndex= 0
    var columnIndex = 0


    // 입력받으면서 찾으면 되는데 자료형은 필요 없는게 아닌가

    for (i in array.indices) {

        val row = br.readLine().split(" ").map { it.toInt() }

        for (j in array[i].indices) {
            array[i][j] = row[j]
            if (row[j] > max) {
                max = row[j]
                rowIndex = i
                columnIndex = j
            }
        }
    }

    println(max)
    println("${rowIndex + 1} ${columnIndex + 1}") // 여기서 표기하는 행열이 1부터 시작하므로 +1
}