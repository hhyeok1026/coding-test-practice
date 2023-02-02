package baekjoon.oneDimensionArray


//나머지

//input -> 수 10개, 42로 나눈 나머지를 저장.

//procces -> 같았던 나머지 값이 몇개인지 찾아야함.

//ouput


fun main() {

    val array: Array<Int> = Array<Int>(10) { 0 }

    // input & pre_procces
    for (i: Int in array.indices){
        val input = readLine()?.toInt()

        if (input != null) {
            array[i] = input % 42
        }
    }

    /*println("===============")
    println("잘들어갔는지 확인")
    for (i: Int in array.indices){
        println(array[i])
    }*/

    // 서로 다른 나머지 값을 찾는 알고리즘이 필요
    // for문 돌면서 검색.

    /*
    2중포문 돌리면 같았던 값을 또 찾는다. -> 머리가 빠개짐 쥰내게 헤맸네 시벌
    그냥 1차로 for문 돌리고, 찾았던 값을 다른 배열에 저장.
    같았던 값이 있으면 중복된 값이니까 저장할 필요없음.
    저장해둔 배열(서로 다른 새로운 값들의 배열)의 index가 문제의 output.
    */

    val saveArray: Array<Int> = Array<Int>(10) { -1 }
    var saveArrayIndex = 0

    for (i in array.indices){

        var hadBeenSaved = false

        //이미 저장된값이 있었나?
        for(j in 0..saveArrayIndex){
            if(array[i] == saveArray[j]){
                //이미 같은값은 다시 count안해도 됨.
                hadBeenSaved = true
                break;
            }
        }

        //없었으면 저장!
        if(!hadBeenSaved){
            //새로운 값 찾음.
            saveArray[saveArrayIndex] = array[i]
            saveArrayIndex++
        }
    }

    /*println("=====저장된 값 확인=====")
    for(i in saveArray.indices){
        println(saveArray[i])
    }*/

    println(saveArrayIndex)
}