package baekjoon.basicMath1

import java.util.StringTokenizer

/*
input
- A B (0 ~ 10의 만승, 10의 만자리..)

ouput
- A + B

누군가는 문자열로 처리해서 문자 더해서 처리하는거 같던데(?)
-> 10만까지되서 계속 오름처리 되면, 성능이 어찌되려나.. n이려나?

큰 수를 더할 수 있게 만든 자료형 biginteger로 풀면 된다.
BigInteger가 뭔지 살펴봐야겠다.
 */

fun main(args: Array<String>) {
    val br = System.`in`.bufferedReader()
    val st = StringTokenizer(br.readLine())

    val a = st.nextToken().toBigInteger()
    val b = st.nextToken().toBigInteger()

    println(a+b)
}
