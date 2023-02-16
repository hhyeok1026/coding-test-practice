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

    // N개 먼저 입력받자
    for (i in 0 until N) {
        pokemonEncyclopedia[i] = br.readLine()
    }

    val sb = StringBuilder()
    for (i in 0 until M) {

        // 문자열이 숫자인지 확인하는게 필요하다.
        // try-catch로하면 안되네..
        try {
            val key = br.readLine().toInt()
            sb.append(pokemonEncyclopedia[key])
            sb.append("\n")
        } catch (ex : NumberFormatException) {
            pokemonEncyclopedia.filter { key }
        }



        // 여기서 바로 프린트 해주면됨. (StringBuilder를 이용해서 한번에 출력하자.)
        sb.append()

    }

    println(sb)

    br.close()
}















