package baekjoon.twoDimensionArray

/*

A A B C D D
a f z z
0 9 1 2 1
a 8 E W g 6
P 5 h 3 k x


input
- 5줄, (1 ~ 15)
-

output
- 세로 읽은거 출력

*/

fun main(args: Array<String>) {

    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val words = Array<String>(5) { "" }
    var columnMaxLength = 0

    repeat(5) {
        words[it] = br.readLine()

        if (words[it].length > columnMaxLength) {
            columnMaxLength = words[it].length
        }
    }

    /*
    00 01 02 03 04
    10 11 12 13 14
    20 ...
    */
    for (i in 0 until columnMaxLength) { // 열
        for (j in words.indices) { // 행
            if (words[j].length > i)
                // print(words[j][i])
                // bw.write("${words[j][i]}")
                bw.write(words[j][i].toString())
        }
    }

    bw.flush()
    bw.close()
    br.close()
    //println(words.contentToString())
}