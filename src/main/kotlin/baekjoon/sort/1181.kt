package baekjoon.sort

import java.lang.StringBuilder

/*
input
- 단어 갯수 N (1 ~ 20,000)
- 소문자로 이루어진 단어들.. (길이 ~50)

output
- 정렬된 것을 출력

정렬기준
- 길이짧은거 우선
- 길이가 같으면, 사전순
( 중복되는 단어는 하나만 남기고 없애야한다 -> 자료형을 set으로 먼저 받고, 변경하기?)

// 누군가는 정렬된 set이라는걸 썼다
// 누군가는 뮤터블리스트로 정렬한다음, 같은게 출력 되지 않도록 했다.
// sortwith에 넣을 compare라는거를 공부를 해야한다.
// stringBuilder랑 BufferedWriter 둘중 뭐가 더 좋은걸까?
// 입출력 자원을 다 쓰고나면 닫아줘야한다.

 */
fun main(args: Array<String>) {
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()

    val set = mutableSetOf<String>()

    for (i in 0 until N) {
        val word = br.readLine()
        set.add(word)
    }

    val list: List<String> = set.toList()

    val orderedList = list.sortedWith { o1, o2 ->
        if (o1.length == o2.length) {
            o1.compareTo(o2)
        } else {
            o1.length.compareTo(o2.length)
        }
    }
    // println(orderedList)

    val sb = StringBuilder()
    orderedList.forEach {
        sb.append(it)
        sb.append("\n")
    }
    println(sb)
}