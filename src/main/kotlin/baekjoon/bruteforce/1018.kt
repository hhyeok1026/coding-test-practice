package baekjoon.bruteforce

import java.util.StringTokenizer

/*

==> 함수 하나 빼야하는 리펙토링 필요해서, 2번째 파일로 만들었음.

m x n 사이즈의 보드
이 보드를 잘라서 8 x 8의 체스판으로 만드려한다.

체스판은 기본적으로
맨왼쪽 위칸이 흰색, 또는 검은색
두가지 경우.

하지만 보드가 체스판처럼 색칠되어 있을 보장이 없다.

그래서 8 x 8로 잘라서 정사각형을 다시 색칠해야함.
8 x 8은 아무거나 골라도 된다.
(하지만 색칠 할 것을 최소값으로 구해야함)

input
- n m (8 ~ 50)
- 체크판 색칠 상태 (W는 흰색, B는 검정)

output
- 색칠해야하는 최소수

// 예제 입력4번 케이스에서 문제가 생기는데.... 골때린다.
// 이게, 첫글자를 보고 뒤의 패턴을 정해야하는게 아니라,
한 보드내에서도 그냥 불규칙한 패턴이라 생각하고,
B로 시작할때의 케이스와 W로 시작할때 둘 다 구해서
작은걸로 픽해야하는구나..

 */
fun main(args: Array<String>) {
    val br = System.`in`.bufferedReader()
    val st = StringTokenizer(br.readLine())

    val n = st.nextToken().toInt() // 행
    val m = st.nextToken().toInt() // 열

    val boardState = Array(n) { CharArray(m) } // null로 값이 들어가는듯.

    // println(boardState.contentDeepToString())
    // println("초기화된 상태")

    // 1. 일단 보드의 상태를 입력을 받자
    for (rowNum in 0 until n) {

        val rowString = br.readLine()

        for (colunmNum in 0 until m) {
            boardState[rowNum][colunmNum] = rowString[colunmNum]
        }
    }

    println(boardState.contentDeepToString())
    println("입력 받아서 저장한 상태")

    var minFixNum : Int = 64 // 모두 고쳐야할 경우는 64개이다.
    // 2. 자르는 로직이 필요함
    // -> 입력받은거에서 8x8로 자를 수 있는 모든 구간을 테스트해야함.
    for (baseRowNum in 0 until n) {
        // 탈출할 수 있는 구간을 만들어야함.
        // n = 8일경우.
        // rowNum = 7
        // 0 1 2 3 4 5 6 7
        if (baseRowNum + 7 >= n) {
            break
        }

        for (baseColumnNum in 0 until m) {
            if (baseColumnNum + 7 >= m) {
                break
            }

            // 바깥 for문 두개는 새로운 '모든' 보드를 구하는 반복문.
            // 이제 이 안쪽 for는 W B를 체크하는 반복문.

            println("현재 선택한 보드의 base : $baseRowNum $baseColumnNum")

            // W or B
            val firstState = boardState[baseRowNum][baseColumnNum]
            val seconState = if (firstState == 'W') 'B' else 'W'
            var switch = 1 // 1 or 2 1이면 first가 와야하는 경우고, 2이면 second가 와야할 경우.
            var nowFixNum = 0

            println("firstState: $firstState")
            println("seconState: $seconState")
            println("switch: $switch")
            println("nowFixNum: $nowFixNum")

            // 이 for내부에서 상태체크를 해야함.
            // 3. 색칠 바꿔야하는것들 판별해서 수정횟수를 체크해둬야함.
            for (checkRow in baseRowNum..(baseRowNum + 7) ) {
                for (checkColumn in baseColumnNum..(baseColumnNum + 7) ) {

                    if (switch == 1) {
                        if(firstState == boardState[checkRow][checkColumn]) {
                            // 수정 필요없음.
                        } else {
                            // 수정 필요
                            nowFixNum++
                            println("문제가 있는 곳 : $checkRow, $checkColumn")
                        }
                        switch = 2
                    } else { // switch == 2 // 두번째 문자랑 같아야하는 경우.
                        if (seconState == boardState[checkRow][checkColumn]) {
                            // 수정 필요없음.
                        } else {
                            // 수정 필요.
                            nowFixNum++
                            println("문제가 있는 곳 : $checkRow, $checkColumn")
                        }
                        switch = 1
                    }

                    // column의 끝에 도달해서, 다음 row로 갈 때는, switch를 변경시켜야함.
                    // 이렇게 flag세우니까 코드가 번거러운거 같은데, row, column을 홀짝으로 해서 구하는게 더 나을거 같긴하다.
                    // 일단 이렇게 만들었으니 이렇게 결과를 내보자, 그리고 이런 flag는 불리언으로 ! 연산자 써서 만드는게 더 편한거 같다.
                    if (checkColumn == baseColumnNum + 7) {
                        if (switch == 1)
                            switch = 2
                        else
                            switch = 1
                    }
                }
            }

            println("구해낸 nowFixNum : $nowFixNum")

            // 수정해야할 횟수는 구했고, 이게 최소인지 저장해야함
            if (nowFixNum < minFixNum ) {
               minFixNum = nowFixNum
            }


        }
    }

    println(minFixNum)
}