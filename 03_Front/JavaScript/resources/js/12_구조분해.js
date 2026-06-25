/*
    스프레드연산자와 구조분해할당
    - 스프레드(...) : 배열/객체의 요소를 펼처서 복사, 전달 사용
    - 구조분해할당 : 배열/객체에서 값을 추출해 개별변수에 할당
*/

// 스프레드 : 배열
let members = ["최지원", "이지원"];
let members2 = [...members, "김지원", "박지원"];
console.log(members2);

let a = [1,2];
let b = [3,4];
console.log([...a, ...b]);

// 스프레드 : 객체
let user1 = {
    id: "user01",
    pwd: "pass01",
    name: "jiwon",
    age : 20,
    height: 180.5,
};
// user1.pwd = "pass02"

//user1의 pwd를 수정
user1 = {...user1, pwd : "pass02"};
console.log(user1);

//user1에 주소를 추가
user1 = {...user1, address: "경기도 안양시"};
console.log(user1);

// 개수를 알 수 없는 여러개의 매개변수를 받을 때
function sumAll(...args){
    console.log(args)
    return args.reduce((sum, v)=> {
        sum = sum+v;
        return sum;
    }, 0)
}

console.log(sumAll(1,2,3,4,8,9,10));

// 배열의 구조분해 할당(순서 기반)
const list = ["최지원", "김지원", "이지원"];
//배열의 각 요소를 변수로 만들어서 쓰고싶다.
const [choi, kim] = list;
console.log(choi, kim);

//일부 건너뛰기
const [first, , third] = list;
console.log(first, third);

// 일부 가져오고 나머지는 한번에
const [haed, ...rest] = list;
console.log(haed, rest);

// 객체 구조분해(key 기반)
user1 = {
    id: "user01",
    pwd: "pass01",
    name: "jiwon",
    age : 20,
    height: 180.5,
};

const {name, age} = user1;
console.log(name, age);

const {name: userName, age: userAge} = user1;
console.log(userName, userAge);

//key가 없을시 기본값 부여 가능.
const {job = "무직"} = user1;
console.log(job);

function printUser({name, age}){
    console.log("제 이름은 " + name + "이고 나이는 " + age + "살 입니다.");
}

printUser(user1);