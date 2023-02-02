package baekjoon.loop

import java.util.Scanner

/*
영수증

영수증에서 계산된 값을 확인하려한다


// 입력
총금액
구입한 물건 수
가격 갯수

// 출력 : Yes or No


// 아직 입력부분을 받는게 엉성하다.
 */

fun main(array: Array<String>) {

    val totalPrice = readLine()?.toInt() ?: return
    // println("totalPrice : $totalPrice")
    var totalNum = readLine()?.toInt() ?: return
    // println("totalNum : $totalNum")

    val scanner = Scanner(System.`in`)
    val input = mutableListOf<Int>()
    while (scanner.hasNext()) {
        // String! 에서 ! 플랫폼 타입, nullable, non-null 알아서 처리해야함
        val item = scanner.nextLine().split(" ").map { it.toInt() }
        input.add(item[0])
        input.add(item[1])
    }
    scanner.close()

    // println(input)

    var calculatedPrice = 0
    var nowIndex = 0

    while (totalNum > 0) {

        // 가격, 갯수
        val price = input[nowIndex]
        nowIndex++
        val num = input[nowIndex]
        nowIndex++

        calculatedPrice += ( price * num )
        // println("calculatedPrice : $calculatedPrice")

        totalNum--
    }

    if (totalPrice == calculatedPrice)
        print("Yes")
    else
        print("No")
}