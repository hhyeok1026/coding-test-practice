package baekjoon.conditional

import java.util.Scanner

fun main(){
    val scanner = Scanner(System.`in`)

    val input1 = scanner.nextLine().toInt()
    val input2 = scanner.nextLine().toInt()

    if(input1 > 0 && input2 > 0){ //양양 - 1사분면
        println(1)
    }else if(input1 < 0 && input2 > 0){ //음양 - 2사분면
        println(2)
    }else if(input1 < 0 && input2 < 0){//음음 - 3사분면
        println(3)
    }else{ //양음 - 4사분면
        println(4)
    }
}