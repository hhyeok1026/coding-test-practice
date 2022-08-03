package baekjoon.inputOuptAndArithmetic

fun main(){

    val input = readLine()

    val a = input?.split(" ")?.get(0)?.toInt()
    val b = input?.split(" ")?.get(1)?.toInt()

    println(b?.let { a?.minus(it) })
}