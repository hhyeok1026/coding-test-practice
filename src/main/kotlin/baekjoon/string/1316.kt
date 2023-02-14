package baekjoon.string

import java.io.BufferedReader
import java.io.InputStreamReader


/*

// input
- 들어올 단어갯수 N (1~100)
- 단어들 (~100)

// output
- 그룹단어의 개수를 출력

---------------------------

특정 문자가 연속적으로 나오면 그룹단어
문자가 떨어지게 되면 그룹단어가 아니다

일단, 입력부터 받아야하는데,
이제는 버퍼리더를 써보자.
 */

fun main(args: Array<String>) {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()

    var count = 0
    repeat(N) {
        val word = br.readLine()

        if(isGroupWord(word))
            count++
    }

    println(count)
}
fun isGroupWord(word: String): Boolean {

    // 이전에 나왔는지 확인할 배열
    // 이전에 검사했던 문자
    // 현재 검사할 문자,
    // 이전 == 현재 문자 일치하면 ok
    // 이전 현재 문자가 다른데, 이전에 나왔던게 배열에 체크되어 있으면 false
    // 반복문 끝나고 문제 없으면 true

    val alphabetSize = 'z'-'a'+1
    val alphabetHistoryCheckArray = Array<Boolean>(alphabetSize) { false }

    var lastChar: Char? = null

    word.forEach {
        // println(it)

        val index = it.code - 'a'.code

        if (lastChar != null &&
            it != lastChar && alphabetHistoryCheckArray[index]) {
            // println("새로운 문자가 왔는데, 이전에 나왔던거")
            return false
        }

        lastChar = it
        alphabetHistoryCheckArray[index] = true
    }

    // println("그룹문자이다.")
    return true
}