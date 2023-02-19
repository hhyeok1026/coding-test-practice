package baekjoon.recursion.gpt2447_3
import java.util.*

fun main(arsgs: Array<String>) {

    printStarPattern(27)
}

fun printStarPattern(n: Int) {
    val starArr = Array(n) { Array(n) { '*' } } // 별(*)로 초기화된 2차원 배열 생성

    drawStarPattern(starArr, n, 0, 0) // 별 패턴 그리기

    for (i in 0 until n) {
        for (j in 0 until n) {
            print(starArr[i][j]) // 별 패턴 출력
        }
        println()
    }
}

fun drawStarPattern(starArr: Array<Array<Char>>, n: Int, x: Int, y: Int) {
    if (n == 1) { // 종료 조건
        return
    }

    val m = n / 3

    for (i in x + m until x + 2 * m) {
        for (j in y + m until y + 2 * m) {
            starArr[i][j] = ' '
        }
    }

    for (i in x until x + n) {
        for (j in y until y + n) {
            if (i % m == m / 2 && j % m == m / 2) {
                continue
            }

            drawStarPattern(starArr, m, x + (i - x) / m * m, y + (j - y) / m * m)
        }
    }
}