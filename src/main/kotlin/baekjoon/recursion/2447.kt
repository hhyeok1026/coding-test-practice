package baekjoon.recursion

/*
input
- N (N은 3의 거듭제곱, N은 3k승이다), k (1~8)

output
- 첫째줄에서 N번째 줄까지 별을 출력

크기3
***
* *
***

***

 */




fun main(args: Array<String>) {

    val N = readln().toInt()

    printStart3(N)
}

fun printStart3(N: Int) {

    if (N == 0) {
        return
    }
    else if (N == 3) {
        println("***")
        println("* *")
        println("***")
    } else {


        printStart3(N / 3)
    }
}