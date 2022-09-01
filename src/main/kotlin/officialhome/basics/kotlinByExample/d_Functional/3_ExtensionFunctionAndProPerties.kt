package officialhome.basics.kotlinByExample.d_Functional

// Extension Functions and Properties

data class Item(val name: String, val price: Float)
data class Order(val items: Collection<Item>)

//확장 function
fun Order.maxPricedItemValue(): Float = this.items.maxByOrNull { it.price }?.price ?: 0F
fun Order.maxPricedItemName() = this.items.maxByOrNull { it.price }?.name ?: "NO_PRODUCTS"

//확장 property
val Order.commaDelimitedItemNames: String
    get() = items.map { it.name }.joinToString()

fun main() {

    val order = Order(listOf(Item("Bread", 25.0F), Item("Wine", 29.0F), Item("Water", 12.0F)))

    println("Max priced item name: ${order.maxPricedItemName()}")
    println("Max priced item value: ${order.maxPricedItemValue()}")
    println("Items: ${order.commaDelimitedItemNames}")
}

// 확장함수에서 null 체크를 할 수 있다.
// fun <T> T?.nullSafeToString() = this?.toString() ?: "NULL"  // 1