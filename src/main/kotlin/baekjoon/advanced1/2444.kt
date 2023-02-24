package baekjoon.advanced1

/*

// input
- n (1~100)

// output
2n-1줄까지 출력

너무 어렵게 짠거 같은 느낌이 든다.

 */

fun main(args: Array<String>) {

    val n = readln().toInt()
    val bw = System.out.bufferedWriter()

    var increase = 1
    val star = "*"
    val whiteSpace = " "

    for (i in 0 until n) {
        val nowStar = star.repeat(increase)
        val nowWhiteSpace = whiteSpace.repeat(n-i-1)
        bw.write("$nowWhiteSpace$nowStar\n")

        if (i != n-1)
            increase += 2
    }

    for (i in n-1 downTo 1) {
        increase -= 2
        val nowStar = star.repeat(increase)
        val nowWhiteSpace = whiteSpace.repeat(n - i)
        bw.write("$nowWhiteSpace$nowStar\n")
    }

    bw.flush()
    bw.close()
}