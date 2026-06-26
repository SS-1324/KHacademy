/*
    함수
    - 선언적 함수 / 함수 표현식
    - 함수는 일급객체 : 담기 / 인자전달 / 반환 가능
    - 오버로딩 미지원, arguments 객체
    - 화살표 함수
*/

/* 
    선언적 함수
    function 함수이름(){
        내용
    }
*/
function test1(){
    console.log("test함수 실행");
}

test1();

// js에서 함수는 일급 객체이므로
// - 변수에 담을 수 있고
// - 다른 함수의 인자로 전달할 수 있고
// - 함수의 결과로 반환할 수도 있다.

// 익명함수
// 함수를 변수에 담아 사용할 수 있기 때문에 이름을 따로 정하지 않아도 됨.
const test2 = function(){
    console.log("test2함수 실행");
}

test2();

// 오버로딩 미지원 + arguments
// js는 매개변수 개수가 달라도 같은 이름이면 동일 함수로 인식
// 모든 함수는 전달된 모든 인자가 담긴 arguments를 가진다.
const test4 = function(name){
    console.log(arguments)
    console.log("name", name);
}

test4(); //js에서는 매개변수를 꼭 맞추지 않아도 됨.
test4("김지인");
test4("김지인", 55, "경기도 파주시");

/* 
    화살표 함수
    const 변수명 = (매개변수) => 실행할 내용(실행할 코드가 한줄일 때 {}생략가능)
*/
//const test5 = () => {return 200;}

//함수의 기능이 딱히 없고 한줄로 끝난다면 {}를 생략할 수 있다.
const test5 = () => 200;
console.log(test5());

const add = (a, b) => a+b;
console.log(add(10,20));

const sumAll = (...args) => {
    // console.log(arguments); 화살표함수에는 arguments가 없다
    // -> 가변인자로 나머지 매개변수를 받을 수 있다.
    console.log(args);
}
sumAll(10,20,30);

// 함수를 인자로 / 반환값으로
function run(callback){
    console.log("작업 시작");
    callback();
}

run(function(){
    console.log("콜백이 실행됨.");
})

function multi(x){
    return function(y){
        return x*y;
    };
}

const db = multi(2);
console.log(db(10)); //20
