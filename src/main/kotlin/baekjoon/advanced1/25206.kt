package baekjoon.advanced1

import java.util.StringTokenizer

/*

input
- 20줄에 걸쳐서, 과목명, 학점, 등급이 공백으로 입력

output
- 전공평점을 출력 (정답과의 절대오차, 상대오차는 10의 -4승이하로)
(전공평점은 학점 x 과목평점의 합을 학점의 총합으로 나눈다.)

p/f 과목은 계산에서 제외


 */


fun main(args: Array<String>) {
    val br = System.`in`.bufferedReader()
    var totalScore = 0.0f
    var totalTime = 0.0f

    repeat(20) escapeWhen@{
        val st = StringTokenizer(br.readLine())
        val subjectName = st.nextToken()
        val time =  st.nextToken().toFloat()
        val grade = st.nextToken()

        when (grade) {
            "A+" -> {
                totalScore += (4.5f * time)
                totalTime += time
            }
            "A0" -> {
                totalScore += (4.0f * time)
                totalTime += time
            }
            "B+" -> {
                totalScore += (3.5f * time)
                totalTime += time
            }
            "B0" -> {
                totalScore += (3.0f * time)
                totalTime += time
            }
            "C+" -> {
                totalScore += (2.5f * time)
                totalTime += time
            }
            "C0" -> {
                totalScore += (2.0f * time)
                totalTime += time
            }
            "D+" -> {
                totalScore += (1.5f * time)
                totalTime += time
            }
            "D0" -> {
                totalScore += (1.0f * time)
                totalTime += time
            }
            "F" -> {
                totalScore += (0.0f * time)
                totalTime += time
            }
            "P" -> return@escapeWhen
        }
    }

    /*for (i in 0 until 20) {
        val st = StringTokenizer(br.readLine())
        val subjectName = st.nextToken()
        val time =  st.nextToken().toFloat()
        val grade = st.nextToken()

        when (grade) {
            "A+" -> {
                totalScore += (4.5f * time)
                totalTime += time
            }
            "A0" -> {
                totalScore += (4.0f * time)
                totalTime += time
            }
            "B+" -> {
                totalScore += (3.5f * time)
                totalTime += time
            }
            "B0" -> {
                totalScore += (3.0f * time)
                totalTime += time
            }
            "C+" -> {
                totalScore += (2.5f * time)
                totalTime += time
            }
            "C0" -> {
                totalScore += (2.0f * time)
                totalTime += time
            }
            "D+" -> {
                totalScore += (1.5f * time)
                totalTime += time
            }
            "D0" -> {
                totalScore += (1.0f * time)
                totalTime += time
            }
            "F" -> {
                totalScore += (0.0f * time)
                totalTime += time
            }
            "P" -> continue
        }
    }*/

    println(totalScore / totalTime)
    br.close()
}