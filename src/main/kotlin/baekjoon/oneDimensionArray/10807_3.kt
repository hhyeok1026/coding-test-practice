package baekjoon.oneDimensionArray

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer


/*
안린이님이 주신 코드.
* */
fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val data = StringTokenizer(readLine())
    Array(n){data.nextToken().toInt()}.also {
        val target = readLine().toInt()
        println(it.count {data -> data == target })
    }
}