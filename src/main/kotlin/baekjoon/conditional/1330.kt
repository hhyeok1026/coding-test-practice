package baekjoon.conditional

fun main(){
    val input : String = readLine() ?: return

    val A = input.split(" ")[0].toInt()
    val B = input.split(" ")[1].toInt()

    if(A < B){
        println("<")
    }else if (A > B){
        println(">")
    } else{ // ==
        println("==")
    }
}