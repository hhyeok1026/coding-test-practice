package baekjoon.factorMultipleDecimal

import java.util.StringTokenizer

/*
어떤 자연수 p, q 가 있고,
p를 q로 나누었을때 나머지가 0이면, q는 p의 약수이다.

자연수 N, K가 주어졌을때, N의 약수들 중 K번째로 작은 수를 출력하는 프로그램을 작성.

input
- N (1 ~ 10,000) K (1 ~ N)

ouput
- N의 약수들중 k번째로 작은 수를 출력, 만일 N의 약수의 개수가 k보다 적어서, k번째 약수가 존재하지 않으면 0을 출력

 */
fun main(args: Array<String>) {
    val br = System.`in`.bufferedReader()
    val st = StringTokenizer(br.readLine())

    val n = st.nextToken().toInt()
    val k = st.nextToken().toInt()
    val factorsOfN = mutableListOf<Int>()

    for (divideNum in 1..n) {
        if(n % divideNum == 0) {
            factorsOfN.add(divideNum)
        }
    }

    if (factorsOfN.size >= k) {
        println(factorsOfN[k-1])
    } else {
        println(0)
    }
}