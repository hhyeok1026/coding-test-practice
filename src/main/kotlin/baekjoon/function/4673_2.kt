package baekjoon.function



// 맞췄다!
// - 이거는 뒤에 어레이리스트 붙여서 생성자들 다 확인할 수 있게 만든거.
// 중간에 로직이 이상할 수 있음.

/*
셀프넘버
d(75) = 75+7+5 = 87이다.

재귀처럼 됨
생성자
생성자가 없는게 셀프넘버
-------------
input
없음

output
10000보다 작은 셀프넘버를 출력
--------------

Dp같은 문제같기도 하고,
어떻게 접근할것인가.

일단, 셀프 넘버인지 확인하는 함수가 필요하다?
그 함수를 반복문 걸어서 돌린다? 시간제한 1초임.

생성자가 없는 것을 찾아야함.
dp처럼 풀어야하는 문제가 맞는거 같은데 왜 함수.. 문제일까
더 쉽게 할 수 있나?
해쉬맵 같은 자료형을 써야하나?

바텀업이냐 -> 1부터 테이블 계산하면서 올리기

(x) 역으로 쪼개는거는 더 어려운듯. 탑다운이냐 -> 해당 값에서 소인수분해하듯이 쪼개는 로직만들기

바텀업으로 1~10000까지 만들 수 있는 수를 쫙 만들어서 저장해야겠는데?
그리고 값이 없는 애가 셀프넘버인데
자료형을 "키(셀프넘버후보들, 범위들)" - "값(생성자들)"로 저장하는 자료형이 하나 있으면 될 듯.

//
1.
(1에서 10000까지) 1부터 시작해서 만들 수 있는 파생되는 수열을 만든다.
2. 만든 수열을 저장한다.
3. 1뒤의 숫자로 인덱스를 올리고, 이 숫자가 예전에 만든 수열에 있으면 셀프넘버가 아니다.
4. 없으면, 그 숫자로 수열을 쭉 만들어서 또 저장한다.
*/

fun main(args: Array<String>) {

    // 1. 아래 함수에서 리턴받은 값에 대해서 범위체크 (1~10000),
    // 2. (1~10000) 범위에 대해서 계속해서 재귀 호출,
    // 3. 각각 리턴 받은 값을 저장해야함

    val keyAndConstructorHashMap = HashMap<Int, ArrayList<Int>>()

    for (i in 1..10000) {

        var nowDValue = i

        while (nowDValue <= 10000) {
            // println(nowDValue)

            val generatedValue = generateNextDValue2(nowDValue)

            // 만들어지고 난게, 10000이상일 수 도 있다.
            if(generatedValue <= 10000) {
                // 키값이 없다면, 넣는다. -> 키값이 아예 없는 숫자들이 나중에 셀프넘버가 된다.
                // 만들어진게 key, 만들때 쓴게 생성자
                if (!keyAndConstructorHashMap.containsKey(generatedValue)) {
                    keyAndConstructorHashMap[generatedValue] = ArrayList()
                    keyAndConstructorHashMap[generatedValue]?.add(nowDValue)
                } else {
                    if (!keyAndConstructorHashMap[generatedValue]!!.contains(nowDValue)) {
                        keyAndConstructorHashMap[generatedValue]?.add(nowDValue)
                    }
                }
            }

            nowDValue = generatedValue
        }
    }

    /*for ((key, values) in keyAndConstructorHashMap) {
        println("key: $key, value: $values")
    }*/
    /*for (str in keyAndConstructorHashMap.entries) {
        println(str)
    }*/

    for (i in 1..10000) {
        if (!keyAndConstructorHashMap.containsKey(i)) {
            println(i)
        }
    }
}


// i 값에 대해서 특정범위내의 무한수열을 만들어내는 로직이 필요하다.
fun generateNextDValue2(number: Int) : Int {

    // (자릿수가 필요한건 아니었다.) 1. 자릿수 확인해야함.
    // Math.log10이라는 함수가 있다. (일단 이거를 안쓰고 함수로 만들어야 할 듯)
    // 나눠서 1/10 1이하가 되는 x를 구해야한다.

    // 모듈로 연산으로 나눠서 나머지 -> 끝자리수,
    // 몫이 0보다 작을때, -> 반복문 끝.
    // 정수 다루는거보다 문자열로 처리하는게 더 쉽긴하겠다.

    val fragmentedNumbers = ArrayList<Int>() // 해당 사이즈가 길이가 된다.
    var tempNumber = number

    while (tempNumber > 0) {
        // println("tempNumber : $tempNumber")
        fragmentedNumbers.add(tempNumber % 10)
        tempNumber /= 10
    }

    // println("해당 숫자의 자릿수 : ${fragmentedNumbers.size}")
    // println(fragmentedNumbers)

    // println("자릿수들의 합 : ${fragmentedNumbers.sum()}")

    val nextDValue = number + fragmentedNumbers.sum()
    // println("nextDValue: $nextDValue")

    return nextDValue
}
