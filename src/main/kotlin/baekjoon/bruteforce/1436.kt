package baekjoon.bruteforce

import kotlin.math.pow

/*

(이거는 잘못짠거,
접근을 잘못했다.)

종말의수 666

문제를 보고,
입력예제4, 5를 봤을때 문제 자체가 이해가 안된다.

나만 안되는게 아니었다.
질문게시판에 누군가의 첫 질문이 문제 이해가 안된다는 질문이었다.

숫자가 작은 수로 666을 넣어서 만들어야하는 369같은 게임이라 해야하나?


// 아이디어2
규칙을 찾아서 하는게 아니라,
무작정 숫자 1씩 증가시키면서
문자열처리로 중간에 666이 들어가 있으면 배열에 넣어서 만들면 되겠네?

'666' -> 끝에 6이 있을때, 앞자리가 6이 아니면 문제가 생김. -> 이 경우 prefix를 붙임.
1666
2666
3666
4666
5666
6660 -> 앞이 6이되면, 뒷자리를 더해도 상관이 없다.
6661
6662
6663
6664
6665
'6666' -> 끝이 6인데, 앞이 6이라 문제가 없다.
6667
6668
6669 -> 끝이 9에 있을때 올림이 생겨서 문제가 생길거다.

16660
66690
66600

지금은 666 3개라서 그렇다쳐도, 6666이 4개로 있으면 머리가 더 아프겠는데?



일단 10시까지 풀어보자.


// ===========
// 아이디어1 -> 뭔가 규칙을 찾으려했음.

1.
어떻게 접근해야하나?

1-1
앞에 숫자를 붙여서 증가해야하는 경우
와
뒤에 숫자를 붙여서 증가해야하는 경우
앞/뒤 뭐가 큰 수인지 확인해야하고

1-2
둘 중 하나를 택해서 앞 또는 뒤로 1~9까지 증가시켜야하고,

1-3
다시 1-1로직을 따라가야함.

1-4
n번째까지 만들어내고 체크.

--------------------

순서대로 만든 종말의 수를 저장할 리스트가 필요함.


앞, 뒤에 숫자를 붙이는게 문자열 처리를 하는게 나을까
숫자를 더하는게 나을까?
-> 숫자 비교연산을 해야하니 숫자로 처리하는게 나을듯.

---------------------

input
- 첫째줄 n (1 ~ 10,000)

output
- 첫째줄에 N번째 영화의 제목에 들어간 수를 출력한다.

 */

fun main(args: Array<String>) {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    val first666 = 666
    val listOf666 = mutableListOf<Int>()
    listOf666.add(first666)

    // 1-4
    while (listOf666.size < n) {

        println("============")
        println("while문 들어옴")

        // 1-1
        val last666 =  listOf666[listOf666.size - 1]

        // pre, suffix 판별이 필요한가? 라는 로직이 필요함. -> 딱히 필요없네.
        // 1~9까지 증가 하고 있을때는 여기 안돌아도 됨. ->  1~9까지가 아니라 6부터 뭔가 문제가 되서 이거도 필요없다.
        var copyOfLast666 = last666
        var digitOfLast666 = 0

        while (copyOfLast666 != 0) {
            copyOfLast666 /= 10
            digitOfLast666++
        }

        val prefix666 = 10.0.pow(digitOfLast666).toInt() + last666
        val suffix666 = last666 * 10 + 1

        println("prefix666 : $prefix666")
        println("suffix666 : $suffix666")
        println("last666 + 1 : ${last666+1}")



        // val now666 : Int
        // 1-2
        // 여기서 last666이랑도 비교해서 last666에 +1해야할 경우도 있음.
        // 애초에 pre, suffix를 구해야하는 딱 케이스가 있을텐데, 그것까지 생각을 못하겠네
        // pre, suffix는 자릿수가 달라지니까 last666이랑 비교하나, last666+1이랑 비교를 하나 문제가 없겠군.
        /*if (prefix666 > suffix666) {
            now666 = prefix666
        } else {
            now666 = suffix666
        }*/

        val now666 = listOf<Int>(prefix666, suffix666, last666+1).min()

        listOf666.add(now666)

        println("listOf666.size : ${listOf666.size}")
    } // end of while


    println(listOf666.toString())

    // 결과 출력
    println(listOf666[n-1]) // 0번째부터 시작이니까 -1
}