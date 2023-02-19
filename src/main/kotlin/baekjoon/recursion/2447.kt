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

chatgpt에게 물어서, 정확한 답도 받고, 틀린 답도 받았는데,
틀린 답에서, 코드가 이해가 더 잘되었고, 거기서 내가 좀 더 응용해서 풀어냈다.
 */
fun main(args: Array<String>) {

    val n = readln().toInt()

    val pattern = makePattern(n)

    for (i in pattern.indices) {
        println(pattern[i])
    }
}

fun makePattern(n: Int): Array<String> {

    if (n == 3) {
        return arrayOf("***", "* *", "***")
    }

    val prePattern = makePattern(n / 3)
    val preSize = prePattern.size
    val pattern = Array(preSize * 3) { "" }

    // println("preSize : $preSize")
    // println("pattern.size : ${pattern.size}")

    for (i in pattern.indices) {
        // println("i: $i")

        if (i / preSize == 1) {
            pattern[i] =
                prePattern[i % preSize]+ " ".repeat(preSize) + prePattern[i % preSize]
        } else {
            pattern[i] = prePattern[i % preSize].repeat(3)
        }
    }

    return pattern
}
