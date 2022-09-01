package officialhome.basics.kotlinByExample.d_Functional

// Lambda Functions


fun main(){
    val upperCase1: (String) -> String = { str: String -> str.uppercase() }
    val upperCase2: (String) -> String = { str -> str.uppercase() }
    val upperCase3 = { str: String -> str.uppercase() }

    // val upperCase4 = { str -> str.uppercase() } // retrun 부분은 추론이 되는데, 파라미터 받는부분은 추론할 수 없나봄.

    val upperCase5: (String) -> String = { it.uppercase() }
    val upperCase6: (String) -> String = String::uppercase //TODO 함수 포인터 연산자(::)가 어떻게 쓰인건지 제대로 이해안됨.

    println(upperCase1("hello"))
    println(upperCase2("hello"))
    println(upperCase3("hello"))
    println(upperCase5("hello"))
    println(upperCase6("hello"))
}

