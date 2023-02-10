package baekjoon.oneDimensionArray

/*

문제가.. 좀 이해하는데 시간이 걸리네
연속 정답시 콤보로 점수가 더 추가된다는거고

input :
- 평가받아야할 라인수
- OX로 구성된 값들 (0~80)

output :
각 줄의 점수 평가

1. 입력받기
첫라인,
갯수
이렇게 되면, 2차원 배열을 써야하는거 아닌가?

스트링 자체도 배열이니,
1차원 사이즈대로 해서 스트링 배열을 만들면 된다.

------------------

2.
각 배열의 요소를 평가하는 로직

 */

fun main(args: Array<String>) {
    val num = readlnOrNull()!!.toInt()
    val inputArray = Array<String>(num) { "" }

    // 1.
    for (i in inputArray.indices) {
        inputArray[i] = readlnOrNull()!!
    }

    // println(inputArray.contentToString())

    // 2.
    for (i in inputArray.indices) {

        var score = 0
        var continuedCount = 0

        for (char in inputArray[i]) {
            if (char == 'O') {
                continuedCount++
                score += continuedCount
            } else {
                continuedCount = 0
            }
        }

        // 3. 출력
        println(score)
    }
}