package officialhome.basics.kotlinByExample.i_kotlinJs

// External declarations
// external 키워드는 타입 세이프한 방식으로 기존 자바스크립트 api를 선언 할 수 있다.

external fun alert(msg: String)

fun main() {
    alert("Hi!")
}