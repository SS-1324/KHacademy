/*
    - 객체: 중괄호 {}안에 속성명:속성값 (key:value)형태로 정의
    - 속성값으로 모든 자료형(문자/숫자/배열/함수)를 담을 수 있음.
*/

const product = {
    pName: "김밥",
    price: 4000,
    kind: "한식",
    ingredient: ["김", "밥"],
}

console.log(product["pName"]);
console.log(product.price);

product.price = 5000;
product.origin = "한국";
console.log(product);

// 메서드
// 객체안에있는 함수
// 메서드 안에서는 this를 사용, this는 그 메서드를 호출한 객체를 가르킴.
const jiwon = {
    name: "최지원",
    eat: function({pName}){
        console.log(this.name + "은 " + pName + "를 먹는다.")
    },
}

jiwon.eat(product);

//객체배열
const stdList = [
    {id: 1, name: "최지원", java:75, db: 80},
    {id: 2, name: "김지원", java:60, db: 70},
    {id: 3, name: "이지원", java:90, db: 95},
]

//각 학생에 총점/ 평균 메서드를 추가
for(let i=0; i<stdList.length; i++){
    stdList[i].totalScore = function(){
        return this.java + this.db;
    }

    stdList[i].avgScore = function(){
        return this.totalScore() / 2;
    }
}

console.log(stdList[1].totalScore());
console.log(stdList[1].avgScore());

//단축속성
const name = "김지민";
const age = 20;
const user = {name, age};
console.log(user);