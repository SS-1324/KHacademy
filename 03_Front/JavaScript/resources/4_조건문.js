/*
    js의 조건문
    - if / else if / else
    - switch
    - 삼항연산자
*/

// if ~ else if ~ else
const score = 82;
let grade;

if(score >= 90) {grade = "A";}
else if(score >= 80) {grade = "B";}
else if(score >= 70) {grade = "C";}
else {grade = "D";}

console.log(grade);

const name = "";
if(name) {
    console.log("이름 있음");
} else {
    console.log("이름 없음");
}

// switch
// 하나의 값을 여러 경우와 === 비교할때

// 0-> 일요일 ~ 6:토요일
const day = 3;
switch(day){
    case 1: 
        console.log("월");
        break;
    case 2: 
        console.log("화");
        break;
    case 3: 
        console.log("수");
        break;
    case 4: 
        console.log("목");
        break;
    case 5: 
        console.log("금");
        break;
    case 6: 
        console.log("토");
        break;
    default: 
        console.log("일");
}

//fall-through를 의도적으로 활용
const month = 12;
switch(month){
    case 12:
    case 1:
    case 2:
        console.log("겨울");
        break;
    case 3:
    case 4:
    case 5:
        console.log("봄");
        break;
    case 6:
    case 7:
    case 8:
        console.log("여름");
        break;
    default:
        console.log("가을");
}

// 삼항연산자
const age = 17;
console.log(age >= 19 ? "성인" : "미성년");