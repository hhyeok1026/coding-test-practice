package baekjoon.sort

/*

N개의 수가 주어졌을때, 오름차순으로 정렬

input
- 수의 개수 N ( 1 ~ 1,000,000 )
- N개의 줄에는 수가 주어진다. ( ~ 1,000,000) (수는 중복되지 않음)

output
- 첫째줄부터 N개의 줄에 오름차순으로 정렬

 */
fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val nums = mutableListOf<Int>()

    repeat(n) {
        val num = br.readLine().toInt()
        nums.add(num)
    }

    nums.sort()

    for (num in nums) {
        bw.write("$num\n")
    }

    bw.flush()
    bw.close()
    br.close()
}