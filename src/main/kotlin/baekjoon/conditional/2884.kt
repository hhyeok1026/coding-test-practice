package baekjoon.conditional

//입력 : 상근이가 설정한 시간
//출력 : 설정해야하는 알람 시간
fun main(){

    val input : String = readLine() ?: return

    val hour = input.split(" ")[0].toInt() //0~23
    val minute = input.split(" ")[1].toInt() //0~59

    //45분 일찍 알람맞춰야함.
    if(minute - 45 >= 0){ //분에서 빼기해서 문제없으면 바로 출력.
        println("$hour ${minute-45}")
    }else{ //분이 음수가 되는 경우.
        if(hour - 1 >= 0){ //시간에서 빌려왔을때, 시간이 음수가 되지 않는 경우.
            //시간에서 -1 하고, 분은 +60 - 45하면 됨.
            println("${hour-1} ${minute+60-45}")
        }else{ //시간에서 빌려왔을때, 시간이 음수가 되는경우.
            //시간을 더 줘야함.
            println("${hour-1+24} ${minute+60-45}")
        }
    }

    //TODO
    //뺄려는 시간을 하드코딩했는데,
    //뺄려는 시간도 변수로 받고,
    //전체시간을 분으로 계산해서 뺀다음에 다시 시간,분 포맷으로 변경해주면 좋을듯.
}