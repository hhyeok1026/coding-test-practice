package baekjoon.basicMath1

/*

벌집


input
- n (1 ~ 1,000,000,000)

output
- 입력으로 주어진 방까지 최소 개수의 방을 지나서 갈때 몇개의 방을 지나가는지 출력.
최소로 가야함.

접근을 어떻게 해야하나 ?

단계별로 껍데기가 있다고 생각하자.

1단계 - 1 (1개)
2단계 - 2, 3, 4, 5, 6, 7 (6개)
3단계 - 8 , 9 , 10 ... ~ 19 (12개) (홀수단계는 홀수바로 가짐, 짝수로 가려면 한번 꺾어야함.)
4단계 - 20 ~ 37 (18개) (짝수단계는 모두 가는 횟수가 동일한가?)

1단계씩 증가할때마다 기존보다 +6씩 증가한다.

1 1개
7 1(maxValueOfTheStep) + 1(step) * 6
12 7 + 2 * 6
37 19 + 3 * 6


1) n의 수가 어느단계에 있는지 확인한다.
2) +1을 해야하는 수인지 아닌지 어떻게 판별하지? -> 잘못생각했다, 홀수이던 짝수이던 항상 같다.
 */


fun main(args: Array<String>) {

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    var step = 1
    var maxValueToTheStep = 1

    while (maxValueToTheStep < n) {
        maxValueToTheStep += step * 6
        step++
    }

    // println("step : $step")
    // println("maxValueToTheStep: $maxValueToTheStep")

    println(step)
}