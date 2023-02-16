package baekjoon.setAndMap

import java.util.StringTokenizer

/*
N개의 문자열로 이루어진 집합 S
입력으로주어지는 M개의 문자열, 집합 S에 포함되어 있는게 몇개인가?

input
- 집합 S의 갯수 N (1 ~ 10,000), 검사해야할 문자열 M (1 ~ 10,000)
- N개의 문자열
- M개의 검사해야할 문자열

(각 문자열은 알파벳 소문자, 길이 ~500, 집합s에는 같은 문자열만)
검사문자열은 중복얘기가 없다. (리스트로 해야하나?, 일단 셋으로 해보자.)

outpu
- 검사한 값 (count 변수 있으면 된다.)

 */

fun main(args: Array<String>) {
    val br = System.`in`.bufferedReader()
    val st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()

    val S: MutableSet<String> = mutableSetOf()

    // 틀렸습니다로 나오는걸 봐서는 중복되는 문자열이 있는듯. 얘는 set이아니라, 리스트로 해야함.
    val stringsToCheck : MutableList<String> = mutableListOf()

    for (i in 0 until N) {
        S.add(br.readLine())
    }

    for (i in 0 until M) {
        stringsToCheck.add(br.readLine())
    }

    var count = 0
    stringsToCheck.forEach {
        if (S.contains(it)) {
            count++
        }
    }

    println(count)
}