package officialhome.basics.kotlinByExample.h_ProductivityBoosters

import java.time.LocalDate
import java.time.chrono.ChronoLocalDate

// Smart Casts

// 코틀린 컴파일러는 대부분 알아서 캐스팅을 수행한다, 아래를 포함해서,
// null 타입을 non-null타입으로 캐스팅
// super 타입을 sub타입으로.

fun main() {

    val date: ChronoLocalDate? = LocalDate.now()

    if (date != null) {
        println(date.isLeapYear)
    }

    if (date != null && date.isLeapYear) {
        println("It's a leap year")
    }

    if (date != null && !date.isLeapYear) {
        println("There's no Feb 29 this year...")
    }

    if (date is LocalDate) {
        val month = date.monthValue
        println(month)
    }
}