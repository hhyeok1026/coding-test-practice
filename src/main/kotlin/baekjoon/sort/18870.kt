package baekjoon.sort

import java.util.StringTokenizer

/*

input
- N (1 ~ 1,000,000)
- 공백으로 분리된, X들 (-1,000,000,000 ~ 1,000,000,000 ) Int가 21억까지 사용가능해서 Int로 가능.

output
- 좌표압축한 결과를 공백으로 분리해서 출력.


좌표압축이 제일 작은 값을 0부터 시작을 해라는 의미인가?
근데 정렬은 필요 없어 보이는데?
정렬을 왜 해야하는지 생각을 안하고 그냥 풀었는데 -> 시간초과가 걸린다.

누군가 잘 정리해둔 블로그를 보고 풀었다.
답을 보고 푸니, 문제는 쉬웠음.

 */
fun main(args: Array<String>) {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val st = StringTokenizer(br.readLine())

    val coordinates = IntArray(n)

    for (i in coordinates.indices) {
        coordinates[i] = st.nextToken().toInt()
    }

    val sortedCoordinates = coordinates.sorted()

    val rankMapForCoordinate = mutableMapOf<Int, Int>()

    var rank = 0
    for (key in sortedCoordinates) {
        if (!rankMapForCoordinate.containsKey(key)) {
            rankMapForCoordinate[key] = rank
            rank++
        }
    }

    for (coordinate in coordinates) {
        bw.write("${rankMapForCoordinate[coordinate]} ")
    }

    bw.flush()
    bw.close()
    br.close()
}