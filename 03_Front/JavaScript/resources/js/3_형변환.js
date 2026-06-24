/*
    - 명시적 형변환
    - 묵시적 형변환
*/

// 명시적 형변환
console.log(Number("123")); //123
console.log(Number("12px")); // NaN : 숫자로 표현할 수 없다.
console.log(parseInt("12px")); //숫자만 변환
console.log(parseFloat("100.75kg"));
console.log(String(123)); // "123"
console.log((123).toString()); // "123"
console.log(Boolean(0)); //false
console.log(!!"hello"); //false

// 묵시적 형변환
// + 에 문자열이 섞이면 -> 문자열 연결
console.log(1 + "1"); // "11"
console.log("5" + 3); // "53"

// - * / -> 숫자로 연산(문자열을 숫자로 변환)
console.log(1 - "1");
console.log("5" * "2");
console.log(true - "2");

/* 
    true / false로 취급되는 값
    false값 : false, 0, -0, "", null, undefined, NaN
    위 7개를 제외한 모든 값은 true
    [] -> true, {} -> true, "0" -> true
*/
if("") console.log("실행될까?");
if([]) console.log("배열이 존재하니?");
if("0") console.log("true일까?");

const emptyArr = [];
if(emptyArr) console.log("배열 존재함");
if(emptyArr.length === 0) console.log("배열 비어있음");

