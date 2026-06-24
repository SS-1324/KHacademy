/*
    호이스팅
    js엔진은 코드 실행 전에 모든 변수, 함수를 전부 상위로 가져와 선언함.
    
    - 선언이 아래에 있어도 위로 끌어올려진 것처럼 보임.
    - 변수가 이동하는게 아니라 선언만 먼저 되는 것.
*/

// 선언 전에 접근해도 에러가 아니라 undefuned값이 나옴.
console.log("선언 전 : " + name1);
var name1 = "jiwon";
console.log("선언 후 : " + name1);

// let/const : 호이스팅은 되지만 TDZ
// 일시적으로 선언코드 전까지는 TDZ에 기록해서 접근할 수 없게함.

//console.log(name3);
const name2 = "지수";
let name3 = "지민";
console.log(name2, name3);

//함수도 호이스팅 대상
//전체함수가 먼저 등록되므로 선언전에 호출 가능.
hello();
function hello(){
    console.log("안녕하세요.");
}

// 익명함수를 만들고 변수에 할당해도 변수의 호이스팅 규칙상 선언전 사용 x
// bye();
const bye = function(){
    console.log("잘가요");
}