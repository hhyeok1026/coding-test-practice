package baekjoon.twoDimensionArray

import java.io.BufferedReader
import java.io.InputStreamReader

/*

// input
N M (N * M 크기의 두 행렬 A, B) (N, M ~100)
A의 행 N개 (원소는 ~100)
B의 행 N개

// output
두 행렬을 더 한 것을 출력

더 좋은 연산자나 메서드없나? 코드가 길어진다..
 */

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (N, M) = br.readLine().split(" ").map { it.toInt() }

    val A = Array(N) { IntArray(M) }
    val B = Array(N) { IntArray(M) }

    // println("$N, $M")

    for (i in 0 until N * 2) {

        val row = br.readLine().split(" ").map { it.toInt() }

        if(i < N) {
            for (j in 0 until M) {
                A[i][j] = row[j]
            }
        } else {
            for (j in 0 until M) {
                B[i - N][j] = row[j]
            }
        }
    }

    // println(A.contentDeepToString())
    // println(B.contentDeepToString())

    for (i in 0 until N) {
        for (j in 0 until M) {
            if (j != M -1) {
                print("${A[i][j] + B[i][j]}")
                print(" ")
            } else {
                print("${A[i][j] + B[i][j]}")
            }
        }
        println()
    }
}