package officialhome.basics.kotlinByExample.e_collections

// List
/*
 아이템에 순서가 있다.
 MutableList -> 읽고 쓰기 가능.
 List -> read-only
*/

val systemUsers: MutableList<Int> = mutableListOf(1, 2, 3)
val sudoers: List<Int> = systemUsers

fun addSystemUser(newUser: Int) {
    systemUsers.add(newUser)
}

fun getSysSudoers(): List<Int> {
    return sudoers
}

fun main() {
    addSystemUser(4)
    println("Tot sudoers: ${getSysSudoers().size}")
    getSysSudoers().forEach {
            i -> println("Some useful info on user $i")
    }

    // getSysSudoers().add(5)
    // sudoers.add(5)
}