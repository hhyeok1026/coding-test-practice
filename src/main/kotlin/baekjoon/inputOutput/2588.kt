package baekjoon.inputOutput

fun main(){
    val input1 : String = readLine() ?: return
    val input2 : String = readLine() ?: return

    val input1Int = input1.toInt()

    val output1 = input1Int * input2[2].toString().toInt() //캐릭터는 toInt로 바꾸면 아스키코드 숫자가 넘어와서 이렇게 처리해야함. (캐릭터도 사실은 숫자니까)
    val output2 = input1Int * Character.getNumericValue(input2[1]) //캐릭터는 toInt로 바꾸면 아스키코드 숫자가 넘어와서 이렇게 처리해야함.
    val output3 = input1Int * input2[0].toString().toInt()
    val output4 = input1Int * input2.toInt()

    println(output1)
    println(output2)
    println(output3)
    println(output4)
}