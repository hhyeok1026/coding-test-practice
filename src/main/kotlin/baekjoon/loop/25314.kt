package baekjoon.loop

/*

input
- N (4의 배수, 4~1000)

output
- long.. int

 */
fun main(args: Array<String>) {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()

    // 반복문이라기에는 좀.. 그런데, 이게 편할듯.
    repeat(n / 4) {
        bw.write("long ")
    }
    bw.write("int")

    bw.flush()

    br.close()
    bw.close()
}