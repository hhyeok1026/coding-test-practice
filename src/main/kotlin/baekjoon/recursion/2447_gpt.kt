package baekjoon.recursion.gpt2447
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()

    val pattern = makePattern(n)
    for (i in pattern.indices) {
        println(pattern[i])
    }
}

fun makePattern(n: Int): Array<String> {
    if (n == 3) {
        return arrayOf("***", "* *", "***")
    }

    val prevPattern = makePattern(n / 3)
    val size = prevPattern.size
    val pattern = Array(size * 3) { "" }

    for (i in pattern.indices) {
        if (i / size == 1) {
            pattern[i] = prevPattern[i % size].padEnd(size * 2, ' ')
        } else {
            pattern[i] = prevPattern[i % size].repeat(3)
        }
    }

    return pattern
}