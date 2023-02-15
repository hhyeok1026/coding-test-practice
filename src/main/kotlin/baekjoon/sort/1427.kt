package baekjoon.sort

/*

input
- 정렬하려는 수 N (0~10억)

output
- 내림차순으로 정렬한 수

단순히 charArray로 비교해도 값비교가 되는구나.
System.`in`.bufferedReader()로 버퍼리더를 가져올 수 있다.
val bw = System.out.bufferedWriter()
val br = System.`in`.bufferedReader()

 */
fun main(args: Array<String>) {
    val input = readlnOrNull() ?: throw IllegalStateException()

    val intArray = IntArray(input.length)

    for(i in input.indices) {
       intArray[i] = Character.getNumericValue(input[i])
    }

    for (i in intArray.indices) {
        for (j in intArray.indices) {
            if (j < intArray.size - 1) {
                if (intArray[j] < intArray[j + 1]) {
                    val temp = intArray[j]
                    intArray[j] = intArray[j + 1]
                    intArray[j + 1] = temp
                }
            }
        }
    }

    println(intArray.joinToString(""))
}