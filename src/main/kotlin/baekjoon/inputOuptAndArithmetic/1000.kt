package baekjoon.inputOuptAndArithmetic

//입력 첫째줄에 a b
//1 2

//출력
//첫째줄에 a+b

fun main()
{
    //val input = input()
    val input = readLine()

    //noitce : toInt안쓰면 string으로 되어서 문자열 합치기가 될 수 있음
    val a = input?.split(" ")?.get(0)?.toInt()
    val b = input?.split(" ")?.get(1)?.toInt()

    //println("a = $a")
    //println("b = $b")
    println(b?.let { a?.plus(it) })
}

fun input() : String{
    return "1 3"
}