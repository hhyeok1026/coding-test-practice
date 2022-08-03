package baekjoon.conditional

//leap year : 윤년이면 출력 1, 아니면 0
fun main(){

    val input : String = readLine() ?: return
    val year = input.toInt()

    //윤년 조건
    //(4의 배수 이면서 100의 배수가 아닐때) or 400의 배수일때.
    if(year % 4 == 0 && year % 100 != 0){
        println(1)
    }else if (year % 400 == 0){
        println(1)
    }else{ //윤년아님
        println(0)
    }
}