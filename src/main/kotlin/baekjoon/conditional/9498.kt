package baekjoon.conditional

fun main(){

    val input :String = readLine() ?: return

    when(input.toInt()){
        in 90..100 -> println("A")
        in 80 .. 89 -> println("B")
        in 70 .. 79 -> println("C")
        in 60 .. 69 -> println("D")
        else -> println("F")
    }
}