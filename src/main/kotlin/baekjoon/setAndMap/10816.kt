package baekjoon.setAndMap

import java.lang.StringBuilder
import java.util.StringTokenizer

/*
숫자카드 2

input
- 숫자 카드 갯수 N (1 ~ 500,000)
- 숫자카드에 적힌 수들 (-10,000,000 ~ 10,000,000)
- M (1 ~ 500,000)
- M에 대한 수들 (-10,000,000 ~ 10,000,000)

output
- M개의 카드에 대해서 각각 몇개를 가지고 있는지 출력


숫자카드1에서는 집합으로 해결되었고,
있는지 유무를 1-0으로 표시했지만,
이번에는 중복 카운팅을 해야한다.


// 시간초과가 뜬다. (애초에 print문도 stringbuilder로 안했다..)
처음입력받는거를 Map(숫자-나온갯수)으로 하고,
뒤에 들어오는거는 key값으로 보고, 바로 값을 가져오면 될듯하다.

새로 짠거도 시간 오버되는데,,, 시간내에 얼추 들어오면 맞는거로 치는가보다.
문자열끝에 공백도 처리 안해도 맞다해주기도하고..
일단, 넘어가자
 */

fun main(args: Array<String>) {

    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt() // 입력이 일정할거라서 쓸모가 없다.
    val secondLineTokens = StringTokenizer(br.readLine())

    // 중복값이 있다.
    // val nForList = mutableListOf<Int>()
    val mapForN = mutableMapOf<Int, Int>()

    while (secondLineTokens.hasMoreTokens()) {
        val num = secondLineTokens.nextToken().toInt()

        if (mapForN.contains(num)) {
            val newValue = mapForN[num]!! + 1 // contains로 확인했기때문에 null일 수 없다.
            mapForN[num] = newValue
        } else {
            mapForN[num] = 1
        }
    }

    val m = br.readLine().toInt() // 입력이 일정할거라서 쓸모가 없다.

    val fourthLineTokens = StringTokenizer(br.readLine())

    val sb = StringBuilder()

    while (fourthLineTokens.hasMoreTokens()) {
        val num = fourthLineTokens.nextToken().toInt()

        if (mapForN.contains(num)) {
            val value = mapForN[num]
            sb.append("$value ")
        } else {
            sb.append("0 ")
        }
    }
    println(sb)

    br.close()
}