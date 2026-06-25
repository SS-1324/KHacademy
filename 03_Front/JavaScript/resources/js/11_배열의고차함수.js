// 배열의 고차함수
// - 고차함수 : 함수를 인자로받는 함수
// - 대부분 원본을 바꾸지않고 새로운 배열을 만들어서 반환 (불변성을 유지)

let stdList = [
    {id: 1, name: "최지원", java:75, db: 80},
    {id: 2, name: "김지원", java:60, db: 70},
    {id: 3, name: "이지원", java:90, db: 95},
]

// forEach(함수) : 전체 순회
stdList.forEach((v, i) => {
    console.log(i + "번째 : " + v.name);
});

/*
function forEach(callback){
    for(let i=0; i<stdList.length; i++){
        callback(stdList[i], i);
    }
}
*/

// map(함수) : 기존배열을 가지고 새로운 배열을 만듬
// 각 요소를 전달해서 리턴받은 값을 가지고 새로운 배열 만듬
let nums = [1,2,3];
let doubled = nums.map((num) => num * 2);
console.log(doubled)

const names = stdList.map(std => std.name);
console.log(names);

// filter(함수) : 조건을 통과한 요소만 남긴 새로운 배열 만듬
// 각요소를 전달해서 리턴값이 true인 요소만 남긴 새로운 배열 만듬
nums = [1,2,3,4,5,6,7,8,9,10];
const even = nums.filter(num => num % 2 === 0);
console.log(even);

stdList = [
    {id: 1, name: "최지원", java:75, db: 80},
    {id: 2, name: "김지원", java:60, db: 70},
    {id: 3, name: "이지원", java:90, db: 95},
]
//id가 2인 학생 삭제
stdList = stdList.filter(std => std.id !== 2);
console.log(stdList);

//id가 3인 학생의 이름을 김지원으로 변경
stdList = stdList.map(std => {
    if(std.id === 3) {
        std.name = "김지원";
    }
    return std;
});

// reduce : 하나의 값으로 누적
// reduce((누적값, 요소) => {...}, 초기값)
const scoreList = [10,20,30];
const total = scoreList.reduce((sum, cur) => {
    sum += cur;
    return sum;
}, 0)

console.log(total);

stdList = [
    {id: 1, name: "최지원", java:75, db: 80},
    {id: 2, name: "김지원", java:60, db: 70},
    {id: 3, name: "이지원", java:90, db: 95},
]

//stdList의 java점수의 총 합을 구해라
const javaSum = stdList.reduce((sum, std) => {
    sum += std.java;
    return sum;
}, 0)
console.log(javaSum);

// find / findIndex
// find : 조건을 만족하는 첫 요소(없으면 undefined)
const found = stdList.find(std => std.name === "김지원");
console.log(found); 

// findIndex : 조건을 만족하는 첫 요소의 인덱스 반환(없으면 -1)
const idx = stdList.findIndex(std => std.name === "김지원");
console.log(idx); 


// some, every
const arr = [1,2,3,4,5];
console.log(arr.some(n => n > 3)); // 조건을 만족시키는 값이 하개라도 있으면 true
console.log(arr.every(n => n > 3)); // 전부 조건을 만족시면 true

stdList = [
    {id: 1, name: "최지원", java:75, db: 80},
    {id: 2, name: "김지원", java:60, db: 70},
    {id: 3, name: "이지원", java:90, db: 95},
]

// db점수가 80점 이상인 학생들의 이름만 저장
const result = stdList
                .filter(std => std.db >= 80)
                .map(std => std.name);

console.log(result);