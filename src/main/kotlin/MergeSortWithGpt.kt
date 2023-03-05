package mergesortwithgpt1

/*
chat gpt를 이용해서 merge sort best practice가져왔다.
 */

fun main(args: Array<String>) {
    val arr = intArrayOf(3, 7, 1, 9, 2, 8, 4, 6, 5)
    mergeSort(arr)
    println(arr.contentToString())
}

fun mergeSort(arr: IntArray) {

    println("=========================================")
    println("mergeSort - arr: ${arr.contentToString()}")

    if (arr.size <= 1) return

    val mid = arr.size / 2
    val left = arr.copyOfRange(0, mid) // mid미포함
    val right = arr.copyOfRange(mid, arr.size)

    mergeSort(left)
    mergeSort(right)

    merge(left, right, arr)
}

fun merge(left: IntArray, right: IntArray, arr: IntArray) {

    println("=========================================")
    println("merge - left: ${left.contentToString()}")
    println("merge - right: ${right.contentToString()}")
    println("merge - arr: ${arr.contentToString()}")

    var i = 0
    var j = 0
    var k = 0

    while (i < left.size && j < right.size) {
        if (left[i] <= right[j]) {
            arr[k++] = left[i++]
            println("merge - while1 - left++ - k: $k, i: $i, arr: ${arr.contentToString()}")
        } else {
            arr[k++] = right[j++]
            println("merge - while1 - right++ - k: $k, j: $j, arr: ${arr.contentToString()}")
        }
    }

    while (i < left.size) {
        arr[k++] = left[i++]
        println("merge - while2 - left++ - k: $k, i: $i, arr: ${arr.contentToString()}")
    }

    while (j < right.size) {
        arr[k++] = right[j++]
        println("merge - while3 - right++ - k: $k, j: $j, arr: ${arr.contentToString()}")
    }
}