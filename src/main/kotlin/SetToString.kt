
/*
gpt와 함께 split에 대해서 고민해보는 시간을 가졌다.
 */

fun main() {
    val set = setOf("a,b", "c")
    val array = set.toTypedArray()
    val str = array.joinToString(separator = ",")
    val result = str.split(",").toTypedArray()

    println(result.contentToString())
}