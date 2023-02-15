package baekjoon.sort

import java.io.BufferedReader
import java.io.InputStreamReader


/*
input
- 수의 개수 N (1~1000)
- 수들.. (-1000~1000, 중복없음)

ouput
- 오름 차순으로 한 줄 씩

내가 구현한게 삽입정렬같은데..
실제로 만들어보니 재밌다

 */
fun main(args: Array<String>) {

    val br = BufferedReader(InputStreamReader(System.`in`))

    val N = br.readLine().toInt()
    // println(N)

    val nums = IntArray(N)

    for (i in 0 until N) {
        // println("i: $i")

        val num  = br.readLine().toInt()

        // 정렬 로직, 일단 N번째 들어오는거 넣어주고,
        nums[i] = num

        // 이전에 들어온것들이랑 값 비교해서 값을 밀어주면 됨
        // i가 0이면 에러인가? - 애초에 for문 조건에 걸려서 안도는듯.
        for (j in i downTo 1) {
            // println("j: $j")

            if (nums[j] < nums[j - 1]) {
                val temp = nums[j]
                nums[j] = nums[j - 1]
                nums[j - 1] = temp
            }
        }
    }

    nums.forEach {
        println(it)
    }
}