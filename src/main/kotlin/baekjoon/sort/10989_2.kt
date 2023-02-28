package baekjoon.sort

/*

카운팅정렬을 구현해봤다.


메모리 초과가 나와서
accumulatedCounts변수를 없앴고,
IntArray로 수정. (ok)

input
- 수의 개수 N (1 ~ 1,000,000)
- 수 ( ~ 10,000)

output
- 오름차순으로 하나씩 출력

 */
fun main(args: Array<String>) {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val n = br.readLine().toInt()
    val inputNums = IntArray(n)
    val resultNums = IntArray(n)

    var max = 0

    // 입력을 받는다
    repeat(n) {
        val num = br.readLine().toInt()
        inputNums[it] = num

        if (num > max)
            max = num
    }

    val counts = IntArray(max + 1)

    // counts 값을 구한다.
    for (num in inputNums) {
        counts[num]++
    }
    
    // counts에 누적합을 구한다.
    for (i in 1 until counts.size) {
        counts[i] = counts[i] + counts[i-1]
    }

    // println(inputNums.contentToString())

    // input값들을 -> result에 넣어준다.
    for (i in inputNums.indices) {

        // println("============================")
        // println("num : ${inputNums[i]}")

        // println("counts : " + counts.contentToString())
        // println("resultNums: " + resultNums.contentToString())

        val index = counts[inputNums[i]] - 1 // 배열시작은 0부터니까
        // println("index: $index")

        counts[inputNums[i]]--
        resultNums[index] = inputNums[i]

        // println("counts : " + counts.contentToString())
        // println("resultNums: " + resultNums.contentToString())
    }

    // 출력
    resultNums.forEach {
        bw.write("$it\n")
    }

    bw.flush()
    bw.close()
    br.close()
}