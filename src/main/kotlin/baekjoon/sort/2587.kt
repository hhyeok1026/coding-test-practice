package baekjoon.sort


/*
input
- 총 5줄 한줄씩 자연수 하나 ( 100보다 작은 10의 배수들 )

output
- 평균
- 중앙값 (정렬시 중앙에 오는값, 인덱스2)

버블소팅도 직접해보니 어렵다..,,,,,,,,,, 버블이 맞는지 모르겠지만..
 */
fun main(args: Array<String>) {

    var sum = 0
    val nums = IntArray(5)

    for (i in nums.indices) {
        val num = readlnOrNull()?.toInt() ?: throw IllegalStateException()
        // println(num)

        nums[i] = num
        sum += num
    }
    // 넣으면서 정렬하려고 하니까 버그가 생기고 머리가 복잡해진다.
    // 다 넣고나서, 정렬한번만 하면 된다.

    // 정렬로직, 아까 삽입 정렬?했으니, 이번은 버블소팅? 해볼까
    for (i in nums.indices) {
        /*     print("정렬전 i $i ; ")
        nums.forEach { print("$it ") }
        println()*/

        // 한 회전마다 제일 큰 값이 제일 뒤로 가게 된다.
        for (j in nums.indices) {
            if (j + 1 < nums.size){
                if (nums[j] > nums[j+1]) {
                    val temp = nums[j]
                    nums[j] = nums[j+1]
                    nums[j+1] = temp
                    // println("nums[$j]: ${nums[j]}")
                    // println("nums[${j+1}]: ${nums[j+1]}")
                }
            }
        }
    }

  /*  println("정렬 완료 : ")
    nums.forEach { print("$it ") }
    println()*/

    val average = sum / 5
    val medium = nums[2]

    println(average)
    println(medium)
}