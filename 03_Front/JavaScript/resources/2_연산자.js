/*
    연산자
    - 산술 / 대입 / 비교 / 논리
    - 삼한연산자, ?.
*/

// 산술연산자 + - * / % **
console.log(10 + 3);
console.log(10 % 3);
console.log(2 ** 10); //거듭제곱

// 대입연산자 = += *= /= -= %= 
let x = 10;
x += 5;
console.log(x);

// 비교연산자 >, <, >=, <=, !=
// == : 타입비교x, 값비교o
// === : 타입비교o, 값비교o
console.log(55 == "55");
console.log(55 === "55");
console.log(55 != "55");
console.log(55 !== "55");

// 논리연산자 && || !
// &&, ||는 단순하게 true/false를 판단하는게 아니라 "값"을 반환(단축평가)
// A && B : A가 false A, true면 B
// A || B : A가 true A, false면 B
console.log(true && "결과");
console.log(false && "결과");
console.log("값" || "기본값");
console.log("" || "기본값");

function greet(name){
    name = name || "손";
    console.log("안녕하세요 . " + name + "님.");
}

greet(); 
greet("최지원");

const user = {name : "지원"};
user && console.log(user.name);

// 삼항연산자 조건 ? A : B
const age = 20;
const msg = age >= 19 ? "성인" : "미성년";
console.log(msg);

// ?? null병합연산 : 왼쪽이 null/undefined일때 오른쪽 사용
const count = 0;
console.log(count || 10); //0은 false로 간주되어 10이 출력
console.log(count ?? 10); //0은 null/undefined가 아니기 때문에 0출력

// ?. 옵셔널 : 값이 없으면 에러없이 nudefined반환
// const data = {user : {
//     name: "jiwon"
// }}
const data = {user : null}
console.log(data.user?.name);