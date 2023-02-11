package baekjoon.string

/*
알파벳 찾기

 */

fun main(args: Array<String>) {

    /*
     val a = 'a'
     val z = 'z'
     println(z-a+1) // 26
    */

    val inputs = readln()
    val outputs = ArrayList<Int>()

    // 필요한 사이즈만큼 초기화를 해준다.
    for (i in 'a'..'z') {
        outputs.add(-1)
    }

    // println(outputs)

    // input값들의 알파벳들이, 각각 처음 출연하는 위치를 output에 작성하면 된다.
    for (i in inputs.indices) {
        if(outputs[inputs[i] - 'a'] == -1) { // 처음 출연한 경우
            outputs[inputs[i] - 'a'] = i
        }
    }

    println(outputs.joinToString(" "))
}