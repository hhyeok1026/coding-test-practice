package baekjoon.inputOuptAndArithmetic

//나눗셈 문제
fun main(args: Array<String>){

    val input : String = readLine() ?: return

    val a = input.split(" ")[0].toDouble()
    val b = input.split(" ")[1].toDouble()

    print(a / b)
}