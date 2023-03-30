
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class SetToStringTest {
    @Test
    fun `test set to string to array`() {
        val set = setOf("a,b;c", "d")
        val array = set.toTypedArray()
        val str = array.joinToString(separator = ";")
        val result = str.split(Regex(";(?=(?:[^\"]*\"[^\"]*\")*[^\"]*\$)")).toTypedArray()

        assertEquals(arrayOf("a,b;c", "d").contentToString(), result.contentToString())
    }
}