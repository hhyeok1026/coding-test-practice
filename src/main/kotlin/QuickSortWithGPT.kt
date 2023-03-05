/*
===============================================

chatGPT로 생성한 퀵소트예제이다.

완성된 코드를 무작정 분석하는건 어렵고,
먼저, 해당 알고리즘에 대한 개념을 이해해야지 분석할 수 있다.
주석과 로그를 달아서 어떻게 진행되는지 확실히 이해했다.

그런데,
재귀 호출되면서 left right 값 벗어나는부분은 제대로 설명을 못하겠다.

=============================================

gpt가 알려준, 개념들...

위 코드에서 quickSort 함수는 partition 함수를 사용하여 분할 정복 방식으로 배열을 정렬합니다.

partition 함수는 배열의 오른쪽 끝을 피벗(pivot)으로 선택하고,
배열의 왼쪽과 오른쪽에서부터 포인터를 이동하면서 피벗보다 작은 값을 왼쪽으로, 큰 값을 오른쪽으로 이동시킵니다.

마지막으로, 피벗을 기준으로 왼쪽과 오른쪽 부분배열로 분할합니다.

위 코드에서 left 매개변수는 정렬하려는 배열의 왼쪽 인덱스를, right 매개변수는 정렬하려는 배열의 오른쪽 인덱스를 나타냅니다.
따라서 quickSort(arr, 0, arr.size - 1)과 같이 호출하면 전체 배열을 정렬할 수 있습니다.

시간 복잡도는 일반적으로 퀵소트가 최악의 경우 O(n^2) 이며, 최선과 평균의 경우 O(n log n) 입니다.
이 예제의 구현에서는 분할할 때마다 n번의 비교연산과 교환연산을 수행하므로, 평균적인 시간복잡도는 O(n log n)입니다.

하지만 최악의 경우에는 입력 배열이 이미 정렬되어 있을 때,
또는 피벗을 선택하는 방법이 최악인 경우(예: 항상 첫 번째 원소를 피벗으로 선택하는 경우) O(n^2)의 시간복잡도가 나타날 수 있습니다.

 */

fun main() {
    val arr = intArrayOf(64, 34, 25, 12, 22, 11, 90)
    quickSort(arr, 0, arr.size - 1)
    println("Sorted array: ${arr.joinToString()}")
}

fun quickSort(arr: IntArray, left: Int, right: Int) { // 정렬할 배열, 정렬할 range를 넣는다.

    println("==========================================")
    println("quickSort 들어옴 left: $left, right: $right")

    // 이 if문.. 왼쪽 오른쪽을 구별하는데, 재귀를 타게 된다면 이 if문이 맞긴하겠다.
    // 근데 유저가 배열을 통으로 정렬되기를 원할텐데, 이 함수를 랩핑하는 함수, 메서드 오버로딩한 함수가 하나 있는게 낫겠네
    if (left < right) {
        val pivot = partition(arr, left, right) // range가 짝수이면 이쁘게 쪼개지겠는데, 홀수이면 어떻게 쪼개는가?
        quickSort(arr, left, pivot - 1) // 이미 파티션 함수들어가면, pivot기준으로 왼쪽 다 정렬되는거 아닌가? 배열을 잘못이해하고 있나
        quickSort(arr, pivot + 1, right)
    } else {
        println("정렬이 필요 없는 range")
    }
}



/*
j는 right index
 arry[j]값이 피봇 값보다 작은지 보고,

i는 left index
i를 1씩 올리면서, 피봇이 들어갈 위치를 정하게 된다.

피봇이 새로 자리를 정했다면, 왼쪽에 들어간 값들은 피봇보다 작은 값이 되고,
우측편은 피봇보다 큰 값이 된다.

그리고 피봇은 제외하고 다시 좌우를 재귀로 풀어나가게 됨.
 */
fun partition(arr: IntArray, left: Int, right: Int): Int {

    println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
    println("partition 들어옴 left: $left, right: $right")

    // 맨 끝 값을 왜 pivot이라는 이름의 변수에 넣지?
    val pivot = arr[right]
    println("pivot : ${arr[right]}")

    // left - 1이면 인덱스 벗어날텐데 왜 지?, 아 반복문에서 바로 증감해서 0부터 사용하는구나.
    // 피벗보다 작은 값들의 마지막 인덱스.
    var i = left - 1 // 피봇값 보다 작은 값들의 마지막 인덱스

    // unitl로 되어 있음.
    for (j in left until right) {

        print("j: $j, ")

        if (arr[j] < pivot) { // i++은 pivot보다 arr[j]가 작으면, 계속해서 올라감. 여기서 i값을 찾아내면서, 뭔가모를 정렬이 이루어지는듯?
            i++
            print("i : $i 회전함")
            val temp = arr[i]
            arr[i] = arr[j]
            arr[j] = temp
        }

        // ==================
        // 변경확인을 위한 출력
        println()
        print("partition for-if: ")
        for (k in arr.indices) {

            if (k == left) {
                print(" | ")
            }

            print("${arr[k]} ")

            if (k == right) {
                print(" | ")
            }
        }
        println()
        // ==================
    }

    // swap()
    val temp = arr[i + 1]
    arr[i + 1] = arr[right]
    arr[right] = temp

    println("i = $i")
    println(arr.contentToString())

    println("정렬되서, 피봇이 찾아간 자기 위치 : ${i + 1}")
    return i + 1
}


// GPT가 달아준 partition함수에 대한 주석
// 주석을 다 분석하고 나서 보니까, 엄청 잘 달아줬다...
/*
fun partition(arr: IntArray, left: Int, right: Int): Int {
    // 피벗을 선택합니다. 이 예제에서는 배열의 오른쪽 끝을 피벗으로 선택합니다.
    val pivot = arr[right]
    // i는 피벗보다 작은 값들의 마지막 인덱스를 나타냅니다.
    var i = left - 1
    // 배열의 왼쪽과 오른쪽에서부터 포인터를 이동하면서 피벗보다 작은 값을 왼쪽으로, 큰 값을 오른쪽으로 이동시킵니다.
    for (j in left until right) {
        if (arr[j] < pivot) {
            i++
            // arr[i]와 arr[j]를 교환합니다.
            val temp = arr[i]
            arr[i] = arr[j]
            arr[j] = temp
        }
    }
    // 피벗을 기준으로 왼쪽과 오른쪽 부분배열로 분할하기 위해 피벗을 올바른 위치로 이동시킵니다.
    val temp = arr[i + 1]
    arr[i + 1] = arr[right]
    arr[right] = temp
    // 피벗의 위치를 반환합니다.
    return i + 1
}
*/

