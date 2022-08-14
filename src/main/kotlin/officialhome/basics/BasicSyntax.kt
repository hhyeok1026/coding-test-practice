package officialhome.basics

//08.11.2022
//코틀린 basic syntax 문서를 공부해 보았다.
//https://kotlinlang.org/docs/basic-syntax.html#conditional-expressions

// 바로 실행해 볼 수 있게, kt파일로 만들었다. -> 사실 저 doc에서 playground로 웹에서 바로 실행이 가능하다.
// txt파일은 code edit에서 색상구별이 안되서 이걸로 만들었음.
// 주석때문에 불편할 수 있음. 내가 보려고 만든거라서

// -> main 문을 하나로 만들어서 거기에 주석을 달자? 이것도 실행해보기에는 않좋을듯한데.. -> 그냥 냅두자.
// (지금 한번하고 안보겠지만, 나중에 기억에 안나는거 있으면 눈으로 슥 훑는 용으로 쓰자.)




//레이블용 주석
/*===============================
===============================*/



/*===============================
Package definition and imports
소스의 top부분에 작성해야함.
===============================*/
/*
package my.demo

import kotlin.text.*
*/



/*===============================
Program entry point 프로그램 진입점
===============================*/

//String의 다양한 arguments를 가질 수 있다.
/*
fun main(args: Array<String>) {
    println(args.contentToString())
}
*/

//arg 쓰지 않을때, 간단하게 작성.
/*
fun main(){
    println("Hello world!")
}
*/


/*===============================
Print to the standard output
표준 입출력으로 프린트 하기
===============================*/

/*
fun main(){
    //줄바꿈 없이 단순 출력
    print("Hello")
    print("World!")

    //줄바꿈 추가
    println("Hello world!")
    println(42)
}
*/


/*===============================
Functions
함수

doc - concepts-> function에서 더 자세하게 다룸
===============================*/

//2개의 Int 입력과 Int타입의 return
fun sum1(a: Int, b: Int): Int {
    return a + b
}

//return 값은 추론 될 수 있어서 타입 생략 가능,
// 브라켓 없이 표현식으로 작성가능
fun sum2(a: Int, b: Int) = a + b

//return 값이 없이 단순 실행하는 void타입의 함수.
// (코틀린에서는 Unit으로 리턴타입을 써야하는듯)
fun printSum1(a: Int, b: Int): Unit {
    print("sum of $a and $b is ${ a + b }")
}

//Unit타입 리턴시, 리턴타입 생략가능.
fun printSum2(a: Int, b: Int) {
    print("sum of $a and $b is ${ a + b }")
}


/*===============================
Variables
변수
===============================*/

/*
fun main(){

    //val 변수
    //읽기 전용 변수, val로 정의(선언)하고, 값은 한번만 할당할 수 있다.
    val a: Int = 1 //즉시 할당
    val b = 2 //타입 추론됨
    val c: Int //할당이 필요한 경우
    c = 3 //지연 할당


    //var
    //var로 정희하면 재할당이 가능하다.
    //var x = 5 //타입 추론
    //x += 1

    //최상위 선언된 x 테스트
    //increamentX()
    //print(x)
}

//변수는 최상위 level에 선언될 수 있다.
val PI = 3.14
var x = 0

fun increamentX(){
    x += 1
}

*/

//=================================================

/*===============================
Creating classes and instances
클래스와 인스턴스 생성하기

concept - class and object에서 자세하게 다룸.
===============================*/

//클래스를 생성할때는 class라는 키워드를 사용
class Shape


//클래스의 속성은, 선언이나 바디부분에 작성될 수 있다.
class Rectangle(var height: Double, var length: Double){
    var perimeter = (height + length) * 2  // perimeter (페레미러/페레미터) 둘레
}


/*===============================
Comment
주석
===============================*/

// This is an end-of-line comment

/* This is a block comment
   on multiple lines. */


/* The comment starts here
/* contains a nested comment */
and ends here. */



/*===============================
String template
문자열 템플릿
===============================*/

/*
fun main(){
    var a = 1
    val s1 = "a is $a"   //$써서 문자열에 변수 바로 넣는거를 문자열 템플릿이라고 하나보다.

    a = 2

    //arbitrary(임의, 아비트레리/아비튜리) expression in template
    // -> 문자열 템플릿을 브라켓으로 감싸면 표현식을 넣어서 간단한 계산같은거도 넣을 수 있나보다.
    val s2 = "${s1.replace("is", "was")}, but now is $a"

    print(s2)
}
*/


/*===============================
Conditional expressions
조건문
===============================*/

fun maxOf(a: Int, b: Int): Int {
    if( a > b ) {
        return a
    } else {
        return b
    }
}

//코틀린에서는 if는 표현식으로 작성 될 수 있다.
fun maxOf2(a: Int, b: Int) = if ( a > b ) a else b




/*===============================
for 반복문
===============================*/

/*
fun main(){

    val items = listOf("apple", "banana", "kiwifruit")

    //자바의 향상된 for문 타입이다.
    for (item in items){
        println(item) //아래와 달리 index 활용 못 할 듯.
    }

    /*============
    or
    ===========*/

    val items2 = listOf("apple", "banana", "kiwifruit")
    for (index in items2.indices) { //indices *색인s, index의 복수형이다.

        //index값을 이용하여 접근, for문 내에서 index를 사용할때 필요하겠다.
        println("item at $index is ${items2[index]}")
    }

}
*/


/*===============================
while 반복문
===============================*/


/*
fun main(){
    val items = listOf("apple", "banana", "kiwi")
    var index = 0

    while (index < items.size) {
        println("item at $index is ${items[index]}")
        index++
    }
}
*/



/*===============================
when expression
===============================*/

//자바의 switch문과 유사.

/*

fun describe(obj: Any): String =
    when (obj) {
        1          -> "One"
        "Hello"    -> "Greeting"
        is Long    -> "Long"
        !is String -> "Not a string"
        else       -> "Unknown"
    }

fun main(){
    println(describe(1))
    println(describe("Hello"))
    println(describe(1L))
    println(describe(true))
    println(describe("ccc"))
}

*/



/*===============================
Ranges

범위 - 이게 java와 다르게 독특해서 꼭 알고 넘어가야 할 듯.
===============================*/

/*

fun main(){

    val x = 10
    val y = 9

    //그냥 in을 쓰면 범위포함(이상~이하, 초과/미만아님.)이다.
    if (x in 1..y+1) {
        println("fits in range")
    }


    //in 앞에 !를 붙이면 범위를 벗어나는것인지 확인 할 수 있다? -> 범위를 벗어난 상태에서만 body가 실행되는듯?
    val list = listOf("a", "b", "c")
    if (-1 !in 0..list.lastIndex) {
        println("-1 is out of range")
    }
    if (list.size !in list.indices) {
        println("list size is out of valid list indices range, too")
    }


    //범위를 반복함.
    for (x in 1..5) {
        print(x)
    }
}

*/


/*===============================
Collections

컬렉션
===============================*/

fun main(){

    //Collections을 위한 반복기
    val items = listOf("orrange", "apple", "c")
    for (item in items) {
        println(item)
    }

    //in 연산자를 이용해서 collection안에 내용을 포함하는지 확인할 수 있다.
    when {
        "orange" in items -> println("juicy")
        "apple" in items -> println("apple is fine too")
    }

    //람다 표현식(중괄호의 표현식)을 이용하여, 컬렉션을 필터 및 매핑.
    val fruits = listOf("banana", "avocado", "apple", "kiwifruit")
    fruits
        .filter { it.startsWith("a") }
        .sortedBy { it }
        .map { it.uppercase() }
        .forEach { println(it) }
}


/*===============================
Nullable values and null checks
null이 올 수 있는 값과 null 확인
===============================*/

// null값이 가능할 때, 반드시 명시적으로 널이라는것을 mark해줘야함.
// null타입은 타입뒤에 ?를 붙인다.


//str값이 int로 파싱되지 못하면 null이 나올것이다.
/*
fun parseInt(str: String): Int? {
    // ...
}
 */

/*
fun printProduct(arg1: String, arg2: String) {
    val x = parseInt(arg1)
    val y = parseInt(arg2)

    // Using `x * y` yields error because they may hold nulls.
    if (x != null && y != null) {
        // x and y are automatically cast to non-nullable after null check
        println(x * y)
    }
    else {
        println("'$arg1' or '$arg2' is not a number")
    }
}
*/

//or


/*
// ...
if (x == null) {
    println("Wrong number format in arg1: '$arg1'")
    return
}
if (y == null) {
    println("Wrong number format in arg2: '$arg2'")
    return
}

// x and y are automatically cast to non-nullable after null check
println(x * y)
*/


/*===============================
Type checks and automatic casts

타입 검사와 자동 캐스트
===============================*/


fun getStringLength(obj: Any): Int? {
    if (obj is String) {
        // `obj` is automatically cast to `String` in this branch
        return obj.length
    }

    // `obj` is still of type `Any` outside of the type-checked branch
    return null
}

//or

fun getStringLength1(obj: Any): Int? {
    if (obj !is String) return null

    // `obj` is automatically cast to `String` in this branch
    return obj.length
}

//or even


