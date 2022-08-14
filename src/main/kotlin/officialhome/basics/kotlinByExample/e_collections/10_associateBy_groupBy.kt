package officialhome.basics.kotlinByExample.e_collections

// associateBy, groupBy
// -> 특정 key로 index한 요소들을 map으로 빌드한다.

// associateBy -> 마지막으로 적합한 요소를 사용한다. -> 단순히 맵을 만들다가 겹치는게 있으면 마지막 값으로 만드는거고 -> 겹칠게 없는거를 key값으로 쓰는게 좋겠네.
// groupBy -> 모든 요소 목록을 만들어 값에 넣는다. -> 얘는 동일한 애가 있으면 배열로 묶어버리는듯.


fun main() {
    data class Person(val name: String, val city: String, val phone: String)

    val people = listOf(
        Person("John", "Boston", "+1-888-123456"),
        Person("Sarah", "Munich", "+49-777-789123"),
        Person("Svyatoslav", "Saint-Petersburg", "+7-999-456789"),
        Person("Vasilisa", "Saint-Petersburg", "+7-999-123456")
    )

    val phoneBook = people.associateBy { it.phone }
    val cityBook = people.associateBy(Person::phone, Person::city)

    val peopleCities = people.groupBy(Person::city, Person::name)
    val lastPersonCity = people.associateBy(Person::city, Person::name)

    println(phoneBook)
    println(cityBook)
    println(peopleCities)
    println(lastPersonCity)
}

