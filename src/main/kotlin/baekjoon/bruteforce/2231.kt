package baekjoon.bruteforce

/*

N의 분해합은 N과 N을 이루는 각 자리수의 합.
어떤 자연수 M의 분해합이 N인 경우, M을 N의 생성자라고 한다.
자연수 N이 주어졌을때, N의 가장 작은 생성자를 구하라.

input
- 자연수 N ( 1 ~ 1000,000 )

output
- 답을 출력 (생성자가 없을 경우에는 0을 출력)


123 = 1 + 2 + 3 + 123 =  129
M + M1 + M2 + M3 = N

각 자리수에 대해서 최대값은 9가 될 수 있다.


생성자가 될 M이 있고,
현재 N에 대해서
M으로 예상될 값을 시작을 잡아야하는데 N의 자릿수에 대해서 어쩌구 저쩌구해야함.

1) N의 자릿수의 갯수를 구한다.
2) 자릿수 갯수 * 9만큼 N에서 뺀다. 예측할 M이라 하고,
3) 예측 M에서 브루트포스로 N을 만들어본다.

브루트 포스를 어떻게 돌려야하는지 해메가다
또 풀이를 찾아봤는데, 누군가는 자릿수를 숫자로 안구하고,
문자열 길이, 문자로 아스키값으로 처리하였다.

*/

fun main(args: Array<String>) {

    val n = readln().toInt()


    var nForDigit = n
    var digitCount = 0
    while (nForDigit > 0) {
        nForDigit /= 10
        digitCount++
    }

    val minPredicateConstructor = n - (digitCount * 9)
    val maxPredicateConstructor = n - 1
    // println("minPredicateConstructor: $minPredicateConstructor")

    var minConstructor = 0 // 밑의 for문에서 못찾으면, 요구사항인 0으로 출력됨.

    for (predictedValue in minPredicateConstructor..maxPredicateConstructor){

        var predictedValueForCalculate = predictedValue
        var digitsSum = 0
        // println("predictedValueForCalculate: $predictedValueForCalculate")

        while (predictedValueForCalculate > 0 ) {
            // println("digitsSum에 저장될 값: ${predictedValueForCalculate % 10}")

            digitsSum += predictedValueForCalculate % 10
            predictedValueForCalculate /= 10
        }

        if (digitsSum + predictedValue == n) {
            minConstructor = predictedValue
            break
        }
    }

    println(minConstructor)
}




