package baekjoon.bruteforce

import java.util.StringTokenizer

/*

input
- 전체사람수 N, (2 ~ 50)
- 몸무게 키 x, y (10 ~ 200)

output
- 덩치 등수를 구해서 그 순서대로 첫 줄에 출력, 덩치 등수는 공백문자로 분리.


몸무게, 키가 더 커야 덩치가 큰거다.
나보다 덩치가 더 큰사람이 k명 있다면, 내 덩치는 k+1이 된다.

몸무게, 키 둘중 하나 씩 다르게 크면 비교할 수 없음이 된다.

 */
fun main(args: Array<String>) {

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    // 키와 몸무게를 저장 할 자료구조가 필요, 2차원 배열?
    val people = Array(n) { IntArray(2) }

    // 출력 할 등수도 자료형에 넣으면 더 나을 것 같은데, 그냥 출력시키자.
    val bw = System.out.bufferedWriter()

    // 입력이 모두 끝난 뒤에 비교 할 수 있다.
    repeat(n) {
        val st = StringTokenizer(br.readLine())
        val weight = st.nextToken().toInt()
        val height = st.nextToken().toInt()

        people[it][0] = weight
        people[it][1] = height
    }

    // 입력 확인
    // println(people.contentDeepToString())

    // 덩치구하는 비교로직, 등수를 저장
    for (person in people) {

        var rank = 1 // 나보다 큰사람 +1해야하니, 1부터 시작.

        // 내 자신을 나와 비교하는게 필요할까 안필요할까?
        // 굳이 비교할 필요는 없는데, 비교해도 등수에 문제가 없을테니 그냥 비교하게 냅두자.
        for (otherPerson in people) {

            // 다른 사람이 몸무게, 키가 둘다 큰 상황
            if (otherPerson[0] > person[0] && otherPerson[1] > person[1]) {
                rank++
            }
        }

        bw.write("$rank ")
    }

    bw.flush()
    bw.close()
    br.close()
}