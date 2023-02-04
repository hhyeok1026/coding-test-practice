package baekjoon.oneDimensionArray

import java.util.Scanner

/*
결국 내 힘으로 맞은 문제
readline 으로 입력받으니 문제가 틀렸음. 왜? 몰라!
*/

/*
입력
- 1. 정수의 개수
- 2. 정수가 공백으로 구별
- 3. 찾으려는 정수

출력
찾으려는 정수의 개수

*/

fun main(array: Array<String>) {

    val scanner = Scanner(System.`in`)

    val inputSize: Int = scanner.nextInt()
    val inputArray = Array(inputSize) { 0 }

    for (index in 0 until inputSize) {
        inputArray[index] = scanner.nextInt()
    }
    //println(inputArray.contentToString())

    val seekValue: Int = scanner.nextInt()
    //println(seekValue)

    var output = 0

    for(i in inputArray) {
        if(i == seekValue) {
            output++
        }
    }

    println(output)
}
