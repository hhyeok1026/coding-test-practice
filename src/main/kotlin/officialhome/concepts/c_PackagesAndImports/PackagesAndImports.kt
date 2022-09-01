package officialhome.concepts.c_PackagesAndImports

/*
    Packages and imports





    package org.example

    fun printMessage() { ... }
    class Message { ... }

    위 함수와 클래스의 풀네임은 org.example.printMessage, org.example.Message이다.

    소스파일은 대게 패지키로 시작된다. 패키지가 없으면 이름 없는 기본 패키지로 된다.




    // ==================
    // Default imports
    // ==================

    기본적으로 코틀린의 많은 패키지가 임포트 된다.
    kotlin.*
    kotlin.annotation.*
    kotlin.collections.*
    kotlin.comparisons.*
    kotlin.io.*
    kotlin.ranges.*
    kotlin.sequences.*
    kotlin.text.*

    부가적으로
    jvm
        java.lang.*
        kotlin.jvm.*

    js
        kotlin.js.*



    // ==================
    // Imports
    // ==================

    기본 import 외에도 고유한 import가 있을 수 있다.

    import org.example.Message // 지정자 없이 message라고 쓸 수 있다.
    import org.example.* // 해당 스코프의 모든 컨텐츠에 접근 할 수 있다.


    만약에 이름이 충돌이 나게 되면, as키워드로 별칭(로컬 rename)을 붙이면 된다.
    import org.example.Message // Message is accessible
    import org.test.Message as testMessage // testMessage stands for 'org.test.Message'


    import 키워드는 클래스에만 국한되는게 아니다.
    - 최상위 함수, 프로퍼티
    - 객체 선언안에 선언된 함수와 프로퍼티
    - enum상수들.
    을 import할 수 있음.



    // ====================================
    // Visibility of top-level declarations
    // ====================================

    만약 최상위 선언이 private로 마크 되면, 선언된 파일에 대해 비공개이다.

    아래링크에 대해서 보면 됨.
    doc- concept - class and object - visibility modifiers
    https://kotlinlang.org/docs/visibility-modifiers.html


*/