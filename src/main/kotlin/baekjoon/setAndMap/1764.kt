package baekjoon.setAndMap

import java.lang.StringBuilder
import java.util.StringTokenizer

/*
듣보잡 !?!

input
- 듣도 못한 사람 N, 보도 못한 사람 M (1 ~ 500,000)
- 듣 이름들 ( ~ 20, 중복없음)
- 보 이름들 ( ~ 20, 중복없음)

output
- 교집합을 구해야하고, 교집합의 갯수, 그 명단을 구하라. (사전순으로 출력해야함)

 */
fun main(args: Array<String>) {
    val br = System.`in`.bufferedReader()
    val firstLineTokens = StringTokenizer(br.readLine())
    val N = firstLineTokens.nextToken().toInt()
    val M = firstLineTokens.nextToken().toInt()

    val setOfN = mutableSetOf<String>()
    val setOfM = mutableSetOf<String>()

    for (i in 0 until N) {
        val dName = br.readLine()
        setOfN.add(dName)
    }

    for (i in 0 until M) {
        val bName = br.readLine()
        setOfM.add(bName)
    }

    val setOfNAndM = setOfN.intersect(setOfM)
    val sortedNAndM = setOfNAndM.sorted()

    println(setOfNAndM.size)

    val sb = StringBuilder()

    // 사전 순으로 출력해야함. 정렬을 해야함.
    sortedNAndM.forEach {
        sb.append("$it\n")
    }
    println(sb)

    br.close()
}