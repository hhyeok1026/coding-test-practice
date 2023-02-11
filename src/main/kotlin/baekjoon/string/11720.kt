package baekjoon.string

/*

input
- N개
- N개들이 공백없이 들어옴.

output
- 숫자들의합
 */

fun main(args: Array<String>) {
    val N = readln().toInt() // 쓸모없음.
    val output = readln().toList().sumOf { Character.getNumericValue(it) }
    println(output)
}