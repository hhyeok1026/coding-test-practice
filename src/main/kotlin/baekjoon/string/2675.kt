package baekjoon.string

import java.util.Scanner

/*
input
- testcase
- 반복수, 문자열

output
- 각케이스별 새로 만들어진 문자열
*/
fun main(args: Array<String>) {

    val testCaseCount = readlnOrNull()!!.toInt()
    // val scanner = Scanner(System.`in`)

    repeat(testCaseCount) {
        val testCase = readlnOrNull()!!
        // println("testCase : $testCase")
        val testCases = testCase.split(" ")
        val repeatCount = testCases[0].toInt()
        val strings = testCases[1]

        for (char in strings) {
            repeat(repeatCount) {
                print(char)
            }
        }
        println()
    }
}