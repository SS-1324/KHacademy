/*
    스코프
    - 변수/함수가 접근할 수 있는 유효 범위
    - 값을 찾을 때 : 내부 -> 외부 -> 전역순으로 거슬러 올라감(스코프 체인)

    종류
    - 전역스코프 : 코드 어디서든 접근 가능
    - 함수스코프 : 함수 내부에서만 접근 가능(var)
    - 블록스코프 : {} 내부에서만 접근이 가능(let / const)
    - 렉시컬 스코프 : "선언된 위치"기준으로 스코프를 결정(js)
*/

// 기본적으로 함수 내부에 값이 없으면 전역에서 값을 찾는다.
var num1 = 20;

function test1(){
    console.log(num1); // 전역에있는 20 
}

function test2(){
    var num1 = 40;
    console.log(num1); // 내부에있는 40
}

test1();
test2();

/*
    렉시컬 스코프
    함수가 "실행된 위치"가 아니라 "선언된 위치"기준으로 스코프를 결정.
    (실행위치 기준은 동적스코프 -> js는 동적 스코프가 아니다.)
*/

var age = 20;

function test3(){
    var age = 40;
    test4();
    console.log("test3 : " + age);
}

function test4(){
    console.log("test4 : " + age);
}

test3();
console.log("전역 age : " + age);

//var(함수 스코프) vs let/const(블록스코프)
var i = 1000;
for(var i=0; i<10; i++){
    //i사용~~
}
console.log("i = " + i);

let j = 1000;
for(let j=0; j<10; j++){
    //j사용~~
}
console.log("j = " + j);