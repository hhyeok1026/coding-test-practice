package baekjoon.advanced1

fun main(args: Array<String>) {

    val input = readln()

    var start = 0
    var end = input.length - 1
    var output = 1

    while (start <= end) {

        if (input[start] != input[end]) {
            output = 0
            break
        }

        start++
        end--
    }

    println(output)
}