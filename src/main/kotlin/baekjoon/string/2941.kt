package baekjoon.string

/*
input
최대 100글자의 알파벳과 기호 1줄로 (변경된 형식)

output
입력이 몇개의 크로아티아 알파벳인가 숫자로 출력

---------------

크로아티아 알파벳에 대한 매핑테이블을 만들어야할듯

입력을 받으면서, 하나씩 매핑테이블에 맞는지 확인하고 맞으면 카운트1,
그냥 알파벳도 하나씩 치는거네.

dz=ak
특정문자들이 크로아티아 알파벳인지 확인하고 -> 아니면, 그냥 알파벳으로 쳐야함.
문자열의 앞과 뒤를 체크할 수 있어야하는 건가?
 */
/*
저 테이블에 대한 문자열들을 뽑아서 파싱하고,
나머지는 그냥 1개단어로 치면 되는 문제네.
*/

fun main(args: Array<String>) {

    val input = readln()
    var count = 0

    val croatianAlphabetList = arrayOf("c=", "c-", "dz=", "d-" ,"lj", "nj", "s=" ,"z=")
    // println(croatianAlphabetList.contentToString())

    do {
        var existCroatian = false

        croatianAlphabetList.forEach {
            if (input.contains(it)) {
                existCroatian = true
                
                // 1. 해당 문자열을 input에서 삭제해내고, 카운트 1올리기
                count++
            }
        }

    } while (existCroatian) // 내부에 foreach로 돌리지만, 계속해서 있을 수 있기때문에 while문을 붙임.

    // 해당 문자열들 찾고, 서브스트링, 여러번 반복해서 없을때까지하고
    // 최후에 남은 문자열을 갯수 세서 +
    // 이중 반복문을 써야하나.. // do-while문을 쓰면 되려나?
    // 일단 쉽게 1개 찾아서 빼보자.
    println(count)
}

