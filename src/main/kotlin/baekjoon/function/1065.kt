package baekjoon.function

// 한수

// 단계적 풀이인데 생각보다 어렵다..
// 신입들이 이거 잘 풀 수 있으려나..?

/*
양의 정수 X 각 자리가 등차수열이면, 그 수를 한수라한다.
등차수열은 연속된 두개의 수의 차이가 일정한 수열.
N이 주어졌을때, 1보다 크거나 같고, N보다 작거나 같은 한수의 개수를 출력
N은 1~1000
 */

fun main(args: Array<String>) {

    val N = readlnOrNull()!!.toInt()
    var count = 0

    for (i in 1..N) {
        if (isHansoo(i)) { // 한수인지 확인하는 함수필요
            // 한수가 맞으면 카운트 증가
            count++
        }
    }

    println(count)
}

fun isHansoo(number: Int) : Boolean {

    val digits = ArrayList<Int>() // 인덱스 0이 끝자리

    var tempNum = number

    // 1. 자리수 파싱
    while (tempNum > 0) {
        // println("number : $tempNum")
        digits.add(tempNum % 10)
        tempNum /= 10 // 자릿수 줄여나가기
    }

    // println("digits : $digits")

    if(digits.size <= 2) { // 1~2자리수들은 무조건 한 수.
        return true
    }

    // 2. 각 자리수의 diff값 확인
        // 2-1 diff가 예상하는 대로 안올라가면, false
            // 처음은 첫값 저장하고, 지나가면되고
            // 두번째는 첫값 저장한거랑 지금 값이랑 비교해서 차이값 만들어야하고, (0-1)
            // 세번는 차이값 만들어진거랑 세번째 (1-2)에서 나온값이랑 그 전에 나온 차이값이랑 비교. 틀리면, false
        // 2-2 diff가 예상되로 작동하면 ture

    var lastValue = -1
    var lastDiffValue: Int = 10 // 자릿수 비교라서 10이상으로 갈 일이 없어서 이 값으로 초기화.

    // 세자리수 이상이라 가정됨.
    for (i in digits.indices) {
        if (i == 0) { // 첫번째
            lastValue = digits[i]
        } else { // 첫번째 이후
            val diffValue = digits[i] - lastValue

            if(i == 1) {
                lastDiffValue = diffValue
            } else {
                if (lastDiffValue == diffValue) { // 등차수열이면, 차이값이 처음꺼랑 계속 같아야함
                    // println("여기로만 들어오면 한수 일 것이다.")
                } else{
                    return false
                }
            }

            lastValue = digits[i]
        }
    }

    // for문에서 한수가 아닌 것들을 걸러내고, 한수인것들은 true
    return true
}


