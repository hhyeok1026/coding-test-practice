package baekjoon.oneDimensionArray

import java.util.Scanner


/*
백준에서 틀렸다고 나옴.
*/

// 첫째줄에 정수의 갯수
// 둘째줄 정수가 공백으로 구별
// 셋째줄 찾으려는 정수

fun main(array: Array<String>) {
    val inputSize = readLine()?.toInt() ?: return
    val inputArray = Array(inputSize) { 0 }

    val scanner = Scanner(System.`in`)

    var index = 0

    repeat(inputSize) {
        inputArray[index] = scanner.nextInt()
        index++
    }

    // println(inputArray.contentToString())

    val seekValue = readLine()?.toInt() ?: return
    // println(seekValue)

    var output = 0

    inputArray.forEach {
        if (it == seekValue) {
            output++
        }
    }

    print(output)
}