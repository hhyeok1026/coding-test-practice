package baekjoon.setAndMap

import java.lang.StringBuilder
import java.util.StringTokenizer

/*
문제가.. 재밌긴한데
좀 길어서 골때린다...

도감을 완성해야함. 일단 가지고 있는 포켓몬에서
포켓몬의 이름을 대면 번호를 말하거나,
번호를 대면 이름을 말해야함.


input
- 포켓몬의 개수 N, 맞춰야하는 문제 M (1 ~ 100,000)
- 1번부터~N번까지 포켓몬 이름들 (2~20)
- M개의 줄이 더 들어오는데, 내가 맞춰야할 문제
(String (항상포켓몬 도감에 있는거만) -> Int)
(Int (1~N) -> String)

output
- 문제에 대한 답을 개행하여 출력

 */

fun main(args: Array<String>) {
    val br = System.`in`.bufferedReader()
    val st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()

    // Map 자료형이 필요함
    val pokemonEncyclopedia = mutableMapOf<Int, String>()
    val reversePokemonEncyclopedia = mutableMapOf<String, Int>()

    // N개 먼저 입력받자
    // 입력 index가 1부터 시작
    for (i in 1..N) {
        val pokemonName = br.readLine()
        val key = i
        pokemonEncyclopedia[i] = pokemonName
        reversePokemonEncyclopedia[pokemonName] = i
    }

    // 시간 초과가 걸리는데, try catch문 때문인가? -> try-catch문제는 아닌듯.
    val sb = StringBuilder()
    for (i in 0 until M) {

        // 문자열이 숫자인지 확인하는게 필요하다.
        val question: String = br.readLine()

        val keyOfQuestion = question.toIntOrNull()

        if (keyOfQuestion != null) { // 질문이 키로 왔을 경우
            val foundValue = pokemonEncyclopedia[keyOfQuestion]
            sb.append(foundValue)
            sb.append("\n")
        } else { // 질문이 포켓몬 이름일 경우.

            // question이 이미 value이고 key를 찾아내면 됨.
            // 이거도 시간초과이면 trycatch문제가 아니라 key값 찾는 로직이 문제다.

            // 리버스맵을 둬서 찾아야 시간내에 해결될듯한데
            // val foundKey = pokemonEncyclopedia.filterValues { question == it }.keys.first()
            val foundKey = reversePokemonEncyclopedia[question]

            sb.append(foundKey)
            sb.append("\n")
        }

        /*try {
            val key: Int = question.toInt()
            val foundValue = pokemonEncyclopedia[key]
            sb.append(foundValue)
            sb.append("\n")
        } catch (ex : NumberFormatException) {
            val foundKey = pokemonEncyclopedia.filter { question == it.value }.keys.first()

            sb.append(foundKey)
            sb.append("\n")
        }*/
    }

    println(sb)
    br.close()
}