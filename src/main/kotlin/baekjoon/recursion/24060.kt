package baekjoon.recursion

import java.util.StringTokenizer

/*

input
- N (배열A의 크기 5 ~ 500,000), k번째 저장횟수
- 배열의 원소들 공백으로 분리, (1 ~ 1,000,000,000 십억)

output
- 배열에 k번째 저장된 수를 출력, 머지정렬에서 저장횟수가 입력으로 온 k보다 작으면, -1을 출력

의사코드를 kotlin소스로 옮기는게 문제인데,
의사코드를 제대로 이해를 못하겠네

병합정렬을 재귀로 구현해야함.
이거를 병합정렬 알고리즘이 어떻게 되는지는 몰라도, 풀긴했는데.
병합정렬이 어떻게 이루어지는지 한번 공부를 해야 할 듯. -> chatgpt에게 bestpractice와 주석을 달라고 해야겠다.

 */

var saveCount = 0
var k = 0
fun main(args: Array<String>) {
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    k = st.nextToken().toInt() // 찾을 저장 횟수번째 k
    val arrayA = IntArray(n)

    st = StringTokenizer(br.readLine())
    for (i in arrayA.indices) {
        arrayA[i] = st.nextToken().toInt()
    }

    // 입력 완료 확인
    // println(arrayA.contentToString())

    mergeSort(arrayA, 0, arrayA.size - 1)

    if (saveCount < k) {
        println("-1")
    }

    // 정렬 완료 확인
    // println(arrayA.contentToString())
}

// A[p..r]을 오름차순 정렬한다.
// 함수시그니처가 이렇게 되는게 맞으려나
fun mergeSort(array: IntArray, p: Int, r: Int) {

    if (p < r) {
        val q = (p + r) / 2
        mergeSort(array, p, q) // 전반부 정렬
        mergeSort(array, q + 1, r) // 후반부 정렬

        merge(array, p, q, r)
    }
}

/*
A[p..q] 와 A[q+1..r]을 병합하여 A[p..r]을 오름차순 정렬된 상태로 만든다.
A[p..q] 와 A[q+1..r]은 이미 오름차순으로 정렬되어 있다.
 */
fun merge(array: IntArray, p: Int, q: Int, r: Int) {

    // 왜 여기서 변수를 따로 받아두려하지?
    // 반복문 돌면서 증감되고, 기존 pqr값은 유지되야하는구나.
    var i = p
    var j = q + 1
    var t = 0 // t가 1부터 시작하는게 맞나? 0되어야하는거 아닌가?

    // 의사코드에 temp 선언 위치, 사이즈 설명이 안되어 있음.
    // 사이즈는 q-p+1이 맞는것 같은데,,, 다시보니.. r-q+1이였다..
    val temp = IntArray(r-p+1)

    while (i <= q && j <= r) {
        if (array[i] <= array[j]) {
            temp[t++] = array[i++]
        } else {
            temp[t++] = array[j++]
        }
    }

    /*println("q: $q")
    println("p: $p")
    println("r: $r")
    println("temp.size: ${temp.size}")
    println("t: $t")
    println("array.size: ${array.size}")
    println("i: $i")*/

    while (i <= q) { // 왼쪽 배열 부분이 남은 경우

        // 여기서 ArrayIndexOutOfBoundException이 뜨는데.. 의사코드가 문제가 있는듯한데.
        // 내가 temp의 size를 잘못구했었다.
        temp[t++] = array[i++]
    }

    while (j <= r) { // 오른쪽 배열 부분이 남은 경우
        temp[t++] = array[j++]
    }

    // 인덱스 초기화
    i = p
    t = 0
    while (i <= r) { // 결과를 A[p..r]에 저장
        array[i] = temp[t]
        saveCount++

        if(saveCount == k){
            println(array[i])
        }

        i++
        t++
    }
}
