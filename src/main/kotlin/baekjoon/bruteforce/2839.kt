package baekjoon.bruteforce

/*

설탕을 정확하게 N킬로그램을 배달해야한다.
설탕봉지는 3키로, 5키로 봉지가 있다.

적은 봉지를 가지고 가려함.
18키로를 배달하려면, 3키로 6개를 가져가도 됨.
하지만, 5키로 3개와 3키로 1개를 배달하면 더 적은 봉지를 배달가능.

상근이가 설탕을 N킬로그램 배달해야 할 때,
봉지를 몇 개 가져가면 되는지 그 수를 구하는 프로그램을 작성.

input
- N (3 ~ 5000)

output
- 배달하는 봉지의 최소 개수, 정확하게 N킬로그램을 만들 수 없으면, -1

접근.
N에 근접할 수 있는 5의 배수를 구한다.
그리고 'N-(5의 배수)'에 대해서 3의 배수인지 확인한다.

3의 배수가 아니라면,
5의 배수의 값을 줄여 나간다.

-------------------

이 문제 정답률이 낮은게 이상한데,
기본수학1에 있을때 어떻게 접근할지 몰라서 어려웠던걸까?

 */
fun main(args: Array<String>) {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    /*
    println("quotientOf5 : $quotientOf5")
    println("remainderOf5 : $remainderOf5")
    println("quotientOf3 : $quotientOf3")
    println("remainderOf3 : $remainderOf3")
    */

    // 5의 몫
    var quotientOf5 = n / 5
    // 5의 나머지
    val remainderOf5 = n % 5

    // quotientOf5가 0일때도 돌아야해서 -1
    while (quotientOf5 > -1) {

        if (remainderOf5 == 0) { // 5키로로 딱 떨어진 경우.
            println(quotientOf5)
            return // break가 아니라 return을 해서 프로그램을 끝내야함.
        }

        val quotientOf3 = (n - quotientOf5 * 5) / 3 //5키로 의 몫에서 하나 씩 빼서 3키로로 만듦
        val remainderOf3 = (n - quotientOf5 * 5) % 3 // 3키로로 딱 떨어지는경우로 출력해야하는 경우이다, 5키로 + 3키로 조합.

        if (remainderOf3 == 0) {
            println(quotientOf5 + quotientOf3) //5키로가 0일 수도 있어서 3키로만 있는 경우도 커버됨.
            return
        }

        quotientOf5-- // 해당 5키로의 몫으로는 구할 수 없어서, 5키로 몫을 줄여서 3키로 몫을 늘려나감.
    }

    println(-1) // while문에서 값을 찾았으면, return되어 프로그램이 종료되고, 못찾았으면 -1이 출력됨.
}