package mergesortwithgpt2

/*
chat gpt를 이용해서 merge sort best practice가져왔다.
 */

fun main() {
    val arr = intArrayOf(3, 7, 1, 9, 2, 8, 4, 6, 5)
    val sortedArr = mergeSort(arr)
    println(sortedArr.contentToString())
}

fun mergeSort(arr: IntArray): IntArray {
    if (arr.size <= 1) return arr

    val mid = arr.size / 2
    val left = arr.copyOfRange(0, mid)
    val right = arr.copyOfRange(mid, arr.size)

    return merge(mergeSort(left), mergeSort(right))
}

fun merge(left: IntArray, right: IntArray): IntArray {
    var i = 0
    var j = 0
    var k = 0
    val merged = IntArray(left.size + right.size)

    while (i < left.size && j < right.size) {
        if (left[i] <= right[j]) {
            merged[k++] = left[i++]
        } else {
            merged[k++] = right[j++]
        }
    }

    while (i < left.size) {
        merged[k++] = left[i++]
    }

    while (j < right.size) {
        merged[k++] = right[j++]
    }

    return merged
}