package baekjoon.string

/*
input
대문자로 이루어진 단어 2~15

output
다이얼을 걸기위한 최소 시간

---------------------------

알파벳에 대해서 숫자가 매핑됨
1을 걸려면 2초가 걸리고
1보다 더 많은 숫자는 각각 1초씩 더 걸린다.

'최소 시간'을 구하라는건 무슨 의미 일까?
그냥 시간아닌가.

//
다른 사람은 다이얼 마다, 걸리는 시간을 미리 싹 계산해놓고,
(나처럼 노가다 아니고, 인덱스도 따로 문자열처리로 정리하고,)
총 걸리는 시간을 돌렸네.
 */
fun main(args: Array<String>) {
    val input = readln()

    var totalTime = 0
    input.forEach {
        // 3개씩 딱 떨어지면 이렇게 노가다 안해도 되겠는데..
        // 일단, 노가다가 제일 빠르겠다, 우측에 각 시간 정하는 함수를 하나 만들어야겠다.
        when(it) {
            'A', 'B', 'C' -> totalTime += calculateTime(2)
            'D', 'E', 'F' -> totalTime += calculateTime(3)
            'G', 'H', 'I' -> totalTime += calculateTime(4)
            'J', 'K', 'L' -> totalTime += calculateTime(5)
            'M', 'N', 'O' -> totalTime += calculateTime(6)
            'P', 'Q', 'R', 'S' -> totalTime += calculateTime(7)
            'T', 'U', 'V' -> totalTime += calculateTime(8)
            'W', 'X', 'Y', 'Z' -> totalTime += calculateTime(9)
        }
    }

    println(totalTime)
}

fun calculateTime(time: Int) : Int {
    return 2 + (time - 1) // 기본적으로 2초 + 더 걸리는 시간
}