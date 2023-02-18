package baekjoon.setAndMap

import java.util.StringTokenizer

/*
A,B집합이 있을때
A B의 대칭 차집합을 구해야한다.

(A-B) + (B-A)
또는
(A+B) - (A&B)

input
- A갯수, B갯수 ( ~ 200,000)
- A요소 값들 ( ~ 100,000,000 )
- B요소 값들 ( ~ 100,000,000 )

output
- 대칭차집합의 요소 갯수

 */
fun main(args: Array<String>) {
    val br = System.`in`.bufferedReader()
    val st1 = StringTokenizer(br.readLine())
    val st2 = StringTokenizer(br.readLine())
    val st3 = StringTokenizer(br.readLine())

    val sizeOfSetA = st1.nextToken().toInt()
    val sizeOfSetB = st1.nextToken().toInt()

    val setA = mutableSetOf<Int>()
    val setB = mutableSetOf<Int>()

    while (st2.hasMoreTokens()) {
        val num = st2.nextToken().toInt()
        setA.add(num)
    }

    while (st3.hasMoreTokens()) {
        val num = st3.nextToken().toInt()
        setB.add(num)
    }

    // val unionSet = setA + setB // 플러스로 해도 되는데, 유니온 함수를 쓰는게 낫겠다.
    val unionSet = setA.union(setB)
    val intersection = setA.intersect(setB)

    println(unionSet.size - intersection.size)
}