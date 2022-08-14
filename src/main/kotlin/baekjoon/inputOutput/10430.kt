package baekjoon.inputOutput

fun main(){
    val input : String = readLine() ?: return
    val parsedInputList = input.split(" ")

    val A = parsedInputList[0].toInt()
    val B = parsedInputList[1].toInt()
    val C = parsedInputList[2].toInt()

    println( (A+B)%C )
    println( ((A%C) + (B%C))%C )
    println( (A*B)%C )
    println( ((A%C) * (B%C))%C )
}