package baekjoon.setAndMap

import java.util.StringTokenizer

/*

input
- 상근이가 가지고 있는 숫자 카드의 개수 N ( 1 ~ 500,000)
- 숫자카드에 적혀있는 정수 (-10,000,000 ~ 10,000,000, 같은수 중복은 없다)
- M개의 수 (1 ~ 500,000)
- M개의 수에 대한 각각의 숫자들 (-10,000,000 ~ 10,000,000)

output
- M개의 수를 상근이가 가지고 있으면 1, 아니면 0으로 공백으로 한 줄 출력

// 접근
카드 중복이 없으니 set으로 해도 될 듯?
일단, 입력부터 받고

출력을 생각해서 자료형을 선택 (출력은 배열)
 */

fun main(args: Array<String>) {

    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val st = StringTokenizer(br.readLine())
    val cardSetOfUser = mutableSetOf<Int>()

    // N개의 카드 입력
    for (i in 0 until N) {
        cardSetOfUser.add(st.nextToken().toInt())
    }

    val M = br.readLine().toInt()
    val output: IntArray = IntArray(M)
    val cardSetForCheck = mutableSetOf<Int>()
    val st2 = StringTokenizer(br.readLine())


    // M개의 정수 입력
    for (i in 0 until M) {
        cardSetForCheck.add(st2.nextToken().toInt())
    }

    cardSetForCheck.forEachIndexed { index, item ->
        if (cardSetOfUser.contains(item)) {
            output[index] = 1
        } else {
            output[index] = 0
        }
    }

    println(output.joinToString(" "))

    br.close()
}