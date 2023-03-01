package baekjoon.sort

import kotlin.math.round

/*

input
- 수의 개수 ( 1 ~ 500,000, 홀수)
- 정수들 (-4000 ~ 4000)

output
- 산술평균 (N개의 수들의 합을 N으로 나눈 값, 소수점 첫째자리에서 반올림)
- 중앙값 (오름차순으로 정렬했을때 중앙에 위치하는 값)
- 최빈값 (가장 많이 나타나는 값, 여러개 있을때는 두번째로 작은값)
- 범위 (최대값과 최소값의 차이)

 */

fun main(args: Array<String>) {

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val nums = IntArray(n)

    // 입력받기
    repeat(n) {
        val num = br.readLine().toInt()

        nums[it] = num
    }

    // 정렬
    nums.sort()
    // println(nums.contentToString())

    // 산술평균
    val arithmeticMean: Int = round(nums.average()).toInt()  // 산술평균 - 반올림후, 정수로 표현됨
    println(arithmeticMean)

    // 중앙값
    val median: Int = nums[n/2] // 중앙값
    println(median)

    // 최빈값 구하기
    /*println(nums.groupBy { it })
    println(nums.groupBy { it }.mapValues { (_, list) -> list.size })
    println(nums.groupBy { it }.mapValues { (_, list) -> list.size }.maxBy { it.value })
    println(nums.groupBy { it }.mapValues { (_, list) -> list.size }.maxBy { it.value }.key)*/

    // 최빈값이 나타난 횟수를 저장한 맵
    val countMapForMode = nums.groupBy { it }.mapValues { (_, list) -> list.size }

    // 여러 최빈값중 하나가 될 수 있는 것
    val entryForMode = countMapForMode.maxBy { it.value }
    val countForMode = entryForMode.value // 최빈값의 count된 값
    val candidForMode = entryForMode.key  // 최빈값으로 count된게 여러개가 아니라면, 이게 최빈값.

    // 최빈값이 여러개 있는지 확인
    val meanMap = countMapForMode.filter { it.value == countForMode }

    val mode: Int // 최빈값
    if (meanMap.keys.size >= 2) {
        mode = meanMap.entries.sortedBy { it.key }[1].key
    } else {
        mode = candidForMode
    }

    println(mode)

    // range
    val max: Int = nums[n-1] // 입력중에 받을 필요가 없고, 정렬이후에, 찾아내면 된다. n-1 인덱스
    val min : Int = nums[0] // 0 인덱스
    val range = max - min
    println(range)

    br.close()
}