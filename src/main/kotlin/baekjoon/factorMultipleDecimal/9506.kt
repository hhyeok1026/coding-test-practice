package baekjoon.factorMultipleDecimal

/*
약수들중에 자기 자신을 제외한 것들을 모두 더한 값이
자기 자신과 같으면 그 수를 완전수라고 한다.

완전수인지 판단하는 프로그램 작성

input
- 테스트케이스들 n (2~100,000)
- 입력마지막엔 -1 (무한 반복문 돌려서 빼야겠네)

output
- 해당 수가 완전수라면, n을 더하기로 표현, 약수들이 오름차순으로
- 완전수가 아니라면 is NOT perfect라고 출력.

 */
fun main(args: Array<String>) {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    while (true) {
        val n = br.readLine().toInt()
        val factorsOfN = mutableListOf<Int>()

        if (n == -1)
            break

        // 약수 구하기
        for (factorCandid in 1 until n) {
            if (n % factorCandid == 0) {
                factorsOfN.add(factorCandid)
            }
        }

        // 완전수인지 확인
        if (factorsOfN.sum() == n) {
            bw.write("$n = ${factorsOfN.joinToString(" + ")}\n")

        } else {
            bw.write("$n is NOT perfect.\n")
        }
    } // end of while

    bw.flush()
    bw.close()
    br.close()
}