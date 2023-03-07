package baekjoon.factorMultipleDecimal

import java.util.StringTokenizer

/*
테스트 케이스들 ~10,000의 자연수
마지막 줄에는 0 2개
 */

fun main(args: Array<String>) {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    while (true) {

        val st = StringTokenizer(br.readLine())
        val num1 = st.nextToken().toInt()
        val num2 = st.nextToken().toInt()

        if (num1 == 0 && num2 == 0) {
            break
        }

        /*
        큰거를 작은거로 모듈러 연산해서 0이 나오면, 배수 또는 약수이다.
        모듈러로 0이 아닌수가 나오면, neither
        */
        val big: Int
        val small: Int
        var case : Int // 0: 약수가 될 가능성, 1: 배수가 될 가능성, 2: neither

        if (num1 >= num2) {
            big = num1
            small = num2
            case = 1
        } else {
            big = num2
            small = num1
            case = 0
        }

        if (big % small != 0) {
            case = 2
        }

        if (case == 0) {
            bw.write("factor\n")
        } else if(case == 1) {
            bw.write("multiple\n")
        } else {
            bw.write("neither\n")
        }
    }

    bw.flush()
    bw.close()
    br.close()
}