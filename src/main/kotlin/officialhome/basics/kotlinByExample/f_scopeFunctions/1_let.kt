package officialhome.basics.kotlinByExample.f_scopeFunctions

/*
    let
    범위 지정과 널체크에 사용할 수 있다.
    object 가 호출되면 block이 실행되고, block내에서 it으로 object를 사용 할 수 있다.
*/

fun customPrint(s: String) {
    print(s.uppercase())
}

fun main() {
    val empty = "test".let {
        customPrint(it)
        println()
        it.isEmpty()
    }

    println(" is empty: $empty")


    fun printNonNull(str: String?) {
        println("Printing \"$str\":")

        str?.let{
            print("\t")
            customPrint(it)
            println()
        }
    }

    fun printIfBothNonNull(strOne: String?, strTwo: String?) {
        strOne?.let { firstString ->
            strTwo.let { secondString ->
                customPrint("$firstString : $secondString")
                println()
            }
        }
    }

    printNonNull(null)
    printNonNull("my string")
    printIfBothNonNull("First", "Second")
}