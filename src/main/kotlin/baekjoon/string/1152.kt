package baekjoon.string

/*
input
- 문장

output
- 문장 내의 단어의 갯수

-> 다른 사람들 코드 보니까 버퍼리더로 읽고, 토큰으로 분리하면 깔끔하게 되는듯하다.
 */
fun main(args: Array<String>) {

    // val input = readln()
    val input = readln().trim()

    val words = input.split(" ").filter { it.isNotEmpty() }
    // 첫공백 끝공백이 있을때, split으로 자르니까 뒤에 하나씩 추가 되네.
    // 왜 공백으로 잘랐는데, 들어가는건지 모르겠네.
    // ""도 문자열로 치는거 같네 ""은 널스트링이라고 부르네

    // println(words)
    println(words.size)
}