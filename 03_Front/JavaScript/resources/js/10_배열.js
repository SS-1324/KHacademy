/*
    배열 : 값을 순서대로 나열하는 자료구조
*/

//배열생성
// 변수 = [];
let members = ["최지원", "김지원", "이지원"];

members.push("박지원"); // 맨 뒤에 추가
console.log(members);

console.log(members.pop()); // 맨 뒤에 값을 가져오고 배열에서 제거
console.log(members);

members.unshift("신지원"); // 맨 앞에 추가
console.log(members);

console.log(members[0]);
console.log(members[2]);
members[1] = "우지원";
console.log(members);

// splice : 배열에서 값을 잘라냄(원본이 변경)
let arr = ["a","b","c","d","e"];

// 배열.splice(인덱스, 몇개)
let spliceArr = arr.splice(1,2); // ["b","c"];
console.log(spliceArr);
console.log(arr);

// slice : 배열에서 값을 추출(원본유지)
arr = ["a","b","c","d","e"];

// 배열.slice(시작 인덱스, 끝 인덱스) : 끝 인덱스 - 1
spliceArr = arr.slice(0,3); // ["a","b","c"];
console.log(spliceArr);
console.log(arr);

// 검색
let colors = ["red", "green", "blue"];
console.log(colors.indexOf("green")); // 있으면 index를 반환 없으면 -1
console.log(colors.includes("blue")); // true / false

// 문자열 변환
members = ["최지원", "김지원", "이지원"];
// 배열.join(구분자)
console.log(members.join()); // ,를 구분자로한 문자열로 변환
console.log(members.join("/"));

// 정렬
members.reverse();
console.log(members);

members.sort(); //오름차순
console.log(members);

// sort의 기준을 전달
// 배열.sort(기준함수)
// a를 b보다 뒤로 -> 0보다 큰 수 반환
// a를 b보다 앞으로 -> 0보다 작은 수 반환
// 순서를 유지 -> 0반환

// 오름차순 기준  (a, b) => a - b;
// 오름차순 기준  (a, b) => b - a;
let nums = [10,3,25,7];
nums.sort((a, b) => a - b);
console.log(nums);

nums.sort((a, b) => b - a);
console.log(nums);

let stdList = [
    {name: "최지원", java:75},
    {name: "김지원", java:60},
    {name: "이지원", java:90},
]

stdList.sort((a,b) => a.java - b.java); // java점수로 오름차순
console.log(stdList);
