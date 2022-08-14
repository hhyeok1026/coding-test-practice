package officialhome.basics

/*
https://kotlinlang.org/docs/idioms.html
*/



//레이블용 주석
/*===============================
Create DTOs (POJOs/POCOs)
===============================*/

data class Customer(val name: String, val email: String)



/*===============================
Default values for function parameters
===============================*/

fun foo(a: Int = 0, b: String = ""){}



/*===============================
Filter a list
===============================*/

// val positives = list.filter { x -> x > 0 }


//shorter
// val positives = list.filter { it > 0 }



/*===============================
Check the presence of an element in a collection
컬렉션에 element가 있는지 체크
===============================*/

/*
if ("john@example.com" in emailsList) { ... }

if ("jane@example.com" !in emailsList) { ... }
*/



/*===============================
String interpolation
(문자열 보간)
===============================*/

// println("Name $name")



/*===============================
Instance checks
===============================*/

/*
    when (x) {
        is Foo -> ...
        is Bar -> ...
        else -> ...
    }
*/



/*===============================
Read-only list
===============================*/

// val list = listOf("a", "b", "c")



/*===============================
Read-only map
===============================*/

// val map = mapOf("a" to 1, "b" to 2, "c" to 3)




/*===============================
Access a map entry
===============================*/

/*
println(map["key"])
map["key"] = value
*/



/*===============================
Traverse(트레버스) a map or a list of pairs
맵 또는 리스트의 쌍 탐색
===============================*/

/*
for ((k, v) in map){
    println("$k -> $v")
}
*/



/*===============================
Iterate over a range
===============================*/

/*
for (i in 1..100) { ... }  // closed range: includes 100
for (i in 1 until 100) { ... } // half-open range: does not include 100
for (x in 2..10 step 2) { ... }
for (x in 10 downTo 1) { ... }
(1..10).forEach { ... }
*/



/*===============================
Lazy property
===============================*/

/*
val P: String by lazy { // the value is computed only on first access
    // compute the string
}
*/



/*===============================
Extension functions
===============================*/

/*
fun String.spaceToCamelCase() { ... }

"Convert this to camelcase".spaceToCamelCase
*/




/*===============================
Create a singleton
===============================*/

object Resource {
    val name = "Name"
}




/*===============================
Instantiate an abstract class
===============================*/

/*
abstract class MyAbstractClass {
    abstract fun doSomething()
    abstract fun sleep()
}

fun main() {
    val myObject = object: MyAbstractClass(){
        override fun doSomething() {
            //...
        }

        override fun sleep() {
            //...
        }
    }
    myObject.doSomething()
}
*/




/*===============================
If-not-null shorthand
null이 아닌 경우 속기
===============================*/

/*
val files = File("test").listFiles()

println(files?.size) // size is printed if file is not null
*/



/*===============================
If-not-null-else shorthand
===============================*/

/*
val files = File("Test").listFiles()

println(files?.size ?: "empty") // if files is null, this prints "empty"

//To calculate the fallback value in a code block, use `run`
val filesSize = files?.size ?: run {
    return someSize
}
println(fileSize)
*/



/*===============================
Execute a statement if null
===============================*/

/*
val values = ...
val email = values["email"] ?: throw IllegalStateException("Email is missing!")
*/



/*===============================
Get first item of a possibly empty collection
비어 있을 수 있는 컬렉션에서 첫번째 값 가져오기
===============================*/

/*
val emails = ... // might be empty
val mainEmail = emails.firstOrNull() ?: ""
*/



/*===============================
Map nullable value if not null
===============================*/

/*
val value = ...

val mapped = value?.let { transformValue(it) } ?: defaultValue
// defaultValue is returned if the value or the transform result is null.
*/



/*===============================
Return on when statement
===============================*/

fun transform(color: String): Int {
    return when (color) {
        "Red" -> 0
        "Green" -> 1
        "Blue" -> 2
        else -> throw IllegalStateException("Invalid color param value")
    }
}



/*===============================
try-catch expression
===============================*/

/*
fun test() {
    val result = try {
        count()
    } catch (e: ArithmeticException){
        throw IllegalStateException(e)
    }

    //Working with result
}
*/



/*===============================
if expression
===============================*/

/*
val y = if ( x == 1) {
    "one"
} else if ( x == 2){
    "two"
} else {
    "other"
}
*/




/*===============================
Builder-style usage of methods that return Unit
===============================*/

fun arrayOfMinusOnes(size: Int): IntArray {
    return IntArray(size).apply { fill(-1) }
}



/*===============================
Single-expression functions
===============================*/

/*
fun theAnswer() = 42

//equals
fun theAnswer(): Int {
    return 42
}

//when절로 응용
fun transform(color: String): Int = when (color) {
    "Red" -> 0
    "Green" -> 1
    "Blue" -> 2
    else -> throw IllegalArgumentException("Invalid color param value")
}
*/




/*===============================
Call multiple methods on an object instance (with)
with 를 사용하는 예시.
===============================*/

/*
class Turtle {
    fun penDown()
    fun penUp()
    fun turn(degrees: Double)
    fun forward(pixels: Double)
}

val myTurtle = Turtle()

with(myTurtle) { //draw a 100 pix square
    penDown()
    for (i in 1..4) {
        forward(100.0)
        turn(90.0)
    }
    penUp()
}
*/



/*===============================
Configure properties of an object (apply)
속성값을 apply로 적용하는 예시
===============================*/

/*
val myRectangle = Rectangle().apply {
    length = 4
    breadth = 5
    color = 0xFAFAFA
}
*/



/*===============================
Java 7's try-with-resources
===============================*/

/*
val stream = Files.newInputStream(Path.get("/some/file.txt"))
stream.buffered().reader().use { reader ->
    println(reader.readText())
}
*/



/*===============================
Generic function that requires the generic type information
-> TODO 이건 이해가 안되네.
===============================*/

//  public final class Gson {
//     ...
//     public <T> T fromJson(JsonElement json, Class<T> classOfT) throws JsonSyntaxException {
//     ...

// inline fun <reified T: Any> Gson.fromJson(json: JsonElement): T = this.fromJson(json, T::class.java)



/*===============================
Nullable Boolean
===============================*/

/*
val b: Boolean? = ...
if (b == true) {
    ...
} else {
    // `b` is false or null
}
*/



/*===============================
Swap two variables
TODO: also라는 키워드는 뭐지?
===============================*/

/*
var a = 1
var b = 2

a = b.also { b = a }
*/



/*===============================
Mark code as incomplete ( TODO )
===============================*/

/*
fun calcTaxes(): BigDecimal = TODO("Waiting for feedback from accounting")
*/

/*===============================
===============================*/

/*===============================
===============================*/

/*===============================
===============================*/

/*===============================
===============================*/

/*===============================
===============================*/

/*===============================
===============================*/

/*===============================
===============================*/

/*===============================
===============================*/

/*===============================
===============================*/