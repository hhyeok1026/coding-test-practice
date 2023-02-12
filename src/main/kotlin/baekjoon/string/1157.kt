package baekjoon.string

/*
input
대소문자가 있는 영어단어 (대소문자 구별하지 않음)

output
가장 많이 쓴 알파벳을 대문자로 출력, 여러개 존재하면 ?로 출력
 */

fun main(args: Array<String>) {

    val input = readln().lowercase() // 대소문자 구별이 없기에, 처리하기 좋게 소문자로 변경
    // println(input.length)

    // a-z(인덱스)와 값(카운트)을 저장하는 배열
    val arraySize = 'z'-'a' + 1
    val aTozCountArray = Array<Int>(arraySize) { 0 }

    // println(aTozCountArray.size)

    // 카운트 올려주기
    input.forEach {
        aTozCountArray[it - 'a']++
    }

    // 들어간거 확인
    /*aTozCountArray.forEach {
        println(it)
    }*/

    // 많이 사용된 알파벳을 찾는 로직
    // max값은 따로 구했고,
    // 인덱스값, 요소가 여러개가 있는가 필요,
    val max = aTozCountArray.max()
    if (aTozCountArray.count { i -> i == max } > 1) {
        println("?")
    } else {
        val findIndex = aTozCountArray.indexOfFirst { i -> i == max }
        println('A'.plus(findIndex))
    }
}