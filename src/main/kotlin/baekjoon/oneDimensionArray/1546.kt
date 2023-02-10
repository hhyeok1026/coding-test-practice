package baekjoon.oneDimensionArray

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

/*
세준이 점수조작.
점수들/최대값*100으로 수정
새로운 평균값을 구하라.


input
- 시험본 과목 갯수 (< 1000)
- 성적들 (0~100)

output
- 새로운 평균 (실제 정답과 출력값의 절대오차 또는 상대오차가 10 -2이하여야한다. (?) 자료형을 큰거를 써라는건가?)


이번에는 코틀린에 버퍼리더로 구현해보자.
 */

fun main(args: Array<String>) {

    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val numOfSubject = bufferedReader.readLine().toInt()

    // println("numOfSubject : $numOfSubject")

    val subjectArray = Array<Int>(numOfSubject) { 0 }
    val recalculatedSubjectArray = Array<Double>(numOfSubject) { 0.0 }

    //-------------------------------

    // 1. 입력받은것들 중에서 최댓값을 찾아야한다.
    // 2. 최대값찾은거로 배열을 다시 계산해내야한다.
    // 3. 계산된 배열을 평균내면 끝.

    val token = StringTokenizer(bufferedReader.readLine())
    var i = 0
    var max = 0
    while (token.hasMoreTokens()) {
        subjectArray[i] = token.nextToken().toInt()
        // 1.
        if(subjectArray[i] > max) {
            max = subjectArray[i]
        }
        i++
    }

    // println(subjectArray.contentToString())
    // println("max : $max")

    // 2.
    subjectArray.forEachIndexed { index, item ->
        recalculatedSubjectArray[index] = (item.toDouble() / max * 100)
    }

    // 3.
    println(recalculatedSubjectArray.average())
}