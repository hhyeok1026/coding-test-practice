package baekjoon.setAndMap

/*
서로 다른 부분 문자열의 개수

input
- 문자열 S (소문자 1000이하)

output
- 문자열S에 대한 부분문자열중, 중복을 제외한 갯수


부분 문자열들을 찾는게 핵심인데,
브루트 포스인가?

 */
fun main(args: Array<String>) {
    val S = readln()
    val substringsSetofS = mutableSetOf<String>()

    // 부분 문자열로 쓸 수 있는 size를 하나씩 올리면서,
    // subString을 하고, set에 넣으면 될 듯.

    // i는 찾으려는 문자열의 사이즈
    for (i in S.indices) {
        // i사이즈에 대해서, S내에서 검색 할 수 있는거를 찾아야함
        for (j in S.indices) {
            if (j + i in S.indices) {
                // println(S.substring(j..j+i))
                substringsSetofS.add(S.substring(j..j+i))
            }
        }
    }

    println(substringsSetofS.size)
}