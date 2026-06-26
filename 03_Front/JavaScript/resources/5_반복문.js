/*
    반복문
    - for / while / do-while
    - break / continue
    - for...of (값 순회) / for...in (key순회)
*/

// for : 반복 횟수가 정해져있을 때
// 1~7까지 반복
for(let i=1; i<=7; i++){
    console.log("회차 : " + i);
}

// while : 조건이 참인 동안 반복할 때
let i=1;
while(i<=7){
    console.log("회차 : " + i);
    i++;
}

// do-while : 1번은 무조건 실행하고 조건검사
i=1;
do{
    console.log("회차 : " + i);
    i++;
}while(i>7);

// break / continue
// break : 즉시 반복문을 종료
// continue : 이번회차는 이제 건너뛰고 다음회차로 이동
for(let j=1; j<=10; j++){
    if(j === 5) continue;
    if(j === 8) break;
    console.log(j);
}

// for...of : 배열의 값을 순회
const members = ["최지원", "김수민", "박지수"];
for(const name of members){
    console.log(name);
}

// for...in : 객체의 key를 순회
const std = {name:"김유안", java:75, db: 80};
for(const key in std){
    console.log(key);
    console.log(std[key]);
}

// 배열을 for...in으로 사용하면?
for(const i in members){
    console.log(i);
}