package baekjoon.conditional

//오븐시계
//입력1 - 현재시각
//입력2 - 요리하는 시간 (분)
//출력 - 요리가 끝나는 시간
fun main(){

    val input1 : String = readLine() ?: return
    val hour = input1.split(" ")[0].toInt()
    val minute = input1.split(" ")[1].toInt()

    val totalMinute = hour * 60 + minute
    val cookTime : Int = readLine()?.toInt() ?: return

    val doneTime : String = doneCookTime(totalMinute, cookTime)
    println(doneTime)
}

fun doneCookTime(totalMinute: Int, cookTime: Int): String {

    var doneMinute = totalMinute + cookTime

    //시간 구하기
    //하루는 1440분
    //하루 넘길 경우 - 1440
    while (doneMinute >= 1440){
        doneMinute -= 1440
    }

    val hour = (doneMinute / 60).toString()
    val minute = (doneMinute % 60).toString()

    return "$hour $minute"
}
