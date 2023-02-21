package baekjoon.string

/*

input
- 최대 100줄 (각 줄은 100글자를 넘지 않음)

output
- 입력받은 것을 그대로 출력한다.

 */
fun main(args: Array<String>) {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    while (true) {
        val str = br.readLine()

        // if (str.isEmpty()) { // 이거는 널이 아니어야만 작동함.
        if (str.isNullOrEmpty()) {
            break
        }

        bw.write("$str\n")
    }

    br.close()
    bw.flush()
    bw.close()
}