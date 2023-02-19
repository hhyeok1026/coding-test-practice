package baekjoon.recursion.gpt2447_2
import java.util.*

fun main() {
    val sc = Scanner(System.`in`)
    val n = sc.nextInt()

    val arr = Array(n) { CharArray(n) }

    for (i in 0 until n) {
        for (j in 0 until n) {
            arr[i][j] = ' '
        }
    }

    drawStar(arr, 0, 0, n)

    for (i in 0 until n) {
        for (j in 0 until n) {
            print(arr[i][j])
        }
        println()
    }
}

fun drawStar(arr: Array<CharArray>, x: Int, y: Int, n: Int) {
    if (n == 1) {
        arr[x][y] = '*'
        return
    }

    val m = n / 3

    for (i in 0 until 3) {
        for (j in 0 until 3) {
            if (i == 1 && j == 1) {
                continue
            }
            drawStar(arr, x + i * m, y + j * m, m)
        }
    }
}