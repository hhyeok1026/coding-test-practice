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

--------------------

해당 문자열들 찾고, 여러번 반복해서 없을때까지하고
최후에 남은 문자열을 갯수 세서 +
이중 반복문을 써야하나.. do-while문을 쓰면 되려나?
일단 쉽게 1개 찾아서 빼보자.

이것도 생각보다 어려웠고 다른사람들 짜둔거를 봐야할듯

dz=d-d-dz=dddz=z=s=z=z
답: 11

dz=dz=dz=dz=dz=dz=dz=dz=dz=dz=dz=dz=dz=dz=dz=dz=dz=dz=dz=dz=dz=dz=dz=dz=dz=dz=dz=dz=dz=dz=dz=dz=dz=d
답: 34

z=z=dz=dz=ddz=ddz=dz=dz=z=z=
답: 12

*/

fun main(args: Array<String>) {

    var input = readLine()!!
    var croatianAlphabetCount = 0

    val croatianAlphabetList = arrayOf("c=", "c-", "dz=", "d-" ,"lj", "nj", "s=" ,"z=")
    // println(croatianAlphabetList.contentToString())

    // 크로아티아 알파벳 제거 및 찾는 로직
    do {

        var existCroatian = false

        // 이 리스트들 사이에서도 우선순위가 있어서, 계속 돌리면 안되고,
        // 하나 찾고나면 break를 해줘야함.
        for (it in croatianAlphabetList) {
            if (input.contains(it)) {
                existCroatian = true
                croatianAlphabetCount++

                // 해당 문자열을 input에서 삭제해내고, 카운트 1올리기
                // println("$input ($it)")

                // 공백없이 replace를 하면, 중간에 문자열이 없어지면, 다시 합쳐진거 처럼됨. 그래서 크로아티아 알파벳 추가로 생겨 버그가 생김
                // 그래서 " "로 대체시키고 뒤쪽 로직에서 다시 처리함.
                input = input.replaceFirst(it, " ")
                break
            }
        }
    } while (existCroatian)
    // println(input)
    // println(croatianAlphabetCount)

    // 크로아티아 알파벳이외의 알파벳 추가해야함.
    // 버그가 안생기게 수정해야함.
    var otherAlphabets = input.split(" ").filter { it != "" }.joinToString("")

    /*otherAlphabets.forEach {
        println(it)
    }*/
    // println(otherAlphabets)
    otherAlphabets = otherAlphabets.replace(regex = "[-=]".toRegex(), replacement = "")
    // println(otherAlphabets)

    val otherAlphabetCount = otherAlphabets.length

    println(croatianAlphabetCount + otherAlphabetCount)
}

