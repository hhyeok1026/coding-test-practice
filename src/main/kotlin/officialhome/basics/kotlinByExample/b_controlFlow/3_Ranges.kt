package officialhome.basics.kotlinByExample.b_controlFlow

// Ranges

fun main(){
    for(i in 0..3) { // 1
        print(i)
    }
    println()

    for(i in 0 until 3) { // 2
        print(i)
    }
    println()

    for(i in 2..8 step 2) { // 3
        print(i)
    }
    println()

    for (i in 3 downTo 0) { // 4
        print(i)
    }
    println()



    // Char ranges
    for (c in 'a'..'d') {
        print(c)
    }
    println()

    for (c in 'z' downTo 's' step 2) {
        print(c)
    }
    println()


    // Ranges in if statement

    val x = 2
    if (x in 1..5) {
        println("x is in range from 1 to 5")
    }

    if (x !in 6..10) {
        println("x is not in range from 6 to 10")
    }

}

