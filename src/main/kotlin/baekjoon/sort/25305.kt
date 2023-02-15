package baekjoon.sort

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

/*
input
- 응시자수 N (1~1000), 상을 받는 사람의 수 k (1~N)
- 각 학생의 점수 x (0~10,000)

output
- 상을 받는 사람의 점수
 */
fun main(args: Array<String>) {

    val br = BufferedReader(InputStreamReader(System.`in`))

    val firstLine = StringTokenizer(br.readLine())
    val N = firstLine.nextToken().toInt()
    val k = firstLine.nextToken().toInt()
    val scores = IntArray(N)

    // println("N: $N, k: $k")
    val scondLine = StringTokenizer(br.readLine())

    var index = 0
    while (scondLine.hasMoreTokens()) {
        scores[index] = scondLine.nextToken().toInt()
        index++
    }

    scores.sort()
    println(scores[scores.size - k])
}