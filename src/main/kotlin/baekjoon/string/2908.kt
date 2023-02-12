package baekjoon.string

/*

상수라는 사람,
3자리수 2개
큰수를 읽어라
상수는 거꾸로 읽음
상수는 거꾸로 읽은뒤 큰수를 말함

input
 두숫자

output
 상수의 대답

 */
fun main(args: Array<String>) {
    val input = readln()

    val output = input
        .split(" ").maxOfOrNull {
            it.reversed().toInt()
        }

    println(output)
}