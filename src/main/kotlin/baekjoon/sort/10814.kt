package baekjoon.sort

import java.util.StringTokenizer

/*

input
- 회원수 N (1 ~ 100,000)
- 각 회원의 나이 이름 (나이 1~200, 이름은 알파벳 대소문자)

output
- 회원을 한 줄씩, 나이순, 나이가 같으면 가입순으로 출력


나는 컬렉션의 메서드를 그냥 썼고,
값이 같은 원소의 전후가 바뀌지 않는것을 안정정렬 (stable sort)라고 하는데,
이미 컬렉션 sort에 이게 구현이 되어 있는거 같다.
이거를 따로 구현하는것을 해봐야할듯한데..
 */

fun main(args: Array<String>) {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val N = br.readLine().toInt()

    val list = mutableListOf<Member>()

    for (i in 0 until N) {
        val st = StringTokenizer(br.readLine())
        val age = st.nextToken().toInt()
        val name = st.nextToken()

        list.add(Member(age, name))
    }

    list.sortWith(compareBy<Member> { it.age } )
    // println(list)

    list.forEach {
        bw.write("${it.age} ${it.name}\n")
    }

    br.close()
    bw.flush()
    bw.close()
}

data class Member(val age: Int, val name: String)