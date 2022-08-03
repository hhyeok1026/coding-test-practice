package baekjoon.inputOuptAndArithmetic

import java.util.Scanner

//사칙연산
fun main(){
    val scanner = Scanner(System.`in`)
    val input = scanner.nextLine()

    val A = input.split(" ")[0].toInt()
    val B = input.split(" ")[1].toInt()

    println(A+B)
    println(A-B)
    println(A*B)
    println(A/B)
    println(A%B)
}