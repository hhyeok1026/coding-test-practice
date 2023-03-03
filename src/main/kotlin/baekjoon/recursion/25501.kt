package baekjoon.recursion._25501

/*
팰린드롬 : 앞뒤가 똑같은 전화번호~

로직은 제공?

팰린드롬이 맞는지 반환값과, 재귀함수 호출 횟수

input
- T, 테스트 케이스 개수 (1 ~ 1000)
- 대문자로 된 알파벳 (1 ~ 1000)

output
-

 */

var count = 0
fun main(args: Array<String>) {

    val br = System.`in`.bufferedReader()
    val T = br.readLine().toInt()

    for (i in 0 until T) {
        val string = br.readLine()
        isPalindrome(string)
    }

    br.close()
}

// 함수의 반환값과, recursion함수의 호출 횟수를 출력해야함.
fun isPalindrome(s: String) {
    count = 0
    val isPalindrome = recursion(s, 0, s.length-1)
    println("$isPalindrome $count")
}

fun recursion(s: String, l: Int, r: Int): Int {
    count++
    if (l >= r) return 1
    else if (s[l] != s[r]) return 0
    else return recursion(s, l+1, r-1)
}
