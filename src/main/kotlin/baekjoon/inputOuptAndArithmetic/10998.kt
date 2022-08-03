package baekjoon.inputOuptAndArithmetic

//입력 : a b
//한줄에 정수 2개

//출력 : axb

fun main(){

    val input = readLine()!!

    val a = input.split(" ").get(0).toInt()
    val b = input.split(" ").get(1).toInt()

    print(a * b)
}