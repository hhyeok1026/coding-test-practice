package baekjoon.bruteforce

import java.util.StringTokenizer

/*
input
- 카드의 개수 N (3 ~ 100), M (10 ~ 300,000)
- 카드에 적힌 수 (1~100,000)

output
- M에 가까운 카드 3장의 합

 */
fun main(args: Array<String>) {
    val br = System.`in`.bufferedReader()
    val st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()

    val nums = IntArray(N)

    val st2 = StringTokenizer(br.readLine())

    // 입력받기
    for (i in nums.indices) {
        val num = st2.nextToken().toInt()
        nums[i] = num
    }

    var max: Int = 0

    // 3중 for문을 돌려야하는건가?
    // 카드 3장을 뽑으면서, M보다 작거나 같은지 확인하고 max에 저장해야함
    // 이미 계산 했던거도 중복해서 계산하게 될텐데, 어떻게 빼지?
    // dp공부를 하면 계산 안하고 넘어갈텐데,, 일단 이렇게 하자.
    for (i in nums.indices) {
        val card1 = nums[i]

        for (j in nums.indices) {
            if (j != i) { // 뽑은카드는 다시 뽑지 않아야함.
                val card2 = nums[j]

                for (k in nums.indices) {

                    if (k != i && k != j) { // 이전에 뽑은 2개의 카드는 다시 뽑지 않아야함.
                        val card3 = nums[k]
                        val sum = card1 + card2 + card3

                        // 카드 다 뽑아놨으니, 더해서 M확인
                        if (sum <= M && sum > max) {
                            max = sum
                        }
                    }
                }
            }
        }
    }

    println(max)

    br.close()
}