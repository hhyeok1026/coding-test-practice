package baekjoon.recursion

import java.util.*

fun drawStars(n: Int, x: Int, y: Int) {
    if (n == 1) {
        stars[x][y] = '*'
        return
    }

    val m = n / 3
    for (i in 0 until 3) {
        for (j in 0 until 3) {
            if (i == 1 && j == 1) {
                continue
            }
            drawStars(m, x + i * m, y + j * m)
        }
    }
}

fun printStars() {
    for (i in 0 until stars.size) {
        for (j in 0 until stars.size) {
            print(stars[i][j])
        }
        println()
    }
}

fun main(args: Array<String>) {

    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()
    val stars = Array(n) { CharArray(n) { ' ' } }

    drawStars(n, 0, 0)
    printStars()
}

