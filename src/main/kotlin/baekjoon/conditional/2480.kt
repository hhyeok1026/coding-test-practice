package baekjoon.conditional

//TODO 1,2,3 다 비교하는게 아니고 반복문을 써야함.
//주사위 3개
//입력 -  주사위 3개
//출력 -  게임의 상금
fun main(){

    //최대 상금 16000
    var reward = 0

    val input : String = readLine() ?: return

    val one = input.split(" ")[0].toInt()
    val two = input.split(" ")[1].toInt()
    val three = input.split(" ")[2].toInt()

    //같은게 있나? 확인하는 메서드.
    if(haveSame(one, two, three)){
        //같은게 몇개인지? -> //같은거에 대한 추가금 플러스
        val sameCount : Int = sameCount(one, two, three)
        //같은게 있는 경우, 같은 눈금이 뭔지 찾는 메서드. * 같은 갯수에 따른 추가 곱
        val sameNumber : Int = sameNumber(one, two, three)

        if(sameCount == 2){
            reward = 1000 + sameNumber * 100
        }else if(sameCount == 3){
            reward = 10000 + sameNumber * 1000
        } //else없음.


    }else{
        //같은게 없으면, 가장 큰거를 찾는 메서드
        val maxNumber : Int = maxNumber(one, two, three)

        //가장 높은 눈 x100
        reward = maxNumber * 100
    }

    println(reward)
}

fun maxNumber(one: Int, two: Int, three: Int): Int {

    val max : Int

    if (one >= two){
        if(one >= three){
           max = one
        }else{
           max = three
        }
    }else{
        if(two >= three){
            max = two
        }else{
            max = three
        }
    }

    return max
}

//무조건 같은게 있다는 가정하에 작성.
fun sameNumber(one: Int, two: Int, three: Int): Int {
    if(one == two || one == three){ //1이랑 같은게 있으면 1
        return one
    }else{  //1이랑 같은게 아니면 2,3이 같을거임.
        return two
    }
}

fun sameCount(one: Int, two: Int, three: Int): Int {

    //1은 같은게 없을때
    //2는 2개가 같을때
    //3은 3개가 같을때
    var sameCount : Int = 1

    if(one == two || one == three){
        sameCount++
        if(two == three){
            sameCount++
            return sameCount
        }else{
            return sameCount
        }
    }else{
        if(two == three){
            sameCount++
            return sameCount
        }else{
            return sameCount
        }
    }
}

fun haveSame(one: Int, two: Int, three: Int): Boolean {
    return one == two || one == three || two == three
}