package baekjoon.string

/*

input
- T, 테스트케이스 수 (1~10)
- 문자열들..

output
- 문자열의 첫글자와 끝글자


 */
fun main(args: Array<String>) {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val t = br.readLine().toInt()

    for (i in 0 until t) {
        val str =  br.readLine().toString()

        val firstChar = str[0]
        val lastChar = str[str.length - 1]

        bw.write("$firstChar$lastChar\n")
    }

    br.close()
    bw.flush()
    bw.close()
}