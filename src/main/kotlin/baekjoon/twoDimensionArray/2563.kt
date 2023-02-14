package baekjoon.twoDimensionArray

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

/*
input
- 색종이수 (~100)
- 각 색종이들의 붙은 위치 (x, y) (색종이가 도화지 밖으로 나가는 일은 없다 -> 이거 계산할 필요는 없다는 뜻이고)
도화지는 100 100, 색종이는 10 10

output
- 검은 영역의 넓이

1. 도화지에서 - 흰공간 빼기
2. 색종이넓이들에서 겹치는부분 빼기

색종이들이 중구난방으로 여러번 겹쳐버리면 2번으로는 머리가 아파질 것이다.
그렇다고 1번으로 하기에도 머리가 아파오는데..


여러개가 겹치는 섹터를 큰그림으로 잡고,
큰그림 (x2 * x1) - (y2 * y1)


// 풀이 힌트
도화지를 100 * 100으로 잡아놓고
색칠을 해나가면 된다.
 */
fun main(args: Array<String>) {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val numOfPaper = br.readLine().toInt()

    val paper = Array(100) { BooleanArray(100) { false } }

    repeat(numOfPaper) {
        val st = StringTokenizer(br.readLine())
        val x = st.nextToken().toInt()
        val y = st.nextToken().toInt()

        // 색칠하면 된다.
        for (i in x until x + 10) {
            for (j in y until y + 10) {
                paper[i][j] = true
            }
        }
    }

    var count = 0
    for (i in paper.indices) {
        for (j in paper[i].indices) {
            if (paper[i][j])
                count++
        }
    }

    println(count)
}