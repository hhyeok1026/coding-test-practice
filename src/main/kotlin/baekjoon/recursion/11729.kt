package baekjoon.recursion._11729

import java.lang.StringBuilder

/*

하노이 탑

한번에 한개의 원판만 옮길 수 있다.
위의것은 아래것 보다 작아야한다.

작업을 수행하는데 필요한 이동순서를 출력
이동횟수는 최소가 되어야한다.

input
- 첫번째 장대에 쌓인 원판의 개수 ( 1 ~ 20 )

output
- 옮긴 횟수 k
- 수행과정을 출력, n장대에서 -> n장대로


자료구조를 스택을 써야하는건가?
배열로 일단 만들어볼까?

이것도 풀이를 보고 풀어버렸다.
가슴으로 이해가 되는데 머리로는 완벽하게 이해가 안됨.
프린트 찍어서 n이 3일때를 따라 가보았는데, 겨우3 인대도 머리가 아프다.

나중에 자료구조로 원소들 넣고, 옮겨가는거 보이게 만들고 싶다.

=================
n1

1
A  B  C
-> 1 3

=================
n2

1
2
A  B  C

2  1
A  B  C
-> 1 2
(여기까지가 n-1 하노이 전처리)

   1  2
A  B  C
-> 1 3
(바닥 옮기고)

      1
      2
A  B  C
-> 2 3
(n-1하노이 후처리)

=================

n=3

1
2
3
A B C
(시작)

n= 2에해서 전처리 시작.

2
3   1
A B C
(여기까지가 n = 1 하노이 전처리)


3 2 1
A B C
(n= 2에 대해서 바닥 옮기기)

  1
3 2
A B C
(여기까지가 n= 1 하노이 후처리)

  1
  2 3
A B C
n3에 대한 바닥 옮기기


n = 2에대해서 후처리
n = 2 - n=1에대해서 전처리

1 2 3
A B C

n=2의 후처리에 대한 바닥옮기기

    2
1   3
A B C

n=2에 후처리에서 n=1에 대해서 후처리

    1
    2
    3
A B C





 */


// 백준에 제출할때 bw를 최상위 변수로 쓰니까 틀렸습니다 라고 나온다.
// val bw = System.out.bufferedWriter()
val sb = StringBuilder()
var moveCount = 0
fun main(args: Array<String>) {

    val n = readln().toInt()

    // n은 원반의 갯수
    hanoi(n, 1, 2, 3)

    println("결과 출력 ============")
    println(moveCount)

    println(sb)
    // bw.flush()
    // bw.close()
}


// pole123이라고 쓰는거보다, start via destination으로 쓰는게 이해가 더 나을듯 하다.
fun hanoi(n: Int, pole1: Int, pole2: Int, pole3: Int) {

    moveCount++

    println("n: $n")
    println("pole1: $pole1")
    println("pole2: $pole2")
    println("pole3: $pole3")

    // n == 1일때 탈출 조건
    if (n == 1) {
        // bw.write("$pole1 $pole3\n")
        sb.append("$pole1 $pole3\n")
        println("$pole1 $pole3\n")
        return
    }

    // (n-1하노이 전처리)
    // 마지막 바닥말고 큰 덩어리 n-1개의 원판을 pole1->pole2로 옮기고 pole3가 경유지가 되는거

    println("========================")
    println("${n-1}에 대한 하노이 전처리")
    hanoi(n-1, pole1, pole3, pole2)

    // (n-1 하노이에서 전처리 끝나고 바닥에 있는거 옮기는거)
    // bw.write("$pole1 $pole3\n") // 마지막 n-1옮기고나서 pole1에 있는것을 pole3로 옮기는것.
    sb.append("$pole1 $pole3\n")

    println("${n-1}을 옮기고 나서 $n 에 대한 바닥 옮기기")
    println("$pole1 $pole3 \n")

    // (n-1 하노이 후처리)
    // 위에 hanoi(n-1) 해서 pole2에 있던것을 pole3로 다시 옮겨줘야함. pole1이 경유지로 쓰임
    println("========================")
    println("${n-1}에 대한 하노이 후처리")
    hanoi(n-1, pole2, pole1, pole3)
}