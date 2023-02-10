package baekjoon.oneDimensionArray

import java.util.Scanner


/*
과제 안내신분
반 30명,
28명 제출
2명 안낸사람
안낸 2명의 출석번호를 구하라

--------------------

input
총 28줄

output
제출하지 않은 출석중, 오름차순 출력.

 */
fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val submitCheckArray = Array<Int>(30) {0}
    var output1 = -1
    var output2 = -1

    // 출석번호는 1부터 시작이라서 index조정
    while (scanner.hasNext()) {
        val index = scanner.nextInt() - 1
        //println("index : $index")
        submitCheckArray[index] = 1
    }

    // (구조분해 될 때 앞에것이 인덱스, 뒤에가 element로 분해된다.)
    for ((index, e) in submitCheckArray.withIndex()) {
        //println("submitCheckArray : $index, $e")
       if (e == 0) {
           //println("출석안한 index: $index")
           if(output1 == -1) {
               output1 = index + 1 // 출석번호는 1부터 시작이라서 index조정
           } else {
               output2 = index + 1
           }
       }
    }

    if (output1 < output2){
        println(output1)
        println(output2)
    } else {
        println(output2)
        println(output1)
    }
}