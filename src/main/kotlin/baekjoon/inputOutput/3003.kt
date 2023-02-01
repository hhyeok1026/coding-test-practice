package baekjoon.inputOutput

// 킹1
// 퀸1
// 룩2
// 비숍2
// 나이트2
// 폰8

/*
검정피스 모두 있음.
흰 피스 이상.

입력 한 줄
흰색의 갯수들
킹, 퀸, 룩, 비숍, 나이트, 폰

원래 있어야할 갯수로 짝을 맞추는 문제임.

*/

fun main(args: Array<String>) {

    val input : List<String>? = readLine()?.split(" ")
    val output : MutableList<Int> = mutableListOf()

    /*
    변수 몇개 없는데 그냥 무식하게 문자열처리로 짜는게 훨씬 낫겠다.
    val king
    val queen
    val rook
    val bishop
    val knight
    val pawn
    */

    for((index, item) in input?.withIndex()!!) {

        val temp = 0
        val itemInt = item.toInt()

        // output = base - input
        when(index) {
            0 -> output.add(1 - itemInt) // 킹
            1 -> output.add(1 - itemInt) // 퀸
            2 -> output.add(2 - itemInt) // 룩
            3 -> output.add(2 - itemInt) // 비숍
            4 -> output.add(2 - itemInt) // 나이트
            5 -> output.add(8 - itemInt) // 폰
        }
    }

    // 결과 출력
    print(output.joinToString(" "))
}
