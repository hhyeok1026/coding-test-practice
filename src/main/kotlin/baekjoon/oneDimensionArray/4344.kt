package baekjoon.oneDimensionArray

import kotlin.math.round

/*
// input
- 테스트 케이스 갯수
- 학생수N, N개의 점수들

// output
각 케이스 마다 평균을 내고
평균을 넘는 학생의 '비율'을 계산, 소수점 3째자리까지 출력, 뒤에 %붙여야함

생각보다 어려운 문제..

 */
fun main(args: Array<String>) {
    val caseNum = readlnOrNull()!!.toInt()

    repeat(caseNum) {
        val case = readlnOrNull()!!
        val caseArray = case.split(" ").map { it.toInt() }.toTypedArray()
        val studentNum = caseArray[0]
        var sum = 0

        for (i in 1 until caseArray.size) {
            sum += caseArray[i]
        }

        // 평균
        val average = sum / studentNum

        // 평균을 넘는 학생의 비율을 찾아야함. (평균넘은 수 / 전체학생수)
        var studentNumOfAverageAbove = 0
        for (i in 1 until caseArray.size) {
            if (average < caseArray[i]) {
                studentNumOfAverageAbove++
            }
        }

        val ratio =  (studentNumOfAverageAbove.toDouble() / studentNum)
        // println("ratio : $ratio")

        val ratioToPercentage = ratio * 100
        // println(ratioToPercentage)

        val rounded = round(ratioToPercentage * 1000) / 1000 // 반올림할 자리수 지정
        // println("rounded : $rounded")

        // 출력될 소수점 자리수 지정
        println("${String.format("%.3f", rounded)}%")
    }
}